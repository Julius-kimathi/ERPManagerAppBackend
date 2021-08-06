package com.cleviro.ErpManagerApp.service.people;

import com.cleviro.ErpManagerApp.dto.mapper.people.EmployeeMapper;
import com.cleviro.ErpManagerApp.dto.model.people.EmployeeDto;
import com.cleviro.ErpManagerApp.exception.EntityType;
import com.cleviro.ErpManagerApp.exception.ExceptionType;
import com.cleviro.ErpManagerApp.model.masters.Company;
import com.cleviro.ErpManagerApp.model.masters.Country;
import com.cleviro.ErpManagerApp.model.masters.Department;
import com.cleviro.ErpManagerApp.model.masters.Location;
import com.cleviro.ErpManagerApp.model.people.Designation;
import com.cleviro.ErpManagerApp.model.people.Employee;
import com.cleviro.ErpManagerApp.model.people.EmploymentType;
import com.cleviro.ErpManagerApp.model.people.Supervisor;
import com.cleviro.ErpManagerApp.repository.people.EmployeeRepository;
import com.cleviro.ErpManagerApp.util.ExceptionUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public EmployeeDto findEmployeeById(Long id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isPresent()) return EmployeeMapper.toEmployeeDto(employee.get());
        else throw ExceptionUtil.exception(EntityType.EMPLOYEE, ExceptionType.ENTITY_NOT_FOUND,id.toString());
    }

    @Override
    public EmployeeDto findEmployeeByIdNo(String idNo) {
        Optional<Employee> employee = Optional.ofNullable(employeeRepository.findByIdNo(idNo));
        if (employee.isPresent())
            return EmployeeMapper.toEmployeeDto(employee.get());
        else
            throw ExceptionUtil.exception(EntityType.CUSTOMER, ExceptionType.ENTITY_NOT_FOUND,idNo);
    }

    @Override
    public EmployeeDto findEmployeeByEmail(String email) {
        Optional<Employee> employee = Optional.ofNullable(employeeRepository.findByEmail(email));
        if (employee.isPresent())
            return EmployeeMapper.toEmployeeDto(employee.get());
        else
            throw ExceptionUtil.exception(EntityType.CUSTOMER, ExceptionType.ENTITY_NOT_FOUND,email);
    }

    @Override
    public Collection<EmployeeDto> findAllEmployees() {
        return StreamSupport.stream(employeeRepository.findAll().spliterator(), false)
                .map(EmployeeMapper::toEmployeeDto)
                .collect(Collectors.toSet());
    }

    @Override
    public EmployeeDto addEmployee(EmployeeDto employeeDto) {
        Optional<Employee> employee = Optional.ofNullable(employeeRepository.findByEmail(employeeDto.getEmail()));
        if (!employee.isPresent()){
            Employee employeeModel = new Employee()
                    .setFirstName(employeeDto.getFirstName())
                    .setMiddleName(employeeDto.getMiddleName())
                    .setLastName(employeeDto.getLastName())
                    .setDob(employeeDto.getDob())
                    .setEmail(employeeDto.getEmail())
                    .setPhone(employeeDto.getPhone())
                    .setPhone1(employeeDto.getPhone1())
                    .setIdNo(employeeDto.getIdNo())
                    .setPostalAddress(employeeDto.getPostalAddress())
                    .setCity(employeeDto.getCity())
                    .setState(employeeDto.getState())
                    .setStatus(employeeDto.getStatus())
                    .setRegDate(employeeDto.getRegDate())
                    .setValidityDate(employeeDto.getValidityDate())
                    .setPayrollNo(employeeDto.getPayrollNo())
                    .setGender(employeeDto.getGender())
                    .setCompany(modelMapper.map(employeeDto.getCompany(), Company.class))
                    .setCountry(modelMapper.map(employeeDto.getCountry(), Country.class))
                    .setLocation(modelMapper.map(employeeDto.getLocation(), Location.class))
                    .setDesignation(modelMapper.map(employeeDto.getDesignation(), Designation.class))
                    .setEmploymentType(modelMapper.map(employeeDto.getEmploymentType(), EmploymentType.class))
                    .setSupervisor(modelMapper.map(employeeDto.getSupervisor(), Supervisor.class))
                    .setDepartment(modelMapper.map(employeeDto.getDepartment(), Department.class));
            return EmployeeMapper.toEmployeeDto(employeeRepository.save(employeeModel));
        }
        throw ExceptionUtil.exception(EntityType.EMPLOYEE, ExceptionType.DUPLICATE_ENTITY,employeeDto.getEmail()); //0702 239 069
    }

    @Override
    public EmployeeDto updateEmployee(EmployeeDto employeeDto) {
        Optional<Employee> employee = employeeRepository.findById(employeeDto.getId());
        if (employee.isPresent()){
            Employee employeeModel = employee.get()
                    .setFirstName(employeeDto.getFirstName())
                    .setMiddleName(employeeDto.getMiddleName())
                    .setLastName(employeeDto.getLastName())
                    .setDob(employeeDto.getDob())
                    .setEmail(employeeDto.getEmail())
                    .setPhone(employeeDto.getPhone())
                    .setPhone1(employeeDto.getPhone1())
                    .setIdNo(employeeDto.getIdNo())
                    .setPostalAddress(employeeDto.getPostalAddress())
                    .setCity(employeeDto.getCity())
                    .setState(employeeDto.getState())
                    .setStatus(employeeDto.getStatus())
                    .setRegDate(employeeDto.getRegDate())
                    .setValidityDate(employeeDto.getValidityDate())
                    .setPayrollNo(employeeDto.getPayrollNo())
                    .setGender(employeeDto.getGender())
                    .setCompany(modelMapper.map(employeeDto.getCompany(), Company.class))
                    .setCountry(modelMapper.map(employeeDto.getCountry(), Country.class))
                    .setLocation(modelMapper.map(employeeDto.getLocation(), Location.class))
                    .setDesignation(modelMapper.map(employeeDto.getDesignation(), Designation.class))
                    .setEmploymentType(modelMapper.map(employeeDto.getEmploymentType(), EmploymentType.class))
                    .setSupervisor(modelMapper.map(employeeDto.getSupervisor(), Supervisor.class))
                    .setDepartment(modelMapper.map(employeeDto.getDepartment(), Department.class));
            return EmployeeMapper.toEmployeeDto(employeeRepository.save(employeeModel));
        }
        throw ExceptionUtil.exception(EntityType.EMPLOYEE, ExceptionType.ENTITY_NOT_FOUND,employeeDto.getEmail());
    }

    @Override
    public void removeEmployee(Long id) {
        if (employeeRepository.findById(id).isPresent()) employeeRepository.deleteById(id);
        else   throw ExceptionUtil.exception(EntityType.EMPLOYEE, ExceptionType.ENTITY_NOT_FOUND,id.toString());
    }
}
