package com.cleviro.ErpManagerApp.controller.v1.api.masters;

import com.cleviro.ErpManagerApp.controller.v1.request.masters.AddDepartmentRequest;
import com.cleviro.ErpManagerApp.dto.model.masters.DepartmentDto;
import com.cleviro.ErpManagerApp.dto.response.Response;
import com.cleviro.ErpManagerApp.service.masters.CompanyService;
import com.cleviro.ErpManagerApp.service.masters.DepartmentService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/masters/departments")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private CompanyService companyService;

    @GetMapping
    @ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    public Response getDepartments(){
        return Response.ok().setPayload(departmentService.findAllDepartments());
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    public Response getDepartment(@PathVariable("id") final Short id){
        Optional<DepartmentDto> departmentDto = Optional.ofNullable(departmentService.findDepartmentById(id));
        if (departmentDto.isPresent())
            return Response.ok().setPayload(departmentDto.get());
        else
            return Response.badRequest().setErrors("Department not found");
    }

    @PostMapping
    @ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    public Response addDepartment(@RequestBody @Valid AddDepartmentRequest addDepartmentRequest){
        DepartmentDto departmentDto = new DepartmentDto()
                .setName(addDepartmentRequest.getName())
                .setDescription(addDepartmentRequest.getDescription())
                .setCompany(companyService.findCompanyById(addDepartmentRequest.getCompanyId()));
        return Response.ok().setPayload(departmentService.addDepartment(departmentDto));
    }

    @PutMapping
    @ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    public  Response updateDepartment(@RequestBody @Valid AddDepartmentRequest addDepartmentRequest){
        DepartmentDto departmentDto = new DepartmentDto()
                .setId(addDepartmentRequest.getId())
                .setName(addDepartmentRequest.getName())
                .setDescription(addDepartmentRequest.getDescription())
                .setCompany(companyService.findCompanyById(addDepartmentRequest.getCompanyId()));
        return Response.ok().setPayload(departmentService.updateDepartment(departmentDto));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    public void  deleteCountry(@PathVariable("id") final Short id){
        departmentService.removeDepartment(id);
    }
}
