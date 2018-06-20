/*
    Esta classe é para uso exclusivamente acadêmico, é absolutamente
  proibido a cópia total ou parcial de qualquer parte deste.

    Todos os direitos reservados.
    Universidade Federal de Campina Grande.
*/
/**
 * Implementação da classe Tempo, ela simula a idéia de um
 * contador com o tempo decaindo.
 *  @author Felipe Ribeiro
 *  @author Flávio Roberto Santos
 *  @author Flávio Vinicius
 *  @author João Arthur
 *  @author Thiago Emmanuel
 *  @author Vinicius Ferraz
 *  @version 1.0
 */

public class Tempo {

  private int
      segundos,
      espaco = 0, delay;

  /**
   * Constroi um novo contador com o número de segundos especificado
   * @param segundos O tempo total do contador
   * @param delay Milisegundos até o próximo clock
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
   * @param delay Milisegundos até o próximo clock
   */
  public void reiniciar(int segundos, int delay) {
    this.segundos = Math.abs(segundos);
    this.delay = (delay > 0 ? delay : 1);
  }

  /**
   * Retorna a representação de Tempo numa string
   * @return String com informações do tempo
   */
  public String toString() {
    return (segundos / 60) + ((segundos % 60) < 10 ? ":0" + (segundos % 60) : ":" + (segundos % 60));
  }
}