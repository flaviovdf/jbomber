/*
	Esta classe é para uso exclusivamente acadêmico, é absolutamente
  proibido a cópia total ou parcial de qualquer parte deste.

	Todos os direitos reservados.
	Universidade Federal de Campina Grande.
*/
import java.awt.Graphics;

import javax.swing.*;
 /**  @author Felipe Ribeiro
 *  @author Flávio Roberto Santos
 *  @author Flávio Vinicius
 *  @author João Arthur
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
   * Cria uma animação que é marcada como excluída depois de ser reproduzida
   * "tempoDeVida" vezes.
   * @param sprites Fonte de imagens
   * @param left Coordenada-x da animação
   * @param top Coordenada-y da animação
   * @param delay Número de clocks (chamada do evento do Timer) necessários para
   * mudar a imagem
   * @param tempoDeVida Número de vezes que a animação será reproduzida
   */
  public AnimacaoTemporaria(Sprite sprites, int left, int top, int delay, int tempoDeVida) {
    super(sprites, delay);
    // configura as opções inicias
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
   * Configura um tempo de vida para a animação.
   * @param valor O número de vezes a a animacao corrente será reproduzida
   * até ser marcada como excluída
   */
  public void setTempoDeVida(int valor) {
    tempoDeVida = (Math.abs(valor) > 0 ? Math.abs(valor) : 1);
  }

  /**
   * Retorna a coordenada da lateral esquerda da animação.
   * @return Cooredenada-x da animação
   */
  public int getLeft() {
    return left;
  }

  /**
   * Retorna a coordenada do topo da animação.
   * @return Cooredenada-y da animação
   */
  public int getTop() {
    return top;
  }

  /**
   * Torna ativa a reprodução da animação.
   * @return Referência para o objeto
   */
  public Object ativar() {
    ativo = true;
    return this;
  }

  /**
   * Pára a reprodução da animação.
   */
  public void desativar() {
    ativo = false;
  }

  /**
   * Informa se a animação está em modo de reprodução.
   * @return Retorna true caso a animação esteja em modo de reprodução
   */
  public boolean isAtivo() {
    return ativo;
  }

  /**
   * Desenha o frame selecionado da animação.
   * @param destino Painel de destino
   * @param g Gráfico que desenhará a animação
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