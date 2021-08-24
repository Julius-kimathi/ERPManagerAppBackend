package com.cleviro.ErpManagerApp.service.billing;

import com.cleviro.ErpManagerApp.dto.mapper.billing.DepartmentBillMapper;
import com.cleviro.ErpManagerApp.dto.model.billing.DepartmentBillDto;
import com.cleviro.ErpManagerApp.dto.model.patients.VisitDto;
import com.cleviro.ErpManagerApp.exception.EntityType;
import com.cleviro.ErpManagerApp.exception.ExceptionType;
import com.cleviro.ErpManagerApp.model.billing.DepartmentBill;
import com.cleviro.ErpManagerApp.model.masters.Department;
import com.cleviro.ErpManagerApp.model.patients.SubVisit;
import com.cleviro.ErpManagerApp.model.patients.Visit;
import com.cleviro.ErpManagerApp.repository.billing.DepartmentBillRepository;
import com.cleviro.ErpManagerApp.util.ExceptionUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class DepartmentBillServiceImpl implements DepartmentBillService{
    @Autowired
    private DepartmentBillRepository departmentBillRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public DepartmentBillDto findDepartmentBillById(Long id) {
        Optional<DepartmentBill> departmentBill = departmentBillRepository.findById(id);
        if (departmentBill.isPresent())
            return DepartmentBillMapper.toDepartmentBillDto(departmentBill.get());
            else throw ExceptionUtil.exception(EntityType.DEPARTMENTBILL, ExceptionType.ENTITY_NOT_FOUND,id.toString());
    }

    @Override
    public DepartmentBillDto findDepartmentBillByVisit(VisitDto visitDto) {
        Optional<DepartmentBill> departmentBill = departmentBillRepository.findByVisit(visitDto);
        if (departmentBill.isPresent())
            return DepartmentBillMapper.toDepartmentBillDto(departmentBill.get());
        else throw ExceptionUtil.exception(EntityType.DEPARTMENTBILL, ExceptionType.ENTITY_NOT_FOUND,visitDto.toString());
    }

    @Override
    public Collection<DepartmentBillDto> findAllDepartmentBills() {
        return StreamSupport.stream(departmentBillRepository.findAll().spliterator(),false)
                .map(DepartmentBillMapper::toDepartmentBillDto)
                .collect(Collectors.toSet());
    }

    @Override
    public DepartmentBillDto addDepartmentBill(DepartmentBillDto departmentBillDto) {
        Optional<DepartmentBill> departmentBill = departmentBillRepository.findByVisit(departmentBillDto.getVisit());
        if (!departmentBill.isPresent()){
            DepartmentBill departmentBillModel = new DepartmentBill()
                    .setBillAmount(departmentBillDto.getBillAmount())
                    .setPaymentStatus(departmentBillDto.getPaymentStatus())
                    .setDepartment(modelMapper.map(departmentBillDto.getDepartment(), Department.class))
                    .setVisit(modelMapper.map(departmentBillDto.getVisit(), Visit.class))
                    .setSubVisit(modelMapper.map(departmentBillDto.getSubVisit(), SubVisit.class));
            return DepartmentBillMapper.toDepartmentBillDto(departmentBillRepository.save(departmentBillModel));
        }
        throw ExceptionUtil.exception(EntityType.DEPARTMENTBILL, ExceptionType.DUPLICATE_ENTITY,departmentBillDto.toString());
    }

    @Override
    public DepartmentBillDto updateDepartmentBill(DepartmentBillDto departmentBillDto) {
        Optional<DepartmentBill> departmentBill = departmentBillRepository.findById(departmentBillDto.getId());
        if (departmentBill.isPresent()){
            DepartmentBill departmentBillModel = departmentBill.get()
                    .setBillAmount(departmentBillDto.getBillAmount())
                    .setPaymentStatus(departmentBillDto.getPaymentStatus())
                    .setDepartment(modelMapper.map(departmentBillDto.getDepartment(), Department.class))
                    .setVisit(modelMapper.map(departmentBillDto.getVisit(), Visit.class))
                    .setSubVisit(modelMapper.map(departmentBillDto.getSubVisit(), SubVisit.class));
            return DepartmentBillMapper.toDepartmentBillDto(departmentBillRepository.save(departmentBillModel));
        }
        throw ExceptionUtil.exception(EntityType.DEPARTMENTBILL, ExceptionType.ENTITY_NOT_FOUND,departmentBillDto.toString());
    }

    @Override
    public void removeDepartmentBill(Long id) {
        Optional<DepartmentBill> departmentBill = departmentBillRepository.findById(id);
        if (departmentBill.isPresent())
           departmentBillRepository.deleteById(id);
        else throw ExceptionUtil.exception(EntityType.DEPARTMENTBILL, ExceptionType.ENTITY_NOT_FOUND,id.toString());
    }
}
