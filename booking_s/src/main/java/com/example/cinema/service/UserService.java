package com.example.cinema.service;

import com.example.cinema.model.Users;
import com.example.cinema.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService {
    @Autowired
    private UserRepository user_repo;

    public List<Users> listAll(){
        return user_repo.findAll();
    }

    public void save(Users user){
        user_repo.save(user);
    }

    public Users get(long id){
        return user_repo.findById(id).get();
    }

    public void delete(long id){
        user_repo.deleteById(id);
    }


    public Optional<Users> findByEmail(String email){
        return user_repo.findByEmail(email);
    }


}