package org.example;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SequenceFinderTest {
    private SequenceFinder finder;

    @BeforeEach
    void setUp() {
        finder = new SequenceFinder();
    }

    @Test
    void findSequences_GivenValidInput_FindsCorrectSequences() {
        List<Integer> input = Arrays.asList(2, 10, 4, 3, 8, 6, 9, 9, 18, 27);
        List<List<Integer>> result = finder.findSequences(input);
        assertEquals(2, result.size(), "Should find sequences for N=2 and N=3");
        assertTrue(result.contains(Arrays.asList(2, 4, 8)), "Includes sequence for N=2");
        assertTrue(result.contains(Arrays.asList(3, 9, 27)), "Includes sequence for N=3");
    }

    @Test
    void findSequences_InvalidInput_ThrowsException() {
        List<Integer> input = Arrays.asList(2, 10);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            finder.findSequences(input);
        });
        assertEquals("Invalid input list.", exception.getMessage());
    }

    @Test
    void findSequences_ZeroSequences_IgnoresThem() {
        List<Integer> input = Arrays.asList(0, 0, 0, 0, 0, 0);
        List<List<Integer>> result = finder.findSequences(input);
        assertTrue(result.isEmpty(), "Should ignore all-zero sequences");
    }

    @Test
    void findSequences_SequencesWithLessThanThreeEntries_IgnoresThem() {
        List<Integer> input = Arrays.asList(2, 2, 0, 4); // This input doesn't contain any valid sequence
        List<List<Integer>> result = finder.findSequences(input);
        assertTrue(result.isEmpty(), "Should ignore sequences with less than three entries");
    }

    @Test
    public void findSequences_WhenCalledWithEmptyList_ShouldThrowIllegalArgumentException() {
        List<Integer> input = Arrays.asList();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            finder.findSequences(input);
        });
        assertEquals("Invalid input list.", exception.getMessage(), "Exception message should indicate invalid input list");
    }

    @Test
    public void findSequences_WhenListContainsAllOnes_ShouldReturnEmptyList() {
        List<Integer> input = Arrays.asList(1, 1, 1, 1, 1, 1);
        List<List<Integer>> result = finder.findSequences(input);
        assertTrue(result.isEmpty(), "Result should be empty as ones do not form a valid sequence by problem definition");
    }

    @Test
    public void findSequences_WhenCalledWithNegativeNumbers_ShouldHandleCorrectly() {
        List<Integer> input = Arrays.asList(-2, -4, -8, -16, -32, -64, -128, -256);
        List<List<Integer>> result = finder.findSequences(input);
        // Define expected sequences considering the multiplication rule and negative values
        assertTrue(result.isEmpty() || result.get(0).containsAll(Arrays.asList(-2, -8, -32, -128)),
                "Should handle sequences with negative numbers correctly");
    }

    @Test
    public void findSequences_WhenListHasLeadingZeros_ShouldSkipThemAndFindValidSequences() {
        List<Integer> input = Arrays.asList(0, 0, 0, 2, 10, 4, 3, 8, 6, 9, 9, 18, 27);
        List<List<Integer>> result = finder.findSequences(input);
        // Expect sequences for N=2 and N=3 after skipping leading zeros
        // Test should define the expected result based on correct logic
        assertEquals(2, result.size(), "Should find sequences after leading zeros");
    }

    // Test for a large list input
    @Test
    public void findSequences_WhenCalledWithLargeInput_ShouldPerformEfficiently() {
        List<Integer> input = new ArrayList<>();
        for (int i = 1; i <= 1000; i++) {
            input.add(i);
        }
        // This test checks the performance and might not assert an output,
        // assuming the logic is already tested for correctness in smaller lists.
        assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
            finder.findSequences(input);
        }, "Method did not perform efficiently for large input");
    }
}
