import org.junit.Before;
import org.junit.Test;

import java.io.StringReader;

import cs3500.marblesolitaire.controller.MarbleSolitaireController;
import cs3500.marblesolitaire.controller.MarbleSolitaireControllerImpl;
import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;
import cs3500.marblesolitaire.view.MarbleSolitaireTextView;
import cs3500.marblesolitaire.view.MarbleSolitaireView;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * To test the EnglishSolitaire game.
 */
public class EnglishSolitaireModelTest {
  private EnglishSolitaireModel boardDefault3x3;
  private EnglishSolitaireModel board3x3Empty31;
  private EnglishSolitaireModel boardDefault5x5;
  private EnglishSolitaireModel board5x5Empty58;
  private MarbleSolitaireView boardDefault3x3View;
  private MarbleSolitaireController boardDefault3x3Controller;
  private Readable input;
  private Appendable output;

  @Before
  public void init() {
    boardDefault3x3 = new EnglishSolitaireModel();
    board3x3Empty31 = new EnglishSolitaireModel(3, 1);
    boardDefault5x5 = new EnglishSolitaireModel(5);
    board5x5Empty58 = new EnglishSolitaireModel(5, 5, 8);
    input = new StringReader("4 2 4 4 4 5 4 3 4 7 4 5 6 4 4 4 3 4 5 4 1 4 3 4");
    output = new StringBuilder();
    boardDefault3x3View = new MarbleSolitaireTextView(boardDefault3x3);
    boardDefault3x3Controller = new MarbleSolitaireControllerImpl(boardDefault3x3,
            boardDefault3x3View, input);
  }

  @Test
  public void playGame() {
    boardDefault3x3Controller.playGame();
//    assertEquals(
//            "    O O O\n" +
//            "    O O O\n" +
//            "O O O O O O O\n" +
//            "O O O _ O O O\n" +
//            "O O O O O O O\n" +
//            "    O O O\n" +
//            "    O O O\n" +
//            "Score: 32\n" +
//            "    O O O\n" +
//            "    O O O\n" +
//            "O O O O O O O\n" +
//            "O _ _ O O O O\n" +
//            "O O O O O O O\n" +
//            "    O O O\n" +
//            "    O O O\n" +
//            "Score: 31\n" +
//            "    O O O\n" +
//            "    O O O\n" +
//            "O O O O O O O\n" +
//            "O _ O _ _ O O\n" +
//            "O O O O O O O\n" +
//            "    O O O\n" +
//            "    O O O\n" +
//            "Score: 30\n" +
//            "    O O O\n" +
//            "    O O O\n" +
//            "O O O O O O O\n" +
//            "O _ O _ O _ _\n" +
//            "O O O O O O O\n" +
//            "    O O O\n" +
//            "    O O O\n" +
//            "Score: 29\n" +
//            "    O O O\n" +
//            "    O O O\n" +
//            "O O O O O O O\n" +
//            "O _ O O O _ _\n" +
//            "O O O _ O O O\n" +
//            "    O _ O\n" +
//            "    O O O\n" +
//            "Score: 28\n" +
//            "    O O O\n" +
//            "    O O O\n" +
//            "O O O _ O O O\n" +
//            "O _ O _ O _ _\n" +
//            "O O O O O O O\n" +
//            "    O _ O\n" +
//            "    O O O\n" +
//            "Score: 27\n" +
//            "Game over!\n" +
//            "    O _ O\n" +
//            "    O _ O\n" +
//            "O O O O O O O\n" +
//            "O _ O _ O _ _\n" +
//            "O O O O O O O\n" +
//            "    O _ O\n" +
//            "    O O O\n" +
//            "Score: 26", boardDefault3x3Controller);
  }

  // tests that all the SlotStates in a board with an
  // empty slot in the middle are correctly instantiated
  private void correctConstructor(EnglishSolitaireModel board) {
    int arm = (board.getBoardSize() + 2) / 3;
    for (int r = 0; r < board.getBoardSize(); r += 1) {
      if (r < arm - 1 || r > board.getBoardSize() - arm) {
        // testing the 'narrow' rows of the game board (arm thickness length)
        for (int c = 0; c < board.getBoardSize(); c += 1) {
          if (c < arm - 1 || c > board.getBoardSize() - arm) {
            assertEquals(MarbleSolitaireModelState.SlotState.Invalid,
                    board.getSlotAt(r, c));
          } else {
            assertEquals(MarbleSolitaireModelState.SlotState.Marble,
                    board.getSlotAt(r, c));
          }
        }
      } else {
        // testing the 'wide' rows of the game board (board size length)
        for (int c = 0; c < board.getBoardSize(); c += 1) {
          if (c == board.getBoardSize() / 2 && r == board.getBoardSize() / 2) {
            assertEquals(MarbleSolitaireModelState.SlotState.Empty,
                    board.getSlotAt(r, c));
          } else {
            assertEquals(MarbleSolitaireModelState.SlotState.Marble,
                    board.getSlotAt(r, c));
          }
        }
      }
    }
  }

  @Test
  public void constructors() {
    correctConstructor(boardDefault3x3);
    correctConstructor(boardDefault5x5);
    try {
      EnglishSolitaireModel boardDefault4x4 = new EnglishSolitaireModel(4);
      fail("Arm thickness is not a positive odd number");
    } catch (IllegalArgumentException e) {
      // correctly throws IllegalArgumentException
    }
    try {
      EnglishSolitaireModel boardNegArm = new EnglishSolitaireModel(-2);
      fail("Arm thickness is not a positive odd number");
    } catch (IllegalArgumentException e) {
      // correctly throws IllegalArgumentException
    }
    try {
      EnglishSolitaireModel boardEmpty11 = new EnglishSolitaireModel(1, 1);
      fail("Invalid position for empty slot");
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
    try {
      MarbleSolitaireTextView boardNull = new MarbleSolitaireTextView(null);
      fail("Provided model cannot be null");
    } catch (IllegalArgumentException e) {
      // correctly throws IllegalArgumentException
    }
  }

  @Test
  public void move() {
    // valid vertical move
    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            boardDefault3x3.getSlotAt(3, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            boardDefault3x3.getSlotAt(4, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            boardDefault3x3.getSlotAt(5, 3));
    boardDefault3x3.move(5, 3, 3, 3);
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            boardDefault3x3.getSlotAt(3, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            boardDefault3x3.getSlotAt(4, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            boardDefault3x3.getSlotAt(5, 3));
    // valid horizontal move
    this.init();
    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            boardDefault3x3.getSlotAt(3, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            boardDefault3x3.getSlotAt(3, 4));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            boardDefault3x3.getSlotAt(3, 5));
    boardDefault3x3.move(3, 5, 3, 3);
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            boardDefault3x3.getSlotAt(3, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            boardDefault3x3.getSlotAt(3, 4));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            boardDefault3x3.getSlotAt(3, 5));
    // check invalid moves
    this.init();
    try {
      boardDefault3x3.move(5, 5, 3, 5);
      fail("'from' position is invalid");
    } catch (IllegalArgumentException e) {
      // correctly throws the IllegalArgumentException
    }
    try {
      boardDefault3x3.move(5, 3, 5, 1);
      fail("'to' position is invalid");
    } catch (IllegalArgumentException e) {
      // correctly throws the IllegalArgumentException
    }
    try {
      boardDefault3x3.move(3, 3, 5, 3);
      fail("no marble at 'from' position");
    } catch (IllegalArgumentException e) {
      // correctly throws the IllegalArgumentException
    }
    try {
      boardDefault3x3.move(2, 1, 2, 3);
      fail("'to' position is not empty");
    } catch (IllegalArgumentException e) {
      // correctly throws the IllegalArgumentException
    }
    try {
      boardDefault3x3.move(3, 2, 3, 3);
      fail("'from' and 'to' positions are not two spaces away");
    } catch (IllegalArgumentException e) {
      // correctly throws the IllegalArgumentException
    }
    try {
      boardDefault3x3.move(3, 2, 3, 5);
      fail("'from' and 'to' positions are not two spaces away");
    } catch (IllegalArgumentException e) {
      // correctly throws the IllegalArgumentException
    }
    try {
      boardDefault3x3.move(4, 4, 3, 3);
      fail("'from' and 'to' positions are not two spaces away");
    } catch (IllegalArgumentException e) {
      // correctly throws the IllegalArgumentException
    }
    try {
      boardDefault3x3.move(2, 4, 4, 2);
      fail("'from' and 'to' positions are not two spaces away");
    } catch (IllegalArgumentException e) {
      // correctly throws the IllegalArgumentException
    }
    boardDefault3x3.move(3, 5, 3, 3); // setup for next test
    try {
      boardDefault3x3.move(3, 6, 3, 4);
      fail("no marble between the 'from' and 'to' positions");
    } catch (IllegalArgumentException e) {
      // correctly throws the IllegalArgumentException
    }
  }

  @Test
  public void isGameOver() {
    assertFalse(boardDefault3x3.isGameOver());
    boardDefault3x3.move(3, 1, 3, 3);
    assertFalse(boardDefault3x3.isGameOver());
    boardDefault3x3.move(3, 4, 3, 2);
    assertFalse(boardDefault3x3.isGameOver());
    boardDefault3x3.move(3, 6, 3, 4);
    assertFalse(boardDefault3x3.isGameOver());
    boardDefault3x3.move(5, 3, 3, 3);
    assertFalse(boardDefault3x3.isGameOver());
    boardDefault3x3.move(2, 3, 4, 3);
    assertFalse(boardDefault3x3.isGameOver());
    boardDefault3x3.move(0, 3, 2, 3);
    assertTrue(boardDefault3x3.isGameOver());
  }

  @Test
  public void getBoardSize() {
    assertEquals(7, boardDefault3x3.getBoardSize());
    assertEquals(7, board3x3Empty31.getBoardSize());
    assertEquals(13, boardDefault5x5.getBoardSize());
    assertEquals(13, board5x5Empty58.getBoardSize());
  }

  @Test
  public void getSlotAt() {
    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            boardDefault3x3.getSlotAt(3, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid,
            boardDefault3x3.getSlotAt(0, 0));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid,
            boardDefault3x3.getSlotAt(0, 6));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid,
            boardDefault3x3.getSlotAt(6, 0));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid,
            boardDefault3x3.getSlotAt(6, 6));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid,
            boardDefault3x3.getSlotAt(1, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid,
            boardDefault3x3.getSlotAt(1, 5));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid,
            boardDefault3x3.getSlotAt(5, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid,
            boardDefault3x3.getSlotAt(5, 5));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            boardDefault3x3.getSlotAt(2, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            boardDefault3x3.getSlotAt(2, 4));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            boardDefault3x3.getSlotAt(4, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            boardDefault3x3.getSlotAt(4, 4));
    try {
      boardDefault3x3.getSlotAt(-1, 3);
      fail("Invalid cell position (-1, 3)");
    } catch (IllegalArgumentException e) {
      // correctly throws IllegalArgumentException
    }
    try {
      boardDefault3x3.getSlotAt(7, 4);
      fail("Invalid cell position (7, 4)");
    } catch (IllegalArgumentException e) {
      // correctly throws IllegalArgumentException
    }
    try {
      boardDefault3x3.getSlotAt(3, -2);
      fail("Invalid cell position (3, -2)");
    } catch (IllegalArgumentException e) {
      // correctly throws IllegalArgumentException
    }
    try {
      boardDefault3x3.getSlotAt(4, 7);
      fail("Invalid cell position (4, 7)");
    } catch (IllegalArgumentException e) {
      // correctly throws IllegalArgumentException
    }
  }

  @Test
  public void getScore() {
    assertEquals(32, boardDefault3x3.getScore());
    boardDefault3x3.move(3, 1, 3, 3);
    assertEquals(31, boardDefault3x3.getScore());
    boardDefault3x3.move(3, 4, 3, 2);
    assertEquals(30, boardDefault3x3.getScore());
    boardDefault3x3.move(3, 6, 3, 4);
    assertEquals(29, boardDefault3x3.getScore());
    boardDefault3x3.move(5, 3, 3, 3);
    assertEquals(28, boardDefault3x3.getScore());
    boardDefault3x3.move(2, 3, 4, 3);
    assertEquals(27, boardDefault3x3.getScore());
    boardDefault3x3.move(0, 3, 2, 3);
    assertEquals(26, boardDefault3x3.getScore());
    assertEquals(32, board3x3Empty31.getScore());
    assertEquals(104, boardDefault5x5.getScore());
    assertEquals(104, board5x5Empty58.getScore());
  }

  @Test
  public void toStringView() {
    assertEquals(
            "    O O O\n" +
                    "    O O O\n" +
                    "O O O O O O O\n" +
                    "O O O _ O O O\n" +
                    "O O O O O O O\n" +
                    "    O O O\n" +
                    "    O O O", boardDefault3x3View.toString());
    boardDefault3x3.move(3, 1, 3, 3);
    boardDefault3x3.move(3, 4, 3, 2);
    boardDefault3x3.move(3, 6, 3, 4);
    boardDefault3x3.move(5, 3, 3, 3);
    boardDefault3x3.move(2, 3, 4, 3);
    boardDefault3x3.move(0, 3, 2, 3);
    assertEquals(
            "    O _ O\n" +
                    "    O _ O\n" +
                    "O O O O O O O\n" +
                    "O _ O _ O _ _\n" +
                    "O O O O O O O\n" +
                    "    O _ O\n" +
                    "    O O O", boardDefault3x3View.toString());
  }
}