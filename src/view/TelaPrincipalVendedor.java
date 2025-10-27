package view;

import model.bean.Vendedor;
import model.dao.VendedorDAO;
import model.dao.JogoDAO;
import java.util.List;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.net.URL;
import java.awt.Image;

public class TelaPrincipalVendedor extends JFrame {

    private Vendedor vendedorLogado;
    private JPanel gamesGridPanel;

    private static final Color COR_FUNDO = new Color(18, 23, 35);
    private static final Color COR_PAINEL_FUNDO = new Color(27, 34, 52);
    private static final Color COR_TEXTO = new Color(220, 220, 220);
    private static final Color COR_DESTAQUE = new Color(0, 255, 255);

    public TelaPrincipalVendedor(Vendedor vendedor) {
        this.vendedorLogado = vendedor;

        if (vendedor.getNomeLoja() == null || vendedor.getNomeLoja().isEmpty()){
            VendedorDAO dao = new VendedorDAO();
            Vendedor temp = dao.buscarVendedorPorId(vendedor.getId());
            if (temp != null) this.vendedorLogado.setNomeLoja(temp.getNomeLoja());
        }
        setTitle("PixelHaus | Painel do Vendedor - " + this.vendedorLogado.getNomeLoja());

        setSize(1280, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(COR_FUNDO);
        setLayout(new BorderLayout(10, 10));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(10, 10, 10, 10));
        add(createHeaderPanel(), BorderLayout.NORTH);
        add(createMainContentPanel(), BorderLayout.CENTER);
        add(createFooterPanel(), BorderLayout.SOUTH);
    }

    private JPanel createHeaderPanel() {
        JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        headerPanel.setBackground(COR_FUNDO);
        JLabel titleLabel = new JLabel("PixelHaus");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titleLabel.setForeground(COR_TEXTO);
        headerPanel.add(titleLabel);
        return headerPanel;
    }

    private JPanel createMainContentPanel() {
        JPanel mainPanel = new JPanel(new GridLayout(1, 1, 10, 0));
        mainPanel.setBackground(COR_FUNDO);
        mainPanel.add(createGameCatalogPanel());
        return mainPanel;
    }

    private JPanel createGameCatalogPanel() {
        JPanel catalogPanel = new JPanel(new BorderLayout());
        catalogPanel.setBackground(COR_PAINEL_FUNDO);
        catalogPanel.setBorder(new LineBorder(COR_DESTAQUE, 1));

        JLabel titleLabel = new JLabel("Bem-vindo(a), " + vendedorLogado.getNomeLoja() + "!");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        titleLabel.setForeground(COR_TEXTO);
        titleLabel.setBorder(new EmptyBorder(10, 10, 10, 10));
        catalogPanel.add(titleLabel, BorderLayout.NORTH);

        this.gamesGridPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 15));
        this.gamesGridPanel.setBackground(COR_PAINEL_FUNDO);

        JScrollPane scrollPane = new JScrollPane(this.gamesGridPanel);
        scrollPane.getViewport().setBackground(COR_PAINEL_FUNDO);
        scrollPane.setBorder(null);
        catalogPanel.add(scrollPane, BorderLayout.CENTER);

        return catalogPanel;
    }

    public void atualizarCatalogo(java.util.List<model.bean.Jogo> jogos) {
        gamesGridPanel.removeAll();
        for (model.bean.Jogo jogo : jogos) {
            JPanel cardDoJogo = createGameItemPanel(
                    jogo.getNome(),
                    String.valueOf(jogo.getTotalDownloads()),
                    String.format("$%.2f USD", jogo.getPreco()),
                    jogo.getNomeArquivoImagem(),
                    jogo.getDescricao()
            );
            gamesGridPanel.add(cardDoJogo);
        }
        gamesGridPanel.revalidate();
        gamesGridPanel.repaint();
    }

    private JPanel createGameItemPanel(String title, String downloads, String price, String nomeImagem, String descricao) {
        JPanel itemPanel = new JPanel();
        itemPanel.setLayout(new BoxLayout(itemPanel, BoxLayout.Y_AXIS));
        itemPanel.setBackground(COR_FUNDO);
        itemPanel.setBorder(new LineBorder(COR_DESTAQUE, 1));
        itemPanel.setPreferredSize(new Dimension(200, 320));

        JLabel imageLabel;
        if (nomeImagem != null && !nomeImagem.isEmpty()) {

            // --- MODIFICAÇÃO COMEÇA AQUI ---

            // 1. Crie um caminho de arquivo relativo à raiz do projeto
            String imagePath = "resources/" + nomeImagem;

            // 2. Verifique se o arquivo existe nesse caminho
            java.io.File imageFile = new java.io.File(imagePath);

            if (imageFile.exists()) {
                // 3. Carregue a imagem diretamente pelo seu caminho (path)
                ImageIcon originalIcon = new ImageIcon(imagePath);
                Image originalImage = originalIcon.getImage();

                // Seu código de redimensionamento (está correto)
                int targetWidth = 180;
                int targetHeight = 120;
                int imageWidth = originalImage.getWidth(null);
                int imageHeight = originalImage.getHeight(null);
                double ratio = (double) imageWidth / imageHeight;

                if (imageWidth > imageHeight) {
                    targetHeight = (int) (targetWidth / ratio);
                } else {
                    targetWidth = (int) (targetHeight * ratio);
                }

                Image resizedImage = originalImage.getScaledInstance(targetWidth, targetHeight, Image.SCALE_SMOOTH);
                imageLabel = new JLabel(new ImageIcon(resizedImage));
            } else {
                // Mensagem de erro mais útil
                imageLabel = new JLabel("<html>Imagem não encontrada:<br>" + imagePath + "</html>");
                imageLabel.setForeground(Color.RED);
            }
            // --- MODIFICAÇÃO TERMINA AQUI ---

        } else {
            imageLabel = new JLabel("IMAGEM DO JOGO");
            imageLabel.setForeground(Color.GRAY);
        }

        imageLabel.setPreferredSize(new Dimension(180, 120));
        imageLabel.setMinimumSize(new Dimension(180, 120));
        imageLabel.setMaximumSize(new Dimension(180, 120));
        imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        titleLabel.setForeground(COR_TEXTO);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setBorder(new EmptyBorder(5, 5, 0, 5));

        JTextArea descriptionArea = new JTextArea(descricao);
        descriptionArea.setFont(new Font("Segoe UI", Font.PLAIN, 10));
        descriptionArea.setForeground(COR_TEXTO);
        descriptionArea.setBackground(COR_FUNDO);
        descriptionArea.setWrapStyleWord(true);
        descriptionArea.setLineWrap(true);
        descriptionArea.setEditable(false);
        descriptionArea.setFocusable(false);
        descriptionArea.setBorder(new EmptyBorder(0, 5, 0, 5));

        JScrollPane descriptionScrollPane = new JScrollPane(descriptionArea);
        descriptionScrollPane.setPreferredSize(new Dimension(180, 60));
        descriptionScrollPane.setMinimumSize(new Dimension(180, 60));
        descriptionScrollPane.setMaximumSize(new Dimension(180, 60));
        descriptionScrollPane.setBorder(null);
        descriptionScrollPane.getViewport().setBackground(COR_FUNDO);
        descriptionScrollPane.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel downloadsLabel = new JLabel("Downloads: " + downloads);
        downloadsLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        downloadsLabel.setForeground(COR_TEXTO);
        downloadsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel priceLabel = new JLabel("Price: " + price);
        priceLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        priceLabel.setForeground(COR_TEXTO);
        priceLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        itemPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        itemPanel.add(imageLabel);
        itemPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        itemPanel.add(titleLabel);
        itemPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        itemPanel.add(descriptionScrollPane);
        itemPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        itemPanel.add(downloadsLabel);
        itemPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        itemPanel.add(priceLabel);
        itemPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        return itemPanel;
    }

    private JPanel createFooterPanel() {
        JPanel footerPanel = new JPanel(new BorderLayout());
        footerPanel.setBackground(COR_FUNDO);

        JPopupMenu popupMenu = new JPopupMenu();
        JMenuItem itemAnalytics = new JMenuItem("Game Analytics");
        JMenuItem itemSettings = new JMenuItem("Account Settings");

        itemAnalytics.addActionListener(e -> {
            JogoDAO jogoDAO = new JogoDAO();
            List<model.bean.Jogo> jogosDoVendedor = jogoDAO.listarPorVendedor(vendedorLogado.getId());
            new TelaAnalyticsJogos(jogosDoVendedor).setVisible(true);
        });

        itemSettings.addActionListener(e -> {
            VendedorDAO vendedorDAO = new VendedorDAO();
            Vendedor vendedorCompleto = vendedorDAO.buscarVendedorPorId(vendedorLogado.getId());
            if (vendedorCompleto != null) {
                new TelaConfiguracoesVendedor(vendedorCompleto).setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "Não foi possível carregar os dados do usuário.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        popupMenu.add(itemAnalytics);
        popupMenu.add(itemSettings);
        popupMenu.addSeparator();

        JMenuItem itemLogout = new JMenuItem("Log Out");
        itemLogout.addActionListener(e -> {
            int resposta = JOptionPane.showConfirmDialog(
                    this,
                    "Deseja mesmo sair?",
                    "Confirmação de Log Out",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE
            );
            if (resposta == JOptionPane.YES_OPTION) {
                new TelaLogin().setVisible(true);
                this.dispose();
            }
        });
        popupMenu.add(itemLogout);

        JButton menuButton = new JButton("MENU");
        menuButton.setBackground(COR_PAINEL_FUNDO);
        menuButton.setForeground(COR_TEXTO);
        menuButton.setBorder(new LineBorder(COR_DESTAQUE, 1));
        menuButton.setFocusPainted(false);

        menuButton.addActionListener(e -> {
            popupMenu.show(menuButton, 0, -popupMenu.getPreferredSize().height);
        });
        footerPanel.add(menuButton, BorderLayout.WEST);

        JLabel statusLabel = new JLabel("SERVER STATUS: ONLINE");
        statusLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        statusLabel.setForeground(COR_DESTAQUE);
        statusLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        footerPanel.add(statusLabel, BorderLayout.EAST);

        return footerPanel;
    }
}