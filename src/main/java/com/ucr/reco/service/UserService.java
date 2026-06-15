package com.ucr.reco.service;

import com.ucr.reco.model.User;
import com.ucr.reco.model.dto.UserDTO;
import com.ucr.reco.repository.UserJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserJpaRepository repository;

    public List<User> findAll() {
        return repository.findAll();
    }

    /*
        public User add(User user){
            if(repository.existsByEmail(user.getEmail())){
                return null;
            }
            return repository.save(user);
        }
    */
    public User add(UserDTO user) {

        if (repository.existsByEmail(user.getEmail())) {
            return null;
        } else {
            if (user.getName() == null || user.getEmail() == null || user.getPassword() == null || user.getRole() == null) {
                return null;
            }
        }
        User userTemp = new User();
        userTemp.setName(user.getName());
        userTemp.setEmail(user.getEmail());
        userTemp.setPassword(user.getPassword());
        userTemp.setRole(user.getRole());
        return repository.save(userTemp);
        //return "Proceso exitoso";
    }

    public User getById(Integer id) {
        User user = repository.findById(id.intValue());
        if (user != null) {
            return user;
        }
        /*if (repository.existsById(id)) {
            return repository.findById(id).get();
        }*/
        return null;
    }

    public User update(UserDTO user) {
        Optional<User> userExits = repository.findUserByEmail(user.getEmail());
        if (userExits.isPresent()) {
            if (user.getName() != null) {
                userExits.get().setName(user.getName());
            }
            if (user.getPassword() != null) {
                userExits.get().setPassword(user.getPassword());
            }
            if (user.getRole() != null) {
                userExits.get().setRole(user.getRole());
            }

        } else {
            return null;
        }
        return repository.save(userExits.get());
    }

    public User delete(Integer id) {
        Optional<User> userExits = repository.findById(id);
        if (userExits.isPresent()) {
            repository.deleteById(id);
            return userExits.get();
        } else {
            return null;
        }
    }

    public User changePassword(String email, String newPassword) {
        User userExits = repository.getByEmail(email);
        if (userExits != null) {
            userExits.setPassword(newPassword);
            return repository.save(userExits);
        } else {
            return null;
        }
    }

    public User getUserByEmail(String email) {
        return repository.getByEmail(email);
    }
}
