/*
    Esta classe é para uso exclusivamente acadêmico, é absolutamente
  proibido a cópia total ou parcial de qualquer parte deste.

    Todos os direitos reservados.
    Universidade Federal de Campina Grande.
*/

/**
 * Implementação da classe Itens, os itens são representados
 * em uma matriz com o mesmo tamanho do tabuleiro e colocados
 * randomicamente. Na classe tabuleiro eles são interpretados
 * para jogo.
 * <BR>
 * Segue abaixo a lista dos códigos dos itens:
 * <BR>
 * 0 = Mais uma bomba para o personagem<BR>
 * 1 = Aumenta força das bombas do personagem<BR>
 *  @author Felipe Ribeiro
 *  @author Flávio Roberto Santos
 *  @author Flávio Vinicius
 *  @author João Arthur
 *  @author Thiago Emmanuel
 *  @author Vinicius Ferraz
 *  @version 1.0
 */
public class Itens {

  private final int PORTAL = 3;
  private int[][] mat;

  /**
   * Cria uma nova matriz de itens baseado na do tabuleiro
   * passado como parametro
   * @param t Tabuleiro a ser interpretado para geração de itens
   */
  public Itens(Tabuleiro t) {
    this.mat = new int[t.getNumeroLinhas()][t.getNumeroColunas()];
    zeraMatriz();
    alocaItens(t);
  }

  // zera a matriz
  private void zeraMatriz() {
    for (int i = 0; i < mat.length; i++) {
      for (int j = 0; j < mat[0].length; j++) {
        mat[i][j] = -1;
      }
    }
  }

  /*
     Aloca randomicamente os itens na matriz, existe uma probabilidade
     de 10% de um item ser colocado a cada interação como também o item
     a ser colocado é randômico. Assim garantimos sempre uma certa
     quantidade de itens no tabuleiro em posições diferentes e que os
     itens também variem.
   */
  private void alocaItens(Tabuleiro t) {
    for (int i = 0; i < mat.length; i++) {
      for (int j = 0; j < mat[0].length; j++) {
        if (t.temBloco(i, j)) {
          int probabilidadeItem = (int) (Math.random() * 100);
          if (probabilidadeItem < 10) {
            int itemRandomico = (int) (Math.random() * 2);
            mat[i][j] = itemRandomico;
          }
        }
      }
    }
  }

  /**
   * Cria um item (portal) que permite a passagem de fase.
   * @param t Tabuleiro que receberá o portal
   */
  public void criaPortal(Tabuleiro t) {
    int linha, coluna;
    do {
      linha = (int) (Math.random() * (mat.length - 2)) + 1;
      coluna = (int) (Math.random() * (mat[0].length - 2)) + 1;
    } while (t.temObstaculo(linha, coluna));

    mat[linha][coluna] = PORTAL;
  }

  /**
   * Diz se tem um portal na posição indicada
   * @param linha Linha
   * @param coluna Coluna
   * @return true caso tenha um portal
   */
  public boolean temPortal(int linha, int coluna) {
    return mat[linha][coluna] == PORTAL;
  }

  /**
   * Verifica se existe um item em uma determinada posição da matriz.
   * @param linha Linha
   * @param coluna Coluna
   * @return O código do item correspodente, caso não tenha algum retorna -1
   */
  public int getItem(int linha, int coluna) {
    return mat[linha][coluna];
  }

  /**
   * Remove um item da matriz
   * @param linha Linha
   * @param coluna Coluna
   */
  public void removeItem(int linha, int coluna) {
    if (getItem(linha, coluna) != -1) {
      mat[linha][coluna] = -1;
    }
  }

}