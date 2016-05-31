package be.fun.kata04;

import static com.google.common.base.Preconditions.checkNotNull;

import com.google.common.base.Objects;

import java.io.Serializable;

/**
 * A daily weather sample.
 *
 * @author Olivier Houyoux
 */
public class Weather implements Serializable {

    private static final long serialVersionUID = 1L;

    private String city;

    private String month;

    private int day;

    private int spread;

    /**
     * Modifies the name of the city where this {@code Weather} sample was recorded.
     *
     * @param city the new city name
     */
    public void setCity(final String city) {
        this.city = city;
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
     * Modifies the month when this sample was recorded.
     *
     * @param month the new month
     */
    public void setMonth(final String month) {
        this.month = month;
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
     * Modifies the day of the month when this sample was recorded.
     *
     * @param day the new day of the month
     */
    public void setDay(final int day) {
        this.day = day;
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
     * Modifies the difference between the maximum and minimum temperature for the sample.
     *
     * @param spread the new temperature spread
     */
    public void setSpread(final int spread) {
        this.spread = spread;
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
    public final int hashCode() {
        return Objects.hashCode(city, month, day, spread);
    }

    @Override
    public final boolean equals(final Object object) {
        if (object == this) {
            return true;
        }

        if (object instanceof Weather) {
            Weather weather = (Weather) object;

            return Objects.equal(city, weather.city)
                    && Objects.equal(month, weather.month)
                    && Objects.equal(day, weather.day)
                    && Objects.equal(spread, weather.spread);
        }

        return false;
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append("Weather [")
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
