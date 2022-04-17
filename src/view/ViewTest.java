package view;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import controller.GraphicalController;
import controller.UiController;
import gameinterfaces.worldbuilderinterfaces.World;
import gamemodels.gamemanagermodels.WorldImpl;
import view.implementations.MainForm;

public class ViewTest {
  public static void main(String[] args) throws FileNotFoundException {
    MainForm mainForm = new MainForm("main window");
    World world =
            new WorldImpl(new BufferedReader(new FileReader("./res/worlds/CortoMaltese"
                    + ".txt")));
    UiController controller = new GraphicalController(world);
    controller.setView(mainForm);
  }
}
