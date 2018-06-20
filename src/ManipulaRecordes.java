/*
	Esta classe é para uso exclusivamente acadêmico, é absolutamente
  proibido a cópia total ou parcial de qualquer parte deste.

	Todos os direitos reservados.
	Universidade Federal de Campina Grande.
*/
import java.util.Arrays;
import java.io.*;

/**
 * Implementação da manipulação de arquivos necessárias para se gravar
 * os recordes do jogo.
 *  @author Felipe Ribeiro
 *  @author Flávio Roberto Santos
 *  @author Flávio Vinicius
 *  @author João Arthur
 *  @author Thiago Emmanuel
 *  @author Vinicius Ferraz
 *  @version 1.0
 */

public class ManipulaRecordes {

  private static final String NOME_ARQUIVO = "recordes.dat";

  /**
   * Verifica se o arquivo já foi criado
   * @return true caso ja exista o arquivo
   */
  public static boolean existeArquivo() {
    ObjectInputStream in;

    try {
      in = new ObjectInputStream(new FileInputStream(NOME_ARQUIVO));
      in.close();
    } catch (FileNotFoundException e) {
      return false;
    } catch (Exception e) { }
    // se nao lancou excecao eh pq o arquivo existe
    return true;
  }

  // cria o arquivo de recordes
  private static void criaArquivo() {
    ObjectOutputStream out;

    try {
      out = new ObjectOutputStream(new FileOutputStream(NOME_ARQUIVO));
      // inicializa todas as posições com jogadores
      Registro[] conteudo = new Registro[10];
      for (int i = 0; i < conteudo.length; i++) {
        conteudo[i] = new Registro("[vazio]", 0);
      }
      out.writeObject(conteudo);
      out.close();
    } catch (FileNotFoundException e) {
      System.err.println("Nao foi possivel criar o arquivo "+ NOME_ARQUIVO);
      System.exit(1);
    } catch (Exception e) { }
  }

  /**
   * Adiciona um registro ao conteudo e depois chama o método gravaNoArquivo()
   * @param nome Nome do jogador
   * @param pontos Pontos do jogador
   */
  public static void gravaRecorde(String nome, int pontos) {
    ObjectInputStream in;

    // se arquivo nao existir, cria-o
    if (!existeArquivo()) {
      criaArquivo();
    }
    try {
      in = new ObjectInputStream(new FileInputStream(NOME_ARQUIVO));
      // conteudo recebe o que tem no arquivo em disco
      Registro[] conteudo = (Registro[]) in.readObject();
      // verifica se o jogador deve ser inserido nos recordes
      if (pontos > conteudo[conteudo.length -1].getPontuacao()) {
        // insere na ultima posicao
        conteudo[conteudo.length - 1] = new Registro(nome, pontos);
        // ordena o array
        Arrays.sort(conteudo);
        // grava no arquivo
        gravaNoArquivo(conteudo);
      }
      in.close();
    } catch (Exception e) { }
  }

  // grava o conteúdo em disco
  private static void gravaNoArquivo(Registro[] conteudo) {
    ObjectOutputStream out;

    try {
      out = new ObjectOutputStream(new FileOutputStream(NOME_ARQUIVO));
      out.writeObject(conteudo);
      out.close();
    } catch (FileNotFoundException e) {
      System.err.println("Nao foi possivel criar o arquivo "+ NOME_ARQUIVO);
      System.exit(1);
    } catch (Exception e) { }
  }

  /**
   * Lê conteúdo gravado em disco.
   * @return Um array de registros
   */
  public static Registro[] getRecordes() {
    ObjectInputStream in;

    try {
      in = new ObjectInputStream(new FileInputStream(NOME_ARQUIVO));
      Registro[] aux = (Registro[]) in.readObject();
      in.close();
      return aux;
    } catch (Exception e) { }
    // se o arquivo nao existir, retorna NULL
    return null;
  }

}