package cs3500.marblesolitaire;

import java.util.HashMap;
import java.util.Map;

import cs3500.marblesolitaire.controller.MarbleSolitaireGUIController;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw04.MarbleSolitaireAbstractFactory;
import cs3500.marblesolitaire.view.MarbleSolitaireGuiView;

public final class GUIMarbleSolitaire {
  public static void main(String[] args) {
    Map<String, String> params = new HashMap<>();
    for (int i = 0; i < args.length; i += 1) {
      if (args[i].equalsIgnoreCase("english") ||
              args[i].equalsIgnoreCase("european")) {
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
    MarbleSolitaireGuiView view = factory.createGUIView();
    MarbleSolitaireGUIController controller = new MarbleSolitaireGUIController(model, view);
  }
}
