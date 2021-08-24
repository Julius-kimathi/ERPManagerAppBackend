package com.cleviro.ErpManagerApp.model.masters;

import com.cleviro.ErpManagerApp.model.patients.Visit;
import com.cleviro.ErpManagerApp.model.people.Employee;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@Entity
@Table(name = "consultation_rates")
public class ConsultationRate {
    @Id
    @Column(name = "consultation_rate_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private BigDecimal fees;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "consultation_type_id")
    private ConsultationType consultationType;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    private Employee doctor;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id")
    private Location location;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "scheme_id")
    private Scheme scheme;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private Department department;
    @OneToMany(mappedBy = "consultationRate", cascade = CascadeType.ALL)
    private Set<Visit> visits;
}
