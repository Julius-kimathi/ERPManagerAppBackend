package com.cleviro.ErpManagerApp.controller.v1.api.people;

import com.cleviro.ErpManagerApp.controller.v1.request.people.AddSupplierRequest;
import com.cleviro.ErpManagerApp.dto.model.people.SupplierDto;
import com.cleviro.ErpManagerApp.dto.response.Response;
import com.cleviro.ErpManagerApp.service.masters.CountryService;
import com.cleviro.ErpManagerApp.service.people.SupplierService;
import com.cleviro.ErpManagerApp.service.people.SupplierTypeService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/people/suppliers")
public class SupplierController {
    @Autowired
    private SupplierService supplierService;

    @Autowired
    private CountryService countryService;

    @Autowired
    private SupplierTypeService supplierTypeService;


    @GetMapping
    @ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    public Response getSuppliers(@RequestParam Optional<String> email){

        if (email.isPresent()){
            Optional<SupplierDto> supplierDto = Optional.ofNullable(supplierService.findSupplierByEmail(email.get()));
            if (supplierDto.isPresent())
                return Response.ok().setPayload(supplierDto.get());
            else return Response.badRequest().setErrors("Supplier not found");
        }

        return Response.ok().setPayload(supplierService.findAllSuppliers());
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    public Response getSupplier(@PathVariable("id") final Long id){
        Optional<SupplierDto> supplierDto = Optional.ofNullable(supplierService.findSupplierById(id));
        if (supplierDto.isPresent())
            return Response.ok().setPayload(supplierDto.get());
        else
            return Response.badRequest().setErrors("Supplier not found");
    }


    @PostMapping
    @ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    public Response addSupplier(@RequestBody @Valid AddSupplierRequest addSupplierRequest){
        SupplierDto supplierDto = new SupplierDto()
                .setName(addSupplierRequest.getName())
                .setEmail(addSupplierRequest.getEmail())
                .setPhone(addSupplierRequest.getPhone())
                .setPhone1(addSupplierRequest.getPhone1())
                .setKraPin(addSupplierRequest.getKraPin())
                .setPostalAddress(addSupplierRequest.getPostalAddress())
                .setState(addSupplierRequest.getState())
                .setCity(addSupplierRequest.getCity())
                .setStatus(addSupplierRequest.getStatus())
                .setRegDate(addSupplierRequest.getRegDate())
                .setWebsite(addSupplierRequest.getWebsite())
                .setCountry(countryService.findCountryById(addSupplierRequest.getCountryId()))
                .setSupplierType(supplierTypeService.findSupplierTypeById(addSupplierRequest.getSupplierTypeId()));
        return  Response.ok().setPayload(supplierService.addSupplier(supplierDto));
    }

    @PutMapping
    @ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    public Response updateSupplier(@RequestBody @Valid AddSupplierRequest addSupplierRequest){
        SupplierDto supplierDto = new SupplierDto()
                .setId(addSupplierRequest.getId())
                .setName(addSupplierRequest.getName())
                .setEmail(addSupplierRequest.getEmail())
                .setPhone(addSupplierRequest.getPhone())
                .setPhone1(addSupplierRequest.getPhone1())
                .setKraPin(addSupplierRequest.getKraPin())
                .setPostalAddress(addSupplierRequest.getPostalAddress())
                .setState(addSupplierRequest.getState())
                .setCity(addSupplierRequest.getCity())
                .setStatus(addSupplierRequest.getStatus())
                .setRegDate(addSupplierRequest.getRegDate())
                .setWebsite(addSupplierRequest.getWebsite())
                .setCountry(countryService.findCountryById(addSupplierRequest.getCountryId()))
                .setSupplierType(supplierTypeService.findSupplierTypeById(addSupplierRequest.getSupplierTypeId()));
        return  Response.ok().setPayload(supplierService.updateSupplier(supplierDto));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    public void deleteCustomer(@PathVariable final Long id){
       supplierService.removeSupplier(id);
    }
}
