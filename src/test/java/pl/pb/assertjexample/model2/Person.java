package pl.pb.assertjexample.model2;

import java.time.Instant;
import java.util.List;

public record Person(String firstname, String lastname, Instant dateOfBirth, List<Address> addresses) {
}
