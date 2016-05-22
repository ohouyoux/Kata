package be.fun.kata04;

import com.google.common.base.Function;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;

import java.io.File;
import java.util.List;

/**
 * A {@code FileScanner} for football data which retrieves the {@code Team} with the smallest difference in ‘for’ and
 * ‘against’ goals.
 *
 * @author Olivier Houyoux
 */
public class FootballScanner extends FileScanner<Team> {

    private static final Splitter SPLITTER = Splitter.on(' ').trimResults().omitEmptyStrings();

    private static final class LineSplitter implements Function<String, Team> {

        /**
         * Splits a text line and uses the data to create a {@code Team} object.
         *
         * @param line the digit based line to be split
         * @return a {@code Weather} object
         */
        public Team apply(final String line) {
            List<String> columns = SPLITTER.splitToList(line);
            String name = columns.get(1);
            int scored = Integer.parseInt(columns.get(6));
            int against = Integer.parseInt(columns.get(8));

            return new Team(name, scored, against);
        }
    }

    /**
     * Instantiates a new {@code FootballScanner}.
     *
     * @param file the {@code File} which contains the football data
     */
    public FootballScanner(final File file) {
        super(file);
    }

    /**
     * Splits each information line into useful data chunk used to create a {@code Team}. Selects the one with the
     * smallest goal difference.
     *
     * @param lines the raw text line that are scanned and split into {@code Team}s
     * @return the {@code Team} with the smallest goal difference
     */
    protected Team split(final List<String> lines) {
        List<Team> teams = Lists.transform(lines, new LineSplitter());

        return Ordering.natural().leastOf(teams, 1).get(0);
    }
}
