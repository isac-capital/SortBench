package sortbench.algorithms;

import sortbench.model.Resultado;

public class MergeSort implements SortAlgorithm {
    private long comparacoes;
    private long trocas;

    @Override
    public String getNome() {
        return "Merge Sort";
    }

    @Override
    public Resultado executar(int[] vetorOriginal, String cenario) {
        int[] vetor = vetorOriginal.clone();
        comparacoes = 0;
        trocas = 0;

        long inicio = System.nanoTime();
        mergeSort(vetor, 0, vetor.length - 1);
        long fim = System.nanoTime();

        double tempoMs = (fim - inicio) / 1_000_000.0;
        return new Resultado(getNome(), cenario, vetor.length, tempoMs, comparacoes, trocas);
    }

    private void mergeSort(int[] arr, int esq, int dir) {
        if (esq < dir) {
            int meio = esq + (dir - esq) / 2;
            mergeSort(arr, esq, meio);
            mergeSort(arr, meio + 1, dir);
            merge(arr, esq, meio, dir);
        }
    }

    private void merge(int[] arr, int esq, int meio, int dir) {
        int n1 = meio - esq + 1;
        int n2 = dir - meio;

        int[] esquerda = new int[n1];
        int[] direita = new int[n2];

        for (int i = 0; i < n1; i++) esquerda[i] = arr[esq + i];
        for (int j = 0; j < n2; j++) direita[j] = arr[meio + 1 + j];

        int i = 0, j = 0, k = esq;
        while (i < n1 && j < n2) {
            comparacoes++;
            if (esquerda[i] <= direita[j]) {
                arr[k++] = esquerda[i++];
            } else {
                arr[k++] = direita[j++];
                trocas += (n1 - i);
            }
        }

        while (i < n1) arr[k++] = esquerda[i++];
        while (j < n2) arr[k++] = direita[j++];
    }
}