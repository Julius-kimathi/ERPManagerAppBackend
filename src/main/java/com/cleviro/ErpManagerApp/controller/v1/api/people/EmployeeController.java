package com.cleviro.ErpManagerApp.controller.v1.api.people;

import com.cleviro.ErpManagerApp.controller.v1.request.people.AddEmployeeRequest;
import com.cleviro.ErpManagerApp.dto.model.people.EmployeeDto;
import com.cleviro.ErpManagerApp.dto.response.Response;
import com.cleviro.ErpManagerApp.service.masters.CompanyService;
import com.cleviro.ErpManagerApp.service.masters.CountryService;
import com.cleviro.ErpManagerApp.service.masters.DepartmentService;
import com.cleviro.ErpManagerApp.service.masters.LocationService;
import com.cleviro.ErpManagerApp.service.people.DesignationService;
import com.cleviro.ErpManagerApp.service.people.EmployeeService;
import com.cleviro.ErpManagerApp.service.people.EmploymentTypeService;
import com.cleviro.ErpManagerApp.service.people.SupervisorService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/people/employees")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private LocationService locationService;

    @Autowired
    private CountryService countryService;

    @Autowired
    private EmploymentTypeService employmentTypeService;

    @Autowired
    private DesignationService designationService;

    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private SupervisorService supervisorService;

    @GetMapping
    @ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    public Response getEmployees(@RequestParam Optional<String> email, Optional<String> idNo){
        if (idNo.isPresent()){
            Optional<EmployeeDto> employeeDto = Optional.ofNullable(employeeService.findEmployeeByIdNo(idNo.get()));
            if (employeeDto.isPresent())
                return Response.ok().setPayload(employeeDto.get());
            else return Response.badRequest().setErrors("Employee not found");
        }
        if (email.isPresent()){
            Optional<EmployeeDto> employeeDto = Optional.ofNullable(employeeService.findEmployeeByEmail(email.get()));
            if (employeeDto.isPresent())
                return Response.ok().setPayload(employeeDto.get());
            else return Response.badRequest().setErrors("Customer not found");
        }

        return Response.ok().setPayload(employeeService.findAllEmployees());
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    public Response getEmployee(@PathVariable("id") final Long id){
        Optional<EmployeeDto> employeeDto = Optional.ofNullable(employeeService.findEmployeeById(id));
        if (employeeDto.isPresent())
            return Response.ok().setPayload(employeeDto.get());
        else
            return Response.badRequest().setErrors("Employee not found");
    }

    @PostMapping
    @ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    public Response addEmployee(@RequestBody @Valid AddEmployeeRequest addEmployeeRequest){
        EmployeeDto employeeDto = new EmployeeDto()
                .setFirstName(addEmployeeRequest.getFirstName())
                .setMiddleName(addEmployeeRequest.getMiddleName())
                .setLastName(addEmployeeRequest.getLastName())
                .setDob(addEmployeeRequest.getDob())
                .setEmail(addEmployeeRequest.getEmail())
                .setPhone(addEmployeeRequest.getPhone())
                .setPhone1(addEmployeeRequest.getPhone1())
                .setIdNo(addEmployeeRequest.getIdNo())
                .setPostalAddress(addEmployeeRequest.getPostalAddress())
                .setCity(addEmployeeRequest.getCity())
                .setState(addEmployeeRequest.getState())
                .setStatus(addEmployeeRequest.getStatus())
                .setRegDate(addEmployeeRequest.getRegDate())
                .setGender(addEmployeeRequest.getGender())
                .setValidityDate(addEmployeeRequest.getValidityDate())
                .setPayrollNo(addEmployeeRequest.getPayrollNo())
                .setCompany(companyService.findCompanyById(addEmployeeRequest.getCompanyId()))
                .setCountry(countryService.findCountryById(addEmployeeRequest.getCountryId()))
                .setLocation(locationService.findLocationById(addEmployeeRequest.getLocationId()))
                .setDesignation(designationService.findDesignationById(addEmployeeRequest.getDesignationId()))
                .setEmploymentType(employmentTypeService.findEmploymentTypeById(addEmployeeRequest.getEmploymentTypeId()))
                .setDepartment(departmentService.findDepartmentById(addEmployeeRequest.getDepartmentId()))
                .setSupervisor(supervisorService.findSupervisorById(addEmployeeRequest.getSupervisorId()));
        return Response.ok().setPayload(employeeService.addEmployee(employeeDto));
    }

    @PutMapping
    @ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    public Response updateEmployee(@RequestBody @Valid AddEmployeeRequest addEmployeeRequest){
        EmployeeDto employeeDto = new EmployeeDto()
                .setId(addEmployeeRequest.getId())
                .setFirstName(addEmployeeRequest.getFirstName())
                .setMiddleName(addEmployeeRequest.getMiddleName())
                .setLastName(addEmployeeRequest.getLastName())
                .setDob(addEmployeeRequest.getDob())
                .setEmail(addEmployeeRequest.getEmail())
                .setPhone(addEmployeeRequest.getPhone())
                .setPhone1(addEmployeeRequest.getPhone1())
                .setIdNo(addEmployeeRequest.getIdNo())
                .setPostalAddress(addEmployeeRequest.getPostalAddress())
                .setCity(addEmployeeRequest.getCity())
                .setState(addEmployeeRequest.getState())
                .setStatus(addEmployeeRequest.getStatus())
                .setRegDate(addEmployeeRequest.getRegDate())
                .setGender(addEmployeeRequest.getGender())
                .setValidityDate(addEmployeeRequest.getValidityDate())
                .setPayrollNo(addEmployeeRequest.getPayrollNo())
                .setCompany(companyService.findCompanyById(addEmployeeRequest.getCompanyId()))
                .setCountry(countryService.findCountryById(addEmployeeRequest.getCountryId()))
                .setLocation(locationService.findLocationById(addEmployeeRequest.getLocationId()))
                .setDesignation(designationService.findDesignationById(addEmployeeRequest.getDesignationId()))
                .setEmploymentType(employmentTypeService.findEmploymentTypeById(addEmployeeRequest.getEmploymentTypeId()))
                .setDepartment(departmentService.findDepartmentById(addEmployeeRequest.getDepartmentId()))
                .setSupervisor(supervisorService.findSupervisorById(addEmployeeRequest.getSupervisorId()));
        return Response.ok().setPayload(employeeService.updateEmployee(employeeDto));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    public void deleteCustomer(@PathVariable final Long id){
       employeeService.removeEmployee(id);
    }
}
