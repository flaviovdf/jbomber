/*
	Esta classe e para uso exclusivamente academico, e absolutamente
  proibido a copia total ou parcial de qualquer parte deste.

	Todos os direitos reservados.
	Universidade Federal de Campina Grande.
*/
import java.awt.*;
import java.awt.event.*;
import java.util.Vector;
import java.util.Iterator;
import java.util.EventObject;
import java.io.*;

import javax.swing.*;

/**
 * Implementacao da interface grafica (GUI) do jogo.
 *  @author Felipe Ribeiro
 *  @author Flavio Roberto Santos
 *  @author Flavio Vinicius
 *  @author Joao Arthur
 *  @author Thiago Emmanuel
 *  @author Vinicius Ferraz
 *  @version 1.0
 */

public class PainelJogo extends JPanel implements Constantes, PersonagemListener, ActionListener, KeyListener {

  private final int DELAY = 25;
  private Tabuleiro tab;
  private AnimacaoPersonagem jogadorGrafico;
  private Vector animacoes = new Vector();
  private Vector inimigos = new Vector();
  private Tempo cronometro = new Tempo(180, DELAY);
  private Timer timer1;
  // sprites
  private Sprite
      sprFumaca,
      sprItens;
  // animacoes
  private Animacao
      animPortal,
      animBomba,
      animBloco,
      animRodape,
      animFundo;
  // banco de imagens da explosao (tratamento especial, pois cada frame da
  //   da explosao tem um codigo relacionado na matriz)
  private ImageIcon[] explosao = new ImageIcon[70];

  /**
   * Cria o painel do jogo.
   */
  public PainelJogo() {
    // cria o temporizador do jogo
    timer1 = new Timer(DELAY, this);
    try {
      // carrega a fase inicial
      carregaFase(0, new Jogador(LADO, LADO - SUP, 3, 0, JOptionPane.showInputDialog("Digite seu nome:"), this));
      // carrega imagens da explosao
      carregaImagensExplosao();
      // criacao dos sprites
      sprFumaca = new Sprite("fumaca/", "6");
      sprItens = new Sprite("itens/", "1:1");
      // criacao das animacoes
      animPortal = new Animacao(new Sprite("portal/", "12"), 4);
      animBomba = new Animacao(new Sprite("bomba/", "4"), 3);
      animRodape = new Animacao(new Sprite("rodape", "1"), 1);
      // carrega o nome do jogador
    } catch (FileNotFoundException e) {
      System.err.println(e.getMessage());
      System.exit(1);
    }
  }

  // carrega uma fase
  private void carregaFase(int numeroFase, Jogador j) {
    // desativa o timer
    timer1.stop();
    // criacao do tabuleiro logico
    tab = new Tabuleiro(j, numeroFase, this);
    //
    inimigos.clear();
    animacoes.clear();
    cronometro.reiniciar(180, DELAY);
    try {
      // carrega o fundo e os blocos da fase
      animFundo = new Animacao(new Sprite("fundos/" + numeroFase, "1"), 1);
      animBloco = new Animacao(new Sprite("blocos/" + numeroFase, "4:5"), 4);
      // criacao dos personagens
      jogadorGrafico = new AnimacaoPersonagem(tab.getJogador(), new Sprite("jogador/", "10:8:10:8:3:6"), 3);
      // ... inimigos
      Iterator seta = tab.iteratorDeInimigos();
      while (seta.hasNext()) {
        Inimigo aux = (Inimigo)seta.next();
        String tipoInimigo = "inimigos";
        if((1 + (int)(Math.random() * 2)) == 1) {
          tipoInimigo += "/helicoptero/";
        }
        else {
          tipoInimigo += "/pinguim/";
        }
        inimigos.add(new AnimacaoPersonagem(aux, new Sprite(tipoInimigo, "4:4:4:3"), 4));
      }
    } catch (FileNotFoundException e) {
      System.err.println(e.getMessage());
      System.exit(1);
    }
    // reativa o timer
    timer1.start();
  }

  private void carregaImagensExplosao() throws FileNotFoundException {
    // carrega o banco de imagens da explosao
    for (int i = 0; i <= 6; i++) {
      for (int j = 3; j <= 9; j++) {
        java.net.URL aux = getClass().getResource("sprites/fogo/" + i + j + ".gif");
//        if (aux == null) {
//          throw new FileNotFoundException("O arquivo sprites/fogo/" + i + j + ".gif nao foi encontrado!");
//        }
        explosao[i * 10 + j] = new ImageIcon("sprites/fogo/" + i + j + ".gif");
      }
    }
  }

  private boolean removeInimigo(Object i) {
    if (!(i instanceof Inimigo)) { return false; }

    Iterator seta = inimigos.iterator();
    while (seta.hasNext()) {
      Personagem p = ((AnimacaoPersonagem)seta.next()).getPersonagem();
      if (p == i) {
        seta.remove();
        return true;
      }
    }
    return false;
  }

  /**
   * Trata o evento do Timer, ajustando o tempo do cronometro
   * e reajustando posicoes.
   * @param event Objeto passado pelo evento
   */
  public void actionPerformed(ActionEvent event) {
    if (!cronometro.ajustaTempo()) {
      tiraVida();
    }
    tab.reajustaPosicoes();
    repaint();
  }

  /**
   * Roda a animacao da morte dos personagens no local em que eles se situavam.
   * Caso o personagem seja um inimigo, roda a animacao da fumaca. Caso seja o jogador
   * retira uma vida, e recarrega a fase ou se nao tiver mais vidas, resulta no GameOver.
   * @param event Objeto passado pelo evento
   */
  public void personagemMorre(EventObject event) {
    if (event.getSource() instanceof Inimigo) {
      Inimigo i = (Inimigo) event.getSource();
      removeInimigo(i);
      // cria a fumaca
      animacoes.add(new AnimacaoTemporaria(sprFumaca, i.getLeft(), i.getTop(), 3, 1).ativar());
    }
    // se nao foi um inimigo que morreu, logo foi o jogador
    // segue abaixo as acoes quando jogador morre
    else {
      // verifica se jogador tem vidas, caso nao tenha a tela de GameOver eh mostrada
      tiraVida();
    }
  }

  // retira uma vida do jogador caso ele tenha
  private void tiraVida() {
    if (tab.getJogador().getVidas() > 0) {
      tab.getJogador().addVidas(-1);
      carregaFase(tab.getFase(), tab.getJogador());
    }
    else {
      timer1.stop();
      // grava a pontuacao do jogador (se for mais alta que o ultimo da lista)
      ManipulaRecordes.gravaRecorde(tab.getJogador().getNome(), tab.getJogador().getPontos());
      // mostra a tela de GameOver
      PainelGameOver obj;
      Principal.getReferencia().removeKeyListener(this);
      Principal.carregaPainel(obj = new PainelGameOver());
      Principal.getReferencia().addKeyListener(obj);
    }
  }

  /**
   * Metodo que e executado quando o jogador passar de fase.
   * @param event Objeto passado pelo evento
   */
  public void jogadorPegaPortal(EventObject event) {
    if (event.getSource() == tab.getJogador()) {
      // se ainda houver fases, carrega a proxima
      if (tab.getFase() < tab.NUMERO_FASES - 1) {
        carregaFase(tab.getFase() + 1, tab.getJogador());
      } else {
        timer1.stop();
        // grava a pontuacao do jogador (se for mais alta que o ultimo da lista)
        ManipulaRecordes.gravaRecorde(tab.getJogador().getNome(), tab.getJogador().getPontos());
        // mostra a tela final
        PainelFim obj;
        Principal.getReferencia().removeKeyListener(this);
        Principal.carregaPainel(obj = new PainelFim());
        Principal.getReferencia().addKeyListener(obj);
      }
    }
  }

  /**
   * Utiliza o grafico para desenhar no painel
   * @param g Grafico que sera desenhado no painel
   */
  public void paintComponent(Graphics g) {
    super.paintComponent(g);

    // desenha imagens na tela
    animFundo.desenha(this, g, 0, 0);
    animRodape.desenha(this, g, 0, 208);
    // percorre o tabuleiro
    for (int i = 0; i < tab.getNumeroLinhas(); i++) {
      for (int j = 0; j < tab.getNumeroColunas(); j++) {
        // traduz a matriz de tabuleiro para objetos graficos
        if (tab.temBloco(i, j)) {
          animBloco.desenha(this, g, j * LADO, i * LADO);
        }
        else if (tab.temPortal(i, j)) {
          animPortal.desenha(this, g, j * LADO, (i - 2) * LADO);
        }
        else if (tab.getItem(i, j) != -1) {
          sprItens.getImageIcon(tab.getItem(i, j), 0).paintIcon(this, g, j * LADO, i * LADO);
        }
        else if (tab.temBomba(i, j)) {
          animBomba.desenha(this, g, j * LADO, i * LADO);
        }
        else if (tab.temFogo(i, j)) {
          explosao[tab.getTermo(i, j)].paintIcon(this, g, j * LADO, i * LADO);
        }
      }
    }
    // avanca um frame dos sprites
    animPortal.avancaFrame();
    animBloco.avancaFrame();
    animBomba.avancaFrame();
    // desenha objetos animados
    // ...inimigos
    Iterator seta = inimigos.iterator();
    while (seta.hasNext()) {
      AnimacaoPersonagem aux = (AnimacaoPersonagem)seta.next();
      aux.desenha(this, g);
    }
    // ... jogador
    jogadorGrafico.desenha(this, g);
    if (tab.getJogador().isInvensivel()) {
      g.fillOval(tab.getJogador().getLeft(), tab.getJogador().getTop(), LADO, LADO + SUP);
    }
    // imprime o vetor de animacoes temporarias
    seta = animacoes.iterator();
    while (seta.hasNext()) {
      AnimacaoTemporaria aux = (AnimacaoTemporaria)seta.next();
      if (!aux.desenha(this, g)) {
        seta.remove();
      }
    }
    // imprime itens do rodape
    g.setFont(new Font("Tahoma", Font.BOLD, 10));
    g.setColor(Color.WHITE);
    g.drawString("" + tab.getJogador().getVidas(), 8, 223);
    g.drawString("" + tab.getJogador().getPontos(), 50, 223);
    g.drawString(cronometro.toString(), 130, 223);
    g.drawString(tab.getJogador().getNome(), 160, 223);
  }

  /**
   * Trata o pressionamento de teclas.
   * @param event Objeto passado pelo evento
   */
  public void keyPressed(KeyEvent event) {
    switch (event.getKeyCode()) {
      case 37: case 38: case 39: case 40:
        if ((tab.getJogador().getDirecao() & (int)Math.pow(2, event.getKeyCode() - 36 - 1)) == 0) {
          tab.getJogador().setDirecao((int)Math.pow(2, event.getKeyCode() - 36 - 1));
        }
        break;
      case KeyEvent.VK_CONTROL:
        tab.colocaBomba(tab.getJogador(), (tab.getJogador().getTop() + SUP + LADO / 2) / LADO, (tab.getJogador().getLeft() + LADO / 2) / LADO);
    }
  }
  public void keyTyped(KeyEvent event) { }
  public void keyReleased(KeyEvent event) {
    switch (event.getKeyCode()) {
      case 37: case 38: case 39: case 40:
        tab.getJogador().setDirecao(- (int)Math.pow(2, event.getKeyCode() - 36 - 1));
    }
  }
}