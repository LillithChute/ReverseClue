package theworld.model;

import static theworld.utilities.MilestoneUtilities.checkParam;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import theworld.exceptions.BotAlreadyExistsException;
import theworld.exceptions.InvalidLocationException;
import theworld.exceptions.NoSuchPlayerException;



/**
 * The World class that represents a world similar to Doctor Lucky's mansion.
 */
public class World {



  private TargetCharacter targetCharacter;
  private List<Iplayer> players;



  private Pet pet;

  private String name;
  private Point bound;
  private final List<Room> rooms;


  private World() {
    this.rooms = new ArrayList<>();
    this.players = new ArrayList<>();
    this.name = "";
    this.bound = null;
    this.targetCharacter = null;
  }

  /**
   * Gets all players in this world (both human and CPU- players).
   *
   * @return all player in this world
   */
  public List<Iplayer> getAllPlayers() {
    return players;
  }

  /**
   * Gets only human players in this world.
   *
   * @return the human players in this world.
   */
  public List<Player> getPlayers() {
    return players.stream()
            .filter(p -> p instanceof Player)
            .map(p -> (Player) p)
            .collect(Collectors.toList());
  }

  /**
   * Gets only CPU controlled players in this world.
   *
   * @return the CPU controlled players in this world
   */
  public List<CpuPlayer> getCpuPlayers() {
    return players.stream()
            .filter(p -> p instanceof CpuPlayer)
            .map(p -> (CpuPlayer) p)
            .collect(Collectors.toList());
  }

  /**
   * Adds a new human-controller player into the world.

   * @param p the player to add
   * @param roomIndex index of the room which the player spawns in
   * @throws InvalidLocationException if no such room is present in this world
   */
  public void addPlayer(Iplayer p, int roomIndex) throws InvalidLocationException {
    checkParam(p);
    try {
      Room r = roomAt(roomIndex);

      if (!rooms.contains(r)) {
        throw new InvalidLocationException(r);
      }
      this.players.add(p);
      r.getPlayers().add(p);
    } catch (IndexOutOfBoundsException ex) {
      throw new InvalidLocationException(roomIndex);
    }
  }

  /**
   * Moves a player to another location.

   * @param p the player to move
   * @param target the new location to place the player
   * @throws NoSuchPlayerException if the player is not present in this world
   * @throws InvalidLocationException if the target location is not reachable from the player's
   *     current location.
   */
  public void movePlayer(Iplayer p, Room target)
          throws NoSuchPlayerException, InvalidLocationException {
    checkParam(p, target);
    Room before = rooms.stream().filter(r -> r.getPlayers().contains(p))
            .findFirst().orElseThrow(() -> new NoSuchPlayerException(p));
    if (!target.isNeighborOf(before)) {
      throw new InvalidLocationException(target);
    }
    before.getPlayers().remove(p);
    target.getPlayers().add(p);
  }

  /**
   * Parse a world into the memory model from a text file.
   *
   * @param path The path to the world description file.
   * @return The world model object
   */
  public static World parseWorldFromFile(String path) {
    checkParam(path);
    File f = new File(path);
    if (!f.exists()) {
      throw new IllegalArgumentException();
    }
    try {
      FileReader file = new FileReader(f);
      BufferedReader buffer = new BufferedReader(file);
      World res = new World();
      String[] worldInfo = buffer.readLine().split(" ");
      res.bound = new Point(Integer.parseInt(worldInfo[1]), Integer.parseInt(worldInfo[0]));
      res.name = String.join(" ", Arrays.copyOfRange(worldInfo, 2, worldInfo.length));
      res.targetCharacter = TargetCharacter.parseCharacter(buffer.readLine());
      res.pet = Pet.parsePet(buffer.readLine(), res.targetCharacter.getLocation());
      int roomCount = Integer.parseInt(buffer.readLine());
      for (int i = 0; i < roomCount; i++) {
        Room single = Room.parseRoom(buffer.readLine());
        res.rooms.add(single);
      }
      int itemCount = Integer.parseInt(buffer.readLine());
      for (int i = 0; i < itemCount; i++) {
        String raw = buffer.readLine();
        String roomNumber = raw.split(" ")[0];
        Item single = Item.parseItem(raw);
        res.roomAt(Integer.parseInt(roomNumber)).getItems().add(single);
      }
      DependencyManager.shared().manage(res);
      return res;
    } catch (IOException ex) {
      throw new IllegalStateException();
    }
  }

  /**
   * inspect a point in the world with relative information.
   *
   * @param at a point to locate in the world
   * @return the information string of that point, including room, items and neighbor information.
   * @throws NoSuchElementException When nothing is located at the point.
   */
  public String inspect(Point at) throws NoSuchElementException {
    checkParam(at);
    Room target = this.rooms.stream()
            .filter(r -> r.containsPoint(at))
            .findFirst().orElseThrow();
    StringBuilder sb = new StringBuilder();
    sb.append(target.getInformation());
    sb.append("Neighbor Rooms:\n");
    for (Room r : neighborsOfRoom(target)) {
      sb.append(r.toString());
    }
    return sb.toString();
  }

  /**
   * Find the neighbors (rooms that share a wall) of a room.
   *
   * @param r The specific room we want neighbors found
   * @return A list of neighbors of that room
   */
  public List<Room> neighborsOfRoom(Room r) {
    checkParam(r);
    return rooms.stream()
            .filter(single -> single != r)
            .filter(r::isNeighborOf)
            .collect(Collectors.toList());
  }

  /**
   * Gets the room object based on an index.
   *
   * @param index the index of the room
   * @return the corresponding room
   */
  public Room roomAt(int index) {
    return this.rooms.get(index);
  }

  /**
   * Move the target character to a neighboring room.
   *
   * @param to index of the target room
   * @throws IllegalArgumentException When passed room index is not a neighbor of target character's
   *                                  current room
   */
  public void moveCharacter(int to) throws IllegalArgumentException {
    Room currentRoom = roomAt(targetCharacter.getLocation());
    Room targetRoom = roomAt(to);
    List<Room> neighbors = neighborsOfRoom(currentRoom);
    if (neighbors.contains(targetRoom)) {
      targetCharacter.setLocation(to);
    } else {
      throw new IllegalArgumentException();
    }
  }

  /**
   * Draw a graphical representation of the world including the boundary of each room
   * and the name of these rooms.
   *
   * @return The graphical representation of the world
   */

  public BufferedImage draw() {
    final int factor = 50;
    final int offset = 100;
    BufferedImage img = new BufferedImage(bound.x * factor + offset,
            bound.y * factor + offset,
            BufferedImage.TYPE_INT_RGB);
    Graphics g = img.getGraphics();
    g.setColor(Color.WHITE);
    g.fillRect(0, 0, bound.x * factor + offset, bound.y * factor + offset);
    g.setColor(Color.BLACK);
    for (Room r : rooms) {

      g.drawRect(r.getLeftPos().x * factor + offset / 2,
              r.getLeftPos().y * factor + offset / 2,
               (r.getRectangle().width + 1) * factor,
              (r.getRectangle().height + 1) * factor);
      g.drawString(r.getName(), r.getLeftPos().x * factor + 20 + offset / 2,
               r.getLeftPos().y * factor + 20 + offset / 2);
    }

    return img;
  }

  /**
   * Determine if the game is ended at this stage.
   *
   * @return true if the game is ended, false otherwise
   */
  public boolean isGameEnded() {
    return this.targetCharacter.getHealth() <= 0;
  }

  /**
   * Gets all rooms in this world.
   *
   * @return all rooms in this world
   */
  public List<Room> getRooms() {
    return rooms;
  }

  /**
   * Gets the target character in this world.
   *
   * @return the target character in this world
   */
  public TargetCharacter getCharacter() {
    return targetCharacter;
  }

  /**
   * Gets the pet in this world.
   *
   * @return the pet in this world
   */
  public Pet getPet() {
    return pet;
  }

  /**
   * Adds a CPU-controlled player into this world.

   * @param c the CPU player to add
   * @throws BotAlreadyExistsException if there's already a CPU controlled player in the world
   */
  public void setCharacter(TargetCharacter c) throws BotAlreadyExistsException {
    checkParam(c);
    if (this.targetCharacter == null) {
      this.targetCharacter = c;
    } else {
      throw new BotAlreadyExistsException(c, this.targetCharacter);
    }
  }

  /**
   * Gets the target character in this world.
   *
   * @return the target character in this world
   */
  public TargetCharacter getTargetCharacter() {
    return targetCharacter;
  }

}
