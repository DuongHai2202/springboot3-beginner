package vn.duonghai.springboot3_beginner.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import vn.duonghai.springboot3_beginner.dto.AddressDTO;
import vn.duonghai.springboot3_beginner.dto.request.UserRequest;
import vn.duonghai.springboot3_beginner.dto.response.PageResponse;
import vn.duonghai.springboot3_beginner.dto.response.UserResponseDetail;
import vn.duonghai.springboot3_beginner.entity.Address;
import vn.duonghai.springboot3_beginner.entity.User;
import vn.duonghai.springboot3_beginner.exception.ResourceNotFoundException;
import vn.duonghai.springboot3_beginner.repository.UserRepository;
import vn.duonghai.springboot3_beginner.service.UserService;
import vn.duonghai.springboot3_beginner.util.UserStatus;
import vn.duonghai.springboot3_beginner.util.UserType;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    /**
     * Save new user to database
     *
     * @param request
     * @return userID
     */
    @Override
    public long addUser(UserRequest request) {
        User user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .dateOfBirth(request.getDateOfBirth())
                .phoneNumber(request.getPhoneNumber())
                .username(request.getUsername())
                .password(request.getPassword())
                .gender(request.getGender())
                .status(request.getStatus())
                .type(UserType.valueOf(request.getType().toUpperCase()))
//                .addresses(convertToAddress(request.getAddresses()))
                .build();
        request.getAddresses().forEach(a ->
                user.saveAddress(Address.builder()
                        .apartmentNumber(a.getApartmentNumber())
                        .floor(a.getFloor())
                        .building(a.getBuilding())
                        .streetNumber(a.getStreetNumber())
                        .street(a.getStreet())
                        .city(a.getCity())
                        .country(a.getCountry())
                        .addressType(a.getAddressType())
                        .build()));

        userRepository.save(user);

        log.info("User had added successfully userId={}", user.getId());

        return user.getId();
    }

    /**
     * Update user by userId
     *
     * @param userId
     * @param request
     */
    @Override
    public void updateUser(long userId, UserRequest request) {
        User user = getUserById(userId);

        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setDateOfBirth(request.getDateOfBirth());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        user.setGender(request.getGender());
        user.setStatus(request.getStatus());
        user.setType(UserType.valueOf(request.getType().toUpperCase()));
        user.setAddresses(convertToAddress(request.getAddresses()));

        userRepository.save(user);

        log.info("User had updated successfully userId={}", user.getId());
    }

    /**
     * Change status of user by userId
     *
     * @param userId
     * @param userStatus
     */
    @Override
    public void changeStatus(long userId, UserStatus userStatus) {
        User user = getUserById(userId);
        user.setStatus(userStatus);
        userRepository.save(user);
        log.info("User had updated status successfully userId={}", user.getId());
    }


    /**
     * Delete user by userId
     *
     * @param userId
     */
    @Override
    public void deleteUser(long userId) {
        userRepository.deleteById(userId);
        log.info("User had deleted permanent successfully userId={}", userId);
    }

    /**
     * Get user detail by userId
     *
     * @param userId
     * @return UserResponseDetail
     */
    @Override
    public UserResponseDetail getUser(long userId) {
        User user = getUserById(userId);

        return UserResponseDetail.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .dateOfBirth(user.getDateOfBirth())
                .gender(user.getGender())
                .phoneNumber(user.getPhoneNumber())
                .email(user.getEmail())
                .username(user.getUsername())
                .status(user.getStatus())
                .type(user.getType().name())
                .build();
    }

    /**
     * Get list user per pageNo and pageSize
     *
     * @param pageNo
     * @param pageSize
     * @return PageResponse
     */
    @Override
    public PageResponse getAllUser(int pageNo, int pageSize) {
        Page<User> page = userRepository.findAll(PageRequest.of(pageNo, pageSize));

        List<UserResponseDetail> list = page.stream().map(user -> UserResponseDetail.builder()
                        .firstName(user.getFirstName())
                        .lastName(user.getLastName())
                        .dateOfBirth(user.getDateOfBirth())
                        .gender(user.getGender())
                        .phoneNumber(user.getPhoneNumber())
                        .email(user.getEmail())
                        .username(user.getUsername())
                        .status(user.getStatus())
                        .type(user.getType().name())
                        .build())
                .toList();

        return PageResponse.builder()
                .pageNo(pageNo)
                .pageSize(pageSize)
                .item(list)
                .totalPages(page.getTotalPages())
                .build();
    }

    /**
     * Get user by userId
     *
     * @param userId
     * @return User
     */
    private User getUserById(long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("userId not found"));
    }

    /**
     * Convert Set<AddressDTO> to Set<Address>
     *
     * @param addresses
     * @return Set<Address>
     */
    private Set<Address> convertToAddress(Set<AddressDTO> addresses) {
        Set<Address> result = new HashSet<>();
        addresses.forEach(a ->
                result.add(Address.builder()
                        .apartmentNumber(a.getApartmentNumber())
                        .floor(a.getFloor())
                        .building(a.getBuilding())
                        .streetNumber(a.getStreetNumber())
                        .street(a.getStreet())
                        .city(a.getCity())
                        .country(a.getCountry())
                        .addressType(a.getAddressType())
                        .build())
        );

        return result;
    }
}
