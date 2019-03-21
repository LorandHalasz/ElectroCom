package com.is.ElectroCom.model;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;

// Clasa care reprezinta modelul User utilizat de baza de date
// De asemenea aceasta ajuta la implementarea design pattern-ului Observer
// Aceasta implementeaza interfata Observer si implicit metoda de update pentru a realiza operatiile necesare asupra Observerilor
@Document
public class User implements Observer{

    private String firstName;
    private String lastName;
    private String address;
    private String phoneNumber;
    @Id
    private String email;
    private String password;
    private String role;
    private List<String> messages;

    public User(String firstName, String lastName, String address, String phoneNumber, String email, String password, String role, ArrayList<String> messages) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
        this.role = role;
        this.messages = messages;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }

    // metoda de update utilizata pentru a realiza operatiile necesare asupra Observerilor
    @Override
    public void update(String message) {
        this.messages.add(message);
    }
}
