package seedu.duke.storage;

import java.io.*;

/**
 * Implements the FileIO interface.
 * Provides concrete implementations for file I/O operations using BufferedReader and BufferedWriter.
 */
public class FileIOImpl implements FileIO {
    /**
     * Returns a BufferedReader for reading from the specified file.
     *
     * @param filePath The path of the file to read from.
     * @return A BufferedReader for reading from the file.
     * @throws IOException If an I/O error occurs while creating the reader.
     */
    @Override
    public BufferedReader getFileReader(String filePath) throws IOException {
        return new BufferedReader(new FileReader(filePath));
    }

    /**
     * Returns a BufferedWriter for writing to the specified file.
     *
     * @param filePath The path of the file to write to.
     * @return A BufferedWriter for writing to the file.
     * @throws IOException If an I/O error occurs while creating the writer.
     */
    @Override
    public BufferedWriter getFileWriter(String filePath) throws IOException {
        return new BufferedWriter(new FileWriter(filePath));
    }

    /**
     * Deletes the file at the specified file path.
     *
     * @param filePath The path of the file to be deleted.
     * @return true if the file was successfully deleted, false otherwise.
     * @throws IOException If an I/O error occurs while deleting the file.
     */
    @Override
    public boolean deleteFile(String filePath) throws IOException {
        File file = new File(filePath);
        return file.delete();
    }
}
