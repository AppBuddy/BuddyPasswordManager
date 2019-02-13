package com.appbuddy.buddypasswordmanager.model;

public class Entry {
    private String email;
    private String password;
    private String siteName;

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

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSiteName() {
        return this.siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }
}