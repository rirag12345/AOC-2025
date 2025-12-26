package org.rirag12345.day03;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.IntStream;

public class DayThree {
	public static BigInteger solve() throws IOException {
		return Files.readAllLines(Path.of("src/main/java/org/rirag12345/day03/input.txt"))
				.stream()
				.map(DayThree::getLargest)
				.reduce(BigInteger.ZERO, BigInteger::add);
	}

	/**
	 * Sorts a given input String of numbers and returns the highest possible joltage
	 *
	 * @param input the input String e.g. "811111111111119"
	 * @return the highest possible value e.g. "89"
	 */
	private static BigInteger getLargest(String input) {
		var values = Arrays.stream(input.split(""))
				.map(Integer::parseInt)
				.toList();

		/*
		logic sufficient for part one:
		for (int i = 0; i < values.size() - 1; i++) {
			for (int j = i + 1; j < values.size(); j++) {
				var first = values.get(i);
				var second = values.get(j);
				// Combine the two highest digits into a two-digit number
				if ((first * 10 + second) > result) {
					result = first * 10 + second;
				}
			}
		}
		 */

		var result = new StringBuilder(12);
		// we are looking for the highest possible digit where K - 1 values are left in the String (K being the
		// length of the highest possible joltage we are looking for)
		for (int k = 12; k >= 1; k--) {
			var elementNeededAfter = k - 1;
			var limit = values.size() - elementNeededAfter;
			var indexOfMax = IntStream.range(0, limit).boxed().max(Comparator.comparing(values::get))
					.orElseThrow(() -> new IllegalStateException("No max found"));

			var localMax = values.get(indexOfMax);
			result.append(localMax);

			// shorten the list to only the elements after the found max
			values = values.subList(indexOfMax + 1, values.size());
		}

		return new BigInteger(result.toString());
	}
}
