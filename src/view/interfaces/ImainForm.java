package view.interfaces;

import controller.ControllerFeatures;
import gameinterfaces.iteminterfaces.ItemViewModel;
import gameinterfaces.playerinterfaces.PlayerViewModel;
import java.awt.image.BufferedImage;
import java.util.List;

/**
 * The interface for the game view that exposes public methods.
 */
public interface ImainForm {

  /**
   * Sets the controller features to be used within the view.
   *
   * @param f a features object that can be invoked upon.
   */
  public void setFeatures(ControllerFeatures f);

  /**
   * Displays a "welcome" or "about" message popup.
   */
  public void welcome();

  /**
   * Sets the visibility of the pre-game view components, preventing users
   * from interacting with them at unintended times.
   *
   * @param enabled if the components should be visible.
   */
  public void setPreGameMenuVisibility(boolean enabled);

  /**
   * Append a message to the gameplay log.
   *
   * @param msg the message to be appended.
   */
  public void logGameplay(String msg);

  /**
   * Sets the graphical representation of the game.
   *
   * @param img the image of the graphical representation,
   */
  public void setGraphics(BufferedImage img);

  /**
   * Sets the turn information displayed on top of the view.
   *
   * @param msg the turn information message.
   */
  public void setTurnInfo(String msg);

  /**
   * Prompt an error message notifying users for an exception in the gamepaly.
   *
   * @param msg the error message to be prompted.
   */
  public void promptError(String msg);

  /**
   * Sets the items to be displayed in the ground item box.
   *
   * @param items a list of view-model of the items.
   */
  public void setGroundItems(List<ItemViewModel> items);

  /**
   * Sets the items to be displayed of which the player is carrying.
   *
   * @param items a list of view-model of the items.
   */
  public void setBackpackItems(List<ItemViewModel> items);

  /**
   * Sets the player whose items are going to be displayed.
   *
   * @param player a player-view model whose items are displayed.
   */
  public void setBackpackPlayer(PlayerViewModel player);

  /**
   * Sets if the game is started.
   *
   * @param started true if the game is started, false otherwise.
   */
  public void setStartedState(boolean started);

  /**
   * Pops up a prompt when the game has ended, indicating the winner (or the lack of a winner).
   *
   * @param winner the name of the winning player of the game.
   */
  public void showEndingPrompt(String winner);
}
