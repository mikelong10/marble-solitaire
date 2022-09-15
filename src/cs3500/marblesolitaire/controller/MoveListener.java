package cs3500.marblesolitaire.controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MoveListener extends MouseAdapter {
  private final Features feature;

  public MoveListener(Features feature) {
    this.feature = feature;
  }

  @Override
  public void mouseClicked(MouseEvent e) {
    int r = e.getY() / 80 - 1;
    int c = e.getX() / 80 - 1;
    this.feature.buildMove(r, c);
  }
}
