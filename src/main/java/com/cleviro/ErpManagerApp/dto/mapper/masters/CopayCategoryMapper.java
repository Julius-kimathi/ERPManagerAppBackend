package com.cleviro.ErpManagerApp.dto.mapper.masters;

import com.cleviro.ErpManagerApp.dto.model.masters.CopayCategoryDto;
import com.cleviro.ErpManagerApp.model.masters.CopayCategory;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class CopayCategoryMapper {
    public static CopayCategoryDto toCopayCategoryDto(CopayCategory copayCategory){
        return new CopayCategoryDto()
                .setId(copayCategory.getId())
                .setName(copayCategory.getName())
                .setPlans(StreamSupport.stream(copayCategory.getPlans().spliterator(),false).map(PlanMapper::toPlanDto).collect(Collectors.toSet()));
    }
}
