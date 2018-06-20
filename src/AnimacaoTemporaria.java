/*
	Esta classe � para uso exclusivamente acad�mico, � absolutamente
  proibido a c�pia total ou parcial de qualquer parte deste.

	Todos os direitos reservados.
	Universidade Federal de Campina Grande.
*/
import java.awt.Graphics;

import javax.swing.*;
 /**  @author Felipe Ribeiro
 *  @author Fl�vio Roberto Santos
 *  @author Fl�vio Vinicius
 *  @author Jo�o Arthur
 *  @author Thiago Emmanuel
 *  @author Vinicius Ferraz
 *  @version 1.0
 */
public class AnimacaoTemporaria extends Animacao {

  private int
      left, top,
      tempoDeVida;
  private boolean ativo = false;

  /**
   * Cria uma anima��o que � marcada como exclu�da depois de ser reproduzida
   * "tempoDeVida" vezes.
   * @param sprites Fonte de imagens
   * @param left Coordenada-x da anima��o
   * @param top Coordenada-y da anima��o
   * @param delay N�mero de clocks (chamada do evento do Timer) necess�rios para
   * mudar a imagem
   * @param tempoDeVida N�mero de vezes que a anima��o ser� reproduzida
   */
  public AnimacaoTemporaria(Sprite sprites, int left, int top, int delay, int tempoDeVida) {
    super(sprites, delay);
    // configura as op��es inicias
    setLeft(left);
    setTop(top);
    setTempoDeVida(tempoDeVida);
  }

  /**
   * Altera o left da imagem.
   * @param valor Novo valor do left (Coordenada-x)
   */
  public void setLeft(int valor) {
    left = valor;
  }

  /**
   * Altera o top da imagem.
   * @param valor Novo valor do top (Coordenada-y)
   */
  public void setTop(int valor) {
    top = valor;
  }

  /**
   * Configura um tempo de vida para a anima��o.
   * @param valor O n�mero de vezes a a animacao corrente ser� reproduzida
   * at� ser marcada como exclu�da
   */
  public void setTempoDeVida(int valor) {
    tempoDeVida = (Math.abs(valor) > 0 ? Math.abs(valor) : 1);
  }

  /**
   * Retorna a coordenada da lateral esquerda da anima��o.
   * @return Cooredenada-x da anima��o
   */
  public int getLeft() {
    return left;
  }

  /**
   * Retorna a coordenada do topo da anima��o.
   * @return Cooredenada-y da anima��o
   */
  public int getTop() {
    return top;
  }

  /**
   * Torna ativa a reprodu��o da anima��o.
   * @return Refer�ncia para o objeto
   */
  public Object ativar() {
    ativo = true;
    return this;
  }

  /**
   * P�ra a reprodu��o da anima��o.
   */
  public void desativar() {
    ativo = false;
  }

  /**
   * Informa se a anima��o est� em modo de reprodu��o.
   * @return Retorna true caso a anima��o esteja em modo de reprodu��o
   */
  public boolean isAtivo() {
    return ativo;
  }

  /**
   * Desenha o frame selecionado da anima��o.
   * @param destino Painel de destino
   * @param g Gr�fico que desenhar� a anima��o
   */
  public boolean desenha(JPanel p, Graphics g) {
    super.desenha(p, g, left, top);
    if (ativo) {
      if (avancaFrame() && super.getFrame() == 0) {
        if (--tempoDeVida == 0) { return false; }
      }
    }
    return true;
  }
}