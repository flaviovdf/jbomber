/*
	Esta classe e para uso exclusivamente academico, e absolutamente
  proibido a copia total ou parcial de qualquer parte deste.

	Todos os direitos reservados.
	Universidade Federal de Campina Grande.
*/
import java.util.*;

/**
 * Classe abstrata personagem, esta classe sera utilizada para a
 * implementacao dos inimigos e do jogador. Aqui e implementado
 * a funcionalidade dos personagens como o numero de bombas e tamanho
 * da explosao. Os atributos left e top sao respectivamente a posicao
 * mais a esquerda e a mais acima da imagem.
 *
 *  @author Felipe Ribeiro
 *  @author Flavio Roberto Santos
 *  @author Flavio Vinicius
 *  @author Joao Arthur
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
   * Retorna o numero de bombas soltas pelo Personagem
   * @return Numero de bombas
   */
  public int getBombasSoltas() {
    return bombas.size();
  }

  /**
   * Incrementa em 1 (um) o numero de bombas que o personagem pode soltar
   */
  public void aumentaMaxBombas() {
    maxBombas++;
  }

  /**
   * Retorna o numero maximo de bombas que o personagem pode soltar
   * @return O maximo de bombas que podem ser soltas
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
   * Retorna o tamanho da explosao.
   * @return Tamanho da explosao
   */
  public int getTamanhoDaExplosao() {
    return tamanhoDaExplosao;
  }

  /**
   * Retorna a ultima bomba colocada pelo personagem.
   * @return Ultima bomba colocada pelo personagem
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