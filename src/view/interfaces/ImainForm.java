package view.interfaces;

import java.awt.image.BufferedImage;

import controller.ControllerFeatures;

public interface ImainForm {
  public void setFeatures(ControllerFeatures f);
  public void welcome();
  public void setPreGameMenuVisibility(boolean enabled);
  public void logGameplay(String msg);
  public void fetchGraphics(BufferedImage img);
}
