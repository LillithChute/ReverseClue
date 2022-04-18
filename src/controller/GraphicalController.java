package controller;

import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Optional;

import gamecommands.AddPlayer;
import gamecommands.LookAround;
import gamecommands.TurnInformation;
import gameinterfaces.iteminterfaces.Item;
import gameinterfaces.iteminterfaces.ItemViewModel;
import gameinterfaces.spaceinterfaces.Space;
import gameinterfaces.spaceinterfaces.SpaceViewModel;
import gameinterfaces.worldbuilderinterfaces.World;
import gameinterfaces.worldcontrollerinterfaces.GameCommand;
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

  }

  @Override
  public void addPlayer(String playerName, String playerLocation, int itemLimit) {
    command = new AddPlayer(playerName, playerLocation, itemLimit);
    command.execute(model);
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
  }
}
