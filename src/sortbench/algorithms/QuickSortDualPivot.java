
package sortbench.algorithms;

import sortbench.model.Resultado;

public class QuickSortDualPivot implements SortAlgorithm {
    
   //contadores globais de metricas para a recursão
    private long comparacoes;
    private long trocas;
    
    @Override
    public String getNome(){
    return "QuickSort Dual-Pivot";}

    @Override
    public Resultado executar(int[]vetorOriginal, String cenario){
        //clona vetor para preservar massa de dados original
        int[]vetor = vetorOriginal.clone();
        comparacoes = 0;
        trocas = 0;
        // Marca o tempo inicial de execução em nanossegundos
        long tempoInicio = System.nanoTime();

        // Inicia a ordenação recursiva com dois pivots
        dualPivotQuicksort(vetor, 0, vetor.length - 1);

        // Calcula o tempo total em milissegundos
        long tempoFim = System.nanoTime();
        double tempoExecucaoMs = (tempoFim - tempoInicio) / 1_000_000.0;

        // Retorna o pacote com as métricas computadas
        return new Resultado(getNome(), vetor.length, cenario, tempoExecucaoMs, comparacoes, trocas);
    }

    // Método recursivo do Dual-Pivot Quicksort
    private void dualPivotQuicksort(int[] a, int low, int high) {
        if (low < high) {
            
            // Garante que o primeiro pivot é menor ou igual ao último
            comparacoes++;
            if (a[low] > a[high]) {
                trocar(a, low, high);
            }

            // Pivots: p1 é o pivot esquerdo, p2 é o pivot direito
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
                        comparacoes++;
                        if (a[g] < p1) {
                            trocar(a, g, l);
                            l++;
                        } else {
                            trocar(a, g, k);
                        }g--; } }k++;
            }

            l--;
            g++;

            trocar(a, low, l);
            trocar(a, high, g);

            // Chamadas recursivas para as três partições
            dualPivotQuicksort(a, low, l - 1);
            dualPivotQuicksort(a, l + 1, g - 1);
            dualPivotQuicksort(a, g + 1, high);
        }
    }

    // Método auxiliar para trocar elementos de posição e contabilizar a troca
    private void trocar(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
        trocas++;
    }
}