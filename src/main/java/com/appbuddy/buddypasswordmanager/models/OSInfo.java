package com.appbuddy.buddypasswordmanager.models;

import lombok.Value;

@Value
public  class OSInfo {
  OSType type;
  String name;
  String version;
  String architecture;

  @Override
  public String toString() {
    return String.format("OS: %s (Type: %s)\nVersion: %s\nArchitecture: %s",
        name, type, version, architecture);
  }
}
