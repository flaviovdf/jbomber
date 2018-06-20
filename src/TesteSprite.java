/*
	Esta classe e para uso exclusivamente academico, e absolutamente
	proibido a copia total ou parcial de qualquer parte deste.

	Todos os direitos reservados.
	Universidade Federal de Campina Grande.
*/

import java.io.FileNotFoundException;

import junit.framework.TestCase;

/**
 * Testes Automaticos para a classe Sprite
 *
 * @author Felipe Ribeiro
 * @author Flavio Roberto
 * @author Flavio Vinicius
 * @author Joao Arthur
 * @author Thiago Emmanuel
 * @author Vinicius Ferraz
 * @version 1.0
 */
public class TesteSprite extends TestCase{

	//atributos
	private Sprite umSprite;

	/**
	 * Cria um novo Teste
	 * @param name Nome do Teste
	 */
	public TesteSprite(String name){
	    super(name);
	}

	/**
	 * Seta os parametros e a classe para os testes
	 */
	public void setUp(){
		try{

			umSprite = new Sprite ("inimigos/pinguim/", "4:4:4:4");
		}catch (FileNotFoundException e) {
      		System.err.println(e.getMessage());
      	}
	}

        /*
         *testa o lancamento da excecao do metodo que
         *carrega as imagens
         */
        public void testCarregaImagens(){

                Sprite outroSprite;

                try{

                        outroSprite = new Sprite ("inimigos/arquivoInexistente/", "4:4:4:4");
                        fail ("Deveria ocorre uma excecao");
                }catch (FileNotFoundException e) {

              }

        }


	/**
	 * Testa o metodo getNumeroDeAnimacoes
	 */
	public void testGetNumeroAnimacoes(){

		//o conjunto de imagens passado como parametro tem 4 imagens
		assertEquals(4,umSprite.getNumeroDeAnimacoes());

	}

	/**
	 * Teste o metodo que volta o numero de imagens
	 */
	public void testGetNumeroImagens(){
		//o conjunto de imagens passado como parametro tem
		//4 imagens em cada animacao
		assertEquals(4,umSprite.getTotalFrames(0));
		assertEquals(4,umSprite.getTotalFrames(1));
		assertEquals(4,umSprite.getTotalFrames(2));
		assertEquals(4,umSprite.getTotalFrames(3));
	}


	/**
	 * Main
	 */
	public static void main(String args[]) {

		String[] testCaseName = {TesteSprite.class.getName()};

		junit.swingui.TestRunner.main(testCaseName);

	}
}
