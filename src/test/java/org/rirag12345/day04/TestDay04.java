package org.rirag12345.day04;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestDay04 {
	@Test
	public void testSolve() throws IOException {
		assertThat(DayFour.solve(), is(9024));
	}
}
