package theworld.model;

import static theworld.utilities.MilestoneUtilities.checkParam;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import theworld.exceptions.AttackFailedException;
import theworld.exceptions.NoSuchItemException;
import theworld.exceptions.WeightExceededException;

/**
 * The abstract base class representing a player in the game (either human or CPU controlled.)
 */
public abstract class BasePlayer implements Iplayer {

  protected final List<Item> backpack;
  protected final String name;
  protected final int strength;

  /**
   * The base constructor for a player (either human or CPU- controlled).
   *
   * @param n the name of the player
   * @param str the carry capacity of the player
   */
  protected BasePlayer(String n, int str) {
    checkParam(n);
    this.name = n;
    this.strength = str;
    this.backpack = new ArrayList<>();
  }

  /**
   * Pick up an item from the player's location.

   * @param i the item to pick up
   * @throws NoSuchItemException If the item is not present in the same room
   * @throws WeightExceededException If the player cannot carry any more items
   */
  public void pickUp(Item i) throws NoSuchItemException, WeightExceededException {
    checkParam(i);
    Room location = DependencyManager.shared().queryRoom(this);
    if (!location.getItems().contains(i)) {
      throw new NoSuchItemException(i, location);
    }
    if (this.backpack.size() == this.strength) {
      throw new WeightExceededException(this);
    }
    location.getItems().remove(i);
    this.backpack.add(i);
  }

  /**
   * Inspect the surrounding area, obtaining information.

   * @return The information of the area player's in
   */
  public String lookAround() {
    Room located = DependencyManager.shared().queryRoom(this);
    StringBuilder sb = new StringBuilder();
    sb.append(String.format("Player %s located in room %s\n", this, located));
    sb.append(String.format("%s\n", located.getInformation()));
    sb.append("Surrounding areas:\n");
    for (Room r : DependencyManager.shared().queryNeighbors(located)) {
      Room invisible = DependencyManager.shared().queryInvisibleRoom();
      if (r == invisible) {
        continue;
      }
      sb.append(String.format("%s\n", r.getInformation(false)));
    }
    return sb.toString();
  }

  /**
   * Gets the information about this player.

   * @return the information about this player
   */
  public String getInformation(boolean full) {
    StringBuilder sb = new StringBuilder();
    sb.append(String.format("Player %s \n Located in %s\nItems:\n", this,
            DependencyManager.shared().queryRoom(this)));
    if (full) {
      for (Item i : this.backpack) {
        sb.append(String.format("%s\n", i));
      }
    }
    if (DependencyManager.shared().queryInvisibleRoom() == DependencyManager
            .shared().queryRoom(this)) {
      sb.append("You foot stepped on the PET!\n");
    }
    return sb.toString();
  }

  /**
   * Gets a long-information string about this player.
   *
   * @return the long-information string
   */
  public String getInformation() {
    return this.getInformation(true);
  }

  /**
   * Let the player automatically roam about the world.
   *
   * @return the result information after the move.
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
  @Override
  public String toString() {
    return String.format("Player: %s", this.name);
  }

  /**
   * Determine if a player can see another player.
   *
   * @param other the other player
   * @return true if this player can see the other player, false otherwise
   */
  public boolean canSee(Iplayer other) {
    checkParam(other);
    Room selfRoom = DependencyManager.shared().queryRoom(this);
    Room otherRoom = DependencyManager.shared().queryRoom(other);

    return (selfRoom == otherRoom || selfRoom.isNeighborOf(otherRoom));
  }

  /**
   * Attempt to attack the target character with an item.
   *
   * @param i the item to be used for the attack
   * @return the result of this attack
   */
  public String attack(Item i) {
    Room me = DependencyManager.shared().queryRoom(this);
    Room target = DependencyManager.shared().queryCharacterRoom();
    if (me != target) {
      throw new AttackFailedException("Player and the target character are not in the same room!");
    }
    for (Iplayer p : DependencyManager.shared()
            .getWorld().getAllPlayers().stream()
            .filter(p -> p != this).collect(Collectors.toList())) {
      if (p.canSee(this)) {
        this.backpack.remove(i);
        throw new AttackFailedException("Your attack was seen by another player, sorry...");
      }
    }
    if (i == null) {
      DependencyManager.shared().getWorld().getCharacter().takeDamage(1);
      return String.format("Dealt %d damage to the target character.", 1);
    }
    if (!this.backpack.contains(i)) {
      throw new NoSuchItemException(i, this);
    }
    DependencyManager.shared().getWorld().getCharacter().takeDamage(i.getDamage());
    this.backpack.remove(i);
    return String.format("Dealt %d damage to the target character with item %s",
            i.getDamage(), i.getName());

  }

  /**
   * Gets the name of this player.
   *
   * @return the name of this player
   */
  public String getName() {
    return name;
  }

  /**
   * Gets the backpack of this player.
   *
   * @return the backpack of this player.
   */
  public List<Item> getBackpack() {
    return backpack;
  }

  /**
   * Gets the carrying capacity of the player.
   *
   * @return the carrying capacity of this player
   */
  public int getStrength() {
    return strength;
  }
}
