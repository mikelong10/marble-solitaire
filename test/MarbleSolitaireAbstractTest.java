import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.StringReader;

import cs3500.marblesolitaire.controller.MarbleSolitaireController;
import cs3500.marblesolitaire.controller.MarbleSolitaireControllerImpl;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw04.MarbleSolitaireAbstractFactory;
import cs3500.marblesolitaire.view.MarbleSolitaireTextView;
import cs3500.marblesolitaire.view.MarbleSolitaireView;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * This abstract class represents the test class for all Marble Solitaire programs. Each
 * variation of Marble Solitaire can be tested by extending this class.
 */
public abstract class MarbleSolitaireAbstractTest {
  protected MarbleSolitaireAbstractFactory factory;
  protected MarbleSolitaireModel defaultModel;
  protected Appendable defaultOutput;
  protected MarbleSolitaireView defaultView;
  protected MarbleSolitaireModel size7Model;
  protected Appendable size7Output;
  protected MarbleSolitaireView size7View;
  protected MarbleSolitaireModel hole22Model;
  protected Appendable hole22Output;
  protected MarbleSolitaireView hole22View;
  protected MarbleSolitaireModel size5hole42Model;
  protected Appendable size5hole42Output;
  protected MarbleSolitaireView size5hole42View;
  protected int startLength;

  @Before
  public void init() {
    factory = MarbleSolitaireAbstractFactory.getFactory(modelType());
    defaultModel = factory.createModel();
    defaultOutput = new StringBuilder();
    defaultView = factory.createTextView(defaultOutput);
    factory = MarbleSolitaireAbstractFactory.getFactory(modelType(), 7);
    size7Model = factory.createModel();
    size7Output = new StringBuilder();
    size7View = factory.createTextView(size7Output);
    factory = MarbleSolitaireAbstractFactory.getFactory(modelType(), 2, 2);
    hole22Model = factory.createModel();
    hole22Output = new StringBuilder();
    hole22View = factory.createTextView(hole22Output);
    factory = MarbleSolitaireAbstractFactory.getFactory(modelType(), 5, 4, 2);
    size5hole42Model = factory.createModel();
    size5hole42Output = new StringBuilder();
    size5hole42View = factory.createTextView(size5hole42Output);
    startLength = this.defaultStartGameOutput().length;
  }

  // to return the model type that this test is testing, in String format
  protected abstract String modelType();

  // to run a sequence of moves on the default model to reach a game over state
  protected abstract void runGameOverSequence();

  // to run one valid move on the default model
  protected void runFirstValidMove() {
    defaultModel.move(5, 3,3, 3);
  }

  // returns the starting score for the default board
  protected abstract int defaultStartingScore();

  // returns the starting score for the size7 board
  protected abstract int size7StartingScore();

  // returns the starting score for the board with hole at (2,2)
  protected int hole22StartingScore() {
    return this.defaultStartingScore();
  }

  // returns the starting score for the size 5 board with hole at (4,2)
  protected abstract int size5hole42StartingScore();

  // to return the correct start game message line by line, including the welcome message
  // and initial board and score
  protected abstract String[] defaultStartGameOutput();

  // returns the String input to be given to the controller to make
  // a first valid move on a default board (different for default triangle)
  protected String firstValidMoveDefaultInput() {
    return "6 4 4 4 q";
  }

  // to return the correct game display after the corresponding first valid move input
  protected abstract String[] firstValidMoveDefaultOutput();

  // returns a string of invalid moves testing edge cases of the model
  protected abstract String invalidMovesDefaultInput();

  // returns the game display corresponding to the set of
  // invalid moves provided in invalidMovesInput()
  protected abstract String[] invalidMovesDefaultOutput();

  // returns a String input of a valid move for the model interrupted by invalid inputs
  protected String interruptedValidMoveInput() {
    return "6 r 4 -4.4 4 ! 4 0 2 q";
  }

  // returns a String input of an invalid move interrupted by invalid inputs
  protected String interruptedInvalidMoveInput() {
    return "2 v 24 -2 4 4.4 4 a 4 2 w 4 q";
  }

  // returns the invalid move message when trying to move with any cell position out of bounds
  protected String invalidMoveCellPosOutput() {
    return "Invalid move. Play again. Invalid cell position (2,24).";
  }

  // returns the output when the game is immediately quit after start
  protected abstract String[] immediateQuitOutput();

  // returns a String input of one valid move then input where
  // the user quits in the middle of making a new move
  protected String quitMidMoveInput() {
    return "6 4 4 4 3 4 Q 3 4 5 4";
  }

  // returns the quit game output from quitting after the first valid move
  protected abstract String[] quitAfterOneMoveOutput();

  // returns a String input of a sequence of moves that will
  // reach a 'game over' state through the model
  protected abstract String gameOverInput();

  // returns the game over output produced when the user
  // causes the game to end through playing moves
  protected abstract String[] gameOverOutput();

  // returns a String input without a 'q' to quit,
  // simulating the Readable input for the view running out of inputs
  protected String runOutOfInputsInput() {
    return "6 4 4 4 4 5";
  }

  // returns a String of inputs for testing that the controller is
  // receiving the correct inputs in a variety of cases
  protected String controllerTestInputs() {
    return "4 2 4 4 p 4 0 5 -2 4 3.3 3 $ 4 8 4 6 8 4 0 6 w 4 q";
  }

  // returns the output produced with the mock model methods
  // displaying what the model receives as input
  protected String[] confirmInputsOutput() {
    return new String[]{
      // model correctly receives input from controller for a valid move
      "Inputs received by model: fromRow = 3, fromCol = 1, toRow = 3, toCol = 3",
      // model correctly receives input from controller for a valid move
      // while being interrupted by invalid inputs
      "Inputs received by model: fromRow = 3, fromCol = 4, toRow = 3, toCol = 2",
      // model still correctly receives inputs from controller for an invalid move
      "Inputs received by model: fromRow = 3, fromCol = 7, toRow = 3, toCol = 5",
      // model still correctly receives inputs from controller for an invalid move
      // while being interrupted by invalid inputs
      "Inputs received by model: fromRow = 7, fromCol = 3, toRow = 5, toCol = 3"
    };
  }

  /*
   ********** MODEL TESTS **********
   */
  @Test
  public abstract void testModelConstructors();

  @Test
  public abstract void testMove();

  @Test
  public void testIsGameOver() {
    assertFalse(defaultModel.isGameOver());
    this.runGameOverSequence();
    assertTrue(defaultModel.isGameOver());
  }

  @Test
  public void testGetBoardSize() {
    assertEquals(7, defaultModel.getBoardSize());
    assertEquals(19, size7Model.getBoardSize());
    assertEquals(7, hole22Model.getBoardSize());
    assertEquals(13, size5hole42Model.getBoardSize());
  }

  @Test
  public abstract void testGetSlotAt();

  @Test
  public void testGetScore() {
    assertEquals(this.defaultStartingScore(), defaultModel.getScore());
    this.runFirstValidMove();
    assertEquals(this.defaultStartingScore() - 1, defaultModel.getScore());
    assertEquals(this.size7StartingScore(), size7Model.getScore());
    assertEquals(this.hole22StartingScore(), hole22Model.getScore());
    assertEquals(this.size5hole42StartingScore(), size5hole42Model.getScore());
  }

  /*
   ********** VIEW TESTS **********
   */
  @Test
  public void testViewConstructors() {
    try {
      MarbleSolitaireView boardNull = new MarbleSolitaireTextView(null);
      fail("Provided state cannot be null");
    } catch (IllegalArgumentException e) {
      // correctly throws IllegalArgumentException
      assertEquals("Provided state cannot be null", e.getMessage());
    }
    try {
      MarbleSolitaireView boardModelNull = new MarbleSolitaireTextView(null, new StringBuilder());
      fail("Provided state cannot be null");
    } catch (IllegalArgumentException e) {
      // correctly throws IllegalArgumentException
      assertEquals("Provided state cannot be null", e.getMessage());
    }
    try {
      MarbleSolitaireView boardDestNull = new MarbleSolitaireTextView(defaultModel, null);
      fail("Appendable destination cannot be null");
    } catch (IllegalArgumentException e) {
      // correctly throws IllegalArgumentException
      assertEquals("Appendable destination cannot be null", e.getMessage());
    }
    try {
      MarbleSolitaireView nullView = new MarbleSolitaireTextView(null, null);
      fail("Parameters cannot be null");
    } catch (IllegalArgumentException e) {
      // correctly throws IllegalArgumentException
      assertEquals("Provided state cannot be null", e.getMessage());
    }
  }

  @Test
  public void testToString() {
    String[] result = defaultView.toString().split(System.lineSeparator());
    for (int i = 2; i < this.defaultStartGameOutput().length - 1; i += 1) {
      assertEquals(this.defaultStartGameOutput()[i], result[i - 2]);
    }
    this.runFirstValidMove();
    result = defaultView.toString().split(System.lineSeparator());
    for (int i = 0; i < this.firstValidMoveDefaultOutput().length - 1; i += 1) {
      assertEquals(this.firstValidMoveDefaultOutput()[i], result[i]);
    }
  }

  @Test
  public void testRenderBoard() {
    try {
      defaultView.renderBoard();
    } catch (IOException e) {
      fail("Failed to render board");
    }
    String[] display = defaultOutput.toString().split(System.lineSeparator());
    for (int i = 2; i < this.defaultStartGameOutput().length - 1; i += 1) {
      assertEquals(this.defaultStartGameOutput()[i], display[i - 2]);
    }
  }

  @Test
  public void testRenderMessage() {
    try {
      defaultView.renderMessage("Welcome to Marble Solitaire!\nPlay Now:\n");
    } catch (IOException e) {
      fail("Failed to render message");
    }
    assertEquals("Welcome to Marble Solitaire!\nPlay Now:\n", defaultOutput.toString());
  }

  @Test
  public void testBadAppendableRender() {
    Appendable bad = new BadAppendable();
    MarbleSolitaireView badView = new MarbleSolitaireTextView(defaultModel, bad);
    try {
      badView.renderMessage("Welcome to Marble Solitaire!\nPlay Now:\n");
      fail("Did not throw IOException");
    } catch (IOException e) {
      // failed to render message due to bad Appendable output
      assertEquals("Failed to append", e.getMessage());
    }
    try {
      badView.renderMessage("\nScore: " + defaultModel.getScore() + "\n");
      fail("Did not throw IOException");
    } catch (IOException e) {
      // failed to render message due to bad Appendable output
      assertEquals("Failed to append", e.getMessage());
    }
    try {
      badView.renderBoard();
      fail("Did not throw IOException");
    } catch (IOException e) {
      // failed to render board due to bad Appendable output
      assertEquals("Failed to append", e.getMessage());
    }
  }

  /*
   ********** CONTROLLER TESTS **********
   */
  @Test
  public void testControllerConstructors() {
    try {
      MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(null,
              defaultView, new StringReader(this.gameOverInput()));
      fail("Does not throw IllegalArgumentException when given null model");
    } catch (IllegalArgumentException e) {
      assertEquals("Model cannot be null", e.getMessage());
    }
    try {
      MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(defaultModel,
              null, new StringReader(this.gameOverInput()));
      fail("Does not throw IllegalArgumentException when given null view");
    } catch (IllegalArgumentException e) {
      assertEquals("View cannot be null", e.getMessage());
    }
    try {
      MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(defaultModel,
              defaultView, null);
      fail("Does not throw IllegalArgumentException when given null Readable");
    } catch (IllegalArgumentException e) {
      assertEquals("Readable cannot be null", e.getMessage());
    }
    try {
      MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(null,
              null, null);
      fail("Does not throw IllegalArgumentException when given null parameters");
    } catch (IllegalArgumentException e) {
      assertEquals("Model cannot be null", e.getMessage());
    }
  }

  @Test
  public void testStartGameMessage() {
    MarbleSolitaireController defaultController = new MarbleSolitaireControllerImpl(defaultModel,
            defaultView, new StringReader("q"));
    defaultController.playGame();
    String[] display = defaultOutput.toString().split(System.lineSeparator());
    for (int i = 0; i < this.startLength; i += 1) {
      assertEquals(this.defaultStartGameOutput()[i], display[i]);
    }
  }

  @Test
  public void testValidMoveMessages() {
    MarbleSolitaireController defaultController = new MarbleSolitaireControllerImpl(defaultModel,
            defaultView, new StringReader(this.firstValidMoveDefaultInput()));
    defaultController.playGame();
    String[] display = defaultOutput.toString().split(System.lineSeparator());
    int totalLength = this.startLength + this.firstValidMoveDefaultOutput().length;
    for (int i = this.startLength; i < totalLength; i += 1) {
      assertEquals(this.firstValidMoveDefaultOutput()[i - this.startLength], display[i]);
    }
  }

  @Test
  public void testInvalidMoveMessages() {
    MarbleSolitaireController defaultController = new MarbleSolitaireControllerImpl(defaultModel,
            defaultView, new StringReader(this.invalidMovesDefaultInput()));
    defaultController.playGame();
    String[] display = defaultOutput.toString().split(System.lineSeparator());
    int totalLength = this.startLength + this.invalidMovesDefaultOutput().length;
    for (int i = this.startLength; i < totalLength; i += 1) {
      assertEquals(this.invalidMovesDefaultOutput()[i - this.startLength], display[i]);
    }
  }

  @Test
  public void testInvalidStringResponse() {
    MarbleSolitaireController defaultController = new MarbleSolitaireControllerImpl(defaultModel,
            defaultView, new StringReader("w quit a q"));
    defaultController.playGame();
    String[] display = defaultOutput.toString().split(System.lineSeparator());
    for (int i = this.startLength; i < this.startLength + 3; i += 1) {
      assertEquals("Please re-enter a valid input " +
              "(positive numbers to play a move OR 'q' to quit):", display[i]);
    }
  }

  @Test
  public void testInvalidNumberResponse() {
    MarbleSolitaireController defaultController = new MarbleSolitaireControllerImpl(defaultModel,
            defaultView, new StringReader("-1 0 2.5 -4 Q"));
    defaultController.playGame();
    String[] display = defaultOutput.toString().split(System.lineSeparator());
    for (int i = this.startLength; i < this.startLength + 4; i += 1) {
      assertEquals("Please re-enter a valid input " +
              "(positive numbers to play a move OR 'q' to quit):", display[i]);
    }
  }

  @Test
  public void testInterruptedValidMoveResponse() {
    MarbleSolitaireController defaultController = new MarbleSolitaireControllerImpl(defaultModel,
            defaultView, new StringReader(this.interruptedValidMoveInput()));
    defaultController.playGame();
    String[] display = defaultOutput.toString().split(System.lineSeparator());
    for (int i = this.startLength; i < this.startLength + defaultModel.getBoardSize() + 5; i += 1) {
      if (i >= this.startLength + 3 &&
              i < this.startLength + defaultModel.getBoardSize() + 4) {
        assertEquals(this.firstValidMoveDefaultOutput()[i - this.startLength - 3], display[i]);
      } else {
        assertEquals("Please re-enter a valid input " +
                "(positive numbers to play a move OR 'q' to quit):", display[i]);
      }
    }
  }

  @Test
  public void testInterruptedInvalidMoveResponse() {
    MarbleSolitaireController defaultController = new MarbleSolitaireControllerImpl(defaultModel,
            defaultView, new StringReader(this.interruptedInvalidMoveInput()));
    defaultController.playGame();
    String[] display = defaultOutput.toString().split(System.lineSeparator());
    for (int i = this.startLength; i < this.startLength + 6; i += 1) {
      if (i == this.startLength + 3) {
        assertEquals(this.invalidMoveCellPosOutput(), display[i]);
      } else {
        assertEquals("Please re-enter a valid input " +
                "(positive numbers to play a move OR 'q' to quit):", display[i]);
      }
    }
  }

  @Test
  public void testImmediateQuitResponse() {
    MarbleSolitaireController defaultController = new MarbleSolitaireControllerImpl(defaultModel,
            defaultView, new StringReader("q"));
    defaultController.playGame();
    String[] display = defaultOutput.toString().split(System.lineSeparator());
    for (int i = this.startLength; i < display.length; i += 1) {
      assertEquals(this.immediateQuitOutput()[i - this.startLength], display[i]);
    }
  }

  @Test
  public void testQuitMidMoveAfterValidMoveResponse() {
    MarbleSolitaireController defaultController = new MarbleSolitaireControllerImpl(defaultModel,
            defaultView, new StringReader(this.quitMidMoveInput()));
    defaultController.playGame();
    String[] display = defaultOutput.toString().split(System.lineSeparator());
    for (int i = this.startLength; i < display.length; i += 1) {
      if (i < this.startLength + this.firstValidMoveDefaultOutput().length) {
        assertEquals(this.firstValidMoveDefaultOutput()[i - this.startLength], display[i]);
      } else {
        assertEquals(this.quitAfterOneMoveOutput()[i -
                (this.startLength + this.firstValidMoveDefaultOutput().length)], display[i]);
      }
    }
  }

  @Test
  public void testGameOverResponse() {
    MarbleSolitaireController defaultController = new MarbleSolitaireControllerImpl(defaultModel,
            defaultView, new StringReader(this.gameOverInput()));
    defaultController.playGame();
    String[] display = defaultOutput.toString().split(System.lineSeparator());
    for (int i = 0; i < display.length; i += 1) {
      assertEquals(this.gameOverOutput()[i], display[i]);
    }
  }

  @Test
  public void testRunOutOfInputsResponse() {
    MarbleSolitaireController defaultController = new MarbleSolitaireControllerImpl(defaultModel,
            defaultView, new StringReader(this.runOutOfInputsInput()));
    try {
      defaultController.playGame();
      fail("Controller did not throw IllegalStateException when its Scanner ran out of inputs");
    } catch (IllegalStateException e) {
      // the controller ran out of user inputs and was not told to quit
      assertEquals("No more inputs remaining", e.getMessage());
    }
  }

  @Test
  public void testEmptyInputResponse() {
    MarbleSolitaireController defaultController = new MarbleSolitaireControllerImpl(defaultModel,
            defaultView, new StringReader(""));
    try {
      defaultController.playGame();
      fail("Controller did not throw IllegalStateException when given an empty input");
    } catch (IllegalStateException e) {
      // the controller ran out of user inputs and was not told to quit
      assertEquals("No more inputs remaining", e.getMessage());
    }
  }

  @Test
  public void testBadReadablePlayGame() {
    Readable badRd = new BadReadable();
    MarbleSolitaireController defaultController = new MarbleSolitaireControllerImpl(defaultModel,
            defaultView, badRd);
    try {
      defaultController.playGame();
      fail("Controller did not throw IllegalStateException " +
              "with a Readable that throws IOExceptions");
    } catch (IllegalStateException e) {
      // correctly throws IllegalStateException
      assertEquals("No more inputs remaining", e.getMessage());
    }
  }

  @Test
  public void testBadAppendablePlayGame() {
    // given list of valid move combinations that should have the user run out of moves
    Appendable bad = new BadAppendable();
    MarbleSolitaireView badView = new MarbleSolitaireTextView(defaultModel, bad);
    MarbleSolitaireController defaultController = new MarbleSolitaireControllerImpl(defaultModel,
            badView, new StringReader(this.gameOverInput()));
    try {
      defaultController.playGame();
      fail("Controller did not throw IllegalStateException, but should have as soon as it tried " +
              "to render the first output with the view");
    } catch (IllegalStateException e) {
      // tried to play a game with a view with a bad Appendable
      assertEquals("Failed to render message", e.getMessage());
    }
  }

  @Test
  public void testControllerInputs() {
    Appendable inputsSent = new StringBuilder();
    MarbleSolitaireModel confirmInputs = new ConfirmInputsModel(inputsSent);
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(confirmInputs,
            defaultView, new StringReader(this.controllerTestInputs()));
    controller.playGame();
    String[] inputsReceived = inputsSent.toString().split(System.lineSeparator());
    for (int i = 0; i < this.confirmInputsOutput().length; i += 1) {
      assertEquals(this.confirmInputsOutput()[i], inputsReceived[i]);
    }
  }
}
