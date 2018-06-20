/*
	Esta classe é para uso exclusivamente acadêmico, é absolutamente
	proibido a cópia total ou parcial de qualquer parte deste.

	Todos os direitos reservados.
	Universidade Federal de Campina Grande.
*/


import java.io.*;
import junit.framework.*;

/**
 * Testes Automaticos para as Animacoes
 *
 * @author Felipe Ribeiro
 * @author Flávio Roberto
 * @author Flávio Vinicius
 * @author João Arthur
 * @author Thiago Emmanuel
 * @author Vinicius Ferraz
 * @version 1.0
 */
public class TesteAnimacao extends TestCase {

	//atributos
	protected Animacao umaAnimacao;
	protected Animacao outraAnimacao;
	
	/**
	 * Controi um novo TestaAnimacao
	 * @param name Nome do Teste
	 */
	public TesteAnimacao (String name){
	    super(name)	;
	}
	
	/**
	 * Seta os parametros e a classe para os testes
	 */
	protected void setUp (){
		try {
			umaAnimacao = new Animacao(new Sprite("bomba/", "4"), 3);
			outraAnimacao = new Animacao (new Sprite("inimigos/pinguim/", "4:4:4:4"), 3);
		}catch (FileNotFoundException e) {
      		System.err.println(e.getMessage());
      	}
	}

	/**
	 * Testa o Delay
	 */
	public void testDelay(){
          //testa o atributo delay, que foi passao no construtor
          assertEquals(3, umaAnimacao.getDelay());

          //para valores menores que 1, passados como parametro
          //o atributo delay e setado para 1
          umaAnimacao.setDelay(0);
          assertEquals(1, umaAnimacao.getDelay());

          umaAnimacao.setDelay( -1);
          assertEquals(1, umaAnimacao.getDelay());

          umaAnimacao.setDelay(1);
          assertEquals(1, umaAnimacao.getDelay());

	}

	/**
	 * Testa o atributo frame, assim como a mudanca do atributo
	 */
	public void testFrame(){

		//seta o atributo delay em 1,para testar a mudanca
		//imediata de frames
		umaAnimacao.setDelay(1);

		//testa o atributo frame, o atributo é setado no inicio como 0
		assertEquals(0,umaAnimacao.getFrame());

		//testa a mudanca de frames,nesta animacao existem 4 frames no total
		umaAnimacao.avancaFrame();
		assertEquals (1,umaAnimacao.getFrame());

		//realiza dois avanços
		for (int i=0;i<=1;i++){
			umaAnimacao.avancaFrame();
		}

		assertEquals (3,umaAnimacao.getFrame());

		//testa se esta voltando para o primeiro frame
		//quando chega ao final
		umaAnimacao.avancaFrame();
		assertEquals (0,umaAnimacao.getFrame());

		//seta o atributo delay em 2,para testar a mudanca
		//nao imediata de frames, a mudanca deve ocorre a cada
		//dois comandos de avanço
		umaAnimacao.setDelay(2);

		for (int i=0;i<=1;i++){
			umaAnimacao.avancaFrame();
		}

		assertEquals (1,umaAnimacao.getFrame());

	}


	/**
	 * Testa  o metodo reiniciar
	 */
	 public void testReiniciar(){

	 	//seta o atributo delay em 1,para testar a mudanca
		//imediata de frames
		umaAnimacao.setDelay(1);

		umaAnimacao.avancaFrame();
		umaAnimacao.reiniciar();
		assertEquals(0,umaAnimacao.getFrame());

	 }

	 /**
	  * Testa o atributo animacao corrente, e metodos relacionados
	  */
	 public void testAnimacaoCorrente (){
	     
	     //o atributo animacao corrente,e inicializado com o valor
	     //zero
	     assertEquals (0,outraAnimacao.getAnimacaoCorrente());
	     
	     //testa a mudanca da animacao
	     //o atributo outraAnimacao tem 4 sequencias de animacao
	     //diferentes
	     outraAnimacao.setAnimacao(1);
	     assertEquals (1,outraAnimacao.getAnimacaoCorrente());
	     //seta o atributo delay em 1,para testar a mudanca
	     //imediata de frames
	     umaAnimacao.setDelay(1);
	     
	     //muda de frames,nesta animacao existem 4 frames no total
	     umaAnimacao.avancaFrame();
	     outraAnimacao.setAnimacao(2);
	     //quando ocorre a mudança de animacao, o frame atual
	     //deve ser o 0
	     assertEquals(0,outraAnimacao.getFrame());
	 }

}

