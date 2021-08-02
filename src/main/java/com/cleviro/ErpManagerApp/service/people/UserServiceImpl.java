package com.cleviro.ErpManagerApp.service.people;

import com.cleviro.ErpManagerApp.dto.mapper.people.UserMapper;
import com.cleviro.ErpManagerApp.dto.model.people.UserDto;
import com.cleviro.ErpManagerApp.exception.ERPException;
import com.cleviro.ErpManagerApp.exception.EntityType;
import com.cleviro.ErpManagerApp.exception.ExceptionType;
import com.cleviro.ErpManagerApp.model.people.Role;
import com.cleviro.ErpManagerApp.model.people.User;
import com.cleviro.ErpManagerApp.model.people.UserRoles;
import com.cleviro.ErpManagerApp.model.people.UserType;
import com.cleviro.ErpManagerApp.repository.people.RoleRepository;
import com.cleviro.ErpManagerApp.repository.people.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class UserServiceImpl implements UserService{

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
   private ModelMapper modelMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;


    @Override
    public UserDto signup(UserDto userDto) {
        Role userRole;
        User user = userRepository.findByEmail(userDto.getEmail());
        if (user == null){
           userRole = roleRepository.findByRole(UserRoles.USER);

            user = new User()
                    .setUserType(UserType.CUSTOMER)
                    .setEmail(userDto.getEmail())
                    .setFirstName(userDto.getFirstName())
                    .setLastName(userDto.getLastName())
                    .setMiddleName(userDto.getMiddleName())
                    .setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()))
                    .setRoles(new HashSet<>(Arrays.asList(userRole)));
            return UserMapper.toUserDto(userRepository.save(user));
        }
        throw exception(EntityType.USER, ExceptionType.DUPLICATE_ENTITY, userDto.getEmail());
    }

    @Override
    public UserDto findUserById(Long id){
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()){
            return UserMapper.toUserDto(user.get());
        }
        throw exception(EntityType.USER, ExceptionType.ENTITY_NOT_FOUND, String.valueOf(id));
    }

    @Transactional
    public UserDto findUserByEmail(String email) {
        Optional<User> user = Optional.ofNullable(userRepository.findByEmail(email));
        if (user.isPresent())
            return modelMapper.map(user.get(), UserDto.class);
        else
      throw exception(EntityType.USER,ExceptionType.ENTITY_NOT_FOUND,email);
    }

    @Override
    public Set<UserDto> findAllUsers(){

        return StreamSupport
                .stream(userRepository.findAll().spliterator(), false)
                .map(user -> modelMapper.map(user, UserDto.class))
                .collect(Collectors.toSet());
    }

    @Override
    public UserDto updateProfile(UserDto userDto) {
        Optional<User> user = Optional.ofNullable(userRepository.findByEmail(userDto.getEmail()));
        if (user.isPresent()){
            User userModel = user.get()
                    .setFirstName(userDto.getFirstName())
                    .setLastName(userDto.getLastName())
                    .setMiddleName(userDto.getMiddleName())
                    .setEmail(userDto.getEmail())
                    .setUserType(userDto.getUserType());
            return UserMapper.toUserDto(userRepository.save(userModel));
        }
        throw exception(EntityType.USER,ExceptionType.ENTITY_NOT_FOUND,userDto.getEmail());
    }

    @Override
    public UserDto changePassword(UserDto userDto, String newPassword){
        Optional<User> user = Optional.ofNullable(userRepository.findByEmail(userDto.getEmail()));
        if (user.isPresent()){
            User userModel = user.get()
                    .setPassword(bCryptPasswordEncoder.encode(newPassword));
            return UserMapper.toUserDto(userRepository.save(userModel));
        }
        throw  exception(EntityType.USER,ExceptionType.ENTITY_NOT_FOUND, userDto.getEmail());
    }


    /*
     * Returns a new RuntimeException
     *
     * @param entityType
     * @param exceptionType
     * @param args
     * @return
     */
    private RuntimeException exception(EntityType entityType, ExceptionType exceptionType, String... args) {
        return ERPException.throwException(entityType, exceptionType, args);
    }
}
