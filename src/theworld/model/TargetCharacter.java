package theworld.model;

import static theworld.utilities.MilestoneUtilities.checkParam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * The model class representing the target character in the world.
 */
public class TargetCharacter {
  private String name;
  private int health;
  private int location;

  /**
   * The constructor for the CPU-Controlled character.

   * @param n the name of the CPU character
   * @param h the health of the character
   * @param l the location to spawn in
   */
  public TargetCharacter(String n, int h, int l) {
    checkParam(n);
    this.name = n;
    this.health = h;
    this.location = l;
  }

  /**
   * Parse a character from a raw line of string.
   *
   * @param raw the raw string representation of a character
   * @return a Character model object.
   */
  public static TargetCharacter parseCharacter(String raw) {
    checkParam(raw);
    String[] info = raw.split(" ");
    int health = Integer.parseInt(info[0]);
    String name = String.join(" ", Arrays.copyOfRange(info, 1, info.length));
    TargetCharacter res = new TargetCharacter(name, health, 0);
    return res;
  }

  /**
   * Gets the name of the target character.
   *
   * @return the name of the target character
   */
  public String getName() {
    return name;
  }

  /**
   * Sets the name of the target character.
   *
   * @param name the name of the target character
   */
  public void setName(String name) {
    checkParam(name);
    this.name = name;
  }

  /**
   * Gets the health of the target character.
   *
   * @return the health of the target character.
   */
  public int getHealth() {
    return health;
  }


  /**
   * Gets the location index of the target character.
   *
   * @return the location index of the target character
   */
  public int getLocation() {
    return location;
  }

  /**
   * Sets the location index of the target character.
   *
   * @param location the location index of the target character
   */
  public void setLocation(int location) {
    this.location = location;
  }

  /**
   * Automatically moves the target character to another neighboring room.
   */
  public void move() {
    Room me = DependencyManager.shared().queryRoom(this.location);
    List<Room> targets = DependencyManager.shared().queryNeighbors(me);
    Room next = targets.get(new Random().nextInt(targets.size()));
    this.location = DependencyManager.shared().queryRoomIndex(next);
  }

  /**
   * Gets the information about where the target character is located.
   *
   * @return The location info about the target character
   */
  public String locatedInfo() {
    return String.format("Target character in Room: %s, Index: %s \n",
            DependencyManager.shared().queryRoom(location), location);
  }

  /**
   * Deals damage to the target character.
   *
   * @param dmg the damage to be applied
   * @return if the target character is dead
   */
  public boolean takeDamage(int dmg) {
    this.health -= dmg;
    return isDead();
  }

  /**
   * Determine if the target character is dead.
   *
   * @return true if the target character is dead, false otherwise
   */
  public boolean isDead() {
    return this.health <= 0;
  }

}
