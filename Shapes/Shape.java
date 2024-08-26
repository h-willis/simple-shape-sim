package Shapes;

import java.awt.*;

public abstract class Shape {
  protected int xPos = 0;
  protected int yPos = 0;
  // velocity
  protected int xSpeed;
  protected int ySpeed;
  // size
  protected int size;
  protected Color colour;

  protected int maxX, maxY;

  public abstract void updatePosition();

  public abstract void draw(Graphics2D g2d);
}
