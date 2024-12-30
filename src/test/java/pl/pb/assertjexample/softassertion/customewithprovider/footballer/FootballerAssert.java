package pl.pb.assertjexample.softassertion.customewithprovider.footballer;

import org.assertj.core.api.AbstractAssert;

import static org.assertj.core.api.Assertions.assertThat;

public class FootballerAssert extends AbstractAssert<FootballerAssert, Footballer> {

    public FootballerAssert(Footballer footballer) {
        super(footballer, FootballerAssert.class);
    }

    public FootballerAssert isNameNotNull() {
        return this.satisfies(footballer -> {
            assertThat(footballer.fullName()).isNotBlank();
        });
    }

    public FootballerAssert isNumberInAcceptedRange() {
        return this.satisfies(footballer -> {
            assertThat(footballer.number()).isBetween(1, 99);
        });
    }

    public FootballerAssert isCountryNotNull() {
        return this.satisfies(footballer -> {
            assertThat(footballer.country()).isNotBlank();
        });
    }
}