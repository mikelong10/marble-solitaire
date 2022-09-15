import java.io.IOException;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;

/**
 * This mock class represents a Marble Solitaire model class that aids in testing that the
 * controller receives correct inputs when playing a game and making moves. Because only the move
 * method deals with user input taken in by the controller, this mock class implements the move
 * method in a way that helps test inputs.
 */
public class ConfirmInputsModel implements MarbleSolitaireModel {
  private final Appendable log;

  /**
   * Creates a mock object of a Marble Solitaire model for testing. The provided Appendable log
   * is used in the methods that deal with user input handled by the controller.
   *
   * @param log the given Appendable output object for this model to append to
   */
  public ConfirmInputsModel(Appendable log) {
    this.log = log;
  }

  /**
   * This implementation of the move method simply appends the values it was given as parameters
   * to an Appendable log. The log output will be used for testing to check if the controller
   * gives the model the right inputs.
   *
   * @param fromRow the row number of the position to be moved from
   *                (starts at 0)
   * @param fromCol the column number of the position to be moved from
   *                (starts at 0)
   * @param toRow   the row number of the position to be moved to
   *                (starts at 0)
   * @param toCol   the column number of the position to be moved to
   *                (starts at 0)
   * @throws IllegalArgumentException if the log fails to append the message containing the inputs
   */
  @Override
  public void move(int fromRow, int fromCol, int toRow, int toCol) throws IllegalArgumentException {
    try {
      this.log.append(String.format("Inputs received by model: fromRow = %d, fromCol = %d, " +
                      "toRow = %d, toCol = %d\n", fromRow, fromCol, toRow, toCol));
    } catch (IOException e) {
      throw new IllegalArgumentException(e);
    }
  }

  @Override
  public boolean isGameOver() {
    return false;
  }

  @Override
  public int getBoardSize() {
    return 0;
  }

  @Override
  public SlotState getSlotAt(int row, int col) throws IllegalArgumentException {
    return null;
  }

  @Override
  public int getScore() {
    return 0;
  }
}
