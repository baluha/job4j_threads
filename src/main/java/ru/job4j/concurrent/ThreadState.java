package ru.job4j.concurrent;

public class ThreadState {
    public static void main(String[] args) {
        Thread first = new Thread(
                () -> {
                    System.out.println(Thread.currentThread().getName());
                }
        );
        Thread second = new Thread(
                () -> {
                    System.out.println(Thread.currentThread().getName());
                }
        );
        first.start();
        second.start();
        System.out.println(first.getState());
        System.out.println(second.getState());
        if (first.getState().equals(Thread.State.TERMINATED)
                && second.getState().equals(Thread.State.TERMINATED)) {
            System.out.println("Работа нитей завершена");
        }
    }
}
