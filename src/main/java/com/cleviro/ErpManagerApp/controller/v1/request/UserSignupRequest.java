package com.cleviro.ErpManagerApp.controller.v1.request;

import com.cleviro.ErpManagerApp.model.people.UserType;
import com.cleviro.ErpManagerApp.validation.ValueOfEnum;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserSignupRequest {
    @NotEmpty @Email
    private String email;
    @NotEmpty @Size(min = 2)
    private String firstName;
    @NotEmpty
    private String lastName;
    private String middleName;
    @NotEmpty @Size(min = 8)
    private String password;

    @NotNull @ValueOfEnum(enumClass = UserType.class)
    private String userTypeString;


     /*
    @Enumerated(EnumType.STRING) @NotNull
    private UserType userType;

  @Pattern(regexp = "CUSTOMER|EMPLOYEE|SUPPLIER") //does not work
    private String getUserTypeName() {
        return userType.name();
    }*/
}
