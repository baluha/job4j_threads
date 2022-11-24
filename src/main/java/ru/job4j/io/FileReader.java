package ru.job4j.io;

import java.io.*;
import java.util.function.Predicate;

public final class FileReader {

    private final File file;

    public FileReader(File file) {
        this.file = file;
    }

    public synchronized String getAllContent() throws IOException {
        return getContent(ch -> true);
    }

    public synchronized String getContentWithoutUnicode() throws IOException {
        return getContent(ch -> ch < 0x80);
    }

    private String getContent(Predicate<Character> pred) throws IOException {
        StringBuilder output = new StringBuilder();
        try (BufferedInputStream i = new BufferedInputStream(new FileInputStream(file))) {
            int data;
            while ((data = i.read()) != -1) {
                if (pred.test((char) data)) {
                    output.append((char) data);
                }
            }
        }
        return output.toString();
    }

}
