# SortBench
Sistema em Java para benchmark e análise de desempenho de algoritmos de ordenação (APS - UNIP).

# 🚀 SortBench - Benchmark de Algoritmos de Ordenação (APS - UNIP)

Bem-vindos ao repositório do **SortBench**! Este projeto tem como objetivo analisar e comparar o desempenho prático de algoritmos de ordenação em Java com interface gráfica.

---

## 📌 Objetivos do Projeto
- **Algoritmos analisados:** InsertionSort e QuickSort (Dual-Pivot).
- **Métricas avaliadas:** Tempo de execução (nanossegundos), número de comparações e trocas/movimentações.
- **Cenários de teste:** Vetores com tamanhos de 1.000 a 1.000.000 de elementos nos estados **Aleatório**, **Crescente** e **Decrescente**.

---

## 📂 Estrutura de Pacotes
- `sortbench.algorithms`: Implementação dos algoritmos de ordenação.
- `sortbench.model`: Classes de dados (ex: `Resultado.java`).
- `sortbench.util`: Utilitários (ex: `GeradorDados.java`).
- `sortbench.main`: Classe principal de execução e telas Swing (`TelaPrincipal.java`).

---

## 💻 Como Executar
1. Clone este repositório para o seu computador.
2. Abra o projeto no **NetBeans**.
3. Execute o projeto a partir da classe `TelaPrincipal.java` ou `Main.java`.
