package src.Shapes;

import src.Settings;

public class CollisionsManager {
  public static void handleCollision(Shape shape1, Shape shape2) {
    // if (shape1.xPos == shape2.xPos && shape1.yPos == shape2.yPos) {
    // shape1.xSpeed = -shape1.xSpeed;
    // shape2.xSpeed = -shape2.xSpeed;
    // }
  }

  public static void manageWallCollisions(Shape shape) {
    if (CollisionsManager.rightWallCollision(shape) || CollisionsManager.leftWallCollision(shape)) {
      shape.speed.x = -shape.speed.x;
    }

    if (CollisionsManager.topWallCollision(shape) || CollisionsManager.bottomWallCollision(shape)) {
      shape.speed.y = -shape.speed.y;
    }
  }

  public static boolean rightWallCollision(Shape shape) {
    if (shape.bottomRight.x < Settings.width) {
      return false;
    }
    return true;
  }

  public static boolean leftWallCollision(Shape shape) {
    if (shape.position.x > 0) {
      return false;
    }
    return true;
  }

  public static boolean bottomWallCollision(Shape shape) {
    if (shape.bottomRight.y < Settings.height) {
      return false;
    }
    return true;
  }

  public static boolean topWallCollision(Shape shape) {
    if (shape.position.y > 0) {
      return false;
    }
    return true;
  }
}
