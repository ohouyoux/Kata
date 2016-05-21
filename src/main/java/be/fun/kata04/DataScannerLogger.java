package be.fun.kata04;

import java.util.logging.Logger;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 *
 *
 * @param <T> the type of data that this {@code DataScanner} will produce
 * @param <E> the type of {@code Exception} that this {@code DataScanner} may throw while parsing data
 */
public class DataScannerLogger<T, E extends Exception> implements DataScanner<T, E> {

    private static final Logger LOGGER = Logger.getLogger(DataScannerLogger.class.getName());

    // Decorator - http://www.oodesign.com/decorator-pattern.html

    private final DataScanner<T, E> scanner;

    /**
     *
     *
     * @param scanner
     */
    public DataScannerLogger(final DataScanner<T, E> scanner) {
        this.scanner = checkNotNull(scanner);
    }

    public T scan() throws E {
        T data = scanner.scan();
        LOGGER.info(data.toString());

        return data;
    }
}
