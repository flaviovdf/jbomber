/*
	Esta classe é para uso exclusivamente acadêmico, é absolutamente
  proibido a cópia total ou parcial de qualquer parte deste.

	Todos os direitos reservados.
	Universidade Federal de Campina Grande.
*/
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Iterator;
import java.util.Vector;
/**
 * Classe que implementa o Tabuleiro para execução do jogo
 *  @author Felipe Ribeiro
 *  @author Flávio Roberto Santos
 *  @author Flávio Vinicius
 *  @author João Arthur
 *  @author Thiago Emmanuel
 *  @author Vinicius Ferraz
 *  @version 1.0
 */
public class Tabuleiro implements Constantes {

  private final int
      LIVRE = 0, OBSTACULO = 1, BLOCO = 70,
      BOMBA_SEM_COLISAO = 128, BOMBA_COM_COLISAO = 256;
  public final int NUMERO_FASES = 2;

  private int[][] matriz = new int[13][15];
  private int numeroFase = 0;
  private Itens itens;
  private Jogador jogador;
  private Vector inimigos = new Vector();

  /**
   * Cria um tabuleiro logico para o jogo
   * @param j O jogador do jogo
   * @param numeroFase Número da fase que o tabuleiro representará
   * @param listener Tratador de eventos dos personagens
   */
  public Tabuleiro(Jogador j, int numeroFase, PersonagemListener listener) {
    // configura a matriz com blocos destrutiveis e indestrutiveis
    setFase(numeroFase);
    carregaEsqueleto(numeroFase);
    // carrega os itens atras dos blocos
    itens = new Itens(this);
    // reajusta o jogador
    jogador = j;
    jogador.setLeft(LADO);
    jogador.setTop(LADO - SUP);
    jogador.ficarInvensivel();
    // insere inimigos no tabuleiro
    distribuirInimigos(listener);
  }

  private void carregaEsqueleto(int numeroFase) {
    BufferedReader in;
    try {
      in = new BufferedReader(new FileReader("fases/" + numeroFase + ".txt"));
      // carrega a matriz que representa o tabuleiro
      for (int i = 0; i < getNumeroLinhas(); i++) {
        for (int j = 0; j < getNumeroColunas(); j++) {
          matriz[i][j] = Character.getNumericValue((char)in.read());
        }
        // libera o restante do stream (da linha atual, somente)
        in.readLine();
      }
      // fecha o arquivo
      in.close();
    } catch (FileNotFoundException e) {
      System.err.println("O arquivo fases/" + numeroFase + ".txt nao foi encontrado!");
      System.exit(1);
    } catch (Exception e) { }
    // distribui os blocos destrutiveis
    distribuirBlocos();
  }

  private void distribuirBlocos() {
    // cria os blocos na matriz principal do jogo...
    // e os carrega no vetor de blocos
    for (int nBlocos = 0; nBlocos < 40; nBlocos++) {
      int linha, coluna;
      // seleciona posicao dos blocos evitando colocar bloco nas posicoes (1,1) (1,2) (2,1)
      do {
        linha = (int)(Math.random() * (getNumeroLinhas() - 2)) + 1;
        coluna = (int)(Math.random() * (getNumeroColunas() - 2)) + 1;
      } while (linha + coluna < 4 || !estaLivre(linha, coluna));
      // coloca um bloco temporario na matriz
      colocaBloco(linha, coluna);
    }
  }

  private void distribuirInimigos(PersonagemListener listener) {
    // cria os blocos na matriz principal do jogo...
    // e os carrega no vetor de blocos
    for (int nInimigos = 0; nInimigos < ((getFase() + 1) * 5); nInimigos++) {
      int linha, coluna;
      // seleciona posicao dos blocos evitando colocar bloco nas posicoes (1,1) (1,2) (2,1)
      do {
        linha = (int)(Math.random() * (getNumeroLinhas() - 2)) + 1;
        coluna = (int)(Math.random() * (getNumeroColunas()- 2)) + 1;
      } while (linha + coluna < 8 || !estaLivre(linha, coluna));
      // coloca um bloco temporario na matriz
      inimigos.add(new Inimigo(coluna * LADO, linha * LADO - SUP, Inimigo.BAIXO, listener));
    }
  }

  private void setFase(int valor) {
    if (valor < NUMERO_FASES) {
      numeroFase = valor;
    }
  }

  /**
   * Retorna a fase que o tabuleiro foi carregado
   * @return O numero da fase
   */
  public int getFase() {
    return numeroFase;
  }

  private void colocaBloco(int linha, int coluna) {
    matriz[linha][coluna] = BLOCO;
  }

  /**
   * Confere se tem algum bloco, na linha e coluna dados, no tabuleiro
   * @param linha Linha
   * @param coluna Coluna
   * @return true caso haja bloco
   */
  public boolean temBloco(int linha, int coluna) {
    return matriz[linha][coluna] == BLOCO;
  }

  /**
   * Confere se tem alguma bomba, na linha e coluna dados, no tabuleiro
   * @param linha Linha
   * @param coluna Coluna
   * @return true caso haja bomba
   */
  public boolean temBomba(int linha, int coluna) {
    return matriz[linha][coluna] >> 7 != 0;
  }

  /**
   * Confere se tem fogo, na linha e coluna dados, no tabuleiro
   * @param linha Linha
   * @param coluna Coluna
   * @return true caso haja fogo
   */
  public boolean temFogo(int linha, int coluna) {
    return matriz[linha][coluna] >= 3 && matriz[linha][coluna] <= 69;
  }

  /**
   * Confere se tem obstaculo (blocos indestrutiveis) no tabuleiro
   * @param linha Linha
   * @param coluna Coluna
   * @return true caso haja obstaculo
   */
  public boolean temObstaculo(int linha, int coluna) {
    return matriz[linha][coluna] == OBSTACULO;
  }

  /**
   * Confere se o termo no tabuleiro esta livre
   * @param linha Linha
   * @param coluna Coluna
   * @return true caso a posição esteja livre
   */
  public boolean estaLivre(int linha, int coluna) {
    return matriz[linha][coluna] == LIVRE;
  }

  // chama os metodos, estaLivre() e temFogo() para verificar se pode andar
  private boolean podeAndar(int linha, int coluna) {
    return estaLivre(linha, coluna) || matriz[linha][coluna] >> 7 == 1
        || temFogo(linha, coluna);
  }

  // na linha e coluna dados seta a posicao como LIVRE
  private void limpaBloco(int linha, int coluna) {
    matriz[linha][coluna] = LIVRE;
  }

  /**
   * Insere uma bomba na matriz e no vetor de Personagem.
   * @param p Personagem que terá uma bomba adicionada
   * @param linha Linha
   * @param coluna Coluna
   */
  public void colocaBomba(Personagem p, int linha, int coluna) {
    if (p.getBombasSoltas() < p.getMaxBombas() && estaLivre(linha, coluna)) {
      p.addBomba(new Bomba(p, linha, coluna));
    }
  }

  // apos o centro do boneco sair da bomba, ajusta-se para ela ter colisao
  private void ajustaBombaParaColidir(int linha, int coluna) {
    if (temBomba(linha, coluna)) {
      matriz[linha][coluna] = BOMBA_COM_COLISAO;
    }
  }

  /**
   * Método que retorna um iterator para o vetor de inimigos
   * @return Um iterator para inimigos
   */
  public Iterator iteratorDeInimigos() {
    return inimigos.iterator();
  }

  /**
   * Retorna o jogador
   * @return O jogador
   */
  public Jogador getJogador() {
    return jogador;
  }

  /**
   * Retorna qual o termo que tem em uma posicao na matriz
   * @param linha Linha
   * @param coluna Coluna
   * @return O numero que tem na matriz na linha e coluna dadas
   */
  public int getTermo(int linha, int coluna) {
    return matriz[linha][coluna];
  }

  /**
   * Retorna o numero de linhas da matriz
   * @return O numero de linhas
   */
  public int getNumeroLinhas() {
    return matriz.length;
  }

  /**
   * Retorna o numero de colunas da matriz
   * @return O numero de colunas
   */
  public int getNumeroColunas() {
    return matriz[0].length;
  }

  // prepara a matriz para representar a explosao
  private void preparaExplosao(int linha, int coluna, int tamanhoDaExplosao) {
    byte direcoes = 0xF; // (0000 1111)

    for (int i = 0; i <= tamanhoDaExplosao; i++) {
      // para cada direcao, verifica ate onde as chamas ira
      for (int bitDirecao = 1; bitDirecao <= 4; bitDirecao++) {
        // se nao encontrou obstaculo...
        if ((direcoes & (byte)Math.pow(2, bitDirecao - 1)) != 0) {
          int deltaLinha = (int)Math.sin(Math.PI / 2 * (3 - bitDirecao)),
              deltaColuna = (int)Math.cos(Math.PI / 2 * (3 - bitDirecao));
          // verifica a parte central das chamas
          if (i < tamanhoDaExplosao) {
            matriz[linha - i * deltaLinha][coluna + i * deltaColuna] = (bitDirecao % 2 + 1) * 10 + 3;
            // verifica se o fogo irah continuar
            if (!estaLivre(linha - (i + 1) * deltaLinha, coluna + (i + 1) * deltaColuna)) {
              direcoes &= ~ (byte)Math.pow(2, bitDirecao - 1);
            }
            // se encontrar uma bomba, explode-a
            if (temBomba(linha - (i + 1) * deltaLinha, coluna + (i + 1) * deltaColuna)) {
              procuraBomba(linha - (i + 1) * deltaLinha, coluna + (i + 1) * deltaColuna)
                  .interrupt();
            }
            // se encontrar um bloco, explode-o
            if (temBloco(linha - (i + 1) * deltaLinha, coluna + (i + 1) * deltaColuna)) {
              limpaBloco(linha - (i + 1) * deltaLinha, coluna + (i + 1) * deltaColuna);
            }
          }
          // verifica as extremidades
          else {
            matriz[linha - i * deltaLinha][coluna + i * deltaColuna] = bitDirecao * 10 + 23;
          }
        }
      }
    }
    // configura o centro que eh obrigatorio existir
    matriz[linha][coluna] = 3;
  }

  /**
   * Reajusta as posicoes dos elementos presentes na matriz
   */
  public void reajustaPosicoes() {
    boolean esquerdaLivre = podeAndar((jogador.getTop() + SUP + MARGEM) / LADO, (jogador.getLeft() - 1) / LADO) && podeAndar((jogador.getTop() + SUP + LADO - 1 - MARGEM) / LADO, (jogador.getLeft() - 1) / LADO),
            cimaLivre = podeAndar((jogador.getTop() + SUP - 1) / LADO, (jogador.getLeft() + MARGEM) / LADO) && podeAndar((jogador.getTop() + SUP - 1) / LADO, (jogador.getLeft() + LADO - 1 - MARGEM) / LADO),
            direitaLivre = podeAndar((jogador.getTop() + SUP + MARGEM) / LADO, (jogador.getLeft() + LADO) / LADO) && podeAndar((jogador.getTop() + SUP + LADO - 1 - MARGEM) / LADO, (jogador.getLeft() + LADO) / LADO),
            baixoLivre = podeAndar((jogador.getTop() + SUP + LADO) / LADO, (jogador.getLeft() + MARGEM) / LADO) && podeAndar((jogador.getTop() + SUP + LADO) / LADO, (jogador.getLeft() + LADO - 1 - MARGEM) / LADO),
            superiorEsquerdoLivre = podeAndar((jogador.getTop() + SUP) / LADO, jogador.getLeft() / LADO),
            superiorDireitoLivre = podeAndar((jogador.getTop() + SUP) / LADO, (jogador.getLeft() + LADO - 1) / LADO),
            inferiorDireitoLivre = podeAndar((jogador.getTop() + SUP + LADO - 1) / LADO, (jogador.getLeft() + LADO - 1) / LADO),
            inferiorEsquerdoLivre = podeAndar((jogador.getTop() + SUP + LADO - 1) / LADO, jogador.getLeft() / LADO),
            pressionandoEsquerda = (jogador.getDirecao() & 1) == Jogador.ESQUERDA,
            pressionandoCima = (jogador.getDirecao() & 2) == Jogador.CIMA,
            pressionandoDireita = (jogador.getDirecao() & 4) == Jogador.DIREITA,
            pressionandoBaixo = (jogador.getDirecao() & 8) == Jogador.BAIXO;

    // calcula a nova posicao do boneco de acordo com a direcao
    if (pressionandoEsquerda && esquerdaLivre ||
        pressionandoCima && !superiorDireitoLivre ||
        pressionandoBaixo && !inferiorDireitoLivre) {
      jogador.anda(Jogador.ESQUERDA);
    }
    if (pressionandoCima && cimaLivre ||
        pressionandoEsquerda && !inferiorEsquerdoLivre ||
        pressionandoDireita && !inferiorDireitoLivre) {
      jogador.anda(Jogador.CIMA);
    }
    if (pressionandoDireita && direitaLivre ||
        pressionandoCima && !superiorEsquerdoLivre ||
        pressionandoBaixo && !inferiorEsquerdoLivre) {
      jogador.anda(Jogador.DIREITA);
    }
    if (pressionandoBaixo && baixoLivre ||
        pressionandoEsquerda && !superiorEsquerdoLivre ||
        pressionandoDireita && !superiorDireitoLivre) {
      jogador.anda(Jogador.BAIXO);
    }
    // inimigo anda
    Iterator seta = inimigos.iterator();
    while (seta.hasNext()) {
      Inimigo aux = (Inimigo)seta.next();
      int direcaoLinear = (int)(Math.log(aux.getDirecao()) / Math.log(2)) + 1;
      aux.anda(estaLivre((- (LADO / 2 + 1) * (int)Math.sin(Math.PI / 2 * (3 - direcaoLinear)) + aux.getTop() + SUP + LADO / 2) / LADO, ((LADO / 2 + 1) * (int)Math.cos(Math.PI / 2 * (3 - direcaoLinear)) + aux.getLeft() + LADO / 2) / LADO));
      //faz o inimigo lancar (ou nao) a bomba
      if(aux.lancaBomba() && aux.getBombasSoltas() < aux.getMaxBombas()) {
        colocaBomba(aux, (aux.getTop() + SUP + LADO / 2) / LADO, (aux.getLeft() + LADO / 2) / LADO);
        ajustaBombaParaColidir((aux.getTop() + SUP + LADO / 2) / LADO, (aux.getLeft() + LADO / 2) / LADO);
      }
    }
    // caso o jogador saia da bomba, esta eh configurada como BOMBA_COM_COLISAO
    Bomba ultimaBomba = null;
    boolean saiuDaBomba = !temBomba((jogador.getTop() + SUP + LADO / 2) / LADO, (jogador.getLeft() + LADO / 2) / LADO);
    if (jogador.getBombasSoltas() != 0) { ultimaBomba = (Bomba)jogador.getUltimaBomba(); }
    if (saiuDaBomba && ultimaBomba != null) {
      ajustaBombaParaColidir(ultimaBomba.getLinha(), ultimaBomba.getColuna());
    }
    // VERIFICA COLISOES
    // verifica se jogador colidiu com inimigo
    seta = inimigos.iterator();
    while (seta.hasNext()) {
      Inimigo aux = (Inimigo)seta.next();
      if (!jogador.isInvensivel() &&
          jogador.getLeft() + LADO / 2 >= aux.getLeft() &&
          jogador.getLeft() + LADO / 2 <= aux.getLeft() + LADO &&
          jogador.getTop() + SUP + LADO / 2 >= aux.getTop() + SUP &&
          jogador.getTop() + SUP + LADO / 2 <= aux.getTop() + SUP + LADO) {
        jogador.explodir();
      }
    }
    // verifica se houve colisao com o fogo
    // ... jogador
    if (!jogador.isInvensivel() && temFogo((jogador.getTop() + SUP + LADO / 2) / LADO, (jogador.getLeft() + LADO / 2) / LADO)) {
      jogador.explodir();
    }
    // ... inimigos
    seta = inimigos.iterator();
    while (seta.hasNext()) {
      Inimigo aux = (Inimigo)seta.next();
      if (temFogo((aux.getTop() + SUP + LADO / 2) / LADO, (aux.getLeft() + LADO / 2) / LADO)) {
        // remove os relacionamentos com o inimigo
        aux.explodir();
        seta.remove();
        // ajusta pontuacao do jogador
        jogador.addPontos(100);
        // se nao tiver mais inimigos, cria o portal para passar de fase
        if (inimigos.size() == 0) {
          itens.criaPortal(this);
        }
      }
    }
    // reajusta os valores (na matriz) da explosao
    reajustaExplosao();
    // captura um possivel item
    capturaItem();
  }


  public boolean temPortal(int linha, int coluna) {
    return itens.temPortal(linha, coluna);
  }

  public int getItem(int linha, int coluna) {
    return (itens.getItem(linha, coluna) >= 0 && itens.getItem(linha, coluna) <= 2 ? itens.getItem(linha, coluna) : -1);
  }

  /**
   * Verifica se existem um item a ser capturado no centro do jogador
   */
  private void capturaItem() {
    // aloca linha e coluna como baseado no centro do jogador
    int coluna = (jogador.getLeft() + (LADO / 2)) / LADO;
    int linha = (jogador.getTop() + SUP + (LADO / 2)) / LADO;
    // verifica se existe um item no local
    switch (itens.getItem(linha, coluna)) {
      case 0:
        jogador.aumentaMaxBombas();
        break;
      case 1:
        jogador.aumentaExplosao();
        break;
      case 3:
        jogador.pegaPortal();
    }
    // remove o item capturado caso haja
    itens.removeItem(linha, coluna);
  }

  // ajusta os valores que representam explosao na matriz
  private void reajustaExplosao() {
    for (int i = 0; i < getNumeroLinhas(); i++) {
      for (int j = 0; j < getNumeroColunas(); j++) {
        if (temFogo(i, j)) {
          // aumenta o codigo do fogo ate que o resto da divisao por 10 seja ZERO
          if (++matriz[i][j] % 10 == 0) {
            matriz[i][j] = LIVRE;
          }
        }
      }
    }
  }

  // procura bomba no vetor de um dos personagens
  private Bomba procuraBombaEm(Iterator i, int linha, int coluna) {
    while (i.hasNext()) {
      Bomba b = (Bomba)i.next();
      if (b.getLinha() == linha && b.getColuna() == coluna) {
        return b;
      }
    }
    return null;
  }

  // procura bomba em todos os personagens
  private Bomba procuraBomba(int linha, int coluna) {
    // procura bomba solta pelo jogador
    Bomba b = procuraBombaEm(jogador.iteratorDeBombas(), linha, coluna);
    if (b != null) {
      return b;
    }
    // procura bomba nos inimigos
    Iterator seta = inimigos.iterator();
    while (seta.hasNext()) {
      Inimigo aux = (Inimigo)seta.next();
      b = procuraBombaEm(aux.iteratorDeBombas(), linha, coluna);
      if (b != null) {
        return b;
      }
    }
    // nao encontrou
    return null;
  }

  // classe interna Bomba
  private class Bomba extends Thread {
    private int linha, coluna;
    private Personagem p;

    public Bomba(Personagem p, int linha, int coluna) {
      this.p = p;
      this.linha = linha;
      this.coluna = coluna;
      matriz[linha][coluna] = BOMBA_SEM_COLISAO;
      start();
    }

    public int getLinha() {
      return linha;
    }

    public int getColuna() {
      return coluna;
    }

    public void run() {
      // espera um tempo ate explodir
      try {
        Thread.sleep(3000);
      } catch (InterruptedException e) { }
      // acao executada apos explosao da bomba
      p.delBomba(this);
      preparaExplosao(linha, coluna, p.getTamanhoDaExplosao());
    }
  }
}