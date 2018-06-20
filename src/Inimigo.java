/*
	Esta classe é para uso exclusivamente acadêmico, é absolutamente
  proibido a cópia total ou parcial de qualquer parte deste.

	Todos os direitos reservados.
	Universidade Federal de Campina Grande.
*/
import java.util.*;

/**
 *Classe que implementa o comportamento dos Inimigos 
 *  @author Felipe Ribeiro
 *  @author Flávio Roberto Santos
 *  @author Flávio Vinicius
 *  @author João Arthur
 *  @author Thiago Emmanuel
 *  @author Vinicius Ferraz
 *  @version 1.0
 */
public class Inimigo extends Personagem {

  public static final int
      ESQUERDA = 1, CIMA = 2,
      DIREITA = 3,  BAIXO = 4;
  private int direcao;
  // listener de eventos
  private PersonagemListener listener;

  /**
   * Cria um inimigo
   * @param left Coordenada-x do inimigo
   * @param top Coordenada-y do inimigo
   * @param direcao Direçao inicial do jogador
   * @param listener Objeto que trata os eventos do personagem
   */
  public Inimigo(int left, int top, int direcao, PersonagemListener listener) {
    setLeft(left);
    setTop(top);
    setDirecao(direcao);
    // ajusta o listener
    this.listener = listener;
  }

  public void explodir() {
    listener.personagemMorre(new EventObject(this));
  }

  /**
   * Altera a posicao do inimigo se for possível
   * @param podeAndar Se ele pode andar na direçao atual
   */
  public void anda(boolean podeAndar) {
    if (podeAndar) {
      // move o inimigo de acordo com sua direcao
      switch (direcao) {
        case ESQUERDA:
          setLeft(getLeft() - 1);
          break;
        case CIMA:
          setTop(getTop() - 1);
          break;
        case DIREITA:
          setLeft(getLeft() + 1);
          break;
        case BAIXO:
          setTop(getTop() + 1);
      }
    }
    else { setDirecao(1 + (int)(Math.random() * 4)); }
  }

  /**
   * Altera a direçao do inimigo.
   * @param valor O novo valor da direçao
   */
  public void setDirecao(int valor) {
    direcao = valor;
  }

  /**
   * Retorna o valor da direção
   * @return Valor da direção
   */
  public int getDirecao() {
    // retorna 1, 2, 4 ou 8
    return (int)Math.pow(2, direcao - 1);
  }

  /**
   * Solta (ou não) uma bomba
   * @return Se irá soltar ou nao (Probabilidade: 1/1000)
   */
  public boolean lancaBomba() {
    return (int)(Math.random() * 2000) == 0;
  }
}