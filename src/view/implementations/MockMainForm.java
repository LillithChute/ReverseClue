package view.implementations;

import controller.ControllerFeatures;
import gameinterfaces.iteminterfaces.ItemViewModel;
import gameinterfaces.playerinterfaces.PlayerViewModel;
import gamemodels.Mock;
import java.awt.image.BufferedImage;
import java.util.List;
import view.interfaces.ImainForm;

public class MockMainForm extends Mock implements ImainForm {
  @Override
  public void setFeatures(ControllerFeatures f) {
    String methodName = new MockMainForm() {}
        .getClass()
        .getEnclosingMethod()
        .getName();
    mockLog.add(String.format("%s called. f = %s", methodName, f));
  }

  @Override
  public void welcome() {
    String methodName = new MockMainForm() {}
        .getClass()
        .getEnclosingMethod()
        .getName();
    mockLog.add(String.format("%s called.", methodName));
  }

  @Override
  public void setPreGameMenuVisibility(boolean enabled) {
    String methodName = new MockMainForm() {}
        .getClass()
        .getEnclosingMethod()
        .getName();
    mockLog.add(String.format("%s called. enabled = %b", methodName, enabled));
  }

  @Override
  public void logGameplay(String msg) {
    String methodName = new MockMainForm() {}
        .getClass()
        .getEnclosingMethod()
        .getName();
    mockLog.add(String.format("%s called. msg = %s", methodName, msg));
  }

  @Override
  public void setGraphics(BufferedImage img) {
    String methodName = new MockMainForm() {}
        .getClass()
        .getEnclosingMethod()
        .getName();
    mockLog.add(String.format("%s called. img = %s", methodName, img));
  }

  @Override
  public void setTurnInfo(String msg) {
    String methodName = new MockMainForm() {}
        .getClass()
        .getEnclosingMethod()
        .getName();
    mockLog.add(String.format("%s called. msg = %s", methodName, msg));
  }

  @Override
  public void promptError(String msg) {
    String methodName = new MockMainForm() {}
        .getClass()
        .getEnclosingMethod()
        .getName();
    mockLog.add(String.format("%s called. msg = %s", methodName, msg));
  }

  @Override
  public void setGroundItems(List<ItemViewModel> items) {
    String methodName = new MockMainForm() {}
        .getClass()
        .getEnclosingMethod()
        .getName();
    mockLog.add(String.format("%s called. items = %s", methodName, items));
  }

  @Override
  public void setBackpackItems(List<ItemViewModel> items) {
    String methodName = new MockMainForm() {}
        .getClass()
        .getEnclosingMethod()
        .getName();
    mockLog.add(String.format("%s called. items = %s", methodName, items));
  }

  @Override
  public void setBackpackPlayer(PlayerViewModel player) {
    String methodName = new MockMainForm() {}
        .getClass()
        .getEnclosingMethod()
        .getName();
    mockLog.add(String.format("%s called. player = %s", methodName, player));
  }

  @Override
  public void setStartedState(boolean started) {
    String methodName = new MockMainForm() {}
        .getClass()
        .getEnclosingMethod()
        .getName();
    mockLog.add(String.format("%s called. started = %b", methodName, started));
  }

  @Override
  public void showEndingPrompt(String winner) {
    String methodName = new MockMainForm() {}
        .getClass()
        .getEnclosingMethod()
        .getName();
    mockLog.add(String.format("%s called. winner = %s", methodName, winner));
  }
}
