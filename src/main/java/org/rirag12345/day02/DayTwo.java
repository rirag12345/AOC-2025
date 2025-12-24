package org.rirag12345.day02;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

public class DayTwo {
	// use BigInteger due to the domain of the values
	public static BigInteger solve() throws IOException {
		var result = BigInteger.ZERO;

		var ranges =
				Arrays.stream(Files.readString(Path.of("src/main/java/org/rirag12345/day02/input.txt")).split(",")).map(rangeString -> {
					// Split the range string into its two bounds
					// the first index is the lower bound, the second index is the upper bound
					return Arrays.stream(rangeString.split("-")).toList();
				}).toList();

		//now we have a list of ranges, we can iterate through them and check for invalid ids
		// an invalid id is one that is just id comprised of a sequence of numbers repeated twice
		for (var range : ranges) {
			var lowerBound = new BigInteger(range.get(0));
			var upperBound = new BigInteger(range.get(1));

			// Iterate through the range and add to the result
			while (lowerBound.compareTo(upperBound) <= 0) {
				var isDuplicate = checkDuplicate(lowerBound);

				if (isDuplicate) {
					result = result.add(lowerBound);
				}

				lowerBound = lowerBound.add(BigInteger.ONE);
			}
		}
		return result; //should be 8576933996
	}

	private static boolean checkDuplicate(BigInteger id) {
		var value = id.toString();

		// the pattern has to consist of a sequence of numbers repeated at least twice
		for (int i = 1; i <= value.length() / 2; i++) {

			// check if the length of the value is divisible by the current index
			if (value.length() % i == 0) {
				// construct the pattern by repeating the substring by the number of times it fits in the value
				var pattern = value.substring(0, i).repeat(value.length() / i);

				// check if the pattern matches the value
				if (pattern.equals(value)) {
					return true;
				}
			}
		}
		return false;
	}
}
