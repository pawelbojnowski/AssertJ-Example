package pl.pb.assertjexample.softassertion.customewithprovider.club;

import org.assertj.core.api.AbstractSoftAssertions;

public class ClubSoftAssertions extends AbstractSoftAssertions {
    public ClubAssert assertThat(Club actual) {
        return proxy(ClubAssert.class, Club.class, actual);
    }
}