package controller;

import gamecommands.AddComputerPlayer;
import gamecommands.AddPlayer;
import gamecommands.Attack;
import gamecommands.DescribePlayer;
import gamecommands.LookAround;
import gamecommands.Move;
import gamecommands.MovePet;
import gamecommands.PickUpItem;
import gamecommands.TurnInformation;
import gameinterfaces.iteminterfaces.ItemViewModel;
import gameinterfaces.playerinterfaces.Player;
import gameinterfaces.playerinterfaces.PlayerViewModel;
import gameinterfaces.spaceinterfaces.Space;
import gameinterfaces.spaceinterfaces.SpaceViewModel;
import gameinterfaces.worldbuilderinterfaces.World;
import gameinterfaces.worldcontrollerinterfaces.GameCommand;
import gamemodels.gamemanagermodels.WorldImpl;
import java.awt.Rectangle;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import utilitles.Utility;
import view.implementations.MainForm;
import view.interfaces.ImainForm;

/**
 * The milestone-4 controller for the game. It performs commands on the model and pushes
 * changes back to the view.
 */
public class GraphicalController implements UiController, ControllerFeatures {

  private World model;
  private ImainForm view;

  private GameCommand command;
  private boolean started;

  private String loaded;

  /**
   * Constructs a controller for the game.
   *
   * @param f The world-representation file to be parsed into the model.
   */
  public GraphicalController(Readable f, String filename) {
    Utility.checkNull(f);
    this.model = new WorldImpl(f);
    this.loaded = filename;
    this.started = false;
  }

  /**
   * Constructs the controller from the given view and the model.
   *
   * @param v the view to be associated with this controller
   * @param m the model to be associated with this controller
   */
  public GraphicalController(ImainForm v, World m) {
    this.view = v;
    this.model = m;
  }


  @Override
  public void addComputerPlayer(String playerName, String playerLocation, int itemLimit) {
    Utility.checkNull(playerName, playerLocation);
    if (!checkPlayerLimit()) {
      return;
    }
    command = new AddComputerPlayer(playerName, playerLocation, itemLimit);
    view.logGameplay(command.execute(model));
    updateViewInfo();
  }

  @Override
  public void addPlayer(String playerName, String playerLocation, int itemLimit) {
    Utility.checkNull(playerName, playerLocation);
    if (!checkPlayerLimit()) {
      return;
    }
    command = new AddPlayer(playerName, playerLocation, itemLimit);
    view.logGameplay(command.execute(model));
    updateViewInfo();
  }

  private boolean checkPlayerLimit() {
    if (this.model.getPlayerCount() > 10) {
      view.promptError("Too many players!");
      return false;
    }
    return true;
  }

  @Override
  public void attack(String itemName) {
    final String poke = "Poke in the Eye";
    if (itemName == null) {
      itemName = poke;
    }
    command = new Attack(itemName);
    view.logGameplay(command.execute(model));
    this.advanceTurn();
    updateViewInfo();
  }

  @Override
  public void describePlayer() {
    command = new DescribePlayer();
    view.logGameplay(command.execute(model));
    this.advanceTurn();
    updateViewInfo();
  }


  @Override
  public void lookaround() {
    command = new LookAround();
    String res = command.execute(model);
    this.view.logGameplay(res);
    this.advanceTurn();
  }

  @Override
  public String getTurnInformation() {
    command = new TurnInformation();

    return command.execute(model);
  }

  @Override
  public void move(String nameOfSpace) {
    Utility.checkNull(nameOfSpace);
    command = new Move(nameOfSpace);
    try {
      this.view.logGameplay(command.execute(model));
      this.advanceTurn();
    } catch (IllegalStateException ex) {
      this.view.promptError(ex.getMessage());
    }
    this.updateViewInfo();
  }

  @Override
  public void movePet(String nameOfSpace) {
    Utility.checkNull(nameOfSpace);
    command = new MovePet(nameOfSpace);
    try {
      this.view.logGameplay(command.execute(model));
      this.advanceTurn();
    } catch (IllegalStateException ex) {
      this.view.promptError(ex.getMessage());
    }
  }

  @Override
  public void pickup(String itemName) {
    Utility.checkNull(itemName);
    command = new PickUpItem(itemName);
    this.view.logGameplay(command.execute(model));
    this.advanceTurn();
  }

  @Override
  public SpaceViewModel hitDetection(double x, double y) {
    Optional<SpaceViewModel> result = Optional.ofNullable(model.hitDetection(x, y));
    return result.orElse(null);
  }

  @Override
  public void setView(ImainForm v) {
    Utility.checkNull(v);
    this.view = v;
    v.setFeatures(this);
    updateViewComponentStates();
    v.welcome();

  }

  @Override
  public void setModel(String file) {
    Utility.checkNull(file);
    this.loaded = file;
    try {
      this.model = new WorldImpl(new BufferedReader(new FileReader(file)));
      this.started = false;
      this.resetView();
    } catch (FileNotFoundException ex) {
      throw new IllegalArgumentException();
    }
  }

  @Override
  public void setModel(World w) {
    Utility.checkNull(w);
    this.model = w;
  }

  @Override
  public void restartGame() {
    if (loaded == null) {
      this.view.promptError("No World is loaded!");
      return;
    }
    setModel(loaded);
  }

  @Override
  public void resetView() {
    this.view.setPreGameMenuVisibility(true);
    this.view.setFeatures(this);
    this.view.setStartedState(false);
  }

  private void updateViewInfo() {
    if (!this.started) {
      return;
    }
    this.view.setGraphics(model.worldImage());
    this.view.setTurnInfo(model.getCurrentPlayerTurnInfo());
    Player current = model.getCurrentPlayer();
    Space currentSpace = model.getSpaces()
        .stream()
        .filter(r -> r.getPlayersInThisSpace().contains(current))
        .findFirst().orElse(null);
    List<ItemViewModel> backpack = new ArrayList<>(current.getPlayerItems());
    List<ItemViewModel> ground = currentSpace.getItems().stream()
        .filter(i -> !i.isItemWithPlayer()).collect(Collectors.toList());
    this.view.setGraphics(model.worldImage());
    this.view.setBackpackPlayer(model.getCurrentPlayer());
    this.view.setGroundItems(ground);
    this.view.setBackpackItems(backpack);
  }

  private void advanceTurn() {
    this.updateViewInfo();
    if (model.isGameOver()) {
      this.updateViewInfo();
      this.started = false;
      this.updateViewComponentStates();
      if (model.getTurnTotal() >= model.getMaxNumberOfTurns()) {
        this.view.showEndingPrompt("Target Character Escaped and NO ONE wins!");
      } else {
        this.view.showEndingPrompt(String.format("%s WINS!",
            model.getCurrentPlayer().getPlayerName()));
      }
      return;
    }
    this.model.moveTarget();
    this.model.nextTurn();
    if (!model.getCurrentPlayer().completedTurn()) {
      this.view.logGameplay(this.model.getCurrentPlayer().takeRandomAction(model));
      this.advanceTurn();
    }
    this.updateViewInfo();
  }

  @Override
  public boolean gameStarted() {
    return this.started;
  }

  @Override
  public void startGame(int turnCount) {
    if (this.model.getPlayerCount() < 1) {
      view.promptError("Not Enough players to start the game!");
      return;
    }
    this.model.setMaxNumberOfTurns(turnCount);
    this.started = true;
    this.updateViewComponentStates();
    this.updateViewInfo();
  }

  private void updateViewComponentStates() {
    this.view.setStartedState(this.started);
  }

  @Override
  public PlayerViewModel getCurrentPlayer() {
    return model.getCurrentPlayer();
  }

  @Override
  public List<SpaceViewModel> spawningRooms() {
    return new ArrayList<>(model.getSpaces());
  }

}
