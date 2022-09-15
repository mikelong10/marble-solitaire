import java.io.IOException;
import java.nio.CharBuffer;

/**
 * This mock class represents a corrupt Readable object. Its read method throws an IOException,
 * mocking the behavior of a corrupt Readable object that cannot read values. This class is used
 * to test controller behavior with a bad Readable object, i.e., handling potential IOExceptions
 * from the Readable object.
 */
public class BadReadable implements Readable {
  @Override
  public int read(CharBuffer cb) throws IOException {
    throw new IOException("Failed to read");
  }
}
