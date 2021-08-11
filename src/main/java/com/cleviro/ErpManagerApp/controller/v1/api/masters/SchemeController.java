package com.cleviro.ErpManagerApp.controller.v1.api.masters;

import com.cleviro.ErpManagerApp.controller.v1.request.masters.AddSchemeRequest;
import com.cleviro.ErpManagerApp.dto.model.masters.SchemeDto;
import com.cleviro.ErpManagerApp.dto.response.Response;
import com.cleviro.ErpManagerApp.service.masters.PayerService;
import com.cleviro.ErpManagerApp.service.masters.SchemeService;
import com.cleviro.ErpManagerApp.service.masters.SchemeTypeService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/masters/schemes")
public class SchemeController {
    @Autowired
    private PayerService payerService;
    @Autowired
    private SchemeTypeService schemeTypeService;
    @Autowired
    private SchemeService schemeService;

    @GetMapping
    @ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    public Response getSchemes(){
        return Response.ok().setPayload(schemeService.findAllschemes());
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    public Response getSchemeById(@PathVariable("id") final Integer id){
        Optional<SchemeDto> schemeDto = Optional.ofNullable(schemeService.findSchemeById(id));
        if (schemeDto.isPresent())
            return Response.ok().setPayload(schemeDto.get());
        else
            return Response.badRequest().setErrors("Scheme not found");
    }

    @PostMapping
    @ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    public Response addScheme(@RequestBody @Valid AddSchemeRequest addSchemeRequest){
        SchemeDto schemeDto = new SchemeDto()
                .setName(addSchemeRequest.getName())
                .setStatus(addSchemeRequest.getStatus())
                .setIsPreauthRequired(addSchemeRequest.getIsPreauthRequired())
                .setSchemeType(schemeTypeService.findSchemeTypeById(addSchemeRequest.getSchemeTypeId()))
                .setPayer(payerService.findPayerById(addSchemeRequest.getSchemeTypeId()));
        return Response.ok().setPayload(schemeService.addScheme(schemeDto));
    }

    @PutMapping
    @ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    public Response updateScheme(@RequestBody @Valid AddSchemeRequest addSchemeRequest){
        SchemeDto schemeDto = new SchemeDto()
                .setId(addSchemeRequest.getId())
                .setName(addSchemeRequest.getName())
                .setStatus(addSchemeRequest.getStatus())
                .setIsPreauthRequired(addSchemeRequest.getIsPreauthRequired())
                .setSchemeType(schemeTypeService.findSchemeTypeById(addSchemeRequest.getSchemeTypeId()))
                .setPayer(payerService.findPayerById(addSchemeRequest.getSchemeTypeId()));
        return Response.ok().setPayload(schemeService.updateScheme(schemeDto));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    public void  deleteScheme(@PathVariable("id") final int id){
        schemeService.removeScheme(id);
    }
}
