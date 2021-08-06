package com.cleviro.ErpManagerApp.model.people;

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
@Table(name = "supervisors")
public class Supervisor {
    @Id
    @Column(name = "supervisor_id")
   // @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @OneToOne
    @MapsId
    @JoinColumn(name = "employee_id")
    private Employee supervisorDetails;
    @OneToMany(mappedBy = "supervisor", cascade = CascadeType.ALL)
    private Set<Employee> supervises;
}
