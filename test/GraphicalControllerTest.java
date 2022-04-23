import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import controller.GraphicalController;
import gamemodels.Mock;
import gamemodels.gamemanagermodels.MockWorld;
import gamemodels.itemmodels.MockItem;
import gamemodels.petmodels.MockPet;
import gamemodels.playermodels.MockPlayer;
import gamemodels.spacemodels.MockSpace;
import gamemodels.targetmodels.MockTarget;
import java.io.BufferedReader;
import java.io.StringReader;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import view.implementations.MockMainForm;
import view.interfaces.ImainForm;

/**
 * The test suite for the milestone 4 graphical controller.
 */
public class GraphicalControllerTest {

  GraphicalController controller;
  MockWorld model;
  MockPlayer player;
  MockSpace space;
  MockItem item;
  MockPet pet;
  MockTarget target;
  MockMainForm view;

  /**
   * Set up some initial test values and objects.
   */
  @Before
  public void setUp() {
    view = new MockMainForm();
    item = new MockItem();
    pet = new MockPet();
    target = new MockTarget();
    player = new MockPlayer(item);
    space = new MockSpace(item, player, target, pet);
    model = new MockWorld(item, pet, player, space, target);
    controller = new GraphicalController(view, model);
    controller.setModel(model);
    controller.setView(view);
  }

  @Test
  public void addComputerPlayer() {
    controller.addComputerPlayer("Computer Player", "Jotunheim", 3);
    String viewexpected = "logGameplay called. msg = Added computer player: Computer Player  "
            + "Location: Jotunheim  Max Items: 3\n";

    assertTrue(model.recentMockLog(1)[0].contains("addComputerPlayer called, playerName = "
            + "Computer Player, "));
    assertEquals("getTheSpaceByName called, nameOfSpace = Jotunheim",
            model.recentMockLog(2)[0]);
    assertEquals(viewexpected, view.recentMockLog(1)[0]);
  }

  @Test
  public void addPlayer() {
    String viewexpected = "logGameplay called. msg = Added player: Human Player  Location: "
            + "Forest  Max Items: 3\n";

    controller.addPlayer("Human Player", "Forest", 3);

    assertTrue(model.recentMockLog(1)[0].contains("addPlayer called, playerName = Human Player, "
            + "playerLocation = "));
    assertEquals(viewexpected, view.recentMockLog(1)[0]);
  }

  @Test
  public void attack() {
    // This is a poke in the eye attack
    controller.attack(null);

    assertEquals("attack called, itemName=Poke in the Eye", player.recentMockLog(2)[0]);
    assertEquals("logGameplay called. msg = null", view.recentMockLog(1)[0]);
  }

  @Test
  public void describePlayer() {
    controller.describePlayer();

    assertEquals("describePlayer called.", player.recentMockLog(2)[0]);
    assertEquals("logGameplay called. msg = null", view.recentMockLog(1)[0]);
  }

  @Test
  public void lookaround() {
    controller.lookaround();

    assertEquals("lookAround called.", player.recentMockLog(2)[0]);
    assertEquals("logGameplay called. msg = null", view.recentMockLog(1)[0]);
  }

  @Test
  public void getTurnInformation() {
    controller.getTurnInformation();

    assertEquals("getCurrentPlayerTurnInfo called.", model.recentMockLog(1)[0]);
    assertEquals("welcome called.", view.recentMockLog(1)[0]);
  }

  @Test
  public void move() {
    controller.move("Forest");

    assertEquals("move called, nameOfSpace=Forest", player.recentMockLog(3)[0]);
    assertEquals("logGameplay called. msg = **null**\n"
            + "Moved to location: Forest", view.recentMockLog(1)[0]);
  }

  @Test
  public void movePet() {
    controller.movePet("Road");

    assertTrue(player.recentMockLog(3)[0].contains("movePet called, spaceToMoveTo"));
    assertEquals("logGameplay called. msg = **null**\n"
            + "Moved pet to location: Road", view.recentMockLog(1)[0]);
  }

  @Test
  public void pickup() {
    controller.pickup("Polka-Dots");

    assertEquals("takeItem called.",
            player.recentMockLog(2)[0]);
    assertEquals("logGameplay called. msg = **null**\n"
            + "null picked up item: Polka-Dots", view.recentMockLog(1)[0]);
  }

  @Test
  public void hitDetection() {
    String modelexpected = "hitDetection called, x=23.000000, y=46.000000";
    String viewexpected = "welcome called.";

    controller.hitDetection(23, 46);

    assertEquals(modelexpected, model.recentMockLog(1)[0]);
    assertEquals(viewexpected, view.recentMockLog(1)[0]);
  }

  @Test
  public void setView() {
    controller.setView(view);

    // Chain of events when setView is invoked
    assertEquals("welcome called.", view.recentMockLog(1)[0]);
    assertEquals("setStartedState called. started = false", view.recentMockLog(2)[0]);
  }

  @Test
  public void setModel() {
    controller.setModel(model);

    // Chain of events when setModel is invoked with the mock
    assertEquals("welcome called.", view.recentMockLog(1)[0]);
    assertEquals("setStartedState called. started = false", view.recentMockLog(2)[0]);
  }

  @Test
  public void restartGame() {
    controller.restartGame();

    // Chain of events when restartGame is invoked with the mock
    assertEquals("promptError called. msg = No World is "
            + "loaded!", view.recentMockLog(1)[0]);
    assertEquals("welcome called.", view.recentMockLog(2)[0]);
    assertEquals("setStartedState called. started = false", view.recentMockLog(3)[0]);
  }

  @Test
  public void resetView() {
    controller.resetView();

    // Chain of events when resetView is invoked with the mock
    assertEquals("setStartedState called. started = false", view.recentMockLog(1)[0]);
    assertEquals("setPreGameMenuVisibility called. enabled = true",
            view.recentMockLog(3)[0]);
    assertEquals("welcome called.", view.recentMockLog(4)[0]);
  }

  @Test
  public void gameStarted() {
    controller.gameStarted();

    // Chain of events when resetView is invoked with the mock
    assertEquals("welcome called.", view.recentMockLog(1)[0]);
    assertEquals("setStartedState called. started = false", view.recentMockLog(2)[0]);
  }

  @Test
  public void startGame() {
    controller.startGame(100);

    // Chain of events when startGame is invoked with the mock
    assertEquals("promptError called. msg = Not Enough players to start the game!",
            view.recentMockLog(1)[0]);
    assertEquals("welcome called.",
            view.recentMockLog(2)[0]);
    assertEquals("setStartedState called. started = false",
            view.recentMockLog(3)[0]);
  }

  @Test
  public void getCurrentPlayer() {
    controller.getCurrentPlayer();

    // Chain of events when getCurrentPlayer is invoked with the mock
    assertEquals("getCurrentPlayer called.", model.recentMockLog(1)[0]);
    assertEquals("welcome called.", view.recentMockLog(1)[0]);
    assertEquals("setStartedState called. started = false", view.recentMockLog(2)[0]);
  }

  @Test
  public void spawningRooms() {
    controller.spawningRooms();

    // Chain of events when spawningRooms is invoked with the mock
    assertEquals("getSpaces called.", model.recentMockLog(1)[0]);
    assertEquals("welcome called.", view.recentMockLog(1)[0]);
    assertEquals("setStartedState called. started = false", view.recentMockLog(2)[0]);
  }
}