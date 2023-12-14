package laslib;

import java.io.EOFException;
import java.io.UncheckedIOException;

public class UncheckedEOFException extends UncheckedIOException {

    public UncheckedEOFException() {
        super(new EOFException());
    }
}
