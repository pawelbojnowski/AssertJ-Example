package pl.pb.assertjexample.softassertion.custom;

import org.assertj.core.api.ObjectAssert;
import org.assertj.core.api.SoftAssertions;
import pl.pb.assertjexample.model2.Player;
import pl.pb.assertjexample.model2.User;

public class PlayerSoftAssertions extends SoftAssertions {

    public PlayerSoftAssertions() {
        super();
    }

    public ObjectAssert<User> isUserNotNull(Player player) {
        return this.assertThat(player.user()).isNotNull();
    }
}
