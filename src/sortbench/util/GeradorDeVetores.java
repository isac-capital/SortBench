package sortbench.util;
import sortbench.algorithms.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Gerador de vetores sintéticos para simulação de cenários de telemetria urbana.
 */
public class GeradorDeVetores {

    /**
     * Gera vetor com elementos totalmente aleatórios.
     */
    public static int[] gerarAleatorio(int tamanho) {
        Random rand = new Random();
        int[] vetor = new int[tamanho];
        for (int i = 0; i < tamanho; i++) {
            vetor[i] = rand.nextInt(tamanho * 2);
        }
        return vetor;
    }

    /**
     * Gera vetor já ordenado em ordem crescente (Melhor Caso).
     */
    public static int[] gerarCrescente(int tamanho) {
        int[] vetor = new int[tamanho];
        for (int i = 0; i < tamanho; i++) {
            vetor[i] = i;
        }
        return vetor;
    }

    /**
     * Gera vetor ordenado em ordem decrescente / invertido (Pior Caso).
     */
    public static int[] gerarDecrescente(int tamanho) {
        int[] vetor = new int[tamanho];
        for (int i = 0; i < tamanho; i++) {
            vetor[i] = tamanho - i;
        }
        return vetor;
    }

    /**
     * Gera vetor com alta taxa de valores duplicados (~40%).
     */
    public static int[] gerarComDuplicados(int tamanho) {
        Random rand = new Random();
        int[] vetor = new int[tamanho];
        int faixaBaixa = Math.max(1, tamanho / 10);
        for (int i = 0; i < tamanho; i++) {
            vetor[i] = rand.nextInt(faixaBaixa);
        }
        return vetor;
    }

    /**
     * Carrega valores numéricos a partir de um ficheiro CSV ou TXT.
     */
    public static int[] carregarDeCSV(String caminhoArquivo) throws IOException {
        List<Integer> lista = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] valores = linha.split("[,;\\s]+");
                for (String val : valores) {
                    if (!val.trim().isEmpty()) {
                        lista.add(Integer.parseInt(val.trim()));
                    }
                }
            }
        }
        int[] vetor = new int[lista.size()];
        for (int i = 0; i < lista.size(); i++) {
            vetor[i] = lista.get(i);
        }
        return vetor;
    }
}