package ru.job4j.concurrent;

public class ConsoleProgress implements Runnable {
    @Override
    public void run() {
        char[] ball = new char[]{'\\', '|', '/', '-'};
        int i = 0;
        try {
            while (!Thread.currentThread().isInterrupted()) {
                System.out.println("\r load: " + ball[i++]);
                if (i == ball.length) {
                    i = 0;
                }
                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void main(String[] args) {
        Thread progress = new Thread(new ConsoleProgress());
        progress.start();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        progress.interrupt();
    }
    }

