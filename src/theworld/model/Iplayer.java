package theworld.model;

import java.util.List;
import theworld.exceptions.NoSuchItemException;
import theworld.exceptions.WeightExceededException;

/**
 * The interface for all classes representing a player in the game.
 */
public interface Iplayer {
  /**
   * Pick up an item from the player's location.

   * @param i the item to pick up
   * @throws NoSuchItemException If the item is not present in the same room
   * @throws WeightExceededException If the player cannot carry any more items
   */
  public void pickUp(Item i) throws NoSuchItemException, WeightExceededException;

  /**
   * Inspect the surrounding area, obtaining information.

   * @return The information of the area player's in
   */
  public String lookAround();

  /**
   * Gets the full information about this player.

   * @param full if full information should be displayed
   * @return the information about this player
   */
  public String getInformation(boolean full);

  /**
   * Gets the information about this player.
   *
   * @return the simplified information string
   */
  public String getInformation();

  /**
   * The abstract method that makes the player automatically roam around the game world,
   * performing several actions.
   *
   * @return The result of the automatic movement.
   */
  public abstract String roamAround();

  /**
   * Returns a string representation of the object. In general, the
   * {@code toString} method returns a string that
   * "textually represents" this object. The result should
   * be a concise but informative representation that is easy for a
   * person to read.
   * It is recommended that all subclasses override this method.
   * The {@code toString} method for class {@code Object}
   * returns a string consisting of the name of the class of which the
   * object is an instance, the at-sign character `{@code @}', and
   * the unsigned hexadecimal representation of the hash code of the
   * object. In other words, this method returns a string equal to the
   * value of:
   * <blockquote>
   * <pre>
   * getClass().getName() + '@' + Integer.toHexString(hashCode())
   * </pre></blockquote>
   *
   * @return a string representation of the object.
   */
  public String toString();

  /**
   * Determine if a player can see another player.
   *
   * @param other the other player
   * @return true if this player can see the other player, false otherwise
   */
  public boolean canSee(Iplayer other);

  /**
   * Attempt to attack the target character with an item.
   *
   * @param i the item to be used for the attack
   * @return the result of this attack
   */
  public String attack(Item i);

  /**
   * Gets the name of this player.
   *
   * @return the name of this player
   */
  public String getName();

  /**
   * Gets the backpack of this player.
   *
   * @return the backpack of this player.
   */
  public List<Item> getBackpack();

  /**
   * Gets the carrying capacity of the player.
   *
   * @return the carrying capacity of this player
   */
  public int getStrength();
}
