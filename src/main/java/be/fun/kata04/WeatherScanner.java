package be.fun.kata04;

import com.google.common.base.Function;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;

import java.io.File;
import java.util.List;

public class WeatherScanner extends LineScanner<Weather> {

    private static final Splitter SPLITTER = Splitter.on(' ').trimResults().omitEmptyStrings();

    public WeatherScanner(final File file) {
        super(file);
    }

    protected List<String> clean(final List<String> lines) {
        List<String> cleaned = super.clean(lines);

        return Lists.transform(cleaned, new Function<String, String>() {

            public String apply(final String line) {
                return line.replaceAll("[^0-9\\.]+", " ");
            }
        });
    }

    protected Weather split(final List<String> lines) {
        Weather weather = null;
        int smallest = Integer.MAX_VALUE;

        for (String line : lines) {
            List<String> columns = SPLITTER.splitToList(line);
            int day = Integer.parseInt(columns.get(0));
            int max = Integer.parseInt(columns.get(1));
            int min = Integer.parseInt(columns.get(2));
            int spread = max - min;

            if (spread < smallest) {
                smallest = spread;
                weather = new Weather("Morristown", "June 2002", day, spread);
            }
        }

        return weather;
    }
}
