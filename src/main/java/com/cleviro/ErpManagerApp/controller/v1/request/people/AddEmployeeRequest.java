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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class AddEmployeeRequest {
    private Long id;
    @NotEmpty(message = "{constraints.NotEmpty.message}")
    private String firstName;
    @NotEmpty(message = "{constraints.NotEmpty.message}")
    private String middleName;
    @NotEmpty(message = "{constraints.NotEmpty.message}")
    private String lastName;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @NotNull(message = "{constraints.NotEmpty.message}")
    @Temporal(TemporalType.DATE)
    private Date dob;
    @NotEmpty(message = "{constraints.NotEmpty.message}")
    private String email;
    @NotEmpty(message = "{constraints.NotEmpty.message}")
    private String phone;
    private String phone1;
    @NotEmpty(message = "{constraints.NotEmpty.message}")
    private String idNo;
    private String postalAddress;
    @NotEmpty(message = "{constraints.NotEmpty.message}")
    private String city;
    private String state;
    private String status;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @NotNull(message = "{constraints.NotEmpty.message}")
    @Temporal(TemporalType.DATE)
    private Date regDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @NotNull(message = "{constraints.NotEmpty.message}")
    @Temporal(TemporalType.DATE)
    private Date validityDate;
    private String payrollNo;
    @Enumerated(EnumType.STRING)
    private Genders gender;

    @NotEmpty(message = "{constraints.NotEmpty.message}")
    private int companyId;
    @NotEmpty(message = "{constraints.NotEmpty.message}")
    private int countryId;
    @NotEmpty(message = "{constraints.NotEmpty.message}")
    private int locationId;
    @NotEmpty(message = "{constraints.NotEmpty.message}")
    private Short designationId;
    @NotEmpty(message = "{constraints.NotEmpty.message}")
    private Short employmentTypeId;
    @NotEmpty(message = "{constraints.NotEmpty.message}")
    private Long supervisorId;
    @NotEmpty(message = "{constraints.NotEmpty.message}")
    private short departmentId;
}
