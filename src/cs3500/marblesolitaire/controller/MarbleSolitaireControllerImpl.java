package cs3500.marblesolitaire.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireView;

/**
 * This class represents an implementation of the MarbleSolitaireController interface to allow a
 * user to play a game of Marble Solitaire. This controller class works with a model of a game of
 * Marble Solitaire to take in user input and tell the model what to do. It also works with a
 * view of the game to tell the view what to display.
 */
public class MarbleSolitaireControllerImpl implements MarbleSolitaireController {
  private final MarbleSolitaireModel model;
  private final MarbleSolitaireView view;
  private final Readable input;

  /**
   * Creates a controller object to play a game given a model, view, and a Readable input. This
   * constructor rejects any null parameters.
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
      throw new IllegalArgumentException("Readable cannot be null");
    }
    this.model = model;
    this.view = view;
    this.input = input;
  }

  /**
   * To play a game of Marble Solitaire, where 'q' or 'Q' can be used to quit the game, and valid
   * integers representing slots on the board (beginning from 1) can be used to make moves and
   * play the game. Any other user input is invalid and will prompt the user to re-enter a value.
   * The game ends if the user runs out of valid moves or enters 'q' or 'Q'.
   *
   * @throws IllegalStateException if the controller is unable to successfully read input or
   *                               transmit output, or if the scanner runs out of inputs before
   *                               the game is over or quit
   */
  @Override
  public void playGame() throws IllegalStateException {
    Scanner scan = new Scanner(this.input);
    boolean quitGame = false;
    // create list of Integers to store potential move inputs given by the user
    ArrayList<Integer> moveInputs = new ArrayList<>();
    this.startGameMessage();
    while (!this.model.isGameOver() && !quitGame) {
      String next;
      try {
        next = scan.next(); // try to get the next element from the scanner
      } catch (NoSuchElementException e) { // if there are no more elements in the scanner
        throw new IllegalStateException("No more inputs remaining");
      }
      if (next.equalsIgnoreCase("q")) { // if 'q' or 'Q' is the next input
        quitGame = true;
        this.quitGameMessage();
      } else if (this.isPositiveInt(next)) {
        moveInputs.add(Integer.parseInt(next) - 1);
        if (moveInputs.size() >= 4) {
          this.attemptMove(moveInputs);
        }
      } else { // the next input is not either a 'q', 'Q', or a positive int
        this.signalInvalidInput(); // ask the user to enter another value
      }
    }
    if (this.model.isGameOver()) {
      this.gameOverMessage();
    }
  }

  // calls the view to render the message at the start of a game and the starting board and score
  private void startGameMessage() throws IllegalStateException {
    this.renderMessageHelper("Welcome to Marble Solitaire!\nPlay Now:\n");
    this.renderBoardAndScore();
  }

  // calls the view to render the board and display the user's current score
  private void renderBoardAndScore() throws IllegalStateException {
    this.renderBoardHelper();
    this.renderMessageHelper("\nScore: " + this.model.getScore() + "\n");
  }

  // to determine whether the String input from the scanner
  // represents a positive integer (true) or not (false)
  private boolean isPositiveInt(String s) {
    int moveInput;
    try {
      // initialize moveInput to the integer value of s,
      // IF it can be parsed as an integer
      moveInput = Integer.parseInt(s);
    } catch (NumberFormatException e) { // if it can't be parsed as an int
      return false;
    }
    return moveInput > 0;
  }

  // to signal to the user that their last input was invalid and
  // prompt them to re-enter a valid value
  private void signalInvalidInput() throws IllegalStateException {
    this.renderMessageHelper("Please re-enter a valid input (positive numbers to play a move OR " +
            "'q' to quit):\n");
  }

  // to have the model attempt a move with the first four values in the given list of potential
  // move inputs, only called when moveInputs has at least 4 values
  private void attemptMove(List<Integer> moveInputs)
          throws IllegalStateException {
    try {
      // calls the model to attempt a move with the given 'from' and 'to' position coordinates
      this.model.move(moveInputs.remove(0), moveInputs.remove(0),
              moveInputs.remove(0), moveInputs.remove(0));
      // renders the updated board and score if the move was successful
      this.renderBoardAndScore();
    } catch (IllegalArgumentException e) { // if the move was invalid
      // prompts the user to try another move combination and explains why the move was invalid
      this.renderMessageHelper("Invalid move. Play again. " + e.getMessage() + "\n");
    }
  }

  // calls the view to display the message shown when a user quits the game
  // and their final board display and score
  private void quitGameMessage() throws IllegalStateException {
    this.renderMessageHelper("Game quit!\n");
    this.renderMessageHelper("State of game when quit:\n");
    this.renderBoardAndScore();
  }

  // calls the view to display the message shown when a user
  // has reached the 'game over' point by playing the game
  private void gameOverMessage() throws IllegalStateException {
    this.renderMessageHelper("Game over!\n");
    this.renderBoardAndScore();
  }

  // helper method calling the view to render the board,
  // taking care of any IOExceptions potentially thrown by renderBoard
  private void renderBoardHelper() {
    try {
      this.view.renderBoard();
    } catch (IOException e) {
      throw new IllegalStateException("Failed to render board");
    }
  }

  // helper method calling the view to render a given message,
  // taking care of any IOExceptions potentially thrown by renderMessage
  private void renderMessageHelper(String message) {
    try {
      this.view.renderMessage(message);
    } catch (IOException e) {
      throw new IllegalStateException("Failed to render message");
    }
  }
}