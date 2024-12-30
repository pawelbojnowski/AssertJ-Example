package pl.pb.assertjexample.model2;

import java.time.Instant;
import java.util.List;

public class TestDataFactory {

    public static Person createDefaultPerson() {
        return createPerson("Matt", "Deep", Instant.parse("1965-01-01T01:01:01.00Z"), List.of(createDefaultAddress()));
    }

    public static Person createPerson(String firstname, String lastname, Instant parse, List<Address> address) {
        return new Person(firstname, lastname, parse, address);
    }

    public static Person createPerson(String firstname, String lastname, String parse, String city, String street, String postalCode) {
        return new Person(firstname, lastname, Instant.parse(parse), List.of(createAddress(city, street, postalCode)));
    }

    public static Address createDefaultAddress() {
        return createAddress("New York", "Broadway", "10012");
    }

    public static Address createAddress(String city, String street, String postalCode) {
        return new Address(city, street, postalCode);
    }

    public static Player createDefaultPlayer() {
        return createPlayer(createDefaultUser(), 100);
    }

    public static Player createPlayer(User defaultUser, int score) {
        return new Player(defaultUser, score);
    }

    public static User createDefaultUser() {
        return createUser("thomdeep", "Thom", "Deep", true);
    }

    public static User createUser(String nickname, String firstname, String lastname, boolean isActive) {
        return new User(nickname, firstname, lastname, isActive);
    }


}
