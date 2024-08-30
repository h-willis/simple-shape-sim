package src.Shapes;

import java.awt.*;

public class Ball extends Shape {
  private Vector2D sizeVec;
  // midpoints at halfway points of each axis for collisions
  public Vector2D mpLeft;
  public Vector2D mpRight;
  public Vector2D mpTop;
  public Vector2D mpBottom;

  public Ball(Vector2D position, Vector2D speed, int size, Color colour) {
    this.position = position;
    this.speed = speed;
    this.size = size;
    this.sizeVec = new Vector2D(this.size, this.size);
    this.colour = colour;
    this.bottomRight = this.position.add(this.sizeVec);

    this.mpLeft = this.position.add(new Vector2D(this.size / 2, 0));
    this.mpTop = this.position.add(new Vector2D(0, this.size / 2));
    this.mpRight = this.bottomRight.subtract(new Vector2D(0, this.size / 2));
    this.mpBottom = this.bottomRight.subtract(new Vector2D(this.size / 2, 0));
  }

  public void setMaxPosition(int maxX, int maxY) {
    this.maxX = maxX;
    this.maxY = maxY;
  }

  private void setPositions(Vector2D pos) {
    this.position = pos;
    this.bottomRight = this.position.add(this.sizeVec);
    this.mpLeft = this.position.add(new Vector2D(this.size / 2, 0));
    this.mpTop = this.position.add(new Vector2D(0, this.size / 2));
    this.mpRight = this.bottomRight.subtract(new Vector2D(0, this.size / 2));
    this.mpBottom = this.bottomRight.subtract(new Vector2D(this.size / 2, 0));
  }

  public void updatePosition() {
    this.setPositions(this.position.add(this.speed));

    CollisionsManager.manageWallCollisions(this);
  }

  public void draw(Graphics2D g2d) {
    g2d.setColor(this.colour);
    g2d.fillOval(this.position.x, this.position.y, this.size, this.size);
  }
}