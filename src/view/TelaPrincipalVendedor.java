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
    private Image backgroundImage;
    private JLabel welcomeLabel;

    private static final Color COR_FUNDO = new Color(18, 23, 35);
    private static final Color COR_PAINEL_FUNDO = new Color(27, 34, 52);
    private static final Color COR_TEXTO = new Color(220, 220, 220);
    private static final Color COR_DESTAQUE = new Color(0, 255, 255);

    public TelaPrincipalVendedor(Vendedor vendedor) {
        this.vendedorLogado = vendedor;

        try {
            URL bgUrl = getClass().getClassLoader().getResource("hexagon_bg.png");
            if (bgUrl == null) {
                throw new RuntimeException("Imagem de fundo 'hexagon_bg.png' não encontrada!");
            }
            this.backgroundImage = new ImageIcon(bgUrl).getImage();
        } catch (Exception e) {
            e.printStackTrace();
            this.backgroundImage = null;
        }

        if (vendedor.getNomeLoja() == null || vendedor.getNomeLoja().isEmpty()) {
            VendedorDAO dao = new VendedorDAO();
            Vendedor temp = dao.buscarVendedorPorId(vendedor.getId());
            if (temp != null) this.vendedorLogado.setNomeLoja(temp.getNomeLoja());
        }
        setTitle("PixelHaus | Painel do Vendedor - " + this.vendedorLogado.getNomeLoja());

        setSize(1280, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        getContentPane().setBackground(COR_FUNDO);
        setLayout(new BorderLayout(10, 10));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(10, 10, 10, 10));
        add(createHeaderPanel(), BorderLayout.NORTH);
        add(createMainContentPanel(), BorderLayout.CENTER);
        add(createFooterPanel(), BorderLayout.SOUTH);
    }

    // --- NOVO MÉTODO: Permite que a tela de config atualize a tela principal ---
    public void atualizarInfoVendedor(Vendedor vendedor) {
        this.vendedorLogado.setNomeLoja(vendedor.getNomeLoja());
        this.vendedorLogado.setEmail(vendedor.getEmail());

        setTitle("PixelHaus | Painel do Vendedor - " + this.vendedorLogado.getNomeLoja());
        welcomeLabel.setText("Bem-vindo(a), " + vendedorLogado.getNomeLoja() + "!");
    }

    private JPanel createHeaderPanel() {
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(COR_FUNDO);
        JPanel leftPanel = new JPanel(new GridBagLayout());
        leftPanel.setBackground(COR_FUNDO);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.SOUTH;
        gbc.insets = new Insets(0, 0, 0, 5);
        leftPanel.add(createMenuButton(), gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.SOUTH;
        gbc.insets = new Insets(0, 0, 0, 0);
        JLabel titleLabel = new JLabel("PixelHaus");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titleLabel.setForeground(COR_TEXTO);
        leftPanel.add(titleLabel, gbc);
        headerPanel.add(leftPanel, BorderLayout.WEST);
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

        BackgroundPanel backgroundPanel = new BackgroundPanel(this.backgroundImage);
        backgroundPanel.setLayout(new GridBagLayout());

        this.gamesGridPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 15));
        this.gamesGridPanel.setOpaque(false);
        this.gamesGridPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.CENTER;
        backgroundPanel.add(this.gamesGridPanel, gbc);

        JScrollPane scrollPane = new JScrollPane(backgroundPanel);
        scrollPane.getViewport().setBackground(COR_PAINEL_FUNDO);
        scrollPane.setBorder(null);
        catalogPanel.add(scrollPane, BorderLayout.CENTER);

        return catalogPanel;
    }

    public void atualizarCatalogo(java.util.List<model.bean.Jogo> jogos) {
        gamesGridPanel.removeAll();
        for (model.bean.Jogo jogo : jogos) {
            JPanel cardDoJogo = createGameItemPanel(
                    jogo.getNome(), String.valueOf(jogo.getTotalDownloads()),
                    String.format("R$%.2f ", jogo.getPreco()),
                    jogo.getNomeArquivoImagem(), jogo.getDescricao()
            );
            gamesGridPanel.add(cardDoJogo);
        }
        gamesGridPanel.revalidate();
        gamesGridPanel.repaint();
    }

    private ImageIcon carregarIcone(String caminho, int largura, int altura) {
        URL url = getClass().getClassLoader().getResource(caminho);
        if (url != null) {
            ImageIcon iconeOriginal = new ImageIcon(url);
            Image imagemOriginal = iconeOriginal.getImage();
            Image imagemRedimensionada = imagemOriginal.getScaledInstance(largura, altura, Image.SCALE_SMOOTH);
            return new ImageIcon(imagemRedimensionada);
        } else {
            System.err.println("Ícone não encontrado: " + caminho);
            return null;
        }
    }

    private JPanel createGameItemPanel(String title, String downloads, String price, String nomeImagem, String descricao) {
        JPanel itemPanel = new JPanel();
        itemPanel.setLayout(new BoxLayout(itemPanel, BoxLayout.Y_AXIS));
        itemPanel.setBackground(COR_FUNDO);
        itemPanel.setOpaque(true);
        itemPanel.setBorder(new LineBorder(COR_DESTAQUE, 1));
        itemPanel.setPreferredSize(new Dimension(200, 320));

        JLabel imageLabel;
        if (nomeImagem != null && !nomeImagem.isEmpty()) {
            URL imageUrl = getClass().getClassLoader().getResource(nomeImagem);
            if (imageUrl != null) {
                ImageIcon originalIcon = new ImageIcon(imageUrl);
                Image originalImage = originalIcon.getImage();
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
                imageLabel = new JLabel("Imagem não encontrada");
                imageLabel.setForeground(Color.RED);
            }
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
        descriptionArea.setOpaque(true);
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
        descriptionScrollPane.getViewport().setOpaque(true);
        descriptionScrollPane.setOpaque(true);
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

    private JButton createMenuButton() {
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
                new TelaConfiguracoesVendedor(this, vendedorCompleto).setVisible(true);
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
                    this, "Deseja mesmo sair?", "Confirmação de Log Out",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE
            );
            if (resposta == JOptionPane.YES_OPTION) {
                new TelaLogin().setVisible(true);
                this.dispose();
            }
        });
        popupMenu.add(itemLogout);
        ImageIcon menuIcon = carregarIcone("menu_icon.png", 20, 20);
        JButton menuButton = new JButton(menuIcon);
        menuButton.setBackground(COR_FUNDO);
        menuButton.setBorder(new EmptyBorder(5, 5, 5, 5));
        menuButton.setFocusPainted(false);
        menuButton.setContentAreaFilled(false);
        menuButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        menuButton.addActionListener(e -> {
            popupMenu.show(menuButton, 0, menuButton.getHeight());
        });
        return menuButton;
    }

    private JPanel createFooterPanel() {
        JPanel footerPanel = new JPanel(new BorderLayout());
        footerPanel.setBackground(COR_FUNDO);

        welcomeLabel = new JLabel("Bem-vindo(a), " + vendedorLogado.getNomeLoja() + "!");
        welcomeLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        welcomeLabel.setForeground(COR_TEXTO);
        welcomeLabel.setBorder(new EmptyBorder(0, 5, 0, 0));
        footerPanel.add(welcomeLabel, BorderLayout.WEST);

        JLabel statusLabel = new JLabel("SERVER STATUS: ONLINE");
        statusLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        statusLabel.setForeground(COR_DESTAQUE);
        statusLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        statusLabel.setBorder(new EmptyBorder(0, 0, 0, 10));
        footerPanel.add(statusLabel, BorderLayout.EAST);
        return footerPanel;
    }
}