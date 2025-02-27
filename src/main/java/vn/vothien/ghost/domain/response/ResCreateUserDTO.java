package vn.vothien.ghost.domain.response;

import java.time.Instant;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResCreateUserDTO {
    private long id;
    private String userName;
    private String email;
    private String address;
    private String gender;
    private Instant createdAt;
}