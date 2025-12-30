package org.rirag12345.day05;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Gatherers;

public record DayFive() {
    public static BigInteger solve() throws IOException {
        var input = Files.readAllLines(Path.of("src/main/java/org/rirag12345/day05/input.txt"));

        var ranges = input.stream()
                .takeWhile(line -> !line.isBlank())
                .flatMap(e -> Arrays.stream(e.split("-")))
                .map(BigInteger::new)
                .gather(Gatherers.windowFixed(2))
                .sorted(Comparator.comparing(List::getFirst))
                .toList();

        return mergeIntervals(ranges).stream()
                .map(entry -> entry.getLast().subtract(entry.getFirst()).add(BigInteger.ONE))
                .reduce(BigInteger.ZERO, BigInteger::add);

		/*
		 sufficient for part one
		var ids = input.stream()
				.filter(line -> !line.isBlank() && !line.contains("-"))
				.map(BigInteger::new)
				.toList();


		return ids.stream().filter(id -> ranges.stream().anyMatch(list -> {
					var lowerLimit = list.get(0);
					var upperLimit = list.get(1);
					return id.compareTo(lowerLimit) > -1 && id.compareTo(upperLimit) < 1;
				})
		).map(_ -> BigInteger.ONE).reduce(BigInteger.ZERO, BigInteger::add);
		 */


		/*
		This should work mathematically but crashes due to an OOM Error, therefore we have to algorithmically merge
		the intervals.
		return ranges.stream()
				.map(pair -> {
					var first = pair.getFirst();
					var limit = pair.getLast();
					var result = new ArrayList<BigInteger>();
					for (var i = first; i.compareTo(limit) < 1; i = i.add(BigInteger.ONE)) {
						result.add(i);
					}
					return result;
				}).flatMap(List::stream)
				.distinct()
				.map(_ -> BigInteger.ONE)
				.reduce(BigInteger.ZERO, BigInteger::add);
		 */
    }

    private static List<List<BigInteger>> mergeIntervals(List<List<BigInteger>> input) {
        //sort intervals by start value


        var result = new ArrayList<List<BigInteger>>();

        //init start and end values
        var currentStart = input.getFirst().getFirst();
        var currentEnd = input.getFirst().getLast();

        for (int i = 1; i < input.size(); i++) {
            var nextInterval = input.get(i);
            var nextStart = nextInterval.getFirst();
            var nextEnd = nextInterval.getLast();

            //check if intervals overlap or are contiguous
            if (currentEnd.add(BigInteger.ONE).compareTo(nextStart) >= 0) {
                currentEnd = currentEnd.max(nextEnd);
            } else {
                result.add(List.of(currentStart, currentEnd));
                currentStart = nextStart;
                currentEnd = nextEnd;
            }
        }

        result.add(List.of(currentStart, currentEnd));
        return result;
    }
}
