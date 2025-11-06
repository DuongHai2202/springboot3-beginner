package vn.duonghai.springboot3_beginner.util;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum UserStatus {
    @JsonProperty("ACTIVE")
    ACTIVE,
    @JsonProperty("INACTIVE")
    INACTIVE,
    @JsonProperty("NONE")
    NONE;
}
