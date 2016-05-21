package be.fun.kata04;

import com.google.common.base.Function;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;

import java.io.File;
import java.util.List;

public class FootballScanner extends FileScanner<Team> {

    private static final Splitter SPLITTER = Splitter.on(' ').trimResults().omitEmptyStrings();

    public FootballScanner(final File file) {
        super(file);
    }

    protected Team split(final List<String> lines) {
        List<Team> teams = Lists.transform(lines, new Function<String, Team>() {

            public Team apply(final String line) {
                List<String> columns = SPLITTER.splitToList(line);
                String name = columns.get(1);
                int scored = Integer.parseInt(columns.get(6));
                int against = Integer.parseInt(columns.get(8));

                return new Team(name, scored, against);
            }
        });

        return Ordering.natural().leastOf(teams, 1).get(0);
    }
}
