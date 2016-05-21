package be.fun.kata04;

import static com.google.common.base.Preconditions.checkNotNull;

// Decorator - http://www.oodesign.com/decorator-pattern.html
public class DataScannerLogger<T, E extends Exception> implements DataScanner<T, E> {

    private final DataScanner<T, E> scanner;

    public DataScannerLogger(final DataScanner<T, E> scanner) {
        this.scanner = checkNotNull(scanner);
    }

    public T scan() throws E {
        T data = scanner.scan();

        System.out.println(data);

        return data;
    }
}
