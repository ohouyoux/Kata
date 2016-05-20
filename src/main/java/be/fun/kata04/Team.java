package be.fun.kata04;


import com.google.common.base.Objects;
import com.google.common.collect.ComparisonChain;

import java.io.Serializable;

import static com.google.common.base.Preconditions.checkNotNull;

public class Team implements Comparable<Team>, Serializable {

    private static final long serialVersionUID = 1L;

    private final String name;

    private int difference;

    public Team(final String name, final int score, final int conceded) {
        this.name = checkNotNull(name, "Team name should not be null");
        this.difference = Math.abs(score - conceded);
    }

    public String getName() {
        return name;
    }

    public int getDifference() {
        return difference;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }

    @Override
    public boolean equals(final Object object) {
        if (object == this) {
            return true;
        }

        if (object instanceof Team) {
            Team team = (Team) object;

            return Objects.equal(name, team.name);
        }

        return false;
    }

    public int compareTo(final Team team) {
        return ComparisonChain.start().compare(difference, team.difference).result();
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append('[')
                .append(getName())
                .append(": ")
                .append(getDifference())
                .append(']')
                .toString();
    }
}
