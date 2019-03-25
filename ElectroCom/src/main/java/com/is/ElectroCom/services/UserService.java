package com.is.ElectroCom.services;

import com.is.ElectroCom.model.Observer;
import com.is.ElectroCom.model.User;
import com.is.ElectroCom.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

// Clasa de service care implementeaza metodele specificate in interfata pe care aceasta o implementeaza
// Printre acestea se afla si metodele interfetei Subject, mai exact metodele pentru adaugarea unui nou Observer, stergerea unui nou Observer si metoda utilizata pentru a notifica toti observerii

@Service
public class UserService implements InterfaceUserService{

    private UserRepository userRepository;
    private User actualUser;
    public List<Observer> observers = new ArrayList<Observer>();

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<User> findAllByRole(String role) {
        List<User> listOfAllUsers = new ArrayList<>();
        List<User> list = new ArrayList<>();
        userRepository.findAll().iterator().forEachRemaining(listOfAllUsers::add);
        for(User user : listOfAllUsers)
            if(user.getRole().equals(role))
                list.add(user);
        return list;
    }

    @Override
    public void registerUser(User user) {
        userRepository.save(user);
        if(user.getRole().equals("Admin"))
            observers.add(user);
    }

    @Override
    public void setActualUser(User actualUser) {
        this.actualUser = actualUser;
    }

    @Override
    public User getActualUser() {
        return actualUser;
    }

    @Override
    public void logout() {
        actualUser = null;
    }

    @Override
    public List<User> getUsers() {
        List<User> list = new ArrayList<>();
        userRepository.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public void addNewUser(User user) {
        userRepository.save(user);
        if(user.getRole().equals("Admin"))
            observers.add(user);
    }

    @Override
    public void deleteUser(String email) {
        if (findByEmail(email).getRole().equals("Admin"));
            observers.remove(findByEmail(email));
        userRepository.deleteById(email);
    }

    @Override
    public void editAdminUser(String email, String firstName, String lastName, String address, String phoneNumber, String password, String role) {
        User user = userRepository.findByEmail(email);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setAddress(address);
        user.setPhoneNumber(phoneNumber);
        user.setPassword(password);
        user.setRole(role);
        userRepository.save(user);
        if(user.getRole().equals("Admin"))
            observers.add(user);
    }

    @Override
    public List<String> getAllMessages(){

        System.out.println("getAllMessages " + actualUser.getMessages().size());
        return actualUser.getMessages();
    }

    //  metoda pentru adaugarea unui nou Observer
    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    //  metoda pentru stergerea unui nou Observer
    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    //  metoda utilizata pentru a notifica toti observerii
    @Override
    public void notifyObserver(String name, String email, String phone, String message) {
        System.out.println("O ajuns in notify " + observers.size());

        String msg = "Mesajul este de la: " + name + ", avand adresa de e-mail: " + email + " si numarul de telefon: " + phone + ". Mesajul este: " + message + "\n";
        for (Observer i:
                observers) {
            System.out.println("Observer: " + ((User)i).getEmail());

            i.update(msg);
            System.out.println("nu inteleg de ce nu merge...");

            userRepository.save((User)i);
        }
        System.out.println("o si iesit");
    }

    // metoda pentru a crea lista de observeri la lansarea aplicatiei
    @Override
    public void createObserverList() {
        List<User> admins = findAllByRole("Admin");
        for(User admin : admins)
            addObserver(admin);
    }
}
