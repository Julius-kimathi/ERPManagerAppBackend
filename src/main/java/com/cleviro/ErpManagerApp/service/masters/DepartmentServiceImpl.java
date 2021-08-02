package com.cleviro.ErpManagerApp.service.masters;

import com.cleviro.ErpManagerApp.dto.mapper.masters.DepartmentMapper;
import com.cleviro.ErpManagerApp.dto.model.masters.DepartmentDto;
import com.cleviro.ErpManagerApp.exception.EntityType;
import com.cleviro.ErpManagerApp.exception.ExceptionType;
import com.cleviro.ErpManagerApp.model.masters.Company;
import com.cleviro.ErpManagerApp.model.masters.Department;
import com.cleviro.ErpManagerApp.repository.masters.DepartmentRepository;
import com.cleviro.ErpManagerApp.util.ExceptionUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class DepartmentServiceImpl implements DepartmentService{

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private DepartmentRepository departmentRepository;


    @Override
    public DepartmentDto findDepartmentById(Short id) {
        Optional<Department> department = departmentRepository.findById(id);
        if (department.isPresent())
            return DepartmentMapper.toDepartmentDto(department.get());
        else throw ExceptionUtil.exception(EntityType.DEPARTMENT, ExceptionType.ENTITY_NOT_FOUND,String.valueOf(id));
    }

    @Override
    public Collection<DepartmentDto> findAllDepartments() {
        return StreamSupport.stream(departmentRepository.findAll().spliterator(), false)
                .map(DepartmentMapper::toDepartmentDto)
                .collect(Collectors.toSet());
    }

    @Override
    public DepartmentDto addDepartment(DepartmentDto departmentDto) {
       Optional<Department> department = Optional.ofNullable(departmentRepository.findByName(departmentDto.getName()));
       if (!department.isPresent()){
           Department departmentModel = new Department()
                   .setName(departmentDto.getName())
                   .setDescription(departmentDto.getDescription())
                   .setCompany(modelMapper.map(departmentDto.getCompany(), Company.class));
           return DepartmentMapper.toDepartmentDto(departmentRepository.save(departmentModel));
       }
        throw ExceptionUtil.exception(EntityType.DEPARTMENT, ExceptionType.DUPLICATE_ENTITY,departmentDto.getName());
    }

    @Override
    public DepartmentDto updateDepartment(DepartmentDto departmentDto) {
        Optional<Department> department = departmentRepository.findById(departmentDto.getId());
        if (department.isPresent()){
            Department departmentModel = new Department()
                    .setName(departmentDto.getName())
                    .setDescription(departmentDto.getDescription())
                    .setCompany(modelMapper.map(departmentDto.getCompany(), Company.class));
            return DepartmentMapper.toDepartmentDto(departmentRepository.save(departmentModel));
        }
        throw ExceptionUtil.exception(EntityType.DEPARTMENT, ExceptionType.ENTITY_NOT_FOUND,departmentDto.getId().toString());
    }

    @Override
    public void removeDepartment(Short id) {
        Optional<Department> department = departmentRepository.findById(id);
        if (department.isPresent())
            departmentRepository.deleteById(id);
        else throw ExceptionUtil.exception(EntityType.DEPARTMENT, ExceptionType.ENTITY_NOT_FOUND,id.toString());

    }
}
