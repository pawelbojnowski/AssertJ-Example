package pl.pb.assertjexample.softassertion;

import org.assertj.core.api.BDDSoftAssertions;
import org.assertj.core.api.SoftAssertions;
import org.assertj.core.api.junit.jupiter.InjectSoftAssertions;
import org.assertj.core.api.junit.jupiter.SoftAssertionsExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(SoftAssertionsExtension.class)
class JUnit5SoftAssertionsTest {

    @InjectSoftAssertions
    private SoftAssertions softAssertions;

    @InjectSoftAssertions
    private BDDSoftAssertions bddSoftAssertions;

    @Test
    void softAssertionsTest() {
        String valueToTest = "Soft Assertions example !";

        // Assert
        softAssertions.assertThat(valueToTest).contains("best example");
        softAssertions.assertThat(valueToTest).startsWith("Assertions");
        softAssertions.assertThat(valueToTest).endsWith("example");
    }

    @Test
    void BDDSoftAssertionsTest() {
        String valueToTest = "Soft Assertions example !";

        // Assert
        bddSoftAssertions.then(valueToTest).contains("best example");
        bddSoftAssertions.then(valueToTest).startsWith("Assertions");
        bddSoftAssertions.then(valueToTest).endsWith("example");
    }
}
