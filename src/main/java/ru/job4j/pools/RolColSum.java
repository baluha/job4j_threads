package ru.job4j.pools;

import java.util.concurrent.CompletableFuture;

public class RolColSum {


    public static Sums[] sum(int[][] matrix) {
        int length = matrix.length;
        Sums[] sums = new Sums[length];
        for (int i = 0; i < length; i++) {
            sums[i] = getSum(matrix, i);
        }
        return sums;
    }

    public static Sums[] asyncSum(int[][] matrix) {
        int length = matrix.length;
        Sums[] sums = new Sums[length];
        CompletableFuture.runAsync(() -> {
                    for (int i = 0; i < length; i++) {
                        sums[i] = getSum(matrix, i);
                    }
                }
                );
        return sums;
    }

    private static Sums getSum(int[][] matrix, int index) {
        int row = 0;
        int column = 0;
        for (int i = 0; i < matrix.length; i++) {
            row += matrix[index][i];
            column += matrix[i][index];
        }
        return new Sums(row, column);
    }


}