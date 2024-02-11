//AVISO: Gosto de misturar inglês e português
// e acabei fazendo um pouco disso... XD

// NÃO É PROPOSTA DO EXERCÍCIO! REUTILIZEI UM JOGO JÁ FEITO POR MIM!

package semana5;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.text.Collator;

public class JogoForca {
    // NÃO É PROPOSTA DO EXERCÍCIO! REUTILIZEI UM JOGO JÁ FEITO POR MIM!
    private static float melhorPontuacao = 0;
    public static void jogar(Jogador jogador) {
        Scanner scanner = new Scanner(System.in);
        String[] palavras = {
                "LAB365",
                "FULLSTACK",
                "BACK END",
                "PROGRAMADOR FULLSTACK",
                "DISCORD",
                "DEV OPS",
                "FRONT END",
                "INTELIGENCIA ARTIFICIAL",
                "DATABASE",
                "MERCADO DE TRABALHO",
                "STRING",
                "ARRAYS",
                "LINGUAGEM ORIENTADA A OBJETOS",
                "TAL TECNOLOGIA NOS TRANSFORMA",
                "JAVASCRIPT",
                "PYTHON",
                "BINARIO",
                "METADADOS",
                "REALIDADE VIRTUAL",
                "TECNOLOGIA DA INFORMACAO",
                "STACK OVERFLOW",
                "PLANEJAMENTO",
                "JOGO DA FORCA",
                "PROCESSAMENTO",
                "LOREM IPSUM",
                "DOLOR SIT AMET",
                "BOOLEAN TRUE",
                "BOOLEAN FALSE",
                "PUBLIC CLASS MAIN",
                "DEBUGGER",
                "BREAKPOINT",
                "IF ELSE",
                "ARRAYLIST",
                "GOOGLE MEET",
                "INTELLIJ",
                "VISUAL STUDIO CODE",
                "SESISENAI",
                "GITHUB",
                "GITHUB",
                "TYPECASTING",
                "OPERADOR TERNARIO",
                "SWITCH",
                "BEBA AGUA",
                "TOQUE NA GRAMA",
                "VARIAVEL",
                "ESTUDANTE",
                "VETORES",
                "SETORES",
                "ESTRUTURAS DE DECISAO",
                "QUE A FORCA ESTEJA COM VOCÊ, PROGRAMADOR!",
                "SPRING",
                "FLORIPA MAIS TECH",
                "BITCOIN",
                "BLOCKCHAIN"
        };

        System.out.println("JOGO DA VELHA INICIADO!");

            System.out.println("Escolha a dificuldade: \n (f)ácil - 0.8x, (m)édio - 1x, (d)ifícil - 1.5x, (p)ersonalizado");
            byte tentativasMod = 5;
            float tentativasModReduzir = 1;
            float scoreMult = 1;
            switch (scanner.next().toLowerCase()) {
                case "f", "facil", "fácil":
                    tentativasMod = 10;
                    scoreMult = 0.8f;
                    break;
                case "m", "medio", "médio":
                    // código não necessário: tentativasMod = 5;
                    // scoreMult = 1;
                    break;
                case "d", "dificil", "difícil":
                    tentativasMod = 0;
                    scoreMult = 1.5f;
                    break;
                default:
                    System.out.println("Digite um número de -128 a 127. Maior = mais tentativas; Menor = menos tentativas." +
                            "\nFácil = 10, Médio = 5, Difícil = 0.");
                    tentativasMod = scanner.nextByte();
                    if (tentativasMod < 0) {
                        tentativasModReduzir = (float) -tentativasMod / 10 + 1;
                        tentativasMod = 0;
                        scoreMult = tentativasModReduzir + 0.5f;
                    } else {
                        scoreMult = 17f / (12 + tentativasMod);
                    }
                    break;
            }

            boolean firstTime = true;
            boolean acabou;
            byte tentativaPos = -1;
            boolean venceu = true;

            double randomNumber = Math.random() * (palavras.length - 1);
            String palavraSecreta = palavras[(int) randomNumber];
            //palavraSecreta = "ESTRUTURAS DE DECISAO"; // CUSTOM WORD
            StringBuilder palavraDisplay = new StringBuilder();
            int maximoTentativas = (int) (contarLetras(palavraSecreta) / tentativasModReduzir + tentativasMod);
            //System.out.println(maximoTentativas + " - " + contarLetras(palavraSecreta) + " -- " + tentativasMod + " --- " + tentativasModReduzir);
            char[] letrasTestadas = new char[maximoTentativas];
            System.out.println("TEMA: PROGRAMAÇÃO (Tema padrão)");
            System.out.println("AVISO: Digite Apenas Letras! Outros caracteres mostrarão automaticamente.");
            do {
                tentativaPos++;
                acabou = true;
                palavraDisplay.delete(0, 1000); //delete all

                for (byte i = 0; i < palavraSecreta.length(); i++) {
                    char caractere = palavraSecreta.charAt(i);
                    if (arrayTemLetra(letrasTestadas, caractere) || !Character.isLetter(caractere)) {
                        //if is a guessed letter or not a letter, show it
                        palavraDisplay.append(caractere);
                    } else {
                        palavraDisplay.append('_');
                        acabou = false;
                        //if any letter is not revealed, game does not end.
                    }
                }

                System.out.println("Palavras testadas:");
                for (int i = 0; i < letrasTestadas.length; i++) {
                    if (i == letrasTestadas.length - 1) {
                        System.out.println(letrasTestadas[i]);
                    } else {
                        System.out.print(letrasTestadas[i] + ", ");
                    }
                }

                System.out.println("(" + palavraSecreta.length() + ") - " + palavraDisplay);
                if (tentativaPos == maximoTentativas && !acabou) {
                    venceu = false;
                    break;
                }
                if (!acabou) {
                    System.out.print("Teste uma letra ou resposta: ");

                    if (firstTime) {
                        scanner.nextLine();
                        firstTime = false;
                    }
                    String entradaJogador = scanner.nextLine().toUpperCase();
                    System.out.println("\n");
                    if (Objects.equals(entradaJogador.toUpperCase(), palavraSecreta.toUpperCase())) {
                        break;
                    } else {
                        char letraTestada = entradaJogador.charAt(0);
                        letrasTestadas[tentativaPos] = letraTestada;
                    }
                }
            } while (!acabou);
            if (venceu) {
                System.out.println("VOCÊ VENCEU! :D");
            } else {
                System.out.println("VOCÊ PERDEU! :(");
                tentativaPos++;
            }
            float score = (float) Math.round(100 * 100 * (((float) contarLetras(palavraSecreta) + 2) / (tentativaPos + 1) - 1)) / 100 * scoreMult;
            // score is inverted function with points (0, (maximoTentativas+1) * 100) and (maximoTentativas + 1, 0)
            if (score > melhorPontuacao) {
                melhorPontuacao = score;
            }
            short maxScoreWord = (short) (100 * (contarLetras(palavraSecreta) + 1) * scoreMult);
            System.out.println("A palavra correta era: " + palavraSecreta);
            System.out.println("Sua pontuação é " + score + " (Melhor pontuação da corrida: " + melhorPontuacao + ") (Pontuação máxima da palavra: " + maxScoreWord + ")");
            jogador.adicionaPontos(Math.round(score));
    }

    public static boolean arrayTemLetra(char[] charArray, char targetLetter) {
        final Collator instance = Collator.getInstance();
        // This strategy mean it'll ignore the accents and the case
        instance.setStrength(Collator.PRIMARY);

        for (char currentLetter : charArray) {
            // Transform chars into strings
            String strCurrent = Normalizer.normalize(String.valueOf(currentLetter), Normalizer.Form.NFD);
            String strTarget = Normalizer.normalize(String.valueOf(targetLetter), Normalizer.Form.NFD);
            if (instance.compare(strCurrent, strTarget) == 0) {
                return true; // Return the matching letter
            }
        }
        return false; // Return a default value (e.g., space) or consider using a special value to indicate no match
    }

    public static byte contarLetras(String palavra) {
        List<Character> letras = new ArrayList<>();
        for (byte i = 0; i < palavra.length(); i++) {
            if (letras.lastIndexOf(palavra.charAt(i)) == -1 && Character.isLetter(palavra.charAt(i))) {
                letras.add(palavra.charAt(i));
            }
        }
        return (byte) letras.size();
    }
}
