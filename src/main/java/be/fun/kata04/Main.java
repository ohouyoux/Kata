package be.fun.kata04;

import com.google.common.io.Resources;

import java.io.File;
import java.net.URL;

/**
 * Main entry point.
 *
 * @see http://codekata.com/kata/kata04-data-munging/
 */
public class Main {

    /**
     * Scans weather and football team data to display meaningful information.
     *
     * @param args the application arguments
     * @throws Exception if for some reason any application data could not be read
     */
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
