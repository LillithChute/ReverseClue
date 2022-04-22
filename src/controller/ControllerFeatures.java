package controller;

import gameinterfaces.iteminterfaces.Item;
import gameinterfaces.playerinterfaces.PlayerViewModel;
import gameinterfaces.spaceinterfaces.SpaceViewModel;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;


public interface ControllerFeatures {
  public BufferedImage obtainImage();

  public void addComputerPlayer(String playerName, String playerLocation, int itemLimit);

  public void addPlayer(String playerName, String playerLocation, int itemLimit);

  public void attack(String itemName);

  public void describePlayer();

  public void describeSpace(String spaceName);

  public void lookaround();

  public String getTurnInformation();

  public void move(String nameOfSpace);

  public void movePet(String nameOfSpace);

  public void pickup(String itemName);

  public SpaceViewModel hitDetection(double x, double y);

  public void setModel(File file);

  public List<Item> getItemsOnTheGround();

  public void advanceTurn();

  public void updateViewInfo();

  public PlayerViewModel getCurrentPlayer();

  public void startGame(int turnCount);

  public List<SpaceViewModel> spawningRooms();

  public void restartGame();

  public boolean gameStarted();
}
