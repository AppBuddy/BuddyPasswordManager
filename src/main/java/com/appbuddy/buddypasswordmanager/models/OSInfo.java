package com.appbuddy.buddypasswordmanager.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class OSInfo {

  private OSType type;
  private String name;
  private String version;
  private String architecture;

  @Override
  public String toString() {
    return String.format("OS: %s (Type: %s)\nVersion: %s\nArchitecture: %s",
        name, type, version, architecture);
  }
}
