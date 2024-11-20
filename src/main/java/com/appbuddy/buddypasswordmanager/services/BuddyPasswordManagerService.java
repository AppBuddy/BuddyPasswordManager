package com.appbuddy.buddypasswordmanager.services;

import com.appbuddy.buddypasswordmanager.configs.Constants;
import com.appbuddy.buddypasswordmanager.view.EntryListTable;
import com.appbuddy.buddypasswordmanager.view.windows.MainWindow;

import javax.swing.table.DefaultTableModel;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;
import java.util.Scanner;
import lombok.extern.slf4j.Slf4j;

/**
 * Represents the main service for interacting with the windows and actions.
 */
@Slf4j
public class BuddyPasswordManagerService {

  public static MainWindow mainWindow;

  public BuddyPasswordManagerService() {
    mainWindow = new MainWindow();
    mainWindow.initWindow();
  }

  public static void doesSaveFileExist() {

  }

  public static void populateTable() {

    log.info("Updating table data...");

    var row = 0;
    var col = 0;
    var filePath = new File("/Users/" + Constants.USERNAME + "/Documents/BuddyInfo.txt");
    try {
      var input = new Scanner(filePath);
      while (Objects.requireNonNull(input).hasNextLine()) {
        var entry = input.nextLine();

        var entryListTable = Objects.requireNonNullElse(mainWindow.getEntryListTable(), new EntryListTable());
        var modelTable = Objects.requireNonNullElse(entryListTable.getModelTable(), new DefaultTableModel());
        modelTable.setValueAt(entry, row, col);

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

  public static void saveAccount(String username, String website, String name, String password) {
    String fileName;
    switch (Constants.OPERATING_SYSTEM) {
      case Constants.WINDOWS_7:
      case Constants.WINDOWS_8:
        fileName = "C:/Users/" + username + "/Documents/" + Constants.SAVE_FILE_NAME;
        printLoginData(fileName, website, name, password);
        break;
      case Constants.WINDOWS_XP:
        fileName = "C:/Documents and Settings/" + username + "/My Documents/" + Constants.SAVE_FILE_NAME;
        printLoginData(fileName, website, name, password);
        break;
      case Constants.MAC_OS_X:
        fileName = "/Users/" + username + "/Documents/" + Constants.SAVE_FILE_NAME;
        printLoginData(fileName, website, name, password);
        break;
    }
  }

  private static void printLoginData(String fileName, String website, String name, String password) {
    FileWriter outFile;
    try {
      outFile = new FileWriter(fileName, true);
      PrintWriter printOutFile = new PrintWriter(Objects.requireNonNull(outFile));
      printOutFile.printf(website + "\n" + name + "\n" + password + "\n");
      printOutFile.close();
    } catch (IOException e) {

      log.error("Error while attempt to print login data", e);
    }
  }
}
