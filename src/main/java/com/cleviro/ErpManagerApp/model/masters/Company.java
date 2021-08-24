package com.cleviro.ErpManagerApp.model.masters;

import com.cleviro.ErpManagerApp.model.patients.Visit;
import com.cleviro.ErpManagerApp.model.people.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Set;


@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@Entity
@Table(name = "companies")
public class Company {
    @Id
    @Column(name = "company_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String companyCode;
    private String name;
    private String postalAddress;
    private String state;
    private String city;
    private String phone;
    private String phone1;
    private String phone2;
    private String email;
    private String email1;
    private String email2;
    private String abbreviation;
    private String website;
    private String orderPrefix;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id")
    private Country country;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    private Set<Location> locations;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    private Set<Zone> zones;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    private Set<Designation> designations; //CEO,ACCOUNTANTS,PROGRAMMERS,DOC,NURSE,SPECIALIST,RECEPTIONISTS,

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    private Set<EmploymentType> employmentTypes; //PERMANENT,CONTRACT,COMMISSION

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    private Set<SupplierType> supplierTypes;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    private Set<Employee> employees;
    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    private Set<Customer> customers;
    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    private Set<Dependent> dependents;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    private Set<Visit> visits;

}
