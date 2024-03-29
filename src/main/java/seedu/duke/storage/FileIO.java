package seedu.duke.storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public interface FileIO {
    BufferedReader getFileReader(String filePath) throws IOException;
    BufferedWriter getFileWriter(String filePath) throws IOException;
}
