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
 *
 *
 * @author Olivier Houyoux
 */
public class WeatherScanner extends FileScanner<Weather> {

    public static final class SpreadComparator implements Comparator<Weather> {

        public int compare(final Weather w1, final Weather w2) {
            return Ints.compare(w1.getSpread(), w2.getSpread());
        }
    }

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
