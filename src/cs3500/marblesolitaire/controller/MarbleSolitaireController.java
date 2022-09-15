package cs3500.marblesolitaire.controller;

/**
 * This interface represents the operations for a controller for the Marble Solitaire game. The
 * controller works with a model and a view of a Marble Solitaire game to play the game.
 */
public interface MarbleSolitaireController {
  /**
   * Takes in user input to play a game of Marble Solitaire. Uses a model to process the
   * functionality of the game according to user input, and uses a view to transmit the produced
   * output of the game as the user makes inputs.
   *
   * @throws IllegalStateException if the controller is unable to successfully read input or
   *                               transmit output, or if the scanner runs out of inputs before
   *                               the game is over or quit
   */
  void playGame() throws IllegalStateException;
}