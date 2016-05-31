package be.fun.kata04;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import com.google.common.io.Resources;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import java.io.File;
import java.net.URL;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

@RunWith(JUnitParamsRunner.class)
public class FileScannerTest {

    private FileScanner systemUnderTest;

    @Before
    public void setUp() {
        systemUnderTest = mock(FileScanner.class, Mockito.CALLS_REAL_METHODS);
    }

    @Test
    @Parameters({ "weather.dat, 33", "football.dat, 22" })
    public void shouldLoadDataLinesFromFile(final String path, final int expected) throws Exception {
        URL url = Resources.getResource(path);
        File file = new File(url.toURI());
        List<String> result = systemUnderTest.load(file);

        assertThat(result.size()).isEqualTo(expected);
    }

    @Test
    @Parameters({ "weather.dat, 30", "football.dat, 20" })
    public void shouldRemoveLinesThatDoNotStartWithDigit(final String path, final int expected) throws Exception {
        URL url = Resources.getResource(path);
        File file = new File(url.toURI());
        List<String> lines = Files.readLines(file, Charsets.UTF_8);
        List<String> result = systemUnderTest.clean(lines);

        assertThat(result.size()).isEqualTo(expected);

    }
}