package com.cleviro.ErpManagerApp.model.patients;

import com.cleviro.ErpManagerApp.model.billing.DepartmentBill;
import com.cleviro.ErpManagerApp.model.masters.*;
import com.cleviro.ErpManagerApp.model.masters.enums.BillTypes;
import com.cleviro.ErpManagerApp.model.people.Customer;
import com.cleviro.ErpManagerApp.model.people.Dependent;
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
@Table(name = "visits", indexes = @Index(name = "idx_visit_code", columnList = "visitCode", unique = true))
public class Visit {
    @Id
    @Column(name = "visit_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String visitCode;
    private LocalDateTime visitDate;
    @Enumerated(EnumType.STRING)
    private BillTypes billType;
    private BigDecimal availableLimit;
    private Boolean overallPaymentStatus;
    private BigDecimal copay;
    private String invoiceNo;
    private BigDecimal invoiceAmount;

    @OneToOne(mappedBy = "visit", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn
    private Triage triage;
    @OneToOne(mappedBy = "visit", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn
    private Consultation consultation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer principal;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dependent_id")
    private Dependent dependent;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "payer_account_id")
    private PayerAccount payerAccount;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "plan_id")
    private Plan plan;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "consultation_rate_id")
    private ConsultationRate consultationRate;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id")
    private Location location;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;

    @OneToMany(mappedBy = "visit", cascade = CascadeType.ALL)
    private Set<DepartmentBill> departmentBills;
    @OneToMany(mappedBy = "visit", cascade = CascadeType.ALL)
    private Set<SubVisit> subVisits;
}
