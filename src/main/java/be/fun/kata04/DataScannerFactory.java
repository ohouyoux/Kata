package be.fun.kata04;

import java.io.File;

/**
 * Helps to instantiate new {@code DataScanner} instances.
 */
public class DataScannerFactory {

    // Factory

    /**
     * Instantiates a new {@code DataScanner}.
     *
     * @param type the type of data that the new {@code DataScanner} will have to parse
     * @param data the data to be parsed by the new {@code DataScanner}
     * @return a new {@code DataScanner}
     */
    public DataScanner newInstance(final DataType type, final File data) {
        DataScanner scanner;

        switch (type) {
            case WEATHER:
                scanner = new WeatherScanner(data);
                break;

            default:
                scanner = new FootballScanner(data);
        }

        return new DataScannerLogger(scanner);
    }
}
