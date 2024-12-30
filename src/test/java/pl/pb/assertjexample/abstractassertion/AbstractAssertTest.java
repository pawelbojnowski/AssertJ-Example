package pl.pb.assertjexample.abstractassertion;

import org.assertj.core.api.junit.jupiter.SoftAssertionsExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pl.pb.assertjexample.model2.Address;
import pl.pb.assertjexample.model2.Person;
import pl.pb.assertjexample.model2.TestDataFactory;

import java.time.Instant;
import java.util.List;

@ExtendWith(SoftAssertionsExtension.class)
class AbstractAssertTest {

    @Test
    void assertCustomAssertion() {
        Person persson = TestDataFactory.createDefaultPerson();

        // Assert
        Address expectedAddress = persson.addresses().get(0);
        PersonAssert.assertPerson(persson)
                .isFirstname("Matt")
                .isLastname("Deep")
                .addressIsNotEmpty()
                .hasAddress(expectedAddress);

    }

    @Test
    void failAssertCustomAssertionOfBatman() {
        Person persson = TestDataFactory.createPerson("Matt", "Deep", Instant.parse("1965-01-01T01:01:01.00Z"), List.of());

        // Assert
        PersonAssert.assertPerson(persson)
                .isFirstname("Matt")
                .isLastname("Deep")
                .addressIsNotEmpty();

    }

}
