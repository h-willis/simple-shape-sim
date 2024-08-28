package src.Shapes;

import java.awt.*;

public class Ball extends Shape {
  private Vector2D sizeVec;

  public Ball(Vector2D position, Vector2D speed, int size, Color colour) {
    this.position = position;
    this.speed = speed;
    this.size = size;
    this.sizeVec = new Vector2D(this.size, this.size);
    this.colour = colour;
    this.bottomRight = this.position.add(this.sizeVec);
  }

  public void setMaxPosition(int maxX, int maxY) {
    this.maxX = maxX;
    this.maxY = maxY;
  }

  private void setPosition(Vector2D pos) {
    this.position = pos;
    this.bottomRight = this.position.add(this.sizeVec);
  }

  public void updatePosition() {
    this.setPosition(this.position.add(this.speed));

    CollisionsManager.manageWallCollisions(this);
  }

  public void draw(Graphics2D g2d) {
    g2d.setColor(this.colour);
    g2d.fillOval(this.position.x, this.position.y, this.size, this.size);
  }
}