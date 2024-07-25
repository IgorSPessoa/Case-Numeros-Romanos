import java.util.Scanner;
import java.util.InputMismatchException;

public class NumerosRomanos {

    private int numero;
    private String romano;

    // Arrays que armazenam os valores dos números inteiros e seus correspondentes
    // algarismos romanos
    private static final int[] values = { 1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1 };
    private static final String[] symbols = { "M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I" };

    // Construtor para converter um número inteiro em algarismo romano
    public NumerosRomanos(int numero) {
        this.numero = numero;
        this.romano = numeroParaRomano(numero);// chamando a função e passando valores
    }

    // Construtor para converter um algarismo romano em número inteiro
    public NumerosRomanos(String romano) {
        this.romano = romano;
        this.numero = romanoParaNumero(romano);// chamando a função e passando valores
    }

    // Método para converter um número inteiro para um algarismo romano
    private String numeroParaRomano(int num) {
        StringBuilder roman = new StringBuilder(); // Usado para construir a string do algarismo romano
        for (int i = 0; i < values.length; i++) { // Percorre todos os valores e símbolos
            while (num >= values[i]) { // Enquanto o número for maior ou igual ao valor atual
                num -= values[i];
                roman.append(symbols[i]); // Adiciona o símbolo correspondente ao resultado
            }
        }
        return roman.toString(); // Retorna o algarismo romano
    }

    // Método para converter um algarismo romano para um número inteiro
    private int romanoParaNumero(String s) {
        int sum = 0; // Armazena o resultado da conversão
        for (int i = 0; i < s.length(); i++) { // Percorre cada caractere do algarismo romano
            int valor = valorRomano(s.charAt(i)); // Obtém o valor do caractere atual
            // Verifica se o valor atual deve ser subtraído
            if (i + 1 < s.length() && valor < valorRomano(s.charAt(i + 1))) {
                sum -= valor; // Subtrai o valor atual do resultado
            } else {
                sum += valor; // Adiciona o valor atual ao resultado
            }
        }
        return sum; // Retorna o número inteiro resultante
    }

    // Método auxiliar para converter um caractere romano individual para seu valor
    // inteiro correspondente
    private int valorRomano(char romanChar) {
        switch (romanChar) {
            case 'M':
                return 1000;
            case 'D':
                return 500;
            case 'C':
                return 100;
            case 'L':
                return 50;
            case 'X':
                return 10;
            case 'V':
                return 5;
            case 'I':
                return 1;
            default:
                throw new IllegalArgumentException("Caractere romano inválido: " + romanChar);
        }
    }

    // Getters
    public int getNumero() {
        return numero;
    }

    public String getRomano() {
        return romano;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("Escolha uma opção: \n");
            System.out.println("1. Converter número inteiro para algarismo romano");
            System.out.println("2. Converter algarismo romano para número inteiro \n");
            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    try {
                        System.out.print("Digite um número inteiro: ");
                        int numero = scanner.nextInt();
                        NumerosRomanos nr = new NumerosRomanos(numero);// Passando valor inserido pelo usuario para o
                                                                       // construtor
                        System.out.println("Número " + numero + " em algarismo romano é: " + nr.getRomano());
                    } catch (InputMismatchException e) {
                        System.out.println("Entrada inválida. Insira um número inteiro.");
                    }
                    break;
                case 2:
                    try {
                        System.out.print("Digite um algarismo romano: ");
                        String romano = scanner.nextLine().toUpperCase();
                        NumerosRomanos nr = new NumerosRomanos(romano); // Passando valor inserido pelo usuario para o
                                                                        // construtor
                        System.out.println("Algarismo romano " + romano + " em número é: " + nr.getNumero());
                    } catch (IllegalArgumentException e) {
                        System.out.println("Entrada inválida. Insira um algarismo romano válido.");
                    }
                    break;
                default:
                    System.out.println("Opção inválida.");
                    break;
            }
        } catch (InputMismatchException e) {
            System.out.println("Opção inválida. Escolha 1 ou 2.");
        } finally {
            scanner.close();
        }
    }
}
