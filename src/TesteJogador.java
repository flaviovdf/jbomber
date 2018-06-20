/*
    Esta classe é para uso exclusivamente acadêmico, é absolutamente
  proibido a cópia total ou parcial de qualquer parte deste.

    Todos os direitos reservados.
    Universidade Federal de Campina Grande.
*/


import junit.framework.*;
import java.util.EventObject;

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

public class TesteJogador extends TestCase implements PersonagemListener {

  private Jogador jogador1;

  public int
      ESQUERDA = 1, CIMA = 2,
      DIREITA = 4, BAIXO = 8, PARADO = 0, SOLTAR = -1;

  /**
   * Construtor
   * @param name Test case name.
   */
  public TesteJogador(String name) {
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

    jogador1 = new Jogador(10, 10, 0, 0, "jogador1", this);

  }

  /**
   * Tears down the text fixture.
   *
   * Called after every test case method.
   */
  protected void tearDown() {

    jogador1 = null;

  }

  /**
   * Testa o setDirecao() de jogador
   */
  public void testSetDirecao() {

    //confere se o jogador esta definido como parado
    assertEquals(PARADO, jogador1.getDirecao());

    //altera a direcao do jogador para esquerda
    jogador1.setDirecao(ESQUERDA);
    assertEquals(ESQUERDA, jogador1.getDirecao());

    //altera a direcao do jogador para cima
    jogador1.setDirecao(SOLTAR * ESQUERDA);
    jogador1.setDirecao(CIMA);
    assertEquals(CIMA, jogador1.getDirecao());

    //altera a direcao do jogador para direita
    jogador1.setDirecao(SOLTAR * CIMA);
    jogador1.setDirecao(DIREITA);
    assertEquals(DIREITA, jogador1.getDirecao());

    //altera a direcao do jogador para baixo
    jogador1.setDirecao(SOLTAR * DIREITA);
    jogador1.setDirecao(BAIXO);
    assertEquals(BAIXO, jogador1.getDirecao());

    //altera a direcao do jogador PARADO
    jogador1.setDirecao(SOLTAR * BAIXO);
    assertEquals(PARADO, jogador1.getDirecao());
  }

  /**
   * Testa a adição de pontos
   */
  public void testAddPontos() {

    assertEquals(0, jogador1.getPontos());
    jogador1.addPontos(125);
    assertEquals(125, jogador1.getPontos());

  }

  /**
   * Testa o metodo anda() de jogador
   */

  public void testAnda() {

    //andando para esquerda
    jogador1.setDirecao(ESQUERDA);
    jogador1.anda(ESQUERDA);
    assertEquals(8, jogador1.getLeft());
    assertEquals(10, jogador1.getTop());

    //andando para cima
    jogador1.setDirecao(SOLTAR * ESQUERDA);
    jogador1.setDirecao(CIMA);
    jogador1.anda(CIMA);
    assertEquals(8, jogador1.getLeft());
    assertEquals(8, jogador1.getTop());

    //andando para direita
    jogador1.setDirecao(SOLTAR * CIMA);
    jogador1.setDirecao(DIREITA);
    jogador1.anda(DIREITA);
    assertEquals(10, jogador1.getLeft());
    assertEquals(8, jogador1.getTop());

    //andando para baixo
    jogador1.setDirecao(SOLTAR * DIREITA);
    jogador1.setDirecao(BAIXO);
    jogador1.anda(BAIXO);
    assertEquals(10, jogador1.getLeft());
    assertEquals(10, jogador1.getTop());

    //confere se ele nao anda quando esta definido como parado
    jogador1.setDirecao(SOLTAR * BAIXO);
    assertEquals(10, jogador1.getLeft());
    assertEquals(10, jogador1.getTop());
  }

  /**
   * Testa a adição de vidas
   */
  public void testAddVidas() {

    assertEquals(0, jogador1.getVidas());
    jogador1.addVidas(3);
    assertEquals(3, jogador1.getVidas());
    jogador1.addVidas(-1);
    assertEquals(2, jogador1.getVidas());
    jogador1.addVidas(-2);
    assertEquals(0, jogador1.getVidas());
  }

  /**
   * Assembles and returns a test suite for
   * all the test methods of this test case.
   *
   * @return A non-null test suite.
   */
  public static Test suite() {

    TestSuite suite = new TestSuite(TesteJogador.class);

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

    String[] testCaseName = {TesteJogador.class.getName()};

    junit.swingui.TestRunner.main(testCaseName);

  }
}