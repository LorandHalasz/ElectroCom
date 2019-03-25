package com.is.ElectroCom.services;

import com.is.ElectroCom.controllers.Subject;
import com.is.ElectroCom.model.User;

import java.util.List;

// Interfata utilizata pentru specificarea metodelor care necesita implementate pentru a putea realiza functionalitatile dorite
// De asemenea aceasta ajuta la implementarea design pattern-ului Observer
// Acesta extinde interfata Subject

public interface InterfaceUserService extends Subject {

    User findByEmail(String email);
    List<User> findAllByRole(String role);
    void registerUser(User user);
    void setActualUser(User actualUser);
    User getActualUser();
    void logout();
    List<User> getUsers();
    void addNewUser(User user);
    void deleteUser(String email);
    void editAdminUser(String email, String firstName, String lastName, String address, String phoneNumber, String password, String role);
    List<String> getAllMessages();
    void createObserverList();
}
