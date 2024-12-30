package pl.pb.assertjexample.abstractassertion;

import org.assertj.core.api.AbstractAssert;
import pl.pb.assertjexample.model2.Address;
import pl.pb.assertjexample.model2.Person;

import static org.assertj.core.api.Assertions.assertThat;

public class PersonAssert extends AbstractAssert<PersonAssert, Person> {

    private PersonAssert(Person person) {
        super(person, PersonAssert.class);
    }

    public static PersonAssert assertPerson(Person actual) {
        return new PersonAssert(actual);
    }

    public PersonAssert addressIsNotEmpty() {
        return this.satisfies(person -> {
            assertThat(person.addresses()).isNotNull();
            assertThat(person.addresses()).isNotEmpty();
        });
    }

    public PersonAssert isFirstname(String firstname) {
        return this.satisfies(person -> {
            assertThat(person.firstname()).isEqualTo(firstname);
        });
    }

    public PersonAssert isLastname(String lastname) {
        return this.satisfies(person -> {
            assertThat(person.lastname()).isEqualTo(lastname);
        });
    }

    public PersonAssert hasAddress(Address address) {
        return this.satisfies(person -> {
            assertThat(person.addresses()).contains(address);
        });
    }

}