import java.io.IOException;

/**
 * This mock class represents a corrupt Appendable object. All of its append methods throw an
 * IOException, mocking the behavior of a corrupt Appendable object that is unable to append any
 * values. This class is used to test the view class' behavior with a bad Appendable object and
 * the controller class' behavior with a view object with a bad Appendable object, i.e.,
 * IOExceptions thrown by transmitting output to a corrupt Appendable destination and trying to
 * render messages and the game board to a corrupt Appendable destination.
 */
public class BadAppendable implements Appendable {
  @Override
  public Appendable append(CharSequence csq) throws IOException {
    throw new IOException("Failed to append");
  }

  @Override
  public Appendable append(CharSequence csq, int start, int end) throws IOException {
    throw new IOException("Failed to append");
  }

  @Override
  public Appendable append(char c) throws IOException {
    throw new IOException("Failed to append");
  }
}
