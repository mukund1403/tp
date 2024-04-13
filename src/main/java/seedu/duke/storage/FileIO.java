package seedu.duke.storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

/**
 * Represents the file I/O operations.
 * Defines methods for reading from and writing to files.
 */
public interface FileIO {
    /**
     * Returns a BufferedReader for reading from the specified file.
     *
     * @param filePath The path of the file to read from.
     * @return A BufferedReader for reading from the file.
     * @throws IOException If an I/O error occurs while creating the reader.
     */
    BufferedReader getFileReader(String filePath) throws IOException;

    /**
     * Returns a BufferedWriter for writing to the specified file.
     *
     * @param filePath The path of the file to write to.
     * @return A BufferedWriter for writing to the file.
     * @throws IOException If an I/O error occurs while creating the writer.
     */
    BufferedWriter getFileWriter(String filePath) throws IOException;

    /**
     * Deletes the file at the specified file path.
     *
     * @param filePath The path of the file to be deleted.
     * @return true if the file was successfully deleted, false otherwise.
     * @throws IOException If an I/O error occurs while deleting the file.
     */
    boolean deleteFile(String filePath) throws IOException;
}
