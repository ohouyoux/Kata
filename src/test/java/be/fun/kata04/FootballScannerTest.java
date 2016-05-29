package be.fun.kata04;

import com.google.common.collect.Lists;
import com.google.common.io.Resources;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.net.URL;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class FootballScannerTest {

    private FootballScanner systemUnderTest;

    private List<String> linesStub;

    @Before
    public void setUp() throws Exception {
        URL url = Resources.getResource("football.dat");
        File file = new File(url.toURI());
        systemUnderTest = new FootballScanner(file);

        linesStub = Lists.newArrayList(
                "1. Arsenal         38    26   9   3    79  -  36    87",
                "2. Liverpool       38    24   8   6    67  -  30    80",
                "3. Manchester_U    38    24   5   9    87  -  45    77",
                "4. Newcastle       38    21   8   9    74  -  52    71",
                "5. Leeds           38    18  12   8    53  -  37    66");
    }

    @Test
    public void shouldSelectTeamWithShortestGoalDifference() {
        Team result = systemUnderTest.split(linesStub);

        assertThat(result.getName()).isEqualTo("Leeds");
    }
}