/*
	Esta classe e para uso exclusivamente academico, e absolutamente
  proibido a copia total ou parcial de qualquer parte deste.

	Todos os direitos reservados.
	Universidade Federal de Campina Grande.
*/
import java.awt.Graphics;

import javax.swing.*;
 /**  @author Felipe Ribeiro
 *  @author Flavio Roberto Santos
 *  @author Flavio Vinicius
 *  @author Joao Arthur
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
   * Cria uma animacao que e marcada como excluida depois de ser reproduzida
   * "tempoDeVida" vezes.
   * @param sprites Fonte de imagens
   * @param left Coordenada-x da animacao
   * @param top Coordenada-y da animacao
   * @param delay Numero de clocks (chamada do evento do Timer) necessarios para
   * mudar a imagem
   * @param tempoDeVida Numero de vezes que a animacao sera reproduzida
   */
  public AnimacaoTemporaria(Sprite sprites, int left, int top, int delay, int tempoDeVida) {
    super(sprites, delay);
    // configura as opcoes inicias
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
   * Configura um tempo de vida para a animacao.
   * @param valor O numero de vezes a a animacao corrente sera reproduzida
   * ate ser marcada como excluida
   */
  public void setTempoDeVida(int valor) {
    tempoDeVida = (Math.abs(valor) > 0 ? Math.abs(valor) : 1);
  }

  /**
   * Retorna a coordenada da lateral esquerda da animacao.
   * @return Cooredenada-x da animacao
   */
  public int getLeft() {
    return left;
  }

  /**
   * Retorna a coordenada do topo da animacao.
   * @return Cooredenada-y da animacao
   */
  public int getTop() {
    return top;
  }

  /**
   * Torna ativa a reproducao da animacao.
   * @return Referencia para o objeto
   */
  public Object ativar() {
    ativo = true;
    return this;
  }

  /**
   * Para a reproducao da animacao.
   */
  public void desativar() {
    ativo = false;
  }

  /**
   * Informa se a animacao esta em modo de reproducao.
   * @return Retorna true caso a animacao esteja em modo de reproducao
   */
  public boolean isAtivo() {
    return ativo;
  }

  /**
   * Desenha o frame selecionado da animacao.
   * @param destino Painel de destino
   * @param g Grafico que desenhara a animacao
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