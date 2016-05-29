package be.fun.kata04;

import com.google.common.collect.Lists;
import com.google.common.io.Resources;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.net.URL;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class WeatherScannerTest {

    private WeatherScanner systemUnderTest;

    private List<String> linesStub;

    @Before
    public void setUp() throws Exception {
        URL url = Resources.getResource("weather.dat");
        File file = new File(url.toURI());
        systemUnderTest = new WeatherScanner(file);

        linesStub = Lists.newArrayList(
                "1  88    59    74          53.8       0.00 F       280  9.6 270  17  1.6  93 23 1004.5",
                "2  79    63    71          46.5       0.00         330  8.7 340  23  3.3  70 28 1004.5",
                "3  77    55    66          39.6       0.00         350  5.0 350   9  2.8  59 24 1016.8",
                "4  77    59    68          51.1       0.00         110  9.1 130  12  8.6  62 40 1021.1",
                "5  90    66    78          68.3       0.00 TFH     220  8.3 260  12  6.9  84 55 1014.4");
    }

    @Test
    public void shouldSelectTeamWithShortestGoalDifference() {
        Weather result = systemUnderTest.split(linesStub);

        assertThat(result.getSpread()).isEqualTo(16);
    }
}
