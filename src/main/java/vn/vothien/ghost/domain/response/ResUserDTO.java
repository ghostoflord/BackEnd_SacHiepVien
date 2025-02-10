package vn.vothien.ghost.domain.response;

import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResUserDTO {
    private long id;
    private String userName;
    private int password;
    private String email;
    private String address;
    private Instant created_at;
    private Instant update_at;
    private String gender;

}
