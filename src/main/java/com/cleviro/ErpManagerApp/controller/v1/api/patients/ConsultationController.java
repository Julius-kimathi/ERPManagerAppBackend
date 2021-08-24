package com.cleviro.ErpManagerApp.controller.v1.api.patients;

import com.cleviro.ErpManagerApp.controller.v1.request.patients.AddConsultationRequest;
import com.cleviro.ErpManagerApp.dto.model.patients.ConsultationDto;
import com.cleviro.ErpManagerApp.dto.response.Response;
import com.cleviro.ErpManagerApp.service.patients.ConsultationService;
import com.cleviro.ErpManagerApp.service.patients.VisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/patients/consultation")
public class ConsultationController {
    @Autowired
    private ConsultationService consultationService;
    @Autowired
    private VisitService visitService;

    @GetMapping
    public Response getConsultations(){
        return Response.ok().setPayload(consultationService.findAllConsultations());
    }

    @GetMapping("/{id}")
    public Response getConsultation(@PathVariable("id") final Long id){
        Optional<ConsultationDto> consultationDto = Optional.ofNullable(consultationService.findConsultationById(id));
        if (consultationDto.isPresent())
            return Response.ok().setPayload(consultationDto.get());
            else
                return Response.badRequest().setErrors("Consultation not found");
    }

    @PostMapping
    public Response addConsultation(@RequestBody @Valid AddConsultationRequest addConsultationRequest){
        ConsultationDto consultationDto = new ConsultationDto()
                .setStatus(addConsultationRequest.getStatus())
                .setVisit(visitService.findVisitById(addConsultationRequest.getVisitId()));
        return Response.ok().setPayload(consultationService.addConsultation(consultationDto));
    }

    @PutMapping
    public Response updateConsultation(@RequestBody @Valid AddConsultationRequest addConsultationRequest){
        ConsultationDto consultationDto = new ConsultationDto()
                .setId(addConsultationRequest.getId())
                .setStatus(addConsultationRequest.getStatus())
                .setVisit(visitService.findVisitById(addConsultationRequest.getVisitId()));
        return Response.ok().setPayload(consultationService.updateConsultation(consultationDto));
    }

    @DeleteMapping("/{id}")
    public void deleteConsultation(@PathVariable("id") final Long id){
        consultationService.removeConsultation(id);
    }
}
