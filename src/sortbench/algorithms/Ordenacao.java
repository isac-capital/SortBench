package sortbench.algorithms;

public class Ordenacao {
    public static long comparacoes = 0;
    public static long trocas = 0;
    public static long copias = 0;

    public static void zerarContadores() {
        comparacoes = 0;
        trocas = 0;
        copias = 0;
    }

    private static void trocar(int[] v, int i, int j) {
        if (i == j) {
            return;
        }
        int aux = v[i];
        v[i] = v[j];
        v[j] = aux;
        trocas++;
    }

    public static void insertionSort(int[] v) {
        for (int i = 1; i < v.length; i++) {
            int chave = v[i];
            int j = i - 1;
            while (j >= 0) {
                comparacoes++;
                if (v[j] > chave) {
                    v[j + 1] = v[j];
                    copias++;
                    j--;
                } else {
                    break;
                }
            }
            v[j + 1] = chave;
            copias++;
        }
    }

    public static void mergeSort(int[] v) {
        if (v.length < 2) {
            return;
        }
        int[] aux = new int[v.length];
        mergeSortRec(v, aux, 0, v.length - 1);
    }

    private static void mergeSortRec(int[] v, int[] aux, int inicio, int fim) {
        if (inicio >= fim) {
            return;
        }
        int meio = (inicio + fim) / 2;
        mergeSortRec(v, aux, inicio, meio);
        mergeSortRec(v, aux, meio + 1, fim);
        intercalar(v, aux, inicio, meio, fim);
    }

    private static void intercalar(int[] v, int[] aux, int inicio, int meio, int fim) {
        int i = inicio;
        int j = meio + 1;
        int k = inicio;
        while (i <= meio && j <= fim) {
            comparacoes++;
            if (v[i] <= v[j]) {
                aux[k] = v[i];
                i++;
            } else {
                aux[k] = v[j];
                j++;
            }
            k++;
            copias++;
        }
        while (i <= meio) {
            aux[k] = v[i];
            i++;
            k++;
            copias++;
        }
        while (j <= fim) {
            aux[k] = v[j];
            j++;
            k++;
            copias++;
        }
        for (k = inicio; k <= fim; k++) {
            v[k] = aux[k];
            copias++;
        }
    }

    public static void heapSort(int[] v) {
        int n = v.length;
        for (int i = n / 2 - 1; i >= 0; i--) {
            descer(v, i, n);
        }
        for (int fim = n - 1; fim > 0; fim--) {
            trocar(v, 0, fim);
            descer(v, 0, fim);
        }
    }

    private static void descer(int[] v, int i, int tamanho) {
        while (true) {
            int maior = i;
            int esquerdo = 2 * i + 1;
            int direito = 2 * i + 2;
            if (esquerdo < tamanho) {
                comparacoes++;
                if (v[esquerdo] > v[maior]) {
                    maior = esquerdo;
                }
            }
            if (direito < tamanho) {
                comparacoes++;
                if (v[direito] > v[maior]) {
                    maior = direito;
                }
            }
            if (maior == i) {
                return;
            }
            trocar(v, i, maior);
            i = maior;
        }
    }

    public static void quickSort(int[] v) {
        quickSortRec(v, 0, v.length - 1);
    }

    private static void quickSortRec(int[] v, int esq, int dir) {
        if (esq >= dir) {
            return;
        }

        int terco = (dir - esq) / 3;
        trocar(v, esq, esq + terco);
        trocar(v, dir, dir - terco);
        comparacoes++;
        if (v[esq] > v[dir]) {
            trocar(v, esq, dir);
        }
        int p = v[esq];
        int q = v[dir];

        int menor = esq + 1;
        int maior = dir - 1;
        int i = esq + 1;
        while (i <= maior) {
            comparacoes++;
            if (v[i] < p) {
                trocar(v, i, menor);
                menor++;
                i++;
            } else {
                comparacoes++;
                if (v[i] > q) {
                    trocar(v, i, maior);
                    maior--;
                } else {
                    i++;
                }
            }
        }

        menor--;
        maior++;
        trocar(v, esq, menor);
        trocar(v, dir, maior);

        quickSortRec(v, esq, menor - 1);
        if (p != q) {
            quickSortRec(v, menor + 1, maior - 1);
        }
        quickSortRec(v, maior + 1, dir);
    }

    public static void radixSort(int[] v) {
        if (v.length < 2) {
            return;
        }

        int menor = v[0];
        int maior = v[0];
        for (int i = 1; i < v.length; i++) {
            if (v[i] < menor) {
                menor = v[i];
            }
            if (v[i] > maior) {
                maior = v[i];
            }
        }

        long deslocamento = 0;
        if (menor < 0) {
            deslocamento = -(long) menor;
        }
        long maiorValor = (long) maior + deslocamento;

        int[] saida = new int[v.length];
        for (long casa = 1; maiorValor / casa > 0; casa = casa * 10) {
            int[] contagem = new int[10];
            for (int i = 0; i < v.length; i++) {
                contagem[digito(v[i], deslocamento, casa)]++;
            }
            for (int d = 1; d < 10; d++) {
                contagem[d] = contagem[d] + contagem[d - 1];
            }
            for (int i = v.length - 1; i >= 0; i--) {
                int d = digito(v[i], deslocamento, casa);
                contagem[d]--;
                saida[contagem[d]] = v[i];
                copias++;
            }
            for (int i = 0; i < v.length; i++) {
                v[i] = saida[i];
                copias++;
            }
        }
    }

    private static int digito(int valor, long deslocamento, long casa) {
        return (int) (((long) valor + deslocamento) / casa % 10);
    }
}
