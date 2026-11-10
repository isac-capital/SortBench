package sortbench.main;

import sortbench.model.Resultado;

import javax.swing.JPanel;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Path2D;
import java.awt.image.BufferedImage;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeSet;

public class GraficoLinhas extends JPanel {

    private static final Locale PT_BR = Locale.forLanguageTag("pt-BR");
    private static final String[] ORDEM_ALGORITMOS = {"QuickSort", "MergeSort", "HeapSort", "RadixSort", "InsertionSort"};
    private static final Color[] PALETA = {
        new Color(31, 119, 180), new Color(255, 127, 14), new Color(44, 160, 44),
        new Color(214, 39, 40), new Color(148, 103, 189), new Color(140, 86, 75)
    };
    private static final Color COR_TEXTO = new Color(30, 41, 59);
    private static final Color COR_GRADE = new Color(226, 232, 240);
    private static final double TEMPO_MINIMO_MS = 0.0001;

    private List<Resultado> resultados = new ArrayList<>();
    private String cenario = null;

    public GraficoLinhas() {
        setBackground(Color.WHITE);
    }

    public void setResultados(List<Resultado> resultados) {
        this.resultados = resultados == null ? new ArrayList<Resultado>() : resultados;
        List<String> lista = getCenarios();
        if (cenario == null || !lista.contains(cenario)) {
            cenario = lista.isEmpty() ? null : lista.get(0);
        }
        repaint();
    }

    public List<String> getCenarios() {
        List<String> lista = new ArrayList<>();
        for (Resultado r : resultados) {
            if (!lista.contains(r.getCenario())) {
                lista.add(r.getCenario());
            }
        }
        return lista;
    }

    public void setCenario(String cenario) {
        this.cenario = cenario;
        repaint();
    }

    public BufferedImage gerarImagem(int largura, int altura) {
        BufferedImage imagem = new BufferedImage(largura, altura, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = imagem.createGraphics();
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, largura, altura);
        desenhar(g, largura, altura);
        g.dispose();
        return imagem;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        desenhar(g2, getWidth(), getHeight());
        g2.dispose();
    }

    private void desenhar(Graphics2D g, int largura, int altura) {
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        List<Resultado> doCenario = new ArrayList<>();
        for (Resultado r : resultados) {
            if (r.getCenario().equals(cenario)) {
                doCenario.add(r);
            }
        }
        if (doCenario.isEmpty()) {
            g.setColor(new Color(100, 116, 139));
            g.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 14));
            g.drawString("Execute um teste para ver o gráfico.", 30, 40);
            return;
        }

        TreeSet<Integer> tamanhos = new TreeSet<>();
        Map<String, List<Resultado>> series = new LinkedHashMap<>();
        double menor = Double.MAX_VALUE;
        double maior = 0;
        for (Resultado r : doCenario) {
            tamanhos.add(r.getTamanhoVetor());
            List<Resultado> serie = series.get(r.getAlgoritmo());
            if (serie == null) {
                serie = new ArrayList<>();
                series.put(r.getAlgoritmo(), serie);
            }
            serie.add(r);
            double valor = Math.max(r.getTempoExecucaoMs(), TEMPO_MINIMO_MS);
            menor = Math.min(menor, valor);
            maior = Math.max(maior, valor);
        }
        for (List<Resultado> serie : series.values()) {
            serie.sort(Comparator.comparingInt(Resultado::getTamanhoVetor));
        }

        boolean modoLinhas = tamanhos.size() >= 2;
        int expMin = (int) Math.floor(Math.log10(menor));
        int expMax = (int) Math.ceil(Math.log10(maior));
        if (expMax <= expMin) {
            expMax = expMin + 1;
        }

        int margemEsq = 95;
        int margemDir = 30;
        int margemTopo = 92;
        int margemBase = modoLinhas ? 75 : 60;
        int x0 = margemEsq;
        int y0 = margemTopo;
        int larguraGrafico = largura - margemEsq - margemDir;
        int alturaGrafico = altura - margemTopo - margemBase;
        if (larguraGrafico < 120 || alturaGrafico < 80) {
            return;
        }

        String titulo = "Mediana do tempo de ordenação: " + cenario;
        if (!modoLinhas) {
            titulo += "  (N = " + String.format(PT_BR, "%,d", tamanhos.first()) + ")";
        }
        int tamanhoFonte = 16;
        Font fonteTitulo = new Font(Font.SANS_SERIF, Font.BOLD, tamanhoFonte);
        while (tamanhoFonte > 10 && g.getFontMetrics(fonteTitulo).stringWidth(titulo) > largura - x0 - 10) {
            tamanhoFonte--;
            fonteTitulo = new Font(Font.SANS_SERIF, Font.BOLD, tamanhoFonte);
        }
        g.setColor(COR_TEXTO);
        g.setFont(fonteTitulo);
        g.drawString(titulo, x0, 28);
        g.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 12));
        g.setColor(new Color(100, 116, 139));
        g.drawString(modoLinhas ? "Escala logarítmica nos dois eixos" : "Escala logarítmica no eixo do tempo", x0, 46);

        if (modoLinhas) {
            desenharLegenda(g, new ArrayList<>(series.keySet()), x0, 60, largura - margemDir);
        }

        g.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 12));
        FontMetrics fm = g.getFontMetrics();
        NumberFormat formato = NumberFormat.getNumberInstance(PT_BR);
        formato.setMaximumFractionDigits(6);
        g.setStroke(new BasicStroke(1f));
        for (int e = expMin; e <= expMax; e++) {
            int y = posicaoY(Math.pow(10, e), expMin, expMax, y0, alturaGrafico);
            g.setColor(COR_GRADE);
            g.drawLine(x0, y, x0 + larguraGrafico, y);
            g.setColor(COR_TEXTO);
            String rotulo = formato.format(Math.pow(10, e));
            g.drawString(rotulo, x0 - 10 - fm.stringWidth(rotulo), y + 4);
        }

        g.setColor(COR_TEXTO);
        Graphics2D rotacionado = (Graphics2D) g.create();
        rotacionado.rotate(-Math.PI / 2);
        String tituloY = "Tempo (ms)";
        rotacionado.drawString(tituloY, -(y0 + alturaGrafico / 2 + fm.stringWidth(tituloY) / 2), 18);
        rotacionado.dispose();

        if (modoLinhas) {
            desenharLinhas(g, series, tamanhos, expMin, expMax, x0, y0, larguraGrafico, alturaGrafico, fm);
        } else {
            desenharBarras(g, series, expMin, expMax, x0, y0, larguraGrafico, alturaGrafico, fm);
        }

        g.setColor(COR_TEXTO);
        g.setStroke(new BasicStroke(1.5f));
        g.drawLine(x0, y0, x0, y0 + alturaGrafico);
        g.drawLine(x0, y0 + alturaGrafico, x0 + larguraGrafico, y0 + alturaGrafico);
    }

    private void desenharLinhas(Graphics2D g, Map<String, List<Resultado>> series, TreeSet<Integer> tamanhos,
                                int expMin, int expMax, int x0, int y0, int larguraGrafico, int alturaGrafico, FontMetrics fm) {
        int folga = 45;
        double logMin = Math.log10(tamanhos.first());
        double logMax = Math.log10(tamanhos.last());
        int xInicio = x0 + folga;
        int largUtil = larguraGrafico - 2 * folga;

        for (int tamanho : tamanhos) {
            int x = xInicio + (int) Math.round((Math.log10(tamanho) - logMin) / (logMax - logMin) * largUtil);
            g.setColor(COR_GRADE);
            g.setStroke(new BasicStroke(1f));
            g.drawLine(x, y0, x, y0 + alturaGrafico);
            g.setColor(COR_TEXTO);
            String rotulo = String.format(PT_BR, "%,d", tamanho);
            g.drawString(rotulo, x - fm.stringWidth(rotulo) / 2, y0 + alturaGrafico + 20);
        }
        String tituloX = "Tamanho do vetor (N)";
        g.setColor(COR_TEXTO);
        g.drawString(tituloX, x0 + larguraGrafico / 2 - fm.stringWidth(tituloX) / 2, y0 + alturaGrafico + 45);

        for (Map.Entry<String, List<Resultado>> entrada : series.entrySet()) {
            Color cor = corDe(entrada.getKey(), new ArrayList<>(series.keySet()));
            Path2D linha = new Path2D.Double();
            boolean primeiro = true;
            List<int[]> pontos = new ArrayList<>();
            for (Resultado r : entrada.getValue()) {
                int x = xInicio + (int) Math.round((Math.log10(r.getTamanhoVetor()) - logMin) / (logMax - logMin) * largUtil);
                int y = posicaoY(Math.max(r.getTempoExecucaoMs(), TEMPO_MINIMO_MS), expMin, expMax, y0, alturaGrafico);
                pontos.add(new int[]{x, y});
                if (primeiro) {
                    linha.moveTo(x, y);
                    primeiro = false;
                } else {
                    linha.lineTo(x, y);
                }
            }
            g.setColor(cor);
            g.setStroke(new BasicStroke(2.6f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
            g.draw(linha);
            for (int[] p : pontos) {
                g.setColor(Color.WHITE);
                g.fillOval(p[0] - 6, p[1] - 6, 12, 12);
                g.setColor(cor);
                g.fillOval(p[0] - 4, p[1] - 4, 8, 8);
            }
        }
    }

    private void desenharBarras(Graphics2D g, Map<String, List<Resultado>> series, int expMin, int expMax,
                                int x0, int y0, int larguraGrafico, int alturaGrafico, FontMetrics fm) {
        List<String> nomes = new ArrayList<>(series.keySet());
        int n = nomes.size();
        int faixa = larguraGrafico / n;
        int larguraBarra = Math.min(90, (int) (faixa * 0.6));
        int base = y0 + alturaGrafico;
        for (int i = 0; i < n; i++) {
            String nome = nomes.get(i);
            Resultado r = series.get(nome).get(0);
            int centro = x0 + faixa * i + faixa / 2;
            int y = posicaoY(Math.max(r.getTempoExecucaoMs(), TEMPO_MINIMO_MS), expMin, expMax, y0, alturaGrafico);
            g.setColor(corDe(nome, nomes));
            g.fillRect(centro - larguraBarra / 2, y, larguraBarra, base - y);
            g.setColor(COR_TEXTO);
            String valor = String.format(PT_BR, "%.3f ms", r.getTempoExecucaoMs());
            g.drawString(valor, centro - fm.stringWidth(valor) / 2, y - 6);
            g.drawString(nome, centro - fm.stringWidth(nome) / 2, base + 22);
        }
    }

    private void desenharLegenda(Graphics2D g, List<String> nomes, int xInicio, int yInicio, int xLimite) {
        g.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 12));
        FontMetrics fm = g.getFontMetrics();
        int x = xInicio;
        int y = yInicio;
        for (String nome : nomes) {
            int larguraItem = 30 + fm.stringWidth(nome) + 22;
            if (x + larguraItem > xLimite && x > xInicio) {
                x = xInicio;
                y += 20;
            }
            g.setColor(corDe(nome, nomes));
            g.setStroke(new BasicStroke(3f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
            g.drawLine(x, y + 5, x + 22, y + 5);
            g.fillOval(x + 7, y + 1, 8, 8);
            g.setColor(COR_TEXTO);
            g.drawString(nome, x + 28, y + 10);
            x += larguraItem;
        }
    }

    private int posicaoY(double valor, int expMin, int expMax, int y0, int alturaGrafico) {
        double fracao = (Math.log10(valor) - expMin) / (expMax - expMin);
        fracao = Math.max(0, Math.min(1, fracao));
        return y0 + alturaGrafico - (int) Math.round(fracao * alturaGrafico);
    }

    private Color corDe(String algoritmo, List<String> encontrados) {
        for (int i = 0; i < ORDEM_ALGORITMOS.length; i++) {
            if (ORDEM_ALGORITMOS[i].equals(algoritmo)) {
                return PALETA[i];
            }
        }
        return PALETA[(ORDEM_ALGORITMOS.length + encontrados.indexOf(algoritmo)) % PALETA.length];
    }
}
