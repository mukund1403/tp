package seedu.duke.storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileIOImpl implements FileIO {
    @Override
    public BufferedReader getFileReader(String filePath) throws IOException {
        return new BufferedReader(new FileReader(filePath));
    }

    @Override
    public BufferedWriter getFileWriter(String filePath) throws IOException {
        return new BufferedWriter(new FileWriter(filePath));
    }
}
