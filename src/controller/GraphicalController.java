package controller;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Objects;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import gamecommands.AddComputerPlayer;
import gamecommands.AddPlayer;
import gamecommands.Attack;
import gamecommands.LookAround;
import gamecommands.Move;
import gamecommands.PickUpItem;
import gamecommands.TurnInformation;
import gameinterfaces.iteminterfaces.Item;
import gameinterfaces.iteminterfaces.ItemViewModel;
import gameinterfaces.playerinterfaces.Player;
import gameinterfaces.playerinterfaces.PlayerViewModel;
import gameinterfaces.spaceinterfaces.Space;
import gameinterfaces.spaceinterfaces.SpaceViewModel;
import gameinterfaces.worldbuilderinterfaces.World;
import gameinterfaces.worldcontrollerinterfaces.GameCommand;
import gamemodels.gamemanagermodels.WorldImpl;
import utilitles.Utility;
import view.implementations.MainForm;
import view.interfaces.ImainForm;

public class GraphicalController implements UiController, ControllerFeatures {

  private World model;
  private ImainForm view;

  private GameCommand command;
  private boolean started;

  public GraphicalController(World w) {
    this.model = w;
    this.started = false;
  }


  @Override
  public BufferedImage obtainImage() {
    return model.worldImage();
  }

  @Override
  public void addComputerPlayer(String playerName, String playerLocation, int itemLimit) {
    Utility.checkNull(playerName, playerLocation);
    command = new AddComputerPlayer(playerName, playerLocation, itemLimit);
    view.logGameplay(command.execute(model));
    updateViewInfo();
  }

  @Override
  public void addPlayer(String playerName, String playerLocation, int itemLimit) {
    Utility.checkNull(playerName, playerLocation);
    command = new AddPlayer(playerName, playerLocation, itemLimit);
    view.logGameplay(command.execute(model));
    updateViewInfo();
  }

  @Override
  public void attack(String itemName) {
    final String poke = "Poke in the Eye";
    if (itemName == null) {
      itemName = poke;
    }
    command = new Attack(itemName);
    view.logGameplay(command.execute(model));
    updateViewInfo();
  }

  @Override
  public void describePlayer() {

  }

  @Override
  public void describeSpace(String spaceName) {

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

  }

  @Override
  public void pickup(String itemName) {
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
  public List<Item> getItemsOnTheGround() {
    SpaceViewModel currentPlayersLocation = model.getSpaces().get(model.getCurrentPlayer().getLocation());

    return currentPlayersLocation.getItems();
  }

  @Override
  public void setView(MainForm v) {
    this.view = v;
    v.setFeatures(this);
    updateViewComponentStates();
    v.welcome();

  }

  @Override
  public void setModel(File file) {
    Objects.requireNonNull(file);
    try {
      this.model = new WorldImpl(new BufferedReader(new FileReader(file)));
      this.resetModel();
    } catch (FileNotFoundException ex) {
      throw new IllegalArgumentException();
    }
  }

  @Override
  public void resetModel() {
    this.view.setPreGameMenuVisibility(false);
    this.view.setFeatures(this);
  }

  @Override
  public void updateViewInfo() {
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
    this.view.setPlayerName(model.getCurrentPlayer());
    this.view.setGroundItems(ground);
    this.view.setBackpackItems(backpack);
  }

  @Override
  public void advanceTurn() {
    this.updateViewInfo();
    if (model.isGameOver()) {
      this.updateViewInfo();
      this.started = false;
      this.updateViewComponentStates();
      this.view.showEndingPrompt(model.getCurrentPlayer().getPlayerName());
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
  public void startGame(int turnCount) {
    this.model.setMaxNumberOfTurns(turnCount);
    this.started = true;
    this.updateViewComponentStates();
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
