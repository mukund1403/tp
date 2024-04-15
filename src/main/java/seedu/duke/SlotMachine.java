package seedu.duke;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class SlotMachine {
    private static final String slotOutputs = "%^#@$*!~";
    private List<List<Character>> slotMachine;

    /**
     * Generates a 3x3 slot machine matrix
     * Each slot takes up 3 rows and 3 columns
     *
     * @param numRows total number of rows
     * @param numCols total number of columns
     */
    SlotMachine(int numRows, int numCols) {
        // create slot machine matrix
        slotMachine = new ArrayList<>(numRows);
        for (int row = 0; row < numRows; row++) {
            List<Character> currentRow = new ArrayList<>(numCols);
            for (int j = 0; j < numCols; j++) {
                // Initialize each cell with empty char
                currentRow.add(' ');
            }
            slotMachine.add(currentRow);
        }
        // randomise each slot
        fillSlots();
    }

    // fill each slot with random characters
    private void fillSlots() {
        Random rand = ThreadLocalRandom.current();
        for (int row = 0; row < slotMachine.size(); row += 3) {
            for (int col = 0; col < slotMachine.get(0).size(); col += 3) {
                char randomChar = slotOutputs.charAt(rand.nextInt(slotOutputs.length()));
                //one char in each slot
                fillSlot(row, col, randomChar);
            }
        }
    }

    // fill one slot with the same random character
    private void fillSlot(int startRow, int startCol, char character) {
        for (int i = startRow; i < startRow + 3; i++) {
            for (int j = startCol; j < startCol + 3; j++) {
                slotMachine.get(i).set(j, character);
            }
        }
    }

    // randomise characters
    void reroll() {
        fillSlots();
    }

    // override toString method to print the slot machine
    @Override
    public String toString() {
        StringBuilder matrix = new StringBuilder();

        // Draw the slot machine
        for (int i = 0; i < slotMachine.size(); i += 3) {
            // Draw the top of the boxes
            for (int j = 0; j < slotMachine.get(0).size(); j += 3) {
                matrix.append("_____");
            }
            matrix.append("\n");
            // Draw the content of each slot
            for (int row = i; row < i + 3; row++) {
                for (int j = 0; j < slotMachine.get(0).size(); j += 3) {
                    matrix.append("|");
                    for (int col = j; col < j + 3; col++) {
                        matrix.append(slotMachine.get(row).get(col));
                    }
                    matrix.append("|");
                }
                matrix.append("\n");
            }
            // Draw the bottom of the boxes
            for (int j = 0; j < slotMachine.get(0).size(); j += 3) {
                matrix.append("_____");
            }
            matrix.append("\n");
        }
        return matrix.toString();
    }
    // check if all characters in the middle rows are the same
    boolean isWin() {
        for (int i = slotMachine.size() / 2 - 1; i <= slotMachine.size() / 2 + 1; i++) {
            char firstChar = slotMachine.get(i).get(0);
            for (int j = 1; j < slotMachine.get(i).size(); j++) {
                if (slotMachine.get(i).get(j) != firstChar) {
                    return false;
                }
            }
        }
        return true;
    }
}
