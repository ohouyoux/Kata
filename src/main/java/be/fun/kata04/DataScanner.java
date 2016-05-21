package be.fun.kata04;

/**
 * Defines how to apply scanning.
 *
 * @param <T> the type of data that this {@code DataScanner} will produce
 * @param <E> the type of {@code Exception} that this {@code DataScanner} may throw while parsing data
 */
public interface DataScanner<T, E extends Exception> {

    // Strategy - http://www.oodesign.com/strategy-pattern.html

    /**
     * Scans raw data to produce meaningful information.
     *
     * @return the result of the scan
     * @throws E if the raw data could not be read
     */
    T scan() throws E;
}
