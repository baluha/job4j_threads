package ru.job4j;

public class CountBarrier {
    private final Object monitor = this;

    private final int total;

    private int count = 0;

    public CountBarrier(final int total) {
        this.total = total;
    }

    public void count() {
        synchronized (monitor) {
            while (count <= total) {
                count++;
                System.out.println(count);
                notifyAll();
            }
        }

    }

    public void await() {
        synchronized (monitor) {
            while (count <= total) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            System.out.println("Counting is over!");
        }
    }

    public static void main(String[] args) {
        CountBarrier cb = new CountBarrier(9);
        Thread thread1 = new Thread(
                cb::count
        );
        Thread thread2 = new Thread(
                cb::await
        );
        thread1.start();
        thread2.start();
    }
}