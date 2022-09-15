package cs3500.marblesolitaire.model.hw04;

import java.util.ArrayList;
import java.util.List;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;

/**
 * This class represents an abstract class for Marble Solitaire models. This abstract class
 * holds similar functionality between other MarbleSolitaireModel implementations in one place.
 * This class represents the model of the game, which handles and implements the actual
 * functionality of the game. This game is played with a board filled with marbles,
 * beginning with one empty slot somewhere on the board. The objective of the game is to 'hop' over
 * marbles to remove them and remove as many marbles as you can before running out of valid moves.
 */
public abstract class MarbleSolitaireAbstractModel implements MarbleSolitaireModel {
  protected final List<List<SlotState>> board;

  /**
   * Creates an English Solitaire game with a given arm thickness with the empty slot in the
   * center of the board.
   *
   * @param arm the desired arm thickness for this game board
   * @throws IllegalArgumentException if the arm thickness is not a positive odd number
   */
  public MarbleSolitaireAbstractModel(int arm) throws IllegalArgumentException {
    if (arm < 3 || arm % 2 == 0) {
      throw new IllegalArgumentException("Arm thickness is not a positive odd number");
    }
    this.board = new ArrayList<>();
    // calculate the board size based on the desired arm thickness
    int boardLength = 3 * arm - 2;
    // constructs all the rows of the game board
    for (int r = 0; r < boardLength; r += 1) {
      if (r < arm - 1 || r > boardLength - arm) {
        ArrayList<SlotState> narrowRow = new ArrayList<>();
        // constructs an individual 'narrow' row of the game board (arm thickness length)
        for (int c = 0; c < boardLength; c += 1) {
          if (c < arm - 1 || c > boardLength - arm) {
            narrowRow.add(SlotState.Invalid);
          } else {
            narrowRow.add(SlotState.Marble);
          }
        }
        // add the finished narrow row to the board
        this.board.add(narrowRow);
      } else {
        ArrayList<SlotState> wideRow = new ArrayList<>();
        // constructs an individual 'wide' row of the game board (board size length)
        for (int c = 0; c < boardLength; c += 1) {
          if (c == boardLength / 2 && r == boardLength / 2) {
            wideRow.add(SlotState.Empty);
          } else {
            wideRow.add(SlotState.Marble);
          }
        }
        // add the finished wide row to the board
        this.board.add(wideRow);
      }
    }
  }

  // sets this board's empty slot to the specified location if the coordinates are valid
  protected void setEmptySlot(int sRow, int sCol) {
    if (this.getSlotAt(sRow, sCol) == SlotState.Invalid) {
      throw new IllegalArgumentException("Invalid empty cell position (" + sRow + "," + sCol + ")");
    } else { // reset the empty slot to be the desired position
      this.board.get(this.getBoardSize() / 2).set(this.getBoardSize() / 2, SlotState.Marble);
      this.board.get(sRow).set(sCol, SlotState.Empty);
    }
  }

  /**
   * Move a single marble from a given position to another given position.
   * A move is valid only if the 'from' and 'to' positions are valid, there is a marble at the
   * specified 'from' position, the 'to' position is empty, the 'to' and 'from' positions are
   * exactly two positions away (horizontally or vertically), and there is a marble in the slot
   * between the 'to' and 'from' positions.
   *
   * @param fromRow the row number of the position to be moved from
   *                (starts at 0)
   * @param fromCol the column number of the position to be moved from
   *                (starts at 0)
   * @param toRow   the row number of the position to be moved to
   *                (starts at 0)
   * @param toCol   the column number of the position to be moved to
   *                (starts at 0)
   * @throws IllegalArgumentException if the move is not possible
   */
  @Override
  public void move(int fromRow, int fromCol, int toRow, int toCol) throws IllegalArgumentException {
    if (this.validMove(fromRow, fromCol, toRow, toCol)) {
      // pick up the marble at the 'from' position
      this.board.get(fromRow).set(fromCol, SlotState.Empty);
      // place it in the desired 'to' position
      this.board.get(toRow).set(toCol, SlotState.Marble);
      // removes the marble that was jumped over
      this.board.get((fromRow + toRow) / 2).set((fromCol + toCol) / 2, SlotState.Empty);
    }
  }

  // to determine if the desired move is valid (based on the constraints described in the
  // documentation for the move method)
  protected boolean validMove(int fromRow, int fromCol, int toRow, int toCol)
          throws IllegalArgumentException {
    boolean fromSlotMarble;
    if (this.getSlotAt(fromRow, fromCol) == SlotState.Empty) {
      throw new IllegalArgumentException("No marble at (" + (fromRow + 1) + "," + (fromCol + 1) +
              ") to move.");
    } else if (this.getSlotAt(fromRow, fromCol) == SlotState.Invalid) {
      throw new IllegalArgumentException("Cannot move from invalid slot (" +
              (fromRow + 1) + "," + (fromCol + 1) + ").");
    } else {
      fromSlotMarble = this.getSlotAt(fromRow, fromCol) == SlotState.Marble;
    }
    boolean toSlotEmpty;
    if (this.getSlotAt(toRow, toCol) == SlotState.Marble) {
      throw new IllegalArgumentException("Slot (" + (toRow + 1) + "," + (toCol + 1) + ") is not" +
              " empty.");
    } else if (this.getSlotAt(toRow, toCol) == SlotState.Invalid) {
      throw new IllegalArgumentException("Cannot move to invalid slot (" +
              (toRow + 1) + "," + (toCol + 1) + ").");
    } else {
      toSlotEmpty = this.getSlotAt(toRow, toCol) == SlotState.Empty;
    }
    boolean twoSpotsAway = this.twoSpotsAway(fromRow, fromCol, toRow, toCol);
    boolean marbleBetween;
    if (this.betweenSlot(fromRow, fromCol, toRow, toCol) != SlotState.Marble) {
      throw new IllegalArgumentException("No marble at (" + ((fromRow + toRow) / 2 + 1) + "," +
              ((fromCol + toCol) / 2 + 1) + ") between the desired 'from' and " +
              "'to' " + "positions" + ".");
    } else {
      marbleBetween = this.betweenSlot(fromRow, fromCol, toRow, toCol) == SlotState.Marble;
    }
    return fromSlotMarble && toSlotEmpty && twoSpotsAway && marbleBetween;
  }

  // to determine if the 'from' and 'to' positions for the desired move are two spaces away
  protected boolean twoSpotsAway(int fromRow, int fromCol, int toRow, int toCol)
          throws IllegalArgumentException {
    if ((fromRow == toRow && Math.abs(fromCol - toCol) == 2) ||
            (fromCol == toCol && Math.abs(fromRow - toRow) == 2)) {
      return true;
    } else {
      throw new IllegalArgumentException("'From' and 'to' positions for the desired move are not " +
              "two spaces away.");
    }
  }

  // to return the SlotState of the space in between valid 'from' and 'to' positions for a
  // desired move, getSlotAt checks if the 'from' and 'to' positions are valid or not
  private SlotState betweenSlot(int fromRow, int fromCol, int toRow, int toCol)
          throws IllegalArgumentException {
    return this.getSlotAt((fromRow + toRow) / 2, (fromCol + toCol) / 2);
  }

  @Override
  public boolean isGameOver() {
    // essentially an or-map to see if at least one valid move still exists in this game
    // loop through all the slots on the board
    for (int r = 0; r < this.getBoardSize(); r += 1) {
      for (int c = 0; c < this.getBoardSize(); c += 1) {
        if (this.getSlotAt(r, c) == SlotState.Marble) {
          if (this.validMoveExists(r, c)) {
            return false;
          }
        }
      }
    }
    // if there are no valid moves anywhere on the board, the game is over
    return true;
  }

  // to determine if there is a valid move at the location with coordinates (r, c)
  protected boolean validMoveExists(int r, int c) {
    return (this.validMoveLeft(r, c) || this.validMoveRight(r, c)
            || this.validMoveUp(r, c) || this.validMoveDown(r, c));
  }

  // to check whether there is a valid move from the given slot coordinates
  // to the slot two spaces to the left
  protected boolean validMoveLeft(int r, int c) {
    boolean ans;
    try {
      ans = this.validMove(r, c, r, c - 2);
    } catch (IllegalArgumentException e) {
      ans = false;
    }
    return ans;
  }

  // to check whether there is a valid move from the given slot coordinates
  // to the slot two spaces to the right
  protected boolean validMoveRight(int r, int c) {
    boolean ans;
    try {
      ans = this.validMove(r, c, r, c + 2);
    } catch (IllegalArgumentException e) {
      ans = false;
    }
    return ans;
  }

  // to check whether there is a valid move from the given slot coordinates
  // to the slot two spaces up
  protected boolean validMoveUp(int r, int c) {
    boolean ans;
    try {
      ans = this.validMove(r, c, r - 2, c);
    } catch (IllegalArgumentException e) {
      ans = false;
    }
    return ans;
  }

  // to check whether there is a valid move from the given slot coordinates
  // to the slot two spaces down
  protected boolean validMoveDown(int r, int c) {
    boolean ans;
    try {
      ans = this.validMove(r, c, r + 2, c);
    } catch (IllegalArgumentException e) {
      ans = false;
    }
    return ans;
  }

  @Override
  public int getBoardSize() {
    return this.board.size();
  }

  @Override
  public SlotState getSlotAt(int row, int col) throws IllegalArgumentException {
    if ((row < 0 || row >= this.getBoardSize()) || (col < 0 || col >= this.getBoardSize())) {
      throw new IllegalArgumentException("Invalid cell position (" + (row + 1) + "," + (col + 1) +
              ").");
    }
    return this.board.get(row).get(col);
  }

  @Override
  public int getScore() {
    int score = 0;
    // loop through all the slots on the board and count the number of Marbles
    for (int r = 0; r < this.getBoardSize(); r += 1) {
      for (int c = 0; c < this.getBoardSize(); c += 1) {
        if (this.getSlotAt(r, c) == SlotState.Marble) {
          score += 1;
        }
      }
    }
    return score;
  }
}
