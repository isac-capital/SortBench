package sortbench.algorithms;

import sortbench.model.Resultado;

public class InsertionSort implements SortAlgorithm {

    @Override
    public String getNome() {
        return "Insertion Sort";
    }

    @Override
    public Resultado executar(int[] vetorOriginal, String cenario) {
        int[] vetor = vetorOriginal.clone();
        long comparacoes = 0;
        long trocas = 0;

        long tempoInicio = System.nanoTime();

        for (int i = 1; i < vetor.length; i++) {
            int chave = vetor[i];
            int j = i - 1;

            while (j >= 0) {
                comparacoes++;
                if (vetor[j] > chave) {
                    vetor[j + 1] = vetor[j];
                    trocas++;
                    j--;
                } else {
                    break;
                }
            }
            vetor[j + 1] = chave;
            trocas++;
        }

        long tempoFim = System.nanoTime();
        double tempoExecucaoMs = (tempoFim - tempoInicio) / 1_000_000.0;

        return new Resultado(getNome(), cenario, vetor.length, tempoExecucaoMs, comparacoes, trocas);
    }
}