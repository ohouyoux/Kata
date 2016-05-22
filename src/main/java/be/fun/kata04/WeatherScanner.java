package be.fun.kata04;

import com.google.common.base.Function;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;
import com.google.common.primitives.Ints;

import java.io.File;
import java.util.Comparator;
import java.util.List;

/**
 * A {@code FileScanner} for weather data which retrieves the {@code Weather} with the smallest temperature spread.
 *
 * @author Olivier Houyoux
 */
public class WeatherScanner extends FileScanner<Weather> {

    /**
     * A {@code Comparator} implementation which uses the {@code Weather}'s spread for ordering.
     */
    public static final class SpreadComparator implements Comparator<Weather> {

        public int compare(final Weather w1, final Weather w2) {
            return Ints.compare(w1.getSpread(), w2.getSpread());
        }
    }

    private static final Splitter SPLITTER = Splitter.on(' ').trimResults().omitEmptyStrings();

    /**
     * Instantiates a new {@code FootballScanner}.
     *
     * @param file the {@code File} which contains the football data
     */
    public WeatherScanner(final File file) {
        super(file);
    }

    /**
     * Cleans every single line of the loaded file from characters which are not parts of a number.
     *
     * @param lines all text lines from the data {@code file}
     * @return the text lines without non-digit characters
     */
    protected List<String> clean(final List<String> lines) {
        List<String> cleaned = super.clean(lines);

        return Lists.transform(cleaned, new Function<String, String>() {

            public String apply(final String line) {
                return line.replaceAll("[^0-9\\.]+", " ");
            }
        });
    }

    /**
     * Splits each information line into useful data chunk used to create a {@code Weather}. Selects the one with the
     * smallest temperature spread.
     *
     * @param lines the raw text line that are scanned and split into {@code Weather}s
     * @return the {@code Weather} with the smallest temperature spread
     */
    protected Weather split(final List<String> lines) {
        List<Weather> weathers = Lists.transform(lines, new Function<String, Weather>() {

            public Weather apply(final String line) {
                List<String> columns = SPLITTER.splitToList(line);
                int day = Integer.parseInt(columns.get(0));
                int max = Integer.parseInt(columns.get(1));
                int min = Integer.parseInt(columns.get(2));
                int spread = max - min;

                return new Weather("Morristown", "June 2002", day, spread);
            }
        });

        return Ordering.from(new SpreadComparator()).leastOf(weathers, 1).get(0);
    }
}
