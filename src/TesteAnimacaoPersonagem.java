/*
        Esta classe e para uso exclusivamente academico, e absolutamente
        proibido a copia total ou parcial de qualquer parte deste.

        Todos os direitos reservados.
        Universidade Federal de Campina Grande.
*/


import java.io.FileNotFoundException;
import java.util.EventObject;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Testes Automaticos para a classe AnimacaoPersonagem
 *
 * @author Felipe Ribeiro
 * @author Flavio Roberto
 * @author Flavio Vinicius
 * @author Joao Arthur
 * @author Thiago Emmanuel
 * @author Vinicius Ferraz
 * @version 1.0
 */


public class TesteAnimacaoPersonagem extends TestCase implements PersonagemListener{


  //atributos
	private AnimacaoPersonagem anime;
	private Personagem person;

        /**
         * Cria um novo Teste
         * @param name Nome do teste
         */
	public TesteAnimacaoPersonagem(String name ){

		super(name);
	}


        public void personagemMorre(EventObject event) {}
        public void jogadorPegaPortal(EventObject event) {}

        /**
         * Seta os parametros e a classe para os testes
         */
	public void setUp(){

		person = new Inimigo(25,25,1,this);
		try{
			anime = new AnimacaoPersonagem (person,new Sprite("inimigos/pinguim/", "4:4:4:4"),1);
		}catch(FileNotFoundException e){
      		System.err.println(e.getMessage());
      	}

	}

	public void tearDown(){

		anime = null;
	}

	/**
	 *Testa o metodo getPersonagem
	 */
	 public void testGetPersonagem(){

	 	assertEquals(person,anime.getPersonagem());
	 }




      public static Test suite() {

    	TestSuite suite = new TestSuite(TesteAnimacaoPersonagem.class);

        return suite;
      }
    /**
     * Main
     */
	public static void main(String args[]) {

		String[] testCaseName = {TesteAnimacaoPersonagem.class.getName()};

		junit.swingui.TestRunner.main(testCaseName);

	}

}

