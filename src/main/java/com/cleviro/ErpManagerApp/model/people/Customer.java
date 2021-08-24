package com.cleviro.ErpManagerApp.model.people;

import com.cleviro.ErpManagerApp.model.masters.Company;
import com.cleviro.ErpManagerApp.model.masters.Country;
import com.cleviro.ErpManagerApp.model.masters.Location;
import com.cleviro.ErpManagerApp.model.patients.Visit;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@Entity
@Table(name = "customers", indexes = @Index(name = "idx_customer_email", columnList = "email", unique = true))
public class Customer {
    @Id
    @Column(name = "customer_id")
   // @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String customerCode;
    private String firstName;
    private String middleName;
    private String lastName;
    private LocalDate dob;
    private String email;
    private String phone;
    private String phone1;
    private String idNo;
    private String postalAddress;
    private String city;
    private String state;
    private String status;
    private LocalDate regDate;
    @Enumerated(EnumType.STRING)
    private Genders gender;

    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id")
    private Country country;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id")
    private Location location;

    @OneToMany(mappedBy = "principal", cascade = CascadeType.ALL)
    private Set<Dependent> dependents;

    @OneToMany(mappedBy = "principal", cascade = CascadeType.ALL)
    private Set<Visit> visits;
}
