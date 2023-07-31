import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Classe principal do jogo
public class Main {
    private NoDeDecisao raiz; // Referência para o nó raiz da árvore de decisão
    private List<String> pratosRespondidos; // Lista de pratos já respondidos

    // Construtor: Inicializa o jogo com uma pergunta e opções iniciais
    public Main() {
        raiz = new NoDeDecisao("O prato que você pensou é massa?");
        raiz.configurarRespostaSim(new NoDeDecisao("O prato que você pensou é Lasanha?"));
        raiz.configurarRespostaNao(new NoDeDecisao("O prato que você pensou é Bolo de Chocolate?"));
        raiz.obterRespostaSim().configurarPratoFinal(new PratoDoJogo("Lasanha"));
        raiz.obterRespostaNao().configurarPratoFinal(new PratoDoJogo("Bolo de Chocolate"));

        pratosRespondidos = new ArrayList<>();
    }

    // Método para jogar o jogo
    public void jogar() {
        Scanner scanner = new Scanner(System.in);
        NoDeDecisao noAtual = raiz;

        // O jogo faz perguntas até chegar a um prato final
        while (true) {
            while (!noAtual.ehFolha()) {
                System.out.println(noAtual.obterPergunta() + " (s/n)");
                String resposta = scanner.nextLine().toLowerCase();

                if (resposta.equals("s")) {
                    noAtual = noAtual.obterRespostaSim(); // Se o jogador responder "sim", vai para a próxima pergunta de "sim"
                } else {
                    noAtual = noAtual.obterRespostaNao(); // Se o jogador responder "não", vai para a próxima pergunta de "não"
                }
            }

            // Ao chegar em um prato final, o jogo tenta adivinhar qual é
            System.out.println("O prato que você pensou é " + noAtual.obterPratoFinal().obterNomeDoPrato() + "? (s/n)");
            String respostaFinal = scanner.nextLine().toLowerCase();

            // Se o jogo acertar, a partida termina
            if (respostaFinal.equals("s")) {
                System.out.println("Acertei de novo!");
                break;
            } else {
                // Se o jogo errar, ele aprende com a resposta do jogador e adiciona um novo prato à lista de opções
                System.out.println("Qual prato você pensou?");
                String novoPrato = scanner.nextLine();

                System.out.println(novoPrato + " é ________ mas " + noAtual.obterPratoFinal().obterNomeDoPrato() + " não.");
                String caracteristica = scanner.nextLine();

                // O jogo adiciona o novo prato e a característica à árvore de decisão
                PratoDoJogo pratoNovo = new PratoDoJogo(novoPrato);
                NoDeDecisao novoNoSim = new NoDeDecisao(novoPrato + " " + caracteristica);
                NoDeDecisao novoNoNao = new NoDeDecisao(noAtual.obterPratoFinal().obterNomeDoPrato() + " " + caracteristica);

                noAtual.configurarRespostaSim(novoNoSim);
                noAtual.configurarRespostaNao(novoNoNao);
                noAtual.configurarPratoFinal(pratoNovo);

                novoNoSim.configurarPratoFinal(new PratoDoJogo(novoPrato));
                novoNoNao.configurarPratoFinal(new PratoDoJogo(noAtual.obterPratoFinal().obterNomeDoPrato()));

                // Reiniciar o jogo
                pratosRespondidos.add(noAtual.obterPergunta());
                noAtual = raiz;
            }
        }
    }

    // Método principal para iniciar o jogo
    public static void main(String[] args) {
        Main jogo = new Main();
        System.out.println("Pense em um prato que gosta");
        jogo.jogar();
    }
}
