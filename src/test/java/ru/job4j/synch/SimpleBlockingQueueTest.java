package ru.job4j.synch;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

class SimpleBlockingQueueTest {

    private final SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(5);

    @Test
    void whenAddThenPush() {
        List<Integer> lst = new ArrayList<>();
        AtomicInteger value = new AtomicInteger();
        Thread thread1 = new Thread(
                () -> {
                        for (int i = 0; i < 10; i++) {
                            try {
                                queue.offer(i);
                            } catch (InterruptedException e) {
                                Thread.currentThread().interrupt();
                            }
                    }
                }
        );
        Thread thread2 = new Thread(
                () -> {
                        for (int i = 0; i < 10; i++) {
                            try {
                                int val = queue.poll();
                                value.set(val);
                                lst.add(val);
                            } catch (InterruptedException e) {
                                Thread.currentThread().interrupt();
                            }
                        }
                }
        );
        thread1.start();
        thread2.start();
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        assertThat(value.get()).isEqualTo(9);
        assertThat(lst).isEqualTo(List.of(0, 1, 2, 3, 4, 5, 6, 7, 8, 9));
    }
}