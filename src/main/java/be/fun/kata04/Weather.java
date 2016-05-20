package be.fun.kata04;


import com.google.common.base.Objects;

import java.io.Serializable;

import static com.google.common.base.Preconditions.checkNotNull;

public class Weather implements Serializable {

    private static final long serialVersionUID = 1L;

    private final String city;

    private final String month;

    private final int day;

    private int spread;

    public Weather(final String city, final String month, final int day, final int spread) {
        this.city = checkNotNull(city, "City should not be null");
        this.month = checkNotNull(month, "Month should not be null");
        this.day = day;
        this.spread = spread;
    }

    public String getCity() {
        return city;
    }

    public String getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

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
