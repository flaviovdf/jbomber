/*
	Esta classe é para uso exclusivamente acadêmico, é absolutamente
  proibido a cópia total ou parcial de qualquer parte deste.

	Todos os direitos reservados.
	Universidade Federal de Campina Grande.
*/

import java.awt.Graphics;

import javax.swing.*;

/**
 *  Classe de componentes do jogo que se mexem pelo tabuleiro
 *  @author Felipe Ribeiro
 *  @author Flávio Roberto Santos
 *  @author Flávio Vinicius
 *  @author João Arthur
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
   * Cria uma animação.
   * e um tempo de vida pré-definido.
   * @param sprites Sprites da animação
   * @param delay Número de clocks (chamada do evento do Timer) necessários para
   * mudar a imagem
   */
  public Animacao(Sprite sprites, int delay) {
    this.sprites = sprites;
    setDelay(delay);
  }

  /**
   * Altera o número de clocks necessários para a mudanca de imagens.
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
   * ATENÇÃO: O frame somente é avançado se o número de clocks necessários para
   * a mudança for atingido.
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
   * Altera o valor da animação corrente.
   * @param valor Novo valor da animação corrente
   */
  public void setAnimacao(int valor) {
    if (valor != animacaoCorrente && Math.abs(valor) < sprites.getNumeroDeAnimacoes()) {
      animacaoCorrente = Math.abs(valor);
      reiniciar();
    }
  }

  /**
   * Retorna o número da animação corrente.
   * @return Número da animação corrente
   */
  public int getAnimacaoCorrente() {
    return animacaoCorrente;
  }

  /**
   * Desenha o frame selecionado da animação.
   * @param p Painel de destino
   * @param g Gráfico que desenhará a animação
   * @param left Coordenada-x da animação
   * @param top Coordenada-y da animação
   */
  public void desenha(JPanel p, Graphics g, int left, int top) {
    sprites.getImageIcon(animacaoCorrente, frame).paintIcon(p, g, left, top);
  }
}