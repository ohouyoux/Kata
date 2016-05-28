package be.fun.kata04;

import com.google.common.io.Resources;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URL;

import static org.assertj.core.api.Assertions.assertThat;

public class DataScannerFactoryTest {

    private DataScannerFactory systemUnderTest;

    @Before
    public void setUp() {
        systemUnderTest = new DataScannerFactory();
    }

    @Test
    public void shouldCreateWeatherScannerForWeatherDataType() throws Exception {
        URL url = Resources.getResource("weather.dat");
        File file = new File(url.toURI());
        DataScanner<Weather, IOException> result = systemUnderTest.newInstance(DataType.WEATHER, file);

        assertThat(result).isInstanceOf(DataScannerLogger.class);

        Field field = DataScannerLogger.class.getDeclaredField("scanner");
        field.setAccessible(true);

        DataScanner<Weather, IOException> scanner = (DataScanner<Weather, IOException>) field.get(result);

        assertThat(scanner).isInstanceOf(WeatherScanner.class);
    }

    @Test
    public void shouldCreateFootballScannerForFootballDataType() throws Exception {
        URL url = Resources.getResource("football.dat");
        File file = new File(url.toURI());
        DataScanner<Weather, IOException> result = systemUnderTest.newInstance(DataType.FOOTBALL, file);

        assertThat(result).isInstanceOf(DataScannerLogger.class);

        Field field = DataScannerLogger.class.getDeclaredField("scanner");
        field.setAccessible(true);

        DataScanner<Team, IOException> scanner = (DataScanner<Team, IOException>) field.get(result);

        assertThat(scanner).isInstanceOf(FootballScanner.class);
    }
}