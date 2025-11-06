package vn.duonghai.springboot3_beginner.util;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum UserType {
    @JsonProperty("OWNER")
    OWNER,
    @JsonProperty("ADMIN")
    ADMIN,
    @JsonProperty("USER")
    USER;
}
