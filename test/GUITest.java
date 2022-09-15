import org.junit.Test;

import cs3500.marblesolitaire.controller.MarbleSolitaireGUIController;
import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireGuiView;
import cs3500.marblesolitaire.view.SwingGuiView;

import static org.junit.Assert.assertEquals;

public class GUITest {
  @Test
  public void testController() {
    MarbleSolitaireModel model = new EnglishSolitaireModel();
    MarbleSolitaireGuiView view = new SwingGuiView(model);
    MarbleSolitaireGUIController controller = new MarbleSolitaireGUIController(model, view);
    controller.buildMove(3, 1);
    controller.buildMove(3, 3);
    assertEquals(31, model.getScore());
  }
}
