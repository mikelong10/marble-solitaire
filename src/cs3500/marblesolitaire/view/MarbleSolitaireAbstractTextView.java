package cs3500.marblesolitaire.view;

import java.io.IOException;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;

/**
 * This abstract class represents a text-based view for a Marble Solitaire program. It uses a
 * MarbleSolitaireModelState to determine the contents it should display and transmits its output
 * to a suitable Appendable destination. This class has the ability to render the board in
 * a text format and also render messages to the provided destination.
 */
public abstract class MarbleSolitaireAbstractTextView implements MarbleSolitaireView {
  protected final MarbleSolitaireModelState state;
  protected final Appendable dest;

  /**
   * Creates a text view of a Marble Solitaire game from a given model game state.
   *
   * @param state the given game state for this view to display
   * @throws IllegalArgumentException if the provided state is null
   */
  public MarbleSolitaireAbstractTextView(MarbleSolitaireModelState state)
          throws IllegalArgumentException {
    if (state == null) {
      throw new IllegalArgumentException("Provided state cannot be null");
    }
    this.state = state;
    this.dest = System.out;
  }

  /**
   * Creates a text view of a Marble Solitaire game from a given model game state that
   * transmits the output to a given Appendable destination.
   *
   * @param state the given game state for this view to display
   * @param dest the given Appendable destination for this view to transmit output to
   * @throws IllegalArgumentException if any of the provided parameters are null
   */
  public MarbleSolitaireAbstractTextView(MarbleSolitaireModelState state, Appendable dest)
          throws IllegalArgumentException {
    if (state == null) {
      throw new IllegalArgumentException("Provided state cannot be null");
    }
    if (dest == null) {
      throw new IllegalArgumentException("Appendable destination cannot be null");
    }
    this.state = state;
    this.dest = dest;
  }

  @Override
  public void renderBoard() throws IOException {
    this.dest.append(this.toString());
  }

  @Override
  public void renderMessage(String message) throws IOException {
    this.dest.append(message);
  }

  @Override
  public abstract String toString();

  // returns the String text view of a valid slot on a game board given a specified String to
  // represent the slot, with a space after the symbol if it is not on the right edge of the board
  protected String processValidSlot(int r, int c, String slotSymbol) {
    if (this.isRightEdgeSlot(r, c)) {
      return slotSymbol;
    } else {
      return slotSymbol + " ";
    }
  }

  // to determine if the given position at (r, c) is at the right edge of the game board with a
  // given arm thickness
  protected boolean isRightEdgeSlot(int r, int c) {
    boolean atRightEdge;
    try {
      atRightEdge = this.state.getSlotAt(r, c + 1) == MarbleSolitaireModelState.SlotState.Invalid;
    } catch (IllegalArgumentException e) {
      atRightEdge = true;
    }
    return atRightEdge;
  }
}
