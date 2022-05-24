package cs3500.marblesolitaire.view;

import java.io.IOException;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;

/**
 * Represents a view of a Marble Solitaire game in text form.
 */
public class MarbleSolitaireTextView implements MarbleSolitaireView {
  private final MarbleSolitaireModelState model;
  private final Appendable dest;

  /**
   * Creates a text view of a Marble Solitaire game from a given model game state.
   *
   * @param model the given game model for this view to display
   * @throws IllegalArgumentException if the provided model is null
   */
  public MarbleSolitaireTextView(MarbleSolitaireModelState model) throws IllegalArgumentException {
    if (model == null) {
      throw new IllegalArgumentException("Provided model cannot be null");
    }
    this.model = model;
    this.dest = System.out;
  }

  /**
   * Creates a text view of a Marble Solitaire game from a given model game state and transmits the
   * output to a given Appendable destination.
   *
   * @param model the given game model for this view to display
   * @param dest the given Appendable destination for this view to transmit output to
   * @throws IllegalArgumentException if any of the provided parameters are null
   */
  public MarbleSolitaireTextView(MarbleSolitaireModelState model, Appendable dest)
          throws IllegalArgumentException {
    if (model == null) {
      throw new IllegalArgumentException("Provided model cannot be null");
    }
    if (dest == null) {
      throw new IllegalArgumentException("Appendable destination cannot be null");
    }
    this.model = model;
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
  public String toString() {
    int armThickness = (this.model.getBoardSize() + 2) / 3;
    String result = "";
    // loop through all the slots on the board
    for (int r = 0; r < this.model.getBoardSize(); r += 1) {
      for (int c = 0; c < this.model.getBoardSize(); c += 1) {
        if (this.model.getSlotAt(r, c) == MarbleSolitaireModelState.SlotState.Marble) {
          result = result.concat(this.processValidSlot(r, c, armThickness, "O"));
        } else if (this.model.getSlotAt(r, c) == MarbleSolitaireModelState.SlotState.Empty) {
          result = result.concat(this.processValidSlot(r, c, armThickness, "_"));
        } else if (this.model.getSlotAt(r, c) == MarbleSolitaireModelState.SlotState.Invalid) {
          if (c < armThickness - 1) {
            result = result.concat("  ");
          }
        }
      }
      if (r != this.model.getBoardSize() - 1) {
        result = result.concat("\n");
      }
    }
    return result;
  }

  // returns the String text view of a valid slot on a game board given a specified String to
  // represent the slot, with a space after the symbol if it is not on the right edge of the board
  private String processValidSlot(int r, int c, int armThickness, String slotSymbol) {
    if (this.isRightEdgeSlot(r, c, armThickness)) {
      return slotSymbol;
    } else {
      return slotSymbol + " ";
    }
  }

  // to determine if the given position at (r, c) is at the right edge of the game board with a
  // given arm thickness
  private boolean isRightEdgeSlot(int r, int c, int armThickness) {
    return (((r < armThickness - 1 || r > this.model.getBoardSize() - armThickness)
            && c == this.model.getBoardSize() - armThickness)
            || ((r >= armThickness - 1 && r <= this.model.getBoardSize() - armThickness)
            && c == this.model.getBoardSize() - 1));
  }
}
