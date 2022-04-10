package gameinterfaces.worldbuilderinterfaces;

import gameinterfaces.playerinterfaces.Player;
import gameinterfaces.spaceinterfaces.Space;
import gamemodels.targetmodels.TargetImpl;
import java.util.List;


/**
 * This interface is a facade.  It basically will manage the game for the client.  The client
 * can hand the items, spaces, and target to this, and it will know what to do with them
 */
public interface World {

  /**
   * This method will return a buffered image that describes how the spaces of the world
   * appear.
   *
   * @return The file path location of the image.
   */
  String printWorldImageToDisk();

  /**
   * This method will call the {@link TargetImpl} class to move the target to the next space
   * in the index of the list of spaces.
   */
  void moveTarget();

  /**
   * This method will set the turn to the next player in the queue.
   */
  void nextTurn();

  /**
   * This will return all the {@link Space} spaces that make up the world of the game.
   *
   * @return - A list of {@link Space} spaces
   */
  List<Space> getSpaces();

  /**
   * This will return a {@link Space} given its name.
   *
   * @param nameOfSpace - The index of the space to be described.
   * @return The space.
   */
  Space getTheSpaceByName(String nameOfSpace);

  /**
   * This returns all the spaces that share a wall with the indicated space.
   *
   * @param indexOfSpace - The index of the space to be processed.
   * @return A list of neighboring spaces.
   */
  List<Space> getNeighborsOfaSpace(int indexOfSpace);

  /**
   * This method adds a {@link Player} to the game.
   *
   * @param playerName - The name of the player.
   * @param playerLocation - The starting space of the player.
   * @param itemLimit - The maximum number of items the player can carry.
   * @param isHuman - True if a human player false otherwise.
   */
  void addPlayer(String playerName, Space playerLocation, int itemLimit, boolean isHuman);

  /**
   * This will set the maximum turns to be played in the game.
   *
   * @param maxTurns - Max number of turns.
   */
  void setMaxNumberOfTurns(int maxTurns);

  /**
   * This will get the maximum allowable turns in the game.
   *
   * @return - Max number of turns.
   */
  int getMaxNumberOfTurns();

  /**
   * We need a way to tell the client all the available locations to choose from when they
   * are adding players to the game.  A player gets to pick where they start, but, in order to
   * do so they need to know the options.
   *
   * @return The locations in the game.
   */
  String getAvailableLocations();

  /**
   * Gets the name of the player whose turn it is.
   *
   * @return Name of the player whose turn it is.
   */
  String getCurrentPlayerTurnInfo();

  /**
   * Gets the player whose turn it is.
   *
   * @return The player whose turn it is.
   */
  Player getCurrentPlayer();

  /**
   * Figure out what the computer player is going to do on its turn.
   *
   * @return The computer's move.
   */
  String getComputerChoice();

  /**
   * Gets the number of turns that have been taken so far.
   *
   * @return Total number of turns taken.
   */
  int getTurnTotal();

  /**
   * Checks to see if the game is over.  This means either the maximum number of turns has been
   * reached or the target is dead.
   *
   * @return True if the above conditions are met, false otherwise.
   */
  boolean isGameOver();
}
