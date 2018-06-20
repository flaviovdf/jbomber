/*
	Esta classe é para uso exclusivamente acadêmico, é absolutamente
  proibido a cópia total ou parcial de qualquer parte deste.

	Todos os direitos reservados.
	Universidade Federal de Campina Grande.
*/
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

/**
 * Implementação da classe PainelGameOver. Este painel é mostrado
 * quando acabam-se as vidas do jogador.
 *  @author Felipe Ribeiro
 *  @author Flávio Roberto Santos
 *  @author Flávio Vinicius
 *  @author João Arthur
 *  @author Thiago Emmanuel
 *  @author Vinicius Ferraz
 *  @version 1.0
 */

public class PainelGameOver extends JPanel implements KeyListener {

  private Animacao animFundo;

  /**
   * Cria o novo painel
   */
  public PainelGameOver() {
    try{
      animFundo = new Animacao(new Sprite("telas/gameover", "1"), 1);
    } catch(Exception e) {
      System.err.println(e.getMessage());
      System.exit(1);
    }
  }

  /**
   * Verifica o pressionamento da tecla [enter] e volta para o
   * painel de abertura
   * @param event Objeto passado pelo evento
   */
  public void keyPressed(KeyEvent event) {
    if (event.getKeyCode() == KeyEvent.VK_ENTER) {
      PainelAbertura obj;
      Principal.getReferencia().removeKeyListener(this);
      Principal.carregaPainel(obj = new PainelAbertura());
      Principal.getReferencia().addKeyListener(obj);
    }
  }
  public void keyTyped(KeyEvent event) { }
  public void keyReleased(KeyEvent event) { }

  /**
   * Utiliza o gráfico para desenhar no painel
   * @param g Gráfico que será desenhado no painel
   */
  public void paintComponent(Graphics g) {
    super.paintComponent(g);

    // desenha o fundo
    animFundo.desenha(this, g, 0, 0);
    // intrucao para usuario
    g.setColor(Color.WHITE);
    g.setFont(new Font("Tahoma", Font.BOLD, 10));
    g.drawString("Pressione [ENTER] para retornar!", 25, 220);
  }
}