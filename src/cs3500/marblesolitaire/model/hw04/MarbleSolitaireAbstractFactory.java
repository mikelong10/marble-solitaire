package cs3500.marblesolitaire.model.hw04;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireGuiView;
import cs3500.marblesolitaire.view.MarbleSolitaireView;

/**
 * This abstract class represents a set of factory methods for building correct
 * corresponding view-model pairs. Each concrete implementation can act as factories that
 * manufacture specific marble solitaire models and views.
 */
public interface MarbleSolitaireAbstractFactory {
  /**
   * Returns the factory object for the given type of Marble Solitaire game to be created
   * from default parameters.
   *
   * @param name the type of Marble Solitaire game to be created (english, european, triangular)
   * @return the factory object to be created from default parameters
   */
  static MarbleSolitaireAbstractFactory getFactory(String name) {
    if (name.equalsIgnoreCase("triangular")) {
      return getFactory(name, 5, 0, 0);
    } else {
      return getFactory(name, 3, 3, 3);
    }
  }

  /**
   * Returns the factory object for the given type of Marble Solitaire game to be created
   * from a given size parameter.
   *
   * @param name the type of Marble Solitaire game to be created (english, european, triangular)
   * @param size the desired size for the game model
   * @return the factory object to be created from the parameters
   */
  static MarbleSolitaireAbstractFactory getFactory(String name, int size) {
    if (name.equalsIgnoreCase("triangular")) {
      return getFactory(name, size, 0, 0);
    } else {
      return getFactory(name, size, (size * 3 - 2) / 2, (size * 3 - 2) / 2);
    }
  }

  /**
   * Returns the factory object for the given type of Marble Solitaire game to be created
   * from a given location for the initial empty slot.
   *
   * @param name the type of Marble Solitaire game to be created (english, european, triangular)
   * @param r    row position for empty slot
   * @param c    column position for empty slot
   * @return the factory object to be created from the parameters
   */
  static MarbleSolitaireAbstractFactory getFactory(String name, int r, int c) {
    if (name.equalsIgnoreCase("triangular")) {
      return getFactory(name, 5, r, c);
    } else {
      return getFactory(name, 3, r, c);
    }
  }

  /**
   * Returns the factory object for the given type of Marble Solitaire game to be created
   * from a given size and location for the initial empty slot.
   *
   * @param name the type of Marble Solitaire game to be created (english, european, triangular)
   * @param size the desired size for the game model
   * @param r    row position for empty slot
   * @param c    column position for empty slot
   * @return the factory object to be created from the parameters
   */
  static MarbleSolitaireAbstractFactory getFactory(String name, int size, int r, int c) {
    switch (name.toLowerCase()) {
      case "english":
        return new EnglishSolitaireFactory(size, r, c);
      case "european":
        return new EuropeanSolitaireFactory(size, r, c);
      case "triangular":
        return new TriangleSolitaireFactory(size, r, c);
      default:
        throw new IllegalArgumentException("Invalid name");
    }
  }

  /**
   * To produce the MarbleSolitaireModel object for this factory,
   * according to the factory's specified parameters.
   *
   * @return the MarbleSolitaireModel that this factory produces from its given parameters
   */
  MarbleSolitaireModel createModel();

  /**
   * To produce the MarbleSolitaireView object for this factory,
   * according to the factory's specified parameters.
   *
   * @return the MarbleSolitaireView that this factory produces from its given parameters
   */
  MarbleSolitaireView createTextView();

  /**
   * To produce the MarbleSolitaireView object for this factory,
   * according to the factory's specified parameters,
   * with the view's Appendable destination set to the given Appendable.
   *
   * @return the MarbleSolitaireView that this factory produces from its given parameters
   *         with the destination set to the provided Appendable object
   */
  MarbleSolitaireView createTextView(Appendable ap);

  MarbleSolitaireGuiView createGUIView();
}
