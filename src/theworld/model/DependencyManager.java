package theworld.model;

import static theworld.utilities.MilestoneUtilities.checkParam;

import java.util.HashMap;
import java.util.List;
import theworld.exceptions.InvalidLocationException;

/**
 * A global singleton class to query information about various classes.
 */
public class DependencyManager {
  private static DependencyManager instance;
  private World world;


  /**
   * Gets the world associated with this class.
   *
   * @return the world associated with this class.
   */
  public World getWorld() {
    return world;
  }

  /**
   * Obtain the singleton shared instance of this class.

   * @return The unique singleton object
   */
  public static DependencyManager shared() {
    if (instance == null) {
      instance = new DependencyManager();
    }
    return instance;
  }

  /**
   * Take ownership of a world model object.

   * @param w The world object to manage
   */
  public void manage(World w) {
    checkParam(w);
    if (this.world == null) {
      this.world = w;
      HashMap<Room, Boolean> visitSet = new HashMap<>();
      for (Room r : w.getRooms()) {
        visitSet.put(r, false);
      }

      Room start = this.queryRoom(world.getTargetCharacter().getLocation());
      this.dfs(visitSet, start);
    } else {
      throw new IllegalStateException();
    }
  }

  private void dfs(HashMap<Room, Boolean> map, Room current) {
    if (map.get(current).equals(false)) {
      map.put(current, true);
      world.getPet().getTraverseList().add(current);
      for (Room r : queryNeighbors(current)) {
        dfs(map, r);
      }
    }
  }

  /**
   * Queries a location which the player sits in.
   *
   * @param p the player to be searched
   * @return the room the player is in
   */
  public Room queryRoom(Iplayer p) {
    checkParam(p);
    return world.getRooms().stream().filter(r -> r.getPlayers().contains(p)).findFirst()
            .orElse(null);
  }

  /**
   * Queries a location based on the index which it's added.
   *
   * @param i the index of the room
   * @return the room corresponding to that index
   */
  public Room queryRoom(int i) {
    try {
      return world.getRooms().get(i);
    } catch (IndexOutOfBoundsException ex) {
      throw new IllegalStateException();
    }
  }

  /**
   * Queries the neighboring rooms of a specific room.
   *
   * @param r a room in a world
   * @return the neighbors of a room
   */
  public List<Room> queryNeighbors(Room r) {
    checkParam(r);
    return world.neighborsOfRoom(r);
  }

  /**
   * Query the world model object for the index of a room.

   * @param r the room to query index for.
   * @return the index of that room.
   * @throws InvalidLocationException if no such room exists in the world.
   */
  public int queryRoomIndex(Room r) throws InvalidLocationException {
    checkParam(r);
    int index = world.getRooms().indexOf(r);
    if (index == -1) {
      throw new InvalidLocationException(r);
    }
    return index;
  }

  /**
   * Queries the invisible room which the pet resides in.
   *
   * @return the invisible room with the pet
   */
  public Room queryInvisibleRoom() {
    return this.queryRoom(this.world.getPet().getLocation());
  }

  /**
   * Queries the room which the target character resides in.
   *
   * @return the room with the target character
   */
  public Room queryCharacterRoom() {
    return this.queryRoom(this.world.getTargetCharacter().getLocation());
  }

  /**
   * Dispose this object, performing cleanups to free resources.
   */
  public void dispose() {
    this.world = null;
  }

}
