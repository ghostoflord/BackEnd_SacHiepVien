package vn.vothien.ghost.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import vn.vothien.ghost.domain.User;
import vn.vothien.ghost.repository.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // get all user
    public List<User> fetchAllUser() {
        return this.userRepository.findAll();
    }

    // handle create user
    public User handleCreateUser(User user) {
        return this.userRepository.save(user);
    }

    // take user by id
    public User fetchUserById(long id) {
        Optional<User> userOptional = this.userRepository.findById(id);
        if (userOptional.isPresent()) {
            return userOptional.get();
        }
        return null;
    }

    // update user
    public User handleUpdateUser(User reqUser) {
        User currentUser = this.fetchUserById(reqUser.getId());
        if (currentUser != null) {
            currentUser.setAddress(reqUser.getAddress());
            currentUser.setGender(reqUser.getGender());
            // update
            currentUser = this.userRepository.save(currentUser);
        }
        return currentUser;
    }

    // delete use
    public void handleDeleteUser(long id) {
        this.userRepository.deleteById(id);
    }
}
