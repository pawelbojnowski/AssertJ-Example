package pl.pb.assertjexample.softassertion.custom;

import org.junit.jupiter.api.Test;
import pl.pb.assertjexample.model2.Player;
import pl.pb.assertjexample.model2.User;

import static pl.pb.assertjexample.model2.TestDataFactory.createDefaultPlayer;
import static pl.pb.assertjexample.model2.TestDataFactory.createDefaultUser;
import static pl.pb.assertjexample.model2.TestDataFactory.createPlayer;

class PlayerSoftAssertionsTest {

    @Test
    void softAssertionsTest() {
        Player player = createDefaultPlayer();
        User expectedUser = createDefaultUser();

        // Assert
        PlayerSoftAssertions softly = new PlayerSoftAssertions();
        softly.assertThat(player.user()).isEqualTo(expectedUser);
        softly.assertThat(player.score()).isEqualTo(100);
        softly.isUserNotNull(player);
        softly.assertAll();
    }

    @Test
    void failSoftAssertionsTest() {
        Player player = createPlayer(null, 100);
        User expectedUser = createDefaultUser();

        PlayerSoftAssertions softly = new PlayerSoftAssertions();
        softly.assertThat(player.user()).isEqualTo(expectedUser);
        softly.assertThat(player.score()).isEqualTo(99);
        softly.isUserNotNull(player);
        softly.assertAll();
    }

}
