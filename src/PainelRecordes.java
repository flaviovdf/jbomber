/*
	Esta classe e para uso exclusivamente academico, e absolutamente
  proibido a copia total ou parcial de qualquer parte deste.

	Todos os direitos reservados.
	Universidade Federal de Campina Grande.
*/
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

/**
 * Implementacao do painel de recordes. Aqui sao mostrados os
 * melhores placares do jogo.
 *  @author Felipe Ribeiro
 *  @author Flavio Roberto Santos
 *  @author Flavio Vinicius
 *  @author Joao Arthur
 *  @author Thiago Emmanuel
 *  @author Vinicius Ferraz
 *  @version 1.0
 */

public class PainelRecordes extends JPanel implements KeyListener {

  private Animacao animFundo;
  private Registro[] melhores;

  /**
   * Cria o painel
   * @param lista Um Array de registros
   */
  public PainelRecordes(Registro[] lista) {
    try{
      animFundo = new Animacao(new Sprite("telas/recordes", "1"), 1);
      melhores = lista;
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
   * Utiliza o grafico para desenhar no painel
   * @param g Grafico que sera desenhado no painel
   */
  public void paintComponent (Graphics g) {
    super.paintComponent(g);

    // desenha o fundo
    animFundo.desenha(this, g, 0, 0);
    // configura cor e fonte
    g.setColor(Color.WHITE);
    g.setFont(new Font("Tahoma", Font.BOLD, 24));
    // imprime cabecalho do JPanel
    g.drawString("RECORDES", 40, 30);
    // reconfigura fonte
    g.setFont(new Font("Tahoma", Font.BOLD, 10));
    // imprime o nome dos recordistas
    if (melhores != null) {
      for (int i = 0; i < melhores.length; i++) {
        g.drawString( (i + 1) + "o) " + melhores[i].getNome(), 10, 100 + i * 10);
        g.drawString("( " + melhores[i].getPontuacao() + " )", 100, 100 + i * 10);
      }
    } else {
      g.drawString("A lista esta vazia!", 10, 100);
    }
    // intrucao para usuario
    g.drawString("Pressione [ENTER] para retornar!", 25, 220);
  }
}