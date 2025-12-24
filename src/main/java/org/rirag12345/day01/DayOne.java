import static java.lang.IO.println;

// Advent of Code - Day 1

// https://adventofcode.com/2025/day/1

// the solution for part 1 is 1141

// the solution for part 2 is

void main() {
	solve();
}

public void solve() {
	try {
		var util = new ModuloUtil(100, 50);
		var lines = Files.readAllLines(Path.of("src/main/java/day01/dummyInput.txt"));

		for (var line : lines) {
			var sign = line.charAt(0) == 'L' ? -1 : 1;
			var number = Integer.parseInt(line.substring(1));

			var temp = util.getCounter();
			// the count is increased in the util class when we pass 0
			util.rotate(number * sign);
			/*
			this is essentially only for part 1, part 2 asks for how many times we "pass" 0,
			 not how many times we land on it
			if (util.getValue() == 0) {
				counter++;
			}
			 */
			System.out.printf("The dial is rotated %s to point at %d%s \n", line, util.getValue(),
					util.getValue() != 0 && temp != util.getCounter() ?
							";during this rotation it points at zero " + (util.getCounter() - temp) + " times" : "");
		}

		println("Counter: " + util.getCounter());
	} catch (IOException e) {
		println("An error occurred while reading the file: " + e.getMessage());
	}
}

public static class ModuloUtil {
	private final int modus;
	private int value;
	private int counter;

	public ModuloUtil(int modus, int initialValue) {
		this.value = initialValue;
		this.modus = modus;
		this.counter = 0;
	}

	public int getValue() {
		return value;
	}

	public int getCounter() {
		return counter;
	}

	// rotates the dial by the given number of steps
	// initially I tried to do this without a loop using modulo arithmetic,
	// but it got too complicated to track how many times we pass 0 since I failed some edge cases
	// and this way is simpler to understand
	public void rotate(int steps) {
		for (var i = 0; i < Math.abs(steps); i++) {
			if (steps > 0) {
				value++;
			} else {
				value--;
			}

			// this handles the wrapping around to avoid negatives (this is purely for readability)
			if (value < 0) {
				value = (value + modus) % modus;
			}

			if (value % modus == 0) {
				value = 0;
				counter++;
			}
		}
	}
}
