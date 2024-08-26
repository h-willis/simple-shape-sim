package src;

import javax.swing.JPanel;
import src.Shapes.Ball;
import src.Shapes.Shape;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class DrawingPanel extends JPanel {

    public DrawingPanel() {
        initShapes();
    }

    private Shape blackBall;

    private void initShapes() {
        this.blackBall = new Ball(200, 200, 1, 0, 20, Color.BLACK);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        this.blackBall.draw(g2d);
    }

    public void updateShapes() {
        this.blackBall.updatePosition();
    }
}
