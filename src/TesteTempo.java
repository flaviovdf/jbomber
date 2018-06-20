/*
	Esta classe � para uso exclusivamente acad�mico, � absolutamente
	proibido a c�pia total ou parcial de qualquer parte deste.

	Todos os direitos reservados.
	Universidade Federal de Campina Grande.
*/

import junit.framework.*;

/**
 * Testes Automaticos para a classe Tempo
 * 
 * @author Felipe Ribeiro
 * @author Fl�vio Roberto
 * @author Fl�vio Vinicius
 * @author Jo�o Arthur
 * @author Thiago Emmanuel
 * @author Vinicius Ferraz
 * @version 1.0
 */
public class TesteTempo extends TestCase{
	//atributos
	protected Tempo relogio;

	/**
	 * Cria um novo Teste
	 * @param name Nome do Teste
	 */
	public TesteTempo(String name){
		super(name);
	}
	
	/**
	 * Seta os parametros e a classe para os testes
	 */
	public void setUp(){

		relogio = new Tempo(300,25);
	}

	/**
	 * Testa a representa��o do conteudo do objeto em forma de String
	 */
	public void testMostraTempo(){

		assertEquals ("5:00",relogio.toString());
	}


	/**
	 * Testa a mudan�a de tempo
	 */
	 public void testAjustaTempo(){

		 //for�a  a passagem de 1 segundo
		for (int i=0;i<=40;i++){
		 	relogio.ajustaTempo();
		}

		assertEquals ("4:59",relogio.toString());

	 	relogio.reiniciar(241,25);
	 	 //for�a  a passagem de 1 segundo
		for (int i=0;i<=40;i++){
		 	relogio.ajustaTempo();
		}
	 	assertEquals ("4:00",relogio.toString());


	 	relogio.reiniciar(1,25);
	 	 //for�a  a passagem de 1 segundo
		for (int i=0;i<=40;i++){
		 	relogio.ajustaTempo();
		}
	 	assertEquals ("0:00",relogio.toString());
                 //for�a  a passagem de 1 segundo
               for (int i=0;i<=40;i++){
                        relogio.ajustaTempo();
               }
               assertEquals ("0:00",relogio.toString());
	 }

	/**
	 * Testa a reinicializa��o do relogio
	 */
	 public void testReiniciar(){

		relogio.reiniciar(241,25);
		assertEquals ("4:01",relogio.toString());

		relogio.reiniciar(0,25);
		assertEquals ("0:00",relogio.toString());

	}

	/**
	 * Main
	 */
	public static void main(String args[]) {

		String[] testCaseName = {TesteTempo.class.getName()};

		junit.swingui.TestRunner.main(testCaseName);

	}
}
