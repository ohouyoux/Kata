package be.fun.kata04;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.logging.Logger;

/**
 * Decorates a {@code DataScanner} to log the result of the scan.
 *
 * @author Olivier Houyoux
 * @param <T> the type of data that this {@code DataScanner} will produce
 * @param <E> the type of {@code Exception} that this {@code DataScanner} may throw while parsing data
 */
public class DataScannerLogger<T, E extends Exception> implements DataScanner<T, E> {

    private static final Logger LOGGER = Logger.getLogger(DataScannerLogger.class.getName());

    // Decorator - http://www.oodesign.com/decorator-pattern.html

    private final DataScanner<T, E> scanner;

    /**
     * Instantiates a new {@code DataScannerLogger}.
     *
     * @param scanner the scanner whose output must be logged
     */
    public DataScannerLogger(final DataScanner<T, E> scanner) {
        this.scanner = checkNotNull(scanner);
    }

    /**
     * Scans the data from {@code scanner} and logs its output.
     *
     * @return the result of the scan from {@code scanner}
     * @throws E if the scan failed
     * @see #scanner
     */
    public T scan() throws E {
        T data = scanner.scan();
        LOGGER.info(data.toString());

        return data;
    }
}
