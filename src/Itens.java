/*
    Esta classe � para uso exclusivamente acad�mico, � absolutamente
  proibido a c�pia total ou parcial de qualquer parte deste.

    Todos os direitos reservados.
    Universidade Federal de Campina Grande.
*/

/**
 * Implementa��o da classe Itens, os itens s�o representados
 * em uma matriz com o mesmo tamanho do tabuleiro e colocados
 * randomicamente. Na classe tabuleiro eles s�o interpretados
 * para jogo.
 * <BR>
 * Segue abaixo a lista dos c�digos dos itens:
 * <BR>
 * 0 = Mais uma bomba para o personagem<BR>
 * 1 = Aumenta for�a das bombas do personagem<BR>
 *  @author Felipe Ribeiro
 *  @author Fl�vio Roberto Santos
 *  @author Fl�vio Vinicius
 *  @author Jo�o Arthur
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
   * @param t Tabuleiro a ser interpretado para gera��o de itens
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
     de 10% de um item ser colocado a cada intera��o como tamb�m o item
     a ser colocado � rand�mico. Assim garantimos sempre uma certa
     quantidade de itens no tabuleiro em posi��es diferentes e que os
     itens tamb�m variem.
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
   * @param t Tabuleiro que receber� o portal
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
   * Diz se tem um portal na posi��o indicada
   * @param linha Linha
   * @param coluna Coluna
   * @return true caso tenha um portal
   */
  public boolean temPortal(int linha, int coluna) {
    return mat[linha][coluna] == PORTAL;
  }

  /**
   * Verifica se existe um item em uma determinada posi��o da matriz.
   * @param linha Linha
   * @param coluna Coluna
   * @return O c�digo do item correspodente, caso n�o tenha algum retorna -1
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