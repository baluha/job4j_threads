package ru.job4j;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CASCountTest {

    @Test
    void whenCountingByTwoThreads() {
        CASCount count = new CASCount();
        Thread thread1 = new Thread(
                () -> {
                    for (int i = 0; i < 5; i++) {
                        count.increment();
                    }
                }
        );
        Thread thread2 = new Thread(
                () -> {
                    for (int i = 0; i < 5; i++) {
                        count.increment();
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
        assertThat(count.get()).isEqualTo(10);
    }

}