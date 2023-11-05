package org.example;

import java.util.List;


public class Main {
    public static void main(String[] args) {
        SequenceFinder finder = new SequenceFinder();
        List<Integer> input = List.of(2, 10, 4, 3, 8, 6, 9, 9, 18, 27, 1, 52, 81, 10, 1, 0, 2, 0, 4, 0, 8, 0, 16, 0, 32, 0, 64, 0, 128, 2, 10, 4, 3, 8, 6, 9, 9, 18, 27, 1, 52, 81, 10, 100, 50, 0, 0, 0, 0, 0, 0);
        List<List<Integer>> sequences = finder.findSequences(input);

        // Printing sequences and their sums
        int totalSum = 0;
        for (List<Integer> sequence : sequences) {
            int sum = sequence.stream().mapToInt(Integer::intValue).sum();
            System.out.println("Sequence (N=" + (sequence.get(1) / sequence.get(0)) + "): " + sequence + " | Sum: " + sum);
            totalSum += sum;
        }
        System.out.println("Total sum of all sequences: " + totalSum);
    }
}