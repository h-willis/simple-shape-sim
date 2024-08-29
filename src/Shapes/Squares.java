package src.Shapes;

import java.awt.*;
import java.util.Iterator;
import java.util.NoSuchElementException;
import src.Settings;

// gunna hold all the squares in the array
public class Squares implements Iterable<Square> {
  public int numSquaresWide;
  public int numSquaresTall;
  // TODO make an iterator function to go through all squares
  // ... except java doesn't have native generator support
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
    // can't use iterator here because it iterates on the class itself
    for (int h = 0; h < this.numSquaresTall; h++) {
      for (int w = 0; w < this.numSquaresWide; w++) {
        this.squares[w][h].draw(g2d);
      }
    }
  }

  @Override
  public Iterator<Square> iterator() {
    return new SquaresIterator();
  }

  private class SquaresIterator implements Iterator<Square> {
    private int w = 0;
    private int h = 0;

    @Override
    public boolean hasNext() {
      return w < numSquaresWide && h < numSquaresTall;
    }

    @Override
    public Square next() {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }

      Square retSquare = squares[w][h];
      w += 1;
      if (w == numSquaresWide) {
        w = 0;
        h += 1;
      }
      return retSquare;
    }
  }
}
