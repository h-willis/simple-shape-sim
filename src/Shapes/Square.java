package src.Shapes;

import java.awt.*; // color

public class Square extends Shape {
  private Vector2D sizeVec;

  public Square(Vector2D position, int size, Color colour) {
    this.position = position;
    this.size = size;
    this.sizeVec = new Vector2D(this.size, this.size);
    this.colour = colour;
    this.bottomRight = this.position.add(this.sizeVec);
  }

  public void updatePosition() {
    return;
  }

  public void draw(Graphics2D g2d) {
    g2d.setColor(this.colour);
    g2d.fillRect(this.position.x, this.position.y, this.size, this.size);
  }
}
