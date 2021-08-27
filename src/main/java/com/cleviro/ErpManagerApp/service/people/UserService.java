package com.cleviro.ErpManagerApp.service.people;

import com.cleviro.ErpManagerApp.dto.model.people.UserDto;
import com.cleviro.ErpManagerApp.model.masters.Location;
import com.cleviro.ErpManagerApp.model.people.User;

import java.util.Set;


public interface UserService {

    /**
     * Register a new user
     *
     * @param userDto
     * @return
     */
    UserDto signup(UserDto userDto);


    /**
     * Search an existing user by email address
     *
     * @param email
     * @return
     */
    UserDto findUserByEmail(String email);

    /**
     * Search an existing user by email address
     *
     * @param id
     * @return
     */
    UserDto findUserById(Long id);

    /**
     * Search an existing user by email address
     *
     * @param
     * @return
     */
    Set<UserDto> findAllUsers();
    /**
     * Update profile of the user
     *
     * @param userDto
     * @return
     */
    UserDto updateProfile(UserDto userDto);

    /**
     * Update password
     *
     * @param newPassword
     * @return
     */
    UserDto changePassword(UserDto userDto, String newPassword);

    Set<Location> getGrantedLocations(String email);

    User findApplicationUserByEmail(String email);
}
