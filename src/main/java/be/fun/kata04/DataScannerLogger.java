package be.fun.kata04;

import java.io.IOException;

import static com.google.common.base.Preconditions.checkNotNull;

// Decorator
public class DataScannerLogger<T> implements DataScanner<T> {

    private final DataScanner<T> scanner;

    public DataScannerLogger(final DataScanner<T> scanner) {
        this.scanner = checkNotNull(scanner);
    }

    public T scan() throws IOException {
        T data = scanner.scan();

        System.out.println(data);

        return data;
    }
}
