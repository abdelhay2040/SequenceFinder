package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SequenceFinder {

    /**
     * Finds sequences in the input list based on the problem's constraints.
     *
     * @param input List of integers to find sequences in.
     * @return List of valid sequences.
     */
    public List<List<Integer>> findSequences(List<Integer> input) {
        if (input == null || input.size() < 3) {
            throw new IllegalArgumentException("Invalid input list.");
        }

        List<List<Integer>> result = new ArrayList<>();
        for (int step = 2; step < input.size(); step++) {
            findSequencesForStep(input, step).ifPresent(result::add);
        }
        return result;
    }

    /**
     * Tries to find a valid sequence for a given step value.
     *
     * @param input List of integers to find a sequence in.
     * @param step  The step value or N.
     * @return A valid sequence if found.
     */
    private java.util.Optional<List<Integer>> findSequencesForStep(List<Integer> input, int step) {
        for (int startIndex = 0; startIndex <= input.size() - 2 * step; startIndex++) {
            List<Integer> sequence = buildSequence(input, startIndex, step);
            if (isValidSequence(sequence)) {
                return java.util.Optional.of(sequence);
            }
        }
        return java.util.Optional.empty();
    }

    /**
     * Builds a sequence starting from a given index for a specific step.
     *
     * @param input       List of integers.
     * @param startIndex  Starting index for the sequence.
     * @param step        The step value or N.
     * @return A potential sequence.
     */
    private List<Integer> buildSequence(List<Integer> input, int startIndex, int step) {
        List<Integer> sequence = new ArrayList<>();
        int index = startIndex;
        while (index < input.size() && (sequence.isEmpty() || input.get(index) == step * sequence.get(sequence.size() - 1))) {
            sequence.add(input.get(index));
            index += step;
        }
        return sequence;
    }

    /**
     * Checks if a sequence is valid based on the problem's constraints.
     *
     * @param sequence Sequence to check.
     * @return True if the sequence is valid, otherwise false.
     */
    private boolean isValidSequence(List<Integer> sequence) {
        return sequence.size() >= 3 && sequence.stream().anyMatch(num -> num != 0);
    }


}
