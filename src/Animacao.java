/*
	Esta classe � para uso exclusivamente acad�mico, � absolutamente
  proibido a c�pia total ou parcial de qualquer parte deste.

	Todos os direitos reservados.
	Universidade Federal de Campina Grande.
*/

import java.awt.Graphics;

import javax.swing.*;

/**
 *  Classe de componentes do jogo que se mexem pelo tabuleiro
 *  @author Felipe Ribeiro
 *  @author Fl�vio Roberto Santos
 *  @author Fl�vio Vinicius
 *  @author Jo�o Arthur
 *  @author Thiago Emmanuel
 *  @author Vinicius Ferraz
 *  @version 1.0
 */

public class Animacao {

  protected Sprite sprites;
  private int
      animacaoCorrente = 0, frame = 0,
      espaco = 0, delay;

  /**
   * Cria uma anima��o.
   * e um tempo de vida pr�-definido.
   * @param sprites Sprites da anima��o
   * @param delay N�mero de clocks (chamada do evento do Timer) necess�rios para
   * mudar a imagem
   */
  public Animacao(Sprite sprites, int delay) {
    this.sprites = sprites;
    setDelay(delay);
  }

  /**
   * Altera o n�mero de clocks necess�rios para a mudanca de imagens.
   * @param valor Valor do clock
   */
  public void setDelay(int valor) {
    delay = (valor > 0 ? valor : 1);
  }

  /**
   * Retorna o atual valor do clock
   * @return Valor do clock
   */
  public int getDelay() {
    return delay;
  }

  /**
   * Retorna o frame atual
   * @return Frame atual
   */
  public int getFrame() {
    return frame;
  }

  /**
   * Retorna ao frame inicial
   */
  public void reiniciar() {
    frame = 0;
  }

  /**
   * Passa para o proximo frame.
   * ATEN��O: O frame somente � avan�ado se o n�mero de clocks necess�rios para
   * a mudan�a for atingido.
   * @return Retorna true caso haja realmente a troca do frame
   */
  public boolean avancaFrame() {
    espaco = (espaco + 1) % delay;
    if (espaco == 0) {
      frame = (frame + 1) % sprites.getTotalFrames(animacaoCorrente);
      return true;
    }
    return false;
  }

  /**
   * Altera o valor da anima��o corrente.
   * @param valor Novo valor da anima��o corrente
   */
  public void setAnimacao(int valor) {
    if (valor != animacaoCorrente && Math.abs(valor) < sprites.getNumeroDeAnimacoes()) {
      animacaoCorrente = Math.abs(valor);
      reiniciar();
    }
  }

  /**
   * Retorna o n�mero da anima��o corrente.
   * @return N�mero da anima��o corrente
   */
  public int getAnimacaoCorrente() {
    return animacaoCorrente;
  }

  /**
   * Desenha o frame selecionado da anima��o.
   * @param p Painel de destino
   * @param g Gr�fico que desenhar� a anima��o
   * @param left Coordenada-x da anima��o
   * @param top Coordenada-y da anima��o
   */
  public void desenha(JPanel p, Graphics g, int left, int top) {
    sprites.getImageIcon(animacaoCorrente, frame).paintIcon(p, g, left, top);
  }
}