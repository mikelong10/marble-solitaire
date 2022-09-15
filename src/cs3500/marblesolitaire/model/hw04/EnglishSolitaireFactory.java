package cs3500.marblesolitaire.model.hw04;

import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireGuiView;
import cs3500.marblesolitaire.view.MarbleSolitaireTextView;
import cs3500.marblesolitaire.view.MarbleSolitaireView;
import cs3500.marblesolitaire.view.SwingGuiView;

/**
 * This class represents the European Solitaire implementation of the
 * MarbleSolitaireAbstractFactory interface. This class can create EnglishSolitaireModel objects
 * and the corresponding view object according to specified parameters.
 */
public class EnglishSolitaireFactory implements MarbleSolitaireAbstractFactory {
  private final MarbleSolitaireModel model;

  /**
   * Creates an EnglishSolitaireFactory object, which initializes an EnglishSolitaireModel
   * with the given size, holeRow, and holeCol.
   *
   * @param size the desired size for the model board
   * @param holeRow the row location for the initial empty slot
   * @param holeCol the column location for the initial empty slot
   */
  public EnglishSolitaireFactory(int size, int holeRow, int holeCol) {
    this.model = new EnglishSolitaireModel(size, holeRow, holeCol);
  }

  @Override
  public MarbleSolitaireModel createModel() {
    return this.model;
  }

  @Override
  public MarbleSolitaireView createTextView() {
    return new MarbleSolitaireTextView(this.model, System.out);
  }

  @Override
  public MarbleSolitaireView createTextView(Appendable ap) {
    return new MarbleSolitaireTextView(this.model, ap);
  }

  @Override
  public MarbleSolitaireGuiView createGUIView() {
    return new SwingGuiView(this.model);
  }
}
