package gamemodels.spacemodels;

import gameinterfaces.iteminterfaces.Item;
import gameinterfaces.petinterfaces.Pet;
import gameinterfaces.playerinterfaces.Player;
import gameinterfaces.spaceinterfaces.Space;
import gameinterfaces.targetinterfaces.Target;
import gamemodels.Mock;
import java.util.List;


/**
 * The mock Space class that behaves like a Space but instead logs all method
 * calls and is used in testing.
 */
public class MockSpace extends Mock implements Space {
  @Override
  public List<Item> getItems() {
    String methodName = new MockSpace() {}
        .getClass()
        .getEnclosingMethod()
        .getName();
    mockLog.add(String.format("%s called.", methodName));
    return null;
  }

  @Override
  public int getIndexOfTheSpace() {
    String methodName = new MockSpace() {}
        .getClass()
        .getEnclosingMethod()
        .getName();
    mockLog.add(String.format("%s called.", methodName));
    return 0;
  }

  @Override
  public List<Space> getNeighbors() {
    String methodName = new MockSpace() {}
        .getClass()
        .getEnclosingMethod()
        .getName();
    mockLog.add(String.format("%s called.", methodName));
    return null;
  }

  @Override
  public boolean isTargetInThisSpace() {
    String methodName = new MockSpace() {}
        .getClass()
        .getEnclosingMethod()
        .getName();
    mockLog.add(String.format("%s called.", methodName));
    return false;
  }

  @Override
  public String getTheNameOfThisSpace() {
    String methodName = new MockSpace() {}
        .getClass()
        .getEnclosingMethod()
        .getName();
    mockLog.add(String.format("%s called.", methodName));
    return null;
  }

  @Override
  public String getTheFullSpaceDescription() {
    String methodName = new MockSpace() {}
        .getClass()
        .getEnclosingMethod()
        .getName();
    mockLog.add(String.format("%s called.", methodName));
    return null;
  }

  @Override
  public int getUpperLeftxCoordinate() {
    String methodName = new MockSpace() {}
        .getClass()
        .getEnclosingMethod()
        .getName();
    mockLog.add(String.format("%s called.", methodName));
    return 0;
  }

  @Override
  public int getUpperLeftyCoordinate() {
    String methodName = new MockSpace() {}
        .getClass()
        .getEnclosingMethod()
        .getName();
    mockLog.add(String.format("%s called.", methodName));
    return 0;
  }

  @Override
  public int getLowerRightxCoordinate() {
    String methodName = new MockSpace() {}
        .getClass()
        .getEnclosingMethod()
        .getName();
    mockLog.add(String.format("%s called.", methodName));
    return 0;
  }

  @Override
  public int getLowerRightyCoordinate() {
    String methodName = new MockSpace() {}
        .getClass()
        .getEnclosingMethod()
        .getName();
    mockLog.add(String.format("%s called.", methodName));
    return 0;
  }

  @Override
  public void moveTargetToThisSpace(Target target) {
    String methodName = new MockSpace() {}
        .getClass()
        .getEnclosingMethod()
        .getName();
    mockLog.add(String.format("%s called.", methodName));

  }

  @Override
  public Target getTargetFromThisSpace() {
    String methodName = new MockSpace() {}
        .getClass()
        .getEnclosingMethod()
        .getName();
    mockLog.add(String.format("%s called.", methodName));
    return null;
  }

  @Override
  public void putItemsInTheSpace(List<Item> items) {
    String methodName = new MockSpace() {}
        .getClass()
        .getEnclosingMethod()
        .getName();
    mockLog.add(String.format("%s called. items = %s", methodName, items));

  }

  @Override
  public void setNeighborsOfThisSpace(List<Space> neighbors) {
    String methodName = new MockSpace() {}
        .getClass()
        .getEnclosingMethod()
        .getName();
    mockLog.add(String.format("%s called. neighbors = %s", methodName, neighbors));

  }

  @Override
  public void addPlayerToSpace(Player player) {
    String methodName = new MockSpace() {}
        .getClass()
        .getEnclosingMethod()
        .getName();
    mockLog.add(String.format("%s called. player=%s", methodName, player));

  }

  @Override
  public void removePlayerFromSpace(Player player) {
    String methodName = new MockSpace() {}
        .getClass()
        .getEnclosingMethod()
        .getName();
    mockLog.add(String.format("%s called. player=%s", methodName, player));
  }

  @Override
  public List<Player> getPlayersInThisSpace() {
    String methodName = new MockSpace() {}
        .getClass()
        .getEnclosingMethod()
        .getName();
    mockLog.add(String.format("%s called.", methodName));
    return null;
  }

  @Override
  public boolean hasPet() {
    String methodName = new MockSpace() {}
        .getClass()
        .getEnclosingMethod()
        .getName();
    mockLog.add(String.format("%s called.", methodName));
    return false;
  }

  @Override
  public void addPetToSpace(Pet pet) {
    String methodName = new MockSpace() {}
        .getClass()
        .getEnclosingMethod()
        .getName();
    mockLog.add(String.format("%s called. pet = %s", methodName, pet));

  }

  @Override
  public Pet getPet() {
    String methodName = new MockSpace() {}
        .getClass()
        .getEnclosingMethod()
        .getName();
    mockLog.add(String.format("%s called.", methodName));
    return null;
  }
}
