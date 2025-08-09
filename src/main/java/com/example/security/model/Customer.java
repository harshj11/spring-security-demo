package com.example.security.model;

import jakarta.persistence.*;

@Entity
@Table(name = "customer") // Used if the class and table name differs, but not required in our case.
public class Customer {

    @Id // This signifies that this is the primary key column
    @GeneratedValue(strategy = GenerationType.IDENTITY) // The IDENTITY type specifies that the value is auto-generated
                                                        // by the database.
    private long id;

    @Column(name = "email") // Used if field name here and column name in DB differs, not required in our case.
    private String email;

    private String pwd;

    private String role;

    public Customer() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return pwd;
    }

    public void setPassword(String password) {
        this.pwd = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
