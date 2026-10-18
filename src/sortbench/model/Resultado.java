package sortbench.model;

public class Resultado {
    private String algoritmo;
    private String cenario;
    private int tamanhoVetor;
    private double tempoExecucaoMs;
    private long comparacoes;
    private long trocas;

    public Resultado(String algoritmo, String cenario, int tamanhoVetor, double tempoExecucaoMs, long comparacoes, long trocas) {
        this.algoritmo = algoritmo;
        this.cenario = cenario;
        this.tamanhoVetor = tamanhoVetor;
        this.tempoExecucaoMs = tempoExecucaoMs;
        this.comparacoes = comparacoes;
        this.trocas = trocas;
    }

    public String getAlgoritmo() {
        return algoritmo;
    }

    public String getCenario() {
        return cenario;
    }

    public int getTamanhoVetor() {
        return tamanhoVetor;
    }

    public double getTempoExecucaoMs() {
        return tempoExecucaoMs;
    }

    public long getComparacoes() {
        return comparacoes;
    }

    public long getTrocas() {
        return trocas;
    }
}