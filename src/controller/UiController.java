package controller;

import java.io.File;
import view.implementations.MainForm;

/**
 * This interface represents public methods that are unique to the controller,
 * but not part of the features used by the view.
 */
public interface UiController {

  /**
   * Associate a view with the controller, making the controller manage the appearance
   * of that view.
   *
   * @param view the view object to be associated to the controller
   */
  public void setView(MainForm view);

  /**
   * Associate a model with the controller, making the controller manage the input and
   * forwards changes to be applied to the model.
   *
   * @param file a file in which the model will be constructed.
   */
  public void setModel(File file);

  /**
   * Reset the view back to its initial state after each game.
   */
  public void resetView();
}
