package com.cleviro.ErpManagerApp.controller.v1.request.people;

import com.cleviro.ErpManagerApp.model.people.Genders;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class AddCustomerRequest {
    private Long id;
    @NotEmpty(message = "{constraints.NotEmpty.message}")
    @Pattern(regexp="[a-z]*", flags = Pattern.Flag.CASE_INSENSITIVE)
    private String firstName;
    private String middleName;
    @NotEmpty(message = "{constraints.NotEmpty.message}")
    private String lastName;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Past(message = "{constraints.Past.message}")
    private LocalDate dob;
    @NotEmpty(message = "{constraints.NotEmpty.message}")
    @Email(message = "{constraints.Email.message}")
    private String email;
    @NotEmpty(message = "{constraints.NotEmpty.message}")
    private String phone;
    private String phone1;
    private String idNo;
    private String postalAddress;
    @NotEmpty(message = "{constraints.NotEmpty.message}")
    private String city;
    private String state;
    private String status;
    @Enumerated(EnumType.STRING)
    private Genders gender;

    private int countryId;
    private Short customerTypeId;
    private Integer locationId;
}
