package gamemodels.playermodels;

import gameinterfaces.iteminterfaces.Item;
import gameinterfaces.playerinterfaces.Player;
import gameinterfaces.spaceinterfaces.Space;
import gameinterfaces.worldbuilderinterfaces.World;
import gamemodels.Mock;
import java.util.ArrayList;
import java.util.List;

/**
 * The mock Player class that behaves like a Player but instead logs all method
 * calls and is used in testing.
 */
public class MockPlayer extends Mock implements Player {

  private Item item;

  /**
   * The default constructor that builds a mock player with logging only to itself.
   */
  public MockPlayer() {

  }

  /**
   * Constructs a Mock player that contains a mock item.
   *
   * @param i the mock item to be used
   */
  public MockPlayer(Item i) {
    this.item = i;
  }

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
    var res = new ArrayList<Item>();
    res.add(item);
    return res;
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
    return true;
  }
}
