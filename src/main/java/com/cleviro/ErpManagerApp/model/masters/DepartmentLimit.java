package com.cleviro.ErpManagerApp.model.masters;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@Entity
@Table(name = "department_limits")
public class DepartmentLimit {
    @Id
    @Column(name = "department_limit_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Short id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private Department department;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "plan_id")
    private Plan plan;
    private BigDecimal overallLimit;
    private BigDecimal visitLimit;
    private BigDecimal copay;
}
