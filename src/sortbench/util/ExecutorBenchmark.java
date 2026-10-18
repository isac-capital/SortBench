package sortbench.util;

import sortbench.algorithms.*;
import sortbench.model.Resultado;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe responsável por orquestrar a execução dos benchmarks de ordenação.
 */
public class ExecutorBenchmark {

    /**
     * Executa a bateria completa de testes exigidos pela APS da UNIP.
     */
    public static List<Resultado> executarTestesCompletos(int tamanho) {
        List<SortAlgorithm> algoritmos = new ArrayList<>();
        algoritmos.add(new InsertionSort());
        algoritmos.add(new MergeSort());
        algoritmos.add(new HeapSort());
        algoritmos.add(new QuickSortDualPivot());
        algoritmos.add(new TimSortAdapter());

        List<Resultado> resultados = new ArrayList<>();

        // Gerar os 4 cenários experimentais oficiais exigidos pela APS
        int[] aleatorio = GeradorDeVetores.gerarAleatorio(tamanho);
        int[] crescente = GeradorDeVetores.gerarCrescente(tamanho);
        int[] decrescente = GeradorDeVetores.gerarDecrescente(tamanho);
        int[] duplicados = GeradorDeVetores.gerarComDuplicados(tamanho);

        for (SortAlgorithm alg : algoritmos) {
            resultados.add(alg.executar(aleatorio, "Aleatório"));
            resultados.add(alg.executar(crescente, "Ordenado (Melhor Caso)"));
            resultados.add(alg.executar(decrescente, "Invertido (Pior Caso)"));
            resultados.add(alg.executar(duplicados, "Com Duplicados (~40%)"));
        }

        return resultados;
    }
}