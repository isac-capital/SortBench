package sortbench.main;

import sortbench.model.Resultado;
import sortbench.util.ExecutorBenchmark;
import sortbench.util.LeitorArquivo;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Main {

    static final String CABECALHO_CSV =
            "algoritmo,cenario,tamanho,mediana_ms,media_ms,desvio_padrao_ms,comparacoes,trocas,copias,ordenado";

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        boolean continuar = true;
        while (continuar) {
            System.out.println();
            System.out.println("===== SortBench (console) =====");
            System.out.println("1 - Testar com dados gerados (1.000 a 100.000 elementos)");
            System.out.println("2 - Testar com dados gerados (completo: 1.000 a 1.000.000 elementos)");
            System.out.println("3 - Testar com um arquivo CSV/TXT");
            System.out.println("0 - Sair");
            System.out.print("Opção: ");
            String opcao = teclado.nextLine().trim();

            if (opcao.equals("1")) {
                testarGerados(new int[]{1000, 10000, 100000});
            } else if (opcao.equals("2")) {
                testarGerados(new int[]{1000, 10000, 100000, 500000, 1000000});
            } else if (opcao.equals("3")) {
                testarArquivo(teclado);
            } else if (opcao.equals("0")) {
                continuar = false;
            } else {
                System.out.println("Opção inválida.");
            }
        }
        System.out.println("Até mais!");
    }

    static void testarGerados(int[] tamanhos) {
        try {
            PrintWriter csv = new PrintWriter(new FileWriter("resultados.csv"));
            csv.println(CABECALHO_CSV);
            System.out.println("Aquecendo a JVM e executando os testes...");
            imprimirCabecalhoTabela();
            for (int i = 0; i < tamanhos.length; i++) {
                List<Resultado> resultados = ExecutorBenchmark.executarTestesCompletos(tamanhos[i]);
                for (int r = 0; r < resultados.size(); r++) {
                    mostrar(resultados.get(r), csv);
                }
            }
            csv.close();
            System.out.println();
            System.out.println("Resultados salvos em resultados.csv");
            System.out.println("(O InsertionSort não é executado acima de " + ExecutorBenchmark.LIMITE_INSERTION + " elementos.)");
        } catch (IOException e) {
            System.out.println("Erro ao gravar o arquivo: " + e.getMessage());
        }
    }

    static void testarArquivo(Scanner teclado) {
        try {
            System.out.print("Caminho do arquivo (ex.: data/telemetria_exemplo.csv): ");
            String caminho = teclado.nextLine().trim();
            System.out.print("Número da coluna a ordenar (a primeira coluna é a 0): ");
            int coluna = Integer.parseInt(teclado.nextLine().trim());
            System.out.print("Casas decimais a manter (0 = arredonda para inteiro): ");
            int casas = Integer.parseInt(teclado.nextLine().trim());

            int[] dados = LeitorArquivo.lerColuna(caminho, coluna, casas);
            System.out.println("Valores lidos: " + dados.length + " | linhas ignoradas: " + LeitorArquivo.linhasIgnoradas
                    + " (inclui o cabeçalho, se houver)");
            if (dados.length == 0) {
                System.out.println("Nenhum valor numérico encontrado nessa coluna. Confira o número da coluna.");
                return;
            }

            PrintWriter csv = new PrintWriter(new FileWriter("resultados_arquivo.csv"));
            csv.println(CABECALHO_CSV);
            imprimirCabecalhoTabela();
            List<Resultado> resultados = ExecutorBenchmark.executarSobreVetor(dados, "Arquivo");
            for (int r = 0; r < resultados.size(); r++) {
                mostrar(resultados.get(r), csv);
            }
            csv.close();
            System.out.println();
            System.out.println("Resultados salvos em resultados_arquivo.csv");
        } catch (NumberFormatException e) {
            System.out.println("Digite apenas números inteiros para a coluna e as casas decimais.");
        } catch (IOException e) {
            System.out.println("Não foi possível ler o arquivo: " + e.getMessage());
        }
    }

    static void mostrar(Resultado r, PrintWriter csv) {
        String situacao = r.isOrdenado() ? "OK" : "ERRO";
        System.out.printf("%-14s %-28s %9d %12.3f %12.3f %10.3f %14d %12d %12d  %s%n",
                r.getAlgoritmo(), r.getCenario(), r.getTamanhoVetor(), r.getTempoExecucaoMs(), r.getMediaMs(),
                r.getDesvioPadraoMs(), r.getComparacoes(), r.getTrocas(), r.getCopias(), situacao);
        csv.println(String.format(Locale.US, "%s,%s,%d,%.3f,%.3f,%.3f,%d,%d,%d,%s",
                r.getAlgoritmo(), r.getCenario(), r.getTamanhoVetor(), r.getTempoExecucaoMs(), r.getMediaMs(),
                r.getDesvioPadraoMs(), r.getComparacoes(), r.getTrocas(), r.getCopias(), situacao));
    }

    static void imprimirCabecalhoTabela() {
        System.out.printf("%-14s %-28s %9s %12s %12s %10s %14s %12s %12s  %s%n",
                "Algoritmo", "Cenário", "Tamanho", "Mediana(ms)", "Média(ms)", "Desvio(ms)",
                "Comparações", "Trocas", "Cópias", "Ordenado");
    }
}
