package pl.pb.assertjexample;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MainTest {

    @Test
    void simpleExampleOfAssertionsWithAsserJ() {
        assertThat("Hello AssertJ ;)")
                .isEqualTo("Hello AssertJ ;)");
    }
}
