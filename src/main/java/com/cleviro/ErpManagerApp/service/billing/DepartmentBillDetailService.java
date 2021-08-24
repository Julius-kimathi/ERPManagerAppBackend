package com.cleviro.ErpManagerApp.service.billing;

import com.cleviro.ErpManagerApp.dto.model.billing.DepartmentBillDetailDto;

import java.util.Collection;

public interface DepartmentBillDetailService {
    /**
     * Get a departmentBillDetail by id
     * @param id
     */
    DepartmentBillDetailDto findDepartmentBillDetailById(Long id);


    /**
     * Find all the departmentBillDetails
     * @param
     */
    Collection<DepartmentBillDetailDto> findAllDepartmentBillDetails();

    /**
     *
     * Create a new departmentBillDetail
     * @param departmentBillDetailDto
     */
    DepartmentBillDetailDto addDepartmentBillDetail(DepartmentBillDetailDto departmentBillDetailDto);

    /**
     * update departmentBillDetail details
     * @param departmentBillDetailDto
     */
    DepartmentBillDetailDto updateDepartmentBillDetail(DepartmentBillDetailDto departmentBillDetailDto);

    /**
     * delete a departmentBillDetail from db
     * @param id
     */
    void removeDepartmentBillDetail(Long id);
}
