package com.cleviro.ErpManagerApp.controller.v1.api.masters;

import com.cleviro.ErpManagerApp.controller.v1.request.masters.AddPlanRequest;
import com.cleviro.ErpManagerApp.dto.model.masters.DepartmentLimitDto;
import com.cleviro.ErpManagerApp.dto.model.masters.PlanDto;
import com.cleviro.ErpManagerApp.dto.response.Response;
import com.cleviro.ErpManagerApp.service.masters.*;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("api/v1/masters/plans")
public class PlanController {
    @Autowired
    private PlanService planService;
    @Autowired
    private PayerAccountService payerAccountService;
    @Autowired
    private PlanCategoryService planCategoryService;
    @Autowired
    private LimitCategoryService limitCategoryService;
    @Autowired
    private CopayCategoryService copayCategoryService;
    @Autowired
    private DepartmentLimitService departmentLimitService;
    @Autowired
    private DepartmentService departmentService;

    @GetMapping
    @ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    public Response getPlans(){
        return Response.ok().setPayload(planService.findAllPlans());
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    public Response getPlanById(@PathVariable("id") final Integer id){
        Optional<PlanDto> planDto = Optional.ofNullable(planService.findPlanById(id));
        if (planDto.isPresent())
            return Response.ok().setPayload(planDto.get());
        else
            return Response.badRequest().setErrors("Plan not found");
    }

    @PostMapping
    @ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    public Response addPlan(@RequestBody @Valid AddPlanRequest addPlanRequest){
        PlanDto planDto = new PlanDto()
                .setName(addPlanRequest.getName())
                .setHasDepartmentalLimits(addPlanRequest.getHasDepartmentalLimits())
                .setHasDepartmentalCopay(addPlanRequest.getHasDepartmentalCopay())
                .setCopay(addPlanRequest.getCopay())
                .setOverallLimit(addPlanRequest.getOverallLimit())
                .setVisitLimit(addPlanRequest.getVisitLimit())
                .setValidityStartDate(addPlanRequest.getValidityStartDate())
                .setValidityEndDate(addPlanRequest.getValidityEndDate())
                .setStatus(addPlanRequest.getStatus())
                .setHasRegistrationFees(addPlanRequest.getHasRegistrationFees())
                .setSubVisitPeriodInDays(addPlanRequest.getSubVisitPeriodInDays())
                .setSkipCopayForSubVisits(addPlanRequest.getSkipCopayForSubVisits())
                .setPayerAccount(payerAccountService.findPayerAccountById(addPlanRequest.getPayerAccountId()))
                .setPlanCategory(planCategoryService.findPlanCategoryById(addPlanRequest.getPlanCategoryId()))
                .setLimitCategory(limitCategoryService.findLimitCategoryById(addPlanRequest.getLimitCategoryId()))
                .setCopayCategory(copayCategoryService.findCopayCategoryById(addPlanRequest.getCopayCategoryId()))
                .setDepartmentLimits(StreamSupport.stream(addPlanRequest.getDepartmentLimitRequests().spliterator(),false)
                    .map(addDepartmentLimitRequest -> new DepartmentLimitDto()
                            .setDepartment(departmentService.findDepartmentById(addDepartmentLimitRequest.getDepartmentId()))
                            .setOverallLimit(addDepartmentLimitRequest.getOverallLimit())
                            .setVisitLimit(addDepartmentLimitRequest.getVisitLimit())
                            .setCopay(addDepartmentLimitRequest.getCopay()))
                    .collect(Collectors.toSet()));
        return Response.ok().setPayload(planService.addPlan(planDto));
    }

    @PutMapping
    @ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    public Response updatePlan(@RequestBody @Valid AddPlanRequest addPlanRequest){
        PlanDto planDto = new PlanDto()
                .setId(addPlanRequest.getId())
                .setName(addPlanRequest.getName())
                .setHasDepartmentalLimits(addPlanRequest.getHasDepartmentalLimits())
                .setHasDepartmentalCopay(addPlanRequest.getHasDepartmentalCopay())
                .setCopay(addPlanRequest.getCopay())
                .setOverallLimit(addPlanRequest.getOverallLimit())
                .setVisitLimit(addPlanRequest.getVisitLimit())
                .setValidityStartDate(addPlanRequest.getValidityStartDate())
                .setValidityEndDate(addPlanRequest.getValidityEndDate())
                .setStatus(addPlanRequest.getStatus())
                .setHasRegistrationFees(addPlanRequest.getHasRegistrationFees())
                .setSubVisitPeriodInDays(addPlanRequest.getSubVisitPeriodInDays())
                .setSkipCopayForSubVisits(addPlanRequest.getSkipCopayForSubVisits())
                .setPayerAccount(payerAccountService.findPayerAccountById(addPlanRequest.getPayerAccountId()))
                .setPlanCategory(planCategoryService.findPlanCategoryById(addPlanRequest.getPlanCategoryId()))
                .setLimitCategory(limitCategoryService.findLimitCategoryById(addPlanRequest.getLimitCategoryId()))
                .setCopayCategory(copayCategoryService.findCopayCategoryById(addPlanRequest.getCopayCategoryId()))
                .setDepartmentLimits(StreamSupport.stream(addPlanRequest.getDepartmentLimitRequests().spliterator(),false)
                        .map(addDepartmentLimitRequest -> new DepartmentLimitDto()
                                .setDepartment(departmentService.findDepartmentById(addDepartmentLimitRequest.getDepartmentId()))
                                .setOverallLimit(addDepartmentLimitRequest.getOverallLimit())
                                .setVisitLimit(addDepartmentLimitRequest.getVisitLimit())
                                .setCopay(addDepartmentLimitRequest.getCopay()))
                        .collect(Collectors.toSet()));
        return Response.ok().setPayload(planService.updatePlan(planDto));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    public void  deletePlan(@PathVariable("id") final int id){
        planService.removePlan(id);
    }
}
