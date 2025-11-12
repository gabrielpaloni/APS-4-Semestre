package view;

import model.bean.Jogo;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.net.URL;
import java.util.List;

public class TelaAnalyticsJogos extends JFrame {

    private static final Color COR_FUNDO = new Color(18, 23, 35);
    private static final Color COR_PAINEL_FUNDO = new Color(27, 34, 52);
    private static final Color COR_TEXTO = new Color(220, 220, 220);
    private static final Color COR_DESTAQUE = new Color(0, 255, 255);

    private Image backgroundImage;

    public TelaAnalyticsJogos(List<Jogo> jogosDoVendedor) {
        setTitle("Análise de Jogos");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(COR_FUNDO);

        try {
            URL bgUrl = getClass().getClassLoader().getResource("analytics_bg.png");
            if (bgUrl == null) {
                throw new RuntimeException("Imagem de fundo 'analytics_bg.png' não encontrada! Verifique o nome na pasta 'resources'.");
            }
            this.backgroundImage = new ImageIcon(bgUrl).getImage();

        } catch (Exception e) {
            e.printStackTrace();
            this.backgroundImage = null;
        }

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setFont(new Font("Segoe UI", Font.BOLD, 14));
        tabbedPane.setOpaque(false);

        JPanel painelJogos = createPainelJogos(jogosDoVendedor, this.backgroundImage);
        tabbedPane.addTab("Jogos", painelJogos);

        JPanel painelRequisitos = createPainelRequisitos(jogosDoVendedor, this.backgroundImage);
        tabbedPane.addTab("Requisitos", painelRequisitos);

        add(tabbedPane);
    }

    private JPanel createPainelJogos(List<Jogo> jogosDoVendedor, Image bgImage) {
        BackgroundPanel painel = new BackgroundPanel(bgImage);
        painel.setLayout(new BorderLayout());
        painel.setBorder(new EmptyBorder(10, 10, 10, 10));

        String[] colunas = {"ID", "Título", "Vendas", "Última Venda", "Lançamento", "Publicação"};
        DefaultTableModel tableModel = new DefaultTableModel(colunas, 0) {
            @Override public boolean isCellEditable(int row, int column) { return false; }
        };

        for (Jogo jogo : jogosDoVendedor) {
            Object[] linha = {
                    jogo.getId(),
                    jogo.getNome(),
                    jogo.getNumeroDeVendas(),
                    (jogo.getDataUltimaVenda() != null ? jogo.getDataUltimaVenda() : "Nenhuma venda"),
                    jogo.getDataLancamento(),
                    jogo.getDataPublicacao()
            };
            tableModel.addRow(linha);
        }

        JTable tabela = new JTable(tableModel);

        tabela.setBackground(COR_PAINEL_FUNDO);
        tabela.setForeground(COR_TEXTO);
        tabela.setGridColor(COR_DESTAQUE);
        tabela.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        tabela.setRowHeight(25);
        tabela.setSelectionBackground(new Color(75, 110, 175));
        tabela.setSelectionForeground(COR_DESTAQUE);

        JTableHeader header = tabela.getTableHeader();
        header.setBackground(Color.BLACK);
        header.setForeground(COR_DESTAQUE);
        header.setFont(new Font("Segoe UI", Font.BOLD, 12));

        JScrollPane scrollPaneTabela = new JScrollPane(tabela);
        scrollPaneTabela.setBorder(BorderFactory.createLineBorder(COR_DESTAQUE));
        scrollPaneTabela.getViewport().setBackground(COR_PAINEL_FUNDO);

        painel.add(scrollPaneTabela, BorderLayout.CENTER);
        return painel;
    }

    private JPanel createPainelRequisitos(List<Jogo> jogosDoVendedor, Image bgImage) {
        BackgroundPanel painelPrincipal = new BackgroundPanel(bgImage);
        painelPrincipal.setLayout(new BorderLayout());

        JPanel painelConteudo = new JPanel();
        painelConteudo.setLayout(new BoxLayout(painelConteudo, BoxLayout.Y_AXIS));
        painelConteudo.setOpaque(false);
        painelConteudo.setBorder(new EmptyBorder(15, 15, 15, 15));

        for (Jogo jogo : jogosDoVendedor) {
            JLabel labelTitulo = new JLabel(jogo.getNome());
            labelTitulo.setFont(new Font("Segoe UI", Font.BOLD, 18));
            labelTitulo.setForeground(COR_DESTAQUE);
            labelTitulo.setAlignmentX(Component.LEFT_ALIGNMENT);
            painelConteudo.add(labelTitulo);

            painelConteudo.add(Box.createRigidArea(new Dimension(0, 5)));

            JTextArea areaRequisitos = new JTextArea(jogo.getRequisitosSistema());
            areaRequisitos.setFont(new Font("Segoe UI", Font.PLAIN, 12));
            areaRequisitos.setForeground(COR_TEXTO);
            areaRequisitos.setOpaque(false);
            areaRequisitos.setBackground(new Color(0, 0, 0, 0));
            areaRequisitos.setEditable(false);

            areaRequisitos.setLineWrap(true);
            areaRequisitos.setWrapStyleWord(true);

            areaRequisitos.setFocusable(false);
            areaRequisitos.setAlignmentX(Component.LEFT_ALIGNMENT);

            painelConteudo.add(areaRequisitos);

            painelConteudo.add(Box.createRigidArea(new Dimension(0, 15)));

            JSeparator separator = new JSeparator();
            separator.setForeground(COR_DESTAQUE);
            separator.setBackground(COR_DESTAQUE);
            separator.setAlignmentX(Component.LEFT_ALIGNMENT);
            painelConteudo.add(separator);

            painelConteudo.add(Box.createRigidArea(new Dimension(0, 15)));
        }

        JScrollPane scrollPane = new JScrollPane(painelConteudo);
        scrollPane.setBorder(null);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);

        painelPrincipal.add(scrollPane, BorderLayout.CENTER);
        return painelPrincipal;
    }
}