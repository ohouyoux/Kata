package be.fun.kata04;


import com.google.common.base.Objects;

import java.io.Serializable;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * A daily weather sample.
 *
 * @author Olivier Houyoux
 */
public class Weather implements Serializable {

    private static final long serialVersionUID = 1L;

    private final String city;

    private final String month;

    private final int day;

    private int spread;

    /**
     * Instantiates a new {@code Weather}.
     *
     * @param city the name of the city where the weather sample was captured
     * @param month the month of the sample
     * @param day the day of the month of the sample
     * @param spread the temperature spread (difference between the maximum and minimum temp during {@code day})
     */
    public Weather(final String city, final String month, final int day, final int spread) {
        this.city = checkNotNull(city, "City should not be null");
        this.month = checkNotNull(month, "Month should not be null");
        this.day = day;
        this.spread = spread;
    }

    /**
     * Returns the name of the city where this {@code Weather} sample was recorded.
     *
     * @return the name of the city
     */
    public String getCity() {
        return city;
    }

    /**
     * Returns the month when this sample was recorded.
     *
     * @return the month of this {@code Weather} sample
     */
    public String getMonth() {
        return month;
    }

    /**
     * Returns the day of the month when this sample was recorded.
     *
     * @return the day of the month of this {@code Weather} sample
     */
    public int getDay() {
        return day;
    }

    /**
     * Returns the difference between the maximum and minimum temperatures measured on {@code day}.
     *
     * @return the temperature spread
     * @see #getDay()
     * @see #getMonth()
     */
    public int getSpread() {
        return spread;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(city, month, day);
    }

    @Override
    public boolean equals(final Object object) {
        if (object == this) {
            return true;
        }

        if (object instanceof Weather) {
            Weather weather = (Weather) object;

            return Objects.equal(city, weather.city)
                    && Objects.equal(month, weather.month)
                    && Objects.equal(day, weather.day);
        }

        return false;
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append('[')
                .append(getCity())
                .append(" - ")
                .append(getMonth())
                .append(": ")
                .append(getDay())
                .append(" > ")
                .append(getSpread())
                .append(']')
                .toString();
    }
}
