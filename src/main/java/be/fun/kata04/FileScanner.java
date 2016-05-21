package be.fun.kata04;

import com.google.common.base.Charsets;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.io.Files;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 *
 *
 * @author Olivier Houyoux
 * @param <T>
 */
public abstract class FileScanner<T> implements DataScanner<T, IOException> {

    private File file;

    /**
     * Instantiates a new {@code FileScanner}.
     *
     * @param file the {@code File} which contains the data to be loaded
     */
    public FileScanner(final File file) {
        this.file = checkNotNull(file, "DataType file should not be null");
    }


    public T scan() throws IOException {
        // Template Method - http://www.oodesign.com/template-method-pattern.html
        List<String> lines = load(file);
        lines = clean(lines);

        // TODO add a step, the split (or scan) should return a list of type that should then be sorted

        return split(lines);
    }

    /**
     * Loads the data to be scanned from the given {@code file}.
     *
     * @param file the {@code File} which contains the data to scan
     * @return the data to scan as a {@code List} of text lines
     * @throws IOException if {@code file} could not be read
     */
    protected List<String> load(final File file) throws IOException {
        return Files.readLines(file, Charsets.UTF_8);
    }

    /**
     * Removes data lines that can't be scanned.
     *
     * @param lines all text lines from the data {@code file}
     * @return the text lines that can be scanned
     */
    protected List<String> clean(final List<String> lines) {
        return Lists.newArrayList(Iterables.filter(lines, new Predicate<String>() {

            public boolean apply(final String line) {
                return line.length() > 0 && Character.isDigit(line.trim().charAt(0));
            }
        }));
    }

    /**
     * Splits the given lines into some meaningful format.
     *
     * @param lines the raw text line that are scanned and split
     * @return a meaningful view of the scanned data
     */
    protected abstract T split(List<String> lines);
}
