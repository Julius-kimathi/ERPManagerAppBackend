package com.cleviro.ErpManagerApp.dto.mapper.masters;

import com.cleviro.ErpManagerApp.dto.model.masters.PlanCategoryDto;
import com.cleviro.ErpManagerApp.model.masters.PlanCategory;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class PlanCategoryMapper {
    public static PlanCategoryDto toPlanCategoryDto(PlanCategory planCategory){
        return new PlanCategoryDto()
                .setId(planCategory.getId())
                .setName(planCategory.getName())
                .setPlans(StreamSupport.stream(planCategory.getPlans().spliterator(),false).map(PlanMapper::toPlanDto).collect(Collectors.toSet()));
    }
}
