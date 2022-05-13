package cs3500.marblesolitaire.view;

/**
 * This interface represents operations that should be offered by
 * a view for the Marble solitaire game.
 */
public interface MarbleSolitaireView {
  /**
   * Return a string that represents the current state of the board. The
   * string should have one line per row of the game board. Each slot on the
   * game board is a single character (O, _ or space for a marble, empty and
   * invalid position respectively). Slots in a row should be separated by a
   * space. Each row has no space before the first slot and after the last slot.
   * @return the game state as a string
   */
  String toString();

}
