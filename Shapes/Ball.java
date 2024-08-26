package Shapes;

import java.awt.*;

public class Ball extends Shape {
  // position
  public Ball(int xPos, int yPos, int xSpeed, int ySpeed, int size, Color colour) {
    this.xPos = xPos;
    this.yPos = yPos;
    this.xSpeed = xSpeed;
    this.ySpeed = ySpeed;
    this.size = size;
    this.colour = colour;
  }

  public void setMaxPosition(int maxX, int maxY) {
    this.maxX = maxX;
    this.maxY = maxY;
  }

  public void updatePosition() {
    this.yPos += this.ySpeed;
    if (this.yPos == this.maxY) {
      this.ySpeed = -this.ySpeed;
    }

    this.xPos += this.xSpeed;
    if (this.xPos == this.maxY) {
      this.xSpeed = -this.xSpeed;
    }
  }

  public void draw(Graphics2D g2d) {
    g2d.setColor(this.colour);
    g2d.drawOval(this.xPos, this.yPos, this.size, this.size);
  }
}