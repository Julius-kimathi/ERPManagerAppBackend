package com.cleviro.ErpManagerApp.controller.v1.api.people;

import com.cleviro.ErpManagerApp.controller.v1.request.people.AddDependentRequest;
import com.cleviro.ErpManagerApp.dto.model.people.DependentDto;
import com.cleviro.ErpManagerApp.dto.response.Response;
import com.cleviro.ErpManagerApp.service.masters.CompanyService;
import com.cleviro.ErpManagerApp.service.masters.CountryService;
import com.cleviro.ErpManagerApp.service.masters.LocationService;
import com.cleviro.ErpManagerApp.service.people.CustomerService;
import com.cleviro.ErpManagerApp.service.people.DependentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/people/dependents")
public class DependentController {
    @Autowired
    private DependentService dependentService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private CountryService countryService;
    @Autowired
    private CompanyService companyService;
    @Autowired
    private LocationService locationService;

    @GetMapping
    public Response getDependents(){ return Response.ok().setPayload(dependentService.findAllDependents()); }

    @GetMapping("/{id}")
    public Response getDependent(@PathVariable("id") final Long id){
        Optional<DependentDto> dependentDto = Optional.ofNullable(dependentService.findDependentById(id));
        if (dependentDto.isPresent())
            return Response.ok().setPayload(dependentDto.get());
            else
                return Response.badRequest().setErrors("Dependent not found");
    }

    @PostMapping
    public Response addDependent(@RequestBody @Valid AddDependentRequest addDependentRequest){
        DependentDto dependentDto = new DependentDto()
                .setFirstName(addDependentRequest.getFirstName())
                .setMiddleName(addDependentRequest.getMiddleName())
                .setLastName(addDependentRequest.getLastName())
                .setDob(addDependentRequest.getDob())
                .setEmail(addDependentRequest.getEmail())
                .setPhone(addDependentRequest.getPhone())
                .setIdNo(addDependentRequest.getIdNo())
                .setPostalAddress(addDependentRequest.getPostalAddress())
                .setCity(addDependentRequest.getCity())
                .setState(addDependentRequest.getState())
                .setStatus(addDependentRequest.getStatus())
                .setGender(addDependentRequest.getGender())
                .setCountry(countryService.findCountryById(addDependentRequest.getCountryId()))
                .setCompany(companyService.findCompanyById(addDependentRequest.getCompanyId()))
                .setLocation(locationService.findLocationById(addDependentRequest.getLocationId()))
                .setPrincipal(customerService.findCustomerById(addDependentRequest.getPrincipalId()));
        return Response.ok().setPayload(dependentService.addDependent(dependentDto));
    }

    @PutMapping
    public Response updateDependent(@RequestBody @Valid AddDependentRequest addDependentRequest){
        DependentDto dependentDto = new DependentDto()
                .setId(addDependentRequest.getId())
                .setFirstName(addDependentRequest.getFirstName())
                .setMiddleName(addDependentRequest.getMiddleName())
                .setLastName(addDependentRequest.getLastName())
                .setDob(addDependentRequest.getDob())
                .setEmail(addDependentRequest.getEmail())
                .setPhone(addDependentRequest.getPhone())
                .setIdNo(addDependentRequest.getIdNo())
                .setPostalAddress(addDependentRequest.getPostalAddress())
                .setCity(addDependentRequest.getCity())
                .setState(addDependentRequest.getState())
                .setStatus(addDependentRequest.getStatus())
                .setGender(addDependentRequest.getGender())
                .setCountry(countryService.findCountryById(addDependentRequest.getCountryId()))
                .setCompany(companyService.findCompanyById(addDependentRequest.getCompanyId()))
                .setLocation(locationService.findLocationById(addDependentRequest.getLocationId()))
                .setPrincipal(customerService.findCustomerById(addDependentRequest.getPrincipalId()));
        return Response.ok().setPayload(dependentService.updateDependent(dependentDto));
    }

    @DeleteMapping("/{id}")
    public void deleteDependent(@PathVariable("id") final Long id){
            dependentService.removeDependent(id);
        }
    }

