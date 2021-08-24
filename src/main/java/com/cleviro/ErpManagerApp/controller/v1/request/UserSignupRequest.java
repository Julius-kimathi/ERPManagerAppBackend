package com.cleviro.ErpManagerApp.controller.v1.request;

import com.cleviro.ErpManagerApp.model.people.UserType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserSignupRequest {
    @NotEmpty
    @Email
    private String email;
    @NotEmpty
    @Size(min = 2, message = "user name should have at least 2 characters")
    private String firstName;
  @NotEmpty
    private String lastName;
    private String middleName;
    @NotEmpty
    @Size(min = 8, message = "password should have at least 8 characters")
    private String password;

    @Enumerated(EnumType.STRING)
    private UserType userType;

}
