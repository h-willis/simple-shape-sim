package src;

import javax.swing.JPanel;
import src.Shapes.Ball;
// import src.Shapes.Shape;
import src.Shapes.CollisionsManager;
import src.Shapes.Square;
import src.Shapes.Squares;
import src.Shapes.Vector2D;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Random;

public class DrawingPanel extends JPanel {

    public DrawingPanel() {
        initShapes();
        setBackground(Color.LIGHT_GRAY);
    }

    private Ball blackBall;
    private Ball whiteBall;
    private Squares squares;

    private Vector2D getRandomSpeed() {
        Random random = new Random();
        int randSpeedx = 0;
        int randSpeedy = 0;
        do {
            // we dont want 0 speed
            randSpeedx = random.nextInt(6);
        } while (randSpeedx == 3);

        do {
            // we dont want 0 speed
            randSpeedy = random.nextInt(6);
        } while (randSpeedy == 3);

        return new Vector2D(randSpeedx - 3, randSpeedy - 3);
    }

    private void initShapes() {
        this.squares = new Squares();
        this.squares.initSquares();

        this.blackBall = new Ball(new Vector2D(100, 200), getRandomSpeed(), 10, Color.BLACK);
        this.blackBall.setMaxPosition(Settings.width, Settings.height);

        this.whiteBall = new Ball(new Vector2D(300, 200), getRandomSpeed(), 10, Color.WHITE);
        this.whiteBall.setMaxPosition(Settings.width, Settings.height);
    }

    private void drawGrid(Graphics2D g2d) {
        // int width = getWidth();
        // int height = getHeight();

        // Left
        // g2d.drawLine(0, 0, 0, Settings.height);
        // // Top
        // g2d.drawLine(0, 0, Settings.width, 0);
        // // Right
        // g2d.drawLine(Settings.width, 0, Settings.width, Settings.height);
        // // Bottom
        // g2d.drawLine(0, Settings.height, Settings.width, Settings.height);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        this.squares.draw(g2d);

        this.blackBall.draw(g2d);
        this.whiteBall.draw(g2d);
        this.drawGrid(g2d);
    }

    public void updateShapes() {
        this.blackBall.updatePosition();
        this.whiteBall.updatePosition();
        CollisionsManager.manageBallSquareCollisions(this.blackBall, this.whiteBall,
                this.squares);
    }
}
