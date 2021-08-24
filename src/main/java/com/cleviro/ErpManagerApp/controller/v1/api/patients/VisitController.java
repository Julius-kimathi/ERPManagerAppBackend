package com.cleviro.ErpManagerApp.controller.v1.api.patients;

import com.cleviro.ErpManagerApp.controller.v1.request.patients.AddVisitRequest;
import com.cleviro.ErpManagerApp.dto.model.patients.VisitDto;
import com.cleviro.ErpManagerApp.dto.response.Response;
import com.cleviro.ErpManagerApp.service.masters.*;
import com.cleviro.ErpManagerApp.service.patients.VisitService;
import com.cleviro.ErpManagerApp.service.people.CustomerService;
import com.cleviro.ErpManagerApp.service.people.DependentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/patients/visits")
public class VisitController {
    @Autowired
    private VisitService visitService;
    @Autowired
    private DependentService dependentService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private PayerAccountService payerAccountService;
    @Autowired
    private PlanService planService;
    @Autowired
    private ConsultationRateService consultationRateService;
    @Autowired
    private CompanyService companyService;
    @Autowired
    private LocationService locationService;

    @GetMapping
    public Response getVisits(){
        return Response.ok().setPayload(visitService.findAllVisits());
    }

    @GetMapping("/{id}")
    public Response getVisit(@PathVariable("id") final Long id){
        Optional<VisitDto> visitDto = Optional.ofNullable(visitService.findVisitById(id));
        if (visitDto.isPresent())
            return Response.ok().setPayload(visitDto.get());
        else
            return Response.badRequest().setErrors("Visit not found");
    }

    @PostMapping
    public Response addVisit(@RequestBody @Valid AddVisitRequest addVisitRequest){
        VisitDto visitDto = new VisitDto()
                .setBillType(addVisitRequest.getBillType())
                .setPrincipal(customerService.findCustomerById(addVisitRequest.getPrincipalId()))
                .setDependent(dependentService.findDependentById(addVisitRequest.getDependentId()))
                .setPayerAccount(payerAccountService.findPayerAccountById(addVisitRequest.getPayerAccountId()))
                .setPlan(planService.findPlanById(addVisitRequest.getPlanId()))
                .setConsultationRate(consultationRateService.findConsultationRateById(addVisitRequest.getConsultationRateId()))
                .setLocation(locationService.findLocationById(addVisitRequest.getLocationId()))
                .setCompany(companyService.findCompanyById(addVisitRequest.getCompanyId()));
        return Response.ok().setPayload(visitService.addVisit(visitDto));
    }
    
    @PutMapping
    public Response updateVisit(@RequestBody @Valid AddVisitRequest addVisitRequest){
        VisitDto visitDto = new VisitDto()
                .setId(addVisitRequest.getId())
                .setBillType(addVisitRequest.getBillType())
                .setPrincipal(customerService.findCustomerById(addVisitRequest.getPrincipalId()))
                .setDependent(dependentService.findDependentById(addVisitRequest.getDependentId()))
                .setPayerAccount(payerAccountService.findPayerAccountById(addVisitRequest.getPayerAccountId()))
                .setPlan(planService.findPlanById(addVisitRequest.getPlanId()))
                .setConsultationRate(consultationRateService.findConsultationRateById(addVisitRequest.getConsultationRateId()))
                .setLocation(locationService.findLocationById(addVisitRequest.getLocationId()))
                .setCompany(companyService.findCompanyById(addVisitRequest.getCompanyId()));
        return Response.ok().setPayload(visitService.updateVisit(visitDto));
    }
    
    @DeleteMapping("/{id}")
    public void deleteVisit(@PathVariable final Long id){
        visitService.removeVisit(id);
    }
    
}
