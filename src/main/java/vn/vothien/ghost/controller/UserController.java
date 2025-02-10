package vn.vothien.ghost.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import vn.vothien.ghost.domain.User;
import vn.vothien.ghost.service.UserService;
import vn.vothien.ghost.util.error.IdInvalidException;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // get all user
    @GetMapping("users")
    public ResponseEntity<List<User>> getAllUser() {
        List<User> fetchUser = this.userService.fetchAllUser();
        return ResponseEntity.status(HttpStatus.OK).body(fetchUser);
    }

    // get user by id
    @GetMapping("users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") long id, String email) {
        User fetchUser = this.userService.fetchUserById(id);
        return ResponseEntity.status(HttpStatus.OK).body(fetchUser);
    }

    // create user
    @PostMapping("/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User postManUser) {
        User ghostUser = this.userService.handleCreateUser(postManUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(ghostUser);
    }

    // delete user
    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") long id) {
        // User currentUser = this.userService.fetchUserById(id);
        this.userService.handleDeleteUser(id);
        return ResponseEntity.ok(null);
    }

    // put user
    @PutMapping("/users")
    public ResponseEntity<User> updateUser(@Valid @RequestBody User user) throws IdInvalidException {
        User ghostUser = this.userService.handleUpdateUser(user);
        return ResponseEntity.ok(ghostUser);
    }
}