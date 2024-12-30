package pl.pb.assertjexample;

import org.junit.jupiter.api.Test;
import pl.pb.assertjexample.model2.Person;
import pl.pb.assertjexample.model2.TestDataFactory;

import java.time.Instant;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class OverridingErrorMessageTest {

    Person input = TestDataFactory.createDefaultPerson();
    Person expected = TestDataFactory.createPerson("John", "Hilton", Instant.parse("1965-01-01T01:01:01.00Z"), List.of());

    @Test
    void as() {
        // Assert
        assertThat(input)
                .as("%n<<< Expected Person: %n<%s>%n should be %n<%s>%n >>>%n", expected, input)
                .isEqualTo(expected);
    }

    @Test
    void withFailMessage() {
        // Assert
        assertThat(input)
                .withFailMessage("%n<<< Expected Person: %n<%s>%n should be %n<%s>%n >>>%n", expected, input)
                .isEqualTo(expected);
    }

    @Test
    void overridingErrorMessage() {
        // Assert
        assertThat(input)
                .overridingErrorMessage("%n<<< Expected Person: %n<%s>%n should be %n<%s>%n >>>%n", expected, input)
                .isEqualTo(expected);
    }

}
