package be.fun.kata04;

import com.google.common.io.Resources;

import java.io.File;
import java.net.URL;

public class Main {

    public static void main(final String[] args) throws Exception {
        URL url = Resources.getResource("weather.dat");
        File file = new File(url.toURI());
        DataScannerFactory factory = new DataScannerFactory();
        DataScanner scanner = factory.newInstance(DataType.WEATHER, file);
        scanner.scan();

        url = Resources.getResource("football.dat");
        file = new File(url.toURI());
        scanner = factory.newInstance(DataType.FOOTBALL, file);
        scanner.scan();
    }
}
