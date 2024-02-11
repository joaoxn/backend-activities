package semana5;

import java.util.Scanner;

public class AdivinhaNumero {
    // NÃO É PROPOSTA DO EXERCÍCIO! REUTILIZEI UM JOGO JÁ FEITO POR MIM!
    public static void jogar(Jogador jogador) {
        jogador.adicionaTentativa(1);

        Scanner scan = new Scanner(System.in);

        System.out.println("Jogo INICIALIZADO!");
        System.out.print("Digite o número MÍNIMO para ser adivinhado: ");
        int numeroMin = scan.nextInt();
        System.out.print("Digite o número MÁXIMO para ser adivinhado: ");
        int numeroMax = scan.nextInt();
        System.out.print("Digite o número de tentativas: ");
        byte tentativasMax = scan.nextByte();
        byte tentativas = 0;
        int numeroAdivinhado;

        boolean venceu = false;
        int numeroSecreto = (int) (Math.random() * (numeroMax - numeroMin) + numeroMin);
        System.out.println();
        do {
            tentativas++;
            if (tentativas > tentativasMax) {
                break;
            }
            do {
                System.out.println("Tentativa no." + tentativas + " (" + tentativas + "/" + tentativasMax + ")");
                System.out.print("Adivinhe um número de " + numeroMin + " a " + numeroMax + ": ");
                try {
                    numeroAdivinhado = scan.nextInt();
                    break;
                } catch (Exception e) {
                    System.out.println("Valor inválido, tente novamente. \n");
                    scan.nextLine();
                }
            } while (true);
            if (numeroAdivinhado == numeroSecreto) {
                venceu = true;
            } else if (numeroSecreto > numeroAdivinhado) {
                System.out.println("O número secreto é MAIOR que " + numeroAdivinhado + ". Tente novamente");
            } else {
                System.out.println("O número secreto é MENOR que " + numeroAdivinhado + ". Tente novamente");
            }
            System.out.println();
        } while (!venceu);
        byte pontuacaoPorcentagem = (byte) ((float) (100 * tentativas) / tentativasMax);
        //byte pontuacao = (byte) (100 - (100 * (float) (tentativas - 1) / (tentativasMax - 1)));
        //int pontuacao = (int) Math.round(50 * tentativasMax * Math.log(numeroMax - numeroMin + 2) / Math.log(2) /  (tentativas * tentativas)); //avg is 50, but max is undefined
        //PONTUACAO NAO FAZ SENTIDOOOOOOOOOO
        //int pontuacao = (int) Math.round(5 * Math.log(numeroMax - numeroMin + 2) / Math.log(2)/tentativasMax * ((double) (tentativasMax + 1) /tentativas - 1)); //avg is 50, but max is undefined
        int pontuacao = (int) Math.round(50 * Math.log(numeroMax - numeroMin + 2)/Math.log(2) / tentativasMax * ((double) (tentativasMax) / Math.pow(2, tentativas-1))); //avg is 50, but max is undefined
        if (venceu) {
            System.out.println("Você venceu! " + tentativas + " tentativas usadas.");
            System.out.println("Sua pontuação é: " + (pontuacao + 50) + ". Você conseguiu em " + pontuacaoPorcentagem + "% das tentativas totais!");
            jogador.adicionaPontos(pontuacao);
        } else {
            System.out.println("Você perdeu! :( Número de tentativas excedidas" + " (" + tentativasMax + "/" + tentativasMax + ")");
            System.out.println("Sua pontuação é: 0/150.");
            System.out.println("O número secreto era: " + numeroSecreto);
        }
    }
}
