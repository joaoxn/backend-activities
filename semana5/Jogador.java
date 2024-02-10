package semana5;

// EX 1 - Crie a Classe semana5.Jogador
public class Jogador {
    // EX2 - Adicione encapsulamento ao semana5.Jogador
    // Todos os atributos estão private e possuem getters e setters
    private String nome;
    private int idade;
    private int pontuacao;
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

    public Jogador() {
    }

    public void adicionaPontos() {

    }
    public void perdePontos() {

    }
    public void adicionaTentativa() {

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

    public void setPontuacao(int pontuacao) {
        this.pontuacao = pontuacao;
    }

    public int getNumeroTentativas() {
        return numeroTentativas;
    }

    public void setNumeroTentativas(int numeroTentativas) {
        this.numeroTentativas = numeroTentativas;
    }
}
