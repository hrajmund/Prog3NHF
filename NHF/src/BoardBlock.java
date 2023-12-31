import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class BoardBlock extends JLabel {
    private String state;
    private Graphics g;
    private int row;
    private int column;

    BoardBlock(String stat) {
        state = stat;
        setPreferredSize(new Dimension(10, 10));
    }
    public void setRow(int row) { this.row = row; }
    public void setColumn(int column) { this.column = column; }

    public int getRow(){ return row; }
    public int getColumn() { return column; }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        switch(state){
            case "E" :{
                g2d.setColor(Color.WHITE);
                g2d.fillRoundRect(5, 5, getWidth() - 100, getHeight() - 10, 20, 20);
                g2d.setColor(Color.BLACK);
                g2d.drawRoundRect(5, 5, getWidth() - 100, getHeight() - 10, 20, 20);
                break;
            }
            case "B":{
                g2d.setColor(Color.BLACK);
                g2d.fillRoundRect(5, 5, getWidth() - 100, getHeight() - 10, 20, 20);
                g2d.setColor(Color.WHITE);
                g2d.drawRoundRect(5, 5, getWidth() - 100, getHeight() - 10, 20, 20);
                break;
            }
            case "P1":{
                g2d.setColor(Color.WHITE);
                g2d.fillRoundRect(5, 5, getWidth() - 100, getHeight() - 10, 20, 20);
                g2d.setColor(Color.BLACK);
                g2d.drawRoundRect(5, 5, getWidth() - 100, getHeight() - 10, 20, 20);

                int rectWidth = getWidth() - 100;
                int rectHeight = getHeight() - 10;
                int centerX = 5 + rectWidth / 2;
                int centerY = 5 + rectHeight / 2;

                int triangleSize = Math.min(rectWidth, rectHeight) / 4;

                int[] xPoints = {centerX, centerX - triangleSize, centerX + triangleSize};
                int[] yPoints = {centerY - triangleSize, centerY + triangleSize, centerY + triangleSize};
                int nPoints = 3;

                g2d.setColor(Color.WHITE);
                g2d.fillPolygon(xPoints, yPoints, nPoints);
                g2d.setColor(Color.BLACK);
                g2d.drawPolygon(xPoints, yPoints, nPoints);
                break;
            }
            case "P2":{
                g2d.setColor(Color.WHITE);
                g2d.fillRoundRect(5, 5, getWidth() - 100, getHeight() - 10, 20, 20);
                g2d.setColor(Color.BLACK);
                g2d.drawRoundRect(5, 5, getWidth() - 100, getHeight() - 10, 20, 20);

                int rectWidth = getWidth() - 100;
                int rectHeight = getHeight() - 10;
                int centerX = 5 + rectWidth / 2;
                int centerY = 5 + rectHeight / 2;

                int triangleSize = Math.min(rectWidth, rectHeight) / 4;

                int[] xPoints = {centerX, centerX - triangleSize, centerX + triangleSize};
                int[] yPoints = {centerY - triangleSize, centerY + triangleSize, centerY + triangleSize};
                int nPoints = 3;

                g2d.setColor(Color.BLACK);
                g2d.fillPolygon(xPoints, yPoints, nPoints);
                g2d.setColor(Color.WHITE);
                g2d.drawPolygon(xPoints, yPoints, nPoints);
                break;
            }
        }
    }

    public String getState(){ return state; }
    public void setState(String state){
        this.state = state;
        this.repaint();
    }
}
