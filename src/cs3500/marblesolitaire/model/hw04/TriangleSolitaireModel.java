package cs3500.marblesolitaire.model.hw04;

/**
 * This class represents a board for Triangular Marble Solitaire, a version of Marble Solitaire
 * that has marbles arranged in an equilateral triangle. A family of such games can be created by
 * modifying the dimension of this board, in the form of the number of marbles in its
 * bottom-most row. In this model, marbles can move left and right and also along the four
 * diagonal directions.
 */
public class TriangleSolitaireModel extends MarbleSolitaireAbstractModel {

  /**
   * Creates a 5-row game with the empty slot at (0,0).
   */
  public TriangleSolitaireModel() {
    super(5);
    this.triangulate();
    this.setEmptySlot(0, 0);
  }

  /**
   * Creates a game with the specified dimension (number of slots in the bottom-most row)
   * and the empty slot at (0,0).
   *
   * @param size the number of slots for the bottom-most row (the size of the triangular board)
   * @throws IllegalArgumentException if the specified dimension is invalid (non-positive)
   */
  public TriangleSolitaireModel(int size) throws IllegalArgumentException {
    super((size / 2) + (size / 2 + 1));
    this.triangulate();
    this.setEmptySlot(0, 0);
    if (size % 2 == 0) {
      this.board.remove(this.getBoardSize() - 1);
    }
  }

  /**
   * Creates a 5-row game with the empty slot at the specified position.
   *
   * @param r the row coordinate for the initial empty slot
   * @param c the column coordinate for the initial empty slot
   * @throws IllegalArgumentException if the specified position is invalid for the board
   */
  public TriangleSolitaireModel(int r, int c) throws IllegalArgumentException {
    super(5);
    this.triangulate();
    this.setEmptySlot(r, c);
  }

  /**
   * Creates a game with the specified dimension
   * and an empty slot at the specified row and column.
   *
   * @param size the number of slots for the bottom-most row (the size of the triangular board)
   * @param r    the row coordinate for the initial empty slot
   * @param c    the column coordinate for the initial empty slot
   * @throws IllegalArgumentException if the specified dimension is invalid (non-positive) OR
   *                                  if the specified position is invalid for the board
   */
  public TriangleSolitaireModel(int size, int r, int c) throws IllegalArgumentException {
    this(size);
    this.board.get(0).set(0, SlotState.Marble);
    this.setEmptySlot(r, c);
  }

  // to turn this solitaire model, originally an English plus-shaped board,
  // into a Triangular board model
  private void triangulate() {
    int boardLength = this.getBoardSize();
    int arm = (boardLength + 2) / 3;
    // remove all rows below the num of rows for triangle model (normal board arm thickness)
    for (int r = boardLength - 1; r > arm - 1; r -= 1) {
      this.board.remove(r);
    }
    for (int r = this.getBoardSize() - 1; r >= 0; r -= 1) {
      for (int c = boardLength - 1; c >= 0; c -= 1) {
        // remove all the Slots to the left or right of the top arm of marbles
        if (c > boardLength - arm) {
          this.board.get(r).remove(c);
        } else if (c < arm - 1) {
          this.board.get(r).remove(c);
        }
      }
      // set the top right half triangle of the current square board of all marbles to Invalid
      // slots, creating the desired triangular-based model, constructed in a rectangular grid
      if (r < arm - 1) {
        for (int c = arm - 1; c >= arm - (arm - 1 - r); c -= 1) {
          this.board.get(r).set(c, SlotState.Invalid);
        }
      }
    }
  }

  // to determine if there is a valid move at the location with coordinates (r, c),
  // considering moves to the left, right, and the four diagonal directions for the triangular board
  @Override
  protected boolean validMoveExists(int r, int c) {
    return super.validMoveExists(r, c) || this.validMoveNW(r, c) || this.validMoveSE(r, c);
  }

  // to determine if there is a valid move to the northwest diagonal direction
  private boolean validMoveNW(int r, int c) {
    boolean ans;
    try {
      ans = this.validMove(r, c, r - 2, c - 2);
    } catch (IllegalArgumentException e) {
      ans = false;
    }
    return ans;
  }

  // to determine if there is a valid move to the southeast diagonal direction
  private boolean validMoveSE(int r, int c) {
    boolean ans;
    try {
      ans = this.validMove(r, c, r + 2, c + 2);
    } catch (IllegalArgumentException e) {
      ans = false;
    }
    return ans;
  }

  // to determine if the 'from' and 'to' positions for the desired move are two spaces away,
  // considering the 'four diagonal' moves for a triangular board
  @Override
  protected boolean twoSpotsAway(int fromRow, int fromCol, int toRow, int toCol)
          throws IllegalArgumentException {
    if ((fromRow == toRow && Math.abs(fromCol - toCol) == 2) ||
            (fromCol == toCol && Math.abs(fromRow - toRow) == 2) ||
            (this.movesWithPositiveSlope(fromRow, fromCol, toRow, toCol))) {
      return true;
    } else {
      throw new IllegalArgumentException("'From' and 'to' positions for the desired move are not " +
              "two spaces away.");
    }
  }

  // to determine if a 'diagonal' move is valid. a diagonal move in the northeast and southwest
  // directions are treated as normal up and down moves in the triangle model, so diagonal moves
  // in the northwest and southeast directions are considered diagonal. we differentiate valid
  // diagonal moves based on if they move on a line with positive slope, with the row numbers
  // being the x-axis and the col numbers being the y-axis (counting from 0 up)
  private boolean movesWithPositiveSlope(int fromRow, int fromCol, int toRow, int toCol) {
    boolean twoAway = Math.abs(fromRow - toRow) == 2 && Math.abs(fromCol - toCol) == 2;
    boolean positiveSlope = (toCol - fromCol) / (toRow - fromRow) > 0;
    return twoAway && positiveSlope;
  }
}

