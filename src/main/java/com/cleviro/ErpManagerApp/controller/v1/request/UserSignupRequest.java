package com.cleviro.ErpManagerApp.controller.v1.request;

import com.cleviro.ErpManagerApp.model.people.UserType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserSignupRequest {
    @NotBlank(message = "Email is mandatory")
    private String email;
  @NotNull(message = "firstName can't be Null")
    private String firstName;
    @NotEmpty(message = "{constraints.NotEmpty.message}")
    private String lastName;
    @NotEmpty(message = "{constraints.NotEmpty.message}")
    private String middleName;
    @NotBlank(message = "Password is required")
    @Size(min = 6, message = "Password must be longer than 6 characters")
    private String password;

    private UserType userType;

}
