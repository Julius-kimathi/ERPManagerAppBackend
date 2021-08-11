package com.cleviro.ErpManagerApp.dto.mapper.masters;

import com.cleviro.ErpManagerApp.dto.model.masters.*;
import com.cleviro.ErpManagerApp.model.masters.Plan;
import org.modelmapper.ModelMapper;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class PlanMapper {
    public static PlanDto toPlanDto(Plan plan){
        return new PlanDto()
                .setId(plan.getId())
                .setPlanCode(plan.getPlanCode())
                .setName(plan.getName())
                .setHasDepartmentalLimits(plan.getHasDepartmentalLimits())
                .setHasDepartmentalCopay(plan.getHasDepartmentalCopay())
                .setCopay(plan.getCopay())
                .setOverallLimit(plan.getOverallLimit())
                .setVisitLimit(plan.getVisitLimit())
                .setValidityStartDate(plan.getValidityStartDate())
                .setValidityEndDate(plan.getValidityEndDate())
                .setStatus(plan.getStatus())
                .setHasRegistrationFees(plan.getHasRegistrationFees())
                .setSubVisitPeriodInDays(plan.getSubVisitPeriodInDays())
                .setSkipCopayForSubVisits(plan.getSkipCopayForSubVisits())
                .setPayerAccount(new ModelMapper().map(plan.getPayerAccount(), PayerAccountDto.class))
                .setPlanCategory(new ModelMapper().map(plan.getPlanCategory(), PlanCategoryDto.class))
                .setLimitCategory(new ModelMapper().map(plan.getLimitCategory(), LimitCategoryDto.class))
                .setCopayCategory(new ModelMapper().map(plan.getCopayCategory(), CopayCategoryDto.class))
                .setDepartmentLimits(StreamSupport.stream(plan.getDepartmentLimits().spliterator(),false).map(DepartmentLimitMapper::toDepartmentLimitDto).collect(Collectors.toSet()));
    }
}
