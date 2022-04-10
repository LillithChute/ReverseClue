package theworld.model;

import static theworld.utilities.MilestoneUtilities.checkParam;

import java.util.ArrayList;
import java.util.List;

/**
 * Representing the target character's pet.
 */
public class Pet {

  private final String name;
  private int location;
  private final List<Room> traverseList;
  private int visitIndex;


  private Pet(String n, int l) {
    checkParam(n);
    this.name = n;
    this.location = l;
    this.traverseList = new ArrayList<>();
    this.visitIndex = 0;
  }

  /**
   * Factory method to parse a pet from a raw string.
   *
   * @param raw the raw string
   * @param l the starting room which the pet is located
   * @return the constructed Pet object
   */
  public static Pet parsePet(String raw, int l) {
    checkParam(raw);
    return new Pet(raw, l);
  }

  /**
   * Automatically moves the pet following a depth-first search.
   */
  public void wander() {
    this.visitIndex++;
    if (this.visitIndex >= this.traverseList.size()) {
      this.visitIndex = 0;
    }
    this.location = DependencyManager.shared().queryRoomIndex(traverseList.get(visitIndex));
  }

  /**
   * Gets the location index of the pet.
   *
   * @return the location index
   */
  public int getLocation() {
    return location;
  }

  /**
   * Sets the location index of the pet.
   *
   * @param location the target location
   */
  public void setLocation(int location) {
    this.location = location;
  }

  /**
   * Gets the traversal list of this pet.
   *
   * @return the traversal list of the pet
   */
  public List<Room> getTraverseList() {
    return traverseList;
  }

  /**
   * Reset the depth-first traversal, making the pet start from the beginning.
   */
  public void resetTraversal() {
    this.visitIndex = 0;
  }
}
