package com.cleviro.ErpManagerApp.dto.model.masters;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@ToString
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PlanDto {
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

    private PayerAccountDto payerAccount;
    private PlanCategoryDto planCategory;
    private LimitCategoryDto limitCategory;
    private CopayCategoryDto copayCategory;
    private Set<DepartmentLimitDto> departmentLimits;
}
