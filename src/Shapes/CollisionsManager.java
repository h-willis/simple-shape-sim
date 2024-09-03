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

  public static double clamp(double value, double min, double max) {
    return Math.max(min, Math.min(max, value));
  }

  private static Vector2D getReflectionVector(Ball ball, Square square) {
    // get distance between centres#
    // Get the closest point on the square to the circle's center
    double closestX = clamp(ball.centre.x, square.position.x, square.position.x + square.size);
    double closestY = clamp(ball.centre.y, square.position.y, square.position.y + square.size);

    // Calculate the distance between the circle's center and the closest point
    double distanceX = ball.centre.x - closestX;
    double distanceY = ball.centre.y - closestY;
    double distanceSquared = (distanceX * distanceX) + (distanceY * distanceY);

    // Check if the distance is less than or equal to the circle's radius squared
    if (distanceSquared > (ball.radius * ball.radius)) {
      return null;
    }

    // find the smallest distance between midpoints and squares
    int distanceLeft = Math.abs(ball.position.x - square.bottomRight.x);
    int distanceRight = Math.abs(ball.bottomRight.x - square.position.x);
    int distanceTop = Math.abs(ball.position.y - square.bottomRight.y);
    int distanceBottom = Math.abs(ball.bottomRight.y - square.position.y);

    int minDist = Math.min(Math.min(distanceLeft, distanceRight), Math.min(distanceTop, distanceBottom));

    if (minDist == distanceLeft || minDist == distanceRight) {
      return new Vector2D(-1, 1);
    } else if (minDist == distanceTop || minDist == distanceBottom) {
      return new Vector2D(1, -1);
    }
    return null;

    // // we have an x axis collision
    // if (ball.mpLeft.x < square.bottomRight.x || ball.mpRight.x >
    // square.position.x) {
    // System.out.println("X collision");
    // return new Vector2D(-1, 1);
    // }

    // // y axis collision
    // if (ball.mpTop.y < square.bottomRight.y || ball.mpBottom.y >
    // square.position.y) {
    // System.out.println("Y collision");
    // return new Vector2D(1, -1);
    // }
    // return null;
  }

  // this is gunna be super ineffecient and have planty of room for improvement
  public static void manageBallSquareCollisions(Ball blackBall, Ball whiteBall, Squares squares) {
    for (Square square : squares) {
      // black ball white square collision
      Vector2D reflectionVector;
      if (square.colour == Color.BLACK) {
        reflectionVector = CollisionsManager.getReflectionVector(blackBall, square);
        if (reflectionVector != null) {
          square.colour = Color.WHITE;
          blackBall.speed = blackBall.speed.multiply(reflectionVector);
        }
      } else {
        reflectionVector = CollisionsManager.getReflectionVector(whiteBall, square);
        if (reflectionVector != null) {
          square.colour = Color.BLACK;
          whiteBall.speed = whiteBall.speed.multiply(reflectionVector);
        }
      }
    }
  }
}
