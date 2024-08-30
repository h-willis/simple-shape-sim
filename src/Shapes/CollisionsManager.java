package src.Shapes;

import java.awt.*;
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

  public static Vector2D hasCollided(Ball ball, Square square) {
    // mid point left x axis collision
    if (ball.mpLeft.x > square.position.x && ball.mpLeft.x < square.bottomRight.x
        && ball.mpLeft.y > square.position.y && ball.mpLeft.y < square.bottomRight.y) {
      return new Vector2D(-1, 1);
    }
    // mid point right x axis collision
    if (ball.mpRight.x > square.position.x && ball.mpRight.x < square.bottomRight.x
        && ball.mpRight.y > square.position.y && ball.mpRight.y < square.bottomRight.y) {
      return new Vector2D(-1, 1);
    }
    if (ball.mpTop.x > square.position.x && ball.mpTop.x < square.bottomRight.x
        && ball.mpTop.y > square.position.y && ball.mpTop.y < square.bottomRight.y) {
      return new Vector2D(1, -1);
    }
    if (ball.mpBottom.x > square.position.x && ball.mpBottom.x < square.bottomRight.x
        && ball.mpBottom.y > square.position.y && ball.mpBottom.y < square.bottomRight.y) {
      return new Vector2D(1, -1);
    }
    // no collisions
    return null;
  }

  // this is gunna be super ineffecient and have planty of room for improvement
  public static void manageBallSquareCollisions(Ball blackBall, Ball whiteBall, Squares squares) {
    for (Square square : squares) {
      // black ball white square collision
      Vector2D reflectionVector;
      if (square.colour == Color.BLACK) {
        reflectionVector = CollisionsManager.hasCollided(blackBall, square);
        if (reflectionVector != null) {
          square.colour = Color.WHITE;
          blackBall.speed = blackBall.speed.multiply(reflectionVector);
        }
      } else {
        reflectionVector = CollisionsManager.hasCollided(whiteBall, square);
        if (reflectionVector != null) {
          square.colour = Color.BLACK;
          whiteBall.speed = whiteBall.speed.multiply(reflectionVector);
        }
      }
    }
  }
}
