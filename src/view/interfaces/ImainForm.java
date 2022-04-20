package view.interfaces;

import java.awt.image.BufferedImage;
import java.util.List;

import controller.ControllerFeatures;
import gameinterfaces.iteminterfaces.ItemViewModel;
import gameinterfaces.playerinterfaces.PlayerViewModel;

public interface ImainForm {
  public void setFeatures(ControllerFeatures f);
  public void welcome();
  public void setPreGameMenuVisibility(boolean enabled);
  public void logGameplay(String msg);
  public void setGraphics(BufferedImage img);
  public void setTurnInfo(String msg);
  public void promptError(String msg);
  public void setGroundItems(List<ItemViewModel> items);
  public void setBackpackItems(List<ItemViewModel> items);
  public void setPlayerName(PlayerViewModel player);
}
