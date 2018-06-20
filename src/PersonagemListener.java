/*
	Esta classe é para uso exclusivamente acadêmico, é absolutamente
  proibido a cópia total ou parcial de qualquer parte deste.

	Todos os direitos reservados.
	Universidade Federal de Campina Grande.
*/
import java.util.*;
/**
 *  @author Felipe Ribeiro
 *  @author Flávio Roberto Santos
 *  @author Flávio Vinicius
 *  @author João Arthur
 *  @author Thiago Emmanuel
 *  @author Vinicius Ferraz
 *  @version 1.0
 */
public interface PersonagemListener {

  public void personagemMorre(EventObject event);

  public void jogadorPegaPortal(EventObject event);
}