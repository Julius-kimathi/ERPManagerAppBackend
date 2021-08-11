package com.cleviro.ErpManagerApp.controller.v1.request.masters;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class AddPlanRequest {
    private int id;
    @NotEmpty(message = "{constraints.NotEmpty.message}")
    private String name;
    private Boolean hasDepartmentalLimits;
    private Boolean hasDepartmentalCopay;
    private BigDecimal copay;
    private BigDecimal overallLimit;
    private BigDecimal visitLimit;
    @NotEmpty(message = "{constraints.NotEmpty.message}")
    private LocalDate validityStartDate;
    @NotEmpty(message = "{constraints.NotEmpty.message}")
    private LocalDate validityEndDate;
    private Boolean status;
    private Boolean hasRegistrationFees;
    private Short subVisitPeriodInDays; //No of days until a visit ain't considered a followup
    private Boolean skipCopayForSubVisits;

    @NotEmpty(message = "{constraints.NotEmpty.message}")
    private Integer payerAccountId;
    @NotEmpty(message = "{constraints.NotEmpty.message}")
    private Short planCategoryId;
    @NotEmpty(message = "{constraints.NotEmpty.message}")
    private Short limitCategoryId;
    @NotEmpty(message = "{constraints.NotEmpty.message}")
    private Short copayCategoryId;
    private Set<AddDepartmentLimitRequest> departmentLimitRequests;
}
