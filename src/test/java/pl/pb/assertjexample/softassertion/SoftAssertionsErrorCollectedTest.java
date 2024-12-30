package pl.pb.assertjexample.softassertion;

import org.assertj.core.api.BDDSoftAssertions;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;

class SoftAssertionsErrorCollectedTest {

    public static final String FORMAT = "--------------------------------------%n%s%n%n--------------------------------------";
    public static final String VALUE_TO_TEST = "Soft Assertions example !";

    @Test
    void softAssertionsTest() {
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.setAfterAssertionErrorCollected(assertionError -> {
            System.out.println(String.format(FORMAT, assertionError.getMessage()));
        });

        // Assert
        softAssertions.assertThat(VALUE_TO_TEST).contains("best example");
        softAssertions.assertThat(VALUE_TO_TEST).startsWith("Assertions");
        softAssertions.assertThat(VALUE_TO_TEST).endsWith("example");

        softAssertions.assertAll();
    }

    @Test
    void softAssertionsAssertSoftlyTest() {
        SoftAssertions
                .assertSoftly(softAssertions -> {
                    softAssertions.setAfterAssertionErrorCollected(assertionError -> {
                        System.out.println(String.format(FORMAT, assertionError.getMessage()));
                    });

                    // Assert
                    softAssertions.assertThat(VALUE_TO_TEST).contains("best example");
                    softAssertions.assertThat(VALUE_TO_TEST).startsWith("Assertions");
                    softAssertions.assertThat(VALUE_TO_TEST).endsWith("example");
                });
    }

    @Test
    void bddSoftAssertionsTest() {
        // Assert
        BDDSoftAssertions bddSoftAssertions = new BDDSoftAssertions();
        bddSoftAssertions.setAfterAssertionErrorCollected(assertionError -> {
            System.out.println(String.format(FORMAT, assertionError.getMessage()));
        });

        // Assert
        bddSoftAssertions.then(VALUE_TO_TEST).contains("best example");
        bddSoftAssertions.then(VALUE_TO_TEST).startsWith("Assertions");
        bddSoftAssertions.then(VALUE_TO_TEST).endsWith("example");

        bddSoftAssertions.assertAll();
    }
}
