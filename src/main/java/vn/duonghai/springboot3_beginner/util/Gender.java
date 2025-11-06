package vn.duonghai.springboot3_beginner.util;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Gender {
    @JsonProperty("MALE")
    MALE,
    @JsonProperty("FEMALE")
    FEMALE,
    @JsonProperty("OTHER")
    OTHER;
}
