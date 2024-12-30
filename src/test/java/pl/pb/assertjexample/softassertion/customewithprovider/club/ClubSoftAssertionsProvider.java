package pl.pb.assertjexample.softassertion.customewithprovider.club;

import org.assertj.core.api.SoftAssertionsProvider;

public interface ClubSoftAssertionsProvider extends SoftAssertionsProvider {

    default ClubAssert assertClub(Club actual) {
        return proxy(ClubAssert.class, Club.class, actual);
    }
}
