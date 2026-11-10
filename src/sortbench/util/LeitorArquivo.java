package sortbench.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class LeitorArquivo {
    public static int linhasIgnoradas = 0;

    public static int[] lerColuna(String caminho, int coluna, int casasDecimais) throws IOException {
        int fator = 1;
        for (int i = 0; i < casasDecimais; i++) {
            fator = fator * 10;
        }

        int[] valores = new int[1000];
        int quantidade = 0;
        linhasIgnoradas = 0;

        BufferedReader leitor = new BufferedReader(new FileReader(caminho));
        String linha = leitor.readLine();
        while (linha != null) {
            if (!linha.trim().isEmpty()) {
                String[] campos = linha.split(separador(linha), -1);
                if (coluna >= campos.length) {
                    linhasIgnoradas++;
                } else {
                    String texto = campos[coluna].trim().replace(",", ".");
                    try {
                        double numero = Double.parseDouble(texto) * fator;
                        if (Double.isNaN(numero) || Math.abs(numero) > Integer.MAX_VALUE) {
                            linhasIgnoradas++;
                        } else {
                            if (quantidade == valores.length) {
                                valores = Arrays.copyOf(valores, quantidade * 2);
                            }
                            valores[quantidade] = (int) Math.round(numero);
                            quantidade++;
                        }
                    } catch (NumberFormatException e) {
                        linhasIgnoradas++;
                    }
                }
            }
            linha = leitor.readLine();
        }
        leitor.close();
        return Arrays.copyOf(valores, quantidade);
    }

    private static String separador(String linha) {
        if (linha.contains(";")) {
            return ";";
        }
        if (linha.contains("\t")) {
            return "\t";
        }
        return ",";
    }
}
