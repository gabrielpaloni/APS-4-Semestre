package view;

import model.bean.Usuario;
import model.bean.Jogo;
import controller.LojaController;

import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class TelaPrincipalUsuario extends JFrame {

    private Usuario usuarioLogado;
    private JPanel gamePanel;
    private JTextField searchField;
    private LojaController controller;
    private final String SEARCH_PLACEHOLDER = "Pesquisar jogo...";

    public TelaPrincipalUsuario(Usuario usuario) {
        this.usuarioLogado = usuario;
        this.controller = new LojaController();

        configurarJanela();
        configurarCabecalho();
        carregarJogos();
    }

    private void configurarJanela() {
        setTitle("PixelHaus - Loja");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setUndecorated(true);

        GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice device = env.getDefaultScreenDevice();

        if (device.isFullScreenSupported()) {
            device.setFullScreenWindow(this);
        } else {
            System.err.println("Modo de tela cheia não suportado pelo dispositivo.");
            setExtendedState(JFrame.MAXIMIZED_BOTH);
            setSize(Toolkit.getDefaultToolkit().getScreenSize());
        }
        setLayout(new BorderLayout());
    }

    private void configurarCabecalho() {
        JPanel topPanel = new JPanel(new BorderLayout(10, 0));
        topPanel.setBackground(new Color(30, 30, 40));
        topPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        JLabel lblBemVindo = new JLabel("Bem-vindo(a), " + usuarioLogado.getNome());
        lblBemVindo.setForeground(Color.WHITE);
        lblBemVindo.setFont(new Font("Segoe UI", Font.BOLD, 14));
        topPanel.add(lblBemVindo, BorderLayout.WEST);

        searchField = new JTextField(SEARCH_PLACEHOLDER);
        searchField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        searchField.setForeground(Color.GRAY);

        searchField.addActionListener(e -> buscarJogos());

        searchField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (searchField.getText().equals(SEARCH_PLACEHOLDER)) {
                    searchField.setText("");
                    searchField.setForeground(Color.WHITE);
                    searchField.setBackground(new Color(50, 50, 60));
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (searchField.getText().isEmpty()) {
                    searchField.setText(SEARCH_PLACEHOLDER);
                    searchField.setForeground(Color.GRAY);
                    searchField.setBackground(new Color(30, 30, 40));
                }
            }
        });

        searchField.setBackground(new Color(30, 30, 40));
        searchField.setCaretColor(Color.WHITE);
        searchField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(80, 80, 80)),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));

        topPanel.add(searchField, BorderLayout.CENTER);

        JButton exitButton = new JButton("Sair (Logout)");
        exitButton.setBackground(new Color(200, 50, 50));
        exitButton.setForeground(Color.white);
        exitButton.setFocusPainted(false);
        exitButton.addActionListener(e -> {
            this.dispose();
            new TelaLogin().setVisible(true);
        });
        topPanel.add(exitButton, BorderLayout.EAST);

        add(topPanel, BorderLayout.NORTH);

        gamePanel = new JPanel(new GridLayout(0, 5, 20, 20));
        gamePanel.setBackground(new Color(18, 18, 18));
        gamePanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JScrollPane scroll = new JScrollPane(gamePanel);
        scroll.setBorder(null);
        scroll.getVerticalScrollBar().setUnitIncrement(16);
        add(scroll, BorderLayout.CENTER);
    }

    private void buscarJogos() {
        String termoBusca = searchField.getText().trim();
        List<Jogo> jogosFiltrados;

        if (termoBusca.isEmpty() || termoBusca.equals(SEARCH_PLACEHOLDER)) {
            jogosFiltrados = controller.listarJogos();
        } else {
            jogosFiltrados = controller.buscarJogos(termoBusca);
        }
        atualizarExibicaoJogos(jogosFiltrados);
    }

    private void carregarJogos() {
        List<Jogo> jogos = controller.listarJogos();
        atualizarExibicaoJogos(jogos);
    }

    private void atualizarExibicaoJogos(List<Jogo> jogos) {
        gamePanel.removeAll();

        if (jogos.isEmpty()) {
            JPanel infoWrapper = new JPanel();
            infoWrapper.setOpaque(false);

            JLabel noResults = new JLabel("Nenhum jogo encontrado para esta busca.");
            noResults.setForeground(Color.WHITE);
            noResults.setFont(new Font("Segoe UI", Font.BOLD, 18));
            infoWrapper.add(noResults);

            gamePanel.add(infoWrapper);

        } else {
            for (Jogo g : jogos) {
                JPanel card = criarCard(g);
                gamePanel.add(card);
            }
        }

        gamePanel.revalidate();
        gamePanel.repaint();
    }

    private JPanel criarCard(Jogo jogo) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(31, 31, 31));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        ImageIcon icon = null;
        try {
            java.net.URL location = getClass().getClassLoader().getResource(jogo.getNomeArquivoImagem());

            if (location != null) {
                icon = new ImageIcon(location);
            } else {
                System.err.println("Imagem não encontrada na pasta 'resources': " + jogo.getNomeArquivoImagem());
                icon = new ImageIcon();
            }
        } catch (Exception e) {
            e.printStackTrace();
            icon = new ImageIcon();
        }
        Image img = icon.getImage().getScaledInstance(300, 200, Image.SCALE_SMOOTH);
        JLabel imgLabel = new JLabel(new ImageIcon(img));
        imgLabel.setHorizontalAlignment(JLabel.CENTER);
        panel.add(imgLabel, BorderLayout.NORTH);

        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setOpaque(false);
        infoPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));

        JLabel nameLabel = new JLabel(jogo.getNome());
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        nameLabel.setAlignmentX(LEFT_ALIGNMENT);

        JLabel descLabel = new JLabel("<html><p style='width:180px;color:gray;font-size: 10px;'>" + jogo.getDescricao() + "</p></html>");
        descLabel.setAlignmentX(LEFT_ALIGNMENT);

        JLabel priceLabel = new JLabel("R$ " + String.format("%.2f", jogo.getPreco()));
        priceLabel.setForeground(new Color(0, 180, 255));
        priceLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        priceLabel.setAlignmentX(LEFT_ALIGNMENT);

        JLabel downloadsLabel = new JLabel("Downloads: " + jogo.getTotalDownloads());
        downloadsLabel.setForeground(Color.LIGHT_GRAY);
        downloadsLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        downloadsLabel.setAlignmentX(LEFT_ALIGNMENT);

        JButton downloadButton = new JButton("Download");
        downloadButton.setBackground(new Color(0, 122, 255));
        downloadButton.setForeground(Color.WHITE);
        downloadButton.setFocusPainted(false);
        downloadButton.setAlignmentX(LEFT_ALIGNMENT);
        downloadButton.setMaximumSize(new java.awt.Dimension(Integer.MAX_VALUE, downloadButton.getMinimumSize().height));

        downloadButton.addActionListener(e -> {
            controller.registrarDownload(jogo);

            downloadsLabel.setText("Downloads: " + jogo.getTotalDownloads());
            JOptionPane.showMessageDialog(this,
                    "Download iniciado para: " + jogo.getNome() + "\n\n" +
                            "Total de downloads atualizado: " + jogo.getTotalDownloads(),
                    "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        });

        infoPanel.add(nameLabel);
        infoPanel.add(descLabel);
        infoPanel.add(Box.createVerticalStrut(5));
        infoPanel.add(priceLabel);
        infoPanel.add(Box.createVerticalStrut(10));
        infoPanel.add(downloadsLabel);
        infoPanel.add(Box.createVerticalStrut(10));
        infoPanel.add(downloadButton);

        panel.add(infoPanel, BorderLayout.SOUTH);
        return panel;
    }
}