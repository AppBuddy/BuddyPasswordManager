package com.appbuddy.buddypasswordmanager.utils;

import com.appbuddy.buddypasswordmanager.gui.EntryListTable;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 */
public class BPMUtils {
    public static void populateTable() {
        int row = 0;
        int col = 0;
        String nameOfUser = System.getProperty("user.name");
        System.out.println("The name of the user is " + nameOfUser);
        String entry;

        File filePath = new File("/Users/" + nameOfUser + "/Documents/Buddy Information.txt");
        System.out.println("The path that the file will be in is " + filePath);

        Scanner input = null;
        try {
            input = new Scanner(filePath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        while (input.hasNextLine()) {
            entry = input.nextLine();
            System.out.println(entry);
            if (col >= 3) {
                col = 0;
            }
            EntryListTable.getModelTable().setValueAt(entry, row, col);

            if (col == 2) {
                row++;
            }
            col++;
        }
    }
}
