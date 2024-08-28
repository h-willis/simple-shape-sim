package src.Shapes;

import java.awt.*;
import src.Settings;

// gunna hold all the squares in the array
public class Squares {
  public int numSquaresWide;
  public int numSquaresTall;
  public Square[][] squares;

  public void initSquares() {
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
  }

  public void draw(Graphics2D g2d) {
    for (int h = 0; h < this.numSquaresTall; h++) {
      for (int w = 0; w < this.numSquaresWide; w++) {
        this.squares[w][h].draw(g2d);
      }
    }
  }
}
