package vn.duonghai.springboot3_beginner.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.*;
import vn.duonghai.springboot3_beginner.dto.AddressDTO;
import vn.duonghai.springboot3_beginner.util.Gender;
import vn.duonghai.springboot3_beginner.util.UserStatus;
import vn.duonghai.springboot3_beginner.util.UserType;
import vn.duonghai.springboot3_beginner.util.validator.EnumPattern;
import vn.duonghai.springboot3_beginner.util.validator.EnumValue;
import vn.duonghai.springboot3_beginner.util.validator.GenderSubSet;
import vn.duonghai.springboot3_beginner.util.validator.PhoneNumber;

import static vn.duonghai.springboot3_beginner.util.Gender.*;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {

    @NotBlank(message = "firstName must be not blank") // Khong cho phep gia tri blank
    @Size(min = 2, max = 50, message = "firstName must be between 2 - 50 characters")
    private String firstName;

    @NotNull(message = "lastName must be not null") // Khong cho phep gia tri
    @Size(min = 2, max = 50, message = "lastName must be between 2 - 50 characters")
    private String lastName;

    @Email(message = "email invalid format") // Chi chap nhan nhung gia tri dung dinh dang email
    private String email;

    @PhoneNumber(message = "phone invalid format")
    private String phoneNumber;

    @NotNull(message = "dateOfBirth must be not null")
    @JsonFormat(pattern = "MM/dd/yyyy")
    private LocalDate dateOfBirth;

    //@Pattern(regexp = "^male|female|other$", message = "gender must be one in {male, female, other}")
    @GenderSubSet(anyOf = {MALE, FEMALE, OTHER})
    private Gender gender;

    @NotNull(message = "username must be not null")
    @Size(min = 6, message = "username must be greater 6 characters")
    private String username;

    @NotNull(message = "password must be not null")
    @Size(min = 8, message = "password must be at least 8 characters")
    private String password;

    @NotNull(message = "type must be not null")
    @EnumValue(name = "type", enumClass = UserType.class)
    private String type;

    @EnumPattern(name = "status", regexp = "ACTIVE|INACTIVE|NONE")
    private UserStatus status;

    @NotEmpty(message = "addresses can not empty")
    private Set<AddressDTO> addresses;
}
