package src;

import javax.swing.JPanel;
import src.Shapes.Ball;
// import src.Shapes.Shape;
import src.Shapes.CollisionsManager;
import src.Shapes.Square;
import src.Shapes.Vector2D;

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
    private boolean squaresDrawn = false;
    private int numSquaresWide;
    private int numSquaresTall;
    private Square[][] squares;
    private CollisionsManager collisionsHandler = new CollisionsManager();

    private void initShapes() {
        if (Settings.width % Settings.shapeScale != 0 || Settings.height % Settings.shapeScale != 0) {
            System.err.println("Error: Canvas width and height must be multple of shapeScale");
            System.exit(1);
        }

        this.numSquaresWide = Settings.width / Settings.shapeScale;
        this.numSquaresTall = Settings.height / Settings.shapeScale;
        squares = new Square[this.numSquaresWide][this.numSquaresTall];
        for (int h = 0; h < this.numSquaresTall; h++) {
            for (int w = 0; w < this.numSquaresWide; w++) {
                Color sqColour;
                if (h > numSquaresWide - 1 - w) {
                    sqColour = Color.BLACK;
                } else {
                    sqColour = Color.WHITE;
                }

                squares[w][h] = new Square(new Vector2D(w * Settings.shapeScale, h * Settings.shapeScale),
                        Settings.shapeScale, sqColour);
            }
        }

        this.blackBall = new Ball(new Vector2D(200, 200), new Vector2D(1, 2), 20, Color.BLACK);
        this.blackBall.setMaxPosition(Settings.width, Settings.height);

        this.whiteBall = new Ball(new Vector2D(250, 100), new Vector2D(2, 1), 20, Color.WHITE);
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

        for (int h = 0; h < this.numSquaresTall; h++) {
            for (int w = 0; w < this.numSquaresWide; w++) {
                this.squares[w][h].draw(g2d);
            }
        }

        this.blackBall.draw(g2d);
        this.whiteBall.draw(g2d);
        this.drawGrid(g2d);
    }

    public void updateShapes() {
        this.blackBall.updatePosition();
        this.whiteBall.updatePosition();
        // CollisionsManager.manageBallSquareCollisions(this.blackBall, this.whiteBall,
        // this.squares);
    }
}
