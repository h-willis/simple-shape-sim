package src;

import javax.swing.JFrame;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import src.Settings;

public class MainApp {

  private static final int FRAME_RATE = 60; // 60 frames per second
  private static final int FRAME_INTERVAL_MS = 1000 / FRAME_RATE;

  public static void main(String[] args) {
    JFrame frame = new JFrame("2D Simulation");
    DrawingPanel panel = new DrawingPanel();

    frame.setSize(Settings.width, Settings.height);
    frame.add(panel);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);

    Timer timer = new Timer(FRAME_INTERVAL_MS, new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        panel.updateShapes(); // Update the positions of the shapes
        panel.repaint(); // Redraw the panel
        frame.getToolkit().sync();
      }
    });
    timer.start();
  }
}
