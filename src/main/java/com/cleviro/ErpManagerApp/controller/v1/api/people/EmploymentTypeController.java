package com.cleviro.ErpManagerApp.controller.v1.api.people;

import com.cleviro.ErpManagerApp.controller.v1.request.people.AddEmploymentTypeRequest;
import com.cleviro.ErpManagerApp.dto.model.people.EmploymentTypeDto;
import com.cleviro.ErpManagerApp.dto.response.Response;
import com.cleviro.ErpManagerApp.service.masters.CompanyService;
import com.cleviro.ErpManagerApp.service.people.EmploymentTypeService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/people/employmentTypes")
public class EmploymentTypeController {
    @Autowired
    private EmploymentTypeService employmentTypeService;

    @Autowired
    private CompanyService companyService;

    @GetMapping
    @ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    public Response getEmploymentTypes(){
        return Response.ok().setPayload(employmentTypeService.findAllEmploymentTypes());
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    public Response getEmploymentType(@PathVariable("id") final Short id){
        Optional<EmploymentTypeDto> employmentTypeDto = Optional.ofNullable(employmentTypeService.findEmploymentTypeById(id));
        if (employmentTypeDto.isPresent())
            return Response.ok().setPayload(employmentTypeDto.get());
        else
            return Response.badRequest().setErrors("Employment type not found");
    }

    @PostMapping
    @ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    public Response addEmploymentType(@RequestBody @Valid AddEmploymentTypeRequest addEmploymentTypeRequest){
        EmploymentTypeDto employmentTypeDto = new EmploymentTypeDto()
                .setName(addEmploymentTypeRequest.getName())
                .setDescription(addEmploymentTypeRequest.getDescription())
                .setCompany(companyService.findCompanyById(addEmploymentTypeRequest.getCompanyId()));
        return Response.ok().setPayload(employmentTypeService.addEmploymentType(employmentTypeDto));
    }

    @PutMapping
    @ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    public Response updateEmploymentType(@RequestBody @Valid AddEmploymentTypeRequest addEmploymentTypeRequest){
        EmploymentTypeDto employmentTypeDto = new EmploymentTypeDto()
                .setId(addEmploymentTypeRequest.getId())
                .setName(addEmploymentTypeRequest.getName())
                .setDescription(addEmploymentTypeRequest.getDescription())
                .setCompany(companyService.findCompanyById(addEmploymentTypeRequest.getCompanyId()));
        return Response.ok().setPayload(employmentTypeService.updateEmploymentType(employmentTypeDto));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    public void deleteCustomer(@PathVariable final Short id){
       employmentTypeService.removeEmploymentType(id);
    }
}
