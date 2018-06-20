/*
    Esta classe � para uso exclusivamente acad�mico, � absolutamente
  proibido a c�pia total ou parcial de qualquer parte deste.

    Todos os direitos reservados.
    Universidade Federal de Campina Grande.
*/

import java.awt.Component;

import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *  Classe que cont�m o tabuleiro do jogo
 *  @author Felipe Ribeiro
 *  @author Fl�vio Roberto Santos
 *  @author Fl�vio Vinicius
 *  @author Jo�o Arthur
 *  @author Thiago Emmanuel
 *  @author Vinicius Ferraz
 *  @version Beta
 */

public class Principal extends JApplet {

  private static JApplet ap;

  /**
   * Inicializa��o do applet
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
   * M�todo Main que roda o applet como applicativo
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
   * Retorna uma refer�ncia para o applet
   * @return A refer�ncia
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
