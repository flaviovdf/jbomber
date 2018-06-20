/*
	Esta classe é para uso exclusivamente acadêmico, é absolutamente
  proibido a cópia total ou parcial de qualquer parte deste.

	Todos os direitos reservados.
	Universidade Federal de Campina Grande.
*/
import java.awt.Graphics;
import java.awt.event.*;
import java.io.*;

import javax.swing.*;

/**
 * Implementação do painel de abertura. O painel de abertura é a
 * interface de comunicação inicial entre o usuário e o jogo.
 *  @author Felipe Ribeiro
 *  @author Flávio Roberto Santos
 *  @author Flávio Vinicius
 *  @author João Arthur
 *  @author Thiago Emmanuel
 *  @author Vinicius Ferraz
 *  @version 1.0
 */

public class PainelAbertura extends JPanel implements KeyListener {

  private Animacao animFundo, animTitulo;
  // botoes
  Animacao[] botoes = new Animacao[3];
  int indice = 0;

  /**
   * Cria um novo painel de abertura
   */
  public PainelAbertura() {

    try {
      // cria o fundo e o titulo
      animFundo = new Animacao(new Sprite("telas/abertura", "1"), 1);
      animTitulo = new Animacao(new Sprite("textos/jbomber", "1"), 1);
      // cria e SELECIONA a primeira opcao
      botoes[0] = new Animacao(new Sprite("botoes/novojogo", "2"), 1);
      botoes[0].avancaFrame();
      // cria as demais opcoes
      botoes[1] = new Animacao(new Sprite("botoes/recordes", "2"), 1);
      botoes[2] = new Animacao(new Sprite("botoes/creditos", "2"), 1);
    } catch (FileNotFoundException e) {
      System.err.println(e.getMessage());
      System.exit(1);
    }

  }

  /**
   * Utiliza o gráfico para desenhar no painel
   * @param g Gráfico que será desenhado no painel
   */
  public void paintComponent(Graphics g) {
    super.paintComponent(g);

    // desenha o fundo e o titulo
    animFundo.desenha(this, g, 0, 0);
    animTitulo.desenha(this, g, 20, 10);
    // desenha os botoes
    for (int i = 0; i < botoes.length; i++ ) {
      botoes[i].desenha(this, g, 0, 100 + i * 24);
    }
  }

  private void proximo() {
    botoes[indice].avancaFrame();
    indice = (indice + 1) % botoes.length;
    botoes[indice].avancaFrame();
    // redesenha a nova configuracao
    repaint();
  }

  private void anterior() {
    botoes[indice].avancaFrame();
    indice = (indice - 1 + botoes.length) % botoes.length;
    botoes[indice].avancaFrame();
    // redesenha a nova configuracao
    repaint();
  }

  /**
   * Trata o pressionamento de teclas.
   * @param event Objeto passado pelo evento
   */
  public void keyPressed(KeyEvent event) {
    switch (event.getKeyCode()) {
      case KeyEvent.VK_DOWN:
        proximo();
        break;
      case KeyEvent.VK_UP:
        anterior();
        break;
      case KeyEvent.VK_ENTER:
        switch (indice) {
          case 0:
            PainelJogo obj;
            Principal.getReferencia().removeKeyListener(this);
            Principal.carregaPainel(obj = new PainelJogo());
            Principal.getReferencia().addKeyListener(obj);
            break;
          case 1:
            PainelRecordes obj1;
            Principal.getReferencia().removeKeyListener(this);
            Principal.carregaPainel(obj1 = new PainelRecordes(ManipulaRecordes.getRecordes()));
            Principal.getReferencia().addKeyListener(obj1);
            break;
          case 2:
            PainelCreditos obj2;
            Principal.getReferencia().removeKeyListener(this);
            Principal.carregaPainel(obj2 = new PainelCreditos());
            Principal.getReferencia().addKeyListener(obj2);
        }
    }
  }
  public void keyTyped(KeyEvent event) { }
  public void keyReleased(KeyEvent event) { }

}