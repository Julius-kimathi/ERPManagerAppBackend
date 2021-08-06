package com.cleviro.ErpManagerApp.controller.v1.api.people;

import com.cleviro.ErpManagerApp.controller.v1.request.people.AddCustomerTypeRequest;
import com.cleviro.ErpManagerApp.dto.model.people.CustomerTypeDto;
import com.cleviro.ErpManagerApp.dto.response.Response;
import com.cleviro.ErpManagerApp.service.masters.CompanyService;
import com.cleviro.ErpManagerApp.service.people.CustomerTypeService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/people/customerTypes")
public class CustomerTypeController {
    @Autowired
    private CustomerTypeService customerTypeService;

    @Autowired
    private CompanyService companyService;

    @GetMapping
    @ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    public Response getCustomerTypes(){
        return Response.ok().setPayload(customerTypeService.findAllCustomerTypes());
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    public Response getCustomerType(@PathVariable("id") final short id){
        Optional<CustomerTypeDto> customerTypeDto = Optional.ofNullable(customerTypeService.findCustomerTypeById(id));
        if (customerTypeDto.isPresent())
            return Response.ok().setPayload(customerTypeDto.get());
        else
            return Response.ok().setErrors("CustomerType not Found");
    }

    @PostMapping
    @ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    public Response addCustomerType(@RequestBody @Valid AddCustomerTypeRequest addCustomerTypeRequest){
        CustomerTypeDto customerTypeDto = new CustomerTypeDto()
                .setName(addCustomerTypeRequest.getName())
                .setDescription(addCustomerTypeRequest.getDescription())
                .setCompany(companyService.findCompanyById(addCustomerTypeRequest.getCompanyId()));
        return Response.ok().setPayload(customerTypeService.addCustomerType(customerTypeDto));
    }

    @PutMapping
    @ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    public Response updateCustomerType(@RequestBody @Valid AddCustomerTypeRequest addCustomerTypeRequest){
        CustomerTypeDto customerTypeDto = new CustomerTypeDto()
                .setId(addCustomerTypeRequest.getId())
                .setName(addCustomerTypeRequest.getName())
                .setDescription(addCustomerTypeRequest.getDescription())
                .setCompany(companyService.findCompanyById(addCustomerTypeRequest.getCompanyId()));
        return Response.ok().setPayload(customerTypeService.updateCustomerType(customerTypeDto));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    public void deleteCustomer(@PathVariable final Short id){
        customerTypeService.removeCustomerType(id);
    }
}
