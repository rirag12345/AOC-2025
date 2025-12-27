package org.rirag12345.day04;

import java.io.IOException;
import java.nio.CharBuffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.stream.Stream;

public record DayFour() {
	public static long solve() throws IOException {
		char[][] rows = Files.readAllLines(Path.of("src/main/java/org/rirag12345/day04/input.txt"))
				.stream()
				.map(String::toCharArray)
				.toArray(char[][]::new);

		char[][] resultingRows = getResult(Stream.of(rows).map(char[]::clone).toArray(char[][]::new));

		var startCount = Arrays.stream(rows).map(row -> CharBuffer
				.wrap(row).chars().filter(e -> e == '@').count()).reduce(0L, Long::sum);

		var endCount = Arrays.stream(resultingRows).map(row -> CharBuffer
				.wrap(row).chars().filter(e -> e == '@').count()).reduce(0L, Long::sum);

		return startCount - endCount;
	}

	/**
	 * Recursively process the rows to find and remove rolls that can be accessed
	 * @param rows the current state of the rows to operate on
	 * @return the final state of the rows after no more rolls can be accessed
	 */
	private static char[][] getResult(char[][] rows) {
		var count = 0;

		for (int i = 0; i < rows.length; i++) {

			for (int j = 0; j < rows[i].length; j++) {

				if (rows[i][j] == '@') {
					var topRow = 0;
					var middleRow = 0;
					var bottomRow = 0;

					if (i - 1 >= 0) {
						topRow = checkForRolls(rows[i - 1], j);
					}

					if (i + 1 < rows.length) {
						bottomRow = checkForRolls(rows[i + 1], j);
					}

					middleRow = checkForRolls(rows[i], j) - 1; // exclude the current "@"

					if ((topRow + middleRow + bottomRow) < 4) {
						rows[i][j] = '.'; // "remove" the roll
						count++;
					}
				}
			}
		}
		return count > 0 ? getResult(rows) : rows;
	}

	/**
	 * Check the given row at the given index and its immediate neighbors for rolls
	 * @param row the row to check
	 * @param index the index to check (meaning the current position of the iteration
	 * @return the number of rolls found at the given index and its immediate neighbors
	 */
	private static int checkForRolls(char[] row, int index) {
		var rolls = row[index] == '@' ? 1 : 0;

		if ((index - 1) >= 0) {
			rolls += row[index - 1] == '@' ? 1 : 0;
		}

		if ((index + 1) < row.length) {
			rolls += row[index + 1] == '@' ? 1 : 0;
		}

		return rolls;
	}
}
