package com.appbuddy.buddypasswordmanager.view;

import static com.appbuddy.buddypasswordmanager.services.BuddyPasswordManagerService.ENCRYPTION_KEY;

import com.appbuddy.buddypasswordmanager.models.AccountEntry;
import com.appbuddy.buddypasswordmanager.view.windows.MainWindow;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.Objects;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.table.DefaultTableModel;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ViewService {

  public MainWindow mainWindow;

  public ViewService() {
    mainWindow = new MainWindow();
    mainWindow.initWindow();
    populateTable();
  }

  /**
   * Populates the table with data from BuddyInfo.txt
   *
   * @throws IllegalStateException if the table or model is null
   */
  public void populateTable() {
    log.info("Starting table population...");

    try {
      // Get the file path using system properties for cross-platform compatibility
      String userHome = System.getProperty("user.home");
      Path filePath = Paths.get(userHome, "BuddyInfo.txt");

      // Verify the file exists
      if (!Files.exists(filePath)) {
        throw new IllegalStateException("BuddyInfo.txt not found");
      }

      // Read all lines from file
      List<String> lines = Files.readAllLines(filePath);

      // Calculate required rows based on data
      int requiredRows = (int) Math.ceil(lines.size() / (double) EntryListTable.COLUMN_TITLES.length);

      // Create data matrix for the table
      Object[][] tableData = new Object[requiredRows][EntryListTable.COLUMN_TITLES.length];

      // Populate the data matrix
      int currentRow = 0;
      int currentCol = 0;

      for (String line : lines) {
        if (!line.trim().isEmpty()) { // Skip empty lines
          tableData[currentRow][currentCol] = line.trim();

          currentCol++;
          if (currentCol >= EntryListTable.COLUMN_TITLES.length) {
            currentCol = 0;
            currentRow++;
          }
        }
      }

      // Get the table and create new model
      EntryListTable entryListTable = Objects.requireNonNull(
          mainWindow.getEntryListTable(),
          "Entry list table cannot be null"
      );

      // Create and set new table model
      DefaultTableModel newModel = new DefaultTableModel(tableData, EntryListTable.COLUMN_TITLES) {
        @Override
        public boolean isCellEditable(int row, int column) {
          return false; // Make table read-only
        }
      };

      // Update the table on the EDT
      javax.swing.SwingUtilities.invokeLater(() -> {
        entryListTable.setModelTable(newModel);
        entryListTable.revalidate();
        entryListTable.repaint();
        log.info("Table populated successfully with {} rows", requiredRows);
      });

    } catch (Exception e) {
      log.error("Error populating table: ", e);
      throw new IllegalStateException("Failed to populate table", e);
    }
  }

  /**
   * Populates the table with decrypted data from BuddyInfo.txt
   * @throws IllegalStateException if the table or model is null
   */
  public void populateTable3() {
    log.info("Starting table population with decryption...");

    try {
      // Get the file path using system properties
      String userHome = System.getProperty("user.home");
      Path filePath = Paths.get(userHome, "BuddyInfo.txt");

      // Verify the file exists
      if (!Files.exists(filePath)) {
        log.error("BuddyInfo.txt not found at: {}", filePath);
        throw new IllegalStateException("BuddyInfo.txt not found");
      }

      // Read and decrypt all lines
      List<String> encryptedLines = Files.readAllLines(filePath);
      List<AccountEntry> decryptedEntries = new ArrayList<>();

      for (String encryptedLine : encryptedLines) {
        if (!encryptedLine.trim().isEmpty()) {
          try {
            String decryptedData = decrypt(encryptedLine.trim());
            String[] parts = decryptedData.split("\\|");
            if (parts.length == 3) {
              decryptedEntries.add(new AccountEntry(parts[0], parts[1], parts[2]));
            }
          } catch (Exception e) {
            log.error("Failed to decrypt line: {}", encryptedLine, e);
          }
        }
      }

      // Create data matrix for the table
      Object[][] tableData = new Object[decryptedEntries.size()][EntryListTable.COLUMN_TITLES.length];

      // Populate the data matrix
      for (int i = 0; i < decryptedEntries.size(); i++) {
        AccountEntry entry = decryptedEntries.get(i);
        tableData[i][0] = entry.getWebsite();
        tableData[i][1] = entry.getUsername();
        tableData[i][2] = maskPassword(entry.getPassword());  // Mask passwords in the table
      }

      // Get the table and create new model
      EntryListTable entryListTable = Objects.requireNonNull(
          mainWindow.getEntryListTable(),
          "Entry list table cannot be null"
      );

      // Create and set new table model with custom cell editing rules
      DefaultTableModel newModel = new DefaultTableModel(tableData, EntryListTable.COLUMN_TITLES) {
        @Override
        public boolean isCellEditable(int row, int column) {
          return false; // Make table read-only
        }

        @Override
        public Class<?> getColumnClass(int columnIndex) {
          return String.class;
        }
      };

      // Update the table on the EDT
      javax.swing.SwingUtilities.invokeLater(() -> {
        entryListTable.setModelTable(newModel);
        entryListTable.revalidate();
        entryListTable.repaint();
        log.info("Table populated successfully with {} entries", decryptedEntries.size());
      });

    } catch (Exception e) {
      log.error("Error populating table: ", e);
      throw new IllegalStateException("Failed to populate table", e);
    }
  }

  /**
   * Decrypts the given string using AES encryption
   * @param encryptedData Base64 encoded encrypted string
   * @return Decrypted string
   */
  private static String decrypt(String encryptedData) throws Exception {
    MessageDigest digest = MessageDigest.getInstance("SHA-256");
    byte[] key = digest.digest(ENCRYPTION_KEY.getBytes(StandardCharsets.UTF_8));
    key = Arrays.copyOf(key, 16); // use only first 128 bits

    SecretKeySpec secretKey = new SecretKeySpec(key, "AES");
    Cipher cipher = Cipher.getInstance("AES");
    cipher.init(Cipher.DECRYPT_MODE, secretKey);

    byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedData));
    return new String(decryptedBytes, StandardCharsets.UTF_8);
  }

  /**
   * Masks a password for display in the table
   * @param password The actual password
   * @return A masked version of the password
   */
  private static String maskPassword(String password) {
    return "•".repeat(8); // Show fixed-length bullet points instead of actual password
  }

  /**
   * Clears all data from the table
   * @param mainWindow the main window containing the table
   */
  public static void clearTable(MainWindow mainWindow) {
    try {
      EntryListTable entryListTable = Objects.requireNonNull(mainWindow.getEntryListTable());
      DefaultTableModel model = new DefaultTableModel(EntryListTable.COLUMN_TITLES, 0);

      javax.swing.SwingUtilities.invokeLater(() -> {
        entryListTable.setModelTable(model);
        entryListTable.revalidate();
        entryListTable.repaint();
        log.info("Table cleared successfully");
      });
    } catch (Exception e) {
      log.error("Error clearing table: ", e);
      throw new IllegalStateException("Failed to clear table", e);
    }
  }

}
