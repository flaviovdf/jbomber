/*
	Esta classe e para uso exclusivamente academico, e absolutamente
  proibido a copia total ou parcial de qualquer parte deste.

	Todos os direitos reservados.
	Universidade Federal de Campina Grande.
*/
import java.util.StringTokenizer;
import java.io.*;

import javax.swing.ImageIcon;

/**
 * Esta classe armazena uma sequencia de animacoes.
 *  @author Felipe Ribeiro
 *  @author Flavio Roberto Santos
 *  @author Flavio Vinicius
 *  @author Joao Arthur
 *  @author Thiago Emmanuel
 *  @author Vinicius Ferraz
 *  @version 1.0
 */

public class Sprite {

  private ImageIcon[][] imagens;

  /**
   * Cria um novo sprite, os arquivos devem ser *.gif e seus nomes
   * do tipo nome00.gif, nome01.gif em diante. Assim, no exemplo
   * acima o parametro "arquivo" voce entra so com o string "nome".
   * O parametro "numeroImagens" diz quantas imagens tera cada animacao
   * (numeros separados por ":").
   * Por exemplo:
   * - Para criar um sprite com duas animacoes (a primeira com 3 imagens
   * e a segunda com 5) os parametros devem ser:
   * > arquivo: [nome do arquivo sem indice e sem extensao]
   * > numeroImagens: "3:5"
   * @param arquivo Nome dos arquivos
   * @param numeroImagens Quantidade de imagens
   * @throws FileNotFoundException Uma excessao caso os arquivos dos sprites nao sejam encontrados
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
//          throw new FileNotFoundException("O arquivo sprites/" + arquivo + i + j + ".gif nao foi encontrado!");
//        }
        imagens[i][j] = new ImageIcon("sprites/" + arquivo + i + j + ".gif");
      }
    }
  }

  /**
   * Retorna o numero de animacoes do sprite
   * @return Numero de animacoes
   */
  public int getNumeroDeAnimacoes() {
    return imagens.length;
  }

  /**
   * Retorna o total de frames de uma certa animacao.
   * @param animacao Linha dos sprites
   * @return Total de frames da linha
   */
  public int getTotalFrames(int animacao) {
    return imagens[animacao].length;
  }

  /**
   * Retorna uma determinada imagem do sprite.
   * @param animacao Numero da animacao
   * @param frame Frame da animacao
   * @return A imagem
   */
  public ImageIcon getImageIcon(int animacao, int frame) {
    return imagens[animacao][frame];
  }
}