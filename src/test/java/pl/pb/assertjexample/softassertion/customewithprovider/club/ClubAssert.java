package pl.pb.assertjexample.softassertion.customewithprovider.club;

import org.assertj.core.api.AbstractAssert;

import static org.assertj.core.api.Assertions.assertThat;

public class ClubAssert extends AbstractAssert<ClubAssert, Club> {

    public ClubAssert(Club club) {
        super(club, ClubAssert.class);
    }

    public ClubAssert isNameNotNull() {
        return this.satisfies(club -> {
            assertThat(club.getName()).isNotBlank();
        });
    }

    public ClubAssert isCountryNotNull() {
        return this.satisfies(club -> {
            assertThat(club.getCountry()).isNotBlank();
        });
    }

    public ClubAssert isCityNotNull() {
        return this.satisfies(club -> {
            assertThat(club.getCity()).isNotBlank();
        });
    }
}