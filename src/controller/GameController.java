package controller;

import gamecommands.AddComputerPlayer;
import gamecommands.AddPlayer;
import gamecommands.Attack;
import gamecommands.DescribePlayer;
import gamecommands.DescribeSpace;
import gamecommands.LookAround;
import gamecommands.Move;
import gamecommands.MovePet;
import gamecommands.PickUpItem;
import gamecommands.SaveWorldImage;
import gameinterfaces.worldbuilderinterfaces.World;
import gameinterfaces.worldcontrollerinterfaces.Controller;
import gameinterfaces.worldcontrollerinterfaces.GameCommand;
import gamemodels.playermodels.PlayerImpl;

import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

/**
 * This controller will initiate the game and hand it off to the model.
 */
public class GameController implements Controller {
  private final Readable in;
  private final Appendable out;

  /**
   * Constructor for the ControllerImpl class.
   *
   * @param in The input stream.
   * @param out The output stream.
   */
  public GameController(Readable in, Appendable out) {
    if (in == null) {
      throw new IllegalArgumentException("Readable cannot be null.");
    }

    if (out == null) {
      throw new IllegalArgumentException("Appendable cannot be null.");
    }

    this.in = in;
    this.out = out;
  }

  @Override
  public void start(World game) {
    Objects.requireNonNull(game);
    int maxTurns = 0;

    // We need to initialize the game by adding players
    try (Scanner scan = new Scanner(this.in)) {
      // Initializing the game here.  This loop is for adding players to the game.  The message
      // is directed at that.

      while (game.getMaxNumberOfTurns() < 1) {
        System.out.println("How many turns will this game have?");

        if (isInt(scan)) {
          maxTurns = scan.nextInt();
        } else {
          System.out.println("Wrong input! Input only integer numbers please...");
          scan.nextLine();
        }

        game.setMaxNumberOfTurns(maxTurns);
      }

      System.out.println("We need to add players to the game.  Each player has a name, a starting"
              + " point, and a maximum number of items they can carry.");

      boolean flagDoneInitializing = false;
      while (!flagDoneInitializing) {
        GameCommand gameInitializeCmd = null;
        String playerName;
        String playerLocation;
        int itemLimit = -1;

        System.out.println("Type 'human' to add a human player.  Type 'computer' to add a "
                + "computer controlled player.  Type 'done' when finished.");

        String input = scan.next();
        switch (input.toLowerCase()) {
          case "human":
            System.out.println("Please enter the name of the player: ");
            playerName = scan.next() + scan.nextLine();

            // Location of player
            System.out.println("Please enter the starting location of the player: ");
            System.out.println("Available locations are: " + game.getAvailableLocations());
            playerLocation = scan.next();
            if (scan.hasNextLine()) {
              playerLocation += scan.nextLine();
            }

            // Item limit of player
            while (itemLimit < 0) {
              System.out.println("Please enter the maximum number items the player can carry: ");

              if (isInt(scan)) {
                itemLimit = scan.nextInt();
              } else {
                System.out.println("Wrong input! Input only integer numbers please...");
                scan.nextLine();
              }
            }

            // Adding the player
            gameInitializeCmd = new AddPlayer(playerName, playerLocation, itemLimit);
            System.out.println("Adding a new player...\n");
            break;
          case "computer":
            System.out.println("Please enter the name of the computer player: ");
            playerName = scan.next() + scan.nextLine();

            // Location of computer
            System.out.println("Please enter the starting location of the computer player: \n");
            System.out.println("Available locations are: " + game.getAvailableLocations());
            playerLocation = scan.next();
            if (scan.hasNextLine()) {
              playerLocation += scan.nextLine();
            }

            // Item limit of player
            while (itemLimit < 0) {
              System.out.println("Please enter the maximum number items the computer player can "
                      + "carry: ");

              if (isInt(scan)) {
                itemLimit = scan.nextInt();
              } else {
                System.out.println("Wrong input! Input only integer numbers please...");
                scan.nextLine();
              }
            }

            // Adding the computer
            gameInitializeCmd = new AddComputerPlayer(playerName, playerLocation, itemLimit);
            System.out.println("Adding a new computer player...\n");
            break;
          case "done":
            flagDoneInitializing = true;
            break;
          default:
            break;
        }

        // Execute the command
        if (gameInitializeCmd != null) {
          try {
            String result = gameInitializeCmd.execute(game);
            this.out.append(result);
          } catch (IOException e) {
            appendableErrorMessage();
          }  catch (IllegalStateException ex) {
            System.err.println(ex.getMessage());
          } catch (IllegalArgumentException ex) {
            System.err.println("ERROR: " + ex.getMessage());
          }
        }
      }

      // Done initializing.  Now we play.
      while (!game.isGameOver()) {

        System.out.println(game.getCurrentPlayerTurnInfo());

        if (!(game.getCurrentPlayer() instanceof PlayerImpl)) {
          try {
            String result = game.getCurrentPlayer().takeRandomAction(game);
            this.out.append(result).append("\n");
          } catch (IllegalStateException | IllegalArgumentException ex) {
            try {
              this.out.append(ex.getMessage());
            } catch (IOException e) {
              appendableErrorMessage();
            }
          } catch (IOException ex) {
            appendableErrorMessage();
          }

          continue;
        }

        System.out.println("You can: look around, describe player, pick up item, move pet, "
                + "move, attack, or quit?\n");


        String input = scan.next();
        if (scan.hasNextLine()) {
          input += scan.nextLine();
        }

        GameCommand gameCmd = null;
        String newLocation;
        String item;
        switch (input.toLowerCase()) {
          case "move":
            System.out.println("Please enter the neighboring location to move to: ");
            newLocation = scan.next() + scan.nextLine();
            gameCmd = new Move(newLocation);
            System.out.println("Attempting to move to " + newLocation);
            break;
          case "look around":
            gameCmd = new LookAround();
            System.out.println("Attempting to look around.");
            break;
          case "describe player":
            gameCmd = new DescribePlayer();
            System.out.println("Examining current player.");
            break;
          case "pick up item":
            System.out.println("Please enter the name of the item you want to pick up: ");
            item = scan.next() + scan.nextLine();
            gameCmd = new PickUpItem(item);
            System.out.println("Attempting to pick up the item.");
            break;
          case "move pet":
            System.out.println("Please enter the name of the space to move the pet to: ");
            String nameOfPetSpace = scan.next() + scan.nextLine();
            gameCmd = new MovePet(nameOfPetSpace);
            System.out.println("Attempting to move pet to " + nameOfPetSpace);
            break;
          case "attack":
            StringBuilder attackText = new StringBuilder();
            attackText.append("Please enter the name of the item to use (")
                            .append(game.getCurrentPlayer().getPlayerItemsDescription())
                                    .append("Poke in the Eye):");
            System.out.println(attackText);
            String nameOfItem = scan.next() + scan.nextLine();
            gameCmd = new Attack(nameOfItem);
            System.out.println("Attempting to attack with " + nameOfItem);
            break;
          case "describe space":
            System.out.println("Please enter the name of the space to describe: ");
            String nameOfSpace = scan.next() + scan.nextLine();
            gameCmd = new DescribeSpace(nameOfSpace);
            System.out.println("Attempting to describe " + nameOfSpace);
            break;
          case "save world image":
            gameCmd = new SaveWorldImage();
            System.out.println("Attempting to save an image of the game board to disk.");
            break;
          case "quit":
          case"q":
            return;
          default:
            try {
              this.out.append("I don't understand the command.\n");
            } catch (IOException e) {
              appendableErrorMessage();
            }
            break;
        }

        // Execute the commands.
        if (gameCmd != null) {
          try {
            String result = gameCmd.execute(game);
            this.out.append(result).append("\n");
          } catch (IllegalStateException | IllegalArgumentException ex) {
            try {
              this.out.append(ex.getMessage());
            } catch (IOException e) {
              appendableErrorMessage();
            }
          } catch (IOException e) {
            appendableErrorMessage();
          }
        }
      }

      try {
        this.out.append("The game is over!\n");
      } catch (IOException e) {
        appendableErrorMessage();
      }
    }
  }

  private boolean isInt(Scanner scan) {
    if (scan.hasNextInt()) {
      return true;
    } else {
      return false;
    }
  }

  private void appendableErrorMessage() {
    System.err.println("Appendable logging error.\n");
  }
}
