package com.cleviro.ErpManagerApp.controller.v1.api.people;

import com.cleviro.ErpManagerApp.controller.v1.request.people.AddCustomerRequest;
import com.cleviro.ErpManagerApp.dto.model.people.CustomerDto;
import com.cleviro.ErpManagerApp.dto.response.Response;
import com.cleviro.ErpManagerApp.service.masters.CountryService;
import com.cleviro.ErpManagerApp.service.people.CustomerService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/people/customers")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @Autowired
    private CountryService countryService;

    @GetMapping
    @ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    public Response getCustomers(@RequestParam Optional<String> email, Optional<String> idNo){

            if (idNo.isPresent()){
                Optional<CustomerDto> customerDto = Optional.ofNullable(customerService.findCustomerByIdNo(idNo.get()));
                if (customerDto.isPresent())
                return Response.ok().setPayload(customerDto.get());
                else return Response.badRequest().setErrors("Customer not found");
            }
            if (email.isPresent()){
                Optional<CustomerDto> customerDto = Optional.ofNullable(customerService.findCustomerByEmail(email.get()));
                if (customerDto.isPresent())
                    return Response.ok().setPayload(customerDto.get());
                else return Response.badRequest().setErrors("Customer not found");
            }

        return Response.ok().setPayload(customerService.findAllCustomers());
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    public Response getCustomer(@PathVariable("id") final Long id){
        Optional<CustomerDto> customerDto = Optional.ofNullable(customerService.findCustomerById(id));
        if (customerDto.isPresent())
            return Response.ok().setPayload(customerDto.get());
        else
            return Response.badRequest().setErrors("Customer not found");
    }

    @PostMapping
    @ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    public Response addCustomer(@RequestBody @Valid AddCustomerRequest addCustomerRequest){
        CustomerDto customerDto = new CustomerDto()
                .setFirstName(addCustomerRequest.getFirstName())
                .setMiddleName(addCustomerRequest.getMiddleName())
                .setLastName(addCustomerRequest.getLastName())
                .setDob(addCustomerRequest.getDob())
                .setEmail(addCustomerRequest.getEmail())
                .setPhone(addCustomerRequest.getPhone())
                .setPhone1(addCustomerRequest.getPhone1())
                .setIdNo(addCustomerRequest.getIdNo())
                .setPostalAddress(addCustomerRequest.getPostalAddress())
                .setState(addCustomerRequest.getState())
                .setCity(addCustomerRequest.getCity())
                .setStatus(addCustomerRequest.getStatus())
                .setGender(addCustomerRequest.getGender())
                .setCountry(countryService.findCountryById(addCustomerRequest.getCountryId()));
        return Response.ok().setPayload(customerService.addCustomer(customerDto));
    }

    @PutMapping
    @ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    public Response updateCustomer(@RequestBody @Valid AddCustomerRequest addCustomerRequest){
        CustomerDto customerDto = new CustomerDto()
                .setId(addCustomerRequest.getId())
                .setFirstName(addCustomerRequest.getFirstName())
                .setMiddleName(addCustomerRequest.getMiddleName())
                .setLastName(addCustomerRequest.getLastName())
                .setDob(addCustomerRequest.getDob())
                .setEmail(addCustomerRequest.getEmail())
                .setPhone(addCustomerRequest.getPhone())
                .setPhone1(addCustomerRequest.getPhone1())
                .setIdNo(addCustomerRequest.getIdNo())
                .setPostalAddress(addCustomerRequest.getPostalAddress())
                .setState(addCustomerRequest.getState())
                .setCity(addCustomerRequest.getCity())
                .setStatus(addCustomerRequest.getStatus())
                .setGender(addCustomerRequest.getGender())
                .setCountry(countryService.findCountryById(addCustomerRequest.getCountryId()));
        return Response.ok().setPayload(customerService.updateCustomer(customerDto));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    public void deleteCustomer(@PathVariable final Long id){
        customerService.removeCustomer(id);
    }
}
