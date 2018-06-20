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
 * Implementacao do painel de creditos. O painel de creditos e onde
 * sera mostrada as informacoes dos desenvolvedores do jogo.
 *  @author Felipe Ribeiro
 *  @author Flavio Roberto Santos
 *  @author Flavio Vinicius
 *  @author Joao Arthur
 *  @author Thiago Emmanuel
 *  @author Vinicius Ferraz
 *  @version 1.0
 */

public class PainelCreditos extends JPanel implements KeyListener {

  private Animacao animFundo;

  /**
   * Cria um novo painel
   */
  public PainelCreditos() {
    try{
      animFundo = new Animacao(new Sprite("telas/creditos", "1"), 1);
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
  public void paintComponent(Graphics g) {
    super.paintComponent(g);

    // desenha o fundo
    animFundo.desenha(this, g, 0, 0);
    // array de autores
    String[] autores = {
        "Felipe Ribeiro (felipe@amedia.com.br)", "Flavio Roberto (flavio@zona7.com.br)",
        "Flavio Vinicius (flaviovdf@uol.com.br)", "Joao Arthur (jota_arthur@hotmail.com)",
        "Thiago Emmanuel (thiago-manel@ig.com.br)", "Vinicius Ferraz (snoopy_ferraz@hotmail.com)" };

    // configura cor e fonte
    g.setColor(Color.YELLOW);
    g.setFont(new Font("Tahoma", Font.BOLD, 24));
    // imprime cabecalho do JPanel
    g.drawString("CREDITOS", 100, 30);
    // reconfigura fonte
    g.setFont(new Font("Tahoma", Font.BOLD, 10));
    // imprime os nomes dos autores
    for (int i = 0; i < autores.length; i++) {
      g.drawString(autores[i], 5, 120 + i * 15);
    }
    // intrucao para usuario
    g.setColor(Color.WHITE);
    g.setFont(new Font("Tahoma", Font.BOLD, 10));
    g.drawString("Pressione [ENTER] para retornar!", 25, 220);
  }
}