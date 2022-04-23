import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import gameinterfaces.playerinterfaces.Player;
import gameinterfaces.spaceinterfaces.Space;
import gameinterfaces.worldbuilderinterfaces.World;
import gamemodels.gamemanagermodels.MockWorld;
import gamemodels.gamemanagermodels.WorldImpl;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.Before;
import org.junit.Test;


/**
 * Class for testing an instance of a Build World class.
 */
public class WorldTest {
  private TestData testData;

  /**
   * Initialize some expected outputs.
   */
  @Before
  public void setup() {
    testData = new TestData();
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidConstructor() {
    new WorldImpl(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidConstructorSpaceOverlapping() {
    new WorldImpl(testData.getCortoMalteseData());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidConstructorSpaceOffTheGrid() {
    new WorldImpl(testData.badSpaceOffGrid());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidConstructorSpaceHasNegativeCoordinates() {
    new WorldImpl(testData.negativeCoordinates());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidConstructorNoPetName() {
    new WorldImpl(testData.noPetName());
  }

  @Test
  public void testValidConstructorGoodFilepath() {
    World game = new WorldImpl(testData.getCortoMalteseData());
    assertEquals("40;40;Corto Maltese", game.toString());
  }

  @Test
  public void testMoveTarget() {
    World game = new WorldImpl(testData.getCortoMalteseData());
    Space playerLocation = game.getTheSpaceByName("Beach Head One");
    game.addPlayer("Harley Quinn", playerLocation, 4);

    // Target starts at index 0
    assertEquals("* Turn of Harley Quinn 0/0 Turns * Pet: Baby Starro is here.\n", game
            .getCurrentPlayerTurnInfo());

    // Move target twice.
    game.moveTarget();
    game.moveTarget();

    // Target should be at index 2
    assertEquals("* Turn of Harley Quinn 0/0 Turns * Pet: Baby Starro is here.\n", game
            .getCurrentPlayerTurnInfo());
  }

  @Test
  public void testMoveTargetWrapAround() {
    World game = new WorldImpl(testData.getCortoMalteseData());
    Space playerLocation = game.getTheSpaceByName("Beach Head One");
    game.addPlayer("Harley Quinn", playerLocation, 4);

    // Target starts at index 0
    assertEquals("* Turn of Harley Quinn 0/0 Turns * Pet: Baby Starro is here.\n", game
            .getCurrentPlayerTurnInfo());

    // Move target past the last space.
    game.moveTarget();
    game.moveTarget();
    game.moveTarget();
    game.moveTarget();
    game.moveTarget();
    game.moveTarget();
    game.moveTarget();
    game.moveTarget();
    game.moveTarget();
    game.moveTarget();
    game.moveTarget();

    // Target should be at index 2
    assertEquals("* Turn of Harley Quinn 0/0 Turns * Pet: Baby Starro is here.\n", game
            .getCurrentPlayerTurnInfo());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetDescriptionOfSpaceByNoName() {
    World game = new WorldImpl(testData.getCortoMalteseData());
    assertEquals("Jotunheim", game.getTheSpaceByName(null));
  }

  @Test
  public void testGetNeighborsOfSpaceByIndex() {
    World game = new WorldImpl(testData.getCortoMalteseData());

    assertEquals(1, game.getNeighborsOfaSpace(8).size());
    assertEquals(2, game.getNeighborsOfaSpace(5).size());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetNeighborsOfSpaceByBadIndex() {
    World game = new WorldImpl(testData.getCortoMalteseData());
    game.getNeighborsOfaSpace(23);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetNeighborsOfSpaceByNegativeIndex() {
    World game = new WorldImpl(testData.getCortoMalteseData());
    game.getNeighborsOfaSpace(-1);
  }

  @Test
  public void testAddPlayer() {
    World game = new WorldImpl(testData.getCortoMalteseData());
    Space playerLocation = game.getTheSpaceByName("Beach Head Two");
    game.addPlayer("Harley Quinn", playerLocation, 4);

    assertEquals("Player Description:\n"
            + "\tName: Harley Quinn\n"
            + "\tItems:\n"
            + "\t\tHarley Quinn is carrying:\n"
            + "\tLocation: Beach Head Two", game.getCurrentPlayer().describePlayer());
  }

  @Test
  public void testAddComputerPlayer() {
    World game = new WorldImpl(testData.getCortoMalteseData());
    Space playerLocation = game.getTheSpaceByName("Beach Head Two");
    game.addComputerPlayer("Computer Harley Quinn", playerLocation, 4);

    assertEquals("Player Description:\n"
            + "\tName: Computer Harley Quinn\n"
            + "\tItems:\n"
            + "\t\tComputer Harley Quinn is carrying:\n"
            + "\tLocation: Beach Head Two", game.getCurrentPlayer().describePlayer());
  }

  @Test
  public void testPickUpItem() {
    World game = new WorldImpl(testData.getCortoMalteseData());
    Space playerLocation = game.getTheSpaceByName("Insurgent Camp");
    game.addPlayer("Harley Quinn", playerLocation, 4);

    assertEquals("Player Description:\n"
            + "\tName: Harley Quinn\n"
            + "\tItems:\n"
            + "\t\tHarley Quinn is carrying:\n"
            + "\tLocation: Insurgent Camp", game.getCurrentPlayer().describePlayer());

    game.getCurrentPlayer().takeItem("Blowgun");

    assertEquals("Player Description:\n"
            + "\tName: Harley Quinn\n"
            + "\tItems:\n"
            + "\t\tHarley Quinn is carrying:\n"
            + "Blowgun\n"
            + "\tLocation: Insurgent Camp", game.getCurrentPlayer().describePlayer());
  }

  @Test
  public void testPickUpMultipleItems() {
    World game = new WorldImpl(testData.getCortoMalteseData());
    Space playerLocation = game.getTheSpaceByName("Insurgent Camp");
    game.addPlayer("Harley Quinn", playerLocation, 4);

    assertEquals("Player Description:\n"
            + "\tName: Harley Quinn\n"
            + "\tItems:\n"
            + "\t\tHarley Quinn is carrying:\n"
            + "\tLocation: Insurgent Camp", game.getCurrentPlayer().describePlayer());

    game.getCurrentPlayer().takeItem("Blowgun");

    assertEquals("Player Description:\n"
            + "\tName: Harley Quinn\n"
            + "\tItems:\n"
            + "\t\tHarley Quinn is carrying:\n"
            + "Blowgun\n"
            + "\tLocation: Insurgent Camp", game.getCurrentPlayer().describePlayer());

    game.getCurrentPlayer().takeItem("Sling Shot");

    assertEquals("Player Description:\n"
            + "\tName: Harley Quinn\n"
            + "\tItems:\n"
            + "\t\tHarley Quinn is carrying:\n"
            + "Blowgun\n"
            + "Sling Shot\n"
            + "\tLocation: Insurgent Camp", game.getCurrentPlayer().describePlayer());
  }

  @Test(expected = IllegalStateException.class)
  public void testPickUpItemInAnotherSpace() {
    World game = new WorldImpl(testData.getCortoMalteseData());
    Space playerLocation = game.getTheSpaceByName("Insurgent Camp");
    game.addPlayer("Harley Quinn", playerLocation, 4);

    assertEquals("Player Description:\n"
            + "\tName: Harley Quinn\n"
            + "\tItems:\n"
            + "\t\tHarley Quinn is carrying:\n"
            + "\tLocation: Insurgent Camp", game.getCurrentPlayer().describePlayer());

    game.getCurrentPlayer().takeItem("Pistol");
  }

  @Test(expected = IllegalStateException.class)
  public void testPickUpNonExistingItem() {
    World game = new WorldImpl(testData.getCortoMalteseData());
    Space playerLocation = game.getTheSpaceByName("Insurgent Camp");
    game.addPlayer("Harley Quinn", playerLocation, 4);

    assertEquals("Player Description:\n"
            + "\tName: Harley Quinn\n"
            + "\tItems:\n"
            + "\t\tHarley Quinn is carrying:\n"
            + "\tLocation: Insurgent Camp", game.getCurrentPlayer().describePlayer());

    game.getCurrentPlayer().takeItem("Groundhog");
  }

  @Test(expected = IllegalStateException.class)
  public void testItemWasAlreadyPickedUp() {
    World game = new WorldImpl(testData.getCortoMalteseData());
    Space playerLocation = game.getTheSpaceByName("Insurgent Camp");
    game.addPlayer("Harley Quinn", playerLocation, 4);

    assertEquals("Player Description:\n"
            + "\tName: Harley Quinn\n"
            + "\tItems:\n"
            + "\t\tHarley Quinn is carrying:\n"
            + "\tLocation: Insurgent Camp", game.getCurrentPlayer().describePlayer());

    game.getCurrentPlayer().takeItem("Blowgun");
    game.getCurrentPlayer().takeItem("Blowgun");
  }

  @Test(expected = IllegalStateException.class)
  public void testTooManyItems() {
    World game = new WorldImpl(testData.getCortoMalteseData());
    Space playerLocation = game.getTheSpaceByName("Insurgent Camp");
    game.addPlayer("Harley Quinn", playerLocation, 1);

    assertEquals("Player Description:\n"
            + "\tName: Harley Quinn\n"
            + "\tItems:\n"
            + "\t\tHarley Quinn is carrying:\n"
            + "\tLocation: Insurgent Camp", game.getCurrentPlayer().describePlayer());

    game.getCurrentPlayer().takeItem("Blowgun");
    game.getCurrentPlayer().takeItem("Sling Shot");
  }

  @Test(expected = IllegalStateException.class)
  public void testMovePlayerToNonNeighbor() {
    World game = new WorldImpl(testData.getCortoMalteseData());
    Space playerLocation = game.getTheSpaceByName("Insurgent Camp");

    // Set the player to Insurgents camp
    game.addPlayer("Harley Quinn", playerLocation, 1);

    // Town is not a neighbor
    game.getCurrentPlayer().move("Town");
  }

  @Test
  public void testMovePlayerToNeighbor() {
    World game = new WorldImpl(testData.getCortoMalteseData());
    Space playerLocation = game.getTheSpaceByName("Insurgent Camp");

    // Set the player to Insurgents camp
    game.addPlayer("Harley Quinn", playerLocation, 1);

    // Forest is a neighbor
    game.getCurrentPlayer().move("Forest");
  }

  @Test
  public void testMovePlayerToMultipleNeighbors() {
    World game = new WorldImpl(testData.getCortoMalteseData());
    Space playerLocation = game.getTheSpaceByName("Insurgent Camp");

    // Set the player to Insurgents camp
    game.addPlayer("Harley Quinn", playerLocation, 1);

    game.getCurrentPlayer().move("Forest");
    game.getCurrentPlayer().move("Beach Head One");
  }

  @Test
  public void testBasicLookAroundAfterInitialization() {
    World game = new WorldImpl(testData.getCortoMalteseData());
    Space playerLocation = game.getTheSpaceByName("Insurgent Camp");
    String description;

    // Set the player to Insurgents camp
    game.addPlayer("Harley Quinn", playerLocation, 1);
    description = game.getCurrentPlayer().lookAround();

    assertEquals("Harley Quinn is in here in Insurgent Camp.\n"
            + "\n"
            + "Here is a list of items located here:\n"
            + "\tBlowgun\n"
            + "\tSling Shot\n"
            + "\tNanaway\n"
            + "\n"
            + "Target information for this room:\n"
            + "\tThe target is not here.\n"
            + "\n"
            + "Pet information for this room:\n"
            + "\tThe pet is not here.\n"
            + "\n"
            + "The following players are in the room:\n"
            + "\tNone.\n"
            + "\n"
            + "Here is the information about neighboring spaces:\n"
            + "\n"
            + "This space is Forest\n"
            + "Here is a list of items located here:\n"
            + "\tSmall Stick\n"
            + "\n"
            + "Target information for this room:\n"
            + "\tThe target is not here.\n"
            + "\n"
            + "The following players are in the room:\n"
            + "\tNone.", description);
  }

  @Test
  public void testLookAroundWithTargetInTheSpace() {
    World game = new WorldImpl(testData.getCortoMalteseData());
    Space playerLocation = game.getTheSpaceByName("Insurgent Camp");

    // Set the player to Insurgents camp
    game.addPlayer("Harley Quinn", playerLocation, 1);

    // move the target artificially
    game.moveTarget();
    game.moveTarget();
    game.moveTarget();
    game.moveTarget();

    String description = game.getCurrentPlayer().lookAround();

    assertEquals("Harley Quinn is in here in Insurgent Camp.\n"
            + "\n"
            + "Here is a list of items located here:\n"
            + "\tBlowgun\n"
            + "\tSling Shot\n"
            + "\tNanaway\n"
            + "\n"
            + "Target information for this room:\n"
            + "\tStarro The Conqueror is here.\n"
            + "\n"
            + "Pet information for this room:\n"
            + "\tThe pet is not here.\n"
            + "\n"
            + "The following players are in the room:\n"
            + "\tNone.\n"
            + "\n"
            + "Here is the information about neighboring spaces:\n"
            + "\n"
            + "This space is Forest\n"
            + "Here is a list of items located here:\n"
            + "\tSmall Stick\n"
            + "\n"
            + "Target information for this room:\n"
            + "\tThe target is not here.\n"
            + "\n"
            + "The following players are in the room:\n"
            + "\tNone.", description);
  }

  @Test
  public void testLookAroundWithTargetInTheSpaceAndMorePlayers() {
    World game = new WorldImpl(testData.getCortoMalteseData());
    Space playerLocation = game.getTheSpaceByName("Insurgent Camp");
    Space neighborLocation = game.getTheSpaceByName("Forest");

    // Set the player to Insurgents camp
    game.addPlayer("Harley Quinn", playerLocation, 1);
    game.addPlayer("Peacemaker", playerLocation, 3);
    game.addPlayer("Rat Catcher", neighborLocation, 10);

    // move the target artificially
    game.moveTarget();
    game.moveTarget();
    game.moveTarget();
    game.moveTarget();

    String description = game.getCurrentPlayer().lookAround();

    assertEquals("Harley Quinn is in here in Insurgent Camp.\n"
            + "\n"
            + "Here is a list of items located here:\n"
            + "\tBlowgun\n"
            + "\tSling Shot\n"
            + "\tNanaway\n"
            + "\n"
            + "Target information for this room:\n"
            + "\tStarro The Conqueror is here.\n"
            + "\n"
            + "Pet information for this room:\n"
            + "\tThe pet is not here.\n"
            + "\n"
            + "The following players are in the room:\n"
            + "Peacemaker is in Insurgent Camp\n"
            + "\n"
            + "Here is the information about neighboring spaces:\n"
            + "\n"
            + "This space is Forest\n"
            + "Here is a list of items located here:\n"
            + "\tSmall Stick\n"
            + "\n"
            + "Target information for this room:\n"
            + "\tThe target is not here.\n"
            + "\n"
            + "The following players are in the room:\n"
            + "Rat Catcher is in Forest", description);
  }

  @Test
  public void testLookAroundWithTargetInTheSpaceAndMorePlayersThenPickUpItem() {
    World game = new WorldImpl(testData.getCortoMalteseData());
    Space playerLocation = game.getTheSpaceByName("Insurgent Camp");
    Space neighborLocation = game.getTheSpaceByName("Forest");

    // Set the player to Insurgents camp
    game.addPlayer("Harley Quinn", playerLocation, 1);
    game.addPlayer("Peacemaker", playerLocation, 3);
    game.addPlayer("Rat Catcher", neighborLocation, 10);

    // move the target artificially
    game.moveTarget();
    game.moveTarget();
    game.moveTarget();
    game.moveTarget();

    // Harley's turn
    String description = game.getCurrentPlayer().lookAround();

    // Move the turn forward
    game.nextTurn();

    assertEquals("Harley Quinn is in here in Insurgent Camp.\n"
            + "\n"
            + "Here is a list of items located here:\n"
            + "\tBlowgun\n"
            + "\tSling Shot\n"
            + "\tNanaway\n"
            + "\n"
            + "Target information for this room:\n"
            + "\tStarro The Conqueror is here.\n"
            + "\n"
            + "Pet information for this room:\n"
            + "\tThe pet is not here.\n"
            + "\n"
            + "The following players are in the room:\n"
            + "Peacemaker is in Insurgent Camp\n"
            + "\n"
            + "Here is the information about neighboring spaces:\n"
            + "\n"
            + "This space is Forest\n"
            + "Here is a list of items located here:\n"
            + "\tSmall Stick\n"
            + "\n"
            + "Target information for this room:\n"
            + "\tThe target is not here.\n"
            + "\n"
            + "The following players are in the room:\n"
            + "Rat Catcher is in Forest", description);

    // Peacemaker's turn
    game.getCurrentPlayer().takeItem("Sling Shot");

    // Move the turn to next player
    game.nextTurn();

    // Rat Catcher
    description = game.getCurrentPlayer().lookAround();

    assertEquals("Rat Catcher is in here in Forest.\n"
            + "\n"
            + "Here is a list of items located here:\n"
            + "\tSmall Stick\n"
            + "\n"
            + "Target information for this room:\n"
            + "\tThe target is not here.\n"
            + "\n"
            + "Pet information for this room:\n"
            + "\tThe pet is not here.\n"
            + "\n"
            + "The following players are in the room:\n"
            + "\tNone.\n"
            + "\n"
            + "Here is the information about neighboring spaces:\n"
            + "\n"
            + "This space is Beach Head One\n"
            + "This space is Insurgent Camp\n"
            + "Here is a list of items located here:\n"
            + "\tBlowgun\n"
            + "\tNanaway\n"
            + "\n"
            + "Target information for this room:\n"
            + "\tStarro The Conqueror is here.\n"
            + "\n"
            + "The following players are in the room:\n"
            + "Harley Quinn is in Insurgent Camp\n"
            + "Peacemaker is in Insurgent Camp", description);
  }

  @Test
  public void testComputerAttackWhenNoPlayersAround() {
    World game = new WorldImpl(testData.getCortoMalteseData());
    game.addComputerPlayer("Harley Quinn", game.getTheSpaceByName("Beach head One"),
            3);
    game.addPlayer("Peacemaker", game.getTheSpaceByName("Town"),
            3);
    Player harley = game.getCurrentPlayer();
    harley.takeItem("helicopter");
    harley.takeItem("boomerang");

    // Make sure Harley has items
    assertEquals("Harley Quinn is carrying:\n"
            + "Helicopter\n"
            + "Boomerang\n", harley.getPlayerItemsDescription());

    // Make sure target is here but players are not.
    assertEquals("Harley Quinn is in here in Beach Head One.\n"
            + "\n"
            + "Here is a list of items located here:\n"
            + "\tMongal\n"
            + "\tDetachable Arms\n"
            + "\n"
            + "Target information for this room:\n"
            + "\tStarro The Conqueror is here.\n"
            + "\n"
            + "Pet information for this room:\n"
            + "\tBaby Starro is here.\n"
            + "\n"
            + "The following players are in the room:\n"
            + "\tNone.\n"
            + "\n"
            + "Here is the information about neighboring spaces:\n"
            + "\n"
            + "This space is Beach Head Two\n"
            + "Here is a list of items located here:\n"
            + "\tPistol\n"
            + "\tOcean Water\n"
            + "\tConch Shell\n"
            + "\n"
            + "Target information for this room:\n"
            + "\tThe target is not here.\n"
            + "\n"
            + "The following players are in the room:\n"
            + "\tNone.\n"
            + "This space is Forest\n"
            + "Here is a list of items located here:\n"
            + "\tSmall Stick\n"
            + "\n"
            + "Target information for this room:\n"
            + "\tThe target is not here.\n"
            + "\n"
            + "The following players are in the room:\n"
            + "\tNone.", harley.lookAround());

    // Computer should attack the target
    String result = harley.takeRandomAction(game);

    assertEquals("You attacked with Helicopter and did 5 points of damage to Starro "
            + "The Conqueror.  Helicopter is now evidence.  Starro The Conqueror now has 70 "
            + "health remaining.", result);

    // Item to evidence
    assertEquals("Harley Quinn is carrying:\n"
            + "Boomerang\n", harley.getPlayerItemsDescription());
  }

  @Test
  public void testComputerAttackWithPetAndPlayerNeighbors() {
    World game = new WorldImpl(testData.getCortoMalteseData());
    game.addComputerPlayer("Harley Quinn", game.getTheSpaceByName("Beach head One"),
            3);
    game.addPlayer("Peacemaker", game.getTheSpaceByName("Forest"),
            3);
    Player harley = game.getCurrentPlayer();
    harley.takeItem("Detachable Arms");
    harley.takeItem("boomerang");

    // Make sure Harley has items
    assertEquals("Harley Quinn is carrying:\n"
            + "Detachable Arms\n"
            + "Boomerang\n", harley.getPlayerItemsDescription());

    // Make sure target is here but players are not.
    assertEquals("Harley Quinn is in here in Beach Head One.\n"
            + "\n"
            + "Here is a list of items located here:\n"
            + "\tHelicopter\n"
            + "\tMongal\n"
            + "\n"
            + "Target information for this room:\n"
            + "\tStarro The Conqueror is here.\n"
            + "\n"
            + "Pet information for this room:\n"
            + "\tBaby Starro is here.\n"
            + "\n"
            + "The following players are in the room:\n"
            + "\tNone.\n"
            + "\n"
            + "Here is the information about neighboring spaces:\n"
            + "\n"
            + "This space is Beach Head Two\n"
            + "Here is a list of items located here:\n"
            + "\tPistol\n"
            + "\tOcean Water\n"
            + "\tConch Shell\n"
            + "\n"
            + "Target information for this room:\n"
            + "\tThe target is not here.\n"
            + "\n"
            + "The following players are in the room:\n"
            + "\tNone.\n"
            + "This space is Forest\n"
            + "Here is a list of items located here:\n"
            + "\tSmall Stick\n"
            + "\n"
            + "Target information for this room:\n"
            + "\tThe target is not here.\n"
            + "\n"
            + "The following players are in the room:\n"
            + "Peacemaker is in Forest", harley.lookAround());

    // Computer should attack the target
    String result = harley.takeRandomAction(game);

    assertEquals("You attacked with Boomerang and did 2 points of damage to Starro The "
            + "Conqueror.  Boomerang is now evidence.  Starro The Conqueror now has 73 health "
            + "remaining.", result);

    // Item to evidence
    assertEquals("Harley Quinn is carrying:\n"
            + "Detachable Arms\n", harley.getPlayerItemsDescription());
  }

  @Test
  public void testComputerDoesNotAttackWhenPlayersAround() {
    World game = new WorldImpl(testData.getCortoMalteseData());
    game.addComputerPlayer("Harley Quinn", game.getTheSpaceByName("Beach head One"),
            3);
    game.addPlayer("Peacemaker", game.getTheSpaceByName("Beach Head One"),
            3);
    Player harley = game.getCurrentPlayer();
    harley.takeItem("helicopter");
    harley.takeItem("boomerang");

    // Make sure Harley has items
    assertEquals("Harley Quinn is carrying:\n"
            + "Helicopter\n"
            + "Boomerang\n", harley.getPlayerItemsDescription());

    // Make sure target is here but players are not.
    assertEquals("Harley Quinn is in here in Beach Head One.\n"
            + "\n"
            + "Here is a list of items located here:\n"
            + "\tMongal\n"
            + "\tDetachable Arms\n"
            + "\n"
            + "Target information for this room:\n"
            + "\tStarro The Conqueror is here.\n"
            + "\n"
            + "Pet information for this room:\n"
            + "\tBaby Starro is here.\n"
            + "\n"
            + "The following players are in the room:\n"
            + "Peacemaker is in Beach Head One\n"
            + "\n"
            + "Here is the information about neighboring spaces:\n"
            + "\n"
            + "This space is Beach Head Two\n"
            + "Here is a list of items located here:\n"
            + "\tPistol\n"
            + "\tOcean Water\n"
            + "\tConch Shell\n"
            + "\n"
            + "Target information for this room:\n"
            + "\tThe target is not here.\n"
            + "\n"
            + "The following players are in the room:\n"
            + "\tNone.\n"
            + "This space is Forest\n"
            + "Here is a list of items located here:\n"
            + "\tSmall Stick\n"
            + "\n"
            + "Target information for this room:\n"
            + "\tThe target is not here.\n"
            + "\n"
            + "The following players are in the room:\n"
            + "\tNone.", harley.lookAround());

    // Computer should attack the target
    String result = harley.takeRandomAction(game);

    // If harley attacked it would be the string below.
    assertNotEquals("You attacked with Helicopter and did 5 points of damage to Starro "
            + "The Conqueror.  Helicopter is now evidence.  Starro The Conqueror now has 70 "
            + "health remaining.", result);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMaxTurnsNegative() {
    World game = new WorldImpl(testData.getCortoMalteseData());

    game.setMaxNumberOfTurns(-1);
  }

  @Test
  public void testSetAndGetMaxTurns() {
    World game = new WorldImpl(testData.getCortoMalteseData());
    assertEquals(0, game.getMaxNumberOfTurns());
    game.setMaxNumberOfTurns(4);
    assertEquals(4, game.getMaxNumberOfTurns());
  }

  @Test
  public void testGetAvailableLocations() {
    World game = new WorldImpl(testData.getCortoMalteseData());
    assertEquals("Beach Head One, Beach Head Two, Town, Forest, Insurgent Camp, Road, "
            + "Palace, Guard House, Jotunheim", game.getAvailableLocations());
  }

  //TODO: REMOVE THIS!
  @Test
  public void testMock() {
    MockWorld mock = new MockWorld();
    mock.getTurnTotal();
    assertEquals("", mock.getMockLog().get(mock.getMockLog().size() - 1));
  }
}
