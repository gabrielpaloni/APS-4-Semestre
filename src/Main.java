import javax.swing.UIManager;
import view.TelaLogin;

public class Main {

    public static void main(String[] args) {

        // Opcional: Tenta aplicar um Look and Feel mais moderno (Nimbus)
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            // Se não der, usa o padrão do Java
        }

        // Inicia a primeira tela da aplicação
        java.awt.EventQueue.invokeLater(() -> {
            new TelaLogin().setVisible(true);
        });
    }
}