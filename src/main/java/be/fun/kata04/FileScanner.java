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
        // Template Method
        List<String> lines = load(file);
        lines = clean(lines);

        return split(lines);
    }

    protected List<String> load(final File file) throws IOException {
        return Files.readLines(file, Charsets.UTF_8);
    }

    protected List<String> clean(final List<String> lines) {
        return Lists.newArrayList(Iterables.filter(lines, new Predicate<String>() {

            public boolean apply(final String line) {
                return line.length() > 0 && Character.isDigit(line.trim().charAt(0));
            }
        }));
    }

    protected abstract T split(List<String> lines);
}
