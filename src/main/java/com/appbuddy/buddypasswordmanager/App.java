package com.appbuddy.buddypasswordmanager;

import com.appbuddy.buddypasswordmanager.services.BuddyPasswordManagerService;

/**
 * Represents the starting point for the application. It will create the main window.
 */
public class App {
  public static void main(String[] args) {

    var buddyPasswordManagerService = BuddyPasswordManagerService.getInstance();
    buddyPasswordManagerService.initService();
  }
}
