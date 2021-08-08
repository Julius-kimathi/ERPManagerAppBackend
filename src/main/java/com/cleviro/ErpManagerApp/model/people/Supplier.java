package com.cleviro.ErpManagerApp.model.people;

import com.cleviro.ErpManagerApp.model.masters.Country;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@Entity
@Table(name = "suppliers", indexes = @Index(name = "idx_employee_email", columnList = "email", unique = true))
public class Supplier {
    @Id
    @Column(name = "supplier_id")
   // @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String supplierCode;
    private String name;
    private String email;
    private String phone;
    private String phone1;
    private String kraPin;
    private String postalAddress;
    private String state;
    private String city;
    private String status;
    private Date regDate;
    private String website;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id")
    private Country country;

    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "supplier_type_id")
    private SupplierType supplierType;
}
