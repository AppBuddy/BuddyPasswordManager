package com.appbuddy.buddypasswordmanager.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents possible fields to save account information for.
 */
@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class AccountEntry {

  private String username;
  private String password;
  private String website;
}