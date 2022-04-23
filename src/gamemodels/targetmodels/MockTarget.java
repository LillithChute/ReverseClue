package gamemodels.targetmodels;

import gameinterfaces.targetinterfaces.Target;
import gamemodels.Mock;

public class MockTarget extends Mock implements Target {
  @Override
  public String getTargetName() {
    String methodName = new MockTarget() {}
        .getClass()
        .getEnclosingMethod()
        .getName();
    mockLog.add(String.format("%s called.", methodName));
    return null;
  }

  @Override
  public int getCurrentHealth() {
    String methodName = new MockTarget() {}
        .getClass()
        .getEnclosingMethod()
        .getName();
    mockLog.add(String.format("%s called.", methodName));
    return 0;
  }

  @Override
  public void setHealth(int newHealth) {
    String methodName = new MockTarget() {}
        .getClass()
        .getEnclosingMethod()
        .getName();
    mockLog.add(String.format("%s called. newHealth = %d", methodName, newHealth));

  }
}
