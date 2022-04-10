package theworld.exceptions;

import theworld.model.BasePlayer;
import theworld.model.Iplayer;

/**
 * This exception is thrown whenever action is made to a non-existing
 * player in the game.
 */
public class NoSuchPlayerException extends GameplayException {

  /**
   * Constructs the exception based on a player.
   *
   * @param p The player which was missing
   */
  public NoSuchPlayerException(Iplayer p) {
    super(String.format("Player %s is not present in the world.", p));
  }
}
