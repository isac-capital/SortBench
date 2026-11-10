package sortbench.main;

import sortbench.model.Resultado;
import sortbench.util.ExecutorBenchmark;
import sortbench.util.LeitorArquivo;

import javax.imageio.ImageIO;
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
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

public class TelaPrincipal extends javax.swing.JFrame {
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(TelaPrincipal.class.getName());

    private JLabel lblCardMaisRapidoVal;
    private JLabel lblCardTotalTestesVal;
    private JLabel lblCardStatusVal;

    private JPanel panelDropZone;
    private JLabel lblDropZoneTexto;

    private static final Locale PT_BR = Locale.forLanguageTag("pt-BR");
    private static final int TAMANHO_MAXIMO = 10_000_000;

    private List<Resultado> resultadosAtuais = new ArrayList<>();

    private GraficoLinhas graficoLinhas;
    private JComboBox<String> comboCenarioGrafico;
    private JButton btnSalvarGrafico;

    private volatile boolean cancelamentoSolicitado = false;
    private JProgressBar barraProgresso;
    private JButton btnCancelar;

    public TelaPrincipal() {
        initComponents();
        estilizarComponentes();
        configurarDragAndDropGlobal();
        setLocationRelativeTo(null);
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

        btnExecutar.setBackground(new java.awt.Color(30, 64, 175));
        btnExecutar.setFont(new java.awt.Font("Segoe UI", Font.BOLD, 12));
        btnExecutar.setForeground(Color.WHITE);
        btnExecutar.setText(" Executar Benchmarks");
        btnExecutar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnExecutar.setFocusPainted(false);
        btnExecutar.setPreferredSize(new java.awt.Dimension(175, 32));
        btnExecutar.addActionListener(evt -> btnExecutarActionPerformed(evt));
        panelBarraAcoes.add(btnExecutar);

        btnCarregarCSV.setBackground(new java.awt.Color(21, 128, 61));
        btnCarregarCSV.setFont(new java.awt.Font("Segoe UI", Font.BOLD, 12));
        btnCarregarCSV.setForeground(Color.WHITE);
        btnCarregarCSV.setText(" Carregar CSV");
        btnCarregarCSV.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCarregarCSV.setFocusPainted(false);
        btnCarregarCSV.setPreferredSize(new java.awt.Dimension(135, 32));
        btnCarregarCSV.addActionListener(evt -> btnCarregarCSVActionPerformed(evt));
        panelBarraAcoes.add(btnCarregarCSV);

        btnExportarCSV.setBackground(new java.awt.Color(51, 65, 85));
        btnExportarCSV.setFont(new java.awt.Font("Segoe UI", Font.BOLD, 12));
        btnExportarCSV.setForeground(Color.WHITE);
        btnExportarCSV.setText(" Exportar CSV");
        btnExportarCSV.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnExportarCSV.setFocusPainted(false);
        btnExportarCSV.setPreferredSize(new java.awt.Dimension(135, 32));
        btnExportarCSV.addActionListener(evt -> btnExportarCSVActionPerformed(evt));
        panelBarraAcoes.add(btnExportarCSV);

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

    private void estilizarComponentes() {
        tabelaResultados.setModel(new DefaultTableModel(new Object[][] {}, new String[] {
            "Algoritmo", "Cenário de Teste", "Tamanho (N)", "Mediana (ms)", "Média (ms)",
            "Desvio (ms)", "Comparações", "Trocas", "Cópias", "Ordenado"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) { return false; }
        });
        int[] largurasColunas = {150, 170, 90, 100, 100, 100, 120, 110, 110, 80};
        for (int i = 0; i < largurasColunas.length; i++) {
            tabelaResultados.getColumnModel().getColumn(i).setPreferredWidth(largurasColunas[i]);
        }

        JTableHeader header = tabelaResultados.getTableHeader();
        header.setOpaque(false);
        header.setFont(new Font("Segoe UI", Font.BOLD, 13));
        header.setBackground(new Color(30, 41, 59));
        header.setForeground(Color.WHITE);
        header.setPreferredSize(new Dimension(header.getPreferredSize().width, 34));

        ((DefaultTableCellRenderer) header.getDefaultRenderer()).setHorizontalAlignment(JLabel.LEFT);

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

        JPanel panelCards = new JPanel(new GridLayout(1, 3, 12, 0));
        panelCards.setBackground(new Color(241, 245, 249));
        panelCards.setBorder(new EmptyBorder(10, 20, 6, 20));

        panelCards.add(criarCardMetrica("Mais Rápido", "---", new Color(21, 128, 61), 1));
        panelCards.add(criarCardMetrica("Testes Executados", "0", new Color(30, 64, 175), 2));
        panelCards.add(criarCardMetrica("Status do Bench", "Aguardando", new Color(71, 85, 105), 3));

        barraProgresso = new JProgressBar(0, 100);
        barraProgresso.setStringPainted(true);
        barraProgresso.setString("Pronto");
        barraProgresso.setPreferredSize(new Dimension(10, 24));

        JPanel faixaProgresso = new JPanel(new BorderLayout());
        faixaProgresso.setBackground(new Color(241, 245, 249));
        faixaProgresso.setBorder(new EmptyBorder(8, 20, 0, 20));
        faixaProgresso.add(barraProgresso, BorderLayout.CENTER);

        JPanel areaInferior = new JPanel(new BorderLayout());
        areaInferior.setBackground(new Color(241, 245, 249));
        areaInferior.add(faixaProgresso, BorderLayout.NORTH);
        areaInferior.add(panelCards, BorderLayout.CENTER);
        panelCentro.add(areaInferior, BorderLayout.SOUTH);

        btnCancelar = new JButton(" Cancelar");
        btnCancelar.setBackground(new Color(185, 28, 28));
        btnCancelar.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnCancelar.setForeground(Color.WHITE);
        btnCancelar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnCancelar.setFocusPainted(false);
        btnCancelar.setPreferredSize(new Dimension(135, 32));
        btnCancelar.setEnabled(false);
        btnCancelar.addActionListener(e -> cancelarExecucao());
        panelBarraAcoes.add(btnCancelar);

        graficoLinhas = new GraficoLinhas();
        comboCenarioGrafico = new JComboBox<>();
        comboCenarioGrafico.addActionListener(e -> {
            Object escolhido = comboCenarioGrafico.getSelectedItem();
            if (escolhido != null) {
                graficoLinhas.setCenario(escolhido.toString());
            }
        });
        btnSalvarGrafico = new JButton("Salvar gráfico (PNG)");
        btnSalvarGrafico.addActionListener(e -> salvarGraficoPng());

        JPanel barraGrafico = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 8));
        barraGrafico.setBackground(Color.WHITE);
        barraGrafico.add(new JLabel("Cenário:"));
        barraGrafico.add(comboCenarioGrafico);
        barraGrafico.add(btnSalvarGrafico);

        JPanel painelGrafico = new JPanel(new BorderLayout());
        painelGrafico.add(barraGrafico, BorderLayout.NORTH);
        painelGrafico.add(graficoLinhas, BorderLayout.CENTER);

        panelCentro.remove(scrollPaneTable);
        JTabbedPane abasResultados = new JTabbedPane();
        abasResultados.addTab("Tabela", scrollPaneTable);
        abasResultados.addTab("Gráfico", painelGrafico);
        panelCentro.add(abasResultados, BorderLayout.CENTER);

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
                        SwingUtilities.invokeLater(() -> processarArquivoExterno(arquivo));
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
        graficoLinhas.setTransferHandler(handler);
        tabelaResultados.setTransferHandler(handler);
    }

    private void processarArquivoExterno(File arquivo) {
        String textoColuna = JOptionPane.showInputDialog(this,
                "Número da coluna a ordenar (a primeira coluna é a 0):", "0");
        if (textoColuna == null) return;
        String textoCasas = JOptionPane.showInputDialog(this,
                "Casas decimais a manter (0 = arredonda para inteiro):", "0");
        if (textoCasas == null) return;

        int colunaLida;
        int casasLidas;
        try {
            colunaLida = Integer.parseInt(textoColuna.trim());
            casasLidas = Integer.parseInt(textoCasas.trim());
            if (colunaLida < 0 || casasLidas < 0 || casasLidas > 9) throw new NumberFormatException();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                    "Digite apenas números inteiros (coluna a partir de 0 e casas decimais de 0 a 9).",
                    "Entrada Inválida", JOptionPane.ERROR_MESSAGE);
            return;
        }
        final int coluna = colunaLida;
        final int casas = casasLidas;

        definirEmExecucao(true, "Processando...");

        SwingWorker<List<Resultado>, Void> worker = new SwingWorker<>() {
            private int valoresLidos;
            private int linhasIgnoradas;

            @Override
            protected List<Resultado> doInBackground() throws Exception {
                int[] vetor = LeitorArquivo.lerColuna(arquivo.getAbsolutePath(), coluna, casas);
                valoresLidos = vetor.length;
                linhasIgnoradas = LeitorArquivo.linhasIgnoradas;
                if (vetor.length == 0) {
                    throw new Exception("Nenhum valor numérico encontrado na coluna " + coluna
                            + ". Confira o número da coluna.");
                }
                return ExecutorBenchmark.executarSobreVetor(vetor, "CSV: " + arquivo.getName(), new AcompanhamentoJanela());
            }

            @Override
            protected void done() {
                try {
                    List<Resultado> resultados = get();
                    mostrarResultados(resultados);
                    if (cancelamentoSolicitado) {
                        avisarCancelamento(resultados.size());
                        return;
                    }
                    String mensagem = String.format(PT_BR,
                            "Arquivo '%s' processado: %,d valores lidos e %,d linhas ignoradas (inclui o cabeçalho, se houver).",
                            arquivo.getName(), valoresLidos, linhasIgnoradas);
                    if (valoresLidos > ExecutorBenchmark.LIMITE_INSERTION) {
                        mensagem += "\nO InsertionSort não foi executado (limite de "
                                + String.format(PT_BR, "%,d", ExecutorBenchmark.LIMITE_INSERTION) + " elementos).";
                    }
                    finalizarBarra("Concluído", 100);
                    JOptionPane.showMessageDialog(TelaPrincipal.this, mensagem, "Concluído", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception ex) {
                    mostrarErro("Erro ao processar arquivo: ", ex);
                } finally {
                    definirEmExecucao(false, null);
                }
            }
        };

        worker.execute();
    }

    private void btnExecutarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExecutarActionPerformed
        int[] lidos;
        try {
            String[] partes = txtTamanhos.getText().split(",");
            lidos = new int[partes.length];
            for (int i = 0; i < partes.length; i++) {
                lidos[i] = Integer.parseInt(partes[i].trim());
                if (lidos[i] < 1 || lidos[i] > TAMANHO_MAXIMO) throw new NumberFormatException();
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                    "Insira tamanhos inteiros entre 1 e 10.000.000, separados por vírgula.",
                    "Entrada Inválida", JOptionPane.ERROR_MESSAGE);
            return;
        }
        final int[] tamanhos = lidos;

        definirEmExecucao(true, "Em Execução...");

        SwingWorker<List<Resultado>, Void> worker = new SwingWorker<>() {
            @Override
            protected List<Resultado> doInBackground() throws Exception {
                return ExecutorBenchmark.executarVarios(tamanhos, new AcompanhamentoJanela());
            }

            @Override
            protected void done() {
                try {
                    List<Resultado> resultados = get();
                    mostrarResultados(resultados);
                    if (cancelamentoSolicitado) {
                        avisarCancelamento(resultados.size());
                        return;
                    }
                    String mensagem = "Testes sintéticos executados com sucesso!";
                    for (int t : tamanhos) {
                        if (t > ExecutorBenchmark.LIMITE_INSERTION) {
                            mensagem += "\nO InsertionSort foi ignorado nos tamanhos acima de "
                                    + String.format(PT_BR, "%,d", ExecutorBenchmark.LIMITE_INSERTION) + ".";
                            break;
                        }
                    }
                    finalizarBarra("Concluído", 100);
                    JOptionPane.showMessageDialog(TelaPrincipal.this, mensagem, "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception ex) {
                    mostrarErro("Erro na execução dos testes: ", ex);
                } finally {
                    definirEmExecucao(false, null);
                }
            }
        };

        worker.execute();
    }//GEN-LAST:event_btnExecutarActionPerformed

    private void mostrarResultados(List<Resultado> resultados) {
        resultadosAtuais = resultados;
        DefaultTableModel model = (DefaultTableModel) tabelaResultados.getModel();
        model.setRowCount(0);

        int maiorTamanho = 0;
        for (Resultado res : resultados) {
            model.addRow(new Object[]{
                res.getAlgoritmo(),
                res.getCenario(),
                String.format(PT_BR, "%,d", res.getTamanhoVetor()),
                String.format(PT_BR, "%.3f", res.getTempoExecucaoMs()),
                String.format(PT_BR, "%.3f", res.getMediaMs()),
                String.format(PT_BR, "%.3f", res.getDesvioPadraoMs()),
                String.format(PT_BR, "%,d", res.getComparacoes()),
                String.format(PT_BR, "%,d", res.getTrocas()),
                String.format(PT_BR, "%,d", res.getCopias()),
                res.isOrdenado() ? "OK" : "ERRO"
            });
            maiorTamanho = Math.max(maiorTamanho, res.getTamanhoVetor());
        }

        Map<String, Double> somaPorAlgoritmo = new LinkedHashMap<>();
        for (Resultado res : resultados) {
            if (res.getTamanhoVetor() == maiorTamanho) {
                somaPorAlgoritmo.merge(res.getAlgoritmo(), res.getTempoExecucaoMs(), Double::sum);
            }
        }
        String maisRapido = "---";
        double menorSoma = Double.MAX_VALUE;
        for (Map.Entry<String, Double> entrada : somaPorAlgoritmo.entrySet()) {
            if (entrada.getValue() < menorSoma) {
                menorSoma = entrada.getValue();
                maisRapido = entrada.getKey();
            }
        }

        lblCardMaisRapidoVal.setText(maisRapido);
        lblCardTotalTestesVal.setText(String.format(PT_BR, "%,d testes (N máx.: %,d)", resultados.size(), maiorTamanho));
        lblCardStatusVal.setText("Concluído");
        lblCardStatusVal.setForeground(new Color(21, 128, 61));

        atualizarGrafico(resultados);
    }

    private void atualizarGrafico(List<Resultado> resultados) {
        graficoLinhas.setResultados(resultados);
        comboCenarioGrafico.removeAllItems();
        for (String cenario : graficoLinhas.getCenarios()) {
            comboCenarioGrafico.addItem(cenario);
        }
        if (comboCenarioGrafico.getItemCount() > 0) {
            comboCenarioGrafico.setSelectedIndex(0);
        }
    }

    private void salvarGraficoPng() {
        if (resultadosAtuais.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Execute um teste antes de salvar o gráfico.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Salvar Gráfico");
        fileChooser.setSelectedFile(new File("grafico_sortbench.png"));

        if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            File arquivo = fileChooser.getSelectedFile();
            if (!arquivo.getName().toLowerCase().endsWith(".png")) {
                arquivo = new File(arquivo.getPath() + ".png");
            }
            try {
                ImageIO.write(graficoLinhas.gerarImagem(1100, 650), "png", arquivo);
                JOptionPane.showMessageDialog(this, "Gráfico salvo com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro ao salvar o gráfico: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void definirEmExecucao(boolean emExecucao, String textoStatus) {
        btnExecutar.setEnabled(!emExecucao);
        btnCarregarCSV.setEnabled(!emExecucao);
        btnExportarCSV.setEnabled(!emExecucao);
        btnCancelar.setEnabled(emExecucao);
        btnExecutar.setText(emExecucao ? " Processando..." : " Executar Benchmarks");
        if (emExecucao) {
            cancelamentoSolicitado = false;
            finalizarBarra("Iniciando...", 0);
            lblCardStatusVal.setText(textoStatus);
            lblCardStatusVal.setForeground(new Color(194, 65, 12));
        }
    }

    private class AcompanhamentoJanela implements ExecutorBenchmark.Acompanhamento {
        @Override
        public void progresso(int concluidos, int total, String descricao) {
            SwingUtilities.invokeLater(() -> atualizarProgresso(concluidos, total, descricao));
        }

        @Override
        public boolean cancelado() {
            return cancelamentoSolicitado;
        }
    }

    private void atualizarProgresso(int concluidos, int total, String descricao) {
        if (cancelamentoSolicitado) {
            return;
        }
        int percentual = total == 0 ? 0 : (int) Math.round(100.0 * concluidos / total);
        barraProgresso.setValue(percentual);
        barraProgresso.setString(String.format(PT_BR, "%d%%  (%d de %d testes) - %s",
                percentual, concluidos, total, descricao));
    }

    private void finalizarBarra(String texto, int valor) {
        barraProgresso.setValue(valor);
        barraProgresso.setString(texto);
    }

    private void cancelarExecucao() {
        cancelamentoSolicitado = true;
        btnCancelar.setEnabled(false);
        barraProgresso.setString("Cancelando... aguardando o teste atual terminar");
    }

    private void avisarCancelamento(int testesConcluidos) {
        lblCardStatusVal.setText("Cancelado");
        lblCardStatusVal.setForeground(new Color(194, 65, 12));
        finalizarBarra("Cancelado (" + testesConcluidos + " testes concluídos)", barraProgresso.getValue());
        JOptionPane.showMessageDialog(this,
                "Execução cancelada. Os " + testesConcluidos + " testes já concluídos continuam na tabela.",
                "Cancelado", JOptionPane.INFORMATION_MESSAGE);
    }

    private void mostrarErro(String prefixo, Exception ex) {
        Throwable causa = ex.getCause() != null ? ex.getCause() : ex;
        lblCardStatusVal.setText("Erro");
        lblCardStatusVal.setForeground(Color.RED);
        finalizarBarra("Erro", 0);
        String detalhe = causa.getMessage() != null ? causa.getMessage() : causa.toString();
        JOptionPane.showMessageDialog(this, prefixo + detalhe,
                "Erro", JOptionPane.ERROR_MESSAGE);
    }

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
        if (resultadosAtuais.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Não existem dados na tabela para exportar.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Salvar Relatório CSV");
        fileChooser.setSelectedFile(new File("relatorio_sortbench.csv"));

        if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            try (PrintWriter pw = new PrintWriter(fileChooser.getSelectedFile(), "UTF-8")) {
                pw.print('\uFEFF');
                pw.println("Algoritmo;Cenario;Tamanho;Mediana_ms;Media_ms;Desvio_ms;Comparacoes;Trocas;Copias;Ordenado");
                for (Resultado res : resultadosAtuais) {
                    pw.println(String.join(";",
                        res.getAlgoritmo(),
                        res.getCenario(),
                        String.valueOf(res.getTamanhoVetor()),
                        String.format(PT_BR, "%.3f", res.getTempoExecucaoMs()),
                        String.format(PT_BR, "%.3f", res.getMediaMs()),
                        String.format(PT_BR, "%.3f", res.getDesvioPadraoMs()),
                        String.valueOf(res.getComparacoes()),
                        String.valueOf(res.getTrocas()),
                        String.valueOf(res.getCopias()),
                        res.isOrdenado() ? "OK" : "ERRO"
                    ));
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
