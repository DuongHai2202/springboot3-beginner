package vn.duonghai.springboot3_beginner.service;

import vn.duonghai.springboot3_beginner.dto.request.UserRequest;
import vn.duonghai.springboot3_beginner.dto.response.PageResponse;
import vn.duonghai.springboot3_beginner.dto.response.UserResponseDetail;
import vn.duonghai.springboot3_beginner.util.UserStatus;

public interface UserService {

    long addUser(UserRequest request);

    void updateUser(long userId, UserRequest request);

    void changeStatus(long userId, UserStatus userStatus);

    void deleteUser(long userId);

    UserResponseDetail getUser(long userId);

    PageResponse getAllUser(int pageNo, int pageSize);
}
