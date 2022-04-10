package theworld.controller;

import static theworld.utilities.MilestoneUtilities.checkParam;

import java.awt.Point;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Scanner;
import theworld.controller.commands.AddPlayer;
import theworld.controller.commands.DrawWorld;
import theworld.controller.commands.IcontrollerCommand;
import theworld.controller.commands.MovePet;
import theworld.controller.commands.Moving;
import theworld.controller.commands.Pickup;
import theworld.controller.commands.PlayerAttack;
import theworld.controller.commands.PlayerInfo;
import theworld.controller.commands.PlayerInspect;
import theworld.controller.commands.WorldInspect;
import theworld.exceptions.GameMechanicException;
import theworld.exceptions.GameplayException;
import theworld.exceptions.InvalidCommandException;
import theworld.exceptions.NoBotException;
import theworld.exceptions.NoSuchItemException;
import theworld.exceptions.NotEnoughPlayersException;
import theworld.model.CpuPlayer;
import theworld.model.DependencyManager;
import theworld.model.Iplayer;
import theworld.model.Item;
import theworld.model.Player;
import theworld.model.Room;
import theworld.model.World;

/**
 * The controller of the world game.
 */
public class WorldGameController {

  private final Scanner in;
  private final Appendable out;

  private final World world;
  private final int maxTurns;
  private int turnCount;
  private int currentPlayerIndex;
  private boolean turnSpent;



  /**
   * The constructor to build this controller.

   * @param w the world model class to use
   * @param max maximum turn limit
   * @param i Readable input object
   * @param o Appendable output object
   */
  public WorldGameController(World w, int max, Readable i, Appendable o) {
    checkParam(w, i, o);
    this.maxTurns = max;
    this.turnCount = -1;
    this.world = w;
    this.in = new Scanner(i);
    this.out = o;
    this.currentPlayerIndex = 0;
    this.turnSpent = false;
  }

  private Iplayer getCurrentPlayer() {
    if (this.world.getAllPlayers().size() == 0) {
      return null;
    }
    return this.world.getAllPlayers().get(currentPlayerIndex);
  }


  /**
   * Runs the main game loop, executes game logic and do the relevant I/O.
   */
  public void gameLoop() {
    while (turnCount < maxTurns) {
      turnSpent = false;
      IcontrollerCommand cmd = null;
      boolean skipPlayer = false;
      try {
        logInfo(prompt());
        if (getCurrentPlayer() instanceof CpuPlayer && turnCount != -1) {
          advance();
          logInfo(getCurrentPlayer().roamAround());
          skipPlayer = true;
        }
        if (!skipPlayer) {
          String input = obtainRaw();
          switch (input) {
            case "poke":
              int x = in.nextInt();
              int y = in.nextInt();
              cmd = new WorldInspect(new Point(x, y), world);
              break;
            case "draw":
              cmd = new DrawWorld(this.world);
              break;
            case "addplayer":
              String pname = in.next();
              int str = in.nextInt();
              int roomNo = in.nextInt();
              Player newPlayer = new Player(pname, str);
              cmd = new AddPlayer(this.world, newPlayer, roomNo);
              break;
            case "addbot":
              String bname = in.next();
              int strength = in.nextInt();
              int roomno = in.nextInt();
              CpuPlayer newBot = new CpuPlayer(bname, strength);
              cmd = new AddPlayer(this.world, newBot, roomno);
              break;
            case "move":
              Room to = world.roomAt(in.nextInt());
              cmd = new Moving(getCurrentPlayer(), to, world);
              advance();
              break;
            case "pickup":
              Room located = DependencyManager.shared().queryRoom(getCurrentPlayer());
              int itemIndex = in.nextInt();
              cmd = new Pickup(getCurrentPlayer(), located.getItems().get(itemIndex));
              advance();
              break;
            case "lookaround":
              cmd = new PlayerInspect(getCurrentPlayer());
              advance();
              break;
            case "playerinfo":
              cmd = new PlayerInfo(getCurrentPlayer());
              break;
            case "attack":
              int itemi = in.nextInt();
              Item actual;
              if (itemi == -1) {
                actual = null;
              } else if (itemi >= Objects.requireNonNull(getCurrentPlayer()).getBackpack().size()) {
                throw new NoSuchItemException(itemi, getCurrentPlayer());
              } else {
                actual = Objects.requireNonNull(getCurrentPlayer()).getBackpack().get(itemi);
              }
              cmd = new PlayerAttack(getCurrentPlayer(), actual);
              advance();
              break;
            case "movepet":
              int nextr = in.nextInt();
              cmd = new MovePet(DependencyManager.shared().getWorld().getPet(), nextr);
              break;
            case "go":
              runGame();
              continue;
            case "gg":
              return;
            default:
              throw new InvalidCommandException(input);
          }
          if (cmd != null) {
            logInfo(cmd.execute());
            cmd = null;
          } else {
            throw new InvalidCommandException("Empty command found.");
          }
        }
      } catch (InvalidCommandException ex) {
        logError(ex.getMessage());
      } catch (GameplayException | IndexOutOfBoundsException ex) {
        logError(String.format("Gameplay error occured, please see following:\n%s\n",
                ex.getMessage()));
      }
      if (this.world.isGameEnded()) {
        logInfo("Target Character was killed. Game END!\n");
        logInfo(String.format("Winner player: %s\n",
                Objects.requireNonNull(getCurrentPlayer()).getName()));
        return;
      }
      if (turnSpent && turnCount != -1) {
        world.getCharacter().move();
        world.getPet().wander();
      }
    }

    logInfo("Maximum turns reached, game END!");
  }

  private void advance() {
    if (this.turnCount == -1) {
      throw new GameMechanicException("Game haven't started yet!");
    }
    int total = this.world.getAllPlayers().size();
    if (this.currentPlayerIndex == total - 1) {
      this.currentPlayerIndex = 0;
      this.turnCount += 1;
    } else {
      this.currentPlayerIndex += 1;
    }
    turnSpent = true;
  }

  private String prompt() {
    if (this.turnCount == -1) {
      return "Preparation stage... type go to start the game.";
    } else {
      return String.format("The turn of %s\n%s",
              Objects.requireNonNull(this.getCurrentPlayer()).getInformation(false),
              this.world.getTargetCharacter().locatedInfo());
    }
  }

  private void runGame() {
    if (world.getAllPlayers().size() == 0) {
      throw new NotEnoughPlayersException();
    }
    if (world.getCharacter() == null) {
      throw new NoBotException();
    }
    if (this.turnCount != -1) {
      throw new GameMechanicException("Game already started.");
    }
    this.turnCount = 0;
  }

  private void logError(String msg)  {
    try {
      out.append(String.format("Gameplay Error Occured:\n%s\n", msg));
    } catch (IOException ex) {
      throw new IllegalStateException();
    }
  }

  private void logInfo(String msg) {
    try {
      out.append(String.format("%s\n", msg));
    } catch (IOException ex) {
      throw new IllegalStateException();
    }
  }

  private String obtainRaw() throws NoSuchElementException {
    return in.next();
  }
}
