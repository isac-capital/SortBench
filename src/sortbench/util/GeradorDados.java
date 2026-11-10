package sortbench.util;

import java.util.Arrays;
import java.util.Random;

public class GeradorDados {
    private static final int LIMITE = 1000000000;

    public static int[] aleatorio(int n) {
        Random sorteio = new Random(42 + n);
        int[] v = new int[n];
        for (int i = 0; i < n; i++) {
            v[i] = sorteio.nextInt(LIMITE);
        }
        return v;
    }

    public static int[] crescente(int n) {
        int[] v = aleatorio(n);
        Arrays.sort(v);
        return v;
    }

    public static int[] decrescente(int n) {
        int[] v = crescente(n);
        for (int i = 0, j = n - 1; i < j; i++, j--) {
            int aux = v[i];
            v[i] = v[j];
            v[j] = aux;
        }
        return v;
    }

    public static int[] quaseOrdenado(int n) {
        int[] v = crescente(n);
        if (n < 2) {
            return v;
        }
        Random sorteio = new Random(99 + n);
        int trocas = n / 20;
        for (int k = 0; k < trocas; k++) {
            int i = sorteio.nextInt(n);
            int j = sorteio.nextInt(n);
            int aux = v[i];
            v[i] = v[j];
            v[j] = aux;
        }
        return v;
    }

    public static int[] duplicados(int n) {
        Random sorteio = new Random(7 + n);
        int[] v = new int[n];
        int diferentes = n - (int) Math.round(n * 0.40);
        for (int i = 0; i < diferentes; i++) {
            v[i] = sorteio.nextInt(LIMITE);
        }
        for (int i = diferentes; i < n; i++) {
            v[i] = v[sorteio.nextInt(diferentes)];
        }
        for (int i = n - 1; i > 0; i--) {
            int j = sorteio.nextInt(i + 1);
            int aux = v[i];
            v[i] = v[j];
            v[j] = aux;
        }
        return v;
    }
}
