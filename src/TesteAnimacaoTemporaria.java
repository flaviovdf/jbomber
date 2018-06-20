/*
	Esta classe e para uso exclusivamente academico, e absolutamente
	proibido a copia total ou parcial de qualquer parte deste.

	Todos os direitos reservados.
	Universidade Federal de Campina Grande.
*/


import java.io.FileNotFoundException;

/**
 * Testes Automaticos para as Animacoes Temporarias
 * 
 * @author Felipe Ribeiro
 * @author Flavio Roberto
 * @author Flavio Vinicius
 * @author Joao Arthur
 * @author Thiago Emmanuel
 * @author Vinicius Ferraz
 * @version 1.0
 */
public class TesteAnimacaoTemporaria extends TesteAnimacao {
	
	//atributos
	protected AnimacaoTemporaria tempAnimacao;
	
	/**
	 * Cria um novo Teste
	 * @param name Nome do teste
	 */
	public TesteAnimacaoTemporaria(String name ){
		super(name);
	}
	
	/**
	 * Seta os parametros e a classe para os testes
	 */	
	public void setUp(){
	    //chamada
	    super.setUp();
	    
	    try {
	        tempAnimacao = new AnimacaoTemporaria(new Sprite("bomba/", "4"),25,30,1,1);
	    }catch (FileNotFoundException e) {
	        System.err.println(e.getMessage());
	    }
	}

	/**
	 * Testa os atributos das coordenadas e metodos relacionados
	 */
	public void testCoordenadas(){

		//testa as coordenadas iniciais
		assertEquals (25,tempAnimacao.getLeft());
		assertEquals (30,tempAnimacao.getTop());

		//testa a mudanca de coordenadas
		tempAnimacao.setLeft(1);
		assertEquals (1,tempAnimacao.getLeft());

		tempAnimacao.setTop(1);
		assertEquals (1,tempAnimacao.getTop());

	}

	/**
	 * Testa a ativacao da imagem
	 */
	public void testAtivacao(){

		//testa as condicoes iniciais, a atributo ativo e iniciado como
		//false
		assertFalse (tempAnimacao.isAtivo());

		//testa a mudanca do atributo
		tempAnimacao.ativar();
		assertTrue (tempAnimacao.isAtivo());

		tempAnimacao.desativar();
		assertFalse (tempAnimacao.isAtivo());

	}
}
