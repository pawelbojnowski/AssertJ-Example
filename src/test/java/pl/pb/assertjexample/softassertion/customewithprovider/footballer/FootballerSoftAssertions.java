package pl.pb.assertjexample.softassertion.customewithprovider.footballer;

import org.assertj.core.api.AbstractSoftAssertions;

public class FootballerSoftAssertions extends AbstractSoftAssertions {
    public FootballerAssert assertThat(Footballer actual) {
        return proxy(FootballerAssert.class, Footballer.class, actual);
    }
}