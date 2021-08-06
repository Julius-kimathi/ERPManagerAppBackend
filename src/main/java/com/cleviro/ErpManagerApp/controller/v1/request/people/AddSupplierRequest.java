package com.cleviro.ErpManagerApp.controller.v1.request.people;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

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
public class AddSupplierRequest {
    private Long id;
    @NotEmpty(message = "{constraints.NotEmpty.message}")
    private String name;
    @NotEmpty(message = "{constraints.NotEmpty.message}")
    private String email;
    @NotEmpty(message = "{constraints.NotEmpty.message}")
    private String phone;
    private String phone1;
    private String kraPin;
    @NotEmpty(message = "{constraints.NotEmpty.message}")
    private String postalAddress;
    private String state;
    @NotEmpty(message = "{constraints.NotEmpty.message}")
    private String city;
    private String status;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @NotNull(message = "{constraints.NotEmpty.message}")
    @Temporal(TemporalType.DATE)
    private Date regDate;
    private String website;

    @NotEmpty(message = "{constraints.NotEmpty.message}")
    private int countryId;
    @NotEmpty(message = "{constraints.NotEmpty.message}")
    private Short supplierTypeId;
}
