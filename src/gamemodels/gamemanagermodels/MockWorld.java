package gamemodels.gamemanagermodels;

import gameinterfaces.playerinterfaces.Player;
import gameinterfaces.spaceinterfaces.Space;
import gameinterfaces.worldbuilderinterfaces.World;
import gamemodels.Mock;
import gamemodels.playermodels.MockPlayer;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

/**
 * The mock World class that behaves like a World but instead logs all method
 * calls and is used in testing.
 */
public class MockWorld extends Mock implements World {

  @Override
  public String printWorldImageToDisk() {
    String methodName = new MockWorld() {}
        .getClass()
        .getEnclosingMethod()
        .getName();
    mockLog.add(String.format("%s called.", methodName));
    return null;
  }

  @Override
  public BufferedImage worldImage() {
    String methodName = new MockWorld() {}
        .getClass()
        .getEnclosingMethod()
        .getName();
    mockLog.add(String.format("%s called.", methodName));
    return null;
  }

  @Override
  public Space hitDetection(double x, double y) {
    String methodName = new MockWorld() {}
        .getClass()
        .getEnclosingMethod()
        .getName();
    mockLog.add(String.format("%s called, x=%f, y=%f", methodName, x, y));
    return null;
  }

  @Override
  public void moveTarget() {
    String methodName = new MockWorld() {}
        .getClass()
        .getEnclosingMethod()
        .getName();
    mockLog.add(String.format("%s called.", methodName));
  }

  @Override
  public void nextTurn() {
    String methodName = new MockWorld() {}
        .getClass()
        .getEnclosingMethod()
        .getName();
    mockLog.add(String.format("%s called.", methodName));
  }

  @Override
  public List<Space> getSpaces() {
    String methodName = new MockWorld() {}
        .getClass()
        .getEnclosingMethod()
        .getName();
    mockLog.add(String.format("%s called.", methodName));
    return null;
  }

  @Override
  public Space getTheSpaceByName(String nameOfSpace) {
    String methodName = new MockWorld() {}
        .getClass()
        .getEnclosingMethod()
        .getName();
    mockLog.add(String.format("%s called, nameOfSpace = %s", methodName, nameOfSpace));
    return null;
  }

  @Override
  public List<Space> getNeighborsOfaSpace(int indexOfSpace) {
    String methodName = new MockWorld() {}
        .getClass()
        .getEnclosingMethod()
        .getName();
    mockLog.add(String.format("%s called, indexOfSpace = %d", methodName, indexOfSpace));
    return null;
  }

  @Override
  public void addPlayer(String playerName, Space playerLocation, int itemLimit) {
    String methodName = new MockWorld() {}
        .getClass()
        .getEnclosingMethod()
        .getName();
    mockLog.add(String.format("%s called, playerName = %s, playerLocation = %s, itemLimit = %d",
        methodName, playerName, playerLocation, itemLimit));
  }

  @Override
  public void addComputerPlayer(String playerName, Space playerLocation, int itemLimit) {
    String methodName = new MockWorld() {}
        .getClass()
        .getEnclosingMethod()
        .getName();
    mockLog.add(String.format("%s called, playerName = %s, playerLocation = %s, itemLimit = %d",
        methodName, playerName, playerLocation, itemLimit));
  }

  @Override
  public int getMaxNumberOfTurns() {
    String methodName = new MockWorld() {}
        .getClass()
        .getEnclosingMethod()
        .getName();
    mockLog.add(String.format("%s called.", methodName));
    return 0;
  }

  @Override
  public void setMaxNumberOfTurns(int maxTurns) {
    String methodName = new MockWorld() {}
        .getClass()
        .getEnclosingMethod()
        .getName();
    mockLog.add(String.format("%s called, maxTurns = %d", methodName, maxTurns));
  }

  @Override
  public String getAvailableLocations() {
    String methodName = new MockWorld() {}
        .getClass()
        .getEnclosingMethod()
        .getName();
    mockLog.add(String.format("%s called.", methodName));
    return null;
  }

  @Override
  public String getCurrentPlayerTurnInfo() {
    String methodName = new MockWorld() {}
        .getClass()
        .getEnclosingMethod()
        .getName();
    mockLog.add(String.format("%s called.", methodName));
    return null;
  }

  @Override
  public Player getCurrentPlayer() {
    String methodName = new MockWorld() {}
        .getClass()
        .getEnclosingMethod()
        .getName();
    mockLog.add(String.format("%s called.", methodName));
    return new MockPlayer();
  }

  @Override
  public int getTurnTotal() {
    String methodName = new MockWorld() {}
        .getClass()
        .getEnclosingMethod()
        .getName();
    mockLog.add(String.format("%s called.", methodName));
    return 0;
  }

  @Override
  public boolean isGameOver() {
    String methodName = new MockWorld() {}
        .getClass()
        .getEnclosingMethod()
        .getName();
    mockLog.add(String.format("%s called.", methodName));
    return false;
  }

  @Override
  public int getPlayerCount() {
    String methodName = new MockWorld() {}
        .getClass()
        .getEnclosingMethod()
        .getName();
    mockLog.add(String.format("%s called.", methodName));
    return 0;
  }
}
