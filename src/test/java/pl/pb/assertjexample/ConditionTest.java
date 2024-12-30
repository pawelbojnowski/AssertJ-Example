package pl.pb.assertjexample;

import org.assertj.core.api.Condition;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.allOf;
import static org.assertj.core.api.Assertions.anyOf;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.not;

class ConditionTest {

    public static final List<String> JEDIS = List.of("Luke", "Yoda", "Obiwan");
    public static final List<String> OTHER = List.of("Predator", "Mario", "Obiwan");

    // implementation with Java 7
    @Test
    void isJava7Test() {
        Condition<String> jedi = new Condition<String>("jedi") {
            @Override
            public boolean matches(String value) {
                return JEDIS.contains(value);
            }
        };

        // Assert
        assertThat("Yoda").is(jedi);
        assertThat("Predator").isNot(jedi);
    }

    // implementation with Java 8 lambda
    @Test
    void isJava8PlusTest() {
        Condition<String> jediPower = new Condition<>(JEDIS::contains, "jedi power");

        // Assert
        assertThat("Yoda").is(jediPower);
        assertThat("Predator").isNot(jediPower);
    }

    @Test
    void arePlusTest() {
        Condition<String> jediPower = new Condition<>(JEDIS::contains, "jedi power");

        // Assert
        assertThat(List.of("Luke", "Yoda")).are(jediPower);
        assertThat(List.of("Predator", "Mario")).areNot(jediPower);
    }

    @Test
    void combiningConditionsTest() {
        Condition<String> jedi = new Condition<>(JEDIS::contains, "jedi power");
        Condition<String> other = new Condition<>(OTHER::contains, "jedi power");

        // Assert
        assertThat("Luke").is(anyOf(jedi, other));
        assertThat("Solo").is(allOf(not(jedi), not(other)));
    }

}
