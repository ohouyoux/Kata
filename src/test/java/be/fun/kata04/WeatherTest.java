package be.fun.kata04;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;
import org.junit.Test;

import static com.google.code.beanmatchers.BeanMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class WeatherTest {

    @Test
    public void gettersAndSettersShouldWorkForEachProperty() {
        assertThat(Weather.class, hasValidGettersAndSetters());
    }

    @Test
    public void allPropertiesShouldInfluenceHashCode() {
        assertThat(Weather.class, hasValidBeanHashCode());
    }

    @Test
    public void allPropertiesShouldBeComparedDuringEquals() {
        assertThat(Weather.class, hasValidBeanEquals());
    }

    @Test
    public void allPropertiesShouldBeRepresentedInToStringOutput() {
        assertThat(Weather.class, hasValidBeanToString());
    }

    @Test
    public void equalsContract() {
        EqualsVerifier.forClass(Weather.class).suppress(Warning.NONFINAL_FIELDS).verify();
    }
}