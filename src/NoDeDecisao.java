// Classe que representa a pergunta e as opções do jogo
class NoDeDecisao {
    private String pergunta;
    private NoDeDecisao respostaSim; // Referência para o próximo nó se o jogador responder "sim"
    private NoDeDecisao respostaNao; // Referência para o próximo nó se o jogador responder "não"
    private PratoDoJogo pratoFinal; // Referência para o prato final caso o jogador acerte

    // Construtor
    public NoDeDecisao(String pergunta) {
        this.pergunta = pergunta;
    }

    // Método para obter a pergunta
    public String obterPergunta() {
        return pergunta;
    }

    // Métodos para obter e configurar as respostas
    public NoDeDecisao obterRespostaSim() {
        return respostaSim;
    }

    public void configurarRespostaSim(NoDeDecisao respostaSim) {
        this.respostaSim = respostaSim;
    }

    public NoDeDecisao obterRespostaNao() {
        return respostaNao;
    }

    public void configurarRespostaNao(NoDeDecisao respostaNao) {
        this.respostaNao = respostaNao;
    }

    // Métodos para obter e configurar o prato final
    public PratoDoJogo obterPratoFinal() {
        return pratoFinal;
    }

    public void configurarPratoFinal(PratoDoJogo pratoFinal) {
        this.pratoFinal = pratoFinal;
    }

    // Método para verificar se o nó atual é uma folha (não possui respostas)
    public boolean ehFolha() {
        return respostaSim == null && respostaNao == null;
    }
}
