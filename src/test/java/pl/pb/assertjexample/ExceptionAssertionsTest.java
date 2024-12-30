package pl.pb.assertjexample;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.assertj.core.api.BDDAssertions.then;

class ExceptionAssertionsTest {

    @Test
    void assertExceptionTest() {
        RuntimeException runtimeException = new RuntimeException("Something went wrong with exampleMethod");

        // Assert
        assertThat(runtimeException)
                .hasMessage("Something went wrong with exampleMethod")
                .hasMessage("Something went wrong with %s", "exampleMethod")
                // check start
                .hasMessageStartingWith("Something went wrong")
                .hasMessageStartingWith("%s went wrong with", "Something")
                // check content
                .hasMessageContaining("Something went wrong with")
                .hasMessageContaining("went %s", "wrong")
                .hasMessageContainingAll("went", "wrong")
                // check end
                .hasMessageEndingWith("exampleMethod")
                .hasMessageEndingWith("with %s", "exampleMethod")
                // check with regex
                .hasMessageMatching("Something went wrong with .*")
                // check does not contain
                .hasMessageNotContaining("Is OK")
                .hasMessageNotContainingAny("Is", "OK");
    }

    @Test
    void assertThatExceptionOfTypeTest() {
        // Assert
        assertThatExceptionOfType(RuntimeException.class)
                .isThrownBy(this::someMethodWhichThrowException)
                .havingCause()
                .withMessage("Something went wrong with exampleMethod");
    }

    @Test
    void rootCauseTest() {
        // Assert
        assertThatThrownBy(this::someMethodWhichThrowException)
                .hasRootCause(new IllegalArgumentException("Something went wrong with exampleMethod"))
                .hasRootCauseMessage("Something went wrong with exampleMethod")
                .hasRootCauseMessage("Something went wrong with %s", "exampleMethod")
                // hasRootCauseInstanceOf will match inheritance
                .hasRootCauseInstanceOf(IllegalArgumentException.class)
                .hasRootCauseInstanceOf(RuntimeException.class)
                // hasRootCauseExactlyInstanceOf will match only exact same type
                .hasRootCauseExactlyInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void assertThatNoExceptionTest() {
        // Assert
        assertThatNoException()
                .isThrownBy(() -> System.out.println("OK"));

        assertThatCode(() -> System.out.println("OK"))
                .doesNotThrowAnyException();
    }

    @Test
    void BddStyleTest() {
        String[] names = {"Pier ", "Pol", "Jak"};
        Throwable thrown = catchThrowable(() -> System.out.println(names[9]));

        // Assert
        then(thrown).isInstanceOf(ArrayIndexOutOfBoundsException.class)
                .hasMessageContaining("9");

        assertThat(thrown).isInstanceOf(ArrayIndexOutOfBoundsException.class)
                .hasMessageContaining("9")
                .hasMessage("Index 9 out of bounds for length 3");

    }

    private void someMethodWhichThrowException() {
        throw new RuntimeException(new IllegalArgumentException("Something went wrong with exampleMethod"));
    }
}
