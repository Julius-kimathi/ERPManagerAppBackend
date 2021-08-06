package com.cleviro.ErpManagerApp.controller.v1.api.masters;

import com.cleviro.ErpManagerApp.controller.v1.request.masters.AddZoneRequest;
import com.cleviro.ErpManagerApp.dto.model.masters.ZoneDto;
import com.cleviro.ErpManagerApp.dto.response.Response;
import com.cleviro.ErpManagerApp.service.masters.CompanyService;
import com.cleviro.ErpManagerApp.service.masters.ZoneService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/masters/zones")
public class ZoneController {
    @Autowired
    private ZoneService  zoneService;

    @Autowired
    private CompanyService companyService;

    @GetMapping
    @ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    public Response getZones(){
        return Response.ok().setPayload(zoneService.findAllZones());
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    public Response getStore(@PathVariable("id") final int id){
        Optional<ZoneDto> zoneDto = Optional.ofNullable(zoneService.findZoneById(id));
        if (zoneDto.isPresent())
            return Response.ok().setPayload(zoneDto.get());
        else
            return Response.badRequest().setErrors("Zone not found");
    }

    @PostMapping
    @ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    public Response addZone(@RequestBody @Valid AddZoneRequest addZoneRequest){
        ZoneDto zoneDto = new ZoneDto()
                .setName(addZoneRequest.getName())
                .setAbbreviation(addZoneRequest.getAbbreviation())
                .setCompany(companyService.findCompanyById(addZoneRequest.getCompanyId()));
        return Response.ok().setPayload(zoneService.addZone(zoneDto));
    }

    @PutMapping
    @ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    public Response updateZone(@RequestBody @Valid AddZoneRequest addZoneRequest){
        ZoneDto zoneDto = new ZoneDto()
                .setId(addZoneRequest.getId())
                .setName(addZoneRequest.getName())
                .setAbbreviation(addZoneRequest.getAbbreviation())
                .setCompany(companyService.findCompanyById(addZoneRequest.getCompanyId()));
        return Response.ok().setPayload(zoneService.updateZone(zoneDto));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    public void  deleteCountry(@PathVariable("id") final int id){
    zoneService.removeZone(id);
    }
}
