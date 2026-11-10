package sortbench.model;

public class Resultado {
    private String algoritmo;
    private String cenario;
    private int tamanhoVetor;
    private double tempoExecucaoMs;
    private double mediaMs;
    private double desvioPadraoMs;
    private long comparacoes;
    private long trocas;
    private long copias;
    private boolean ordenado;

    public Resultado(String algoritmo, String cenario, int tamanhoVetor, double tempoExecucaoMs, long comparacoes, long trocas) {
        this(algoritmo, cenario, tamanhoVetor, tempoExecucaoMs, tempoExecucaoMs, 0, comparacoes, trocas, 0, true);
    }

    public Resultado(String algoritmo, String cenario, int tamanhoVetor, double medianaMs, double mediaMs,
                     double desvioPadraoMs, long comparacoes, long trocas, long copias, boolean ordenado) {
        this.algoritmo = algoritmo;
        this.cenario = cenario;
        this.tamanhoVetor = tamanhoVetor;
        this.tempoExecucaoMs = medianaMs;
        this.mediaMs = mediaMs;
        this.desvioPadraoMs = desvioPadraoMs;
        this.comparacoes = comparacoes;
        this.trocas = trocas;
        this.copias = copias;
        this.ordenado = ordenado;
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

    public double getMediaMs() {
        return mediaMs;
    }

    public double getDesvioPadraoMs() {
        return desvioPadraoMs;
    }

    public long getComparacoes() {
        return comparacoes;
    }

    public long getTrocas() {
        return trocas;
    }

    public long getCopias() {
        return copias;
    }

    public boolean isOrdenado() {
        return ordenado;
    }
}
