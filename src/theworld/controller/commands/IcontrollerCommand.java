package theworld.controller.commands;

import theworld.exceptions.GameplayException;

/**
 * The interface for all game commands following the command pattern.
 */
public interface IcontrollerCommand {
  /**
   * Executes the command, making changes to the model.
   *
   * @return the result of the execution
   */
  public String execute();
}
