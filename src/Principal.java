/*
    Esta classe e para uso exclusivamente academico, e absolutamente
  proibido a copia total ou parcial de qualquer parte deste.

    Todos os direitos reservados.
    Universidade Federal de Campina Grande.
*/

import java.awt.Component;

import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *  Classe que contem o tabuleiro do jogo
 *  @author Felipe Ribeiro
 *  @author Flavio Roberto Santos
 *  @author Flavio Vinicius
 *  @author Joao Arthur
 *  @author Thiago Emmanuel
 *  @author Vinicius Ferraz
 *  @version Beta
 */

public class Principal extends JApplet {

  private static JApplet ap;

  /**
   * Inicializacao do applet
 * @param janela 
   */
  public void init(JFrame janela) {
    // cria o painel inicial
    PainelAbertura pnlAbertura;

    // guarda uma referencia para o applet numa variavel estatica
    ap = this;

    // carrega o painel inicial
    carregaPainel(pnlAbertura = new PainelAbertura());

    // permite que o tratamento do teclado seja processado
    janela.addKeyListener(pnlAbertura);
  }

  /**
   * Metodo Main que roda o applet como applicativo
   * @param args Argumentos de entrada
   */
  public static void main(String[] args) {
    JFrame janela = new JFrame("JBomber 1.0");
    Principal objetoApplet = new Principal();

    objetoApplet.init(janela);
    objetoApplet.start();
    janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    janela.getContentPane().add(objetoApplet);
    janela.pack();
    janela.setSize(240 + janela.getInsets().left + janela.getInsets().right,
                   232 + janela.getInsets().top + janela.getInsets().bottom);
    janela.setVisible(true);
  }

  /**
   * Retorna uma referencia para o applet
   * @return A referencia
   */
  public static Component getReferencia() {
    return ap;
  }

  /**
   * Carrega um JPanel para o container do JApplet
   * @param p Painel a ser adicionado
   */
  public static void carregaPainel(JPanel p) {
    // remove todos os componentes do container
    ap.getContentPane().removeAll();
    // insere o painel no container
    ap.getContentPane().add(p);
    // valida area de impressao
    ap.validate();
  }
}
