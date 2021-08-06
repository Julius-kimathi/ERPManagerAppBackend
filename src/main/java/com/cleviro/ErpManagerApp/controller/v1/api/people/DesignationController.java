package com.cleviro.ErpManagerApp.controller.v1.api.people;

import com.cleviro.ErpManagerApp.controller.v1.request.people.AddDesignationRequest;
import com.cleviro.ErpManagerApp.dto.model.people.DesignationDto;
import com.cleviro.ErpManagerApp.dto.response.Response;
import com.cleviro.ErpManagerApp.service.masters.CompanyService;
import com.cleviro.ErpManagerApp.service.people.DesignationService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/people/designations")
public class DesignationController {
    @Autowired
    private DesignationService designationService;

    @Autowired
    private CompanyService companyService;

    @GetMapping
    @ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    public Response getDesignations(){
        return Response.ok().setPayload(designationService.findAllDesignations());
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    public Response getDesignation(@PathVariable("id") final Short id){
        Optional<DesignationDto> designationDto = Optional.ofNullable(designationService.findDesignationById(id));
        if (designationDto.isPresent())
            return Response.ok().setPayload(designationDto.get());
        else
            return Response.badRequest().setErrors("Designation not found");
    }

    @PostMapping
    @ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    public Response addDesignation(@RequestBody @Valid AddDesignationRequest addDesignationRequest){
        DesignationDto designationDto = new DesignationDto()
                .setName(addDesignationRequest.getName())
                .setDescription(addDesignationRequest.getDescription())
                .setCompany(companyService.findCompanyById(addDesignationRequest.getCompanyId()));
        return Response.ok().setPayload(designationService.addDesignation(designationDto));
    }

    @PutMapping
    @ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    public Response updateDesignation(@RequestBody @Valid AddDesignationRequest addDesignationRequest)
    {
        DesignationDto designationDto = new DesignationDto()
                .setId(addDesignationRequest.getId())
                .setName(addDesignationRequest.getName())
                .setDescription(addDesignationRequest.getDescription())
                .setCompany(companyService.findCompanyById(addDesignationRequest.getCompanyId()));
        return Response.ok().setPayload(designationService.updateDesignation(designationDto));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    public void deleteCustomer(@PathVariable final Short id){
       designationService.removeDesignation(id);
    }
}
