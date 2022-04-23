import static org.junit.Assert.assertEquals;

import controller.GraphicalController;
import gamemodels.gamemanagermodels.MockWorld;
import java.io.BufferedReader;
import java.io.StringReader;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import view.implementations.MockMainForm;

/**
 * The test suite for the milestone 4 graphical controller.
 */
public class GraphicalControllerTest {

  GraphicalController controller;
  String readFrom;
  MockWorld model;
  MockMainForm view;

  @Before
  public void setUp() {
    view = new MockMainForm();
    model = new MockWorld();
    controller = new GraphicalController(view, model);
    controller.setModel(model);
    controller.setView(view);
  }

  @Test
  public void addComputerPlayer() {
    controller.addComputerPlayer("test", "mocked", 3);
    String modelexpected = "addComputerPlayer called, playerName = test,"
        + " playerLocation = null, itemLimit = 3";
    assertEquals(modelexpected, model.recentMockLog(1)[0]);
    String viewexpected = "logGameplay called. msg = Added computer player:"
        + " test  Location: mocked  Max Items: 3\n";
    assertEquals(viewexpected, view.recentMockLog(1)[0]);
  }

  @Test
  public void addPlayer() {
  }

  @Test
  public void attack() {
  }

  @Test
  public void describePlayer() {
  }

  @Test
  public void lookaround() {
  }

  @Test
  public void getTurnInformation() {
  }

  @Test
  public void move() {
  }

  @Test
  public void movePet() {
  }

  @Test
  public void pickup() {
  }

  @Test
  public void hitDetection() {
  }

  @Test
  public void setView() {
  }

  @Test
  public void setModel() {
  }

  @Test
  public void restartGame() {
  }

  @Test
  public void resetView() {
  }

  @Test
  public void gameStarted() {
  }

  @Test
  public void startGame() {
  }

  @Test
  public void getCurrentPlayer() {
  }

  @Test
  public void spawningRooms() {
  }
}