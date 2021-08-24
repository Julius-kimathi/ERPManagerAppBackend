package com.cleviro.ErpManagerApp.dto.mapper.patients;

import com.cleviro.ErpManagerApp.dto.model.billing.DepartmentBillDto;
import com.cleviro.ErpManagerApp.dto.model.masters.LocationDto;
import com.cleviro.ErpManagerApp.dto.model.patients.SubVisitDto;
import com.cleviro.ErpManagerApp.model.patients.SubVisit;
import org.modelmapper.ModelMapper;

import java.util.HashSet;
import java.util.stream.Collectors;

public class SubVisitMapper {
    public static SubVisitDto toSubVisitDto(SubVisit subVisit){
        return new SubVisitDto()
                .setId(subVisit.getId())
                .setSubVisitCode(subVisit.getSubVisitCode())
                .setSubVisitDate(subVisit.getSubVisitDate())
                .setAvailableLimit(subVisit.getAvailableLimit())
                .setOverallPaymentStatus(subVisit.getOverallPaymentStatus())
                .setCopay(subVisit.getCopay())
                .setInvoiceNo(subVisit.getInvoiceNo())
                .setInvoiceAmount(subVisit.getInvoiceAmount())
                .setLocation(new ModelMapper().map(subVisit.getLocation(), LocationDto.class))
                .setDepartmentBills(new HashSet<>(subVisit.getDepartmentBills().stream().map(departmentBill -> new ModelMapper().map(departmentBill, DepartmentBillDto.class)).collect(Collectors.toSet())));
    }
}
