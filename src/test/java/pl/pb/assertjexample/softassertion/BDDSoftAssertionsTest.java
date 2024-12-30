package pl.pb.assertjexample.softassertion;

import org.assertj.core.api.BDDSoftAssertions;
import org.junit.jupiter.api.Test;

class BDDSoftAssertionsTest {

    @Test
    void bddSoftAssertionsTest() {
        String valueToTest = "Soft Assertions example !";

        // Assert
        BDDSoftAssertions softly = new BDDSoftAssertions();
        softly.then(valueToTest).contains("best example");
        softly.then(valueToTest).startsWith("Assertions");
        softly.then(valueToTest).endsWith("example");

        softly.assertAll();
    }
}
