package com.cleviro.ErpManagerApp.dto.mapper.masters;

import com.cleviro.ErpManagerApp.dto.model.masters.LimitCategoryDto;
import com.cleviro.ErpManagerApp.model.masters.LimitCategory;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class LimitCategoryMapper {
    public static LimitCategoryDto toLimitCategoryDto(LimitCategory limitCategory){
        return  new LimitCategoryDto()
                .setId(limitCategory.getId())
                .setName(limitCategory.getName())
                .setPlans(StreamSupport.stream(limitCategory.getPlans().spliterator(),false).map(PlanMapper::toPlanDto).collect(Collectors.toSet()));
    }
}
