/*
	Esta classe � para uso exclusivamente acad�mico, � absolutamente
  proibido a c�pia total ou parcial de qualquer parte deste.

	Todos os direitos reservados.
	Universidade Federal de Campina Grande.
*/
import java.util.*;

/**
 *Classe que implementa o comportamento dos Inimigos 
 *  @author Felipe Ribeiro
 *  @author Fl�vio Roberto Santos
 *  @author Fl�vio Vinicius
 *  @author Jo�o Arthur
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
   * @param direcao Dire�ao inicial do jogador
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
   * Altera a posicao do inimigo se for poss�vel
   * @param podeAndar Se ele pode andar na dire�ao atual
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
   * Altera a dire�ao do inimigo.
   * @param valor O novo valor da dire�ao
   */
  public void setDirecao(int valor) {
    direcao = valor;
  }

  /**
   * Retorna o valor da dire��o
   * @return Valor da dire��o
   */
  public int getDirecao() {
    // retorna 1, 2, 4 ou 8
    return (int)Math.pow(2, direcao - 1);
  }

  /**
   * Solta (ou n�o) uma bomba
   * @return Se ir� soltar ou nao (Probabilidade: 1/1000)
   */
  public boolean lancaBomba() {
    return (int)(Math.random() * 2000) == 0;
  }
}