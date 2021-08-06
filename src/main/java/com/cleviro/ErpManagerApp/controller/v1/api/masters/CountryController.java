package com.cleviro.ErpManagerApp.controller.v1.api.masters;

import com.cleviro.ErpManagerApp.controller.v1.request.masters.AddCountryRequest;
import com.cleviro.ErpManagerApp.dto.model.masters.CountryDto;
import com.cleviro.ErpManagerApp.dto.response.Response;
import com.cleviro.ErpManagerApp.service.masters.CountryService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/masters/countries")
public class CountryController {
    @Autowired
    private CountryService countryService;

    @GetMapping
    @ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    public Response getCountries(){
        return Response.ok().setPayload(countryService.findAllCountries());
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    public Response getCountry(@PathVariable("id") final int id){
        Optional<CountryDto> countryDto = Optional.ofNullable(countryService.findCountryById(id));
        if (countryDto.isPresent())
            return  Response.ok().setPayload(countryDto.get());
        else
            return Response.badRequest().setErrors("Country not found");
    }

    @PostMapping
    @ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    public Response addCountry(@RequestBody @Valid AddCountryRequest addCountryRequest){
        CountryDto countryDto = new CountryDto()
                .setName(addCountryRequest.getName())
                .setMobileCode(addCountryRequest.getMobileCode())
                .setAbbreviation(addCountryRequest.getAbbreviation())
                .setCurrency(addCountryRequest.getCurrency())
                .setCurrencyCode(addCountryRequest.getCurrencyCode())
                .setCurrencySymbol(addCountryRequest.getCurrencySymbol());
        return Response.ok().setPayload(countryService.addCountry(countryDto));
    }

    @PutMapping
    @ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    public Response updateCountry(@RequestBody @Valid AddCountryRequest addCountryRequest){
        CountryDto countryDto = new CountryDto()
                .setId(addCountryRequest.getId())
                .setName(addCountryRequest.getName())
                .setMobileCode(addCountryRequest.getMobileCode())
                .setAbbreviation(addCountryRequest.getAbbreviation())
                .setCurrency(addCountryRequest.getCurrency())
                .setCurrencyCode(addCountryRequest.getCurrencyCode())
                .setCurrencySymbol(addCountryRequest.getCurrencySymbol());
        return Response.ok().setPayload(countryService.updateCountry(countryDto));
    }
    @DeleteMapping("/{id}")
    @ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    public void  deleteCountry(@PathVariable("id") final int id){
        countryService.removeCountry(id);
    }
}
