package src.Shapes;

import java.awt.*;

public abstract class Shape {
  protected Vector2D position;
  protected Vector2D bottomRight;
  // velocity
  protected Vector2D speed;
  // size
  protected int size;
  protected Color colour;

  protected int maxX, maxY;

  public abstract void updatePosition();

  public abstract void draw(Graphics2D g2d);
}
