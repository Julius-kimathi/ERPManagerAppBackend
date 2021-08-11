package com.cleviro.ErpManagerApp.controller.v1.api.masters;

import com.cleviro.ErpManagerApp.controller.v1.request.masters.AddPayerRequest;
import com.cleviro.ErpManagerApp.dto.model.masters.PayerDto;
import com.cleviro.ErpManagerApp.dto.response.Response;
import com.cleviro.ErpManagerApp.service.masters.PayerService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/masters/payers")
public class PayerController {
    @Autowired
    private PayerService payerService;

    @GetMapping
    @ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    public Response getPayers(){
        return Response.ok().setPayload(payerService.findAllPayers());
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    public Response getPayerById(@PathVariable("id") final Integer id){
        Optional<PayerDto> payerDto = Optional.ofNullable(payerService.findPayerById(id));
        if (payerDto.isPresent())
            return Response.ok().setPayload(payerDto.get());
        else
            return Response.badRequest().setErrors("Payer not found");
    }

    @PostMapping
    @ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    public Response addPayer(@RequestBody @Valid AddPayerRequest addPayerRequest){
        PayerDto payerDto = new PayerDto()
                .setName(addPayerRequest.getName());
        return Response.ok().setPayload(payerService.addPayer(payerDto));
    }

    @PutMapping
    @ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    public Response updatePayer(@RequestBody @Valid AddPayerRequest addPayerRequest){
        PayerDto payerDto = new PayerDto()
                .setId(addPayerRequest.getId())
                .setName(addPayerRequest.getName());
        return Response.ok().setPayload(payerService.updatePayer(payerDto));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    public void  deletePayer(@PathVariable("id") final int id){
        payerService.removePayer(id);
    }
}
