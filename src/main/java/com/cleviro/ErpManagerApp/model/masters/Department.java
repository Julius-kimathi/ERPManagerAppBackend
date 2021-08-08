package com.cleviro.ErpManagerApp.model.masters;

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
@Table(name = "departments")
public class Department {
    @Id
    @Column(name = "department_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Short id;
    private String name;
    private String description;

    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
    private Set<Employee> employees;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;

    @ManyToMany(mappedBy = "departments")
    private Set<User> users;  //All Users who have access to a department

    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
    private Set<ConsultationRate> consultationRates;

    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
    private Set<DepartmentLimit> departmentLimits;
}
