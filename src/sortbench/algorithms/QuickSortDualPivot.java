package sortbench.algorithms;

import sortbench.model.Resultado;

public class QuickSortDualPivot implements SortAlgorithm {
    private long comparacoes;
    private long trocas;

    @Override
    public String getNome() {
        return "QuickSort Dual-Pivot";
    }

    @Override
    public Resultado executar(int[] vetorOriginal, String cenario) {
        int[] vetor = vetorOriginal.clone();
        comparacoes = 0;
        trocas = 0;

        long tempoInicio = System.nanoTime();

        dualPivotQuicksort(vetor, 0, vetor.length - 1);

        long tempoFim = System.nanoTime();
        double tempoExecucaoMs = (tempoFim - tempoInicio) / 1_000_000.0;

        return new Resultado(getNome(), cenario, vetor.length, tempoExecucaoMs, comparacoes, trocas);
    }

    private void dualPivotQuicksort(int[] a, int low, int high) {
        if (low < high) {
            comparacoes++;
            if (a[low] > a[high]) {
                trocar(a, low, high);
            }

            int p1 = a[low];
            int p2 = a[high];

            int l = low + 1;
            int g = high - 1;
            int k = l;

            while (k <= g) {
                comparacoes++;
                if (a[k] < p1) {
                    trocar(a, k, l);
                    l++;
                } else {
                    comparacoes++;
                    if (a[k] >= p2) {
                        while (a[g] > p2 && k < g) {
                            comparacoes++;
                            g--;
                        }
                        trocar(a, k, g);
                        g--;
                        comparacoes++;
                        if (a[k] < p1) {
                            trocar(a, k, l);
                            l++;
                        }
                    }
                }
                k++;
            }
            l--;
            g++;

            trocar(a, low, l);
            trocar(a, high, g);

            dualPivotQuicksort(a, low, l - 1);
            dualPivotQuicksort(a, l + 1, g - 1);
            dualPivotQuicksort(a, g + 1, high);
        }
    }

    private void trocar(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
        trocas++;
    }
}