package com.cleviro.ErpManagerApp.controller.v1.api.people;

import com.cleviro.ErpManagerApp.controller.v1.request.people.AddSupplierTypeRequest;
import com.cleviro.ErpManagerApp.dto.model.people.SupplierTypeDto;
import com.cleviro.ErpManagerApp.dto.response.Response;
import com.cleviro.ErpManagerApp.service.masters.CompanyService;
import com.cleviro.ErpManagerApp.service.people.SupplierTypeService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/people/supplierTypes")
public class SupplierTypeController {
    @Autowired
    private SupplierTypeService supplierTypeService;

    @Autowired
    private CompanyService companyService;

    @GetMapping
    @ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    public Response getSupplierTypes(){
        return Response.ok().setPayload(supplierTypeService.findAllSupplierTypes());
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    public Response getSupplierType(@PathVariable("id") final Short id){
        Optional<SupplierTypeDto> supplierTypeDto = Optional.ofNullable(supplierTypeService.findSupplierTypeById(id));
        if (supplierTypeDto.isPresent())
            return Response.ok().setPayload(supplierTypeDto.get());
        else
            return Response.badRequest().setErrors("Supplier type not found");
    }

    @PostMapping
    @ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    public Response addSupplierType(@RequestBody @Valid AddSupplierTypeRequest addSupplierTypeRequest){
        SupplierTypeDto supplierTypeDto = new SupplierTypeDto()
                .setName(addSupplierTypeRequest.getName())
                .setDescription(addSupplierTypeRequest.getDescription())
                .setCompany(companyService.findCompanyById(addSupplierTypeRequest.getCompanyId()));
        return Response.ok().setPayload(supplierTypeService.addSupplierType(supplierTypeDto));
    }

    @PutMapping
    @ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    public Response updateSupplierType(@RequestBody @Valid AddSupplierTypeRequest addSupplierTypeRequest){
        SupplierTypeDto supplierTypeDto = new SupplierTypeDto()
                .setId(addSupplierTypeRequest.getId())
                .setName(addSupplierTypeRequest.getName())
                .setDescription(addSupplierTypeRequest.getDescription())
                .setCompany(companyService.findCompanyById(addSupplierTypeRequest.getCompanyId()));
        return Response.ok().setPayload(supplierTypeService.updateSupplierType(supplierTypeDto));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    public void deleteCustomer(@PathVariable final Short id){
        supplierTypeService.removeSupplierType(id);
    }
}
