
package sortbench.algorithms;

import sortbench.model.Resultado;

public interface SortAlgorithm {
    // Define o nome que identifica o algoritmo
    
    String getNome();
    
    // Executa a ordenação e retorna as métricas coletadas
    Resultado executar(int[]vetorOriginal, String cenario);
    
}
