package com.cleviro.ErpManagerApp.model.masters;

import com.cleviro.ErpManagerApp.model.people.Customer;
import com.cleviro.ErpManagerApp.model.people.Employee;
import com.cleviro.ErpManagerApp.model.people.Supplier;
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
@Table(name = "countries")
public class Country {
    @Id
    @Column(name = "country_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private Short mobileCode;
    private String abbreviation;
    private String currency;
    private Byte currencyCode;
    private Short currencySymbol;

    @OneToMany(mappedBy = "country", cascade = CascadeType.ALL)
    private Set<Company> companies;

    @OneToMany(mappedBy = "country", cascade = CascadeType.ALL)
    private Set<Supplier> suppliers;

    @OneToMany(mappedBy = "country", cascade = CascadeType.ALL)
    private Set<Customer> customers;

    @OneToMany(mappedBy = "country", cascade = CascadeType.ALL)
    private Set<Employee> employees;
}
