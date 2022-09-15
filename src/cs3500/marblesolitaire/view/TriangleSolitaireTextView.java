package cs3500.marblesolitaire.view;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;

/**
 * Represents a view of a Triangular Marble Solitaire game in text form. This class works with a
 * model of a game of Triangle Solitaire to produce the output that would be shown and also
 * transmits that output to a suitable Appendable destination. This class has the ability to
 * render the board in a text format and also render messages to the provided destination.
 */
public class TriangleSolitaireTextView extends MarbleSolitaireAbstractTextView {

  /**
   * Creates a text view of a Triangular Marble Solitaire game from a given model game state.
   *
   * @param state the given game state for this view to display
   * @throws IllegalArgumentException if the provided state is null
   */
  public TriangleSolitaireTextView(MarbleSolitaireModelState state)
          throws IllegalArgumentException {
    super(state);
  }

  /**
   * Creates a text view of a Triangular Marble Solitaire game from a given model game state that
   * transmits the output to a given Appendable destination.
   *
   * @param state the given game state for this view to display
   * @param dest the given Appendable destination for this view to transmit output to
   * @throws IllegalArgumentException if any of the provided parameters are null
   */
  public TriangleSolitaireTextView(MarbleSolitaireModelState state, Appendable dest)
          throws IllegalArgumentException {
    super(state, dest);
  }

  @Override
  public String toString() {
    String result = "";
    for (int r = 0; r < this.state.getBoardSize(); r += 1) {
      for (int c = 0; c < (this.state.getBoardSize() * 2 - 1) / 2 - r; c += 1) {
        result = result.concat(" ");
      }
      for (int c = 0; c <= r; c += 1) {
        if (this.state.getSlotAt(r, c) == MarbleSolitaireModelState.SlotState.Marble) {
          result = result.concat(this.processValidSlot(r, c, "O"));
        } else if (this.state.getSlotAt(r, c) == MarbleSolitaireModelState.SlotState.Empty) {
          result = result.concat(this.processValidSlot(r, c, "_"));
        }
      }
      if (r != this.state.getBoardSize() - 1) {
        result = result.concat("\n");
      }
    }
    return result;
  }
}
