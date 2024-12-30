package pl.pb.assertjexample;

import org.assertj.core.api.recursive.comparison.ComparingSnakeOrCamelCaseFields;
import org.assertj.core.api.recursive.comparison.RecursiveComparisonConfiguration;
import org.assertj.core.api.recursive.comparison.RecursiveComparisonIntrospectionStrategy;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pl.pb.assertjexample.model2.Address;
import pl.pb.assertjexample.model2.Person;
import pl.pb.assertjexample.model2.TestDataFactory;

import java.time.Instant;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static pl.pb.assertjexample.model2.TestDataFactory.createAddress;
import static pl.pb.assertjexample.model2.TestDataFactory.createDefaultAddress;
import static pl.pb.assertjexample.model2.TestDataFactory.createPerson;

class FieldByFieldRecursiveComparisonTest {

    private static final Person PERSON = TestDataFactory.createDefaultPerson();
    private static final Person PERSON_2 = createPerson("Matt", "Deep", Instant.parse("1965-01-01T01:01:01.00Z"), List.of(createAddress("Warsaw", "Nowowiejska 3", "01-001")));
    private static final Person PERSON_3 = createPerson("Jhon", "Deep", Instant.parse("1965-02-02T02:02:02.00Z"), List.of(createAddress("Warsaw", "Nowowiejska 3", "01-001")));
    private static final Person PERSON_4 = createPerson("Jhon", "Deep", Instant.parse("1965-02-02T02:02:02.00Z"), List.of(createDefaultAddress()));

    private static Stream<Arguments> personProvider() {
        return Stream.of(
                Arguments.of(PERSON),
                Arguments.of(PERSON_2),
                Arguments.of(PERSON_3),
                Arguments.of(PERSON_4)
        );
    }

    @Test
    void isEqualToTest() {
        // Assert
        assertThat(PERSON)
                .isEqualTo(PERSON_2);
    }

    @Test
    void usingRecursiveComparisonTest() {
        // Assert
        assertThat(PERSON)
                .usingRecursiveComparison()
                .isEqualTo(PERSON_2);
    }

    @ParameterizedTest
    @MethodSource("personProvider")
    void withEqualsForType(Person expectedPerson) {
        BiPredicate<? super Address, ? super Address> equals = new BiPredicate<Address, Address>() {
            @Override
            public boolean test(Address address1, Address address2) {
                return address1.street().equals(address2.street());
            }
        };

        // Assert
        assertThat(PERSON)
                .usingRecursiveComparison()
                .withEqualsForType(equals, Address.class)
                .isEqualTo(expectedPerson);
    }


    @ParameterizedTest
    @MethodSource("personProvider")
    void withEqualsForFields(Person expectedPerson) {

        BiPredicate<String, String> equals = new BiPredicate<String, String>() {
            @Override
            public boolean test(String date1, String date2) {
                return date1.equals(date2);
            }
        };

        // Assert
        assertThat(PERSON)
                .usingRecursiveComparison()
                .withEqualsForFields(equals, "addresses[0].city")
                .isEqualTo(expectedPerson);
    }

    @ParameterizedTest
    @MethodSource("personProvider")
    void withErrorMessageForTypeTest(Person expectedPerson) {
        // Assert
        assertThat(PERSON)
                .usingRecursiveComparison()
                .withErrorMessageForType(" >>> Instant is incorrect :/ <<<", Instant.class)
                .isEqualTo(expectedPerson);
    }

    @ParameterizedTest
    @MethodSource("personProvider")
    void withErrorMessageForFieldsTest(Person expecedPerson) {
        // Assert
        assertThat(PERSON)
                .usingRecursiveComparison()
                .withErrorMessageForFields("Fields dateOfBirth is incorrect :/ ", "dateOfBirth")
                .isEqualTo(expecedPerson);
    }

    @Test
    void usingRecursiveFieldByFieldElementComparatorTest() {
        record Person(String name, boolean hasPhd) {

        }

        record Doctor(String name, boolean hasPhd) {

        }

        Doctor drSheldon = new Doctor("Sheldon Cooper", true);
        Doctor drLeonard = new Doctor("Leonard Hofstadter", true);
        Doctor drRaj = new Doctor("Raj Koothrappali", true);

        Person sheldon = new Person("Sheldon Cooper", false);
        Person leonard = new Person("Leonard Hofstadter", false);
        Person raj = new Person("Raj Koothrappali", false);

        List<Doctor> doctors = List.of(drSheldon, drLeonard, drRaj);
        List<Person> people = List.of(sheldon, leonard, raj);

        RecursiveComparisonConfiguration configuration = RecursiveComparisonConfiguration.builder()
                .withIgnoredFields("hasPhd")
                .build();

        // Assert
        assertThat(doctors).usingRecursiveFieldByFieldElementComparator(configuration)
                .isEqualTo(people);
    }


    @Test
    void withIntrospectionStrategyTest() {

        record Author(String firstName, String LAST_NAME, int age, String profileUrl) {
        }

        record Book(String title, Author author) {
        }

        record AuthorDto(String first_name, String last_name, int age, String profile_url) {
        }

        record BookDto(String title, AuthorDto author) {
        }

        Author martinFowler = new Author("Martin", "Fowler", 58, "https://www.thoughtworks.com/profiles/leaders/martin-fowler");
        Book refactoring = new Book("Refactoring", martinFowler);

        AuthorDto martinFowlerDto = new AuthorDto("Martin", "Fowler", 58, "https://www.thoughtworks.com/profiles/leaders/martin-fowler");
        BookDto refactoringDto = new BookDto("Refactoring", martinFowlerDto);

        RecursiveComparisonIntrospectionStrategy comparingSnakeOrCamelCaseFields = new ComparingSnakeOrCamelCaseFields();

        // Assert
        assertThat(refactoring).usingRecursiveComparison()
                .withIntrospectionStrategy(comparingSnakeOrCamelCaseFields)
                .isEqualTo(refactoringDto);
    }

}
