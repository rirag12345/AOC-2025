package org.rirag12345.day05;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigInteger;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class DayFiveTest {

	@Test
	void solve() throws IOException {
		assertThat(DayFive.solve(), is(new BigInteger("365804144481581")));
	}
}