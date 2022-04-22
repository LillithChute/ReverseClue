package controller;

import gameinterfaces.iteminterfaces.Item;
import gameinterfaces.playerinterfaces.PlayerViewModel;
import gameinterfaces.spaceinterfaces.SpaceViewModel;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;


/**
 * The features interface for the controller that a view could invoke upon.
 */
public interface ControllerFeatures {

  /**
   * Adds a computer player into the game.
   *
   * @param playerName the name of the computer player
   * @param playerLocation the name of the location in which the player is spawned
   * @param itemLimit the maximum number of items that the player can carry
   */
  public void addComputerPlayer(String playerName, String playerLocation, int itemLimit);

  /**
   * Adds a human player into the game.
   *
   * @param playerName the name of the human player
   * @param playerLocation the name of the location in which the player is spawned
   * @param itemLimit the maximum number of items that the player can carry
   */
  public void addPlayer(String playerName, String playerLocation, int itemLimit);

  /**
   * Issues an attack command towards the target character.
   *
   * @param itemName The name of the item to used for the attack
   */
  public void attack(String itemName);

  /**
   * Obtains the information associated with a player.
   */
  public void describePlayer();

  /**
   * Obtains the information when looking around a space.
   */
  public void lookaround();

  /**
   * Obtain the turn information (current player, current turn count, turns in total) with the
   * current game.
   *
   * @return the turn information string.
   */
  public String getTurnInformation();

  /**
   * Issues a moving command that moves the player into another space.
   *
   * @param nameOfSpace the name of the space to move into.
   */
  public void move(String nameOfSpace);

  /**
   * Issues a command that moves the pet into another space.
   *
   * @param nameOfSpace the name of the space to move the pet into.
   */
  public void movePet(String nameOfSpace);

  /**
   * Issues a command that makes a player pick up an item from the ground.
   *
   * @param itemName The name of the item to pick up.
   */
  public void pickup(String itemName);

  /**
   * Determine if a point lies within the boundary of s space and obtains a reference
   * to its corresponding view-model.
   *
   * @param x the x-coordinate of a point
   * @param y the y-coordinate of a point
   * @return a view-model of the space containing that point, if no spaces contains that point,
   *     null will be returned.
   */
  public SpaceViewModel hitDetection(double x, double y);

  /**
   * Associate a model with the controller, making the controller manage the input and
   * forwards changes to be applied to the model.
   *
   * @param file a file in which the model will be constructed.
   */
  public void setModel(File file);

  /**
   * Obtains a view-model corresponding to the player of the current turn.
   *
   * @return the player view-model in this turn
   */
  public PlayerViewModel getCurrentPlayer();

  /**
   * Instructs the controller to start the game after all players are added.
   *
   * @param turnCount the maximum number of turns to play.
   */
  public void startGame(int turnCount);

  /**
   * Gets a list of view-models corresponding to the feasible spaces to spawn players in.
   *
   * @return a list of space view-models that can spawn a player in.
   */
  public List<SpaceViewModel> spawningRooms();

  /**
   * Instructs the controller to reset the progress and restart the game.
   */
  public void restartGame();

  /**
   * Gets the status regarding if the game has started.
   *
   * @return true if the game is started, false otherwise
   */
  public boolean gameStarted();
}
