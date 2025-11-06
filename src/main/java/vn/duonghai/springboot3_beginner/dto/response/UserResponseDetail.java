package vn.duonghai.springboot3_beginner.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.mapping.Set;
import vn.duonghai.springboot3_beginner.entity.Address;
import vn.duonghai.springboot3_beginner.util.Gender;
import vn.duonghai.springboot3_beginner.util.UserStatus;
import vn.duonghai.springboot3_beginner.util.UserType;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@Builder
public class UserResponseDetail implements Serializable {

    private String firstName;

    private String lastName;

    private String email;

    private String phoneNumber;

    private LocalDate dateOfBirth;

    private Gender gender;

    private UserStatus status;

    private String type;

    private String username;
}
