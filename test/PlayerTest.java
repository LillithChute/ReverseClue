import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import gameinterfaces.iteminterfaces.Item;
import gameinterfaces.playerinterfaces.Player;
import gameinterfaces.spaceinterfaces.Space;
import gameinterfaces.worldbuilderinterfaces.World;
import gamemodels.gamemanagermodels.WorldImpl;
import gamemodels.playermodels.ComputerPlayerImpl;
import gamemodels.playermodels.PlayerImpl;
import instancecreationhelpers.InstanceBuilder;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

/**
 * This file will test the functionality of the Player class.
 */
public class PlayerTest {

  private InstanceBuilder getInstance;
  private Item itemGun;
  private Item itemKnife;
  private Item itemJavenlin;
  private Space beachHeadTwo;
  private Space beachHeadOne;
  private Space jotunheim;
  private Space forest;
  private List<Space> neighbors;
  private List<Space> differentNeighbors;
  private TestData testData;

  /**
   * Initialize some expected outputs.
   */
  @Before
  public void setup() {
    getInstance = new InstanceBuilder();
    itemGun = getInstance.itemBuilder("Gun", 4, 0);
    itemKnife = getInstance.itemBuilder("Knife", 2, 1);
    itemJavenlin = getInstance.itemBuilder("Javelin", 3, 2);
    beachHeadTwo = getInstance.spaceBuilder(1, "Beach Head Two",  2,  5,  8, 11);
    beachHeadOne = getInstance.spaceBuilder(0, "Beach Head One",  9,  2, 35,  5);
    jotunheim = getInstance.spaceBuilder(8, "Jotunheim",  10, 33, 30, 39);
    forest = getInstance.spaceBuilder(3, "Forest",  16,  6, 30, 13);

    // Add items to location
    List<Item> itemsBeach = new ArrayList<>();
    itemsBeach.add(itemKnife);
    itemsBeach.add(itemGun);
    itemsBeach.add(itemJavenlin);
    beachHeadTwo.putItemsInTheSpace(itemsBeach);

    List<Item> itemsForest = new ArrayList<>();
    itemsForest.add(itemJavenlin);
    forest.putItemsInTheSpace(itemsForest);

    // Add neighbors
    neighbors = new ArrayList<>();
    neighbors.add(jotunheim);
    neighbors.add(forest);
    neighbors.add(beachHeadOne);
    beachHeadTwo.setNeighborsOfThisSpace(neighbors);

    differentNeighbors = new ArrayList<>();
    differentNeighbors.add(beachHeadTwo);
    beachHeadOne.setNeighborsOfThisSpace(differentNeighbors);

    // Set up additional test data for moving pets
    testData = new TestData();
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidConstructorNullPlayerName() {
    getInstance.playerBuilder(null, beachHeadTwo, 4);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidConstructorBlankPlayerName() {
    getInstance.playerBuilder("  ", beachHeadTwo, 4);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidConstructorEmptyPlayerName() {
    getInstance.playerBuilder("", beachHeadTwo, 4);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidConstructorPlayerLocationNull() {
    getInstance.playerBuilder("Harley Quinn", null, 4);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidConstructorPlayerItemLimitNegative() {
    getInstance.playerBuilder("Harley Quinn", beachHeadTwo, -4);
  }

  @Test
  public void testValidConstructor() {
    Player newPlayer = getInstance.playerBuilder("Harley Quinn", beachHeadTwo, 4);
    assertEquals("Harley Quinn;1;4", newPlayer.toString());
  }

  @Test
  public void testToStringRightAfterConstructor() {
    Player newPlayer = getInstance.playerBuilder("Harley Quinn", beachHeadTwo, 4);
    assertEquals("Harley Quinn;1;4", newPlayer.toString());
  }

  @Test
  public void testGetPlayerName() {
    Player newPlayer = getInstance.playerBuilder("Harley Quinn", beachHeadTwo, 4);
    assertEquals("Harley Quinn", newPlayer.getPlayerName());
  }

  @Test
  public void testTakeItem() {
    Player newPlayer = getInstance.playerBuilder("Harley Quinn", beachHeadTwo, 4);
    assertEquals("Harley Quinn;1;4", newPlayer.toString());

    // Add an item
    newPlayer.takeItem("Gun");
    assertEquals("Harley Quinn;1;4;Gun", newPlayer.toString());
  }

  @Test
  public void testTakeMultipleItems() {
    Player newPlayer = getInstance.playerBuilder("Harley Quinn", beachHeadTwo, 4);
    assertEquals("Harley Quinn;1;4", newPlayer.toString());

    // Add items
    newPlayer.takeItem("Gun");
    newPlayer.takeItem("Knife");
    assertEquals("Harley Quinn;1;4;Gun;Knife", newPlayer.toString());
  }

  @Test
  public void testGetPlayerItems() {
    Player newPlayer = getInstance.playerBuilder("Harley Quinn", beachHeadTwo, 4);
    assertEquals("Harley Quinn;1;4", newPlayer.toString());

    // Add items
    newPlayer.takeItem("Gun");
    newPlayer.takeItem("Knife");
    assertEquals("Harley Quinn is carrying:\n"
            + "Gun\n"
            + "Knife\n", newPlayer.getPlayerItemsDescription());
  }

  @Test(expected = IllegalStateException.class)
  public void testExceedMaxNumberOfPlayerItems() {
    Player newPlayer = getInstance.playerBuilder("Harley Quinn", beachHeadTwo, 2);

    // Add items
    newPlayer.takeItem("Gun");
    newPlayer.takeItem("Knife");
    newPlayer.takeItem("Javellin");
  }

  @Test
  public void testGetLocation() {
    Player newPlayer = getInstance.playerBuilder("Harley Quinn", beachHeadTwo, 4);
    assertEquals(1, newPlayer.getLocation());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidMoveNull() {
    Player newPlayer = getInstance.playerBuilder("Harley Quinn", beachHeadTwo, 4);
    newPlayer.move(null);
  }

  @Test
  public void testMove() {
    Player newPlayer = getInstance.playerBuilder("Harley Quinn", beachHeadTwo, 4);
    assertEquals(1, newPlayer.getLocation());
    newPlayer.move("Jotunheim");
    assertEquals(8, newPlayer.getLocation());
  }

  @Test(expected = IllegalStateException.class)
  public void testMoveNotNeighbor() {
    Player newPlayer = getInstance.playerBuilder("Harley Quinn", beachHeadTwo, 4);
    assertEquals(1, newPlayer.getLocation());
    newPlayer.move("Insurgent Camp");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveEmptyString() {
    Player newPlayer = getInstance.playerBuilder("Harley Quinn", beachHeadTwo, 4);
    assertEquals(1, newPlayer.getLocation());
    newPlayer.move("");
  }

  @Test
  public void testIsHuman() {
    Player newPlayer = getInstance.playerBuilder("Harley Quinn", beachHeadTwo, 4);
    assertTrue(newPlayer instanceof PlayerImpl);
  }

  @Test
  public void testIsComputer() {
    Player newPlayer = new ComputerPlayerImpl("Harley Quinn", beachHeadTwo, 4);
    assertTrue(newPlayer instanceof ComputerPlayerImpl);
  }

  @Test
  public void testDescribePlayerNoItems() {
    Player newPlayer = getInstance.playerBuilder("Harley Quinn", beachHeadTwo, 4);
    newPlayer.move("Forest");
    assertEquals("Player Description:\n"
            + "\tName: Harley Quinn\n"
            + "\tItems:\n"
            + "\t\tHarley Quinn is carrying:\n"
            + "\tLocation: Forest", newPlayer.describePlayer());
  }

  @Test
  public void testDescribePlayerWithItems() {
    Player newPlayer = getInstance.playerBuilder("Harley Quinn", beachHeadTwo, 4);
    newPlayer.move("Forest");
    newPlayer.takeItem("Javelin");

    assertEquals("Player Description:\n"
            + "\tName: Harley Quinn\n"
            + "\tItems:\n"
            + "\t\tHarley Quinn is carrying:\n"
            + "Javelin\n"
            + "\tLocation: Forest", newPlayer.describePlayer());
  }

  @Test
  public void testCanPlayerBeSeenWhenItIsOnlyThemInSpaceNoNeighbors() {
    Player newPlayer = getInstance.playerBuilder("Harley Quinn", beachHeadTwo, 4);
    assertEquals(false, newPlayer.canPlayerBeSeen());
  }

  @Test
  public void testCanPlayerBeSeenWhenAnotherPlayerInTheSpace() {
    Player newPlayer = getInstance.playerBuilder("Harley Quinn", beachHeadTwo, 4);
    Player secondPlayer = getInstance.playerBuilder("Rat Catcher", beachHeadTwo, 4);
    assertEquals(true, newPlayer.canPlayerBeSeen());
  }

  @Test
  public void testCanPlayerBeSeenWhenPlayersAreInNeighbors() {
    Player newPlayer = getInstance.playerBuilder("Harley Quinn", beachHeadTwo, 4);
    Player secondPlayer = getInstance.playerBuilder("Rat Catcher", forest, 4);
    Player thirdPlayer = getInstance.playerBuilder("Peacemaker", jotunheim, 4);
    assertEquals(true, newPlayer.canPlayerBeSeen());
  }

  @Test
  public void testPlayerCanBeSeenThenMovesAndCannotBeSeen() {
    Player newPlayer = getInstance.playerBuilder("Harley Quinn", beachHeadTwo, 4);
    Player secondPlayer = getInstance.playerBuilder("Rat Catcher", forest, 4);
    assertEquals(true, newPlayer.canPlayerBeSeen());
    newPlayer.move("Beach Head One");
    assertEquals(false, newPlayer.canPlayerBeSeen());
  }

  @Test
  public void testMovePet() {
    World game = new WorldImpl(testData.getCortoMalteseData());
    game.addPlayer("Harley Quinn", game.getTheSpaceByName("Beach Head Two"),
            3);
    Player newPlayer = game.getCurrentPlayer();

    // Pet will be in space 0
    assertEquals("Baby Starro", game.getSpaces().get(0).getPet().getName());

    // Move pet to forest
    Space petSpaceToGoTo = game.getTheSpaceByName("forest");
    newPlayer.movePet(petSpaceToGoTo, game.getSpaces());

    // Is the pet there
    assertEquals("Baby Starro", game.getTheSpaceByName("forest").getPet()
            .getName());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMovePetNoNonExistingSpace() {
    World game = new WorldImpl(testData.getCortoMalteseData());
    game.addPlayer("Harley Quinn", game.getTheSpaceByName("Beach Head Two"),
            3);
    Player newPlayer = game.getCurrentPlayer();

    // Pet will be in space 0
    assertEquals("Baby Starro", game.getSpaces().get(0).getPet().getName());

    // Move pet to forest
    Space petSpaceToGoTo = game.getTheSpaceByName("Garbage");
  }

  @Test
  public void testLookAroundWithPetInNeighboringSpaceThenMovePet() {
    World game = new WorldImpl(testData.getCortoMalteseData());
    game.addPlayer("Harley Quinn", game.getTheSpaceByName("Beach Head Two"),
            3);
    Player newPlayer = game.getCurrentPlayer();

    // Beach head two has a neighbor, beach head one.  We should not see anything for beach head
    // one, which is where the target and the pet are.
    assertEquals("Harley Quinn is in here in Beach Head Two.\n"
            + "\n"
            + "Here is a list of items located here:\n"
            + "\tPistol\n"
            + "\tOcean Water\n"
            + "\tConch Shell\n"
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
            + "This space is Beach Head One", newPlayer.lookAround());

    // Move the pet and we should see Beach Head One stuff
    newPlayer.movePet(game.getTheSpaceByName("forest"), game.getSpaces());

    assertEquals("Harley Quinn is in here in Beach Head Two.\n"
            + "\n"
            + "Here is a list of items located here:\n"
            + "\tPistol\n"
            + "\tOcean Water\n"
            + "\tConch Shell\n"
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
            + "Here is a list of items located here:\n"
            + "\tHelicopter\n"
            + "\tMongal\n"
            + "\tDetachable Arms\n"
            + "\tBoomerang\n"
            + "\n"
            + "Target information for this room:\n"
            + "\tStarro The Conqueror is here.\n"
            + "\n"
            + "The following players are in the room:\n"
            + "\tNone.", newPlayer.lookAround());
  }

  @Test
  public void testLookAroundWithPetInPlayersSpace() {
    World game = new WorldImpl(testData.getCortoMalteseData());
    game.addPlayer("Harley Quinn", game.getTheSpaceByName("Forest"),
            3);
    Player newPlayer = game.getCurrentPlayer();

    // Move the pet to the Forest
    newPlayer.movePet(game.getTheSpaceByName("forest"), game.getSpaces());

    // Beach head two has a neighbor, beach head one.  We should not see anything for beach head
    // one, which is where the target and the pet are.
    assertEquals("Harley Quinn is in here in Forest.\n"
            + "\n"
            + "Here is a list of items located here:\n"
            + "\tSmall Stick\n"
            + "\n"
            + "Target information for this room:\n"
            + "\tThe target is not here.\n"
            + "\n"
            + "Pet information for this room:\n"
            + "\tBaby Starro is here.\n"
            + "\n"
            + "The following players are in the room:\n"
            + "\tNone.\n"
            + "\n"
            + "Here is the information about neighboring spaces:\n"
            + "\n"
            + "This space is Beach Head One\n"
            + "Here is a list of items located here:\n"
            + "\tHelicopter\n"
            + "\tMongal\n"
            + "\tDetachable Arms\n"
            + "\tBoomerang\n"
            + "\n"
            + "Target information for this room:\n"
            + "\tStarro The Conqueror is here.\n"
            + "\n"
            + "The following players are in the room:\n"
            + "\tNone.\n"
            + "This space is Insurgent Camp\n"
            + "Here is a list of items located here:\n"
            + "\tBlowgun\n"
            + "\tSling Shot\n"
            + "\tNanaway\n"
            + "\n"
            + "Target information for this room:\n"
            + "\tThe target is not here.\n"
            + "\n"
            + "The following players are in the room:\n"
            + "\tNone.", newPlayer.lookAround());
  }

  @Test
  public void testLookAroundWithPetInPlayersSpaceThenMoveFromSpace() {
    World game = new WorldImpl(testData.getCortoMalteseData());
    game.addPlayer("Harley Quinn", game.getTheSpaceByName("Forest"),
            3);
    Player newPlayer = game.getCurrentPlayer();

    // Move the pet to the Forest
    newPlayer.movePet(game.getTheSpaceByName("forest"), game.getSpaces());

    // Beach head two has a neighbor, beach head one.  We should not see anything for beach head
    // one, which is where the target and the pet are.
    assertEquals("Harley Quinn is in here in Forest.\n"
            + "\n"
            + "Here is a list of items located here:\n"
            + "\tSmall Stick\n"
            + "\n"
            + "Target information for this room:\n"
            + "\tThe target is not here.\n"
            + "\n"
            + "Pet information for this room:\n"
            + "\tBaby Starro is here.\n"
            + "\n"
            + "The following players are in the room:\n"
            + "\tNone.\n"
            + "\n"
            + "Here is the information about neighboring spaces:\n"
            + "\n"
            + "This space is Beach Head One\n"
            + "Here is a list of items located here:\n"
            + "\tHelicopter\n"
            + "\tMongal\n"
            + "\tDetachable Arms\n"
            + "\tBoomerang\n"
            + "\n"
            + "Target information for this room:\n"
            + "\tStarro The Conqueror is here.\n"
            + "\n"
            + "The following players are in the room:\n"
            + "\tNone.\n"
            + "This space is Insurgent Camp\n"
            + "Here is a list of items located here:\n"
            + "\tBlowgun\n"
            + "\tSling Shot\n"
            + "\tNanaway\n"
            + "\n"
            + "Target information for this room:\n"
            + "\tThe target is not here.\n"
            + "\n"
            + "The following players are in the room:\n"
            + "\tNone.", newPlayer.lookAround());

    // move player then look around.  Shouldn't see anything in the forest
    newPlayer.move("Insurgent Camp");

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
            + "This space is Forest", newPlayer.lookAround());
  }

  //region Attack Tests
  @Test
  public void testAttackWithAnotherPlayerInTheRoom() {
    World game = new WorldImpl(testData.getCortoMalteseData());
    game.addPlayer("Harley Quinn", game.getTheSpaceByName("Beach head One"),
            3);
    game.addPlayer("Peacemaker", game.getTheSpaceByName("Beach head One"),
            3);
    Player harley = game.getCurrentPlayer();
    harley.takeItem("helicopter");
    harley.takeItem("boomerang");

    // Make sure Harley has items
    assertEquals("Harley Quinn is carrying:\n"
            + "Helicopter\n"
            + "Boomerang\n", harley.getPlayerItemsDescription());

    // Make sure target and players are in her space.
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

    // Attack the target
    String result = harley.attack("helicopter");

    assertEquals("You were seen!  Your attack was not successful.  "
            + "Helicopter is now evidence.", result);
    assertEquals("Harley Quinn is carrying:\n"
            + "Boomerang\n", harley.getPlayerItemsDescription());
  }

  @Test
  public void testAttackWithAnotherPlayerInNeighboringRoom() {
    World game = new WorldImpl(testData.getCortoMalteseData());
    game.addPlayer("Harley Quinn", game.getTheSpaceByName("Beach head One"),
            3);
    game.addPlayer("Peacemaker", game.getTheSpaceByName("Beach head two"),
            3);
    Player harley = game.getCurrentPlayer();
    harley.takeItem("helicopter");
    harley.takeItem("boomerang");

    // Move the pet away for this test
    harley.movePet(game.getTheSpaceByName("Town"), game.getSpaces());

    // Make sure Harley has items
    assertEquals("Harley Quinn is carrying:\n"
            + "Helicopter\n"
            + "Boomerang\n", harley.getPlayerItemsDescription());

    // Make sure target and players are in her space.
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
            + "\tThe pet is not here.\n"
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
            + "Peacemaker is in Beach Head Two\n"
            + "This space is Forest\n"
            + "Here is a list of items located here:\n"
            + "\tSmall Stick\n"
            + "\n"
            + "Target information for this room:\n"
            + "\tThe target is not here.\n"
            + "\n"
            + "The following players are in the room:\n"
            + "\tNone.", harley.lookAround());

    // Attack the target
    String result = harley.attack("boomerang");

    assertEquals("You were seen!  Your attack was not successful.  "
            + "Boomerang is now evidence.", result);

    // Item to evidence
    assertEquals("Harley Quinn is carrying:\n"
            + "Helicopter\n", harley.getPlayerItemsDescription());
  }

  @Test
  public void testAttackWithAnotherPlayerInNeighboringRoomWithPet() {
    World game = new WorldImpl(testData.getCortoMalteseData());
    game.addPlayer("Harley Quinn", game.getTheSpaceByName("Beach head Two"),
            3);
    game.addPlayer("Peacemaker", game.getTheSpaceByName("Beach head One"),
            3);
    Player harley = game.getCurrentPlayer();
    harley.takeItem("Pistol");
    harley.takeItem("Conch Shell");

    // Move target to Beach Head Two
    game.moveTarget();

    // Make sure Harley has items
    assertEquals("Harley Quinn is carrying:\n"
            + "Pistol\n"
            + "Conch Shell\n", harley.getPlayerItemsDescription());

    // Make sure target and players are in her space.
    assertEquals("Harley Quinn is in here in Beach Head Two.\n"
            + "\n"
            + "Here is a list of items located here:\n"
            + "\tOcean Water\n"
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
            + "This space is Beach Head One", harley.lookAround());

    // Attack the target
    String result = harley.attack("Pistol");

    assertEquals("You were seen!  Your attack was not successful.  "
            + "Pistol is now evidence.", result);

    // Item to evidence
    assertEquals("Harley Quinn is carrying:\n"
            + "Conch Shell\n", harley.getPlayerItemsDescription());
  }

  @Test(expected = IllegalStateException.class)
  public void testAttackWithAnotherPlayerInNeighboringRoomWithPetTargetNotHere() {
    World game = new WorldImpl(testData.getCortoMalteseData());
    game.addPlayer("Harley Quinn", game.getTheSpaceByName("Beach head Two"),
            3);
    game.addPlayer("Peacemaker", game.getTheSpaceByName("Beach head One"),
            3);
    Player harley = game.getCurrentPlayer();
    harley.takeItem("Pistol");
    harley.takeItem("Conch Shell");

    // Make sure Harley has items
    assertEquals("Harley Quinn is carrying:\n"
            + "Pistol\n"
            + "Conch Shell\n", harley.getPlayerItemsDescription());

    // Make sure target and players are in her space.
    assertEquals("Harley Quinn is in here in Beach Head Two.\n"
            + "\n"
            + "Here is a list of items located here:\n"
            + "\tOcean Water\n"
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
            + "This space is Beach Head One", harley.lookAround());

    // Attack the target
    harley.attack("Pistol");
  }

  @Test
  public void testAttackWithPokeInTheEye() {
    World game = new WorldImpl(testData.getCortoMalteseData());
    game.addPlayer("Harley Quinn", game.getTheSpaceByName("Beach head One"),
            3);
    Player harley = game.getCurrentPlayer();

    // Make sure Harley has items
    assertEquals("Harley Quinn is carrying:\n", harley.getPlayerItemsDescription());

    // Make sure target is in her space.
    assertEquals("Harley Quinn is in here in Beach Head One.\n"
            + "\n"
            + "Here is a list of items located here:\n"
            + "\tHelicopter\n"
            + "\tMongal\n"
            + "\tDetachable Arms\n"
            + "\tBoomerang\n"
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

    // Attack the target
    String result = harley.attack("Poke in the Eye");

    assertEquals("You poked Starro The Conqueror in the eye and did 1 point of damage!"
            + "  Starro The Conqueror now has 74 health remaining.", result);

    // Item to evidence
    assertEquals("Harley Quinn is carrying:\n", harley.getPlayerItemsDescription());
  }

  @Test
  public void testAttackWithItemAndPetInRoomPlayersInNeighboringSpace() {
    World game = new WorldImpl(testData.getCortoMalteseData());
    game.addPlayer("Harley Quinn", game.getTheSpaceByName("Beach head One"),
            3);
    game.addPlayer("Peacemaker", game.getTheSpaceByName("Forest"),
            3);
    Player harley = game.getCurrentPlayer();
    harley.takeItem("helicopter");
    harley.takeItem("boomerang");

    // Make sure Harley has items
    assertEquals("Harley Quinn is carrying:\n"
            + "Helicopter\n"
            + "Boomerang\n", harley.getPlayerItemsDescription());

    // Make sure target is in her space.
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
            + "Peacemaker is in Forest", harley.lookAround());

    // Attack the target
    String result = harley.attack("helicopter");

    assertEquals("You attacked with Helicopter and did 5 points of damage to "
            + "Starro The Conqueror.  Helicopter is now evidence.  Starro The Conqueror "
            + "now has 70 health remaining.", result);

    // Item to evidence
    assertEquals("Harley Quinn is carrying:\n"
            + "Boomerang\n", harley.getPlayerItemsDescription());
  }

  @Test
  public void testAttackWithItem() {
    World game = new WorldImpl(testData.getCortoMalteseData());
    game.addPlayer("Harley Quinn", game.getTheSpaceByName("Beach head One"),
            3);
    Player harley = game.getCurrentPlayer();
    harley.takeItem("helicopter");
    harley.takeItem("boomerang");

    // Make sure Harley has items
    assertEquals("Harley Quinn is carrying:\n"
            + "Helicopter\n"
            + "Boomerang\n", harley.getPlayerItemsDescription());

    // Make sure target is in her space.
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

    // Attack the target
    String result = harley.attack("helicopter");

    assertEquals("You attacked with Helicopter and did 5 points of damage to "
            + "Starro The Conqueror.  Helicopter is now evidence.  Starro The Conqueror "
            + "now has 70 health remaining.", result);

    // Item to evidence
    assertEquals("Harley Quinn is carrying:\n"
            + "Boomerang\n", harley.getPlayerItemsDescription());
  }

  @Test
  public void testAttackWithItemAndItemIsNotInInventoryNow() {
    World game = new WorldImpl(testData.getCortoMalteseData());
    game.addPlayer("Harley Quinn", game.getTheSpaceByName("Beach head One"),
            3);
    Player harley = game.getCurrentPlayer();
    harley.takeItem("helicopter");
    harley.takeItem("boomerang");

    // Make sure Harley has items
    assertEquals("Harley Quinn is carrying:\n"
            + "Helicopter\n"
            + "Boomerang\n", harley.getPlayerItemsDescription());

    // Make sure target is in her space.
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

    // Attack the target
    String result = harley.attack("helicopter");

    assertEquals("You attacked with Helicopter and did 5 points of damage to "
            + "Starro The Conqueror.  Helicopter is now evidence.  Starro The Conqueror "
            + "now has 70 health remaining.", result);

    // Item to evidence
    assertEquals("Harley Quinn is carrying:\n"
            + "Boomerang\n", harley.getPlayerItemsDescription());
  }

  @Test
  public void testAttackWithItemAndKillTarget() {
    World game = new WorldImpl(testData.getCortoMalteseDataWeakTarget());
    game.addPlayer("Harley Quinn", game.getTheSpaceByName("Beach head One"),
            3);
    Player harley = game.getCurrentPlayer();
    harley.takeItem("helicopter");
    harley.takeItem("boomerang");

    // Make sure Harley has items
    assertEquals("Harley Quinn is carrying:\n"
            + "Helicopter\n"
            + "Boomerang\n", harley.getPlayerItemsDescription());

    // Make sure target is in her space.
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

    // Attack the target
    String result = harley.attack("helicopter");

    assertEquals("You attacked with Helicopter and did 5 points of damage to Starro The "
            + "Conqueror.  Helicopter is now evidence.  Starro The Conqueror now has 0 health "
            + "remaining.\n"
            + "\n"
            + "Congratulations!  You killed Starro The Conqueror!\n", result);

    // Item to evidence
    assertEquals("Harley Quinn is carrying:\n"
            + "Boomerang\n", harley.getPlayerItemsDescription());
  }

  @Test(expected = IllegalStateException.class)
  public void testAttackWithItemWeDoNotHave() {
    World game = new WorldImpl(testData.getCortoMalteseDataWeakTarget());
    game.addPlayer("Harley Quinn", game.getTheSpaceByName("Beach head One"),
            3);
    Player harley = game.getCurrentPlayer();

    // Make sure target is in her space.
    assertEquals("Harley Quinn is in here in Beach Head One.\n"
            + "\n"
            + "Here is a list of items located here:\n"
            + "\tHelicopter\n"
            + "\tMongal\n"
            + "\tDetachable Arms\n"
            + "\tBoomerang\n"
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

    // Attack the target
    String result = harley.attack("helicopter");
  }

  //endregion
  @Test
  public void testToString() {
    Player newPlayer = getInstance.playerBuilder("The Joker", forest, 5);
    assertEquals("The Joker;3;5", newPlayer.toString());
  }
}

