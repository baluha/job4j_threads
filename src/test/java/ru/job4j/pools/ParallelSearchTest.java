package ru.job4j.pools;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ParallelSearchTest {

    @Test
    void searchIntegerIndex() {
        Integer[] integers = new Integer[] {1, 5, 10, 15, 45, 65, 4, 84, 74, 63, 55, 41};
        assertThat(ParallelSearch.search(integers, 15)).isEqualTo(3);
    }

    @Test
    void searchCharIndex() {
        Character[] characters = new Character[] {'a', 'b', 'c', 'r', 'q', 'p', 's', ';', 'y', 'n', 'g', 'l'};
        assertThat(ParallelSearch.search(characters, 'q')).isEqualTo(4);
    }
}