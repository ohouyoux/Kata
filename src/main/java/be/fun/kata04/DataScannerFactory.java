package be.fun.kata04;

import java.io.File;

// Factory
public class DataScannerFactory {

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
