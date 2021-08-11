package com.cleviro.ErpManagerApp.controller.v1.api.masters;

import com.cleviro.ErpManagerApp.controller.v1.request.masters.AddConsultationRateRequest;
import com.cleviro.ErpManagerApp.dto.model.masters.ConsultationRateDto;
import com.cleviro.ErpManagerApp.dto.response.Response;
import com.cleviro.ErpManagerApp.service.masters.*;
import com.cleviro.ErpManagerApp.service.people.EmployeeService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/masters/consultationRates")
public class ConsultationRateController {
    @Autowired
    private ConsultationRateService consultationRateService;
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private LocationService locationService;
    @Autowired
    private SchemeService schemeService;
    @Autowired
    private ConsultationTypeService consultationTypeService;
    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    @ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    public Response getConsultationRates(){
        return Response.ok().setPayload(consultationRateService.findAllConsultationRates());
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    public Response getConsultationRateById(@PathVariable("id") final Integer id){
        Optional<ConsultationRateDto> consultationRateDto = Optional.ofNullable(consultationRateService.findConsultationRateById(id));
        if (consultationRateDto.isPresent())
            return Response.ok().setPayload(consultationRateDto.get());
        else
            return Response.badRequest().setErrors("Consultation Rate not found");
    }

    @PostMapping
    @ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    public Response addConsultationRate(@RequestBody @Valid AddConsultationRateRequest addConsultationRateRequest){

        ConsultationRateDto consultationRateDto = new ConsultationRateDto()
                .setFees(addConsultationRateRequest.getFees())
                .setConsultationType(consultationTypeService.findConsultationTypeById(addConsultationRateRequest.getConsultationTypeId()))
                .setDoctor(employeeService.findEmployeeById(addConsultationRateRequest.getDoctorId()))
                .setLocation(locationService.findLocationById(addConsultationRateRequest.getLocationId()))
                .setScheme(schemeService.findSchemeById(addConsultationRateRequest.getSchemeId()))
                .setDepartment(departmentService.findDepartmentById(addConsultationRateRequest.getDepartmentId()));
        return Response.ok().setPayload(consultationRateService.addConsultationRate(consultationRateDto));
    }

    @PutMapping
    @ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    public Response updateConsultationRate(@RequestBody @Valid AddConsultationRateRequest addConsultationRateRequest){
        ConsultationRateDto consultationRateDto = new ConsultationRateDto()
                .setFees(addConsultationRateRequest.getFees())
                .setId(addConsultationRateRequest.getId())
                .setConsultationType(consultationTypeService.findConsultationTypeById(addConsultationRateRequest.getConsultationTypeId()))
                .setDoctor(employeeService.findEmployeeById(addConsultationRateRequest.getDoctorId()))
                .setLocation(locationService.findLocationById(addConsultationRateRequest.getLocationId()))
                .setScheme(schemeService.findSchemeById(addConsultationRateRequest.getSchemeId()))
                .setDepartment(departmentService.findDepartmentById(addConsultationRateRequest.getDepartmentId()));
        return Response.ok().setPayload(consultationRateService.updateConsultationRate(consultationRateDto));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    public void  deleteConsultationRate(@PathVariable("id") final int id){
        consultationRateService.removeConsultationRate(id);
    }
}
