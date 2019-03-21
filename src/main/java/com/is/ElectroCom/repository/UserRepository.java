package com.is.ElectroCom.repository;

import com.is.ElectroCom.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

// interfata pentru specificarea unor metode utile pentru a realiza anumite operatii asupra bazei de date

@Service
public interface UserRepository extends MongoRepository<User, String> {
    User findByEmail(String email);
    List<User> findAllByRole(String role);
}