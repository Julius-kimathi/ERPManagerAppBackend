package com.cleviro.ErpManagerApp.model.masters;

import com.cleviro.ErpManagerApp.model.patients.SubVisit;
import com.cleviro.ErpManagerApp.model.patients.Visit;
import com.cleviro.ErpManagerApp.model.people.Customer;
import com.cleviro.ErpManagerApp.model.people.Dependent;
import com.cleviro.ErpManagerApp.model.people.Employee;
import com.cleviro.ErpManagerApp.model.people.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Accessors(chain = true)
@Table(name = "locations")
public class Location {
    @Id
    @Column(name = "location_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String locationCode;
    private String name;
    private String abbreviation;
    private String postalAddress;
    private String state;
    private String city;
    private String phone;
    private String phone1;
    private String email;
    private String email1;
    private String status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "zone_id")
    private Zone zone;

    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL)
    private Set<Store> stores;

    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL)
    private Set<Employee> employees;

    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL)
    private Set<Customer> customers;
    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL)
    private Set<Dependent> dependents;

    @ManyToMany(mappedBy = "locations")
    private Set<User> users;  //All Users who have access to a location

    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL)
    private Set<ConsultationRate> consultationRates;
    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL)
    private Set<Visit> visits;
    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL)
    private Set<SubVisit> subVisits;
}
