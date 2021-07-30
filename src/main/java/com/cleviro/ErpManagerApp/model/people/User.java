package com.cleviro.ErpManagerApp.model.people;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Collection;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@Entity
@Table(name = "user", indexes = @Index(name = "idx_user_email", columnList = "email", unique = true))
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
    @JoinTable(name = "user_role", joinColumns = {@JoinColumn(name = "user_id")}, inverseJoinColumns = {@JoinColumn(name="role_id")})
    private Collection<Role> roles;

    @Enumerated(EnumType.STRING)
    private UserType userType;

    public String getFullName(){
        return firstName != null && middleName != null ? firstName.concat(" ").concat(middleName).concat(" ").concat(lastName) : "";
    }

}
