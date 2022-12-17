package ru.job4j.pool;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ParallelSearch<V> extends RecursiveTask<Integer> {

    private final V[] array;
    private final V value;
    private final int from;
    private final int to;

    public ParallelSearch(V[] array, V value, int from, int to) {
        this.array = array;
        this.value = value;
        this.from = from;
        this.to = to;
    }

    @Override
    protected Integer compute() {
        int result = 0;
        if ((to - from) <= 10) {
            for (int i = from; i <= to; i++) {
                if (value.equals(array[i])) {
                    result = i;
                    break;
                }
            }
            return result;
        }
        int mid = (from + to) / 2;
        ParallelSearch<V> leftSort = new ParallelSearch<>(array, value, from, mid);
        ParallelSearch<V> rightSort = new ParallelSearch<>(array, value, mid + 1, to);

        leftSort.fork();
        rightSort.fork();
        return Math.max(rightSort.join(), leftSort.join());
    }

    public static <V> Integer search(V[] array, V var) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        return forkJoinPool
                .invoke(new ParallelSearch<>(array, var, 0, array.length - 1));
    }

}

