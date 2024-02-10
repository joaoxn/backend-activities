package semana5;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        ArrayList<Jogador> jogadores = new ArrayList<>();

        jogadores.add(new Jogador("Bot 1", 23, 400, 2));
        jogadores.add(new Jogador("Bot 2", 16, 600, 3));
        jogadores.add(new Jogador("Bot 3", 16, 200, 3));
        jogadores.add(new Jogador("Bot 4", 16, 600, 3));
        jogadores.add(new Jogador("Bot 5", 16, 650, 3));
        jogadores.add(new Jogador("Bot 6", 16, 520, 3));
        jogadores.add(new Jogador("Bot 7", 16, 900, 3));
        jogadores.add(new Jogador("Bot 8", 16, 600, 3));
        jogadores.add(new Jogador("Bot 9", 16, 300, 3));
        jogadores.add(new Jogador("Bot 10", 16, 100, 3));
        jogadores.add(new Jogador("Bot 11", 16, 1000, 3));

        while (true) {
            // Config do jogador
            Jogador jogador = configJogador(scan, jogadores);
            if (jogador == null) continue;

            // Jogar
            // Mostrar Resultados
            organizarLista(jogadores);
            verLista(jogador, jogadores);
            // Pedir para jogar denovo
            break;
        }
    }

    // EX 5 + EX 1
    // Valida + cria o objeto jogador
    private static Jogador configJogador(Scanner scan, ArrayList<Jogador> jogadores) {
        System.out.println("Deseja criar um novo jogador?\n(Digite enter para NÃO (\"\"), string vazia");
        if(!Objects.equals(scan.nextLine(), "")) {

            System.out.println("Digite o seu usuário de jogador");

            String nome = scan.nextLine();
            boolean aprovado = validarLogin(nome, jogadores);
            if (!aprovado) {
                System.out.println("Nome inválido!");
                return null;
            }
            Jogador jogador = new Jogador(nome);
            jogadores.add(jogador);

            System.out.println("Digite a sua idade");
            jogador.setIdade(scan.nextInt());
            return jogador;
        }
        return null;
    }

    // EX 3 - Lista de melhores Jogadores
    // organizarLista() ordena os jogadores em ordem de pontuação
    public static void organizarLista(ArrayList<Jogador> lista) {
        //lista.stream().sorted(p1, p2 -> p1.getIdade() < p2.getPontuacao);
        lista.sort(Comparator.comparingInt(Jogador::getPontuacao).reversed());
    }

    // EX 4 - Ranking the Jogadores
    // Exibição da lista de jogadores
    public static void verLista(Jogador jogador, ArrayList<Jogador> lista) {
        for (int i = 0; i < 10 && i < lista.size(); i++) {
            System.out.println((i + 1) + " - " + lista.get(i).getNome());
        }
        if (lista.size() > 10) {
            System.out.println("\n"  + (lista.indexOf(jogador)+1) + " - " + jogador.getNome());
        }
    }

    //EX 5 - Valide se o jogador já existe
    public static boolean validarLogin(String nome, ArrayList<Jogador> lista) {
        for (Jogador jogador : lista) {
            if (jogador.getNome().equals(nome)) {
                return false;
            }
        }
        return true;
    }

    // jogadores.stream.sorted()
}