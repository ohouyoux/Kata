package be.fun.kata04;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;

import java.io.File;
import java.util.List;

public class FootballScanner extends LineScanner<List<Team>> {

    private static final Splitter SPLITTER = Splitter.on(' ').trimResults().omitEmptyStrings();

    public FootballScanner(final File file) {
        super(file);
    }

    protected List<Team> split(final List<String> lines) {
        return Lists.transform(lines, new Function<String, Team>() {

            public Team apply(final String line) {
                List<String> columns = SPLITTER.splitToList(line);
                String name = columns.get(1);
                int scored = Integer.parseInt(columns.get(6));
                int against = Integer.parseInt(columns.get(7));

                return new Team(name, scored, against);
            }
        });
    }

    protected Predicate<String> getCleaner() {
        return new Predicate<String>() {

            public boolean apply(final String line) {
                return line.length() > 0 && Character.isDigit(line.trim().charAt(0));
            }
        };
    }
}
