package src;

import javax.swing.JPanel;
import src.Shapes.Ball;
// import src.Shapes.Shape;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class DrawingPanel extends JPanel {

    public DrawingPanel() {
        initShapes();
        setBackground(Color.LIGHT_GRAY);
    }

    private Ball blackBall;
    private Ball whiteBall;

    private void initShapes() {
        this.blackBall = new Ball(200, 200, 1, 2, 20, Color.BLACK);
        this.blackBall.setMaxPosition(400, 400);

        this.whiteBall = new Ball(200, 200, 2, 1, 20, Color.WHITE);
        this.whiteBall.setMaxPosition(400, 400);
    }

    private void drawGrid(Graphics2D g2d) {
        // int width = getWidth();
        // int height = getHeight();

        // Draw vertical lines
        g2d.drawLine(0, 0, 0, 400);
        g2d.drawLine(0, 0, 400, 0);
        g2d.drawLine(400, 0, 400, 400);
        g2d.drawLine(0, 400, 400, 400);

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        this.blackBall.draw(g2d);
        this.whiteBall.draw(g2d);
        this.drawGrid(g2d);
    }

    public void updateShapes() {
        this.blackBall.updatePosition();
        this.whiteBall.updatePosition();
    }
}
