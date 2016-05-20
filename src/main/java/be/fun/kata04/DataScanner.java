package be.fun.kata04;

import java.io.IOException;

// Strategy
public interface DataScanner<T> {

    T scan() throws IOException;
}
