package gameinterfaces.worldcontrollerinterfaces;

import gameinterfaces.worldbuilderinterfaces.World;

/**
 * This presents the allowable operations for the controller of the game.
 */
public interface Controller {

  /**
   * Create a method that passes control of the game to the model.
   *
   * @param game - An instance of the game manager.
   */
  void start(World game);
}
