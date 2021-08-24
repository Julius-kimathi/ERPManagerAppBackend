package com.cleviro.ErpManagerApp.model.billing;

import com.cleviro.ErpManagerApp.model.masters.Department;
import com.cleviro.ErpManagerApp.model.patients.SubVisit;
import com.cleviro.ErpManagerApp.model.patients.Visit;
import com.cleviro.ErpManagerApp.model.patients.enums.PaymentStatuses;
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
@Table(name = "departmentBills")
public class DepartmentBill {
    @Id
    @Column(name = "department_bill_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal billAmount;
    @Enumerated(EnumType.STRING)
    private PaymentStatuses paymentStatus;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private Department department;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "visit_id")
    private Visit visit;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sub_visit_id")
    private SubVisit subVisit;

    @OneToMany(mappedBy = "departmentBill", cascade = CascadeType.ALL)
    private Set<DepartmentBillDetail> departmentBillDetails;

}
