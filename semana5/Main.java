package semana5;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

// EX 8 - Main e loop principal => Crie a Classe Main
public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        // EX 3 - Crie uma lista de melhores jogadores
        // A lista é ordenada toda a vez que organizarLista() é chamado.
        ArrayList<Jogador> jogadores = new ArrayList<>();
        jogadores.add(new Jogador("Bot 1", 23, 405, 25));
        jogadores.add(new Jogador("Bot 2", 16, 10, 3));
        jogadores.add(new Jogador("Bot 3", 34, 20, 2));
        jogadores.add(new Jogador("Bot 4", 19, 0, 4));
        jogadores.add(new Jogador("Bot 5", 41, 0, 2));
        jogadores.add(new Jogador("Bot 6", 13, 150, 5));
        jogadores.add(new Jogador("Bot 7", 28, 20, 6));
        jogadores.add(new Jogador("Bot 8", 69, 240, 15));
        jogadores.add(new Jogador("Bot 9", 36, -10, 1));
        jogadores.add(new Jogador("Bot 10", 12, -50, 4));
        jogadores.add(new Jogador("Bot 11", 14, -20, 3));
        LOOPPRINCIPAL:
        while (true) { // EX 8 - Loop principal
            // Config do jogador
            Jogador jogador;
            switch (menu(2, scan)) {
                case 1: // ENTRAR NA CONTA => EX 8 - Identificar pelo nome
                    System.out.print("Digite o usuário do jogador: ");
                    Jogador jogadorLogin = validarLogin(scan.nextLine(), jogadores);
                    if (jogadorLogin == null) {
                        System.out.println("Jogador inexistente! Tente um nome da lista:");
                        verLista(1, null, jogadores);
                        continue;
                    }
                    System.out.print("Digite a idade na conta, para confirmar sua identidade: ");
                    int jogadorIdade = nextInt(scan);
                    if (jogadorLogin.getIdade() == jogadorIdade) {
                        jogador = jogadorLogin;
                    } else {
                        System.out.println("Idade inválida! Tente novamente.");
                        continue;
                    }
                    break;
                case 2: // CRIAR CONTA => EX 8 - Criar um novo jogador
                    jogador = criarJogador(scan, jogadores);
                    break;
                default: // FECHAR PROGRAMA
                    break LOOPPRINCIPAL;
            }
//            jogador = configJogador(scan, jogadores);
            if (jogador == null) continue;
            MENU:
            while(true) { // Loop do MENU
                switch (menu(0, scan)) {
                    case 1: // VER LISTA COMPLETA
                        verLista(1, jogador, jogadores);
                        break;
                    case 2: // VER TOP 10
                        verLista(0, jogador, jogadores);
                        break;
                    case 3: // JOGAR
                        switch (menu(1, scan)) {
                            case 1:
                                Jogo.jogar(jogador);
                                break;
                            case 2:
                                System.out.println("Deseja adivinhar números de 0 a quanto?");
                                System.out.println("Dica: valores maiores dão mais pontos!");
                                Jogo.jogar(nextInt(scan), jogador);
                                break;
                            case 3:
                                // ADIVINHA NÚMERO NÃO É DA PROPOSTA DE EXERCÍCIO!
                                AdivinhaNumero.jogar(jogador);
                                // Usei um jogo velho que eu já tinha feito, só faltou adaptar
                                break;
                            case 4:
                                // JOGO FORCA NÃO É DA PROPOSTA DO EXERCÍCIO!
                                JogoForca.jogar(jogador);
                                // Usei um jogo antigo que já tinha pronto e feito, só faltou adaptar
                                break;
                        }
                        break;
                    case 4: // EXIBIR INFORMAÇÕES DO USUÁRIO
                        organizarLista(jogadores); // atualizar posição no placar
                        System.out.println("Nome: "+jogador.getNome());
                        System.out.println("Idade: "+jogador.getIdade());
                        System.out.println("Pontuação total: "+jogador.getPontuacao());
                        System.out.println("Número de tentativas: "+jogador.getNumeroTentativas());
                        System.out.println("Posição no placar: "+(jogadores.indexOf(jogador)+1));
                        System.out.println();
                        break;
                    case 5: // EXIBIR INFORMAÇÕES DOS JOGOS
                        System.out.println("Jogadas totais de Pedra Papel Tesoura: "+Jogo.getNumeroJogadasPPT());
                        System.out.println("Jogadas totais de Adivinhe o Número: "+Jogo.getNumeroJogadasAN());
                        System.out.println("Jogadas totais: "+Jogo.getNumeroJogadas());
                        break;
                    case 0: // SAIR DA CONTA
                        break MENU;
                    case -1: // DELETAR A CONTA
                        System.out.println("Você está prestes a Deletar Sua Conta PARA SEMPRE! (é um tempão!)");
                        System.out.println("Digite seu usuário completo para prosseguir");
                        if (!scan.nextLine().equalsIgnoreCase(jogador.getNome())) {
                            System.out.println("Nome incorreto, cancelando...");
                            break;
                        }
                        System.out.println("Digite sua idade para DELETAR SUA CONTA");
                        if (nextInt(scan) != jogador.getIdade()) {
                            System.out.println("Idade incorreta, cancelando...");
                            break;
                        }
                        jogadores.remove(jogador);
                        break MENU;
                }
            }
        }
    }

    // EX 8 - Main e loop principal
    private static int menu(int tipo, Scanner scan) {
        System.out.println();
        switch (tipo) {
            case 0: // EX 8 - Opções do MENU
                System.out.println("Bem-vindo ao MENU!");
                System.out.println("Escolha o número da ação desejada.\n");
                System.out.println("1 - Ver ranking completo");
                System.out.println("2 - Ver top 10");
                System.out.println("3 - Jogar");
                System.out.println("4 - Ver meus dados");
                System.out.println("5 - Ver dados dos jogos");
                System.out.println("0 - Sair da conta (!)");
                System.out.println("-1 - Deletar conta (!!!)");
                System.out.print("\nOpção escolhida: ");
                return nextInt(scan);
            case 1:
                System.out.println("Que jogo deseja jogar?");
                System.out.println("1 - Pedra Papel Tesoura");
                System.out.println("2 - Adivinhe o Número");
                System.out.println("3 - Adivinhe o Número (Avançado) (Extra)");
                System.out.println("4 - Jogo da Forca (Avançado) (Extra)");
                System.out.println("0 - Voltar");
                System.out.println("\nOpção escolhida: ");
                return nextInt(scan);
            default:
                System.out.println("O que deseja fazer?");
                System.out.println("1 - Entrar como um jogador existente");
                System.out.println("2 - Criar um novo jogador");
                System.out.println("0 - Encerrar o programa");
                System.out.println("\nOpção escolhida: ");
                return nextInt(scan);
        }
    }

    // EX 1 - Instanciar jogador
    // EX 5  - Validar o jogador
    // Valida + cria o objeto jogador
    private static Jogador criarJogador(Scanner scan, ArrayList<Jogador> jogadores) {
        System.out.println("Digite o seu usuário de jogador");

        String nome = scan.nextLine();
        Jogador jogadorRepetido = validarLogin(nome, jogadores);
        if (jogadorRepetido != null) {
            System.out.println("Nome inválido!");
            return null;
        }
        Jogador jogador = new Jogador(nome);
        jogadores.add(jogador);

        System.out.println("Digite a sua idade");
        jogador.setIdade(nextInt(scan));
        return jogador;
    }

    // EX 3 - Lista de melhores Jogadores
    // organizarLista() ordena os jogadores em ordem de pontuação. Se empatar, prioriza o jogador com menos tentativas.
    public static void organizarLista(ArrayList<Jogador> lista) {
        //lista.stream().sorted(p1, p2 -> p1.getIdade() < p2.getPontuacao);
        // Sort baseado em maior pontuação. Critério de empate: menor qtd de tentativas
        lista.sort(Comparator.comparingInt(Jogador::getPontuacao).reversed().thenComparing(Jogador::getNumeroTentativas));
    }

    // EX 4 - Ranking the Jogadores
    // Exibição da lista de jogadores
    public static void verLista(int tipo, Jogador jogador, ArrayList<Jogador> lista) {
        organizarLista(lista);
        if (tipo == 1) { // Exibir lista COMPLETA (tipo == 1)
            for (int i = 0; i < lista.size(); i++) {
                System.out.println((i + 1) + " - " + lista.get(i).getNome() +
                        " (" + lista.get(i).getPontuacao() + " Pts)");
            }
        } else { // Exibir TOP 10 (tipo != 1) (foi usado o 0)
            // EX 4 - Exibir top 10
            for (int i = 0; i < 10 && i < lista.size(); i++) {
                System.out.println((i + 1) + " - " + lista.get(i).getNome() +
                        " (" + lista.get(i).getPontuacao() + " Pts)");
            }
        }
        if (jogador != null) { // se jogador for dado como parâmetro, exibir seu usuário após a lista
            System.out.println("\n" +
                    (lista.indexOf(jogador) + 1) + " - " + jogador.getNome() +
                    " (VOCÊ) (" + jogador.getPontuacao() + " Pts)");
        }
        System.out.println();
    }

    //EX 5 - Valide se o jogador já existe
    public static Jogador validarLogin(String nome, ArrayList<Jogador> lista) {
        for (Jogador jogador : lista) {
            if (jogador.getNome().equalsIgnoreCase(nome)) {
                return jogador;
            }
        }
        return null;
    }

    public static int nextInt(Scanner scan) {
        int input = scan.nextInt();
        scan.nextLine();
        return input;
    }

    // jogadores.stream.sorted()
}