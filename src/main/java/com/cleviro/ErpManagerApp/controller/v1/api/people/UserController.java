package com.cleviro.ErpManagerApp.controller.v1.api.people;

import com.cleviro.ErpManagerApp.controller.v1.request.UserSignupRequest;
import com.cleviro.ErpManagerApp.controller.v1.request.UserUpdateRequest;
import com.cleviro.ErpManagerApp.controller.v1.request.people.LoginRequest;
import com.cleviro.ErpManagerApp.dto.model.people.UserDto;
import com.cleviro.ErpManagerApp.dto.response.Response;
import com.cleviro.ErpManagerApp.model.people.UserType;
import com.cleviro.ErpManagerApp.service.people.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    @ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    public Response getAllUsers(@RequestParam Optional<String> email) {
        if (email.isEmpty()){
            return Response
                    .ok()
                    .setPayload(userService.findAllUsers());
        }else{

            Optional<UserDto> userDto = Optional.ofNullable(userService.findUserByEmail(email.get()));
            if (userDto.isPresent())
                return Response.ok().setPayload(userDto.get());
            else
                return Response.badRequest().setErrors("User not found");
        }

    }

    @GetMapping("/{id}")
    @ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    public Response getUserById(@PathVariable("id") final Long id){

        Optional<UserDto> userDto = Optional.ofNullable(userService.findUserById(id));
        if (userDto.isPresent())
            return Response.ok().setPayload(userDto.get());
        else
          return Response.badRequest().setErrors("User not found");
    }

  /*  @GetMapping("/{email}")
    @ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    public Response getUserByEmail(@RequestParam(value = "email") String email){

        Optional<UserDto> userDto = Optional.ofNullable(userService.findUserByEmail(email));
        if (userDto.isPresent())
            return Response.ok().setPayload(userDto.get());
        else
            return Response.badRequest().setErrors("User not found");
    }*/

    @PostMapping(value ="/auth/login")
    public void signup(@Valid @RequestBody LoginRequest loginRequest){

    }

    @PostMapping(value ="/signup", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Response signup(@Valid @RequestBody UserSignupRequest userSignupRequest){

        UserDto userDto = new UserDto()
                .setUserType(UserType.valueOf(userSignupRequest.getUserTypeString().toUpperCase()))
                .setEmail(userSignupRequest.getEmail())
                .setFirstName(userSignupRequest.getFirstName())
                .setLastName(userSignupRequest.getLastName())
                .setMiddleName(userSignupRequest.getMiddleName())
                .setPassword(userSignupRequest.getPassword());
        return Response.ok().setPayload(userService.signup(userDto));

    }

    @PostMapping("/update")
    @ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    public Response updateUser(@RequestBody @Valid UserUpdateRequest userUpdateRequest){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = (String) auth.getPrincipal();
        System.out.println("logged in user is: "+email);
        Optional<UserDto> userDto = Optional.ofNullable(userService.findUserByEmail(email));
        if (userDto.isPresent()){
            UserDto newUserDto = userDto.get()
                    .setFirstName(userUpdateRequest.getFirstName())
                    .setMiddleName(userUpdateRequest.getMiddleName())
                    .setLastName(userUpdateRequest.getLastName())
                   // .setEmail(userUpdateRequest.getEmail())
                    .setUserType(UserType.valueOf(userUpdateRequest.getUserTypeString().toUpperCase()));

           return Response.ok().setPayload(userService.updateProfile(newUserDto));

        }
        return Response.badRequest().setErrors("Unable to update profile");
    }

    @GetMapping(value = {"logout"})
    public String logout() {
        SecurityContextHolder.getContext().setAuthentication(null);
        return "Successfully logged out";
    }



}
