package cs3500.marblesolitaire;

import java.io.InputStreamReader;
import java.util.Map;
import java.util.TreeMap;

import cs3500.marblesolitaire.controller.MarbleSolitaireController;
import cs3500.marblesolitaire.controller.MarbleSolitaireControllerImpl;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw04.MarbleSolitaireAbstractFactory;
import cs3500.marblesolitaire.view.MarbleSolitaireView;

/**
 * This class represents the main class for the Marble Solitaire program. It is the entry point for
 * the program, holding the main method where the program can be run.
 */
public final class MarbleSolitaire {
  /**
   * The main method for the Marble Solitaire program.
   *
   * @param args command-line arguments:
   *             one of english, european, or triangular to choose the type of board,
   *             and optionally -size N where N is a number size for the board,
   *             and -hole R C, where R and C are numbers to specify the row and column of the
   *             initial hole
   */
  public static void main(String[] args) {
    Map<String, String> params = new TreeMap<>();
    for (int i = 0; i < args.length; i += 1) {
      if (args[i].equalsIgnoreCase("english") ||
              args[i].equalsIgnoreCase("european") ||
              args[i].equalsIgnoreCase("triangular")) {
        params.put("type", args[i]);
      } else if (args[i].equalsIgnoreCase("-size")) {
        params.put("size", args[i + 1]);
      } else if (args[i].equalsIgnoreCase("-hole")) {
        params.put("holeRow", args[i + 1]);
        params.put("holeCol", args[i + 2]);
      }
    }
    String type = params.get("type");
    MarbleSolitaireAbstractFactory factory = MarbleSolitaireAbstractFactory.getFactory(type);
    if (params.size() == 2) {
      int size = Integer.parseInt(params.get("size"));
      factory = MarbleSolitaireAbstractFactory.getFactory(type, size);
    } else if (params.size() == 3) {
      int holeRow = Integer.parseInt(params.get("holeRow")) - 1;
      int holeCol = Integer.parseInt(params.get("holeCol")) - 1;
      factory = MarbleSolitaireAbstractFactory.getFactory(type, holeRow, holeCol);
    } else if (params.size() == 4) {
      int size = Integer.parseInt(params.get("size"));
      int holeRow = Integer.parseInt(params.get("holeRow")) - 1;
      int holeCol = Integer.parseInt(params.get("holeCol")) - 1;
      factory = MarbleSolitaireAbstractFactory.getFactory(type, size, holeRow, holeCol);
    }
    MarbleSolitaireModel model = factory.createModel();
    MarbleSolitaireView view = factory.createTextView();
    Readable input = new InputStreamReader(System.in);
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(model, view, input);
    controller.playGame();
  }
}