package gamemodels.gamemanagermodels;

import customexceptions.IncorrectFilePathException;
import gameinterfaces.iteminterfaces.Item;
import gameinterfaces.petinterfaces.Pet;
import gameinterfaces.playerinterfaces.Player;
import gameinterfaces.spaceinterfaces.Space;
import gameinterfaces.targetinterfaces.Target;
import gameinterfaces.worldbuilderinterfaces.World;
import gamemodels.petmodels.PetImpl;
import gamemodels.playermodels.ComputerPlayerImpl;
import gamemodels.playermodels.PlayerImpl;
import instancecreationhelpers.InstanceBuilder;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.imageio.ImageIO;

/**
 * This is my facade class.  It takes the required methods from the model that are needed to
 * perform game management and collects them.  Thus, a client will call this to do the activities
 * of the game.  So here, we can create the world by taking in a file containing the description
 * of it.  We can get space descriptions, move the target, and print out an image of a map.
 */
public class WorldImpl implements World {
  private int rows;
  private int cols;
  private String gameName;
  private int totalSpaces;
  private int totalItems;
  private Target target;
  private String targetName;
  private int targetHealth;
  private int playerTurn;
  private int totalPlayers;
  private int maximumTurns;
  private int turnTotal;
  private final List<Player> players;
  private final List<Space> spaces;
  private final List<Item> items;
  private final InstanceBuilder instanceBuilder;

  private final int scale;

  /**
   * This will take the filename of the file that has the information about creating the world of
   * the game.  Using that, it will build the world.
   *
   *
   * @param file - File data that contains the world description.
   */
  public WorldImpl(Readable file) {
    if (file == null) {
      throw new IllegalArgumentException("You must enter a valid file and path for input.");
    }

    spaces = new ArrayList<>();
    items = new ArrayList<>();
    players = new ArrayList<>();
    instanceBuilder = new InstanceBuilder();
    playerTurn = 0;
    totalPlayers = 0;
    turnTotal = 0;
    this.scale = 30;

    generateGameBoard((BufferedReader) file);

    // Place Target in first room
    spaces.get(0).moveTargetToThisSpace(target);
  }

  @Override
  public String printWorldImageToDisk() {
    File file;
    BufferedImage bufferedImage = this.worldImage();
    try {
      file = new File(gameName + ".png");
      ImageIO.write(bufferedImage, "png", file);
    } catch (IOException ex) {
      throw new IncorrectFilePathException("Unable to write image file.");
    }

    return file.getAbsolutePath();
  }

  @Override
  public BufferedImage worldImage() {
    int width = cols + 7; //7
    int height = rows + 1; //1

    // Constructs a BufferedImage of one of the predefined image types.
    BufferedImage bufferedImage = new BufferedImage((width * scale), (height * scale), BufferedImage.TYPE_INT_RGB);

    // Create a graphics which can be used to draw into the buffered image
    Graphics2D g2d = bufferedImage.createGraphics();
    //g2d.scale(scale, scale);
    for (Space currentSpace :
            spaces) {
      int rectWidth = currentSpace
              .getLowerRightxCoordinate() - currentSpace.getUpperLeftxCoordinate();
      int rectHeight = currentSpace
              .getLowerRightyCoordinate() - currentSpace.getUpperLeftyCoordinate();
      int x = currentSpace.getUpperLeftxCoordinate();
      int y = currentSpace.getUpperLeftyCoordinate();
      //draw the room itself
      g2d.draw(new Rectangle2D.Double((x - 1) * scale, (y - 1) * scale,
              (rectWidth + 1) * scale, (rectHeight + 1) * scale));
      //draw the name of the room
      g2d.drawString(currentSpace.getTheNameOfThisSpace(), (x - 1 + 0.1f) * scale,
              (y - 0.1f + rectHeight) * scale);
      //draw players
      int i = 0;
      float v = (x - 1) * scale + 0.2f * scale;
      for (; i < currentSpace.getPlayersInThisSpace().size(); i++) {
        Player p = currentSpace.getPlayersInThisSpace().get(i);
        g2d.draw(new Rectangle2D.Double(
                ((x - 1) * scale + 0.1f * scale),
                (((y - 1 + 0.2f) + i) * scale),
                ((rectWidth + 0.8f) * scale),
                0.8f * scale ));
        g2d.drawString(p.getPlayerName(),
                v,
                (((y - 1 + 0.8f) + i) * scale));
      }
      if (currentSpace.isTargetInThisSpace() && !currentSpace.hasPet()) {
        g2d.draw(new Rectangle2D.Double(
                ((x - 1) * scale + 0.1f * scale),
                (((y - 1 + 0.2f) + i) * scale),
                ((rectWidth + 0.8f) * scale),
                0.8f * scale ));
        g2d.drawString(target.getTargetName(),
                v,
                (((y - 1 + 0.8f) + i) * scale));
      }
    }
    //g2d.draw(new Rectangle2D.Double(x, y, rectWidth, rectHeight));

    g2d.dispose();
    return bufferedImage;
  }

  @Override
  public Space hitDetection(double mx, double my) {
    for (Space s: spaces)
    {
      int rectWidth = s
              .getLowerRightxCoordinate() - s.getUpperLeftxCoordinate();
      int rectHeight = s
              .getLowerRightyCoordinate() - s.getUpperLeftyCoordinate();
      int x = s.getUpperLeftxCoordinate();
      int y = s.getUpperLeftyCoordinate();
      Rectangle box = new Rectangle((x - 1) * scale, (y - 1) * scale,
              (rectWidth + 1) * scale, (rectHeight + 1) * scale);
      Point p = new Point((int) mx, (int) my);
      if (box.contains(p)) {
        return s;
      }
    }
    return null;
  }

  @Override
  public List<Space> getSpaces() {
    // this is mutable data.  Can't share class data.  Make a copy.
    return new ArrayList<>(spaces);
  }

  @Override
  public Space getTheSpaceByName(String nameOfSpace) {
    Space getRequiredSpace = null;

    if (nameOfSpace == null || nameOfSpace.isBlank()) {
      throw new IllegalArgumentException("The name of the space cannot be null or blank.");
    }

    for (Space currentSpace :
            this.getSpaces()) {
      if (currentSpace.getTheNameOfThisSpace().equalsIgnoreCase(nameOfSpace)) {
        getRequiredSpace = currentSpace;
      }
    }

    if (getRequiredSpace == null) {
      throw new IllegalArgumentException("Unable to find the location in the World.");
    }

    return getRequiredSpace;
  }

  @Override
  public void moveTarget() {
    int currentIndex;
    Space newTargetLocation;

    // Where is the target now?
    for (Space currentSpace :
            spaces) {
      if (currentSpace.isTargetInThisSpace()) {
        // Get the index of this space because we want to move target
        currentIndex = currentSpace.getIndexOfTheSpace();

        // We need to make sure that if this is the last space, we wrap around
        // to the beginning
        if (currentIndex == spaces.size() - 1) {
          // Wrap back to the beginning.
          newTargetLocation = spaces.get(0);
        } else {
          // Get the target to the new space
          newTargetLocation = spaces.get(currentIndex + 1);
        }

        // Add target to this space
        newTargetLocation.moveTargetToThisSpace(currentSpace.getTargetFromThisSpace());

        // Remove the target from the current location
        currentSpace.moveTargetToThisSpace(null);

        // Only one target, so if we are here we are done
        break;
      }
    }
  }

  @Override
  public List<Space> getNeighborsOfaSpace(int indexOfThisSpace) {
    Space getRequiredSpace = null;
    List<Space> neighbors;

    for (Space currentSpace :
            this.getSpaces()) {
      if (currentSpace.getIndexOfTheSpace() == indexOfThisSpace) {
        getRequiredSpace = currentSpace;
      }
    }

    if (getRequiredSpace != null) {
      neighbors = getRequiredSpace.getNeighbors();
    } else {
      throw new IllegalArgumentException("The index of this space is not valid.");
    }

    return neighbors;
  }

  @Override
  public void addPlayer(String playerName, Space playerLocation, int itemLimit) {
    Player newPlayer = new PlayerImpl(playerName, playerLocation, itemLimit);
    players.add(newPlayer);
    totalPlayers = players.size();
  }

  @Override
  public void addComputerPlayer(String playerName, Space playerLocation, int itemLimit) {
    Player newPlayer = new ComputerPlayerImpl(playerName, playerLocation, itemLimit);
    players.add(newPlayer);
    totalPlayers = players.size();
  }

  @Override
  public void setMaxNumberOfTurns(int maxTurns) {
    if (maxTurns < 1) {
      throw new IllegalArgumentException("The maximum number of turns must be greater than 0.");
    }

    this.maximumTurns = maxTurns;
  }

  @Override
  public int getMaxNumberOfTurns() {
    return maximumTurns;
  }

  @Override
  public String getAvailableLocations() {
    StringBuilder buildString = new StringBuilder();

    for (Space currentSpace :
            spaces) {
      buildString.append(currentSpace.getTheNameOfThisSpace())
              .append(", ");
    }

    // Get rid of the trailing comma and space
    String locations = buildString.toString().trim();

    return locations.substring(0, locations.length() - 1);
  }

  @Override
  public String getCurrentPlayerTurnInfo() {
    StringBuilder turnInformation = new StringBuilder();

    // Get player name
    turnInformation.append("******************************************************************\n");
    turnInformation.append("* It is ").append(getCurrentPlayer().getPlayerName()).append("'s turn"
            + ".\n");

    turnInformation.append("* You have the following information available:\n*\n");

    // Get basic surrounding information
    turnInformation.append(spaces.get(
            getCurrentPlayer().getLocation()).getTheFullSpaceDescription()
    );

    // Get the location of the Target
    turnInformation.append(getTargetNameAndLocation());

    // If the pet is here
    turnInformation.append("\n* Pet:\n");
    if (spaces.get(getCurrentPlayer().getLocation()).hasPet()) {
      turnInformation.append("* ")
              .append(spaces.get(getCurrentPlayer().getLocation()).getPet().getName())
             .append(" is here.\n");
    } else {
      turnInformation.append("* The pet is not here.\n");
    }

    turnInformation.append("******************************************************************\n");

    return turnInformation.toString();
  }

  private String getTargetNameAndLocation() {
    StringBuilder targetInformation = new StringBuilder();

    // Get the items here
    targetInformation.append("*\n* TARGET:\n");

    for (Space currentSpace :
         spaces) {
      if (currentSpace.isTargetInThisSpace()) {
        var targetName = currentSpace.getTargetFromThisSpace().getTargetName();
        var targetLocation = currentSpace.getTheNameOfThisSpace();
        targetInformation.append("* ").append(targetName)
                .append(" is located at ")
                .append(targetLocation)
                .append("\n*");
      }
    }

    return targetInformation.toString();
  }

  @Override
  public int getTurnTotal() {
    return this.turnTotal;
  }

  @Override
  public boolean isGameOver() {
    return getTurnTotal() >= getMaxNumberOfTurns() || target.getCurrentHealth() <= 0;
  }

  @Override
  public String toString() {
    StringBuilder buildString = new StringBuilder();

    buildString.append(rows)
            .append(";")
            .append(cols)
            .append(";")
            .append(gameName);

    return buildString.toString();
  }

  @Override
  public void nextTurn() {
    // If the index of the turn of the player equals the total number of
    // players then we need to go back to the beginning of the list.
    if (playerTurn == totalPlayers - 1) {
      playerTurn = 0;
    } else {
      playerTurn += 1;
    }

    // We are changing the current player which means we are incrementing a turn
    turnTotal += 1;
  }

  @Override
  public Player getCurrentPlayer() {
    return players.get(playerTurn);
  }

  private void generateGameBoard(BufferedReader br) {
    if (br == null) {
      throw new IllegalArgumentException("The world building file cannot be null.");
    }

    StringBuilder buildPetName;
    Pet pet = null;

    try {
      String line;
      int lineNumber = 1;
      int spaceIndexNumber = 0;
      int beginningIndexOfItems = 0;

      while ((line = br.readLine()) != null) {
        String[] gameData = line.trim().replace("  ", " ").split(" ");

        // Line one contains global level data
        if (lineNumber == 1) {
          buildGameLevelData(gameData);
        }

        // Line 2 has the target data
        if (lineNumber == 2) {
          buildTargetData(gameData);
        }

        // Line 3 has the Pet.
        if (lineNumber == 3) {
          buildPetName = new StringBuilder();

          for (int i = 0; i < gameData.length; i++) {
            buildPetName.append(gameData[i]).append(" ");
          }

          if (buildPetName.toString().trim().isBlank()) {
            throw new IllegalArgumentException("The pet must have a name.");
          }

          // Create a pet
          pet = new PetImpl(buildPetName.toString().trim());
        }

        // Line 4 has the total number of spaces
        if (lineNumber == 4) {
          totalSpaces = Integer.parseInt(line);

          // Now that we have the total number of spaces, we can create the target
          target = instanceBuilder.targetBuilder(targetName, totalSpaces, targetHealth);
        }

        // Line 4 through however many spaces is the number of spaces
        // we need to create.
        if (lineNumber > 4 && lineNumber <= totalSpaces + 4) {
          int upperLeftY = Integer.parseInt(gameData[0]);
          int upperLeftX = Integer.parseInt(gameData[1]);
          int lowerRightY = Integer.parseInt(gameData[2]);
          int lowerRightX = Integer.parseInt(gameData[3]);

          StringBuilder buildSpaceName = new StringBuilder();
          for (int i = 4; i < gameData.length; i++) {
            buildSpaceName.append(gameData[i]).append(" ");
          }

          String spaceName = buildSpaceName.toString().trim();

          // Now that we have space info, we can create the instance
          if (upperLeftX < 0 || upperLeftX > cols || lowerRightX < 0
                  || lowerRightX > cols || upperLeftY < 0 || upperLeftY > rows
                  || lowerRightY < 0 || lowerRightY > rows) {
            throw new IllegalArgumentException("The coordinates for building the space '"
                    + spaceName + "' are not valid.  An index is out of range.");
          }

          Space space = instanceBuilder.spaceBuilder(spaceIndexNumber, spaceName,
                  upperLeftX, upperLeftY, lowerRightX, lowerRightY);

          spaces.add(space);

          spaceIndexNumber += 1;
        }

        // Now we get the number of items.
        if (lineNumber == totalSpaces + 5) {
          totalItems = Integer.parseInt(line);
          beginningIndexOfItems = lineNumber + 1;
        }


        // Get the item data and create an instance of item to add to the list.
        if (lineNumber >= beginningIndexOfItems
                && lineNumber <= totalItems + beginningIndexOfItems) {
          int spaceItemIsIn = Integer.parseInt(gameData[0]);
          int damage = Integer.parseInt(gameData[1]);

          StringBuilder buildItemName = new StringBuilder();
          for (int i = 2; i < gameData.length; i++) {
            buildItemName.append(gameData[i]).append(" ");
          }

          String itemName = buildItemName.toString().trim();

          // Now that we have the total number of spaces, we can create the target
          if (spaceItemIsIn < 0 || spaceItemIsIn > totalSpaces - 1) {
            throw new IllegalArgumentException("The item '" + itemName
                    + "' has an invalid location at index: " + spaceItemIsIn);
          } else {
            Item item = instanceBuilder.itemBuilder(itemName, damage, spaceItemIsIn);

            items.add(item);
          }
        }

        lineNumber += 1;
      } // End of loop for reading the file.

      // Target starts in the first space as does the Pet
      spaces.get(0).moveTargetToThisSpace(this.target);
      spaces.get(0).addPetToSpace(pet);

      // Put the items in their respective spaces
      for (int i = 0; i < spaces.size(); i++) {
        List<Item> itemsInThisSpace = new ArrayList<>();

        for (Item currentItem :
                items) {
          if (currentItem.getSpaceIndexOfItem() == i) {
            itemsInThisSpace.add(currentItem);
          }
        }

        // Add these items to the space
        spaces.get(i).putItemsInTheSpace(itemsInThisSpace);

        // Set the neighbors
        List<Space> theNeighbors = getNeighbors(spaces, spaces.get(i));
        spaces.get(i).setNeighborsOfThisSpace(theNeighbors);
      }

    } catch (IOException ex) {
      throw new IncorrectFilePathException("Unable to read File.");
    }
  }

  private List<Space> getNeighbors(List<Space> allTheSpaces, Space spaceBeingExamined) {
    // Going to collect the neighbors
    List<Space> neighbors = new ArrayList<>();

    // Loop through list of the spaces in the game.
    for (Space currentSpace :
            allTheSpaces) {
      // If the space to be examined is not this space, process it.
      if (currentSpace.getIndexOfTheSpace() != spaceBeingExamined.getIndexOfTheSpace()) {
        // Calculate if these two are neighbors
        if (areTheseSpacesNeighbors(currentSpace, spaceBeingExamined)) {
          neighbors.add(currentSpace);
        }
      }
    }

    return neighbors;
  }

  private boolean areTheseSpacesNeighbors(Space spaceInTheListOfAll, Space spaceBeingExamined) {
    // Examine north wall.  upper left X upper left Y, lower right X; upper left Y of current room
    // and upper left X, lower right y+1; lower right X, lower right Y+1.  For inclusivity add 1 to
    // lower right Y of incoming space.
    if (doesWallIntersect(spaceBeingExamined.getUpperLeftxCoordinate(),
            spaceBeingExamined.getUpperLeftyCoordinate(),
            spaceBeingExamined.getLowerRightxCoordinate(),
            spaceBeingExamined.getUpperLeftyCoordinate(),
            spaceInTheListOfAll.getUpperLeftxCoordinate(),
            spaceInTheListOfAll.getLowerRightyCoordinate() + 1,
            spaceInTheListOfAll.getLowerRightxCoordinate(),
            spaceInTheListOfAll.getLowerRightyCoordinate() + 1)) {

      return true;

      // Examine east wall.  upper left X upper left Y, upper left X; lower right Y of current room
      // and lower right X+1, upper left Y; lower right X+1, lower right Y.
    } else if (doesWallIntersect(spaceBeingExamined.getUpperLeftxCoordinate(),
            spaceBeingExamined.getUpperLeftyCoordinate(),
            spaceBeingExamined.getUpperLeftxCoordinate(),
            spaceBeingExamined.getLowerRightyCoordinate(),
            spaceInTheListOfAll.getLowerRightxCoordinate() + 1,
            spaceInTheListOfAll.getUpperLeftyCoordinate(),
            spaceInTheListOfAll.getLowerRightxCoordinate() + 1,
            spaceInTheListOfAll.getLowerRightyCoordinate())) {

      return true;
      // Examine south wall.  upper left X lower right Y, lower right X; lower right Y of current
      // room and upper left X, upper left Y-1; lower right X, upper left Y-1.
    } else if (doesWallIntersect(spaceBeingExamined.getUpperLeftxCoordinate(),
            spaceBeingExamined.getLowerRightyCoordinate(),
            spaceBeingExamined.getLowerRightxCoordinate(),
            spaceBeingExamined.getLowerRightyCoordinate(),
            spaceInTheListOfAll.getUpperLeftxCoordinate(),
            spaceInTheListOfAll.getUpperLeftyCoordinate() - 1,
            spaceInTheListOfAll.getLowerRightxCoordinate(),
            spaceInTheListOfAll.getUpperLeftyCoordinate() - 1)) {

      return true;
      // Examine west wall. lower right X upper left Y, lower right X; lower right Y of current room
      // and upper left X-1, upper left Y; upper left X-1, lower right Y.
    } else {
      return doesWallIntersect(spaceBeingExamined.getLowerRightxCoordinate(),
              spaceBeingExamined.getUpperLeftyCoordinate(),
              spaceBeingExamined.getLowerRightxCoordinate(),
              spaceBeingExamined.getLowerRightyCoordinate(),
              spaceInTheListOfAll.getUpperLeftxCoordinate() - 1,
              spaceInTheListOfAll.getUpperLeftyCoordinate(),
              spaceInTheListOfAll.getUpperLeftxCoordinate() - 1,
              spaceInTheListOfAll.getLowerRightyCoordinate());
    }
  }

  private boolean doesWallIntersect(int thisSpaceX1, int thisSpaceY1, int thisSpaceX2,
                                    int thisSpaceY2, int compareSpaceX1, int compareSpaceY1,
                                    int compareSpaceX2, int compareSpaceY2) {
    Line2D line1 = new Line2D.Float(thisSpaceX1, thisSpaceY1, thisSpaceX2, thisSpaceY2);
    Line2D line2 = new Line2D.Float(compareSpaceX1, compareSpaceY1, compareSpaceX2, compareSpaceY2);

    return line2.intersectsLine(line1);
  }

  private boolean doSpacesOverlap(Space newSpace, Space currentSpace) {

    // Represent incoming space as (bottom left) (top right) [col1, row1, col2, row2]
    int[] spaceOne = new int[]{newSpace.getUpperLeftxCoordinate(),
            newSpace.getLowerRightyCoordinate(),
            newSpace.getLowerRightxCoordinate(),
            newSpace.getUpperLeftyCoordinate()};


    int[] existingSpace = new int[]{currentSpace.getUpperLeftxCoordinate(),
            currentSpace.getLowerRightyCoordinate(),
            currentSpace.getLowerRightxCoordinate(),
            currentSpace.getUpperLeftyCoordinate()};

    // Need to add a +1 for inclusivity of the coordinates.
    return !(spaceOne[2] + 1 <= existingSpace[0] //Is space one to the left of existing
            || spaceOne[3] >= existingSpace[1] // Is space one below existing
            || spaceOne[0] >= existingSpace[2] // Is space one to the right of existing
            || spaceOne[1] + 1 >= existingSpace[3]);
  }

  private void buildGameLevelData(String[] gameData) {
    // We know the first two elements are the row and col of
    // the world.  Everything after is the name of it.
    rows = Integer.parseInt(gameData[0]);
    cols = Integer.parseInt(gameData[1]);

    if (rows < 2 || cols < 2) {
      throw new IllegalArgumentException("You need to have at least 2 rows and two columns.");
    }

    StringBuilder buildGameName = new StringBuilder();
    for (int i = 2; i < gameData.length; i++) {
      buildGameName.append(gameData[i]).append(" ");
    }


    gameName = buildGameName.toString().trim();

    if (gameName.isBlank()) {
      throw new IllegalArgumentException("The game must have a name.");
    }
  }

  private void buildTargetData(String[] gameData) {
    // first element is target health
    targetHealth = Integer.parseInt(gameData[0]);

    // Everything else is the name
    StringBuilder buildTargetName = new StringBuilder();
    for (int i = 1; i < gameData.length; i++) {
      buildTargetName.append(gameData[i]).append(" ");
    }

    // Set Target Name
    targetName = buildTargetName.toString().trim();
  }
}

