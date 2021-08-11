package com.cleviro.ErpManagerApp.dto.mapper.masters;

import com.cleviro.ErpManagerApp.dto.model.masters.DepartmentDto;
import com.cleviro.ErpManagerApp.dto.model.masters.DepartmentLimitDto;
import com.cleviro.ErpManagerApp.dto.model.masters.PlanDto;
import com.cleviro.ErpManagerApp.model.masters.DepartmentLimit;
import org.modelmapper.ModelMapper;

public class DepartmentLimitMapper {
    public  static DepartmentLimitDto toDepartmentLimitDto(DepartmentLimit departmentLimit){
        return new DepartmentLimitDto()
                .setId(departmentLimit.getId())
                .setDepartment(new ModelMapper().map(departmentLimit.getDepartment(), DepartmentDto.class))
                .setPlan(new ModelMapper().map(departmentLimit.getPlan(), PlanDto.class))
                .setOverallLimit(departmentLimit.getOverallLimit())
                .setVisitLimit(departmentLimit.getVisitLimit())
                .setCopay(departmentLimit.getCopay());

    }
}
