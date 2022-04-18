package controller;

import java.awt.image.BufferedImage;

import java.io.File;
import java.util.List;
import gameinterfaces.iteminterfaces.Item;
import gameinterfaces.iteminterfaces.ItemViewModel;


public interface ControllerFeatures {
  public BufferedImage obtainImage();

  public void addComputerPlayer(String playerName, String playerLocation, int itemLimit);
  public void addPlayer(String playerName, String playerLocation, int itemLimit);
  public void attack();
  public void describePlayer();
  public void describeSpace(String spaceName);
  public String lookaround();
  public String getTurnInformation();
  public void move(String nameOfSpace);
  public void movePet(String nameOfSpace);
  public void pickup(String itemName);
  public void hitDetection(double x, double y);
  public void setModel(File file);
  public List<Item> getItemsOnTheGround();
  public void advanceTurn();
  public void updateGraphics();
}
