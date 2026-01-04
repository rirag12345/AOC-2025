package org.rirag12345.day06;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public record DaySix() {
    public static BigInteger solve() throws IOException {
        var result = BigInteger.ZERO;
        List<String> input = Files.readAllLines(Path.of("src/main/java/org/rirag12345/day06/input.txt"));
        List<List<Character>> transposed = transpose(input);

        var currrentBlockNumbers = new ArrayList<BigInteger>();
        //Default operation, since the program will crash for null values here
        Operation currentBlockOperation = Operation.ADD;

        for (int i = transposed.size() - 1; i >= 0; i--) {
            var column = transposed.get(i);
            // if the entire column is empty we can skip it
            var isEmpty = column.stream().allMatch(c -> c == ' ');

            if (isEmpty) {
                if (!currrentBlockNumbers.isEmpty()) {
                    result = result.add(calculateBlock(currrentBlockNumbers, currentBlockOperation));
                    // reset for the next block
                    currrentBlockNumbers.clear();
                    currentBlockOperation = Operation.ADD;
                }
                continue;
            }

            // Determine the operation for this column
            switch (column.getLast()) {
                case '+' -> currentBlockOperation = Operation.ADD;
                case '*' -> currentBlockOperation = Operation.MULTIPLY;
            }

            // Process the numbers in this column
            var sb = new StringBuilder();
            for (Character c : column) {
                if (Character.isDigit(c)) {
                    sb.append(c);
                }
            }

            if (!sb.isEmpty()) {
                currrentBlockNumbers.add(new BigInteger(sb.toString()));
            }
        }
        if (!currrentBlockNumbers.isEmpty()) {
            result = result.add(calculateBlock(currrentBlockNumbers, currentBlockOperation));
        }
        return result;
    }

    private static BigInteger calculateBlock(ArrayList<BigInteger> numbers, Operation operation) {
        if (numbers.isEmpty()) return BigInteger.ZERO;

        BigInteger result = null;
        for (BigInteger number : numbers) {
            if (result == null) {
                result = number;
                continue;
            }
            switch (operation) {
                case ADD -> result = result.add(number);
                case MULTIPLY -> result = result.multiply(number);
            }
        }
        return result;
    }

    // Transposes the input list of strings into a list of lists of characters so they can be processed column-wise
    public static List<List<Character>> transpose(List<String> input) {
        var result = new ArrayList<List<Character>>();

        // we need Column-Major order iteration here
        int maxWidth = input.stream().mapToInt(String::length).max().orElse(0);

        for (int column = 0; column < maxWidth; column++) {
            var temp = new ArrayList<Character>();
            for (String row : input) {
                if (row.length() > column) {
                    temp.add(row.charAt(column));
                } else {
                    temp.add(' ');
                }
            }
            result.add(temp);
        }
        return result;
    }

    private enum Operation {
        ADD, MULTIPLY
    }
}
