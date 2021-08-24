package com.cleviro.ErpManagerApp.model.patients;

import com.cleviro.ErpManagerApp.model.billing.DepartmentBill;
import com.cleviro.ErpManagerApp.model.masters.Location;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@Entity
@Table(name = "subVisits", indexes = @Index(name = "idx_sub_visit_code", columnList = "subVisitCode", unique = true))
public class SubVisit {
    @Id
    @Column(name = "sub_visit_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String subVisitCode;
    private LocalDateTime subVisitDate;
    private BigDecimal availableLimit;
    private Boolean overallPaymentStatus;
    private BigDecimal copay;
    private String invoiceNo;
    private BigDecimal invoiceAmount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id")
    private Location location;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "visit_id")
    private Visit visit;

    @OneToMany(mappedBy = "subVisit", cascade = CascadeType.ALL)
    private Set<DepartmentBill> departmentBills;
}
