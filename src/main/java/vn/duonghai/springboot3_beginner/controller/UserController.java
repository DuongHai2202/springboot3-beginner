package vn.duonghai.springboot3_beginner.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import vn.duonghai.springboot3_beginner.dto.request.UserRequest;
import vn.duonghai.springboot3_beginner.dto.response.PageResponse;
import vn.duonghai.springboot3_beginner.dto.response.ResponseData;
import vn.duonghai.springboot3_beginner.dto.response.ResponseError;
import vn.duonghai.springboot3_beginner.dto.response.UserResponseDetail;
import vn.duonghai.springboot3_beginner.service.UserService;
import vn.duonghai.springboot3_beginner.util.UserStatus;

@RestController
@RequestMapping("/user")
@Slf4j
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    private static final String ERROR_MESSAGE = "errorMessage={}";

    @Operation(method = "POST", summary = "Add new user", description = "Send a request via api add new user")
    @PostMapping(value = "/")
    public ResponseData<Long> addUser(@Valid @RequestBody UserRequest request) {
        log.info("Request add user, {} {}", request.getFirstName(), request.getLastName());

        try {
            long userId = userService.addUser(request);
            return new ResponseData<>(HttpStatus.OK.value(), "Add user successfully", userId);
        } catch (Exception e) {
            log.error(ERROR_MESSAGE, e.getMessage(), e.getCause());
            return new ResponseError(HttpStatus.BAD_REQUEST.value(), "Add user failed");
        }
    }

    @Operation(method = "PUT", summary = "Update user", description = "Send a request via api update user")
    @PutMapping("/{userId}")
    public ResponseData<Void> updateUser(@PathVariable @Min(1) long userId, @Valid @RequestBody UserRequest request) {
        log.info("Request update userId, {}", userId);

        try {
            userService.updateUser(userId, request);
            return new ResponseData<>(HttpStatus.ACCEPTED.value(), "Add user successfully");
        } catch (Exception e) {
            log.error(ERROR_MESSAGE, e.getMessage(), e.getCause());
            return new ResponseError(HttpStatus.BAD_REQUEST.value(), "Add user failed");
        }
    }

    @Operation(method = "PATCH", summary = "Update status", description = "Send a request via api update status")
    @PatchMapping("/{userId}")
    public ResponseData<Void> changeStatus(@PathVariable @Min(1) long userId, @RequestBody UserStatus status) {
        log.info("Request change status userId={}", userId);

        try {
            userService.changeStatus(userId, status);
            return new ResponseData<>(HttpStatus.ACCEPTED.value(), "Change Status Successfully");
        } catch (Exception e) {
            log.error(ERROR_MESSAGE, e.getMessage(), e.getCause());
            return new ResponseError(HttpStatus.BAD_REQUEST.value(), "Change Status failed");
        }
    }

    @Operation(method = "DELETE", summary = "Delete user permanent", description = "Send a request via api delete user")
    @DeleteMapping("{userId}")
    public ResponseData<Void> deleteUser(@PathVariable @Min(1) long userId) {
        log.info("Request update delete userId={}", userId);

        try {
            userService.deleteUser(userId);
            return new ResponseData<>(HttpStatus.NO_CONTENT.value(), "Delete User Successfully");
        } catch (Exception e) {
            log.error(ERROR_MESSAGE, e.getMessage(), e.getCause());
            return new ResponseError(HttpStatus.BAD_REQUEST.value(), "Delete User failed");
        }
    }

    @Operation(method = "GET", summary = "Get user by id", description = "Send a request via api get user")
    @GetMapping("/{userId}")
    public ResponseData<UserResponseDetail> getUser(@PathVariable @Min(1) long userId) {
        log.info("Request get user detail, userId={}", userId);

        try {
            UserResponseDetail user = userService.getUser(userId);
            return new ResponseData<>(HttpStatus.OK.value(), "Get User Detail Successfully", user);
        } catch (Exception e) {
            log.error(ERROR_MESSAGE, e.getMessage(), e.getCause());
            return new ResponseError(HttpStatus.BAD_REQUEST.value(), "Get user detail failed");
        }
    }

    @Operation(method = "GET", summary = "Get list user by pageNo", description = "Send a request via api get user by pageNo")
    @GetMapping("/list")
    public ResponseData<PageResponse> getAllUser(@RequestParam(defaultValue = "0", required = false) int pageNo,
                                                 @Min(10) @RequestParam(defaultValue = "20", required = false) int pageSize) {
        log.info("Request get all user, pageNo={}, pageSize={}", pageNo, pageSize);

        try {
            PageResponse<?> users = userService.getAllUser(pageNo, pageSize);
            return new ResponseData<>(HttpStatus.OK.value(), "Get All User", users);
        } catch (Exception e) {
            log.error(ERROR_MESSAGE, e.getMessage(), e.getCause());
            return new ResponseError(HttpStatus.BAD_REQUEST.value(), "get list user failed");
        }
    }
}
