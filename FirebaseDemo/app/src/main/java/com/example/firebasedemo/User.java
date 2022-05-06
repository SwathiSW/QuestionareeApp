package com.firebaseauth;

public class User {
    String etFullname;
    String etAge;
    String etEmail;


    public User() {
    }
    public User(String etFullname, String etAge, String etEmail) {
        this.etFullname = etFullname;
        this.etAge = etAge;
        this.etEmail = etEmail;
    }

    public String getEtFullname() {
        return etFullname;
    }

    public String getEtAge() {
        return etAge;
    }

    public String getEtEmail() {
        return etEmail;
    }
}
