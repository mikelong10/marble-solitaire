import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;
import cs3500.marblesolitaire.model.hw04.TriangleSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireView;
import cs3500.marblesolitaire.view.TriangleSolitaireTextView;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

/**
 * This class represents the test class for Triangle Solitaire. This class tests the functionality
 * of the solitaire game's model, view, and controller through the abstract class.
 */
public class TriangleSolitaireTest extends MarbleSolitaireAbstractTest {
  @Override
  protected String modelType() {
    return "triangular";
  }

  @Override
  protected void runGameOverSequence() {
    defaultModel.move(2, 2, 0, 0);
    assertFalse(defaultModel.isGameOver());
    defaultModel.move(4, 4, 2, 2);
    assertFalse(defaultModel.isGameOver());
    defaultModel.move(3, 1, 3, 3);
    assertFalse(defaultModel.isGameOver());
    defaultModel.move(2, 2, 4, 4);
    assertFalse(defaultModel.isGameOver());
    defaultModel.move(2, 0, 2, 2);
    assertFalse(defaultModel.isGameOver());
    defaultModel.move(0, 0, 2, 0);
    assertFalse(defaultModel.isGameOver());
    defaultModel.move(3, 0, 1, 0);
  }

  @Override
  protected void runFirstValidMove() {
    defaultModel.move(2, 2, 0, 0);
  }

  @Override
  protected int defaultStartingScore() {
    return 14;
  }

  @Override
  protected int size7StartingScore() {
    return 27;
  }

  @Override
  protected int size5hole42StartingScore() {
    return this.defaultStartingScore();
  }

  @Override
  protected String[] defaultStartGameOutput() {
    return new String[]{
      "Welcome to Marble Solitaire!",
      "Play Now:",
      "    _",
      "   O O",
      "  O O O",
      " O O O O",
      "O O O O O",
      "Score: 14"};
  }

  @Override
  protected String firstValidMoveDefaultInput() {
    return "3 3 1 1 q";
  }

  @Override
  protected String[] firstValidMoveDefaultOutput() {
    return new String[]{
      "    O",
      "   O _",
      "  O O _",
      " O O O O",
      "O O O O O",
      "Score: 13"};
  }

  @Override
  protected String invalidMovesDefaultInput() {
    return "4 1 1 1 " +
            "4 3 2 1 " +
            "3 3 1 1 " +
            "5 5 3 3 " +
            "2 2 4 4 " +
            "4 2 4 4 " +
            "4 5 4 3 " +
            "4 4 4 2 " +
            "5 4 3 4 " +
            "6 2 4 2 q";
  }

  @Override
  protected String[] invalidMovesDefaultOutput() {
    return new String[]{
      "Invalid move. Play again. " +
              "'From' and 'to' positions for the desired move are not two spaces away.",
      "Invalid move. Play again. Slot (2,1) is not empty.",
      "    O",
      "   O _",
      "  O O _",
      " O O O O",
      "O O O O O",
      "Score: 13",
      "    O",
      "   O _",
      "  O O O",
      " O O O _",
      "O O O O _",
      "Score: 12",
      "Invalid move. Play again. No marble at (2,2) to move.",
      "    O",
      "   O _",
      "  O O O",
      " O _ _ O",
      "O O O O _",
      "Score: 11",
      "Invalid move. Play again. Cannot move from invalid slot (4,5).",
      "Invalid move. Play again. " +
              "No marble at (4,3) between the desired 'from' and 'to' positions.",
      "Invalid move. Play again. Cannot move to invalid slot (3,4).",
      "Invalid move. Play again. Invalid cell position (6,2)."
    };
  }

  @Override
  protected String interruptedValidMoveInput() {
    return "3 r 3 -4.4 1 ! 1 0 2 q";
  }

  @Override
  protected String interruptedInvalidMoveInput() {
    return "3 v 33 -2 1 1.1 1 a 5 5 w 3 q";
  }

  @Override
  protected String invalidMoveCellPosOutput() {
    return "Invalid move. Play again. Invalid cell position (3,33).";
  }

  @Override
  protected String quitMidMoveInput() {
    return "3 3 1 1 4 2 Q 4 2 2 2";
  }

  @Override
  protected String[] immediateQuitOutput() {
    return new String[]{
      "Game quit!",
      "State of game when quit:",
      "    _",
      "   O O",
      "  O O O",
      " O O O O",
      "O O O O O",
      "Score: 14"
    };
  }

  @Override
  protected String[] quitAfterOneMoveOutput() {
    return new String[]{
      "Game quit!",
      "State of game when quit:",
      "    O",
      "   O _",
      "  O O _",
      " O O O O",
      "O O O O O",
      "Score: 13"
    };
  }

  @Override
  protected String gameOverInput() {
    return "3 3 1 1 5 5 3 3 4 2 4 4 3 3 5 5 3 1 3 3 1 1 3 1 4 1 2 1";
  }

  @Override
  protected String[] gameOverOutput() {
    return new String[]{
      "Welcome to Marble Solitaire!",
      "Play Now:",
      "    _",
      "   O O",
      "  O O O",
      " O O O O",
      "O O O O O",
      "Score: 14",
      "    O",
      "   O _",
      "  O O _",
      " O O O O",
      "O O O O O",
      "Score: 13",
      "    O",
      "   O _",
      "  O O O",
      " O O O _",
      "O O O O _",
      "Score: 12",
      "    O",
      "   O _",
      "  O O O",
      " O _ _ O",
      "O O O O _",
      "Score: 11",
      "    O",
      "   O _",
      "  O O _",
      " O _ _ _",
      "O O O O O",
      "Score: 10",
      "    O",
      "   O _",
      "  _ _ O",
      " O _ _ _",
      "O O O O O",
      "Score: 9",
      "    _",
      "   _ _",
      "  O _ O",
      " O _ _ _",
      "O O O O O",
      "Score: 8",
      "    _",
      "   O _",
      "  _ _ O",
      " _ _ _ _",
      "O O O O O",
      "Score: 7",
      "Game over!",
      "    _",
      "   O _",
      "  _ _ O",
      " _ _ _ _",
      "O O O O O",
      "Score: 7"
    };
  }

  @Override
  public String runOutOfInputsInput() {
    return "3 3 1 1 5 5";
  }

  @Override
  public String controllerTestInputs() {
    return "3 3 1 1 p 5 0 5 -2 3 3.3 3 $ 1 1 2 2 4 3 0 2 w 2 q";
  }

  @Override
  protected String[] confirmInputsOutput() {
    return new String[]{
      // model correctly receives input from controller for a valid move
      "Inputs received by model: fromRow = 2, fromCol = 2, toRow = 0, toCol = 0",
      // model correctly receives input from controller for a valid move
      // while being interrupted by invalid inputs
      "Inputs received by model: fromRow = 4, fromCol = 4, toRow = 2, toCol = 2",
      // model still correctly receives inputs from controller for an invalid move
      "Inputs received by model: fromRow = 0, fromCol = 0, toRow = 1, toCol = 1",
      // model still correctly receives inputs from controller for an invalid move
      // while being interrupted by invalid inputs
      "Inputs received by model: fromRow = 3, fromCol = 2, toRow = 1, toCol = 1"
    };
  }

  @Override
  public void testModelConstructors() {
    try {
      TriangleSolitaireModel model = new TriangleSolitaireModel(-5);
      fail("Does not throw IllegalArgumentException when size is less than 3");
    } catch (IllegalArgumentException e) {
      // correctly throws IllegalArgumentException
    }
    try {
      TriangleSolitaireModel model = new TriangleSolitaireModel(3, 5);
      fail("Does not throw IllegalArgumentException when position for empty slot is invalid");
    } catch (IllegalArgumentException e) {
      // correctly throws IllegalArgumentException
    }
    try {
      TriangleSolitaireModel model = new TriangleSolitaireModel(-4, 2, 2);
      fail("Does not throw IllegalArgumentException when board dimension is invalid");
    } catch (IllegalArgumentException e) {
      // correctly throws IllegalArgumentException
    }
    try {
      TriangleSolitaireModel model = new TriangleSolitaireModel(8, 8, 3);
      fail("Does not throw IllegalArgumentException when position for empty slot is invalid");
    } catch (IllegalArgumentException e) {
      // correctly throws IllegalArgumentException
    }
  }

  @Override
  public void testViewConstructors() {
    try {
      MarbleSolitaireView boardNull = new TriangleSolitaireTextView(null);
      fail("Provided state cannot be null");
    } catch (IllegalArgumentException e) {
      // correctly throws IllegalArgumentException
      assertEquals("Provided state cannot be null", e.getMessage());
    }
    try {
      MarbleSolitaireView boardModelNull = new TriangleSolitaireTextView(null, new StringBuilder());
      fail("Provided state cannot be null");
    } catch (IllegalArgumentException e) {
      // correctly throws IllegalArgumentException
      assertEquals("Provided state cannot be null", e.getMessage());
    }
    try {
      MarbleSolitaireView boardDestNull = new TriangleSolitaireTextView(defaultModel, null);
      fail("Appendable destination cannot be null");
    } catch (IllegalArgumentException e) {
      // correctly throws IllegalArgumentException
      assertEquals("Appendable destination cannot be null", e.getMessage());
    }
    try {
      MarbleSolitaireView nullView = new TriangleSolitaireTextView(null, null);
      fail("Parameters cannot be null");
    } catch (IllegalArgumentException e) {
      // correctly throws IllegalArgumentException
      assertEquals("Provided state cannot be null", e.getMessage());
    }
  }

  @Override
  public void testMove() {
    // valid northeast move
    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            defaultModel.getSlotAt(0, 0));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            defaultModel.getSlotAt(1, 0));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            defaultModel.getSlotAt(2, 0));
    defaultModel.move(2, 0, 0, 0);
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            defaultModel.getSlotAt(0, 0));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            defaultModel.getSlotAt(1, 0));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            defaultModel.getSlotAt(2, 0));
    // valid northwest move
    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            defaultModel.getSlotAt(2, 0));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            defaultModel.getSlotAt(3, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            defaultModel.getSlotAt(4, 2));
    defaultModel.move(4, 2, 2, 0);
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            defaultModel.getSlotAt(2, 0));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            defaultModel.getSlotAt(3, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            defaultModel.getSlotAt(4, 2));
    // valid southwest move
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            defaultModel.getSlotAt(1, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            defaultModel.getSlotAt(2, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            defaultModel.getSlotAt(3, 1));
    defaultModel.move(1, 1, 3, 1);
    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            defaultModel.getSlotAt(1, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            defaultModel.getSlotAt(2, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            defaultModel.getSlotAt(3, 1));
    // valid southeast move
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            defaultModel.getSlotAt(2, 0));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            defaultModel.getSlotAt(3, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            defaultModel.getSlotAt(4, 2));
    defaultModel.move(2, 0, 4, 2);
    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            defaultModel.getSlotAt(2, 0));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            defaultModel.getSlotAt(3, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            defaultModel.getSlotAt(4, 2));
    // valid west move
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            defaultModel.getSlotAt(3, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            defaultModel.getSlotAt(3, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            defaultModel.getSlotAt(3, 1));
    defaultModel.move(3, 3, 3, 1);
    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            defaultModel.getSlotAt(3, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            defaultModel.getSlotAt(3, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            defaultModel.getSlotAt(3, 1));
    // valid east move
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            defaultModel.getSlotAt(3, 0));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            defaultModel.getSlotAt(3, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            defaultModel.getSlotAt(3, 2));
    defaultModel.move(3, 0, 3, 2);
    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            defaultModel.getSlotAt(3, 0));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            defaultModel.getSlotAt(3, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            defaultModel.getSlotAt(3, 2));
    try {
      defaultModel.move(2, 3, 2, 1);
      fail("Does not throw IllegalArgumentException when 'from' position is invalid");
    } catch (IllegalArgumentException e) {
      // correctly throws the IllegalArgumentException
      assertEquals("Cannot move from invalid slot (3,4).", e.getMessage());
    }
    try {
      defaultModel.move(3, 2, 1, 2);
      fail("Does not throw IllegalArgumentException when 'to' position is invalid");
    } catch (IllegalArgumentException e) {
      // correctly throws the IllegalArgumentException
      assertEquals("Cannot move to invalid slot (2,3).", e.getMessage());
    }
    try {
      defaultModel.move(1, 1, 3, 3);
      fail("Does not throw IllegalArgumentException when no marble at 'from' position");
    } catch (IllegalArgumentException e) {
      // correctly throws the IllegalArgumentException
      assertEquals("No marble at (2,2) to move.", e.getMessage());
    }
    try {
      defaultModel.move(2, 2, 4, 2);
      fail("Does not throw IllegalArgumentException when 'to' position is not empty");
    } catch (IllegalArgumentException e) {
      // correctly throws the IllegalArgumentException
      assertEquals("Slot (5,3) is not empty.", e.getMessage());
    }
    try {
      defaultModel.move(2, 2, 2, 0);
      fail("Does not throw IllegalArgumentException for having no marble between the 'from' and " +
              "'to' positions");
    } catch (IllegalArgumentException e) {
      // correctly throws the IllegalArgumentException
      assertEquals("No marble at (3,2) between the desired 'from' and 'to' positions.",
              e.getMessage());
    }
    defaultModel.move(4, 3, 2, 1); // setup for
    defaultModel.move(2, 2, 2, 0); // next test
    try {
      defaultModel.move(4, 0, 2, 2);
      fail("Does not throw IllegalArgumentException when 'from' and 'to' positions are not two " +
              "spaces away");
    } catch (IllegalArgumentException e) {
      // correctly throws the IllegalArgumentException
      assertEquals("'From' and 'to' positions for the desired move are not two spaces away.",
              e.getMessage());
    }
  }

  @Override
  public void testGetBoardSize() {
    assertEquals(5, defaultModel.getBoardSize());
    assertEquals(7, size7Model.getBoardSize());
    assertEquals(5, hole22Model.getBoardSize());
    assertEquals(5, size5hole42Model.getBoardSize());
  }

  @Override
  public void testGetSlotAt() {
    for (int r = 0; r < defaultModel.getBoardSize(); r += 1) {
      for (int c = 0; c < defaultModel.getBoardSize(); c += 1) {
        if (r == 0 && c == 0) {
          assertEquals(MarbleSolitaireModelState.SlotState.Empty, defaultModel.getSlotAt(r, c));
        } else if (r >= c) {
          assertEquals(MarbleSolitaireModelState.SlotState.Marble, defaultModel.getSlotAt(r, c));
        } else {
          assertEquals(MarbleSolitaireModelState.SlotState.Invalid, defaultModel.getSlotAt(r, c));
        }
      }
    }
    try {
      defaultModel.getSlotAt(defaultModel.getBoardSize(), defaultModel.getBoardSize());
      fail("Did not throw IllegalArgumentException for invalid cell position");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid cell position (6,6).", e.getMessage());
    }
  }
}
