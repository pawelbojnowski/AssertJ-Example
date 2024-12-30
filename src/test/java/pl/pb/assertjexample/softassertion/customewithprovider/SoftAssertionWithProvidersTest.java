package pl.pb.assertjexample.softassertion.customewithprovider;


import org.assertj.core.api.junit.jupiter.SoftAssertionsExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pl.pb.assertjexample.softassertion.customewithprovider.club.Club;
import pl.pb.assertjexample.softassertion.customewithprovider.footballer.Footballer;


@ExtendWith(SoftAssertionsExtension.class)
class SoftAssertionWithProvidersTest {

    @Test
    void assertSoftAssertionWithProviders() {
        LeagueSoftAssertions leagueSoftAssertions = new LeagueSoftAssertions();

        // Assert
        leagueSoftAssertions
                .assertClub(new Club("Rea Madrid", "Spain", "Madrid"))
                .isNameNotNull()
                .isCountryNotNull()
                .isCityNotNull();

        leagueSoftAssertions
                .assertClub(new Club(null, "Spain", "Madrid"))
                .isNameNotNull()
                .isCountryNotNull()
                .isCityNotNull();

        leagueSoftAssertions
                .assertClub(new Club("Rea Madrid", null, "Madrid"))
                .isNameNotNull()
                .isCountryNotNull()
                .isCityNotNull();

        leagueSoftAssertions
                .assertClub(new Club("Rea Madrid", "Spain", null))
                .isNameNotNull()
                .isCountryNotNull()
                .isCityNotNull();

        leagueSoftAssertions.assertFootballer(new Footballer("Raul Gonzales", 7, "Spain"))
                .isNameNotNull()
                .isCountryNotNull()
                .isNumberInAcceptedRange();

        leagueSoftAssertions.assertFootballer(new Footballer(null, 8, "Spain"))
                .isNameNotNull()
                .isCountryNotNull()
                .isNumberInAcceptedRange();

        leagueSoftAssertions.assertFootballer(new Footballer("Gavi", 0, "Spain"))
                .isNameNotNull()
                .isCountryNotNull()
                .isNumberInAcceptedRange();

        leagueSoftAssertions.assertFootballer(new Footballer("Gavi", 100, "Spain"))
                .isNameNotNull()
                .isCountryNotNull()
                .isNumberInAcceptedRange();

        leagueSoftAssertions.assertFootballer(new Footballer("Gavi", 99, null))
                .isNameNotNull()
                .isCountryNotNull()
                .isNumberInAcceptedRange();

        leagueSoftAssertions.assertAll();

        /*
        org.assertj.core.error.AssertJMultipleFailuresError:
        Multiple Failures (7 failures)
        -- failure 1 --
        Multiple Failures (1 failure)
        -- failure 1 --
        Expecting not blank but was: null
        at ClubAssert.lambda$isNameNotNull$0(ClubAssert.java:15)
        -- failure 2 --
        Multiple Failures (1 failure)
        -- failure 1 --
        Expecting not blank but was: null
        at ClubAssert.lambda$isCountryNotNull$1(ClubAssert.java:21)
        -- failure 3 --
        Multiple Failures (1 failure)
        -- failure 1 --
        Expecting not blank but was: null
        at ClubAssert.lambda$isCityNotNull$2(ClubAssert.java:27)
        -- failure 4 --
        Multiple Failures (1 failure)
        -- failure 1 --
        Expecting not blank but was: null
        at FootballerAssert.lambda$isNameNotNull$0(FootballerAssert.java:15)
        -- failure 5 --
        Multiple Failures (1 failure)
        -- failure 1 --
        Expecting actual:
          0
        to be between:
          [1, 99]
        at FootballerAssert.lambda$isNumberInAcceptedRange$1(FootballerAssert.java:21)
        -- failure 6 --
        Multiple Failures (1 failure)
        -- failure 1 --
        Expecting actual:
          100
        to be between:
          [1, 99]
        at FootballerAssert.lambda$isNumberInAcceptedRange$1(FootballerAssert.java:21)
        -- failure 7 --
        Multiple Failures (1 failure)
        -- failure 1 --
        Expecting not blank but was: null
        at FootballerAssert.lambda$isCountryNotNull$2(FootballerAssert.java:27)
         */
    }

}
