package be.fun.kata04;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(JUnitParamsRunner.class)
public class TeamTest {

    @Test
    @Parameters({ "Arsenal, 79, 36", "Liverpool, 67, 30", "Manchester_U, 87, 45" })
    public void shouldRememberTeamName(final String name, final int score, final int conceded) {
        Team systemUnderTest = new Team(name, score, conceded);

        assertThat(systemUnderTest.getName()).isEqualTo(name);
    }

    @Test
    @Parameters({ "Arsenal, 79, 36, 43", "Liverpool, 67, 30, 37", "Manchester_U, 87, 45, 42" })
    public void shouldCalculateGoalDifference(
            final String name,
            final int score,
            final int conceded,
            final int expected) {

        Team systemUnderTest = new Team(name, score, conceded);

        assertThat(systemUnderTest.getDifference()).isEqualTo(expected);
    }

    @Test
    public void equalsContract() {
        EqualsVerifier.forClass(Team.class).suppress(Warning.NONFINAL_FIELDS).verify();
    }
}