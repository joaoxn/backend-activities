package semana5;

import java.util.Scanner;

// EX 6 - Crie o Jogo
public class Jogo {
    private static int numeroJogadasPPT; //PPT => Pedra Papel Tesoura
    private static int numeroJogadasAN; //AN => Adivinhar o Número
// EX 6 - Crie o Jogo
    public static boolean jogar(Jogador jogador) {
        numeroJogadasPPT++;
        jogador.adicionaTentativa(1);
        Scanner scan = new Scanner(System.in);
        while (true) {
            boolean vitoria = false;

            System.out.println("Escolha sua jogada:");
            System.out.println("1 - Pedra");
            System.out.println("2 - Papel");
            System.out.println("3 - Tesoura");
            int jogada = scan.nextInt();
            int jogadaBot = (int) Math.round(Math.random() * 2) + 1; // Random int: 1 to 3
            String jogadaElem = pptNumeroElem(jogada);
            String jogadaBotElem = pptNumeroElem(jogadaBot);

            if (jogada < 1 || jogada > 3) {
                System.out.println("Ação indisponível!");
            } else if (jogada == jogadaBot) {
                System.out.println("EMPATE! " +
                        jogadaElem + " (Você) X " + jogadaBotElem +
                        " (Bot) Tente novamente.");
                continue;
            } else if (jogada > jogadaBot || jogada == 1 && jogadaBot == 3) {
                vitoria = true;
            }
            System.out.println(vitoria ? "Você VENCEU! :)" : "Você PERDEU! :(");
            System.out.println(jogadaElem + " (Você) X " + jogadaBotElem + " (Bot)");
            System.out.println(vitoria ? "+50 Pts" : "-10 Pts");
                jogador.adicionaPontos(vitoria ? 50 : -10);
            System.out.println("\n");
                return vitoria;
        }
    }
// ppt = Pedra Papel Tesoura
    private static String pptNumeroElem(int numero) {
        return switch (numero) {
            case 1 -> "Pedra";
            case 2 -> "Papel";
            case 3 -> "Tesoura";
            default -> "Erro: Irreconhecível";
        };
    }
// EX 7 - Sobrecarga de Jogos
    public static boolean jogar(int num, Jogador jogador) {
        numeroJogadasAN++;
        Scanner scan = new Scanner(System.in);
        jogador.adicionaTentativa(1);

        boolean vitoria = false;

        int numeroSecreto = (int) Math.round(Math.random()*num);

        System.out.println("Digite um número de 0 a "+num);
        if (scan.nextInt() == numeroSecreto) {
            vitoria = true;
        }

        System.out.println(vitoria ? "Você VENCEU! :)" : "Você PERDEU! :(");
        System.out.println("Número secreto: "+ numeroSecreto);
//      *50 para equivaler ao sistema de pontuação padrão
        int pts = vitoria ? num*50 : -20;
        if (num < 0) { pts = -pts; }
        System.out.println("Ganhou: "+pts+" Pts");
        jogador.adicionaPontos(pts);

        return vitoria;
    }

    public static int getNumeroJogadasPPT() {
        return numeroJogadasPPT;
    }
    public static int getNumeroJogadasAN() {
        return numeroJogadasAN;
    }
    public static int getNumeroJogadas() {
        return numeroJogadasPPT + numeroJogadasAN;
    }
}
