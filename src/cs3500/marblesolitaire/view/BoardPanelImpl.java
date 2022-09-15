package cs3500.marblesolitaire.view;

import java.awt.*;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

import cs3500.marblesolitaire.controller.Features;
import cs3500.marblesolitaire.controller.MoveListener;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;

public class BoardPanelImpl extends JPanel implements BoardPanel {
  private MarbleSolitaireModelState modelState;
  private Image emptySlot, marbleSlot, blankSlot;
  private final int cellDimension;
  private int originX, originY;
  private Features feature;

  public BoardPanelImpl(MarbleSolitaireModelState state) throws IllegalStateException {
    super();
    this.modelState = state;
    this.setBackground(Color.WHITE);
    this.cellDimension = 80;
    try {
      emptySlot = ImageIO.read(new FileInputStream("res/empty.png"));
      emptySlot = emptySlot.getScaledInstance(cellDimension, cellDimension, Image.SCALE_DEFAULT);

      marbleSlot = ImageIO.read(new FileInputStream("res/marble.png"));
      marbleSlot = marbleSlot.getScaledInstance(cellDimension, cellDimension, Image.SCALE_DEFAULT);

      blankSlot = ImageIO.read(new FileInputStream("res/blank.png"));
      blankSlot = blankSlot.getScaledInstance(cellDimension, cellDimension, Image.SCALE_DEFAULT);

      this.setPreferredSize(
              new Dimension((this.modelState.getBoardSize() + 2) * cellDimension
                      , (this.modelState.getBoardSize() + 2) * cellDimension));
    } catch (IOException e) {
      throw new IllegalStateException("Icons not found!");
    }
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);

    originX = (int) (this.getPreferredSize().getWidth() / 2 - this.modelState.getBoardSize() * cellDimension / 2);
    originY = (int) (this.getPreferredSize().getHeight() / 2 - this.modelState.getBoardSize() * cellDimension / 2);

    //your code to the draw the board should go here. 
    //The originX and originY is the top-left of where the cell (0,0) should start
    //cellDimension is the width (and height) occupied by every cell
    for (int r = 0; r < this.modelState.getBoardSize(); r += 1) {
      for (int c = 0; c < this.modelState.getBoardSize(); c += 1) {
        if (this.modelState.getSlotAt(r, c) == MarbleSolitaireModelState.SlotState.Marble) {
          g.drawImage(marbleSlot, c * cellDimension + originX, r * cellDimension + originY, this);
        } else if (this.modelState.getSlotAt(r, c) == MarbleSolitaireModelState.SlotState.Empty) {
          g.drawImage(emptySlot, c * cellDimension + originX, r * cellDimension + originY, this);
        } else if (this.modelState.getSlotAt(r, c) == MarbleSolitaireModelState.SlotState.Invalid) {
          g.drawImage(blankSlot, c * cellDimension + originX, r * cellDimension + originY, this);
        }
      }
    }
  }

  @Override
  public void setFeature(Features feature) {
    this.feature = feature;
    this.addMouseListener(new MoveListener(feature));
  }
}
