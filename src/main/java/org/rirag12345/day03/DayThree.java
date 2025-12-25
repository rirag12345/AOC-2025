package org.rirag12345.day03;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

public class DayThree {
	public static int solve() throws IOException {
		return Files.readAllLines(Path.of("src/main/java/org/rirag12345/day03/input.txt"))
				.stream()
				.map(DayThree::getLargest)
				.reduce(0, Integer::sum);
	}

	/**
	 * Sorts a given input String of numbers and returns the highest possible joltage
	 *
	 * @param input the input String e.g. "811111111111119"
	 * @return the highest possible value e.g. "89"
	 */
	private static int getLargest(String input) {
		var result = 0;
		var values = Arrays.stream(input.split(""))
				.map(Integer::parseInt)
				.toList();

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

		return result;
	}
}
