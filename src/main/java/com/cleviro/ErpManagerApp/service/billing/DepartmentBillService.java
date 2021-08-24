package com.cleviro.ErpManagerApp.service.billing;

import com.cleviro.ErpManagerApp.dto.model.billing.DepartmentBillDto;
import com.cleviro.ErpManagerApp.dto.model.patients.VisitDto;

import java.util.Collection;

public interface DepartmentBillService {
    /**
     * Get a departmentBill by id
     * @param id
     */
    DepartmentBillDto findDepartmentBillById(Long id);

    /**
     * Get a departmentBill by id
     * @param visitDto
     */
    DepartmentBillDto findDepartmentBillByVisit(VisitDto visitDto);


    /**
     * Find all the departmentBills
     * @param
     */
    Collection<DepartmentBillDto> findAllDepartmentBills();

    /**
     *
     * Create a new departmentBill
     * @param departmentBillDto
     */
    DepartmentBillDto addDepartmentBill(DepartmentBillDto departmentBillDto);

    /**
     * update departmentBill details
     * @param departmentBillDto
     */
    DepartmentBillDto updateDepartmentBill(DepartmentBillDto departmentBillDto);

    /**
     * delete a departmentBill from db
     * @param id
     */
    void removeDepartmentBill(Long id);
}
