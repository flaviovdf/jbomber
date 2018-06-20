/*
	Esta classe � para uso exclusivamente acad�mico, � absolutamente
  proibido a c�pia total ou parcial de qualquer parte deste.

	Todos os direitos reservados.
	Universidade Federal de Campina Grande.
*/
import java.awt.Graphics;

import javax.swing.*;

/**
 * Classe de componentes do jogo que se mexem pelo tabuleiro.
 *  @author Felipe Ribeiro
 *  @author Fl�vio Roberto Santos
 *  @author Fl�vio Vinicius
 *  @author Jo�o Arthur
 *  @author Thiago Emmanuel
 *  @author Vinicius Ferraz
 *  @version 1.0
 */

public class AnimacaoPersonagem extends Animacao {

  private Personagem personagem;

  /**
   * Cria uma associa��o entre um personagem e uma interface gr�fica
   * @param p Personagem que ter� interface
   * @param sprites Sprites da anima��o
   * @param delay N�mero de clocks (chamada do evento do Timer) necess�rios para
   * mudar a imagem
   */
  public AnimacaoPersonagem(Personagem p, Sprite sprites, int delay) {
    super(sprites, delay);

    personagem = p;
  }

  /**
   * Retorna o personagem associado
   * @return O personagem
   */
  public Personagem getPersonagem() {
    return personagem;
  }

  /**
   * Desenha o frame selecionado da anima��o.
   * @param destino Painel de destino
   * @param g Gr�fico que desenhar� a anima��o
   */
  public void desenha(JPanel p, Graphics g) {
    super.desenha(p, g, personagem.getLeft(), personagem.getTop());
    super.avancaFrame();
    switch (personagem.getDirecao()) {
      case 1: case 11:
        setAnimacao(0);
        break;
      case 2: case 7:
        setAnimacao(1);
        break;
      case 4: case 14:
        setAnimacao(2);
        break;
      case 8: case 13:
        setAnimacao(3);
        break;
      case 3: case 6: case 12: case 9:
        break;
      default:
        reiniciar();
    }
  }
}