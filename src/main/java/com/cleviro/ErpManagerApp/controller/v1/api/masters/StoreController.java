package com.cleviro.ErpManagerApp.controller.v1.api.masters;

import com.cleviro.ErpManagerApp.controller.v1.request.masters.AddStoreRequest;
import com.cleviro.ErpManagerApp.dto.model.masters.StoreDto;
import com.cleviro.ErpManagerApp.dto.response.Response;
import com.cleviro.ErpManagerApp.service.masters.LocationService;
import com.cleviro.ErpManagerApp.service.masters.StoreService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/masters/stores")
public class StoreController {
    @Autowired
    private StoreService storeService;

    @Autowired
    private LocationService locationService;

    @GetMapping
    @ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    public Response getStores(){
        return Response.ok().setPayload(storeService.findAllStores());
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    public Response getStore(@PathVariable("id") final int id){
        Optional<StoreDto> storeDto = Optional.ofNullable(storeService.findStoreById(id));
        if (storeDto.isPresent())
            return Response.ok().setPayload(storeDto.get());
        else
            return Response.badRequest().setErrors("Store not found");
    }

    @PostMapping
    @ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    public Response addStore(@RequestBody @Valid AddStoreRequest addStoreRequest){
        StoreDto storeDto = new StoreDto()
                .setName(addStoreRequest.getName())
             .setStatus(addStoreRequest.getStatus())
                .setLocation(locationService.findLocationById(addStoreRequest.getId()));
        return Response.ok().setPayload(storeService.addStore(storeDto));
    }

    @PutMapping
    @ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    public Response updateLocation(@RequestBody @Valid AddStoreRequest addStoreRequest){
        StoreDto storeDto = new StoreDto()
                .setId(addStoreRequest.getId())
                .setName(addStoreRequest.getName())
                .setStatus(addStoreRequest.getStatus())
                .setLocation(locationService.findLocationById(addStoreRequest.getLocationId()));
        return Response.ok().setPayload(storeService.updateStore(storeDto));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    public void  deleteCountry(@PathVariable("id") final int id){
       storeService.removeStore(id);
    }
}
