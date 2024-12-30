package pl.pb.assertjexample;

import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;
import pl.pb.assertjexample.model2.Person;
import pl.pb.assertjexample.model2.TestDataFactory;

import java.time.Instant;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static pl.pb.assertjexample.model2.TestDataFactory.createAddress;
import static pl.pb.assertjexample.model2.TestDataFactory.createDefaultAddress;
import static pl.pb.assertjexample.model2.TestDataFactory.createPerson;


class CollectionsTest {

    private static final Person PERSON_1 = TestDataFactory.createDefaultPerson();
    private static final Person PERSON_2 = createPerson("Matt", "Deep", Instant.parse("1965-01-01T01:01:01.00Z"), List.of(createAddress("Warsaw", "Nowowiejska 3", "01-001")));
    private static final Person PERSON_3 = createPerson("Jhon", "Deep", Instant.parse("1965-02-02T02:02:02.00Z"), List.of(createAddress("Warsaw", "Nowowiejska 3", "01-001")));
    private static final Person PERSON_4 = createPerson("Nick", "Deep", Instant.parse("1965-02-02T02:02:02.00Z"), List.of(createDefaultAddress()));

    private List<Person> persons = List.of(PERSON_1, PERSON_2, PERSON_3, PERSON_4);

    @Test
    void doesNotContainTest() {
        Person notExistingPerson = createPerson("Alex", "Gatuzo", Instant.parse("1965-02-02T02:02:02.00Z"), List.of(createDefaultAddress()));

        // Assert
        assertThat(persons).doesNotContain(notExistingPerson);
    }

    @Test
    void anySatisfyTest() {
        // Assert
        assertThat(persons)
                // will pass
                .anySatisfy(person -> {
                    assertThat(person.firstname()).isEqualTo(PERSON_1.firstname());
                    assertThat(person.dateOfBirth()).isEqualTo(PERSON_1.dateOfBirth());
                });
    }

    @Test
    void allSatisfyTest() {
        // Assert
        assertThat(persons)
                .anySatisfy(person -> {
                    assertThat(person.firstname()).isEqualTo("Matt");
                });
    }

    @Test
    void noneSatisfyTest() {
        // Assert
        assertThat(persons)
                .noneSatisfy(person -> {
                    assertThat(person.firstname()).isEqualTo("Kris");
                });
    }

    @Test
    void allMatchTest() {
        // Assert
        assertThat(persons)
                .allMatch(character -> character.dateOfBirth().isAfter(Instant.parse("1962-02-02T02:02:02.00Z")), "Wrong age")
                .anyMatch(character -> StringUtils.isNotBlank(character.lastname()))
                .noneMatch(character -> character.firstname().equals("Tom"));
    }

    @Test
    void singleElementTest() {
        // Assert
        assertThat(persons).first().isEqualTo(PERSON_1);
        assertThat(persons).element(2).isEqualTo(PERSON_3);
        assertThat(persons).last().isEqualTo(PERSON_4);
    }
}
