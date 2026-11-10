package sortbench.util;

import sortbench.algorithms.Ordenacao;
import sortbench.model.Resultado;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ExecutorBenchmark {

    public interface Acompanhamento {
        void progresso(int concluidos, int total, String descricao);

        boolean cancelado();
    }

    public static final String[] ALGORITMOS = {"QuickSort", "MergeSort", "HeapSort", "RadixSort", "InsertionSort"};
    public static final String[] CENARIOS = {"Aleatório", "Ordenado (Melhor Caso)", "Quase Ordenado (~5% trocas)",
        "Invertido (Pior Caso)", "Com Duplicados (~40%)"};
    public static final int EXECUCOES = 6;
    public static final int LIMITE_INSERTION = 100000;

    private static final Acompanhamento SEM_ACOMPANHAMENTO = new Acompanhamento() {
        @Override
        public void progresso(int concluidos, int total, String descricao) {
        }

        @Override
        public boolean cancelado() {
            return false;
        }
    };

    private static boolean aquecido = false;

    public static int contarTestes(int tamanho) {
        int algoritmos = ALGORITMOS.length;
        if (tamanho > LIMITE_INSERTION) {
            algoritmos--;
        }
        return CENARIOS.length * algoritmos;
    }

    public static synchronized List<Resultado> executarTestesCompletos(int tamanho) {
        return executarVarios(new int[]{tamanho}, SEM_ACOMPANHAMENTO);
    }

    public static synchronized List<Resultado> executarVarios(int[] tamanhos, Acompanhamento acompanhamento) {
        int total = 0;
        for (int tamanho : tamanhos) {
            total += contarTestes(tamanho);
        }
        List<Resultado> resultados = new ArrayList<>();
        int[] concluidos = {0};

        acompanhamento.progresso(0, total, "Aquecendo a JVM...");
        if (!aquecerSeNecessario(acompanhamento)) {
            return resultados;
        }
        for (int tamanho : tamanhos) {
            for (int c = 0; c < CENARIOS.length; c++) {
                int[] vetor = gerar(c, tamanho);
                if (!medirTodos(CENARIOS[c], vetor, acompanhamento, resultados, concluidos, total)) {
                    return resultados;
                }
            }
        }
        return resultados;
    }

    public static synchronized List<Resultado> executarSobreVetor(int[] vetor, String cenario) {
        return executarSobreVetor(vetor, cenario, SEM_ACOMPANHAMENTO);
    }

    public static synchronized List<Resultado> executarSobreVetor(int[] vetor, String cenario, Acompanhamento acompanhamento) {
        int total = ALGORITMOS.length;
        if (vetor.length > LIMITE_INSERTION) {
            total--;
        }
        List<Resultado> resultados = new ArrayList<>();
        int[] concluidos = {0};

        acompanhamento.progresso(0, total, "Aquecendo a JVM...");
        if (!aquecerSeNecessario(acompanhamento)) {
            return resultados;
        }
        medirTodos(cenario, vetor, acompanhamento, resultados, concluidos, total);
        return resultados;
    }

    public static int[] gerar(int cenario, int n) {
        if (cenario == 0) {
            return GeradorDados.aleatorio(n);
        } else if (cenario == 1) {
            return GeradorDados.crescente(n);
        } else if (cenario == 2) {
            return GeradorDados.quaseOrdenado(n);
        } else if (cenario == 3) {
            return GeradorDados.decrescente(n);
        } else {
            return GeradorDados.duplicados(n);
        }
    }

    private static boolean aquecerSeNecessario(Acompanhamento acompanhamento) {
        if (aquecido) {
            return true;
        }
        for (int vez = 0; vez < 5; vez++) {
            for (int i = 0; i < ALGORITMOS.length; i++) {
                int tamanho = 30000;
                if (ALGORITMOS[i].equals("InsertionSort")) {
                    tamanho = 3000;
                }
                for (int c = 0; c < CENARIOS.length; c++) {
                    if (acompanhamento.cancelado()) {
                        return false;
                    }
                    ordenar(ALGORITMOS[i], gerar(c, tamanho));
                }
            }
        }
        aquecido = true;
        return true;
    }

    private static boolean medirTodos(String cenario, int[] original, Acompanhamento acompanhamento,
                                      List<Resultado> resultados, int[] concluidos, int total) {
        for (int i = 0; i < ALGORITMOS.length; i++) {
            if (ALGORITMOS[i].equals("InsertionSort") && original.length > LIMITE_INSERTION) {
                continue;
            }
            if (acompanhamento.cancelado()) {
                return false;
            }
            String descricao = ALGORITMOS[i] + " - " + cenario + " (N = " + original.length + ")";
            acompanhamento.progresso(concluidos[0], total, descricao);
            Resultado resultado = medir(ALGORITMOS[i], cenario, original, acompanhamento);
            if (resultado == null) {
                return false;
            }
            resultados.add(resultado);
            concluidos[0]++;
            acompanhamento.progresso(concluidos[0], total, descricao);
        }
        return true;
    }

    private static Resultado medir(String algoritmo, String cenario, int[] original, Acompanhamento acompanhamento) {
        int execucoes = EXECUCOES;
        double[] tempos = new double[EXECUCOES];
        int medidas = 0;
        boolean ordenado = true;
        long comparacoes = 0;
        long trocas = 0;
        long copias = 0;

        for (int e = 0; e < execucoes; e++) {
            if (acompanhamento.cancelado()) {
                return null;
            }
            int[] v = Arrays.copyOf(original, original.length);
            Ordenacao.zerarContadores();

            long inicio = System.nanoTime();
            ordenar(algoritmo, v);
            long fim = System.nanoTime();

            double milissegundos = (fim - inicio) / 1000000.0;
            if (!estaOrdenado(v)) {
                ordenado = false;
            }

            if (e == 0) {
                comparacoes = Ordenacao.comparacoes;
                trocas = Ordenacao.trocas;
                copias = Ordenacao.copias;
                if (milissegundos > 1000) {
                    execucoes = 3;
                }
            } else {
                tempos[medidas] = milissegundos;
                medidas++;
            }
        }

        double[] medidos = Arrays.copyOf(tempos, medidas);
        return new Resultado(algoritmo, cenario, original.length,
                Estatistica.mediana(medidos), Estatistica.media(medidos), Estatistica.desvioPadrao(medidos),
                comparacoes, trocas, copias, ordenado);
    }

    private static void ordenar(String algoritmo, int[] v) {
        if (algoritmo.equals("QuickSort")) {
            Ordenacao.quickSort(v);
        } else if (algoritmo.equals("MergeSort")) {
            Ordenacao.mergeSort(v);
        } else if (algoritmo.equals("HeapSort")) {
            Ordenacao.heapSort(v);
        } else if (algoritmo.equals("RadixSort")) {
            Ordenacao.radixSort(v);
        } else {
            Ordenacao.insertionSort(v);
        }
    }

    private static boolean estaOrdenado(int[] v) {
        for (int i = 1; i < v.length; i++) {
            if (v[i - 1] > v[i]) {
                return false;
            }
        }
        return true;
    }
}
