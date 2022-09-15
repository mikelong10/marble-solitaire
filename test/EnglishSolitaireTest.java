import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

/**
 * This class represents the test class for English Solitaire. This class tests the functionality
 * of the solitaire game's model, view, and controller through the abstract class.
 */
public class EnglishSolitaireTest extends MarbleSolitaireAbstractTest {
  @Override
  protected String modelType() {
    return "english";
  }

  @Override
  protected void runGameOverSequence() {
    defaultModel.move(3, 1, 3, 3);
    assertFalse(defaultModel.isGameOver());
    defaultModel.move(3, 4, 3, 2);
    assertFalse(defaultModel.isGameOver());
    defaultModel.move(3, 6, 3, 4);
    assertFalse(defaultModel.isGameOver());
    defaultModel.move(5, 3, 3, 3);
    assertFalse(defaultModel.isGameOver());
    defaultModel.move(2, 3, 4, 3);
    assertFalse(defaultModel.isGameOver());
    defaultModel.move(0, 3, 2, 3);
  }

  @Override
  protected int defaultStartingScore() {
    return 32;
  }

  @Override
  protected int size7StartingScore() {
    return 216;
  }

  @Override
  protected int size5hole42StartingScore() {
    return 104;
  }

  @Override
  protected String[] defaultStartGameOutput() {
    return new String[]{
      "Welcome to Marble Solitaire!",
      "Play Now:",
      "    O O O",
      "    O O O",
      "O O O O O O O",
      "O O O _ O O O",
      "O O O O O O O",
      "    O O O",
      "    O O O",
      "Score: 32"};
  }

  @Override
  protected String[] firstValidMoveDefaultOutput() {
    return new String[]{
      "    O O O",
      "    O O O",
      "O O O O O O O",
      "O O O O O O O",
      "O O O _ O O O",
      "    O _ O",
      "    O O O",
      "Score: 31"};
  }

  @Override
  protected String invalidMovesDefaultInput() {
    return "4 3 4 4 " +
            "4 1 4 4 " +
            "4 2 4 4 " +
            "4 4 4 2 " +
            "4 5 4 3 " +
            "4 2 4 4 " +
            "5 1 5 3 " +
            "2 2 4 2 " +
            "6 3 4 5 " +
            "6 2 4 2 " +
            "6 4 6 6 " +
            "4 8 4 6 q";
  }

  @Override
  protected String[] invalidMovesDefaultOutput() {
    return new String[]{
      "Invalid move. Play again. 'From' and 'to' positions for the desired move are " +
              "not two spaces away.",
      "Invalid move. Play again. 'From' and 'to' positions " +
              "for the desired move are not two spaces away.",
      "    O O O",
      "    O O O",
      "O O O O O O O",
      "O _ _ O O O O",
      "O O O O O O O",
      "    O O O",
      "    O O O",
      "Score: 31",
      "Invalid move. Play again. No marble at (4,3) between the desired 'from' and " +
              "'to' positions.",
      "    O O O",
      "    O O O",
      "O O O O O O O",
      "O _ O _ _ O O",
      "O O O O O O O",
      "    O O O",
      "    O O O",
      "Score: 30",
      "Invalid move. Play again. No marble at (4,2) to move.",
      "Invalid move. Play again. Slot (5,3) is not empty.",
      "Invalid move. Play again. Cannot move from invalid slot (2,2).",
      "Invalid move. Play again. 'From' and 'to' positions for the desired move are " +
              "not two spaces away.",
      "Invalid move. Play again. Cannot move from invalid slot (6,2).",
      "Invalid move. Play again. Cannot move to invalid slot (6,6).",
      "Invalid move. Play again. Invalid cell position (4,8)."};
  }

  @Override
  protected String[] immediateQuitOutput() {
    return new String[]{
      "Game quit!",
      "State of game when quit:",
      "    O O O",
      "    O O O",
      "O O O O O O O",
      "O O O _ O O O",
      "O O O O O O O",
      "    O O O",
      "    O O O",
      "Score: 32"
    };
  }

  @Override
  protected String[] quitAfterOneMoveOutput() {
    return new String[]{
      "Game quit!",
      "State of game when quit:",
      "    O O O",
      "    O O O",
      "O O O O O O O",
      "O O O O O O O",
      "O O O _ O O O",
      "    O _ O",
      "    O O O",
      "Score: 31"
    };
  }

  @Override
  protected String gameOverInput() {
    return "4 2 4 4 4 5 4 3 4 7 4 5 6 4 4 4 3 4 5 4 1 4 3 4";
  }

  @Override
  protected String[] gameOverOutput() {
    return new String[]{
      "Welcome to Marble Solitaire!",
      "Play Now:",
      "    O O O",
      "    O O O",
      "O O O O O O O",
      "O O O _ O O O",
      "O O O O O O O",
      "    O O O",
      "    O O O",
      "Score: 32",
      "    O O O",
      "    O O O",
      "O O O O O O O",
      "O _ _ O O O O",
      "O O O O O O O",
      "    O O O",
      "    O O O",
      "Score: 31",
      "    O O O",
      "    O O O",
      "O O O O O O O",
      "O _ O _ _ O O",
      "O O O O O O O",
      "    O O O",
      "    O O O",
      "Score: 30",
      "    O O O",
      "    O O O",
      "O O O O O O O",
      "O _ O _ O _ _",
      "O O O O O O O",
      "    O O O",
      "    O O O",
      "Score: 29",
      "    O O O",
      "    O O O",
      "O O O O O O O",
      "O _ O O O _ _",
      "O O O _ O O O",
      "    O _ O",
      "    O O O",
      "Score: 28",
      "    O O O",
      "    O O O",
      "O O O _ O O O",
      "O _ O _ O _ _",
      "O O O O O O O",
      "    O _ O",
      "    O O O",
      "Score: 27",
      "    O _ O",
      "    O _ O",
      "O O O O O O O",
      "O _ O _ O _ _",
      "O O O O O O O",
      "    O _ O",
      "    O O O",
      "Score: 26",
      "Game over!",
      "    O _ O",
      "    O _ O",
      "O O O O O O O",
      "O _ O _ O _ _",
      "O O O O O O O",
      "    O _ O",
      "    O O O",
      "Score: 26"
    };
  }

  @Override
  public void testModelConstructors() {
    try {
      EnglishSolitaireModel boardDefault4x4 = new EnglishSolitaireModel(4);
      fail("Does not throw IllegalArgumentException when arm thickness is not a positive odd " +
              "number");
    } catch (IllegalArgumentException e) {
      // correctly throws IllegalArgumentException
    }
    try {
      EnglishSolitaireModel boardNegArm = new EnglishSolitaireModel(-2);
      fail("Does not throw IllegalArgumentException when arm thickness is not a positive odd " +
              "number");
    } catch (IllegalArgumentException e) {
      // correctly throws IllegalArgumentException
    }
    try {
      EnglishSolitaireModel boardEmpty11 = new EnglishSolitaireModel(1, 1);
      fail("Does not throw IllegalArgumentException for invalid position for empty slot");
    } catch (IllegalArgumentException e) {
      // correctly throws IllegalArgumentException
    }
    try {
      EnglishSolitaireModel boardOOBEmpty = new EnglishSolitaireModel(-1, -1);
      fail("Position for empty slot out of bounds");
    } catch (IllegalArgumentException e) {
      // correctly throws IllegalArgumentException
    }
    try {
      EnglishSolitaireModel boardInvalidEmpty = new EnglishSolitaireModel(3, 5, 1);
      fail("Invalid position for empty slot");
    } catch (IllegalArgumentException e) {
      // correctly throws IllegalArgumentException
    }
    try {
      EnglishSolitaireModel boardInvalidArm = new EnglishSolitaireModel(6, 5, 4);
      fail("Arm thickness is not a positive odd number");
    } catch (IllegalArgumentException e) {
      // correctly throws IllegalArgumentException
    }
  }

  @Override
  public void testMove() {
    // valid vertical move
    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            defaultModel.getSlotAt(3, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            defaultModel.getSlotAt(4, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            defaultModel.getSlotAt(5, 3));
    defaultModel.move(5, 3, 3, 3);
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            defaultModel.getSlotAt(3, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            defaultModel.getSlotAt(4, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            defaultModel.getSlotAt(5, 3));
    // valid horizontal move
    this.init();
    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            defaultModel.getSlotAt(3, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            defaultModel.getSlotAt(3, 4));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            defaultModel.getSlotAt(3, 5));
    defaultModel.move(3, 5, 3, 3);
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            defaultModel.getSlotAt(3, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            defaultModel.getSlotAt(3, 4));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            defaultModel.getSlotAt(3, 5));
    // check invalid moves
    this.init();
    try {
      defaultModel.move(5, 5, 3, 5);
      fail("Does not throw IllegalArgumentException when 'from' position is invalid");
    } catch (IllegalArgumentException e) {
      // correctly throws the IllegalArgumentException
    }
    try {
      defaultModel.move(5, 3, 5, 1);
      fail("Does not throw IllegalArgumentException when 'to' position is invalid");
    } catch (IllegalArgumentException e) {
      // correctly throws the IllegalArgumentException
    }
    try {
      defaultModel.move(3, 3, 5, 3);
      fail("Does not throw IllegalArgumentException when no marble at 'from' position");
    } catch (IllegalArgumentException e) {
      // correctly throws the IllegalArgumentException
    }
    try {
      defaultModel.move(2, 1, 2, 3);
      fail("Does not throw IllegalArgumentException when 'to' position is not empty");
    } catch (IllegalArgumentException e) {
      // correctly throws the IllegalArgumentException
    }
    try {
      defaultModel.move(3, 2, 3, 3);
      fail("Does not throw IllegalArgumentException when 'from' and 'to' positions are not two " +
              "spaces away");
    } catch (IllegalArgumentException e) {
      // correctly throws the IllegalArgumentException
    }
    try {
      defaultModel.move(3, 2, 3, 5);
      fail("Does not throw IllegalArgumentException when 'from' and 'to' positions are not two " +
              "spaces away");
    } catch (IllegalArgumentException e) {
      // correctly throws the IllegalArgumentException
    }
    try {
      defaultModel.move(4, 4, 3, 3);
      fail("Does not throw IllegalArgumentException when 'from' and 'to' positions are not two " +
              "spaces away");
    } catch (IllegalArgumentException e) {
      // correctly throws the IllegalArgumentException
    }
    try {
      defaultModel.move(2, 4, 4, 2);
      fail("Does not throw IllegalArgumentException when 'from' and 'to' positions are not two " +
              "spaces away");
    } catch (IllegalArgumentException e) {
      // correctly throws the IllegalArgumentException
    }
    defaultModel.move(3, 5, 3, 3); // setup for next test
    try {
      defaultModel.move(3, 6, 3, 4);
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
        } else if ((r < arm - 1 || r > defaultModel.getBoardSize() - arm) &&
                (c < arm - 1 || c > defaultModel.getBoardSize() - arm)) {
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
