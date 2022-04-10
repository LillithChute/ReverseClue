package theworld.model;

import static theworld.utilities.MilestoneUtilities.checkParam;

import java.util.Arrays;

/**
 * Model class representing a single item in a room.
 */
public class Item {

  private int damage;

  private String name;

  /**
   * Parse an item from a raw line of string.
   *
   * @param raw the raw string representation of this item.
   * @return The Item model object.
   */
  public static Item parseItem(String raw) {
    checkParam(raw);
    String[] info = raw.split(" ");
    Item res = new Item();
    res.damage = Integer.parseInt(info[1]);
    res.name = String.join(" ", Arrays.copyOfRange(info, 2, info.length));
    return res;
  }

  /**
   * Gets the name of this item.
   *
   * @return the name of this item
   */
  public String getName() {
    return name;
  }

  /**
   * Sets the name of this item.
   *
   * @param name the name of this item
   */
  public void setName(String name) {
    checkParam(name);
    this.name = name;
  }

  /**
   * Gets the damage of this item.
   *
   * @return the damage of this item
   */
  public int getDamage() {
    return damage;
  }

  /**
   * Emit the name and the damage of this item.
   *
   * @return an information string.
   */
  public String getInformation() {
    return String.format("Name: %s, Damage: %d\n", getName(), getDamage());
  }

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
    return this.name;
  }
}
