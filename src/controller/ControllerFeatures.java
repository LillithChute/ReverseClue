package controller;

import java.awt.image.BufferedImage;

public interface ControllerFeatures {
  public BufferedImage obtainImage();

  public void addComputerPlayer(String playerName, String playerLocation, int itemLimit);
  public void addPlayer(String playerName, String playerLocation, int itemLimit);
  public void attack();
  public void describePlayer();
  public void describeSpace(String spaceName);
  public String lookaround();
  public void move(String nameOfSpace);
  public void movePet(String nameOfSpace);
  public void pickup(String itemName);
  public void hitDetection(double x, double y);
}
