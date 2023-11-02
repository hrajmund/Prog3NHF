import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class BoardBlock extends JLabel {
    private String state;

    BoardBlock(String stat) {
        state = stat;
        setPreferredSize(new Dimension(10, 10)); // Beállítjuk a preferált méretet
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Rajzoljuk meg a lekerekített fehér négyzetet fekete szegéllyel
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.WHITE);
        g2d.fillRoundRect(5, 5, getWidth() - 100, getHeight() - 10, 20, 20); // Lekerekített fehér négyzet
        g2d.setColor(Color.BLACK);
        g2d.drawRoundRect(5, 5, getWidth() - 100, getHeight() - 10, 20, 20); // Fekete szegély
    }
}
