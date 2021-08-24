package com.cleviro.ErpManagerApp.dto.mapper.billing;

import com.cleviro.ErpManagerApp.dto.model.billing.DepartmentBillDetailDto;
import com.cleviro.ErpManagerApp.dto.model.billing.DepartmentBillDto;
import com.cleviro.ErpManagerApp.model.billing.DepartmentBillDetail;
import org.modelmapper.ModelMapper;

public class DepartmentBillDetailMapper {
    public static DepartmentBillDetailDto toDepartmentBillDetailDto(DepartmentBillDetail departmentBillDetail){
        return new DepartmentBillDetailDto()
                .setId(departmentBillDetail.getId())
                .setDepartmentBill(new ModelMapper().map(departmentBillDetail.getDepartmentBill(), DepartmentBillDto.class));
    }
}
