/*
	Esta classe e para uso exclusivamente academico, e absolutamente
  proibido a copia total ou parcial de qualquer parte deste.

	Todos os direitos reservados.
	Universidade Federal de Campina Grande.
*/

import java.awt.Graphics;

import javax.swing.*;

/**
 *  Classe de componentes do jogo que se mexem pelo tabuleiro
 *  @author Felipe Ribeiro
 *  @author Flavio Roberto Santos
 *  @author Flavio Vinicius
 *  @author Joao Arthur
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
   * Cria uma animacao.
   * e um tempo de vida pre-definido.
   * @param sprites Sprites da animacao
   * @param delay Numero de clocks (chamada do evento do Timer) necessarios para
   * mudar a imagem
   */
  public Animacao(Sprite sprites, int delay) {
    this.sprites = sprites;
    setDelay(delay);
  }

  /**
   * Altera o numero de clocks necessarios para a mudanca de imagens.
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
   * ATENCAO: O frame somente e avancado se o numero de clocks necessarios para
   * a mudanca for atingido.
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
   * Altera o valor da animacao corrente.
   * @param valor Novo valor da animacao corrente
   */
  public void setAnimacao(int valor) {
    if (valor != animacaoCorrente && Math.abs(valor) < sprites.getNumeroDeAnimacoes()) {
      animacaoCorrente = Math.abs(valor);
      reiniciar();
    }
  }

  /**
   * Retorna o numero da animacao corrente.
   * @return Numero da animacao corrente
   */
  public int getAnimacaoCorrente() {
    return animacaoCorrente;
  }

  /**
   * Desenha o frame selecionado da animacao.
   * @param p Painel de destino
   * @param g Grafico que desenhara a animacao
   * @param left Coordenada-x da animacao
   * @param top Coordenada-y da animacao
   */
  public void desenha(JPanel p, Graphics g, int left, int top) {
    sprites.getImageIcon(animacaoCorrente, frame).paintIcon(p, g, left, top);
  }
}