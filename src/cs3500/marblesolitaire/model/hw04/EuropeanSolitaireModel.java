package cs3500.marblesolitaire.model.hw04;

/**
 * This class represents the European version of the Marble Solitaire board model. This model's
 * functionality is similar to an English board, except the corners between the arms of the cross
 * are filled in to produce an octagon shape.
 */
public class EuropeanSolitaireModel extends MarbleSolitaireAbstractModel {
  /**
   * Creates an octagonal board whose sides have length 3,
   * with the empty slot in the center of the board.
   */
  public EuropeanSolitaireModel() {
    super(3);
    this.europeanize();
  }

  /**
   * Creates a game with the specified side length,
   * and the empty slot in the center of the board.
   *
   * @param sideLength the length for each side of the octagonal board
   */
  public EuropeanSolitaireModel(int sideLength) {
    super(sideLength);
    this.europeanize();
  }

  /**
   * Creates an octagonal board with two parameters (row, col)
   * to specify the initial position of the empty slot,
   * in a board of default size 3.
   *
   * @param row the row coordinate for the initial empty slot
   * @param col the column coordinate for the initial empty slot
   */
  public EuropeanSolitaireModel(int row, int col) {
    super(3);
    this.europeanize();
    this.setEmptySlot(row, col);
  }

  /**
   * Creates an octagonal board with three parameters (side length, row, col),
   * to specify the size of the board and the initial position of the empty slot.
   *
   * @param sideLength the length for each side of the octagonal board
   * @param row        the row coordinate for the initial empty slot
   * @param col        the column coordinate for the initial empty slot
   */
  public EuropeanSolitaireModel(int sideLength, int row, int col) {
    super(sideLength);
    this.europeanize();
    this.setEmptySlot(row, col);
  }

  // to turn this solitaire model, originally an English plus-shaped board,
  // into a European octagon-shaped board model
  private void europeanize() {
    int arm = (this.getBoardSize() + 2) / 3;
    for (int r = 0; r < this.getBoardSize(); r += 1) {
      // filling in the extra triangles of marbles on the top half of the board
      if (r < arm - 1) {
        for (int c = arm - 1; c >= arm - 1 - r; c -= 1) {
          this.board.get(r).set(c, SlotState.Marble);
        }
        for (int c = this.getBoardSize() - arm + 1; c < this.getBoardSize() - arm + 1 + r; c += 1) {
          this.board.get(r).set(c, SlotState.Marble);
        }
      }
      // filling in the extra triangles of marbles on the bottom half of the board
      if (r > this.getBoardSize() - arm) {
        for (int c = (arm - 1) - ((this.getBoardSize() - 1) - r); c < arm - 1; c += 1) {
          this.board.get(r).set(c, SlotState.Marble);
        }
        for (int c = this.getBoardSize() - arm + 1;
             c < (this.getBoardSize() - arm) + ((this.getBoardSize()) - r); c += 1) {
          this.board.get(r).set(c, SlotState.Marble);
        }
      }
    }
  }
}
