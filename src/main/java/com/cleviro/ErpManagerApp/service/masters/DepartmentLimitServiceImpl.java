package com.cleviro.ErpManagerApp.service.masters;

import com.cleviro.ErpManagerApp.dto.mapper.masters.DepartmentLimitMapper;
import com.cleviro.ErpManagerApp.dto.model.masters.DepartmentLimitDto;
import com.cleviro.ErpManagerApp.exception.EntityType;
import com.cleviro.ErpManagerApp.exception.ExceptionType;
import com.cleviro.ErpManagerApp.model.masters.Department;
import com.cleviro.ErpManagerApp.model.masters.DepartmentLimit;
import com.cleviro.ErpManagerApp.model.masters.Plan;
import com.cleviro.ErpManagerApp.repository.masters.DepartmentLimitRepository;
import com.cleviro.ErpManagerApp.util.ExceptionUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class DepartmentLimitServiceImpl implements DepartmentLimitService{

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private DepartmentLimitRepository departmentLimitRepository;

    @Override
    public DepartmentLimitDto findDepartmentLimitById(Short id) {
        Optional<DepartmentLimit> departmentLimit = departmentLimitRepository.findById(id);
        if (departmentLimit.isPresent()){
            return DepartmentLimitMapper.toDepartmentLimitDto(departmentLimit.get());
        }
        throw ExceptionUtil.exception(EntityType.DEPARTMENTLIMIT, ExceptionType.ENTITY_NOT_FOUND,id.toString());
    }

    @Override
    public Collection<DepartmentLimitDto> findAllDepartmentLimits() {
        return StreamSupport.stream(departmentLimitRepository.findAll().spliterator(),false)
                .map(DepartmentLimitMapper::toDepartmentLimitDto)
                .collect(Collectors.toSet());
    }

    @Override
    public DepartmentLimitDto addDepartmentLimit(DepartmentLimitDto departmentLimitDto) {
       Optional<DepartmentLimit> departmentLimit = departmentLimitRepository.findByDepartmentAndPlan(departmentLimitDto.getDepartment(),departmentLimitDto.getPlan());
       if (!departmentLimit.isPresent()){
           DepartmentLimit departmentLimitModel = new DepartmentLimit()
                   .setDepartment(modelMapper.map(departmentLimitDto.getDepartment(), Department.class))
                   .setPlan(modelMapper.map(departmentLimitDto.getPlan(), Plan.class))
                   .setOverallLimit(departmentLimitDto.getOverallLimit())
                   .setVisitLimit(departmentLimitDto.getVisitLimit())
                   .setCopay(departmentLimitDto.getCopay());
           return DepartmentLimitMapper.toDepartmentLimitDto(departmentLimitRepository.save(departmentLimitModel));
       }
       throw ExceptionUtil.exception(EntityType.DEPARTMENTLIMIT,ExceptionType.DUPLICATE_ENTITY,departmentLimitDto.toString());
    }

    @Override
    public DepartmentLimitDto updateDepartmentLimit(DepartmentLimitDto departmentLimitDto) {
        Optional<DepartmentLimit> departmentLimit = departmentLimitRepository.findById(departmentLimitDto.getId());

        if (departmentLimit.isPresent()){
            DepartmentLimit departmentLimitModel = departmentLimit.get()
                    .setDepartment(modelMapper.map(departmentLimitDto.getDepartment(), Department.class))
                    .setPlan(modelMapper.map(departmentLimitDto.getPlan(), Plan.class))
                    .setOverallLimit(departmentLimitDto.getOverallLimit())
                    .setVisitLimit(departmentLimitDto.getVisitLimit())
                    .setCopay(departmentLimitDto.getCopay());
            return DepartmentLimitMapper.toDepartmentLimitDto(departmentLimitRepository.save(departmentLimitModel));
    }
        throw ExceptionUtil.exception(EntityType.DEPARTMENTLIMIT,ExceptionType.ENTITY_NOT_FOUND,departmentLimitDto.toString());
    }

    @Override
    public void removeDepartmentLimit(Short id) {

        Optional<DepartmentLimit> departmentLimit = departmentLimitRepository.findById(id);
        if (departmentLimit.isPresent())
            departmentLimitRepository.deleteById(id);
        else  throw ExceptionUtil.exception(EntityType.DEPARTMENTLIMIT,ExceptionType.ENTITY_NOT_FOUND,id.toString());
    }
}
