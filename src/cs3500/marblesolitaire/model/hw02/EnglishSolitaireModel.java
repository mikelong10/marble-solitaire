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
    this.board = (new EnglishSolitaireModel(3)).board;
  }

  /**
   * Creates an English Solitaire game with an arm thickness of 3 with the empty slot at the
   * given coordinates (sRow, sCol)
   *
   * @param sRow the desired row coordinate for the empty slot for this game
   * @param sCol the desired column coordinate for the empty slot for this game
   * @throws IllegalArgumentException if the specified position is invalid
   */
  public EnglishSolitaireModel(int sRow, int sCol) throws IllegalArgumentException {
    if ((sRow < 2 || sRow > 4) && (sCol < 2 || sCol > 4)) {
      throw new IllegalArgumentException("Invalid empty cell position (" + sRow + "," + sCol + ")");
    }
    this.board = (new EnglishSolitaireModel()).board;
    this.board.get(3).set(3, SlotState.Marble);
    this.board.get(sRow).set(sCol, SlotState.Empty);
  }

  /**
   * Creates an English Solitaire game with a given arm thickness with the empty slot in the
   * center of the board
   *
   * @param arm the desired arm thickness for this game board
   * @throws IllegalArgumentException if the arm thickness is not a positive odd number
   */
  public EnglishSolitaireModel(int arm) throws IllegalArgumentException {
    if (arm <= 0 || arm % 2 == 1) {
      throw new IllegalArgumentException("Arm thickness is not a positive odd number");
    }
    this.board = new ArrayList<>();
    int boardLength = arm * 2 + 1;
    for (int i = 0; i < boardLength; i += 1) {
      if (i < arm - 1 || i > boardLength - arm) {
        ArrayList<SlotState> narrowRow = new ArrayList<>();
        for (int j = 0; j < boardLength; j += 1) {
          if (j < arm - 1 || j > boardLength - arm) {
            narrowRow.add(SlotState.Invalid);
          } else {
            narrowRow.add(SlotState.Marble);
          }
        }
        this.board.add(narrowRow);
      } else {
        ArrayList<SlotState> wideRow = new ArrayList<>();
        for (int j = 0; j < boardLength; j += 1) {
          if (j == boardLength / 2) {
            wideRow.add(SlotState.Empty);
          } else {
            wideRow.add(SlotState.Marble);
          }
        }
        this.board.add(wideRow);
      }
    }
  }

  /**
   * Creates an English Solitaire game with a specified arm thickness with the empty slot at
   * the given coordinates (sRow, sCol)
   *
   * @param arm  the desired arm thickness for this game board
   * @param sRow the desired row coordinate for the empty slot for this game
   * @param sCol the desired column coordinate for the empty slot for this game
   * @throws IllegalArgumentException if the arm thickness is not a positive odd number or if the
   *                                  specified coordinate is invalid
   */
  public EnglishSolitaireModel(int arm, int sRow, int sCol) throws IllegalArgumentException {
    this.board = (new EnglishSolitaireModel(arm)).board;
    int boardLength = arm * 2 + 1;
    this.board.get(boardLength / 2).set(boardLength / 2, SlotState.Marble);
    this.board.get(sRow).set(sCol, SlotState.Empty);
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

  }

  @Override
  public boolean isGameOver() {
    return false;
  }

  /**
   * Return the size of this board, representing the longest dimension of this board (either the
   * width or the height of the board on its longest length of valid positions)
   *
   * @return the size as an integer
   */
  @Override
  public int getBoardSize() {
    return this.board.size();
  }

  @Override
  public SlotState getSlotAt(int row, int col) throws IllegalArgumentException {
    if ((row < this.getBoardSize() - 1
            || row > this.getBoardSize() - (this.getBoardSize() / 2 - 1))
            && (row < this.getBoardSize() - 1 ||
            row > this.getBoardSize() - (this.getBoardSize() / 2 - 1))) {
      throw new IllegalArgumentException("Invalid cell position (" + row + "," + col + ")");
    }
    return this.board.get(row).get(col);
  }

  @Override
  public int getScore() {
    int score = 0;
    for (ArrayList<SlotState> gameRow : this.board) {
      for (SlotState slot : gameRow) {
        if (slot.equals(SlotState.Marble)) {
          score += 1;
        }
      }
    }
    return score;
  }
}
