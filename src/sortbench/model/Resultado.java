
package sortbench.model;


public class Resultado {
    //Atributos para armazenar metricas de desempenho de aps
    private String algoritmo;
    private int tamanhoVetor;
    private String cenario;
    private double tempoExecucaoMs;
    private long comparacoes;  
    private long trocas; 
    
    //Construtor para inicializar os dados do teste
    public Resultado(String algoritmo, int tamanhoVetor, String cenario, double tempoExecucaoMs, long comparacoes, long trocas){
        this.algoritmo = algoritmo;
        this.cenario = cenario;
        this.comparacoes = comparacoes;
        this.tamanhoVetor = tamanhoVetor;
        this.tempoExecucaoMs = tempoExecucaoMs;
        this.trocas = trocas;
    }
    // Métodos de acesso (Getters) para recuperar as métricas
    public String getAlgoritmo(){ return algoritmo;}
    public int getTamanhoVetor(){return tamanhoVetor;}
    public String getCenario(){return cenario;}
    public double getTempoExecucaoMs(){return tempoExecucaoMs;}
    public long getComparacoes(){return comparacoes;}
    public long getTrocas(){return trocas;}
    
}
