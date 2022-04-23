package gamemodels.petmodels;

import gameinterfaces.petinterfaces.Pet;
import gamemodels.Mock;


public class MockPet extends Mock implements Pet {
  @Override
  public String getName() {
    String methodName = new MockPet() {}
        .getClass()
        .getEnclosingMethod()
        .getName();
    mockLog.add(String.format("%s called.", methodName));
    return null;
  }
}
