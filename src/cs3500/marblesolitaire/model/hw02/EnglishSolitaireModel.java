package cs3500.marblesolitaire.model.hw02;

import cs3500.marblesolitaire.model.hw04.MarbleSolitaireAbstractModel;

/**
 * Represents the English version of the Marble Solitaire game with a plus-shaped board. This
 * class represents the model of the game, which handles and implements the actual functionality
 * of the game. This game is played with a plus-shaped board filled with marbles to begin with
 * one empty slot somewhere on the board. The objective of the game is to 'hop' over marbles to
 * remove them and remove as many marbles as you can before running out of valid moves.
 */
public class EnglishSolitaireModel extends MarbleSolitaireAbstractModel {

  /**
   * Creates a default English Solitaire game with an arm thickness of 3 with the empty slot in
   * the middle of the board.
   */
  public EnglishSolitaireModel() {
    // create a board with an arm thickness of 3 with the empty slot
    // defaulting to the middle of the board
    super(3);
  }

  /**
   * Creates an English Solitaire game with a given arm thickness with the empty slot in the
   * center of the board.
   *
   * @param arm the desired arm thickness for this game board
   * @throws IllegalArgumentException if the arm thickness is not a positive odd number
   */
  public EnglishSolitaireModel(int arm) throws IllegalArgumentException {
    super(arm);
  }

  /**
   * Creates an English Solitaire game with an arm thickness of 3 with the empty slot at the
   * given coordinates (sRow, sCol).
   *
   * @param sRow the desired row coordinate for the empty slot for this game
   * @param sCol the desired column coordinate for the empty slot for this game
   * @throws IllegalArgumentException if the specified position is invalid or out of bounds
   */
  public EnglishSolitaireModel(int sRow, int sCol) throws IllegalArgumentException {
    // initialize default 3x3 board
    super(3);
    super.setEmptySlot(sRow, sCol);
  }

  /**
   * Creates an English Solitaire game with a specified arm thickness with the empty slot at
   * the given coordinates (sRow, sCol).
   *
   * @param arm  the desired arm thickness for this game board
   * @param sRow the desired row coordinate for the empty slot for this game
   * @param sCol the desired column coordinate for the empty slot for this game
   * @throws IllegalArgumentException if the arm thickness is not a positive odd number or if the
   *                                  specified coordinate is invalid or out of bounds
   */
  public EnglishSolitaireModel(int arm, int sRow, int sCol) throws IllegalArgumentException {
    // initializes the board with a given arm thickness with the empty slot in the middle of the
    // board, and also checks if the given arm thickness is a positive odd number
    super(arm);
    super.setEmptySlot(sRow, sCol);
  }
}