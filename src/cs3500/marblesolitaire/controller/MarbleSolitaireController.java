package cs3500.marblesolitaire.controller;

/**
 * This interface represents a controller for a game of Marble Solitaire.
 */
public interface MarbleSolitaireController {
  /**
   * To play a game of Marble Solitaire.
   *
   * @throws IllegalStateException if the controller is unable to successfully
   *                               read input or transmit output
   */
  void playGame() throws IllegalStateException;
}
