package vn.vothien.ghost.service;

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

    // handle create user
    public User handleCreateUser(User user) {
        // // check company
        // if (user.getCompany() != null) {
        // Optional<Company> companyOptional =
        // this.companyService.findById(user.getCompany().getId());
        // user.setCompany(companyOptional.isPresent() ? companyOptional.get() : null);
        // }

        // // check role
        // if (user.getRole() != null) {
        // Role r = this.roleService.fetchRoleById(user.getRole().getId());
        // user.setRole(r != null ? r : null);
        // }
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

            // check company
            // if (reqUser.getCompany() != null) {
            // Optional<Company> companyOptional =
            // this.companyService.findById(reqUser.getCompany().getId());
            // currentUser.setCompany(companyOptional.isPresent() ? companyOptional.get() :
            // null);
            // }

            // check role
            // if (reqUser.getRole() != null) {
            // Role r = this.roleService.fetchRoleById(reqUser.getRole().getId());
            // currentUser.setRole(r != null ? r : null);
            // }

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
