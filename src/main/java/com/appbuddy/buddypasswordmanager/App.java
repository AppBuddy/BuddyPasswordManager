package com.appbuddy.buddypasswordmanager;

import com.appbuddy.buddypasswordmanager.view.windows.MainWindow;

/**
 * Represents the starting point for the application. It will create the main window.
 */
public class App {
  public static void main(String[] args) {

    var mainWindow = new MainWindow();
    mainWindow.initWindow();
  }
}
