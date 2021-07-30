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
@Table(name = "customers", indexes = @Index(name = "idx_employee_email", columnList = "email", unique = true))
public class Customer {
    @Id
    @Column(name = "customer_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String middleName;
    private String lastName;
    private Date dob;
    private String email;
    private String phone;
    private String phone1;
    private String idNo;
    private String postalAddress;
    private String city;
    private String state;
    private String status;
    private Date regDate;
    @Enumerated(EnumType.STRING)
    private Genders gender;

    @OneToOne(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id")
    private Country country;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_type_id")
    private CustomerType customerType;
}
