/*
	Esta classe � para uso exclusivamente acad�mico, � absolutamente
  proibido a c�pia total ou parcial de qualquer parte deste.

	Todos os direitos reservados.
	Universidade Federal de Campina Grande.
*/
import java.util.StringTokenizer;
import java.io.*;

import javax.swing.ImageIcon;

/**
 * Esta classe armazena uma sequ�ncia de anima��es.
 *  @author Felipe Ribeiro
 *  @author Fl�vio Roberto Santos
 *  @author Fl�vio Vinicius
 *  @author Jo�o Arthur
 *  @author Thiago Emmanuel
 *  @author Vinicius Ferraz
 *  @version 1.0
 */

public class Sprite {

  private ImageIcon[][] imagens;

  /**
   * Cria um novo sprite, os arquivos devem ser *.gif e seus nomes
   * do tipo nome00.gif, nome01.gif em diante. Assim, no exemplo
   * acima o parametro "arquivo" voc� entra so com o string "nome".
   * O par�metro "numeroImagens" diz quantas imagens ter� cada anima��o
   * (n�meros separados por ":").
   * Por exemplo:
   * - Para criar um sprite com duas anima��es (a primeira com 3 imagens
   * e a segunda com 5) os parametros devem ser:
   * > arquivo: [nome do arquivo sem indice e sem extens�o]
   * > numeroImagens: "3:5"
   * @param arquivo Nome dos arquivos
   * @param numeroImagens Quantidade de imagens
   * @throws FileNotFoundException Uma excess�o caso os arquivos dos sprites n�o sejam encontrados
   */
  public Sprite(String arquivo, String numeroImagens) throws FileNotFoundException {
    StringTokenizer t = new StringTokenizer(numeroImagens, ":");
    int nLinhas = t.countTokens();

    imagens = new ImageIcon[nLinhas][];
    for (int i = 0; i < nLinhas; i++) {
      imagens[i] = new ImageIcon[Integer.parseInt(t.nextToken())];
      for (int j = 0; j < imagens[i].length; j++) {
        java.net.URL aux = getClass().getResource("sprites/" + arquivo + i + j + ".gif");
//        if (aux == null) {
//          throw new FileNotFoundException("O arquivo sprites/" + arquivo + i + j + ".gif n�o foi encontrado!");
//        }
        imagens[i][j] = new ImageIcon("sprites/" + arquivo + i + j + ".gif");
      }
    }
  }

  /**
   * Retorna o n�mero de anima��es do sprite
   * @return N�mero de anima��es
   */
  public int getNumeroDeAnimacoes() {
    return imagens.length;
  }

  /**
   * Retorna o total de frames de uma certa anima��o.
   * @param animacao Linha dos sprites
   * @return Total de frames da linha
   */
  public int getTotalFrames(int animacao) {
    return imagens[animacao].length;
  }

  /**
   * Retorna uma determinada imagem do sprite.
   * @param animacao N�mero da anima��o
   * @param frame Frame da anima��o
   * @return A imagem
   */
  public ImageIcon getImageIcon(int animacao, int frame) {
    return imagens[animacao][frame];
  }
}