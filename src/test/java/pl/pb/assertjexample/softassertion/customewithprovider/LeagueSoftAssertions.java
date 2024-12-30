package pl.pb.assertjexample.softassertion.customewithprovider;

import org.assertj.core.api.SoftAssertions;
import pl.pb.assertjexample.softassertion.customewithprovider.club.ClubSoftAssertionsProvider;
import pl.pb.assertjexample.softassertion.customewithprovider.footballer.FootballerSoftAssertionsProvider;

public class LeagueSoftAssertions extends SoftAssertions implements ClubSoftAssertionsProvider, FootballerSoftAssertionsProvider {

}
