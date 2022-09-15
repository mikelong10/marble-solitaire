package cs3500.marblesolitaire.controller;

import java.util.ArrayList;
import java.util.List;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireGuiView;

public class MarbleSolitaireGUIController implements Features {
  private final MarbleSolitaireModel model;
  private final MarbleSolitaireGuiView view;
  private final ArrayList<Integer> moveInputs;

  public MarbleSolitaireGUIController(MarbleSolitaireModel model,
                                      MarbleSolitaireGuiView view) {
    this.model = model;
    this.view = view;
    this.view.setFeature(this);
    this.moveInputs = new ArrayList<Integer>();
  }

  @Override
  public void buildMove(int r, int c) {
    moveInputs.add(r);
    moveInputs.add(c);
    view.renderMessage("Selected slot (" + (r + 1) + ", " + (c + 1) + ")");
    if (moveInputs.size() >= 4) {
      attemptMove(r, c, moveInputs);
    }
    if (model.isGameOver()) {
      if (model.getScore() > 0) {
        view.renderMessage("Game Over! No more valid moves.");
      } else {
        view.renderMessage("Congratulations! You win!");
      }
    }
  }

  // to have the model attempt a move with the first four values in the given list of potential
  // move inputs, only called when moveInputs has at least 4 values
  private void attemptMove(int r, int c, List<Integer> moveInputs) throws IllegalStateException {
    try {
      // calls the model to attempt a move with the given 'from' and 'to' position coordinates
      model.move(moveInputs.remove(0), moveInputs.remove(0),
              moveInputs.remove(0), moveInputs.remove(0));
      // renders the updated board and score if the move was successful
      view.renderMessage("Successful move to " + "(" + (r + 1) + ", " + (c + 1) + ")!");
      view.refresh();
    } catch (IllegalArgumentException e) { // if the move was invalid
      // prompts the user to try another move combination and explains why the move was invalid
      view.renderMessage("Invalid move. Play again. " + e.getMessage() + "\n");
    }
  }
}
