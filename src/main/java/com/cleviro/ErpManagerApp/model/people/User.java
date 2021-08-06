package com.cleviro.ErpManagerApp.model.people;

import com.cleviro.ErpManagerApp.model.masters.Department;
import com.cleviro.ErpManagerApp.model.masters.Location;
import com.cleviro.ErpManagerApp.model.masters.Store;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@Entity
@Table(name = "users", indexes = @Index(name = "idx_user_email", columnList = "email", unique = true))
public class User {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String middleName;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles", joinColumns = {@JoinColumn(name = "user_id")}, inverseJoinColumns = {@JoinColumn(name="role_id")})
    private Collection<Role> roles;



    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn
    private Employee employee;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn
    private Customer customer;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn
    private Supplier supplier;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_locations", joinColumns = {@JoinColumn(name = "user_id")}, inverseJoinColumns = {@JoinColumn(name="location_id")})
    private Set<Location> locations;  //User has access to these locations

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_stores", joinColumns = {@JoinColumn(name = "user_id")}, inverseJoinColumns = {@JoinColumn(name="store_id")})
    private Set<Store> stores;  //User has access to these stores

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_departments", joinColumns = {@JoinColumn(name = "user_id")}, inverseJoinColumns = {@JoinColumn(name="department_id")})
    private Set<Department> departments;  //User has access to these departments


    @Enumerated(EnumType.STRING)
    private UserType userType; //CUSTOMER,EMPLOYEE,SUPPLIER


    public String getFullName(){
        return firstName != null && middleName != null ? firstName.concat(" ").concat(middleName).concat(" ").concat(lastName) : "";
    }

}
