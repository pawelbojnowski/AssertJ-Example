package pl.pb.assertjexample.softassertion.customewithprovider.footballer;

import org.assertj.core.api.SoftAssertionsProvider;

public interface FootballerSoftAssertionsProvider extends SoftAssertionsProvider {

    default FootballerAssert assertFootballer(Footballer actual) {
        return proxy(FootballerAssert.class, Footballer.class, actual);
    }
}
