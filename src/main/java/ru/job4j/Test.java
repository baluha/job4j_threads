package ru.job4j;

import ru.job4j.synch.SimpleBlockingQueue;

public class Test extends Thread {
    public static void main(String[] args) {
        SimpleBlockingQueue<Integer> sbk = new SimpleBlockingQueue<>();
        Thread thread1 = new Thread(
                () -> {
                    for (int i = 0; i < 10; i++) {
                        sbk.offer(i);
                        System.out.println("Add " + i);
                    }
                }
        );
        Thread thread2 = new Thread(
                () -> {
                    for (int i = 0; i < 10; i++) {
                        System.out.println(sbk.poll());
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
    }
}
