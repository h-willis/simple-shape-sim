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

  public static boolean hasCollided(Shape ball, Square square) {
    // if (ball.position.x < square.bottomRight.x && ball.position.y >
    // square.bottomRight.y) {
    // return true;
    // }
    // if (ball.bottomRight.x > square.position.x && ball.bottomRight.y <
    // square.position.y) {
    // return true;
    // }
    // return false;
    // AABB
    // Check if there is no overlap on the x-axis
    if (ball.position.x > square.bottomRight.x || square.position.x > ball.bottomRight.x) {
      return false;
    }
    // Check if there is no overlap on the y-axis
    if (ball.position.y > square.bottomRight.y || square.position.y > ball.bottomRight.y) {
      return false;
    }
    // If neither condition is true, the rectangles overlap
    return true;

    // if (rect1.topLeft.x > rect2.bottomRight.x || rect2.topLeft.x >
    // rect1.bottomRight.x) {
    // return false;
    // }
    // // Check if there is no overlap on the y-axis
    // if (rect1.topLeft.y > rect2.bottomRight.y || rect2.topLeft.y >
    // rect1.bottomRight.y) {
    // return false;
    // }
    // // If neither condition is true, the rectangles overlap
    // return true;
  }

  // this is gunna be super ineffecient and have planty of room for improvement
  public static void manageBallSquareCollisions(Shape blackBall, Shape whiteBall, Squares squares) {
    for (int w = 0; w < squares.numSquaresWide; w++) {
      for (int h = 0; h < squares.numSquaresTall; h++) {
        // black ball white square collision
        if (squares.squares[w][h].colour == Color.BLACK) {
          if (CollisionsManager.hasCollided(blackBall, squares.squares[w][h])) {
            blackBall.speed = blackBall.speed.multiply(new Vector2D(-1, -1));
            squares.squares[w][h].colour = Color.BLACK;
          }
        } else {
          if (CollisionsManager.hasCollided(whiteBall, squares.squares[w][h])) {
            blackBall.speed = whiteBall.speed.multiply(new Vector2D(-1, -1));
            squares.squares[w][h].colour = Color.WHITE;
          }
        }
      }
    }
  }
}
