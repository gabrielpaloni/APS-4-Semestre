import view.TelaLogin;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import java.awt.Color;

public class Main {

    public static void main(String[] args) {

        try {
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");

            Color corFundoPainel = new Color(27, 34, 52);
            Color corTexto = new Color(220, 220, 220);
            Color corDestaque = new Color(0, 255, 255);
            Color corSelecaoFundo = new Color(75, 110, 175);

            UIManager.put("PopupMenu.background", corFundoPainel);
            UIManager.put("PopupMenu.border", new LineBorder(corDestaque, 1));

            UIManager.put("MenuItem.background", corFundoPainel);
            UIManager.put("MenuItem.foreground", corTexto);

            UIManager.put("MenuItem.selectionBackground", corSelecaoFundo);
            UIManager.put("MenuItem.selectionForeground", corDestaque);

            UIManager.put("Separator.background", corDestaque);
            UIManager.put("Separator.foreground", corDestaque);

        } catch (Exception e) {
            e.printStackTrace();
        }

        java.awt.EventQueue.invokeLater(() -> {
            new TelaLogin().setVisible(true);
        });
    }
}