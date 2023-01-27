package ru.job4j.io;

import java.io.*;

public final class ParseFile {

    private final File file;

    public ParseFile(File file) {
        this.file = file;
    }

    public String getContent() throws IOException {
        StringBuilder output = new StringBuilder();
        try (BufferedInputStream is = new BufferedInputStream(new FileInputStream(file))) {
            int data;
            while ((data = is.read()) != -1) {
                output.append((char) data);
            }
            return output.toString();
        }
    }

    public String getContentWithoutUnicode() throws IOException {
        StringBuilder output = new StringBuilder();
        try (BufferedInputStream i = new BufferedInputStream(new FileInputStream(file))) {
            int data;
        while ((data = i.read()) != -1) {
            if (data < 0x80) {
                    output.append((char) data);
                }
            }
        }
        return output.toString();
    }

    public void saveContent(String content) throws IOException {
        try (BufferedOutputStream o = new BufferedOutputStream(new FileOutputStream(file))) {
            o.write(content.getBytes());
            }
        }
}