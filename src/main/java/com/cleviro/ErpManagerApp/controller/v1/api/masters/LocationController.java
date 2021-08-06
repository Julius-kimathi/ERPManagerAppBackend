package com.cleviro.ErpManagerApp.controller.v1.api.masters;

import com.cleviro.ErpManagerApp.controller.v1.request.masters.AddLocationRequest;
import com.cleviro.ErpManagerApp.dto.model.masters.LocationDto;
import com.cleviro.ErpManagerApp.dto.response.Response;
import com.cleviro.ErpManagerApp.service.masters.CompanyService;
import com.cleviro.ErpManagerApp.service.masters.LocationService;
import com.cleviro.ErpManagerApp.service.masters.ZoneService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/masters/locations")
public class LocationController {
    @Autowired
    private LocationService locationService;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private ZoneService zoneService;

    @GetMapping
    @ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    public Response getLocations(){
        return Response.ok().setPayload(locationService.findAllLocations());
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    public Response getLocation(@PathVariable("id") final Short id){
        Optional<LocationDto> locationDto = Optional.ofNullable(locationService.findLocationById(id));
        if (locationDto.isPresent())
            return Response.ok().setPayload(locationDto.get());
        else
            return Response.badRequest().setErrors("Location not found");
    }

    @PostMapping
    @ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    public Response addLocation(@RequestBody @Valid AddLocationRequest addLocationRequest){
        LocationDto locationDto = new LocationDto()
                .setName(addLocationRequest.getName())
                .setAbbreviation(addLocationRequest.getAbbreviation())
                .setPostalAddress(addLocationRequest.getPostalAddress())
                .setState(addLocationRequest.getState())
                .setCity(addLocationRequest.getCity())
                .setPhone(addLocationRequest.getPhone())
                .setPhone1(addLocationRequest.getPhone1())
                .setEmail(addLocationRequest.getEmail())
                .setEmail1(addLocationRequest.getEmail1())
                .setStatus(addLocationRequest.getStatus())
                .setCompany(companyService.findCompanyById(addLocationRequest.getCompanyId()))
                .setZone(zoneService.findZoneById(addLocationRequest.getZoneId()));
        return Response.ok().setPayload(locationService.addLocation(locationDto));
    }

    @PutMapping
    @ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    public Response updateLocation(@RequestBody @Valid AddLocationRequest addLocationRequest){
        LocationDto locationDto = new LocationDto()
                .setId(addLocationRequest.getId())
                .setName(addLocationRequest.getName())
                .setAbbreviation(addLocationRequest.getAbbreviation())
                .setPostalAddress(addLocationRequest.getPostalAddress())
                .setState(addLocationRequest.getState())
                .setCity(addLocationRequest.getCity())
                .setPhone(addLocationRequest.getPhone())
                .setPhone1(addLocationRequest.getPhone1())
                .setEmail(addLocationRequest.getEmail())
                .setEmail1(addLocationRequest.getEmail1())
                .setStatus(addLocationRequest.getStatus())
                .setCompany(companyService.findCompanyById(addLocationRequest.getCompanyId()))
                .setZone(zoneService.findZoneById(addLocationRequest.getZoneId()));
        return Response.ok().setPayload(locationService.updateLocation(locationDto));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    public void  deleteCountry(@PathVariable("id") final Short id){
       locationService.removeLocation(id);
    }
}
