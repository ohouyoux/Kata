package be.fun.kata04;

import com.google.common.base.Charsets;
import com.google.common.base.Predicate;
import com.google.common.base.Splitter;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.io.Files;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

public abstract class LineScanner<T> implements DataScanner<T> {

    private final File file;

    public LineScanner(final File file) {
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
        Predicate<String> cleaner = getCleaner();

        return Lists.newArrayList(Iterables.filter(lines, cleaner));
    }

    protected abstract T split(List<String> lines);

    // Factory Method
    protected abstract Predicate<String> getCleaner();
}
