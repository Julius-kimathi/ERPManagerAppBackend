package com.cleviro.ErpManagerApp.service.billing;

import com.cleviro.ErpManagerApp.dto.mapper.billing.DepartmentBillDetailMapper;
import com.cleviro.ErpManagerApp.dto.model.billing.DepartmentBillDetailDto;
import com.cleviro.ErpManagerApp.exception.EntityType;
import com.cleviro.ErpManagerApp.exception.ExceptionType;
import com.cleviro.ErpManagerApp.model.billing.DepartmentBill;
import com.cleviro.ErpManagerApp.model.billing.DepartmentBillDetail;
import com.cleviro.ErpManagerApp.repository.billing.DepartmentBillDetailRepository;
import com.cleviro.ErpManagerApp.util.ExceptionUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class DepartmentBillDetailServiceImpl implements DepartmentBillDetailService{
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private DepartmentBillDetailRepository departmentBillDetailRepository;

    @Override
    public DepartmentBillDetailDto findDepartmentBillDetailById(Long id) {
        Optional<DepartmentBillDetail> departmentBillDetail = departmentBillDetailRepository.findById(id);
        if (departmentBillDetail.isPresent())
            return DepartmentBillDetailMapper.toDepartmentBillDetailDto(departmentBillDetail.get());
        else
            throw ExceptionUtil.exception(EntityType.DEPARTMENTBILLDETAIL, ExceptionType.ENTITY_NOT_FOUND,id.toString());
    }

    @Override
    public Collection<DepartmentBillDetailDto> findAllDepartmentBillDetails() {
        return StreamSupport.stream(departmentBillDetailRepository.findAll().spliterator(),false)
                .map(DepartmentBillDetailMapper::toDepartmentBillDetailDto)
                .collect(Collectors.toSet());
    }

    @Override
    public DepartmentBillDetailDto addDepartmentBillDetail(DepartmentBillDetailDto departmentBillDetailDto) {
        DepartmentBillDetail departmentBillDetailModel = new DepartmentBillDetail()
                .setQuantity(departmentBillDetailDto.getQuantity())
                .setUnitPrice(departmentBillDetailDto.getUnitPrice())
                .setDepartmentBill(modelMapper.map(departmentBillDetailDto.getDepartmentBill(), DepartmentBill.class));
        return DepartmentBillDetailMapper.toDepartmentBillDetailDto(departmentBillDetailRepository.save(departmentBillDetailModel));
    }

    @Override
    public DepartmentBillDetailDto updateDepartmentBillDetail(DepartmentBillDetailDto departmentBillDetailDto) {
        Optional<DepartmentBillDetail> departmentBillDetail = departmentBillDetailRepository.findById(departmentBillDetailDto.getId());
        if (departmentBillDetail.isPresent()){
            DepartmentBillDetail departmentBillDetailModel = departmentBillDetail.get()
                    .setDepartmentBill(modelMapper.map(departmentBillDetailDto.getDepartmentBill(), DepartmentBill.class));
            return DepartmentBillDetailMapper.toDepartmentBillDetailDto(departmentBillDetailRepository.save(departmentBillDetailModel));
        }
        throw ExceptionUtil.exception(EntityType.DEPARTMENTBILLDETAIL, ExceptionType.ENTITY_NOT_FOUND,departmentBillDetailDto.toString());
    }

    @Override
    public void removeDepartmentBillDetail(Long id) {
        Optional<DepartmentBillDetail> departmentBillDetail = departmentBillDetailRepository.findById(id);
        if (departmentBillDetail.isPresent())
            departmentBillDetailRepository.deleteById(id);
        else
            throw ExceptionUtil.exception(EntityType.DEPARTMENTBILLDETAIL, ExceptionType.ENTITY_NOT_FOUND,id.toString());
    }
}
