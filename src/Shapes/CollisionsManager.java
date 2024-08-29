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
  }

  public static Vector2D getCollisionAxis(Shape ball, Square square) {
    if (ball.position.x < square.bottomRight.x || ball.bottomRight.x > square.position.x) {
      return new Vector2D(-1, 1);
    }
    return new Vector2D(0, -1);
  }

  // this is gunna be super ineffecient and have planty of room for improvement
  public static void manageBallSquareCollisions(Shape blackBall, Shape whiteBall, Squares squares) {
    for (int w = 0; w < squares.numSquaresWide; w++) {
      for (int h = 0; h < squares.numSquaresTall; h++) {
        // black ball white square collision
        if (squares.squares[w][h].colour == Color.BLACK) {
          if (CollisionsManager.hasCollided(blackBall, squares.squares[w][h])) {
            squares.squares[w][h].colour = Color.WHITE;
            // TODO this speed changing needs to be done based on which axies collided
            blackBall.speed = blackBall.speed
                .multiply(CollisionsManager.getCollisionAxis(blackBall, squares.squares[w][h]));
          }
        } else {
          if (CollisionsManager.hasCollided(whiteBall, squares.squares[w][h])) {
            squares.squares[w][h].colour = Color.BLACK;
            whiteBall.speed = whiteBall.speed
                .multiply(CollisionsManager.getCollisionAxis(whiteBall, squares.squares[w][h]));
          }
        }
      }
    }
  }
}
