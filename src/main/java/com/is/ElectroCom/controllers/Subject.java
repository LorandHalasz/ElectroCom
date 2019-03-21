package com.is.ElectroCom.controllers;

import com.is.ElectroCom.model.Observer;

// Interfata pentru implementarea design pattern-ului Observer

public interface Subject {
    void addObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObserver(String name, String email, String phone, String message);
}
