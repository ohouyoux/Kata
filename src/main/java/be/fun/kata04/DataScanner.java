package be.fun.kata04;

import java.io.IOException;

/**
 * Defines how to apply scanning.
 *
 * @param <T> the type of data that this {@code DataScanner} will produce
 */
public interface DataScanner<T> {

    // Strategy

    /**
     * Scans raw data to produce meaningful information.
     *
     * @return the result of the scan
     * @throws IOException if the raw data could not be read
     */
    T scan() throws IOException;
}
