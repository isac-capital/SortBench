package sortbench.algorithms;

import sortbench.model.Resultado;
import java.util.Arrays;

/**
 * Adaptador para o algoritmo TimSort (nativo do Java via Arrays.sort).
 */
public class TimSortAdapter implements SortAlgorithm {

    @Override
    public String getNome() {
        return "TimSort (Híbrido)";
    }

    @Override
    public Resultado executar(int[] vetorOriginal, String cenario) {
        int[] vetor = vetorOriginal.clone();
        
        long inicio = System.nanoTime();
        Arrays.sort(vetor); // Utiliza o TimSort nativo do Java
        long fim = System.nanoTime();

        double tempoMs = (fim - inicio) / 1_000_000.0;
        // O TimSort nativo não expõe contadores internos de comparações/trocas
        return new Resultado(getNome(), cenario, vetor.length, tempoMs, -1, -1);
    }
}