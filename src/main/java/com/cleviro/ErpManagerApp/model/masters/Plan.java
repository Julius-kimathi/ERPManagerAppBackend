package com.cleviro.ErpManagerApp.model.masters;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@Entity
@Table(name = "plans")
public class Plan {
    @Id
    @Column(name = "plan_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String planCode;
    private String name;
    private Boolean hasDepartmentalLimits;
    private Boolean hasDepartmentalCopay;
    private BigDecimal copay;
    private BigDecimal overallLimit;
    private BigDecimal visitLimit;
    private LocalDate validityStartDate;
    private LocalDate validityEndDate;
    private Boolean status;
    private Boolean hasRegistrationFees;
    private Short subVisitPeriodInDays; //No of days until a visit ain't considered a followup
    private Boolean skipCopayForSubVisits;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "payer_account_id")
    private PayerAccount payerAccount;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "plan_category_id")
    private PlanCategory planCategory;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "limit_category_id")
    private LimitCategory limitCategory;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "copay_category_id")
    private CopayCategory copayCategory;
    @OneToMany(mappedBy = "plan", cascade = CascadeType.ALL)
    private Set<DepartmentLimit> departmentLimits;
}
