package controller;

import java.awt.image.BufferedImage;
import java.io.File;

public interface ControllerFeatures {
  public BufferedImage obtainImage();

  public void addComputerPlayer(String playerName, String playerLocation, int itemLimit);
  public void addPlayer(String playerName, String playerLocation, int itemLimit);
  public void attack();
  public void describePlayer();
  public void describeSpace(String spaceName);
  public void lookaround();
  public void move(String nameOfSpace);
  public void movePet(String nameOfSpace);
  public void pickup(String itemName);
  public void hitDetection(double x, double y);
  public void setModel(File file);
}
