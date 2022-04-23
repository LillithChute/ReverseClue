package gamemodels.itemmodels;

import gameinterfaces.iteminterfaces.Item;
import gamemodels.Mock;

/**
 * The mock Item class that behaves like an Item but instead logs all method
 * calls and is used in testing.
 */
public class MockItem extends Mock implements Item {
  
  @Override
  public String getNameOfItem() {
    String methodName = new MockItem() {}
        .getClass()
        .getEnclosingMethod()
        .getName();
    mockLog.add(String.format("%s called.", methodName));
    return null;
  }

  @Override
  public int getSpaceIndexOfItem() {
    String methodName = new MockItem() {}
        .getClass()
        .getEnclosingMethod()
        .getName();
    mockLog.add(String.format("%s called.", methodName));
    return 0;
  }

  @Override
  public boolean isItemWithPlayer() {
    String methodName = new MockItem() {}
        .getClass()
        .getEnclosingMethod()
        .getName();
    mockLog.add(String.format("%s called.", methodName));
    return false;
  }

  @Override
  public void setIsItemWithPlayer() {
    String methodName = new MockItem() {}
        .getClass()
        .getEnclosingMethod()
        .getName();
    mockLog.add(String.format("%s called.", methodName));

  }

  @Override
  public boolean isItemEvidence() {
    String methodName = new MockItem() {}
        .getClass()
        .getEnclosingMethod()
        .getName();
    mockLog.add(String.format("%s called.", methodName));
    return false;
  }

  @Override
  public void setItemIsEvidence() {
    String methodName = new MockItem() {}
        .getClass()
        .getEnclosingMethod()
        .getName();
    mockLog.add(String.format("%s called.", methodName));

  }

  @Override
  public int getItemDamage() {
    String methodName = new MockItem() {}
        .getClass()
        .getEnclosingMethod()
        .getName();
    mockLog.add(String.format("%s called.", methodName));
    return 0;
  }
}
