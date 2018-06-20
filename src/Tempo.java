/*
    Esta classe � para uso exclusivamente acad�mico, � absolutamente
  proibido a c�pia total ou parcial de qualquer parte deste.

    Todos os direitos reservados.
    Universidade Federal de Campina Grande.
*/
/**
 * Implementa��o da classe Tempo, ela simula a id�ia de um
 * contador com o tempo decaindo.
 *  @author Felipe Ribeiro
 *  @author Fl�vio Roberto Santos
 *  @author Fl�vio Vinicius
 *  @author Jo�o Arthur
 *  @author Thiago Emmanuel
 *  @author Vinicius Ferraz
 *  @version 1.0
 */

public class Tempo {

  private int
      segundos,
      espaco = 0, delay;

  /**
   * Constroi um novo contador com o n�mero de segundos especificado
   * @param segundos O tempo total do contador
   * @param delay Milisegundos at� o pr�ximo clock
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
   * @param delay Milisegundos at� o pr�ximo clock
   */
  public void reiniciar(int segundos, int delay) {
    this.segundos = Math.abs(segundos);
    this.delay = (delay > 0 ? delay : 1);
  }

  /**
   * Retorna a representa��o de Tempo numa string
   * @return String com informa��es do tempo
   */
  public String toString() {
    return (segundos / 60) + ((segundos % 60) < 10 ? ":0" + (segundos % 60) : ":" + (segundos % 60));
  }
}