package dz.airalgerie.conge.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dz.airalgerie.conge.entities.User;
import dz.airalgerie.conge.repositories.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository employeRepository;

    public User addUser(User employe) {
        return employeRepository.save(employe);
    }
    
    
    public List<User> getAllUsers() {
        return employeRepository.findAll();
    }
}

