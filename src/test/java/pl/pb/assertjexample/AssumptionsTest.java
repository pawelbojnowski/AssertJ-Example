package pl.pb.assertjexample;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.Assumptions.assumeThat;

class AssumptionsTest {

    @Test
    void assumeThatTest() {

        // Assert
        // Ignore test if assumeThat is false
        assumeThat("22").isEqualTo("22");
        // ... this assertion is not performed
        assertThat("2").contains("2");
    }

    @Test
    void assumeThatNotFulfillTest() {

        // Assert
        // Ignore test if assumeThat is false
        assumeThat("22").isEqualTo("2");
        // ... this assertion is not performed
        assertThat("2").contains("2");
    }
}
