package cs3500.marblesolitaire.model.hw04;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireGuiView;
import cs3500.marblesolitaire.view.MarbleSolitaireView;
import cs3500.marblesolitaire.view.TriangleSolitaireTextView;

/**
 * This class represents the Triangle Solitaire implementation of the
 * MarbleSolitaireAbstractFactory interface. This class can create TriangleSolitaireModel objects
 * and the corresponding view object according to specified parameters.
 */
public class TriangleSolitaireFactory implements MarbleSolitaireAbstractFactory {
  private final MarbleSolitaireModel model;

  /**
   * Creates an TriangleSolitaireFactory object, which initializes an TriangleSolitaireModel
   * with the given size, holeRow, and holeCol.
   *
   * @param size the desired size for the model board
   * @param holeRow the row location for the initial empty slot
   * @param holeCol the column location for the initial empty slot
   */
  public TriangleSolitaireFactory(int size, int holeRow, int holeCol) {
    this.model = new TriangleSolitaireModel(size, holeRow, holeCol);
  }

  @Override
  public MarbleSolitaireModel createModel() {
    return this.model;
  }

  @Override
  public MarbleSolitaireView createTextView() {
    return new TriangleSolitaireTextView(this.model, System.out);
  }

  @Override
  public MarbleSolitaireView createTextView(Appendable ap) {
    return new TriangleSolitaireTextView(this.model, ap);
  }

  @Override
  public MarbleSolitaireGuiView createGUIView() {
    throw new UnsupportedOperationException();
  }
}
