package com.cleviro.ErpManagerApp.dto.mapper.billing;

import com.cleviro.ErpManagerApp.dto.model.billing.DepartmentBillDetailDto;
import com.cleviro.ErpManagerApp.dto.model.billing.DepartmentBillDto;
import com.cleviro.ErpManagerApp.dto.model.masters.DepartmentDto;
import com.cleviro.ErpManagerApp.dto.model.patients.SubVisitDto;
import com.cleviro.ErpManagerApp.dto.model.patients.VisitDto;
import com.cleviro.ErpManagerApp.model.billing.DepartmentBill;
import org.modelmapper.ModelMapper;

import java.util.stream.Collectors;

public class DepartmentBillMapper {
    public static DepartmentBillDto toDepartmentBillDto(DepartmentBill departmentBill){
        return new DepartmentBillDto()
                .setId(departmentBill.getId())
                .setBillAmount(departmentBill.getBillAmount())
                .setPaymentStatus(departmentBill.getPaymentStatus())
                .setDepartment(new ModelMapper().map(departmentBill.getDepartment(), DepartmentDto.class))
                .setVisit(new ModelMapper().map(departmentBill.getVisit(), VisitDto.class))
                .setSubVisit(new ModelMapper().map(departmentBill.getSubVisit(), SubVisitDto.class))
                .setDepartmentBillDetails(departmentBill.getDepartmentBillDetails()
                        .stream()
                        .map(departmentBillDetail -> new ModelMapper().map(departmentBillDetail,DepartmentBillDetailDto.class))
                        .collect(Collectors.toSet()));
    }
}
