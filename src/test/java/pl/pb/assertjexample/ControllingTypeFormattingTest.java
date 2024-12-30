package pl.pb.assertjexample;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.assertj.core.presentation.StandardRepresentation;
import org.junit.jupiter.api.Test;
import pl.pb.assertjexample.model2.Person;
import pl.pb.assertjexample.model2.TestDataFactory;
import pl.pb.assertjexample.model2.User;

import java.time.Instant;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static pl.pb.assertjexample.model2.TestDataFactory.createDefaultAddress;
import static pl.pb.assertjexample.model2.TestDataFactory.createDefaultPerson;
import static pl.pb.assertjexample.model2.TestDataFactory.createPerson;

class ControllingTypeFormattingTest {


    public class CustomRepresentation extends StandardRepresentation {
        public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper().registerModule(new JavaTimeModule());

        @Override
        public String fallbackToStringOf(Object o) {
            if (o instanceof Person) {
                try {
                    return String.format("%s=%s", o.getClass().getSimpleName(), OBJECT_MAPPER.writeValueAsString(o));
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }
            return super.fallbackToStringOf(o);
        }
    }

    @Test
    void displayCustomRepresentationForPerson() {
        Person input = createDefaultPerson();
        Person result = createPerson("Kriss", "Deep", Instant.parse("1965-01-01T01:01:01.00Z"), List.of(createDefaultAddress()));

        // Assert
        assertThat(input)
                .withRepresentation(new CustomRepresentation())
                .isEqualTo(result);
    }

    @Test
    void displayDefaultRepresentationForUser() {
        User input = TestDataFactory.createDefaultUser();
        User result = TestDataFactory.createUser("krisalone", "Kris", "Alone", true);

        // Assert
        assertThat(input)
                .withRepresentation(new CustomRepresentation())
                .isEqualTo(result);
    }
}
