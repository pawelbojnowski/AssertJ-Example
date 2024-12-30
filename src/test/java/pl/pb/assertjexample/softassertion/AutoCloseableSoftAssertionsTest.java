package pl.pb.assertjexample.softassertion;

import org.assertj.core.api.AutoCloseableBDDSoftAssertions;
import org.assertj.core.api.AutoCloseableSoftAssertions;
import org.junit.jupiter.api.Test;

class AutoCloseableSoftAssertionsTest {

    @Test
    void softAssertionsTest() {
        String valueToTest = "Soft Assertions example !";

        // Assert
        try (AutoCloseableSoftAssertions softly = new AutoCloseableSoftAssertions()) {
            softly.assertThat(valueToTest).contains("best example");
            softly.assertThat(valueToTest).startsWith("Assertions");
            softly.assertThat(valueToTest).endsWith("example");

            softly.assertAll();
        } catch (AssertionError e) {
            System.out.println("Some assertions failed");
        }
    }

    @Test
    void BDDSoftAssertionsTest() {
        String valueToTest = "Soft Assertions example !";

        // Assert
        try (AutoCloseableBDDSoftAssertions softly = new AutoCloseableBDDSoftAssertions()) {
            softly.then(valueToTest).contains("best example");
            softly.then(valueToTest).startsWith("Assertions");
            softly.then(valueToTest).endsWith("example");

            softly.assertAll();
        }
    }
}
