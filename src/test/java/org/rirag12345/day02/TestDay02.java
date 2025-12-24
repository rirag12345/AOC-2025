package org.rirag12345.day02;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigInteger;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class TestDay02 {
	@Test
	public void testSolve() throws IOException {
		assertThat(DayTwo.solve(), is(new BigInteger("25663320831")));
	}
}
