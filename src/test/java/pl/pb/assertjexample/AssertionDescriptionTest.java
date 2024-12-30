package pl.pb.assertjexample;

import org.junit.jupiter.api.Test;
import pl.pb.assertjexample.model2.Person;
import pl.pb.assertjexample.model2.TestDataFactory;

import static org.assertj.core.api.Assertions.assertThat;

class AssertionDescriptionTest {

    @Test
    void simpleAssertionDescription() {
        Person person = TestDataFactory.createDefaultPerson();

        // Assert
        assertThat(person.firstname())
                .as("check first name: %s", person.firstname())
                .isEqualTo("Mikke");
    }
}
