/*
	Esta classe e para uso exclusivamente academico, e absolutamente
	proibido a copia total ou parcial de qualquer parte deste.

	Todos os direitos reservados.
	Universidade Federal de Campina Grande.
*/

import junit.framework.*;
import java.util.EventObject;

/**
 * Testes Automaticos para a classe Tabuleiro
 *
 * @author Felipe Ribeiro
 * @author Flavio Roberto
 * @author Flavio Vinicius
 * @author Joao Arthur
 * @author Thiago Emmanuel
 * @author Vinicius Ferraz
 * @version 1.0
 */

public class TesteTabuleiro extends TestCase implements PersonagemListener {

	//atributos
	Tabuleiro tab;

	/**
	 * Cria um novo Teste
	 * @param name Nome do Teste
	 */
	public TesteTabuleiro(String name) {
		super(name);
	}

    public void personagemMorre(EventObject event) {}

    public void jogadorPegaPortal(EventObject event) {}

	/**
	 * Seta os parametros e a classe para os testes
	 */
	protected void setUp() {
//		Jogador jogador = new Jogador(10, 10, 3, 0, "jogador1", this);
		tab = new Tabuleiro(new Jogador(10,10,3,0,"jogador teste",this),0,this);

	}

	/**
	 * Metodo chamado apos cada teste para reiniciar o tabuleiro
	 */
	protected void tearDown() {
		tab = null;
	}

	/**
	 * Testa o metodo colocaBomba
	 */
	public void testColocabomba(){
		Inimigo inimigo = new Inimigo(10, 10, 2, this);
		if (tab.estaLivre(2,2)){
			tab.colocaBomba(inimigo,2,2);
			assertTrue(tab.temBomba(2,2));
		}
	}

	/**
	 * Testa o metodo temBomba
	 */
	public void testTemBomba() {
		Inimigo inimigo = new Inimigo(10, 10, 2, this);
		if (tab.estaLivre(2,2)) {
			tab.colocaBomba((Personagem)inimigo,2,2);
			assertTrue(tab.temBomba(2,2));
		}
	}

	/**
	 * Testa o return de numero de colunas
	 */
	public void testGetNumeroColunas(){
		assertEquals(15,tab.getNumeroColunas(),0);
	}

	/**
	 * Testa o return do numero de linhas
	 */
	public void testGetNumeroLinhas(){
		assertEquals(13,tab.getNumeroLinhas(),0);
	}

	/**
	 * Testa o return do getFase
	 */
	public void testGetFase(){
		assertEquals(0,tab.getFase(),0);
	}
	/*
	public void testGetJogador(){
		assertSame(tab.getJogador(),new Jogador(10, 10, 3, 0, "jogador1", this)));
	}
	*/

	/**
	 * Main
	 */
	public static void main(String[] args) {
	    junit.swingui.TestRunner.run(TesteTabuleiro.class);
	}

}

