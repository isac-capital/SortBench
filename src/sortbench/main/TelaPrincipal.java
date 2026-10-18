package sortbench.main;

// Importação das classes dos algoritmos de ordenação
import sortbench.algorithms.*;

// Importação das classes de modelo de dados e utilitários do sistema
import sortbench.model.Resultado;
import sortbench.util.ExecutorBenchmark;
import sortbench.util.GeradorDeVetores;

// Importação dos componentes visuais da biblioteca Swing, bordas e manipuladores de arquivos
import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe responsável pela Interface Gráfica Principal (GUI) do SortBench.
 * Reformulada com tipografia padronizada, alto contraste e sem emojis quebrados.
 */
public class TelaPrincipal extends javax.swing.JFrame {

    // Logger para registro de mensagens do sistema
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(TelaPrincipal.class.getName());

    // Componentes dinâmicos do Dashboard de Métricas
    private JLabel lblCardMaisRapidoVal;
    private JLabel lblCardTotalTestesVal;
    private JLabel lblCardStatusVal;
    
    // Componentes da Área Visual de Drag and Drop
    private JPanel panelDropZone;
    private JLabel lblDropZoneTexto;

    /**
     * Construtor da janela principal.
     */
    public TelaPrincipal() {
        initComponents();
        estilizarComponentes();         // Aplica a estilização moderna e corrigida
        configurarDragAndDropGlobal();  // Ativa o Drag and Drop na janela
        setLocationRelativeTo(null);     // Centraliza a janela na tela
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelHeader = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        lblSubtitulo = new javax.swing.JLabel();
        
        panelBarraAcoes = new javax.swing.JPanel();
        lblTamanhos = new javax.swing.JLabel();
        txtTamanhos = new javax.swing.JTextField();
        btnExecutar = new javax.swing.JButton();
        btnCarregarCSV = new javax.swing.JButton();
        btnExportarCSV = new javax.swing.JButton();

        panelCentro = new javax.swing.JPanel();
        scrollPaneTable = new javax.swing.JScrollPane();
        tabelaResultados = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("SortBench v2.0 - Análise de Desempenho de Algoritmos");
        setMinimumSize(new java.awt.Dimension(980, 680));

        // Cabeçalho Superior da Aplicação (Azul Escuro Profissional)
        panelHeader.setBackground(new java.awt.Color(15, 23, 42));
        panelHeader.setBorder(javax.swing.BorderFactory.createEmptyBorder(16, 20, 16, 20));
        panelHeader.setLayout(new java.awt.BorderLayout(4, 4));

        lblTitulo.setFont(new java.awt.Font("Segoe UI", Font.BOLD, 20));
        lblTitulo.setForeground(new java.awt.Color(248, 250, 252));
        lblTitulo.setText("SortBench - Telemetria Urbana & Algoritmos");
        panelHeader.add(lblTitulo, java.awt.BorderLayout.NORTH);

        lblSubtitulo.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 12));
        lblSubtitulo.setForeground(new java.awt.Color(148, 163, 184));
        lblSubtitulo.setText("APS UNIP • Análise Científica e Benchmark Comparativo de Algoritmos de Ordenação");
        panelHeader.add(lblSubtitulo, java.awt.BorderLayout.SOUTH);

        getContentPane().add(panelHeader, java.awt.BorderLayout.NORTH);

        // Barra Superior de Ações e Parâmetros
        panelBarraAcoes.setBackground(new java.awt.Color(241, 245, 249));
        panelBarraAcoes.setBorder(javax.swing.BorderFactory.createEmptyBorder(12, 20, 12, 20));
        panelBarraAcoes.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 10, 5));

        lblTamanhos.setFont(new java.awt.Font("Segoe UI", Font.BOLD, 13));
        lblTamanhos.setForeground(new java.awt.Color(51, 65, 85));
        lblTamanhos.setText("Vetor(es):");
        panelBarraAcoes.add(lblTamanhos);

        txtTamanhos.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 13));
        txtTamanhos.setText("1000, 5000, 10000");
        txtTamanhos.setPreferredSize(new java.awt.Dimension(150, 32));
        panelBarraAcoes.add(txtTamanhos);

        // Botão de Execução dos Benchmarks (Azul Sóbrio)
        btnExecutar.setBackground(new java.awt.Color(30, 64, 175));
        btnExecutar.setFont(new java.awt.Font("Segoe UI", Font.BOLD, 12));
        btnExecutar.setForeground(Color.WHITE);
        btnExecutar.setText(" Executar Benchmarks");
        btnExecutar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnExecutar.setFocusPainted(false);
        btnExecutar.setPreferredSize(new java.awt.Dimension(175, 32));
        btnExecutar.addActionListener(evt -> btnExecutarActionPerformed(evt));
        panelBarraAcoes.add(btnExecutar);

        // Botão para Abrir Arquivo CSV (Verde Sóbrio)
        btnCarregarCSV.setBackground(new java.awt.Color(21, 128, 61));
        btnCarregarCSV.setFont(new java.awt.Font("Segoe UI", Font.BOLD, 12));
        btnCarregarCSV.setForeground(Color.WHITE);
        btnCarregarCSV.setText(" Carregar CSV");
        btnCarregarCSV.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCarregarCSV.setFocusPainted(false);
        btnCarregarCSV.setPreferredSize(new java.awt.Dimension(135, 32));
        btnCarregarCSV.addActionListener(evt -> btnCarregarCSVActionPerformed(evt));
        panelBarraAcoes.add(btnCarregarCSV);

        // Botão para Exportar Relatório (Cinza Grafite)
        btnExportarCSV.setBackground(new java.awt.Color(51, 65, 85));
        btnExportarCSV.setFont(new java.awt.Font("Segoe UI", Font.BOLD, 12));
        btnExportarCSV.setForeground(Color.WHITE);
        btnExportarCSV.setText(" Exportar CSV");
        btnExportarCSV.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnExportarCSV.setFocusPainted(false);
        btnExportarCSV.setPreferredSize(new java.awt.Dimension(135, 32));
        btnExportarCSV.addActionListener(evt -> btnExportarCSVActionPerformed(evt));
        panelBarraAcoes.add(btnExportarCSV);

        // Configuração da Tabela
        tabelaResultados.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 13));
        tabelaResultados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {},
            new String [] {
                "Algoritmo", "Cenário de Teste", "Tamanho (N)", "Tempo (ms)", "Comparações", "Trocas"
            }
        ) {
            boolean[] canEdit = new boolean [] { false, false, false, false, false, false };
            public boolean isCellEditable(int rowIndex, int columnIndex) { return canEdit [columnIndex]; }
        });
        tabelaResultados.setRowHeight(28);
        scrollPaneTable.setViewportView(tabelaResultados);

        panelCentro.setLayout(new java.awt.BorderLayout(0, 0));
        panelCentro.add(panelBarraAcoes, java.awt.BorderLayout.NORTH);
        panelCentro.add(scrollPaneTable, java.awt.BorderLayout.CENTER);

        getContentPane().add(panelCentro, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Aplica o design visual moderno nos componentes com alto contraste.
     */
    private void estilizarComponentes() {
        // Estilização do Cabeçalho da Tabela (Texto Branco em Fundo Escuro)
        JTableHeader header = tabelaResultados.getTableHeader();
        header.setOpaque(false);
        header.setFont(new Font("Segoe UI", Font.BOLD, 13));
        header.setBackground(new Color(30, 41, 59));
        header.setForeground(Color.WHITE);
        header.setPreferredSize(new Dimension(header.getPreferredSize().width, 34));

        // Renderização customizada para garantir legibilidade dos títulos das colunas
        ((DefaultTableCellRenderer) header.getDefaultRenderer()).setHorizontalAlignment(JLabel.LEFT);

        // Alternância sutil de cores nas linhas da tabela (Zebra Striping)
        tabelaResultados.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (!isSelected) {
                    c.setBackground(row % 2 == 0 ? Color.WHITE : new Color(241, 245, 249));
                    c.setForeground(new Color(15, 23, 42));
                } else {
                    c.setBackground(new Color(191, 219, 254));
                    c.setForeground(Color.BLACK);
                }
                setBorder(noFocusBorder);
                return c;
            }
        });

        // Painel do Dashboard com Cards
        JPanel panelCards = new JPanel(new GridLayout(1, 3, 12, 0));
        panelCards.setBackground(new Color(241, 245, 249));
        panelCards.setBorder(new EmptyBorder(10, 20, 6, 20));

        panelCards.add(criarCardMetrica("Mais Rápido", "---", new Color(21, 128, 61), 1));
        panelCards.add(criarCardMetrica("Registros Processados", "0", new Color(30, 64, 175), 2));
        panelCards.add(criarCardMetrica("Status do Bench", "Aguardando", new Color(71, 85, 105), 3));

        panelCentro.add(panelCards, BorderLayout.SOUTH);

        // Área Informativa do Drag and Drop
        panelDropZone = new JPanel(new BorderLayout());
        panelDropZone.setBackground(new Color(238, 242, 255));
        panelDropZone.setBorder(new CompoundBorder(
                new EmptyBorder(4, 20, 14, 20),
                new LineBorder(new Color(99, 102, 241), 1, true)
        ));

        lblDropZoneTexto = new JLabel(" [ARRASTE E SOLTE O ARQUIVO CSV / TXT AQUI] ", SwingConstants.CENTER);
        lblDropZoneTexto.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblDropZoneTexto.setForeground(new Color(67, 56, 202));
        lblDropZoneTexto.setPreferredSize(new Dimension(lblDropZoneTexto.getPreferredSize().width, 30));

        panelDropZone.add(lblDropZoneTexto, BorderLayout.CENTER);
        getContentPane().add(panelDropZone, BorderLayout.SOUTH);
    }

    /**
     * Monta cada Card de Métrica do Dashboard.
     */
    private JPanel criarCardMetrica(String titulo, String valorInicial, Color corDestaque, int tipoCard) {
        JPanel card = new JPanel(new BorderLayout(4, 4));
        card.setBackground(Color.WHITE);
        card.setBorder(new CompoundBorder(
                new LineBorder(new Color(203, 213, 225), 1, true),
                new EmptyBorder(8, 12, 8, 12)
        ));

        JLabel lblTituloCard = new JLabel(titulo.toUpperCase());
        lblTituloCard.setFont(new Font("Segoe UI", Font.BOLD, 11));
        lblTituloCard.setForeground(new Color(100, 116, 139));

        JLabel lblValorCard = new JLabel(valorInicial);
        lblValorCard.setFont(new Font("Segoe UI", Font.BOLD, 15));
        lblValorCard.setForeground(corDestaque);

        card.add(lblTituloCard, BorderLayout.NORTH);
        card.add(lblValorCard, BorderLayout.CENTER);

        if (tipoCard == 1) lblCardMaisRapidoVal = lblValorCard;
        if (tipoCard == 2) lblCardTotalTestesVal = lblValorCard;
        if (tipoCard == 3) lblCardStatusVal = lblValorCard;

        return card;
    }

    /**
     * Configura o Drag and Drop global.
     */
    private void configurarDragAndDropGlobal() {
        TransferHandler handler = new TransferHandler() {
            @Override
            public boolean canImport(TransferHandler.TransferSupport support) {
                if (support.isDataFlavorSupported(java.awt.datatransfer.DataFlavor.javaFileListFlavor)) {
                    panelDropZone.setBackground(new Color(220, 252, 231));
                    lblDropZoneTexto.setText(" >>> SOLTE O ARQUIVO PARA EXECUTAR O BENCHMARK <<< ");
                    lblDropZoneTexto.setForeground(new Color(22, 101, 52));
                    return true;
                }
                return false;
            }

            @Override
            public boolean importData(TransferHandler.TransferSupport support) {
                panelDropZone.setBackground(new Color(238, 242, 255));
                lblDropZoneTexto.setText(" [ARRASTE E SOLTE O ARQUIVO CSV / TXT AQUI] ");
                lblDropZoneTexto.setForeground(new Color(67, 56, 202));

                if (!canImport(support)) return false;

                try {
                    List<File> files = (List<File>) support.getTransferable()
                            .getTransferData(java.awt.datatransfer.DataFlavor.javaFileListFlavor);

                    if (!files.isEmpty()) {
                        File arquivo = files.get(0);
                        String nome = arquivo.getName().toLowerCase();
                        if (!nome.endsWith(".csv") && !nome.endsWith(".txt")) {
                            JOptionPane.showMessageDialog(TelaPrincipal.this,
                                    "Por favor, selecione apenas arquivos .csv ou .txt",
                                    "Formato Inválido", JOptionPane.WARNING_MESSAGE);
                            return false;
                        }
                        processarArquivoExterno(arquivo);
                    }
                    return true;
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(TelaPrincipal.this,
                            "Erro ao ler arquivo: " + ex.getMessage(),
                            "Erro", JOptionPane.ERROR_MESSAGE);
                    return false;
                }
            }
        };

        ((JComponent) getContentPane()).setTransferHandler(handler);
        scrollPaneTable.setTransferHandler(handler);
        tabelaResultados.setTransferHandler(handler);
    }

    /**
     * Processa arquivo externo.
     */
    private void processarArquivoExterno(File arquivo) {
        btnExecutar.setEnabled(false);
        btnCarregarCSV.setEnabled(false);
        btnExportarCSV.setEnabled(false);
        lblCardStatusVal.setText("Processando...");
        lblCardStatusVal.setForeground(new Color(194, 65, 12));

        SwingWorker<List<Resultado>, Void> worker = new SwingWorker<>() {
            @Override
            protected List<Resultado> doInBackground() throws Exception {
                int[] vetor = GeradorDeVetores.carregarDeCSV(arquivo.getAbsolutePath());

                List<SortAlgorithm> algoritmos = new ArrayList<>();
                algoritmos.add(new InsertionSort());
                algoritmos.add(new MergeSort());
                algoritmos.add(new HeapSort());
                algoritmos.add(new QuickSortDualPivot());
                algoritmos.add(new TimSortAdapter());

                List<Resultado> resultados = new ArrayList<>();
                for (SortAlgorithm alg : algoritmos) {
                    resultados.add(alg.executar(vetor, "CSV: " + arquivo.getName()));
                }
                return resultados;
            }

            @Override
            protected void done() {
                try {
                    List<Resultado> resultados = get();
                    DefaultTableModel model = (DefaultTableModel) tabelaResultados.getModel();
                    model.setRowCount(0);

                    Resultado maisRapido = null;

                    for (Resultado res : resultados) {
                        model.addRow(new Object[]{
                            res.getAlgoritmo(),
                            res.getCenario(),
                            String.format("%,d", res.getTamanhoVetor()),
                            String.format("%.3f ms", res.getTempoExecucaoMs()),
                            res.getComparacoes() >= 0 ? String.format("%,d", res.getComparacoes()) : "N/A",
                            res.getTrocas() >= 0 ? String.format("%,d", res.getTrocas()) : "N/A"
                        });

                        if (maisRapido == null || res.getTempoExecucaoMs() < maisRapido.getTempoExecucaoMs()) {
                            maisRapido = res;
                        }
                    }

                    if (maisRapido != null) {
                        lblCardMaisRapidoVal.setText(maisRapido.getAlgoritmo());
                        lblCardTotalTestesVal.setText(String.format("%,d reg", maisRapido.getTamanhoVetor()));
                    }
                    lblCardStatusVal.setText("Concluído");
                    lblCardStatusVal.setForeground(new Color(21, 128, 61));

                    JOptionPane.showMessageDialog(TelaPrincipal.this,
                            "Arquivo '" + arquivo.getName() + "' processado com sucesso!",
                            "Concluído", JOptionPane.INFORMATION_MESSAGE);

                } catch (Exception ex) {
                    lblCardStatusVal.setText("Erro");
                    lblCardStatusVal.setForeground(Color.RED);
                    JOptionPane.showMessageDialog(TelaPrincipal.this,
                            "Erro ao processar arquivo: " + ex.getMessage(),
                            "Erro", JOptionPane.ERROR_MESSAGE);
                } finally {
                    btnExecutar.setEnabled(true);
                    btnCarregarCSV.setEnabled(true);
                    btnExportarCSV.setEnabled(true);
                }
            }
        };

        worker.execute();
    }

    /**
     * Dispara os testes sintéticos.
     */
    private void btnExecutarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExecutarActionPerformed
        try {
            String[] partes = txtTamanhos.getText().split(",");
            int[] tamanhos = new int[partes.length];
            for (int i = 0; i < partes.length; i++) {
                tamanhos[i] = Integer.parseInt(partes[i].trim());
            }

            btnExecutar.setEnabled(false);
            btnCarregarCSV.setEnabled(false);
            btnExportarCSV.setEnabled(false);
            btnExecutar.setText(" Processando...");
            lblCardStatusVal.setText("Em Execução...");
            lblCardStatusVal.setForeground(new Color(194, 65, 12));

            SwingWorker<List<Resultado>, Void> worker = new SwingWorker<>() {
                @Override
                protected List<Resultado> doInBackground() throws Exception {
                    List<Resultado> todos = new ArrayList<>();
                    for (int t : tamanhos) {
                        todos.addAll(ExecutorBenchmark.executarTestesCompletos(t));
                    }
                    return todos;
                }

                @Override
                protected void done() {
                    try {
                        List<Resultado> resultados = get();
                        DefaultTableModel model = (DefaultTableModel) tabelaResultados.getModel();
                        model.setRowCount(0);

                        Resultado maisRapido = null;
                        int totalItens = 0;

                        for (Resultado res : resultados) {
                            model.addRow(new Object[]{
                                res.getAlgoritmo(),
                                res.getCenario(),
                                String.format("%,d", res.getTamanhoVetor()),
                                String.format("%.3f ms", res.getTempoExecucaoMs()),
                                res.getComparacoes() >= 0 ? String.format("%,d", res.getComparacoes()) : "N/A",
                                res.getTrocas() >= 0 ? String.format("%,d", res.getTrocas()) : "N/A"
                            });

                            if (maisRapido == null || res.getTempoExecucaoMs() < maisRapido.getTempoExecucaoMs()) {
                                maisRapido = res;
                            }
                            totalItens += res.getTamanhoVetor();
                        }

                        if (maisRapido != null) {
                            lblCardMaisRapidoVal.setText(maisRapido.getAlgoritmo());
                            lblCardTotalTestesVal.setText(String.format("%,d reg", totalItens));
                        }
                        lblCardStatusVal.setText("Concluído");
                        lblCardStatusVal.setForeground(new Color(21, 128, 61));

                        JOptionPane.showMessageDialog(TelaPrincipal.this,
                                "Testes sintéticos executados com sucesso!",
                                "Sucesso", JOptionPane.INFORMATION_MESSAGE);

                    } catch (Exception ex) {
                        lblCardStatusVal.setText("Erro");
                        lblCardStatusVal.setForeground(Color.RED);
                        JOptionPane.showMessageDialog(TelaPrincipal.this,
                                "Erro na execução dos testes: " + ex.getMessage(),
                                "Erro", JOptionPane.ERROR_MESSAGE);
                    } finally {
                        btnExecutar.setEnabled(true);
                        btnCarregarCSV.setEnabled(true);
                        btnExportarCSV.setEnabled(true);
                        btnExecutar.setText(" Executar Benchmarks");
                    }
                }
            };

            worker.execute();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                    "Insira tamanhos numéricos válidos separados por vírgula.",
                    "Entrada Inválida", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnExecutarActionPerformed

    private void btnCarregarCSVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCarregarCSVActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Selecionar Arquivo CSV ou TXT");
        fileChooser.setFileFilter(new FileNameExtensionFilter("Arquivos de Dados (CSV, TXT)", "csv", "txt"));

        int userSelection = fileChooser.showOpenDialog(this);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            processarArquivoExterno(fileChooser.getSelectedFile());
        }
    }//GEN-LAST:event_btnCarregarCSVActionPerformed

    private void btnExportarCSVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportarCSVActionPerformed
        DefaultTableModel model = (DefaultTableModel) tabelaResultados.getModel();
        if (model.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "Não existem dados na tabela para exportar.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Salvar Relatório CSV");
        fileChooser.setSelectedFile(new File("relatorio_sortbench.csv"));

        if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            try (PrintWriter pw = new PrintWriter(fileChooser.getSelectedFile())) {
                pw.println("Algoritmo;Cenario;Tamanho;Tempo_ms;Comparacoes;Trocas");
                for (int i = 0; i < model.getRowCount(); i++) {
                    pw.println(
                        model.getValueAt(i, 0) + ";" +
                        model.getValueAt(i, 1) + ";" +
                        model.getValueAt(i, 2) + ";" +
                        model.getValueAt(i, 3) + ";" +
                        model.getValueAt(i, 4) + ";" +
                        model.getValueAt(i, 5)
                    );
                }
                JOptionPane.showMessageDialog(this, "Relatório exportado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro ao exportar: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnExportarCSVActionPerformed

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(() -> new TelaPrincipal().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCarregarCSV;
    private javax.swing.JButton btnExecutar;
    private javax.swing.JButton btnExportarCSV;
    private javax.swing.JLabel lblSubtitulo;
    private javax.swing.JLabel lblTamanhos;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JPanel panelBarraAcoes;
    private javax.swing.JPanel panelCentro;
    private javax.swing.JPanel panelHeader;
    private javax.swing.JScrollPane scrollPaneTable;
    private javax.swing.JTable tabelaResultados;
    private javax.swing.JTextField txtTamanhos;
    // End of variables declaration//GEN-END:variables
}