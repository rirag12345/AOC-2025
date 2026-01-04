package org.rirag12345.day06;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigInteger;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class DaySixTest {

    @Test
    void solve() throws IOException {
        assertThat(DaySix.solve(), is(new BigInteger("9029931401920")));
    }
}
