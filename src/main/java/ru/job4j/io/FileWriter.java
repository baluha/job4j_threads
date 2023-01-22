package ru.job4j.io;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public final class FileWriter {

    private final File file;

    public FileWriter(File file) {
        this.file = file;
    }
    public synchronized void saveContent(String content) throws IOException {
        try (BufferedOutputStream os = new BufferedOutputStream(new FileOutputStream(file))) {
            os.write(content.getBytes());
        }
    }
}
