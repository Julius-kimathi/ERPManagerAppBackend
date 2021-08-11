package com.cleviro.ErpManagerApp.controller.v1.api.masters;

import com.cleviro.ErpManagerApp.controller.v1.request.masters.AddPayerAccountRequest;
import com.cleviro.ErpManagerApp.dto.model.masters.PayerAccountDto;
import com.cleviro.ErpManagerApp.dto.response.Response;
import com.cleviro.ErpManagerApp.service.masters.PayerAccountService;
import com.cleviro.ErpManagerApp.service.masters.PayerService;
import com.cleviro.ErpManagerApp.service.masters.SchemeService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/masters/payerAccounts")
public class PayerAccountController {
    @Autowired
    private PayerAccountService payerAccountService;
    @Autowired
    private PayerService payerService;
    @Autowired
    private SchemeService schemeService;

    @GetMapping
    @ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    public Response getPayerAccounts(){
        return Response.ok().setPayload(payerAccountService.findAllPayerAccounts());
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    public Response getPayerAccountById(@PathVariable("id") final Integer id){
        Optional<PayerAccountDto> payerAccountDto = Optional.ofNullable(payerAccountService.findPayerAccountById(id));
        if (payerAccountDto.isPresent())
            return Response.ok().setPayload(payerAccountDto.get());
        else
            return Response.badRequest().setErrors("Payer Account not found");
    }

    @PostMapping
    @ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    public Response addPayerAccount(@RequestBody @Valid AddPayerAccountRequest addPayerAccountRequest){
        PayerAccountDto payerAccountDto = new PayerAccountDto()
                .setName(addPayerAccountRequest.getName())
                .setEmail(addPayerAccountRequest.getEmail())
                .setPhone(addPayerAccountRequest.getPhone())
                .setAddress(addPayerAccountRequest.getAddress())
                .setStatus(addPayerAccountRequest.getStatus())
                .setScheme(schemeService.findSchemeById(addPayerAccountRequest.getSchemeId()))
                .setPayer(payerService.findPayerById(addPayerAccountRequest.getPayerId()));
        return Response.ok().setPayload(payerAccountService.addPayerAccount(payerAccountDto));
    }

    @PutMapping
    @ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    public Response updatePayerAccount(@RequestBody @Valid AddPayerAccountRequest addPayerAccountRequest){
        PayerAccountDto payerAccountDto = new PayerAccountDto()
                .setId(addPayerAccountRequest.getId())
                .setName(addPayerAccountRequest.getName())
                .setEmail(addPayerAccountRequest.getEmail())
                .setPhone(addPayerAccountRequest.getPhone())
                .setAddress(addPayerAccountRequest.getAddress())
                .setStatus(addPayerAccountRequest.getStatus())
                .setScheme(schemeService.findSchemeById(addPayerAccountRequest.getSchemeId()))
                .setPayer(payerService.findPayerById(addPayerAccountRequest.getPayerId()));
        return Response.ok().setPayload(payerAccountService.updatePayerAccount(payerAccountDto));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    public void  deletePayerAccount(@PathVariable("id") final int id){
        payerAccountService.removePayerAccount(id);
    }
}
