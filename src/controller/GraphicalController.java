package controller;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Objects;
import java.util.List;
import java.util.Optional;

import gamecommands.AddComputerPlayer;
import gamecommands.AddPlayer;
import gamecommands.LookAround;
import gamecommands.TurnInformation;
import gameinterfaces.iteminterfaces.Item;
import gameinterfaces.iteminterfaces.ItemViewModel;
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

  public GraphicalController(World w) {
    this.model = w;
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
  }

  @Override
  public void addPlayer(String playerName, String playerLocation, int itemLimit) {
    Utility.checkNull(playerName, playerLocation);
    command = new AddPlayer(playerName, playerLocation, itemLimit);
    view.logGameplay(command.execute(model));
  }

  @Override
  public void attack() {

  }

  @Override
  public void describePlayer() {

  }

  @Override
  public void describeSpace(String spaceName) {

  }

  @Override
  public String lookaround() {
    command = new LookAround();

    return command.execute(model);
  }

  @Override
  public String getTurnInformation() {
    command = new TurnInformation();

    return command.execute(model);
  }

  @Override
  public void move(String nameOfSpace) {

  }

  @Override
  public void movePet(String nameOfSpace) {

  }

  @Override
  public void pickup(String itemName) {

  }

  @Override
  public void hitDetection(double x, double y) {
    Optional<SpaceViewModel> result = Optional.ofNullable(model.hitDetection(x, y));
    //TODO: This is testing only, replace in actual submission.
    String name = result.map(SpaceViewModel::getTheNameOfThisSpace).orElse("EMPTY");
    System.out.println(name);
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
  public void advanceTurn() {

  }
}
