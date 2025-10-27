package view;

import model.bean.Jogo;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.util.List;

public class TelaAnalyticsJogos extends JFrame {

    private static final Color COR_FUNDO = new Color(18, 23, 35);
    private static final Color COR_PAINEL_FUNDO = new Color(27, 34, 52);
    private static final Color COR_TEXTO = new Color(220, 220, 220);
    private static final Color COR_DESTAQUE = new Color(0, 255, 255);

    public TelaAnalyticsJogos(List<Jogo> jogosDoVendedor) {
        setTitle("Análise de Jogos");
        setSize(1200, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(COR_FUNDO);

        JPanel painelPrincipal = new JPanel(new BorderLayout());
        painelPrincipal.setBorder(new EmptyBorder(10,10,10,10));
        painelPrincipal.setBackground(COR_FUNDO);

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitPane.setResizeWeight(0.7);
        splitPane.setBorder(null);
        splitPane.setBackground(COR_FUNDO);
        splitPane.setDividerSize(10);

        String[] colunas = {"ID", "Título", "Vendas", "Última Venda", "Lançamento", "Publicação"};
        DefaultTableModel tableModel = new DefaultTableModel(colunas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
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

        splitPane.setLeftComponent(scrollPaneTabela);

        JPanel painelDireita = new JPanel(new BorderLayout(0, 10));
        painelDireita.setBackground(COR_FUNDO);
        painelDireita.setBorder(BorderFactory.createLineBorder(COR_DESTAQUE));

        JLabel labelRequisitos = new JLabel("Requisitos do Sistema");
        labelRequisitos.setFont(new Font("Segoe UI", Font.BOLD, 16));
        labelRequisitos.setForeground(COR_TEXTO);
        labelRequisitos.setBorder(new EmptyBorder(10, 10, 0, 10));
        painelDireita.add(labelRequisitos, BorderLayout.NORTH);

        JTextArea areaRequisitos = new JTextArea();
        areaRequisitos.setBackground(COR_PAINEL_FUNDO);
        areaRequisitos.setForeground(COR_TEXTO);
        areaRequisitos.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        areaRequisitos.setEditable(false);
        areaRequisitos.setLineWrap(true);
        areaRequisitos.setWrapStyleWord(true);
        areaRequisitos.setText("<- Clique em um jogo na tabela para ver os requisitos.");
        areaRequisitos.setBorder(new EmptyBorder(5, 10, 10, 10));

        JScrollPane scrollPaneRequisitos = new JScrollPane(areaRequisitos);
        scrollPaneRequisitos.setBorder(null);
        scrollPaneRequisitos.getViewport().setBackground(COR_PAINEL_FUNDO);

        painelDireita.add(scrollPaneRequisitos, BorderLayout.CENTER);

        splitPane.setRightComponent(painelDireita);

        tabela.getSelectionModel().addListSelectionListener(event -> {
            if (!event.getValueIsAdjusting()) {
                int linhaSelecionada = tabela.getSelectedRow();
                if (linhaSelecionada != -1) {
                    String requisitosCompletos = jogosDoVendedor.get(linhaSelecionada).getRequisitosSistema();
                    areaRequisitos.setText(requisitosCompletos);
                    areaRequisitos.setCaretPosition(0);
                }
            }
        });

        painelPrincipal.add(splitPane, BorderLayout.CENTER);
        add(painelPrincipal);
    }
}
