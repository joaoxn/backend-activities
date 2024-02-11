package semana5;

// EX 1 - Crie a Classe Jogador
public class Jogador {
    // EX2 - Adicione encapsulamento ao semana5.Jogador
    // Todos os atributos estão private e possuem getters e setters
    private String nome;
    private int idade;
    public int pontuacao;
    private int numeroTentativas;

    public Jogador(String nome, int idade, int pontuacao, int numeroTentativas) {
        this.nome = nome;
        this.idade = idade;
        this.pontuacao = pontuacao;
        this.numeroTentativas = numeroTentativas;
    }

    public Jogador(String nome) {
        this.nome = nome;
    }

    public void adicionaPontos(int qtd) {
        this.pontuacao += qtd;
    }

    // Método perdePontos() removido, pois adicionaPontos() pode ser usado, com um parâmetro negativo.

////   public void perdePontos(int qtd) {
////        this.pontuacao -= qtd;
////   }

    public void adicionaTentativa(int qtd) {
        this.numeroTentativas+= qtd;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }


    public void setIdade(int idade) {
        this.idade = idade;
    }

    public int getPontuacao() {
        return pontuacao;
    }

    public int getNumeroTentativas() {
        return numeroTentativas;
    }
}
