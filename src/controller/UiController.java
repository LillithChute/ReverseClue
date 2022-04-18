package controller;

import java.io.File;

import gameinterfaces.worldbuilderinterfaces.World;
import view.implementations.MainForm;

public interface UiController {
  public void setView(MainForm view);
  public void setModel(File file);
  public void resetModel();
}
