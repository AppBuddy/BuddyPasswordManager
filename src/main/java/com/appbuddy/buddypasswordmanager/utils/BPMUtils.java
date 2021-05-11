package com.appbuddy.buddypasswordmanager.utils;

import com.appbuddy.buddypasswordmanager.configs.Constants;
import com.appbuddy.buddypasswordmanager.gui.EntryListTable;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;
import java.util.Scanner;

/**
 *
 */
public class BPMUtils {
    /**
     *
     */
    public static void populateTable() {
        int row = 0;
        int col = 0;
        File filePath = new File("/Users/" + Constants.USERNAME + "/Documents/Buddy Information.txt");
        try {
            var input = new Scanner(filePath);
            while (Objects.requireNonNull(input).hasNextLine()) {
                var entry = input.nextLine();
                EntryListTable.getModelTable().setValueAt(entry, row, col);
                col++;
                if (col > 2) {
                    col = 0;
                }
                if (col == 0) {
                    row++;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param username
     * @param website
     * @param name
     * @param password
     */
    public static void writeNewEntryToFile(String username, String website, String name, String password) {
        String fileName;
        switch (Constants.OPERATING_SYSTEM) {
            case Constants.WINDOWS_7:
            case Constants.WINDOWS_8:
                fileName = "C:/Users/" + username + "/Documents/Buddy Information.txt";
                printLoginData(fileName, website, name, password);
                break;
            case Constants.WINDOWS_XP:
                fileName = "C:/Documents and Settings/" + username + "/My Documents/Buddy Information.txt";
                printLoginData(fileName, website, name, password);
                break;
            case Constants.MAC_OS_X:
                fileName = "/Users/" + username + "/Documents/Buddy Information.txt";
                printLoginData(fileName, website, name, password);
                break;
        }
    }

    /**
     *
     * @param fileName
     * @param website
     * @param name
     * @param password
     */
    private static void printLoginData(String fileName, String website, String name, String password) {
        FileWriter outFile;
        try {
            outFile = new FileWriter(fileName, true);
            PrintWriter printOutFile = new PrintWriter(Objects.requireNonNull(outFile));
            printOutFile.printf(website + "\n" + name + "\n" + password + "\n");
            printOutFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
