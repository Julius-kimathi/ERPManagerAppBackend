package com.cleviro.ErpManagerApp.controller.v1.api.masters;

import com.cleviro.ErpManagerApp.controller.v1.request.masters.AddCompanyRequest;
import com.cleviro.ErpManagerApp.dto.model.masters.CompanyDto;
import com.cleviro.ErpManagerApp.dto.response.Response;
import com.cleviro.ErpManagerApp.service.masters.CompanyService;
import com.cleviro.ErpManagerApp.service.masters.CountryService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/masters/companies")
public class CompanyController {
    @Autowired
    private CompanyService companyService;

    @Autowired
    private CountryService countryService;

    @GetMapping
    @ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    public Response getCompanies(){
        return Response.ok().setPayload(companyService.findAllCompanies());
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    public Response getCompanyById(@PathVariable("id") final int id){
        Optional<CompanyDto> companyDto = Optional.ofNullable(companyService.findCompanyById(id));
        if (companyDto.isPresent())
            return Response.ok().setPayload(companyDto.get());
        else
            return Response.badRequest().setErrors("Company not found");
    }

    @PostMapping
    @ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    public Response addCompany(@RequestBody @Valid AddCompanyRequest addCompanyRequest){
        CompanyDto companyDto = new CompanyDto()
                .setName(addCompanyRequest.getName())
                .setPostalAddress(addCompanyRequest.getPostalAddress())
                .setState(addCompanyRequest.getState())
                .setCity(addCompanyRequest.getCity())
                .setPhone(addCompanyRequest.getPhone())
                .setPhone1(addCompanyRequest.getPhone1())
                .setPhone2(addCompanyRequest.getPhone2())
                .setEmail(addCompanyRequest.getEmail())
                .setEmail1(addCompanyRequest.getEmail1())
                .setEmail2(addCompanyRequest.getEmail2())
                .setAbbreviation(addCompanyRequest.getAbbreviation())
                .setWebsite(addCompanyRequest.getWebsite())
                .setOrderPrefix(addCompanyRequest.getOrderPrefix())
                .setCountry(countryService.findCountryById(addCompanyRequest.getCountryId()));
        return Response.ok().setPayload(companyService.addCompany(companyDto));
    }

    @PutMapping
    @ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    public Response updateCompany(@RequestBody @Valid AddCompanyRequest addCompanyRequest){
        CompanyDto companyDto = new CompanyDto()
                .setId(addCompanyRequest.getId())
                .setName(addCompanyRequest.getName())
                .setPostalAddress(addCompanyRequest.getPostalAddress())
                .setState(addCompanyRequest.getState())
                .setCity(addCompanyRequest.getCity())
                .setPhone(addCompanyRequest.getPhone())
                .setPhone1(addCompanyRequest.getPhone1())
                .setPhone2(addCompanyRequest.getPhone2())
                .setEmail(addCompanyRequest.getEmail())
                .setEmail1(addCompanyRequest.getEmail1())
                .setEmail2(addCompanyRequest.getEmail2())
                .setAbbreviation(addCompanyRequest.getAbbreviation())
                .setWebsite(addCompanyRequest.getWebsite())
                .setOrderPrefix(addCompanyRequest.getOrderPrefix())
                .setCountry(countryService.findCountryById(addCompanyRequest.getCountryId()));
        return Response.ok().setPayload(companyService.updateCompany(companyDto));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    public void  deleteCompany(@PathVariable("id") final int id){
        companyService.removeCompany(id);
    }
}
