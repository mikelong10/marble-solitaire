import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;
import cs3500.marblesolitaire.model.hw04.EuropeanSolitaireModel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * This class represents the test class for European Solitaire. This class tests the functionality
 * of the solitaire game's model, view, and controller through the abstract class.
 */
public class EuropeanSolitaireTest extends MarbleSolitaireAbstractTest {
  @Override
  protected String modelType() {
    return "european";
  }

  @Override
  protected void runGameOverSequence() {
    defaultModel.move(5, 3, 3, 3);
    defaultModel.move(2, 3, 4, 3);
    defaultModel.move(0, 3, 2, 3);
    defaultModel.move(3, 1, 3, 3);
    defaultModel.move(3, 4, 3, 2);
    defaultModel.move(3, 6, 3, 4);
    defaultModel.move(1, 1, 3, 1);
    defaultModel.move(4, 1, 2, 1);
    defaultModel.move(1, 5, 3, 5);
    defaultModel.move(4, 5, 2, 5);
    defaultModel.move(4, 3, 4, 5);
    defaultModel.move(4, 6, 4, 4);
    defaultModel.move(5, 1, 5, 3);
    defaultModel.move(5, 4, 5, 2);
    defaultModel.move(3, 4, 5, 4);
    defaultModel.move(5, 5, 5, 3);
    defaultModel.move(5, 3, 5, 1);
    defaultModel.move(3, 2, 5, 2);
    defaultModel.move(5, 1, 5, 3);
    defaultModel.move(6, 3, 4, 3);
    defaultModel.move(1, 2, 3, 2);
    defaultModel.move(1, 4, 3, 4);
    defaultModel.move(2, 6, 2, 4);
    defaultModel.move(2, 4, 2, 2);
    defaultModel.move(2, 1, 2, 3);
  }

  @Override
  protected int defaultStartingScore() {
    return 36;
  }

  @Override
  protected int size7StartingScore() {
    return 276;
  }

  @Override
  protected int size5hole42StartingScore() {
    return 128;
  }

  @Override
  protected String[] defaultStartGameOutput() {
    return new String[]{
      "Welcome to Marble Solitaire!",
      "Play Now:",
      "    O O O",
      "  O O O O O",
      "O O O O O O O",
      "O O O _ O O O",
      "O O O O O O O",
      "  O O O O O",
      "    O O O",
      "Score: 36"
    };
  }

  @Override
  protected String[] firstValidMoveDefaultOutput() {
    return new String[]{
      "    O O O",
      "  O O O O O",
      "O O O O O O O",
      "O O O O O O O",
      "O O O _ O O O",
      "  O O _ O O",
      "    O O O",
      "Score: 35"
    };
  }

  @Override
  protected String invalidMovesDefaultInput() {
    return "4 1 4 4 " +
            "4 2 4 4 " +
            "4 4 4 2 " +
            "4 5 4 3 " +
            "4 2 4 4 " +
            "5 1 5 3 " +
            "2 2 4 2 " +
            "2 4 2 2 " +
            "1 2 3 2 " +
            "2 5 2 7 " +
            "4 8 4 6 q";
  }

  @Override
  protected String[] invalidMovesDefaultOutput() {
    return new String[] {
      "Invalid move. Play again. " +
              "'From' and 'to' positions for the desired move are not two spaces away.",
      "    O O O",
      "  O O O O O",
      "O O O O O O O",
      "O _ _ O O O O",
      "O O O O O O O",
      "  O O O O O",
      "    O O O",
      "Score: 35",
      "Invalid move. Play again. " +
              "No marble at (4,3) between the desired 'from' and 'to' positions.",
      "    O O O",
      "  O O O O O",
      "O O O O O O O",
      "O _ O _ _ O O",
      "O O O O O O O",
      "  O O O O O",
      "    O O O",
      "Score: 34",
      "Invalid move. Play again. No marble at (4,2) to move.",
      "Invalid move. Play again. Slot (5,3) is not empty.",
      "    O O O",
      "  _ O O O O",
      "O _ O O O O O",
      "O O O _ _ O O",
      "O O O O O O O",
      "  O O O O O",
      "    O O O",
      "Score: 33",
      "    O O O",
      "  O _ _ O O",
      "O _ O O O O O",
      "O O O _ _ O O",
      "O O O O O O O",
      "  O O O O O",
      "    O O O",
      "Score: 32",
      "Invalid move. Play again. Cannot move from invalid slot (1,2).",
      "Invalid move. Play again. Cannot move to invalid slot (2,7).",
      "Invalid move. Play again. Invalid cell position (4,8)."
    };
  }

  @Override
  protected String[] immediateQuitOutput() {
    return new String[]{
      "Game quit!",
      "State of game when quit:",
      "    O O O",
      "  O O O O O",
      "O O O O O O O",
      "O O O _ O O O",
      "O O O O O O O",
      "  O O O O O",
      "    O O O",
      "Score: 36"
    };
  }

  @Override
  protected String[] quitAfterOneMoveOutput() {
    return new String[]{
      "Game quit!",
      "State of game when quit:",
      "    O O O",
      "  O O O O O",
      "O O O O O O O",
      "O O O O O O O",
      "O O O _ O O O",
      "  O O _ O O",
      "    O O O",
      "Score: 35"
    };
  }

  @Override
  protected String gameOverInput() {
    return "6 4 4 4 3 4 5 4 1 4 3 4 4 2 4 4 4 5 4 3 4 7 4 5 2 2 4 2 5 2 3 2 2 6 4 6 5 6 3 6 5 4 5" +
            " 6 5 7 5 5 6 2 6 4 6 5 6 3 4 5 6 5 6 6 6 4 6 4 6 2 4 3 6 3 6 2 6 4 7 4 5 4 2 3 4 3 2" +
            " 5 4 5 3 7 3 5 3 5 3 3 3 2 3 4";
  }

  @Override
  protected String[] gameOverOutput() {
    return new String[]{
      "Welcome to Marble Solitaire!",
      "Play Now:",
      "    O O O",
      "  O O O O O",
      "O O O O O O O",
      "O O O _ O O O",
      "O O O O O O O",
      "  O O O O O",
      "    O O O",
      "Score: 36",
      "    O O O",
      "  O O O O O",
      "O O O O O O O",
      "O O O O O O O",
      "O O O _ O O O",
      "  O O _ O O",
      "    O O O",
      "Score: 35",
      "    O O O",
      "  O O O O O",
      "O O O _ O O O",
      "O O O _ O O O",
      "O O O O O O O",
      "  O O _ O O",
      "    O O O",
      "Score: 34",
      "    O _ O",
      "  O O _ O O",
      "O O O O O O O",
      "O O O _ O O O",
      "O O O O O O O",
      "  O O _ O O",
      "    O O O",
      "Score: 33",
      "    O _ O",
      "  O O _ O O",
      "O O O O O O O",
      "O _ _ O O O O",
      "O O O O O O O",
      "  O O _ O O",
      "    O O O",
      "Score: 32",
      "    O _ O",
      "  O O _ O O",
      "O O O O O O O",
      "O _ O _ _ O O",
      "O O O O O O O",
      "  O O _ O O",
      "    O O O",
      "Score: 31",
      "    O _ O",
      "  O O _ O O",
      "O O O O O O O",
      "O _ O _ O _ _",
      "O O O O O O O",
      "  O O _ O O",
      "    O O O",
      "Score: 30",
      "    O _ O",
      "  _ O _ O O",
      "O _ O O O O O",
      "O O O _ O _ _",
      "O O O O O O O",
      "  O O _ O O",
      "    O O O",
      "Score: 29",
      "    O _ O",
      "  _ O _ O O",
      "O O O O O O O",
      "O _ O _ O _ _",
      "O _ O O O O O",
      "  O O _ O O",
      "    O O O",
      "Score: 28",
      "    O _ O",
      "  _ O _ O _",
      "O O O O O _ O",
      "O _ O _ O O _",
      "O _ O O O O O",
      "  O O _ O O",
      "    O O O",
      "Score: 27",
      "    O _ O",
      "  _ O _ O _",
      "O O O O O O O",
      "O _ O _ O _ _",
      "O _ O O O _ O",
      "  O O _ O O",
      "    O O O",
      "Score: 26",
      "    O _ O",
      "  _ O _ O _",
      "O O O O O O O",
      "O _ O _ O _ _",
      "O _ O _ _ O O",
      "  O O _ O O",
      "    O O O",
      "Score: 25",
      "    O _ O",
      "  _ O _ O _",
      "O O O O O O O",
      "O _ O _ O _ _",
      "O _ O _ O _ _",
      "  O O _ O O",
      "    O O O",
      "Score: 24",
      "    O _ O",
      "  _ O _ O _",
      "O O O O O O O",
      "O _ O _ O _ _",
      "O _ O _ O _ _",
      "  _ _ O O O",
      "    O O O",
      "Score: 23",
      "    O _ O",
      "  _ O _ O _",
      "O O O O O O O",
      "O _ O _ O _ _",
      "O _ O _ O _ _",
      "  _ O _ _ O",
      "    O O O",
      "Score: 22",
      "    O _ O",
      "  _ O _ O _",
      "O O O O O O O",
      "O _ O _ _ _ _",
      "O _ O _ _ _ _",
      "  _ O _ O O",
      "    O O O",
      "Score: 21",
      "    O _ O",
      "  _ O _ O _",
      "O O O O O O O",
      "O _ O _ _ _ _",
      "O _ O _ _ _ _",
      "  _ O O _ _",
      "    O O O",
      "Score: 20",
      "    O _ O",
      "  _ O _ O _",
      "O O O O O O O",
      "O _ O _ _ _ _",
      "O _ O _ _ _ _",
      "  O _ _ _ _",
      "    O O O",
      "Score: 19",
      "    O _ O",
      "  _ O _ O _",
      "O O O O O O O",
      "O _ _ _ _ _ _",
      "O _ _ _ _ _ _",
      "  O O _ _ _",
      "    O O O",
      "Score: 18",
      "    O _ O",
      "  _ O _ O _",
      "O O O O O O O",
      "O _ _ _ _ _ _",
      "O _ _ _ _ _ _",
      "  _ _ O _ _",
      "    O O O",
      "Score: 17",
      "    O _ O",
      "  _ O _ O _",
      "O O O O O O O",
      "O _ _ _ _ _ _",
      "O _ _ O _ _ _",
      "  _ _ _ _ _",
      "    O _ O",
      "Score: 16",
      "    O _ O",
      "  _ _ _ O _",
      "O O _ O O O O",
      "O _ O _ _ _ _",
      "O _ _ O _ _ _",
      "  _ _ _ _ _",
      "    O _ O",
      "Score: 15",
      "    O _ O",
      "  _ _ _ _ _",
      "O O _ O _ O O",
      "O _ O _ O _ _",
      "O _ _ O _ _ _",
      "  _ _ _ _ _",
      "    O _ O",
      "Score: 14",
      "    O _ O",
      "  _ _ _ _ _",
      "O O _ O O _ _",
      "O _ O _ O _ _",
      "O _ _ O _ _ _",
      "  _ _ _ _ _",
      "    O _ O",
      "Score: 13",
      "    O _ O",
      "  _ _ _ _ _",
      "O O O _ _ _ _",
      "O _ O _ O _ _",
      "O _ _ O _ _ _",
      "  _ _ _ _ _",
      "    O _ O",
      "Score: 12",
      "    O _ O",
      "  _ _ _ _ _",
      "O _ _ O _ _ _",
      "O _ O _ O _ _",
      "O _ _ O _ _ _",
      "  _ _ _ _ _",
      "    O _ O",
      "Score: 11",
      "Game over!",
      "    O _ O",
      "  _ _ _ _ _",
      "O _ _ O _ _ _",
      "O _ O _ O _ _",
      "O _ _ O _ _ _",
      "  _ _ _ _ _",
      "    O _ O",
      "Score: 11"
    };
  }

  @Override
  public void testModelConstructors() {
    try {
      EuropeanSolitaireModel boardDefault4x4 = new EuropeanSolitaireModel(4);
      fail("Does not throw IllegalArgumentException when arm thickness is not a positive odd " +
              "number");
    } catch (IllegalArgumentException e) {
      // correctly throws IllegalArgumentException
    }
    try {
      EuropeanSolitaireModel boardNegArm = new EuropeanSolitaireModel(-2);
      fail("Does not throw IllegalArgumentException when arm thickness is not a positive odd " +
              "number");
    } catch (IllegalArgumentException e) {
      // correctly throws IllegalArgumentException
    }
    try {
      EuropeanSolitaireModel boardEmpty11 = new EuropeanSolitaireModel(0, 1);
      fail("Does not throw IllegalArgumentException for invalid position for empty slot");
    } catch (IllegalArgumentException e) {
      // correctly throws IllegalArgumentException
    }
    try {
      EuropeanSolitaireModel boardOOBEmpty = new EuropeanSolitaireModel(-1, -1);
      fail("Position for empty slot out of bounds");
    } catch (IllegalArgumentException e) {
      // correctly throws IllegalArgumentException
    }
    try {
      EuropeanSolitaireModel boardInvalidEmpty = new EuropeanSolitaireModel(3, 5, 6);
      fail("Invalid position for empty slot");
    } catch (IllegalArgumentException e) {
      // correctly throws IllegalArgumentException
    }
    try {
      EuropeanSolitaireModel boardInvalidArm = new EuropeanSolitaireModel(6, 5, 5);
      fail("Arm thickness is not a positive odd number");
    } catch (IllegalArgumentException e) {
      // correctly throws IllegalArgumentException
    }
  }

  @Override
  public void testMove() {
    EuropeanSolitaireModel modelHole11 = new EuropeanSolitaireModel(1, 1);
    // valid vertical move
    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            modelHole11.getSlotAt(1, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            modelHole11.getSlotAt(2, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            modelHole11.getSlotAt(3, 1));
    modelHole11.move(3, 1, 1, 1);
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            modelHole11.getSlotAt(1, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            modelHole11.getSlotAt(2, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            modelHole11.getSlotAt(3, 1));
    // valid horizontal move
    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            modelHole11.getSlotAt(3, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            modelHole11.getSlotAt(3, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            modelHole11.getSlotAt(3, 3));
    modelHole11.move(3, 3, 3, 1);
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            modelHole11.getSlotAt(3, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            modelHole11.getSlotAt(3, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            modelHole11.getSlotAt(3, 3));
    // check invalid moves
    modelHole11.move(3, 5, 3, 3);
    try {
      modelHole11.move(0, 1, 2, 1);
      fail("Does not throw IllegalArgumentException when 'from' position is invalid");
    } catch (IllegalArgumentException e) {
      // correctly throws the IllegalArgumentException
    }
    try {
      modelHole11.move(1, 2, 1, 0);
      fail("Does not throw IllegalArgumentException when 'to' position is invalid");
    } catch (IllegalArgumentException e) {
      // correctly throws the IllegalArgumentException
    }
    try {
      modelHole11.move(3, 4, 3, 2);
      fail("Does not throw IllegalArgumentException when no marble at 'from' position");
    } catch (IllegalArgumentException e) {
      // correctly throws the IllegalArgumentException
    }
    try {
      modelHole11.move(0, 2, 0, 4);
      fail("Does not throw IllegalArgumentException when 'to' position is not empty");
    } catch (IllegalArgumentException e) {
      // correctly throws the IllegalArgumentException
    }
    try {
      modelHole11.move(3, 3, 3, 2);
      fail("Does not throw IllegalArgumentException when 'from' and 'to' positions are not two " +
              "spaces away");
    } catch (IllegalArgumentException e) {
      // correctly throws the IllegalArgumentException
    }
    try {
      modelHole11.move(0, 2, 3, 2);
      fail("Does not throw IllegalArgumentException when 'from' and 'to' positions are not two " +
              "spaces away");
    } catch (IllegalArgumentException e) {
      // correctly throws the IllegalArgumentException
    }
    try {
      modelHole11.move(1, 2, 3, 4);
      fail("Does not throw IllegalArgumentException when 'from' and 'to' positions are not two " +
              "spaces away");
    } catch (IllegalArgumentException e) {
      // correctly throws the IllegalArgumentException
    }
    try {
      modelHole11.move(5, 2, 3, 4);
      fail("Does not throw IllegalArgumentException for having no marble between the 'from' and " +
              "'to' positions");
    } catch (IllegalArgumentException e) {
      // correctly throws the IllegalArgumentException
    }
  }

  @Override
  public void testGetSlotAt() {
    int arm = (defaultModel.getBoardSize() + 2) / 3;
    for (int r = 0; r < defaultModel.getBoardSize(); r += 1) {
      for (int c = 0; c < defaultModel.getBoardSize(); c += 1) {
        if (r == 3 && c == 3) {
          assertEquals(MarbleSolitaireModelState.SlotState.Empty, defaultModel.getSlotAt(r, c));
        } else if ((r + c < arm - 1) || Math.abs(c - r) > 2 * arm - 2 || r + c > 5 * arm - 5) {
          assertEquals(MarbleSolitaireModelState.SlotState.Invalid, defaultModel.getSlotAt(r, c));
        } else {
          assertEquals(MarbleSolitaireModelState.SlotState.Marble, defaultModel.getSlotAt(r, c));
        }
      }
    }
    try {
      defaultModel.getSlotAt(defaultModel.getBoardSize(), defaultModel.getBoardSize());
      fail("Did not throw IllegalArgumentException for invalid cell position");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid cell position (8,8).", e.getMessage());
    }
  }
}
