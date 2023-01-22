package ru.job4j.pools;

import org.junit.jupiter.api.Test;


import static org.assertj.core.api.Assertions.assertThat;

class RolColSumTest {

    @Test
    void whenCountingSums() {
        int[][] matrix = new int[][]{{1, 2}, {3, 4}};
        Sums[] sums = new Sums[] {new Sums(3, 4),
                new Sums(7, 6)};
        assertThat(RolColSum.sum(matrix)).isEqualTo(sums);
    }

    @Test
    void whenAsyncCountingSums() {
        int[][] matrix = new int[][]{{1, 2}, {3, 4}};
        Sums[] sums = new Sums[] {new Sums(3, 4),
                new Sums(7, 6)};
        assertThat(RolColSum.asyncSum(matrix)).isEqualTo(sums);
    }

}