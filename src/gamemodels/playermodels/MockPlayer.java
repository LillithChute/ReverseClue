package gamemodels.playermodels;

import gameinterfaces.iteminterfaces.Item;
import gameinterfaces.playerinterfaces.Player;
import gameinterfaces.spaceinterfaces.Space;
import gameinterfaces.worldbuilderinterfaces.World;
import gamemodels.Mock;
import java.util.List;

public class MockPlayer extends Mock implements Player {
  
  @Override
  public void move(String nameOfSpace) {
    String methodName = new MockPlayer() {}
        .getClass()
        .getEnclosingMethod()
        .getName();
    mockLog.add(String.format("%s called, nameOfSpace=%s", methodName, nameOfSpace));
  }

  @Override
  public String getPlayerName() {
    String methodName = new MockPlayer() {}
        .getClass()
        .getEnclosingMethod()
        .getName();
    mockLog.add(String.format("%s called.", methodName));
    return null;
  }

  @Override
  public int getLocation() {
    String methodName = new MockPlayer() {}
        .getClass()
        .getEnclosingMethod()
        .getName();
    mockLog.add(String.format("%s called.", methodName));
    return 0;
  }

  @Override
  public String getPlayerItemsDescription() {
    String methodName = new MockPlayer() {}
        .getClass()
        .getEnclosingMethod()
        .getName();
    mockLog.add(String.format("%s called.", methodName));
    return null;
  }

  @Override
  public List<Item> getPlayerItems() {
    String methodName = new MockPlayer() {}
        .getClass()
        .getEnclosingMethod()
        .getName();
    mockLog.add(String.format("%s called.", methodName));
    return null;
  }

  @Override
  public void takeItem(String item) {
    String methodName = new MockPlayer() {}
        .getClass()
        .getEnclosingMethod()
        .getName();
    mockLog.add(String.format("%s called.", methodName));
  }

  @Override
  public String describePlayer() {
    String methodName = new MockPlayer() {}
        .getClass()
        .getEnclosingMethod()
        .getName();
    mockLog.add(String.format("%s called.", methodName));
    return null;
  }

  @Override
  public String lookAround() {
    String methodName = new MockPlayer() {}
        .getClass()
        .getEnclosingMethod()
        .getName();
    mockLog.add(String.format("%s called.", methodName));
    return null;
  }

  @Override
  public boolean canPlayerBeSeen() {
    String methodName = new MockPlayer() {}
        .getClass()
        .getEnclosingMethod()
        .getName();
    mockLog.add(String.format("%s called.", methodName));
    return false;
  }

  @Override
  public void movePet(Space spaceToMoveTo, List<Space> allSpaces) {
    String methodName = new MockPlayer() {}
        .getClass()
        .getEnclosingMethod()
        .getName();
    mockLog.add(String.format("%s called, spaceToMoveTo=%s, allSpaces=%s",
        methodName, spaceToMoveTo, allSpaces));
  }

  @Override
  public String attack(String itemName) {
    String methodName = new MockPlayer() {}
        .getClass()
        .getEnclosingMethod()
        .getName();
    mockLog.add(String.format("%s called, itemName=%s", methodName, itemName));
    return null;
  }

  @Override
  public String takeRandomAction(World world) {
    String methodName = new MockPlayer() {}
        .getClass()
        .getEnclosingMethod()
        .getName();
    mockLog.add(String.format("%s called. world=%s", methodName, world));
    return null;
  }

  @Override
  public boolean completedTurn() {
    String methodName = new MockPlayer() {}
        .getClass()
        .getEnclosingMethod()
        .getName();
    mockLog.add(String.format("%s called.", methodName));
    return false;
  }
}
