package com.appbuddy.buddypasswordmanager.model;

public class Entry {
    private final String email;
    private final String password;
    private final String siteName;

    public Entry(String email, String password, String siteName) {
        this.email = email;
        this.password = password;
        this.siteName = siteName;
    }

    public Entry() {
        this("test@test.com", "password", "http://www.example.com");
    }

    public String getEmail() {
        return this.email;
    }

    public String getPassword() {
        return this.password;
    }

    public String getSiteName() {
        return this.siteName;
    }
}