package com.appbuddy.buddypasswordmanager.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountEntry {
  private String username;
  private String password;
  private String website;
}