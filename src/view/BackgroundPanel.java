package view;

import javax.swing.*;
import java.awt.*;

public class BackgroundPanel extends JPanel {

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