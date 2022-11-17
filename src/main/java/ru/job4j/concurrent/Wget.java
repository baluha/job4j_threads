package ru.job4j.concurrent;

import java.io.BufferedInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class Wget implements Runnable {
    private static final String regex =
            "^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";

    private final String url;
    private final int speed;

    public Wget(String url, int speed) {
        this.url = url;
        this.speed = speed;
    }

    private static void validate(String[] st) {
        if (st.length < 2) {
            throw new IllegalArgumentException("Arguments length < 2 or wrong arguments");
        }
        if (!st[0].matches(regex)) {
            throw new IllegalArgumentException(String.format("Wrong url %s", st[0]));
        }
        if (Integer.parseInt(st[1]) < 0) {
            throw new IllegalArgumentException(String.format("Wrong speed %s", st[1]));
        }
    }

    @Override
    public void run() {
        String[] formatS = url.split(".");
        String format = formatS[formatS.length - 1];
        try (BufferedInputStream input = new BufferedInputStream(new URL(url).openStream());
        FileOutputStream os = new FileOutputStream("fileName" + "." + format)) {
            long start = System.currentTimeMillis();
            int readBytes;
            int download = 0;
            byte[] data = new byte[1024];
            while ((readBytes = input.read(data, 0, 1024)) != -1) {
                download += readBytes;
                if (speed < download) {
                    long downloadTime = System.currentTimeMillis() - start;
                    if (downloadTime > 1000) {
                        Thread.sleep(1000 - downloadTime);
                    }
                    start = System.currentTimeMillis();
                    download = 0;
                }
                os.write(data, 0, readBytes);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        validate(args);
        String url = args[0];
        int speed = Integer.parseInt(args[1]);
        Thread wget = new Thread(new Wget(url, speed));
        wget.start();
        wget.join();
    }
}
