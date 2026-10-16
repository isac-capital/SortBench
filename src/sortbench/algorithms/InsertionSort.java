
package sortbench.algorithms;

import sortbench.model.Resultado;

public class InsertionSort implements SortAlgorithm {
    
    @Override
    public String getNome(){
        return "Insertion Sort";
    }
    @Override
    public Resultado executar(int[]vetorOriginal, String cenario){
        //clona o vetor para preservar a massa de dados intacta
        int[]vetor = vetorOriginal.clone();
        long comparacoes = 0;
        long trocas = 0;
        
        // Marca o tempo inicial de execução em nanossegundos para maior precisão
        long tempoInicio = System.nanoTime();
        
        //Logica de ordenação por inserção
        for(int i = 1; i < vetor.length;i++){
            int chave = vetor[i];
            int j = i -1;
            
        //Compara os elementos e contabiliza as operações
        while(j >= 0 ){
            comparacoes++;
            if(vetor[j] > chave){
                vetor[j+1] = vetor[j]; //desloca o elemento
                trocas++;
                j--;} else{ break;}}
        vetor[j+1] = chave;
        trocas++; //Contabiliza a atribuição da chave
        }
        //Calcula o tempo total de execução em milisegundos
        long tempoFim = System.nanoTime();
        double tempoExecucaoMs = (tempoFim - tempoInicio)/ 1_000_000.0;
         
        // Retorna o pacote de métricas preenchido
        return new Resultado(getNome(), vetor.length, cenario, tempoExecucaoMs, comparacoes, trocas);
        
    }
    
}
