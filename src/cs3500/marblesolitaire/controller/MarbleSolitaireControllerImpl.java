package cs3500.marblesolitaire.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireView;

/**
 * This class represents an implementation of the MarbleSolitaireController interface to allow a
 * user to play a game of Marble Solitaire.
 */
public class MarbleSolitaireControllerImpl implements MarbleSolitaireController {
  private final MarbleSolitaireModel model;
  private final MarbleSolitaireView view;
  private final Readable input;

  /**
   * Creates a controller object to play a game.
   *
   * @param model the model representing the functionality of the game
   * @param view  the view that displays the state of the game
   * @param input the Readable object from which to read input
   * @throws IllegalArgumentException if any of the parameters are null
   */
  public MarbleSolitaireControllerImpl(MarbleSolitaireModel model, MarbleSolitaireView view,
                                       Readable input) throws IllegalArgumentException {
    if (model == null) {
      throw new IllegalArgumentException("Model cannot be null");
    }
    if (view == null) {
      throw new IllegalArgumentException("View cannot be null");
    }
    if (input == null) {
      throw new IllegalArgumentException("Input cannot be null");
    }
    this.model = model;
    this.view = view;
    this.input = input;
  }

  @Override
  public void playGame() throws IllegalStateException {
    Scanner scan = new Scanner(input); // use Scanner to read input from Readable
    // list of ints to store potential move inputs given by the user
    ArrayList<Integer> moveInputs = new ArrayList<>();
    this.renderBoardAndScore();
    while (!this.model.isGameOver()) { // while the game is not over
      String next;
      try {
        next = scan.next(); // try to get the next element from the scanner
      } catch (NoSuchElementException e) { // if there are no more elements in the scanner
        throw new IllegalStateException("No more inputs remaining");
      }
      if (next.equalsIgnoreCase("q")) { // if 'q' is the next input
        this.quitGameMessage();
        return; // end the method
      } else if (this.isPositiveInt(next)) { // if the next input is a positive int
        moveInputs.add(Integer.parseInt(next) - 1); // add it to the list of potential move inputs
      } else { // the next input is not either a 'q', 'Q', or a positive int
        this.signalInvalidInput(); // ask the user to enter another value
      }
      if (moveInputs.size() >= 4) { // once we have at least 4 potential move inputs,
        // we can attempt a move with the user inputs
        this.attemptMove(moveInputs.remove(0), moveInputs.remove(0),
                moveInputs.remove(0), moveInputs.remove(0));
      }
    }
    // the user has reached a 'game over' state with the model (by playing the game)
    this.gameOverMessage();
  }

  // calls the view to render the board and display the user's current score
  private void renderBoardAndScore() throws IllegalStateException {
    try {
      this.view.renderBoard();
      this.view.renderMessage("\nScore: " + this.model.getScore() + "\n");
    } catch (IOException e) {
      throw new IllegalStateException("Failed to render board or score");
    }
  }

  // to determine whether the String input from the scanner
  // is a positive integer(true) or not(false)
  private boolean isPositiveInt(String s) {
    int moveInput;
    try {
      // initialize moveInput to the integer value of s,
      // IF it can be parsed as an integer
      moveInput = Integer.parseInt(s);
    } catch (Exception e) { // if it can't be parsed as an int
      return false;
    }
    return moveInput > 0;
  }

  // to signal to the user that their last input was invalid and
  // prompts them to re-enter a value
  private void signalInvalidInput() throws IllegalStateException {
    try {
      this.view.renderMessage("Please re-enter a valid input:\n");
    } catch (IOException e) {
      throw new IllegalStateException("Failed to render board or score");
    }
  }

  // to have the model attempt a move with the given positions
  private void attemptMove(int fromRow, int fromCol, int toRow, int toCol)
          throws IllegalStateException {
    try {
      // calls the model to attempt a move with the given 'from' and 'to' position coordinates
      this.model.move(fromRow, fromCol, toRow, toCol);
      // renders the updated board and score if the move was successful
      this.renderBoardAndScore();
    } catch (IllegalArgumentException e) {
      // prompt the user to try another move combination
      try {
        this.view.renderMessage("Invalid move. Play again.\n");
      } catch (IOException err) {
        throw new IllegalStateException("Failed to render message");
      }
    }
  }

  // calls the view to display the message shown when a user quits the game
  private void quitGameMessage() throws IllegalStateException {
    try {
      this.view.renderMessage("Game quit!\n");
      this.view.renderMessage("State of game when quit:\n");
      this.renderBoardAndScore();
    } catch (IOException e) {
      throw new IllegalStateException("Failed to render board or message");
    }
  }

  // calls the view to display the message shown when a user has reached the 'game over' point
  private void gameOverMessage() throws IllegalStateException {
    try {
      this.view.renderMessage("Game over!\n");
    } catch (IOException e) {
      throw new IllegalStateException("Failed to render message");
    }
    this.renderBoardAndScore();
  }
}