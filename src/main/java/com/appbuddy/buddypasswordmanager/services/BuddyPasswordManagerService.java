package com.appbuddy.buddypasswordmanager.services;

import com.appbuddy.buddypasswordmanager.configs.Constants;
import com.appbuddy.buddypasswordmanager.models.OSInfo;
import com.appbuddy.buddypasswordmanager.models.OSType;
import com.appbuddy.buddypasswordmanager.view.EntryListTable;
import com.appbuddy.buddypasswordmanager.view.ViewService;
import com.appbuddy.buddypasswordmanager.view.windows.MainWindow;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
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

  private static final BuddyPasswordManagerService INSTANCE = new BuddyPasswordManagerService();
  public ViewService viewService;
  public static MainWindow mainWindow;
  private OSInfo osInfo;
  private static final String BUDDY_INFO_FILENAME = "BuddyInfo.txt";
  public static final String ENCRYPTION_KEY = "YourSecretKey123";

  public BuddyPasswordManagerService() {

  }

  public void initService() {
    initializeBuddyInfoFile();
    osInfo = getOSDetails();
    viewService = new ViewService();
  }

  public static BuddyPasswordManagerService getInstance() {
    return INSTANCE;
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

  /**
   * Checks for BuddyInfo.txt in user's home directory and creates it if it doesn't exist
   *
   * @return true if file was created, false if it already existed or creation failed
   */
  public static boolean initializeBuddyInfoFile() {
    try {
      var userHome = System.getProperty("user.home");
      Path filePath = Paths.get(userHome, BUDDY_INFO_FILENAME);

      if (Files.exists(filePath)) {
        log.info("BuddyInfo.txt already exists at: {}", filePath);
        return false;
      }

      Files.createFile(filePath);
      log.info("Successfully created BuddyInfo.txt at: {}", filePath);
      return true;

    } catch (IOException e) {
      log.error("Error handling BuddyInfo.txt: {}", e.getMessage());
      return false;
    }
  }

  /**
   * Gets detailed information about the operating system
   *
   * @return OSInfo object containing OS details
   */
  public static OSInfo getOSDetails() {
    String osName = System.getProperty("os.name");
    String osVersion = System.getProperty("os.version");
    String osArch = System.getProperty("os.arch");

    OSType type;
    if (osName.toLowerCase().contains("windows")) {
      type = OSType.WINDOWS;
    } else if (osName.toLowerCase().contains("mac")) {
      type = OSType.MAC;
    } else if (osName.toLowerCase().contains("linux")) {
      type = OSType.LINUX;
    } else {
      type = OSType.OTHER;
    }

    return new OSInfo(type, osName, osVersion, osArch);
  }

  public void scheduleTableReload() {

    viewService.populateTable3();
  }

  public static boolean saveAccount2(String username, String website, String name, String password) {
    Objects.requireNonNull(username, "Username cannot be null");
    Objects.requireNonNull(website, "Website cannot be null");
    Objects.requireNonNull(name, "Name cannot be null");
    Objects.requireNonNull(password, "Password cannot be null");

    try {
      // Get user home directory in a platform-independent way
      String userHome = System.getProperty("user.home");
      Path filePath = Paths.get(userHome, BUDDY_INFO_FILENAME);

      // Create the file if it doesn't exist
      if (!Files.exists(filePath)) {
        Files.createFile(filePath);
        log.info("Created new BuddyInfo file at: {}", filePath);
      }

      // Format and encrypt the data
      String encryptedData = formatAndEncryptData(website, name, password);

      // Write the data with proper line endings
      Files.write(
          filePath,
          (encryptedData + System.lineSeparator()).getBytes(StandardCharsets.UTF_8),
          StandardOpenOption.APPEND
      );

      log.info("Successfully saved account data for website: {}", website);
      return true;

    } catch (IOException e) {
      log.error("Error saving account data: ", e);
      return false;
    }
  }


  /**
   * Sanitizes input strings to prevent injection attacks
   *
   * @param input String to sanitize
   * @return Sanitized string
   */
  private static String sanitizeInput(String input) {
    return input.replace("|", "").replace("\n", "").trim();
  }

  /**
   * Formats and encrypts the account data
   *
   * @param website  Website URL
   * @param name     Login username
   * @param password Login password
   * @return Encrypted string of account data
   */
  private static String formatAndEncryptData(String website, String name, String password) {
    try {
      // Format data with a delimiter that won't appear in normal input
      String rawData = String.format("%s|%s|%s",
          sanitizeInput(website),
          sanitizeInput(name),
          sanitizeInput(password)
      );

      // Encrypt the data
      return encrypt(rawData);

    } catch (Exception e) {
      log.error("Error formatting/encrypting data: ", e);
      throw new IllegalStateException("Failed to process account data", e);
    }
  }

  /**
   * Encrypts the given string using AES encryption
   *
   * @param data String to encrypt
   * @return Encrypted string in Base64 format
   */
  private static String encrypt(String data) {
    try {
      MessageDigest digest = MessageDigest.getInstance("SHA-256");
      byte[] key = digest.digest(ENCRYPTION_KEY.getBytes(StandardCharsets.UTF_8));
      key = Arrays.copyOf(key, 16); // use only first 128 bits

      SecretKeySpec secretKey = new SecretKeySpec(key, "AES");
      Cipher cipher = Cipher.getInstance("AES");
      cipher.init(Cipher.ENCRYPT_MODE, secretKey);

      byte[] encryptedBytes = cipher.doFinal(data.getBytes());
      return Base64.getEncoder().encodeToString(encryptedBytes);

    } catch (Exception e) {
      log.error("Encryption error: ", e);
      throw new IllegalStateException("Encryption failed", e);
    }
  }

  /**
   * Clears all data from the file
   *
   * @return true if clear was successful, false otherwise
   */
  public static boolean clearFile() {
    try {
      String userHome = System.getProperty("user.home");
      Path filePath = Paths.get(userHome, BUDDY_INFO_FILENAME);

      if (Files.exists(filePath)) {
        Files.write(filePath, new byte[0], StandardOpenOption.TRUNCATE_EXISTING);
        log.info("Successfully cleared file contents");
        return true;
      }

      return false;
    } catch (IOException e) {
      log.error("Error clearing file: ", e);
      return false;
    }
  }

  /**
   * Backs up the current file
   *
   * @return true if backup was successful, false otherwise
   */
  public static boolean backupFile() {
    try {
      String userHome = System.getProperty("user.home");
      Path sourcePath = Paths.get(userHome, BUDDY_INFO_FILENAME);
      Path backupPath = Paths.get(userHome, BUDDY_INFO_FILENAME + ".backup");

      if (Files.exists(sourcePath)) {
        Files.copy(sourcePath, backupPath, StandardCopyOption.REPLACE_EXISTING);
        log.info("Successfully created backup at: {}", backupPath);
        return true;
      }

      return false;
    } catch (IOException e) {
      log.error("Error creating backup: ", e);
      return false;
    }
  }

}
