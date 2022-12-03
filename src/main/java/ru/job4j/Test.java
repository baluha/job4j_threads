package ru.job4j;

public class Test extends Thread {

    volatile boolean b = true;

    public void run() {
        long counter = 0;
        while (b) {
            counter++;
        }
        System.out.println("Number is " + counter);
    }

    public static void main(String[] args) throws InterruptedException {
        Test test = new Test();
        test.start();
        Thread.sleep(3000);
        System.out.println("Wake up!");
        test.b = false;
        test.join();
        System.out.println("End");
    }
}
