package pl.pb.assertjexample;

import org.assertj.core.api.Condition;
import org.assertj.core.data.Index;
import org.junit.jupiter.api.Test;
import pl.pb.assertjexample.model2.Person;
import pl.pb.assertjexample.model2.Player;
import pl.pb.assertjexample.model2.Team;

import java.util.Arrays;
import java.util.List;

import static java.time.Instant.parse;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.in;
import static org.assertj.core.api.AssertionsForClassTypes.tuple;
import static pl.pb.assertjexample.model2.TestDataFactory.createPerson;
import static pl.pb.assertjexample.model2.TestDataFactory.createPlayer;
import static pl.pb.assertjexample.model2.TestDataFactory.createUser;

class CollectionsContainsTest {

    public static final Person JOHN = createPerson("John", "Smith", "1985-06-15T08:30:00.00Z", "New York", "5th Avenue", "10001");
    public static final Person EMILY = createPerson("Emily", "Johnson", "1990-12-10T14:45:00.00Z", "Los Angeles", "Sunset Boulevard", "90001");
    public static final Person MICHAEL = createPerson("Michael", "Brown", "1975-03-25T09:15:00.00Z", "Chicago", "Wacker Drive", "60601");
    public static final Person SARAH = createPerson("Sarah", "Williams", "1988-08-05T17:20:00.00Z", "Houston", "Main Street", "77001");
    private List<Person> persons = List.of(JOHN, EMILY, MICHAEL, SARAH);

    public static final Player johnsmithPlayer = createPlayer(createUser("johnsmith", "John", "Smith", true), 123);
    public static final Player emilyjohnsonPlayer = createPlayer(createUser("emilyjohnson", "Emily", "Johnson", true), 2);
    public static final Player michaelbrownPlayer = createPlayer(createUser("michaelbrown", "Michael", "Brown", true), 22);
    public static final Player sarahwilliamsPlayer = createPlayer(createUser("sarahwilliams", "Sarah", "Williams", true), 143);
    private List<Player> players = List.of(johnsmithPlayer, emilyjohnsonPlayer, michaelbrownPlayer, sarahwilliamsPlayer);

    public static final Team teamA = new Team("A", List.of(johnsmithPlayer, emilyjohnsonPlayer));
    public static final Team teamB = new Team("B", List.of(michaelbrownPlayer, sarahwilliamsPlayer));
    private List<Team> teams = List.of(teamA, teamB);


    @Test
    void containsTest() {
        Person expectedJohn = JOHN;
        Person expectedTaylor = EMILY;

        // Assert
        assertThat(persons)
                .contains(expectedJohn)
                .contains(expectedTaylor)
                //OR
                .contains(expectedJohn, expectedTaylor)
                //OR
                .contains(expectedJohn, Index.atIndex(0));
    }

    @Test
    void containsOnlyTest() {
        // Assert
        assertThat(persons)
                .containsOnly(JOHN, EMILY, MICHAEL, SARAH);
    }

    @Test
    void containsSequenceTest() {
        // Assert
        assertThat(persons).containsSubsequence(JOHN, EMILY, MICHAEL);
    }

    @Test
    void containsSubsequenceTest() {
        // Assert
        assertThat(persons).containsSubsequence(JOHN, EMILY, MICHAEL);
    }

    @Test
    void containsAnyOfTest() {
        // Assert
        assertThat(persons).containsAnyOf(JOHN);
    }


    @Test
    void filteredOnTest() {
        // Assert
        assertThat(persons)
                .filteredOn(person -> person.dateOfBirth().isAfter(parse("1985-06-15T08:30:00.00Z")))
                .containsOnly(EMILY, SARAH);
    }

    @Test
    void filteredOnPropertyOrFieldTest() {
        // Assert
        assertThat(persons)
                .filteredOn("lastname", "Johnson")
                .containsOnly(EMILY);
    }

    //
    @Test
    void filteredOnPropertyOrField2Test() {
        // Assert
        assertThat(persons)
                .filteredOn("dateOfBirth", in(parse("1985-06-15T08:30:00.00Z"), parse("1975-03-25T09:15:00.00Z")))
                .containsOnly(JOHN, MICHAEL);
    }

    @Test
    void filteredOnConditionTest() {
        Condition<Person> condition = new Condition<Person>(person -> person.dateOfBirth().isBefore(parse("1985-06-15T08:30:00.00Z")), "dateOfBirth");

        // Assert
        assertThat(persons)
                .filteredOn(condition)
                .containsOnly(MICHAEL);
    }

    @Test
    void filteredOnNullTest() {
        Person personWithUnknownName = createPerson(null, "Freeman", "1985-06-15T08:30:00.00Z", "New York", "5th Avenue", "10001");
        List<Person> people = List.of(MICHAEL, SARAH, personWithUnknownName);

        // Assert
        assertThat(people)
                .filteredOnNull("firstname")
                .contains(personWithUnknownName);
    }

    @Test
    void filteredOnAssertionsTest() {
        // Assert
        assertThat(persons)
                .filteredOnAssertions(person -> {
                    assertThat(person.dateOfBirth()).isBefore(parse("1985-06-15T08:30:00.00Z"));
                })
                .containsOnly(MICHAEL);
    }

    @Test
    void extractingByFieldsOrMethodTest() {
        // Assert
        assertThat(players)
                .extracting("user.nickname")
                .containsOnly(
                        johnsmithPlayer.user().nickname(),
                        emilyjohnsonPlayer.user().nickname(),
                        michaelbrownPlayer.user().nickname(),
                        sarahwilliamsPlayer.user().nickname());

        // Assert
        assertThat(players)
                .extracting("user.firstname")
                .containsOnly(JOHN.firstname(),
                        EMILY.firstname(),
                        MICHAEL.firstname(),
                        SARAH.firstname()
                );
    }

    @Test
    void extractingByLambdaTest() {
        // Assert
        assertThat(players)
                .extracting(player -> player.user().nickname())
                .containsOnly(johnsmithPlayer.user().nickname(),
                        emilyjohnsonPlayer.user().nickname(),
                        michaelbrownPlayer.user().nickname(),
                        sarahwilliamsPlayer.user().nickname());
    }

    @Test
    void extractingMultipleFieldsTest() {
        // Assert
        assertThat(players)
                .extracting("user.nickname", "user.firstname", "user.lastname")
                .containsOnly(
                        tuple("johnsmith", "John", "Smith"),
                        tuple("emilyjohnson", "Emily", "Johnson"),
                        tuple("michaelbrown", "Michael", "Brown"),
                        tuple("sarahwilliams", "Sarah", "Williams")
                );

        // OR
        // Assert
        assertThat(players)
                .extracting(player -> player.user().nickname(),
                        player -> player.user().firstname(),
                        player -> player.user().lastname())
                .containsOnly(
                        tuple("johnsmith", "John", "Smith"),
                        tuple("emilyjohnson", "Emily", "Johnson"),
                        tuple("michaelbrown", "Michael", "Brown"),
                        tuple("sarahwilliams", "Sarah", "Williams")
                );
    }

    @Test
    void extractingByMapTest() {
        // Assert
        assertThat(players)
                .map(player -> String.format("%s with age: %s", player.user().nickname(), player.score()))
                .containsOnly("johnsmith with age: 123",
                        "emilyjohnson with age: 2",
                        "michaelbrown with age: 22",
                        "sarahwilliams with age: 143");
    }

    @Test
    void flatExtractingTest() {
        // Assert
        assertThat(teams)
                .flatExtracting("players")
                .containsOnly(johnsmithPlayer, emilyjohnsonPlayer, michaelbrownPlayer, sarahwilliamsPlayer);

        // Assert
        assertThat(teams)
                .flatExtracting(Team::players)
                .containsOnly(johnsmithPlayer, emilyjohnsonPlayer, michaelbrownPlayer, sarahwilliamsPlayer);
    }

    @Test
    void usingElementComparatorTest() {
        List<Player> playersAsArray = Arrays.asList(sarahwilliamsPlayer, johnsmithPlayer, michaelbrownPlayer, emilyjohnsonPlayer);

        // Assert
        assertThat(playersAsArray)
                .usingElementComparator((o1, o2) -> {
                    return o1.user().nickname().compareTo(o2.user().nickname());
                })
                .containsAll(List.of(emilyjohnsonPlayer, johnsmithPlayer, michaelbrownPlayer, sarahwilliamsPlayer));
    }
}
