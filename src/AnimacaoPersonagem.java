/*
	Esta classe é para uso exclusivamente acadêmico, é absolutamente
  proibido a cópia total ou parcial de qualquer parte deste.

	Todos os direitos reservados.
	Universidade Federal de Campina Grande.
*/
import java.awt.Graphics;

import javax.swing.*;

/**
 * Classe de componentes do jogo que se mexem pelo tabuleiro.
 *  @author Felipe Ribeiro
 *  @author Flávio Roberto Santos
 *  @author Flávio Vinicius
 *  @author João Arthur
 *  @author Thiago Emmanuel
 *  @author Vinicius Ferraz
 *  @version 1.0
 */

public class AnimacaoPersonagem extends Animacao {

  private Personagem personagem;

  /**
   * Cria uma associação entre um personagem e uma interface gráfica
   * @param p Personagem que terá interface
   * @param sprites Sprites da animação
   * @param delay Número de clocks (chamada do evento do Timer) necessários para
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
   * Desenha o frame selecionado da animação.
   * @param destino Painel de destino
   * @param g Gráfico que desenhará a animação
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