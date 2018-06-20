/*
	Esta classe e para uso exclusivamente academico, e absolutamente
  proibido a copia total ou parcial de qualquer parte deste.

	Todos os direitos reservados.
	Universidade Federal de Campina Grande.
*/
import java.awt.*;
import java.awt.event.*;
import java.util.EventObject;
import java.util.Iterator;

import javax.swing.*;
/**
 *  @author Felipe Ribeiro
 *  @author Flavio Roberto Santos
 *  @author Flavio Vinicius
 *  @author Joao Arthur
 *  @author Thiago Emmanuel
 *  @author Vinicius Ferraz
 *  @version 1.0
 */
public class PainelDemonstracao extends JPanel implements Constantes, PersonagemListener, ActionListener, KeyListener {

  private Tabuleiro tab;
  private Timer timer1;

  public PainelDemonstracao() {
		carregaFase();
  }

	public void carregaFase () {
		tab = new Tabuleiro(new Jogador(LADO, LADO, 3, 0, "[sem nome]", this), 0, this);
		timer1 = new Timer(25, this);
		timer1.start();
	}

  public void actionPerformed(ActionEvent actionEvent) {
    tab.reajustaPosicoes();
    repaint();
  }

	/**
	 * Verifica se o jogador morreu
	 */
  public void personagemMorre(EventObject event) {
  	if (event.getSource() instanceof Jogador) {
			tab.getJogador().addVidas(-1);
			carregaFase();
  	}
  }

  /**
   * Jogador pega portal para mudar de fase
   */
  public void jogadorPegaPortal(EventObject event) {
		if (event.getSource() == tab.getJogador()) {
			carregaFase();
		}
  }

	/**
	 * Desenha os componentes na tela
	 */
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    for (int i = 0; i < tab.getNumeroLinhas(); i++) {
      for (int j = 0; j < tab.getNumeroColunas(); j++) {
        //Desenha os retangulos
        g.drawRect(j * 30, i * 30, 30, 30);
  			//Desenha o portal quando nao houver inimigos
  			if (tab.temPortal(i, j)) {
				  g.drawString(".PO", j * 30, (i + 1) * 30);
				}
				//Desenha itens caso o bloco seja explodido
				else if (tab.getItem(i, j) != -1 && !tab.temBloco(i,j)) {
					switch (tab.getItem(i, j)) {
						case 0:
							g.drawString(".MB", j * 30, (i + 1) * 30);
				  		break;
				  	case 1:
							g.drawString(".ME", j * 30, (i + 1) * 30);
							break;
						case 2:
							g.drawString(".MV", j * 30, (i + 1) * 30);
							break;
					}
				}
				/* desenho dos blocos ficou por ultimo para quando aparecer
				 * itens um nao sobrescrever o outro
				 */
				else {
					g.drawString("." + tab.getTermo(i, j), j * 30, (i + 1) * 30);
				}
      }
    }
    g.setColor(Color.blue);
    g.drawOval(tab.getJogador().getLeft(), tab.getJogador().getTop(), 30, 30);
    g.setColor(Color.red);
    // inimigos
    Iterator seta = tab.iteratorDeInimigos();
    while (seta.hasNext()) {
      Inimigo aux = (Inimigo)seta.next();
      g.drawOval(aux.getLeft(), aux.getTop(), 30, 30);
    }
  }

  public void keyPressed(KeyEvent event) {
    switch (event.getKeyCode()) {
      case 37: case 38: case 39: case 40:
        if ((tab.getJogador().getDirecao() & (int)Math.pow(2, event.getKeyCode() - 36 - 1)) == 0) {
          tab.getJogador().setDirecao((int)Math.pow(2, event.getKeyCode() - 36 - 1));
        }
        break;
      case KeyEvent.VK_CONTROL:
        tab.colocaBomba(tab.getJogador(), (tab.getJogador().getTop() + LADO / 2) / LADO, (tab.getJogador().getLeft() + LADO / 2) / LADO);
    }
  }
  public void keyTyped(KeyEvent event) {  }
  public void keyReleased(KeyEvent event) {
    switch (event.getKeyCode()) {
      case 37: case 38: case 39: case 40:
        tab.getJogador().setDirecao(- (int)Math.pow(2, event.getKeyCode() - 36 - 1));
    }
  }
}