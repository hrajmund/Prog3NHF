import javax.swing.border.Border;
import java.awt.*;


public class RounderBorder implements Border {
    private int radius;

    public RounderBorder(int radius){
        this.radius = radius;
    }

    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        g.drawRoundRect(x, y, width-1, height-1, radius, radius);
    }

    public Insets getBorderInsets(Component c){
        return new Insets(radius, radius, radius, radius);
    }

    public boolean isBorderOpaque(){
        return true;
    }
}