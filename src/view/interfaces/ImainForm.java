package view.interfaces;

import java.awt.image.BufferedImage;

import controller.ControllerFeatures;

public interface ImainForm {
  public void setFeatures(ControllerFeatures f);
  public void welcome();
  public void setPreGameMenuVisibility(boolean enabled);
  public void logGameplay(String msg);
  public void setGraphics(BufferedImage img);
  public void setTurnInfo(String msg);
  public void promptError(String msg);
}
