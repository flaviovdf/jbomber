/*
    Esta classe é para uso exclusivamente acadêmico, é absolutamente
  proibido a cópia total ou parcial de qualquer parte deste.

    Todos os direitos reservados.
    Universidade Federal de Campina Grande.
*/
import java.util.EventObject;
import junit.framework.*;

/**
 *  Classe do tabuleiro onde os componentes se mexem
 *  @author Felipe Ribeiro
 *  @author Flávio Roberto Santos
 *  @author Flávio Vinicius
 *  @author João Arthur
 *  @author Thiago Emmanuel
 *  @author Vinicius Ferraz
 *  @version 1.0
 */

public class TesteInimigo extends TestCase implements PersonagemListener {

  private Inimigo inimigo1;


  public int
        ESQUERDA = 1, CIMA = 2,
        DIREITA = 4,  BAIXO = 8, PARADO = 0;




  /**
   * Construtor
   * @param name Test case name.
   */
  public TesteInimigo(String name) {
    super(name);
  }



  public void personagemMorre(EventObject event) {}
  public void jogadorPegaPortal(EventObject event) {}
  /**
   * Sets up the text fixture.
   *
   * Called before every test case method.
   */
  protected void setUp() {

    inimigo1 = new Inimigo(10, 10, PARADO, this);

  }

  /**
   * Tears down the text fixture.
   *
   * Called after every test case method.
   */
  protected void tearDown() {
    inimigo1 = null;

  }

  /**
   * Testa o setDirecao.
   */
  public void testSetDirecao() {

    //confere se o inimigo esta inicialmente parado
    assertEquals(PARADO, inimigo1.getDirecao());

    //altera a direcao para esquerda
    inimigo1.setDirecao(1);
    assertEquals(ESQUERDA, inimigo1.getDirecao());

    //altera a direcao para cima
    inimigo1.setDirecao(2);
    assertEquals(CIMA, inimigo1.getDirecao());

    //altera a direcao para direita
    inimigo1.setDirecao(3);
    assertEquals(DIREITA, inimigo1.getDirecao());

    //altera a direcao para baixo
    inimigo1.setDirecao(4);
    assertEquals(BAIXO, inimigo1.getDirecao());
  }

    /**
     * Testa o anda() podendo ou nao andar em todas as direcoes
     */
    public void testAnda() {

      //testa ele podendo andar para esquerda
      inimigo1.setDirecao(1);
      inimigo1.anda(true);
      assertEquals(9, inimigo1.getLeft());
      assertEquals(10, inimigo1.getTop());

      //testa ele NAO podendo andar para cima
      inimigo1.setDirecao(1);
      inimigo1.anda(false);
      assertEquals(9, inimigo1.getLeft());
      assertEquals(10, inimigo1.getTop());

      //testa ele podendo andar para cima
      inimigo1.setDirecao(2);
      inimigo1.anda(true);
      assertEquals(9, inimigo1.getLeft());
      assertEquals(9, inimigo1.getTop());

      //testa ele NAO podendo andar para cima
      inimigo1.setDirecao(2);
      inimigo1.anda(false);
      assertEquals(9, inimigo1.getLeft());
      assertEquals(9, inimigo1.getTop());

      //testa ele podendo andar para direita
      inimigo1.setDirecao(3);
      inimigo1.anda(true);
      assertEquals(10, inimigo1.getLeft());
      assertEquals(9, inimigo1.getTop());

      //testa ele NAO podendo andar para direita
      inimigo1.setDirecao(3);
      inimigo1.anda(false);
      assertEquals(10, inimigo1.getLeft());
      assertEquals(9, inimigo1.getTop());

      //testa ele podendo andar para baixo
      inimigo1.setDirecao(4);
      inimigo1.anda(true);
      assertEquals(10, inimigo1.getLeft());
      assertEquals(10, inimigo1.getTop());

      //testa ele NAO podendo andar para baixo
      inimigo1.setDirecao(4);
      inimigo1.anda(false);
      assertEquals(10, inimigo1.getLeft());
      assertEquals(10, inimigo1.getTop());

    }

  /**
   * Assembles and returns a test suite for
   * all the test methods of this test case.
   *
   * @return A non-null test suite.
   */
  public static Test suite() {

    TestSuite suite = new TestSuite(TesteInimigo.class);

    return suite;
    }

  /**
   * Returns the string representation of this test.
   *
   * @return Test case name.
   */
  public String toString() {
    return getName();
  }

  /**
   * Main.
   */
  public static void main(String args[]) {

    String[] testCaseName = {TesteInimigo.class.getName()};

    junit.swingui.TestRunner.main(testCaseName);

  }
}