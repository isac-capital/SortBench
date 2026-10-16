package sortbench.util;

import sortbench.algorithms.SortAlgorithm;
import sortbench.model.Resultado;
import java.util.ArrayList;
import java.util.List;

public class ExecutorBenchmark {

    // Executa os testes automatizados para os algoritmos e tamanhos informados
    public static List<Resultado> executarTestes(List<SortAlgorithm> algoritmos, int[] tamanhos) {
        List<Resultado> listaResultados = new ArrayList<>();

        // Percorre cada algoritmo
        for (SortAlgorithm algoritmo : algoritmos) {
            
            // Testa o algoritmo para cada tamanho de vetor
            for (int tamanho : tamanhos) {
                
                // Cenário 1: Vetor com números aleatórios
                int[] vetorAleatorio = GeradorDeVetores.gerarAleatorio(tamanho);
                listaResultados.add(algoritmo.executar(vetorAleatorio, "Aleatório"));

                // Cenário 2: Vetor já ordenado
                int[] vetorOrdenado = GeradorDeVetores.gerarOrdenado(tamanho);
                listaResultados.add(algoritmo.executar(vetorOrdenado, "Ordenado"));

                // Cenário 3: Vetor em ordem decrescente
                int[] vetorDecrescente = GeradorDeVetores.gerarDecrescente(tamanho);
                listaResultados.add(algoritmo.executar(vetorDecrescente, "Decrescente"));
            }
        }

        // Retorna a lista com todas as métricas recolhidas
        return listaResultados;
    }
}