package view;

import controller.GraphicalController;
import controller.UiController;
import gameinterfaces.worldbuilderinterfaces.World;
import gamemodels.gamemanagermodels.WorldImpl;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import view.implementations.MainForm;

public class ViewTest {
  public static void main(String[] args) throws FileNotFoundException {
    MainForm mainForm = new MainForm("Reverse Clue");
    World world =
        new WorldImpl(new BufferedReader(new FileReader("./res/worlds/CortoMaltese"
            + ".txt")));
    UiController controller = new GraphicalController(new File("./res/worlds/CortoMaltese"
        + ".txt"));
    controller.setView(mainForm);
  }
}
