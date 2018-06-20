/*
    Esta classe e para uso exclusivamente academico, e absolutamente
  proibido a copia total ou parcial de qualquer parte deste.

    Todos os direitos reservados.
    Universidade Federal de Campina Grande.
*/
/**
 * Implementacao da classe Tempo, ela simula a ideia de um
 * contador com o tempo decaindo.
 *  @author Felipe Ribeiro
 *  @author Flavio Roberto Santos
 *  @author Flavio Vinicius
 *  @author Joao Arthur
 *  @author Thiago Emmanuel
 *  @author Vinicius Ferraz
 *  @version 1.0
 */

public class Tempo {

  private int
      segundos,
      espaco = 0, delay;

  /**
   * Constroi um novo contador com o numero de segundos especificado
   * @param segundos O tempo total do contador
   * @param delay Milisegundos ate o proximo clock
   */
  public Tempo(int segundos, int delay) {
    reiniciar(segundos, delay);
  }

  /**
   * Diminui o contador de acordo com o delay.
   * @return false caso o tempo tenha esgotado
   */
  public boolean ajustaTempo() {
    espaco += delay;

    if (espaco % 1000 == 0 && segundos > 0) {
      segundos--;
    }
    return segundos != 0;
  }

  /**
   * Reinicia o contador com novo tempo inicial e delay
   * @param segundos O tempo total do contador
   * @param delay Milisegundos ate o proximo clock
   */
  public void reiniciar(int segundos, int delay) {
    this.segundos = Math.abs(segundos);
    this.delay = (delay > 0 ? delay : 1);
  }

  /**
   * Retorna a representacao de Tempo numa string
   * @return String com informacoes do tempo
   */
  public String toString() {
    return (segundos / 60) + ((segundos % 60) < 10 ? ":0" + (segundos % 60) : ":" + (segundos % 60));
  }
}