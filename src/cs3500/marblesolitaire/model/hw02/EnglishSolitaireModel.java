package cs3500.marblesolitaire.model.hw02;

import java.util.ArrayList;

/**
 * Represents the English version of the Marble Solitaire game with a plus-shaped board.
 */
public class EnglishSolitaireModel implements MarbleSolitaireModel {
  private final ArrayList<ArrayList<SlotState>> board;

  /**
   * Creates a default English Solitaire game with an arm thickness of 3 with the empty slot in
   * the middle of the board.
   */
  public EnglishSolitaireModel() {
    // create a board with an arm thickness of 3 with the empty slot defaulting to the middle of
    // the board
    this.board = (new EnglishSolitaireModel(3)).board;
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
    this.board = (new EnglishSolitaireModel()).board;
    this.setEmptySlot(sRow, sCol);
  }

  /**
   * Creates an English Solitaire game with a given arm thickness with the empty slot in the
   * center of the board.
   *
   * @param arm the desired arm thickness for this game board
   * @throws IllegalArgumentException if the arm thickness is not a positive odd number
   */
  public EnglishSolitaireModel(int arm) throws IllegalArgumentException {
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
    this.board = (new EnglishSolitaireModel(arm)).board;
    this.setEmptySlot(sRow, sCol);
  }

  // sets this board's empty slot to the specified location if the coordinates are valid
  private void setEmptySlot(int sRow, int sCol) {
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
    if (!this.validMove(fromRow, fromCol, toRow, toCol)) {
      throw new IllegalArgumentException("Invalid move");
    } else {
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
  private boolean validMove(int fromRow, int fromCol, int toRow, int toCol) {
    return (this.getSlotAt(fromRow, fromCol) == SlotState.Marble)
            && (this.getSlotAt(toRow, toCol) == SlotState.Empty)
            && this.twoSpotsAway(fromRow, fromCol, toRow, toCol)
            && this.betweenSlot(fromRow, fromCol, toRow, toCol) == SlotState.Marble;
  }

  // to determine if the 'from' and 'to' positions for the desired move are two spaces away
  private boolean twoSpotsAway(int fromRow, int fromCol, int toRow, int toCol) {
    if (fromRow == toRow) {
      return Math.abs(fromCol - toCol) == 2;
    } else if (fromCol == toCol) {
      return Math.abs(fromRow - toRow) == 2;
    } else {
      return false;
    }
  }

  // to return the SlotState of the space in between valid 'from' and 'to' positions for a
  // desired move, getSlotAt checks if the 'from' and 'to' positions are valid or not
  private SlotState betweenSlot(int fromRow, int fromCol, int toRow, int toCol) {
    return this.getSlotAt((fromRow + toRow) / 2, (fromCol + toCol) / 2);
  }

  @Override
  public boolean isGameOver() {
    // essentially an or-map to see if at least one valid move still exists in this game
    // loop through all the slots on the board
    for (int r = 0; r < this.getBoardSize(); r += 1) {
      for (int c = 0; c < this.getBoardSize(); c += 1) {
        if (this.getSlotAt(r, c) != SlotState.Invalid) {
          // if at one of the top 2 rows
          if (r < 2) {
            // check left, right, down for a valid move
            if (this.validMove(r, c, r, c - 2)
                    || this.validMove(r, c, r, c + 2)
                    || this.validMove(r, c, r + 2, c)) {
              return false;
            }
            // if at one of the bottom 2 rows
          } else if (r > this.getBoardSize() - 3) {
            // check left, right, up for a valid move
            if (this.validMove(r, c, r, c - 2)
                    || this.validMove(r, c, r, c + 2)
                    || this.validMove(r, c, r - 2, c)) {
              return false;
            }
            // if at one of the 2 leftmost columns
          } else if (c < 2) {
            // check up, down, right for a valid move
            if (this.validMove(r, c, r - 2, c)
                    || this.validMove(r, c, r + 2, c)
                    || this.validMove(r, c, r, c + 2)) {
              return false;
            }
            // if at one of the 2 rightmost columns
          } else if (c > this.getBoardSize() - 3) {
            // check up, down, left for a valid move
            if (this.validMove(r, c, r - 2, c)
                    || this.validMove(r, c, r + 2, c)
                    || this.validMove(r, c, r, c - 2)) {
              return false;
            }
          } else { // we are safe from going out of bounds looking for valid moves
            if (this.validMove(r, c, r - 2, c)
                    || this.validMove(r, c, r + 2, c)
                    || this.validMove(r, c, r, c - 2)
                    || this.validMove(r, c, r, c + 2)) {
              return false;
            }
          }
        }
      }
    }
    // if there are no valid moves anywhere on the board, the game is over
    return true;
  }

  @Override
  public int getBoardSize() {
    return this.board.size();
  }

  @Override
  public SlotState getSlotAt(int row, int col) throws IllegalArgumentException {
    if ((row < 0 || row >= this.getBoardSize()) || (col < 0 || col >= this.getBoardSize())) {
      throw new IllegalArgumentException("Invalid cell position (" + row + "," + col + ")");
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
