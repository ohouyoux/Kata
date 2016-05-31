package be.fun.kata04;

import com.google.common.base.Function;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;
import com.google.common.primitives.Ints;

import java.io.File;
import java.io.Serializable;
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
    public static final class SpreadComparator implements Comparator<Weather>, Serializable {

        private static final long serialVersionUID = 1L;

        public int compare(final Weather w1, final Weather w2) {
            return Ints.compare(w1.getSpread(), w2.getSpread());
        }
    }

    private static final class LineCleaner implements Function<String, String> {

        /**
         * Removes non-digit characters from a line.
         *
         * @param line the raw line to be cleaned
         * @return a text line made of only number characters
         */
        public String apply(final String line) {
            return line.replaceAll("[^0-9\\.]+", " ");
        }
    }

    private static final class LineSplitter implements Function<String, Weather> {

        /**
         * Splits a numbered text line and uses the numbers to create a {@code Weather} object.
         *
         * @param line the digit based line to be split
         * @return a {@code Weather} object
         */
        public Weather apply(final String line) {
            Weather weather = new Weather();
            weather.setCity("Morristown");
            weather.setMonth("June 2002");

            List<String> columns = SPLITTER.splitToList(line);
            int day = Integer.parseInt(columns.get(0));
            weather.setDay(day);
            int max = Integer.parseInt(columns.get(1));
            int min = Integer.parseInt(columns.get(2));
            int spread = max - min;
            weather.setSpread(spread);

            return weather;
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

        return Lists.transform(cleaned, new LineCleaner());
    }

    /**
     * Splits each information line into useful data chunk used to create a {@code Weather}. Selects the one with the
     * smallest temperature spread.
     *
     * @param lines the raw text line that are scanned and split into {@code Weather}s
     * @return the {@code Weather} with the smallest temperature spread
     */
    protected Weather split(final List<String> lines) {
        List<Weather> weathers = Lists.transform(lines, new LineSplitter());

        return Ordering.from(new SpreadComparator()).leastOf(weathers, 1).get(0);
    }
}
