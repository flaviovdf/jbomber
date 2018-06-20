/*
	Esta classe � para uso exclusivamente acad�mico, � absolutamente
  proibido a c�pia total ou parcial de qualquer parte deste.

	Todos os direitos reservados.
	Universidade Federal de Campina Grande.
*/
import java.util.*;

/**
 * Classe abstrata personagem, esta classe ser� utilizada para a
 * implementa��o dos inimigos e do jogador. Aqui � implementado
 * a funcionalidade dos personagens como o n�mero de bombas e tamanho
 * da explos�o. Os atributos left e top s�o respectivamente a posi��o
 * mais a esquerda e a mais acima da imagem.
 *
 *  @author Felipe Ribeiro
 *  @author Fl�vio Roberto Santos
 *  @author Fl�vio Vinicius
 *  @author Jo�o Arthur
 *  @author Thiago Emmanuel
 *  @author Vinicius Ferraz
 *  @version 1.0
 */

public abstract class Personagem {

  private int left, top;
  private Vector bombas = new Vector();
  private int maxBombas = 1;
  private int tamanhoDaExplosao = 2;

  /**
   * Seta o valor da coordenada-x do personagem
   * @param valor Valor da coordenada-x do personagem
   */
  public void setLeft(int valor) {
    left = valor;
  }

  /**
   * Seta o valor da coordenada-y do personagem
   * @param valor Valor da coordenada-y do personagem
   */
  public void setTop(int valor) {
    top = valor;
  }

  /**
   * Retorna O valor da coordenada-x do personagem
   * @return Valor da coordenada-x do personagem
   */
  public int getLeft() {
    return left;
  }

  /**
   * Retorna O valor da coordenada-y do personagem
   * @return Valor da coordenada-y do personagem
   */
  public int getTop() {
    return top;
  }

  /**
   * Retorna um iterator para as bombas do personagem
   * @return Um iterator para as bombas do personagem
   */
  public Iterator iteratorDeBombas() {
    return bombas.iterator();
  }

  /**
   * Adiciona uma bomba ao vetor do personagem
   * @param b Bomba a ser adicionada
   */
  public void addBomba(Object b) {
    bombas.add(b);
  }

  /**
   * Remove uma bomba do vetor do personagem
   * @param b Bomba a ser removida
   */
  public void delBomba(Object b) {
    bombas.remove(b);
  }

  /**
   * Retorna o n�mero de bombas soltas pelo Personagem
   * @return N�mero de bombas
   */
  public int getBombasSoltas() {
    return bombas.size();
  }

  /**
   * Incrementa em 1 (um) o n�mero de bombas que o personagem pode soltar
   */
  public void aumentaMaxBombas() {
    maxBombas++;
  }

  /**
   * Retorna o n�mero m�ximo de bombas que o personagem pode soltar
   * @return O m�ximo de bombas que podem ser soltas
   */
  public int getMaxBombas() {
    return maxBombas;
  }

  /**
   * Aumenta o poder de fogo da bomba
   */
  public void aumentaExplosao() {
    tamanhoDaExplosao++;
  }

  /**
   * Retorna o tamanho da explos�o.
   * @return Tamanho da explos�o
   */
  public int getTamanhoDaExplosao() {
    return tamanhoDaExplosao;
  }

  /**
   * Retorna a �ltima bomba colocada pelo personagem.
   * @return �ltima bomba colocada pelo personagem
   */
  public Object getUltimaBomba() {
    return bombas.lastElement();
  }

  /**
   * Altera o valor da direcao
   * @param valor Novo valor da direcao
   */
  public abstract void setDirecao(int valor);

  /**
   * Retorna o valor da direcao
   * @return O valor da direcao
   */
  public abstract int getDirecao();
}