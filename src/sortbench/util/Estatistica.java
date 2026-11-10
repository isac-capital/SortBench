package sortbench.util;

import java.util.Arrays;

public class Estatistica {
    public static double media(double[] valores) {
        double soma = 0;
        for (int i = 0; i < valores.length; i++) {
            soma = soma + valores[i];
        }
        return soma / valores.length;
    }

    public static double mediana(double[] valores) {
        double[] copia = Arrays.copyOf(valores, valores.length);
        Arrays.sort(copia);
        int meio = copia.length / 2;
        if (copia.length % 2 == 1) {
            return copia[meio];
        }
        return (copia[meio - 1] + copia[meio]) / 2;
    }

    public static double desvioPadrao(double[] valores) {
        if (valores.length < 2) {
            return 0;
        }
        double media = media(valores);
        double soma = 0;
        for (int i = 0; i < valores.length; i++) {
            soma = soma + (valores[i] - media) * (valores[i] - media);
        }
        return Math.sqrt(soma / (valores.length - 1));
    }
}
