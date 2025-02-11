package vn.vothien.ghost.domain;

import java.time.Instant;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import vn.vothien.ghost.domain.Role;

@Entity
@Table(name = "User")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    private String userName;
    private String password;
    private String email;
    private String address;
    private String refresh_token;
    private Instant created_at;
    private Instant created_by;
    private Instant update_at;
    private Instant update_by;
    private String gender;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    public Instant getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Instant created_at) {
        this.created_at = created_at;
    }

    public Instant getCreated_by() {
        return created_by;
    }

    public void setCreated_by(Instant created_by) {
        this.created_by = created_by;
    }

    public Instant getUpdate_at() {
        return update_at;
    }

    public void setUpdate_at(Instant update_at) {
        this.update_at = update_at;
    }

    public Instant getUpdate_by() {
        return update_by;
    }

    public void setUpdate_by(Instant update_by) {
        this.update_by = update_by;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", userName=" + userName + ", password=" + password + ", email=" + email
                + ", address=" + address + ", refresh_token=" + refresh_token + ", created_at=" + created_at
                + ", created_by=" + created_by + ", update_at=" + update_at + ", update_by=" + update_by + ", gender="
                + gender + "]";
    }
}
