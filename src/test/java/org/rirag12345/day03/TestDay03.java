package org.rirag12345.day03;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class TestDay03 {
	@Test
	public void testSolve() throws IOException {
		var result = DayThree.solve();
		assertThat(result, is(16973));
	}
}
