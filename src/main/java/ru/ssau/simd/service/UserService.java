package ru.ssau.simd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.ssau.simd.entity.User;
import ru.ssau.simd.exception.NoEntityException;
import ru.ssau.simd.pojo.UserPojo;
import ru.ssau.simd.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    // create
    public UserPojo saveUser(UserPojo pojo) {
        User user = userRepository.save(User.builder()
                .name(pojo.getName()).build());

        return UserPojo.fromEntity(user);
    }

    // read all
    public List<UserPojo> getAllUsers() {
        List<User> users = userRepository.findAll();
        List<UserPojo> userPojos = new ArrayList<>();

        for (User user : users)
            userPojos.add(UserPojo.fromEntity(user));

        return userPojos;
    }

    // read
    public UserPojo getUserById(Long id) throws NoEntityException {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NoEntityException(id));

        return UserPojo.fromEntity(user);
    }

    // update
    public UserPojo updateUser(Long id, UserPojo pojo) throws NoEntityException{
        User userToUpdate = userRepository.findById(id)
                .orElseThrow(() -> new NoEntityException(id));

        userToUpdate.setName(pojo.getName());

        return UserPojo.fromEntity(userRepository.save(userToUpdate));
    }

    // delete
    public void deleteUser(Long id) throws NoEntityException {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NoEntityException(id));

        userRepository.deleteById(id);
    }
}
