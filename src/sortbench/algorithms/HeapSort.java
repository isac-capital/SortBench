package sortbench.algorithms;

import sortbench.model.Resultado;

/**
 * Algoritmo HeapSort com contagem de comparações e trocas.
 */
public class HeapSort implements SortAlgorithm {
    private long comparacoes;
    private long trocas;

    @Override
    public String getNome() {
        return "Heap Sort";
    }

    @Override
    public Resultado executar(int[] vetorOriginal, String cenario) {
        int[] vetor = vetorOriginal.clone();
        comparacoes = 0;
        trocas = 0;

        long inicio = System.nanoTime();
        heapSort(vetor);
        long fim = System.nanoTime();

        double tempoMs = (fim - inicio) / 1_000_000.0;
        return new Resultado(getNome(), cenario, vetor.length, tempoMs, comparacoes, trocas);
    }

    private void heapSort(int[] arr) {
        int n = arr.length;

        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }

        for (int i = n - 1; i > 0; i--) {
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
            trocas++;

            heapify(arr, i, 0);
        }
    }

    private void heapify(int[] arr, int n, int i) {
        int maior = i;
        int esquerda = 2 * i + 1;
        int direita = 2 * i + 2;

        if (esquerda < n) {
            comparacoes++;
            if (arr[esquerda] > arr[maior]) {
                maior = esquerda;
            }
        }

        if (direita < n) {
            comparacoes++;
            if (arr[direita] > arr[maior]) {
                maior = direita;
            }
        }

        if (maior != i) {
            int swap = arr[i];
            arr[i] = arr[maior];
            arr[maior] = swap;
            trocas++;

            heapify(arr, n, maior);
        }
    }
}