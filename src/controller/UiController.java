package controller;

import java.io.File;
import view.implementations.MainForm;

public interface UiController {
  public void setView(MainForm view);

  public void setModel(File file);

  public void resetView();
}
