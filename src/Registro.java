/*
	Esta classe e para uso exclusivamente academico, e absolutamente
  proibido a copia total ou parcial de qualquer parte deste.

	Todos os direitos reservados.
	Universidade Federal de Campina Grande.
*/
import java.io.*;

/**
 * Implementacao da classe registro. O registro e como as
 * informacoes dos recordes sao armazenadas.
 *  @author Felipe Ribeiro
 *  @author Flavio Roberto Santos
 *  @author Flavio Vinicius
 *  @author Joao Arthur
 *  @author Thiago Emmanuel
 *  @author Vinicius Ferraz
 *  @version 1.0
 */

public class Registro implements Comparable, Serializable {

  private String nome;
  private int pontos;

  /**
   * Cria um novo registro com o nome e os pontos.
   * @param nome Nome do novo registro
   * @param pontos Os pontos
   */
  public Registro(String nome, int pontos) {
    this.nome = nome;
    this.pontos = pontos;
  }

  /**
   * Retorna o nome do registro
   * @return Nome do registro
   */
  public String getNome() {
    return nome;
  }

  /**
   * Retorna a pontuacao do registro
   * @return A pontuacao
   */
  public int getPontuacao() {
    return pontos;
  }

  /**
   * Compara o registro com outro objeto.
   * @return -1 caso o outro objeto tenha pontuacao maior, 1 caso seja menor
   * e 0 se tiverem mesma pontuacao.
   */
  public int compareTo(Object o) {
    Registro aux = (Registro)o;
    if (pontos < aux.getPontuacao()) { return 1; }
    if (pontos > aux.getPontuacao()) { return -1; }
    return 0;
  }
}