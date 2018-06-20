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
 *  @author Felipe Ribeiro
 *  @author Flavio Roberto Santos
 *  @author Flavio Vinicius
 *  @author Joao Arthur
 *  @author Thiago Emmanuel
 *  @author Vinicius Ferraz
 *  @version 1.0
 **/
public class PainelFim extends JPanel implements KeyListener {

  private Animacao animFundo;

  public PainelFim() {
    try{
      animFundo = new Animacao(new Sprite("telas/fim", "1"), 1);
    } catch(Exception e) {
      System.err.println(e.getMessage());
      System.exit(1);
    }
  }

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

  public void paintComponent(Graphics g) {
    super.paintComponent(g);

    // desenha o fundo
    animFundo.desenha(this, g, 0, 0);
    // configura cor e fonte
    g.setColor(Color.YELLOW);
    g.setFont(new Font("Tahoma", Font.BOLD, 24));
    // imprime cabecalho do JPanel
    g.drawString("FIM", 90, 60);
    // intrucao para usuario
    g.setColor(Color.WHITE);
    g.setFont(new Font("Tahoma", Font.BOLD, 10));
    g.drawString("Pressione [ENTER] para retornar!", 25, 220);
  }
}