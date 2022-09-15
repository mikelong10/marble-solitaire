package cs3500.marblesolitaire.view;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;

/**
 * Represents a view of a Marble Solitaire game in text form. This class works with a model of a
 * game of Marble Solitaire to produce the output that would be shown and also transmits that
 * output to a suitable Appendable destination. This class has the ability to render the board in
 * a text format and also render messages to the provided destination.
 */
public class MarbleSolitaireTextView extends MarbleSolitaireAbstractTextView {

  /**
   * Creates a text view of a Marble Solitaire game from a given model game state.
   *
   * @param state the given game state for this view to display
   * @throws IllegalArgumentException if the provided state is null
   */
  public MarbleSolitaireTextView(MarbleSolitaireModelState state) throws IllegalArgumentException {
    super(state);
  }

  /**
   * Creates a text view of a Marble Solitaire game from a given model game state
   * that transmits the output to a given Appendable destination.
   *
   * @param state the given game state for this view to display
   * @param dest  the given Appendable destination for this view to transmit output to
   * @throws IllegalArgumentException if any of the provided parameters are null
   */
  public MarbleSolitaireTextView(MarbleSolitaireModelState state, Appendable dest)
          throws IllegalArgumentException {
    super(state, dest);
  }

  @Override
  public String toString() {
    int armThickness = (this.state.getBoardSize() + 2) / 3;
    StringBuilder result = new StringBuilder();
    // loop through all the slots on the board
    for (int r = 0; r < this.state.getBoardSize(); r += 1) {
      for (int c = 0; c < this.state.getBoardSize(); c += 1) {
        if (this.state.getSlotAt(r, c) == MarbleSolitaireModelState.SlotState.Marble) {
          result.append(this.processValidSlot(r, c, "O"));
        } else if (this.state.getSlotAt(r, c) == MarbleSolitaireModelState.SlotState.Empty) {
          result.append(this.processValidSlot(r, c, "_"));
        } else if (this.state.getSlotAt(r, c) == MarbleSolitaireModelState.SlotState.Invalid) {
          if (c < armThickness - 1) {
            result.append("  ");
          }
        }
      }
      if (r != this.state.getBoardSize() - 1) {
        result.append("\n");
      }
    }
    return result.toString();
  }
}
