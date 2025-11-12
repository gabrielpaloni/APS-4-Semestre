package view;

import model.bean.Usuario;
import model.bean.Jogo;
import controller.LojaController;

import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.net.URL;
import java.util.List;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class TelaPrincipalUsuario extends JFrame {

    private Usuario usuarioLogado;
    private JPanel lojaPanel;
    private JPanel bibliotecaPanel;
    private JTextField searchField;
    private LojaController controller;
    private final String SEARCH_PLACEHOLDER = "Pesquisar jogo...";

    private Image backgroundImage;
    private CardLayout cardLayout;
    private JPanel mainContentPanel;

    private static final Color COR_FUNDO_CABECALHO = new Color(30, 30, 40);
    private static final Color COR_FUNDO_PRINCIPAL = new Color(18, 18, 18);
    private static final Color COR_TEXTO_CLARO = Color.WHITE;
    private static final Color COR_TEXTO_CINZA = Color.GRAY;
    private static final Color COR_CARD_FUNDO = new Color(31, 31, 31);
    private static final Color COR_BOTAO_COMPRAR = new Color(0, 122, 255);
    private static final Color COR_PRECO = new Color(0, 180, 255);
    private static final Color COR_BOTAO_LOGOUT = new Color(80, 80, 80);
    private static final Color COR_DESTAQUE = new Color(0, 255, 255);

    public TelaPrincipalUsuario(Usuario usuario) {
        this.usuarioLogado = usuario;
        this.controller = new LojaController();

        try {
            String projectRoot = System.getProperty("user.dir");
            String bgPath = projectRoot + java.io.File.separator + "resources" + java.io.File.separator + "user_bg.png";

            java.io.File bgFile = new java.io.File(bgPath);
            if (bgFile.exists()) {
                this.backgroundImage = new ImageIcon(bgPath).getImage();
            } else {
                System.err.println("Imagem de fundo não encontrada: " + bgPath);
                this.backgroundImage = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            this.backgroundImage = null;
        }

        configurarJanela();
        configurarCabecalho();
        configurarPaineisPrincipais();

        carregarJogosDaLoja();
        carregarJogosDaBiblioteca();
    }

    public void refreshTelas() {
        carregarJogosDaLoja();
        carregarJogosDaBiblioteca();
    }

    private void configurarJanela() {
        setTitle("PixelHaus - Loja");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1280, 720);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());

    }

    private void configurarCabecalho() {
        JPanel topPanel = new JPanel(new BorderLayout(10, 0));
        topPanel.setBackground(COR_FUNDO_CABECALHO);
        topPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        JPanel leftPanel = new JPanel(new GridBagLayout());
        leftPanel.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0; gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(0, 0, 0, 10);
        JButton menuButton = createMenuButton();
        leftPanel.add(menuButton, gbc);

        JPanel searchPanel = new JPanel(new BorderLayout(5, 0));
        searchPanel.setBackground(COR_FUNDO_CABECALHO);
        JLabel iconLabel = new JLabel();
        try {
            ImageIcon searchIcon = carregarIcone("/resources/search_icon.png", 16, 16);
            if (searchIcon != null) iconLabel.setIcon(searchIcon);
        } catch (Exception e) { e.printStackTrace(); }
        searchPanel.add(iconLabel, BorderLayout.WEST);

        searchField = new JTextField(SEARCH_PLACEHOLDER);
        searchField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        searchField.setForeground(COR_TEXTO_CINZA);
        searchField.getDocument().addDocumentListener(new DocumentListener() {
            @Override public void insertUpdate(DocumentEvent e) { buscarJogos(); }
            @Override public void removeUpdate(DocumentEvent e) { buscarJogos(); }
            @Override public void changedUpdate(DocumentEvent e) { buscarJogos(); }
        });
        searchField.addFocusListener(new FocusAdapter() {
            @Override public void focusGained(FocusEvent e) {
                if (searchField.getText().equals(SEARCH_PLACEHOLDER)) {
                    searchField.setText("");
                    searchField.setForeground(COR_TEXTO_CLARO);
                    searchField.setBackground(new Color(50, 50, 60));
                }
            }
            @Override public void focusLost(FocusEvent e) {
                if (searchField.getText().isEmpty()) {
                    searchField.setText(SEARCH_PLACEHOLDER);
                    searchField.setForeground(COR_TEXTO_CINZA);
                    searchField.setBackground(COR_FUNDO_CABECALHO);
                }
            }
        });
        searchField.setBackground(COR_FUNDO_CABECALHO);
        searchField.setCaretColor(COR_TEXTO_CLARO);
        searchField.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        searchField.setPreferredSize(new Dimension(250, 25));

        searchPanel.add(searchField, BorderLayout.CENTER);

        gbc.gridx = 1; gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(0, 0, 0, 0);
        leftPanel.add(searchPanel, gbc);

        topPanel.add(leftPanel, BorderLayout.WEST);

        JPanel rightPanel = new JPanel(new GridBagLayout());
        rightPanel.setOpaque(false);

        JLabel lblBemVindo = new JLabel("Bem-vindo(a), " + usuarioLogado.getNome());
        lblBemVindo.setForeground(COR_TEXTO_CLARO);
        lblBemVindo.setFont(new Font("Segoe UI", Font.BOLD, 14));

        rightPanel.add(lblBemVindo, new GridBagConstraints());

        topPanel.add(rightPanel, BorderLayout.EAST);

        add(topPanel, BorderLayout.NORTH);
    }

    private JButton createMenuButton() {
        JPopupMenu popupMenu = new JPopupMenu();

        JMenuItem itemLoja = new JMenuItem("Loja");
        JMenuItem itemBiblioteca = new JMenuItem("Biblioteca");

        itemLoja.addActionListener(e -> {
            if (cardLayout != null) {
                cardLayout.show(mainContentPanel, "LOJA");
            }
        });
        itemBiblioteca.addActionListener(e -> {
            if (cardLayout != null) {
                cardLayout.show(mainContentPanel, "BIBLIOTECA");
            }
        });

        popupMenu.add(itemLoja);
        popupMenu.add(itemBiblioteca);

        popupMenu.add(new JSeparator());
        JMenuItem itemLogOut = new JMenuItem("Log Out");
        itemLogOut.addActionListener(e -> {
            int resposta = JOptionPane.showConfirmDialog(
                    TelaPrincipalUsuario.this,
                    "Você tem certeza que deseja sair?",
                    "Confirmação de Log Out",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE
            );

            if (resposta == JOptionPane.YES_OPTION) {
                this.dispose();
                new TelaLogin().setVisible(true);
            }

        });

        popupMenu.add(itemLogOut);

        ImageIcon menuIcon = carregarIcone("/resources/menu_icon.png", 20, 20);
        JButton menuButton = new JButton(menuIcon);

        menuButton.setBackground(COR_FUNDO_CABECALHO);
        menuButton.setBorder(new EmptyBorder(5, 5, 5, 5));
        menuButton.setFocusPainted(false);
        menuButton.setContentAreaFilled(false);
        menuButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        menuButton.addActionListener(e -> {
            popupMenu.show(menuButton, 0, menuButton.getHeight());
        });

        return menuButton;
    }

    private void configurarPaineisPrincipais() {
        cardLayout = new CardLayout();
        mainContentPanel = new JPanel(cardLayout);

        lojaPanel = new BackgroundPanel(this.backgroundImage);
        lojaPanel.setLayout(new GridLayout(0, 5, 20, 20)); // Loja é GridLayout
        lojaPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        JScrollPane scrollLoja = new JScrollPane(lojaPanel);
        scrollLoja.setBorder(null);
        scrollLoja.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
        scrollLoja.getVerticalScrollBar().setUnitIncrement(16);
        scrollLoja.getViewport().setOpaque(false);

        bibliotecaPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
        bibliotecaPanel.setOpaque(false);

        BackgroundPanel wrapperBiblioteca = new BackgroundPanel(this.backgroundImage);
        wrapperBiblioteca.setLayout(new GridBagLayout());
        wrapperBiblioteca.add(bibliotecaPanel, new GridBagConstraints());

        JScrollPane scrollBiblioteca = new JScrollPane(wrapperBiblioteca);
        scrollBiblioteca.setBorder(null);
        scrollBiblioteca.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
        scrollBiblioteca.getVerticalScrollBar().setUnitIncrement(16);
        scrollBiblioteca.getViewport().setOpaque(false);

        mainContentPanel.add(scrollLoja, "LOJA");
        mainContentPanel.add(scrollBiblioteca, "BIBLIOTECA");

        add(mainContentPanel, BorderLayout.CENTER);
    }

    private void buscarJogos() {
        String termoBusca = searchField.getText().trim();
        List<Jogo> jogosFiltrados;
        if (termoBusca.isEmpty() || termoBusca.equals(SEARCH_PLACEHOLDER)) {
            jogosFiltrados = controller.listarJogosDaLoja(usuarioLogado.getId());
        } else {
            jogosFiltrados = controller.buscarJogos(termoBusca, usuarioLogado.getId());
        }
        atualizarExibicaoLoja(jogosFiltrados);
    }
    private void carregarJogosDaLoja() {
        List<Jogo> jogos = controller.listarJogosDaLoja(usuarioLogado.getId());
        atualizarExibicaoLoja(jogos);
    }
    private void carregarJogosDaBiblioteca() {
        List<Jogo> jogos = controller.listarJogosDaBiblioteca(usuarioLogado.getId());
        atualizarExibicaoBiblioteca(jogos);
    }

    private void atualizarExibicaoLoja(List<Jogo> jogos) {
        lojaPanel.removeAll();

        if (jogos.isEmpty()) {
            JPanel noResultsPanel = new JPanel(new GridBagLayout());
            noResultsPanel.setOpaque(false);

            JLabel noResults = new JLabel(searchField.getText().equals(SEARCH_PLACEHOLDER) ? "Todos os jogos já estão na sua biblioteca!" : "Nenhum jogo encontrado para esta busca.");
            noResults.setForeground(COR_TEXTO_CLARO);
            noResults.setFont(new Font("Segoe UI", Font.BOLD, 18));
            noResultsPanel.add(noResults);
            lojaPanel.add(noResultsPanel);

        } else {
            for (Jogo g : jogos) {
                JPanel card = criarCardLoja(g);

                JPanel cardWrapper = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
                cardWrapper.setOpaque(false);
                cardWrapper.add(card);

                lojaPanel.add(cardWrapper);
            }
        }
        lojaPanel.revalidate();
        lojaPanel.repaint();
    }

    private void atualizarExibicaoBiblioteca(List<Jogo> jogos) {
        bibliotecaPanel.removeAll();
        if (jogos.isEmpty()) {
            JLabel noResults = new JLabel("Sua biblioteca está vazia. Visite a loja para comprar novos jogos!");
            noResults.setForeground(COR_TEXTO_CLARO); noResults.setFont(new Font("Segoe UI", Font.BOLD, 18));
            bibliotecaPanel.add(noResults);
        } else {
            for (Jogo g : jogos) {
                JPanel card = criarCardBiblioteca(g);
                bibliotecaPanel.add(card);
            }
        }
        bibliotecaPanel.revalidate(); bibliotecaPanel.repaint();
    }

    private ImageIcon carregarIcone(String caminho, int largura, int altura) {
        if (caminho.startsWith("/")) {
            caminho = caminho.substring(1);
        }

        String projectRoot = System.getProperty("user.dir");
        String absolutePath = projectRoot + java.io.File.separator + caminho;

        java.io.File imageFile = new java.io.File(absolutePath);

        if (imageFile.exists()) {
            ImageIcon iconeOriginal = new ImageIcon(absolutePath);
            Image imagemOriginal = iconeOriginal.getImage();
            Image imagemRedimensionada = imagemOriginal.getScaledInstance(largura, altura, Image.SCALE_SMOOTH);
            return new ImageIcon(imagemRedimensionada);
        } else {
            System.err.println("Ícone não encontrado: " + absolutePath);
            return null;
        }
    }

    private JPanel criarCardLoja(Jogo jogo) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(COR_CARD_FUNDO);
        panel.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(COR_DESTAQUE, 1),
                new EmptyBorder(10, 10, 10, 10)
        ));

        panel.setPreferredSize(new Dimension(200, 270));

        Image img = null;
        try {
            String projectRoot = System.getProperty("user.dir");
            String absolutePath = projectRoot + java.io.File.separator + "resources" + java.io.File.separator + jogo.getNomeArquivoImagem();

            java.io.File imageFile = new java.io.File(absolutePath);

            if (imageFile.exists() && !jogo.getNomeArquivoImagem().isEmpty()) {
                img = new ImageIcon(absolutePath).getImage().getScaledInstance(180, 120, Image.SCALE_SMOOTH);
            } else {
                System.err.println("Imagem não encontrada: " + absolutePath);
            }
        } catch (Exception e) { e.printStackTrace(); }

        JLabel imgLabel;
        if (img != null) {
            imgLabel = new JLabel(new ImageIcon(img));
        } else {
            imgLabel = new JLabel("Imagem não disponível");
            imgLabel.setPreferredSize(new Dimension(180, 120));
            imgLabel.setHorizontalAlignment(JLabel.CENTER);
            imgLabel.setForeground(Color.GRAY);
            imgLabel.setOpaque(true);
            imgLabel.setBackground(Color.DARK_GRAY);
        }
        imgLabel.setHorizontalAlignment(JLabel.CENTER);
        panel.add(imgLabel, BorderLayout.NORTH);

        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setOpaque(false);
        infoPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));

        JLabel nameLabel = new JLabel(jogo.getNome());
        nameLabel.setForeground(COR_TEXTO_CLARO);
        nameLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel priceLabel = new JLabel("Preço: R$" + String.format("%,.2f", jogo.getPreco()));
        priceLabel.setForeground(COR_PRECO);
        priceLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        priceLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton comprarButton = new JButton("Comprar");
        comprarButton.setBackground(COR_BOTAO_COMPRAR);
        comprarButton.setForeground(Color.WHITE);
        comprarButton.setFocusPainted(false);
        comprarButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        comprarButton.setMaximumSize(new java.awt.Dimension(Integer.MAX_VALUE, comprarButton.getMinimumSize().height));

        comprarButton.addActionListener(e -> {
            new TelaCompra(this, jogo, usuarioLogado, controller).setVisible(true);
        });

        infoPanel.add(nameLabel);

        infoPanel.add(Box.createVerticalGlue());
        infoPanel.add(priceLabel);
        infoPanel.add(Box.createVerticalStrut(10));
        infoPanel.add(comprarButton);

        panel.add(infoPanel, BorderLayout.SOUTH);
        return panel;
    }

    private JPanel criarCardBiblioteca(Jogo jogo) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(COR_CARD_FUNDO);
        panel.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(COR_DESTAQUE, 1),
                new EmptyBorder(10, 10, 10, 10)
        ));
        panel.setPreferredSize(new Dimension(250, 320));

        Image img = null;
        try {
            String projectRoot = System.getProperty("user.dir");
            String absolutePath = projectRoot + java.io.File.separator + "resources" + java.io.File.separator + jogo.getNomeArquivoImagem();

            java.io.File imageFile = new java.io.File(absolutePath);

            if (imageFile.exists() && !jogo.getNomeArquivoImagem().isEmpty()) {
                img = new ImageIcon(absolutePath).getImage().getScaledInstance(180, 120, Image.SCALE_SMOOTH);
            } else {
                System.err.println("Imagem não encontrada: " + absolutePath);
            }
        } catch (Exception e) { e.printStackTrace(); }

        JLabel imgLabel;
        if (img != null) {
            imgLabel = new JLabel(new ImageIcon(img));
        } else {
            imgLabel = new JLabel("Imagem não disponível");
            imgLabel.setPreferredSize(new Dimension(180, 120));
            imgLabel.setHorizontalAlignment(JLabel.CENTER);
            imgLabel.setForeground(Color.GRAY);
            imgLabel.setOpaque(true);
            imgLabel.setBackground(Color.DARK_GRAY);
        }
        imgLabel.setHorizontalAlignment(JLabel.CENTER);
        panel.add(imgLabel, BorderLayout.NORTH);

        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setOpaque(false);
        infoPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));

        JLabel nameLabel = new JLabel(jogo.getNome());
        nameLabel.setForeground(COR_TEXTO_CLARO);
        nameLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        String descHtml = "<html><p style='width:180px;text-align:center;color:gray;font-size: 10px;'>" + jogo.getDescricao() + "</p></html>";
        JLabel descLabel = new JLabel(descHtml);
        descLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel downloadsLabel = new JLabel("Downloads: " + jogo.getTotalDownloads());
        downloadsLabel.setForeground(Color.LIGHT_GRAY);
        downloadsLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        downloadsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel priceLabel = new JLabel("Preço: R$" + String.format("%,.2f", jogo.getPreco()));
        priceLabel.setForeground(COR_PRECO);
        priceLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        priceLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        infoPanel.add(nameLabel);
        infoPanel.add(Box.createVerticalStrut(5));
        infoPanel.add(descLabel);
        infoPanel.add(Box.createVerticalGlue());
        infoPanel.add(downloadsLabel);
        infoPanel.add(Box.createVerticalStrut(5));
        infoPanel.add(priceLabel);
        infoPanel.add(Box.createVerticalStrut(10));

        panel.add(infoPanel, BorderLayout.SOUTH);
        return panel;
    }

    class BackgroundPanel extends JPanel {
        private Image backgroundImage;
        public BackgroundPanel(Image backgroundImage) {
            this.backgroundImage = backgroundImage;
        }
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (backgroundImage != null) {
                int imgWidth = backgroundImage.getWidth(this);
                int imgHeight = backgroundImage.getHeight(this);
                if (imgWidth <= 0 || imgHeight <= 0) return;

                for (int y = 0; y < getHeight(); y += imgHeight) {
                    for (int x = 0; x < getWidth(); x += imgWidth) {
                        g.drawImage(backgroundImage, x, y, this);
                    }
                }
            }
        }
    }
} 