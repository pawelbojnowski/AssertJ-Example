package pl.pb.assertjexample;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import pl.pb.assertjexample.model2.Person;
import pl.pb.assertjexample.model2.TestDataFactory;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.setDescriptionConsumer;
import static org.assertj.core.api.Assertions.setPrintAssertionsDescription;

class AssertionDescriptionWithConsumerTest {
    // init StringBuilder
    private static final StringBuilder DESCRIPTION_REPORT_BUILDER = new StringBuilder(String.format("Assertions:%n"));
    private static final String DESCRIPTION_FORMAT = " >> %s%n";

    {
        // set Print Assertions Description
        setPrintAssertionsDescription(true);

        // Set description consumer
        setDescriptionConsumer(description -> {
            String format = String.format(DESCRIPTION_FORMAT, description);
            System.out.println(format);
            DESCRIPTION_REPORT_BUILDER.append(format);
        });
    }

    @AfterAll
    public static void afterAll() {
        System.out.println(DESCRIPTION_REPORT_BUILDER);
    }

    @Test
    void testAssertionsFirst() {
        Person person = TestDataFactory.createDefaultPerson();

        // Assert
        assertThat(person.lastname())
                .as("Incorrect data in test: testAssertionsFirst")
                .isEqualTo("Cloney");
    }

    @Test
    void testAssertionsSecond() {
        Person person = TestDataFactory.createDefaultPerson();

        // Assert
        assertThat(person.lastname())
                .as("Incorrect data in test: testAssertionsSecond")
                .isEqualTo("Cloney");
    }
}
