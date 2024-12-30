package pl.pb.assertjexample.softassertion;

import org.assertj.core.api.BDDSoftAssertions;
import org.junit.jupiter.api.Test;

class SoftAssertionsTest {

    @Test
    void BDDSoftAssertionsTest() {

        String valueToTest = "Soft Assertions example !";

        BDDSoftAssertions softly = new BDDSoftAssertions();

        // Assert
        softly.then(valueToTest).contains("best example");
        softly.then(valueToTest).startsWith("Assertions");
        softly.then(valueToTest).endsWith("example");

        softly.assertAll();
    }
}
