package sortbench.util;

import java.util.Random;

public class GeradorDeVetores {

    // Gera um vetor com números inteiros aleatórios
    public static int[] gerarAleatorio(int tamanho) {
        int[] vetor = new int[tamanho];
        Random random = new Random();
        for (int i = 0; i < tamanho; i++) {
            vetor[i] = random.nextInt(tamanho * 10);
        }
        return vetor;
    }

    // Gera um vetor já ordenado (crescente)
    public static int[] gerarOrdenado(int tamanho) {
        int[] vetor = new int[tamanho];
        for (int i = 0; i < tamanho; i++) {
            vetor[i] = i;
        }
        return vetor;
    }

    // Gera um vetor em ordem decrescente
    public static int[] gerarDecrescente(int tamanho) {
        int[] vetor = new int[tamanho];
        for (int i = 0; i < tamanho; i++) {
            vetor[i] = tamanho - i;
        }
        return vetor;
    }
}