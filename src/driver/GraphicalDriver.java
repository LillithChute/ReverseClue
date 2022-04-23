package driver;

import controller.GraphicalController;
import controller.UiController;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import javax.swing.JOptionPane;
import view.implementations.MainForm;


/**
 * The driver for the milestone 4 graphical game.
 */
public class GraphicalDriver {

  /**
   * The entry point of the Application. It reads a world description file
   * from and command-line and passes it to the necessary model objects.
   *
   * @param args the command-line arguments
   */
  public static void main(String[] args) {
    if (args.length < 1 || args.length > 2) {
      JOptionPane.showMessageDialog(null,
          "You must provide a world file in the command-line arguments!",
          "ERROR",
          JOptionPane.ERROR_MESSAGE);
    }
    String path = args[0];
    MainForm mainForm = new MainForm("Reverse Clue");
    try {
      UiController controller = new GraphicalController(new BufferedReader(
          new FileReader(path)
      ), path);
      controller.setView(mainForm);
    } catch (FileNotFoundException ex) {
      JOptionPane.showMessageDialog(null,
          "File does not exist!",
          "ERROR",
          JOptionPane.ERROR_MESSAGE);
    }
  }
}
