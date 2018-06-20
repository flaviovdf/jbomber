/*
    Esta classe é para uso exclusivamente acadêmico, é absolutamente
  proibido a cópia total ou parcial de qualquer parte deste.

    Todos os direitos reservados.
    Universidade Federal de Campina Grande.
*/

import java.util.*;

/**
 *  Classe de componentes do jogo que se mexem pelo tabuleiro
 *  @author Felipe Ribeiro
 *  @author Flávio Roberto Santos
 *  @author Flávio Vinicius
 *  @author João Arthur
 *  @author Thiago Emmanuel
 *  @author Vinicius Ferraz
 *  @version 1.0
 */

public class Jogador extends Personagem {

  public static final byte
      ESQUERDA = 0x01, CIMA = 0x02,
      DIREITA = 0x04,  BAIXO = 0x08;

  private boolean invensivel = false;
  private byte direcao = 0x00;
  private int vidas = 0;
  private String nome = "[sem nome]";
  private int pontos = 0;
  // listener de eventos
  private PersonagemListener listener;

  /**
   * Cria um jogador.
   * @param left Coordenada-x do jogador
   * @param top Coordenada-y do jogador
   * @param vidas Numero de vidas inicial
   * @param pontos Numero de pontos inicial
   * @param nome String que representativa
   * @param listener Tratador dos eventos
   */
  public Jogador(int left, int top, int vidas, int pontos, String nome, PersonagemListener listener) {
    setLeft(left);
    setTop(top);
    addVidas(vidas);
    addPontos(pontos);
    setNome(nome);
    // ajusta o listener
    this.listener = listener;
  }

  /**
   * Torna o jogador invensivel
   */
  public void ficarInvensivel() {
    // cria a Thread que ativara e desativara a invensibilidade
    // depois de certo tempo
    new Thread(new Runnable() {
      public void run() {
        invensivel = true;
        try {
          Thread.sleep(7000);
        } catch(InterruptedException e) {  }
        invensivel = false;
      }
    }).start();
  }

  /**
   * Vefirica se o jogador esta invensivel
   * @return Se o jogador esta invensivel
   */
  public boolean isInvensivel() {
    return invensivel;
  }

  public void explodir() {
    listener.personagemMorre(new EventObject(this));
  }

  public void pegaPortal() {
    listener.jogadorPegaPortal(new EventObject(this));
  }

  /**
   * Aumenta o numero de pontos
   * @param valor Valor do incremento
   */
  public void addPontos(int valor) {
    pontos += valor;
  }

  /**
   * Retorna o numero de pontos
   * @return O numero de pontos
   */
  public int getPontos() {
    return pontos;
  }

  /**
   * Retorna o nome
   * @return o nome
   */
  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    try {
      if (!nome.equals("")) {
        this.nome = nome.substring(0, 8);
      }
    } catch (NullPointerException e) {
    } catch (IndexOutOfBoundsException e) {
      this.nome = nome;
    }
  }

  /**
   * Altera o numero de vidas
   * @param valor O valor do incremento das vidas (pode ser negativo)
   */
  public void addVidas(int valor) {
    vidas += valor;
  }

  /**
   * Retorna o numero de vidas
   * @return O numero de vidas
   */
  public int getVidas() {
    return vidas;
  }

  public void anda(int direcao) {

    if (direcao == Jogador.ESQUERDA) {
      setLeft(getLeft() - 2);
    }
    if (direcao == Jogador.CIMA) {
      setTop(getTop() - 2);
    }
    if (direcao == Jogador.DIREITA) {
      setLeft(getLeft() + 2);
    }
    if (direcao == Jogador.BAIXO) {
      setTop(getTop() + 2);
    }
  }

  /**
   * Altera a direcao do jogador. Se o valor entrado for positivo, este é inserido
   * no byte "direcao". No caso de ser negativo, o valor é removido.
   * @param valor Possibilidades: [1 = ESQUERDA, 2 = CIMA, 4 = DIREITA, 8 = BAIXO]
   */
  public void setDirecao(int valor) {
    int valorAbsoluto = Math.abs(valor);

    // testa se a tecla direcional jah estah no buffer de direcao
      // valor positivo: tecla pressionada | negativo: solta
      if (valor > 0) {
        direcao |= valorAbsoluto;
      }
      else { direcao &= ~valorAbsoluto; }
  }

  /**
   * Retorna a direção do jogador.
   * @return Valor correspondente a direção do jogador
   * [1 = ESQUERDA, 2 = CIMA, 4 = DIREITA, 8 = BAIXO]
   */
  public int getDirecao() {
    return direcao;
  }
}