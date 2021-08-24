package com.cleviro.ErpManagerApp.controller.v1.api.patients;

import com.cleviro.ErpManagerApp.controller.v1.request.patients.AddTriageRequest;
import com.cleviro.ErpManagerApp.dto.model.patients.TriageDto;
import com.cleviro.ErpManagerApp.dto.response.Response;
import com.cleviro.ErpManagerApp.service.patients.TriageService;
import com.cleviro.ErpManagerApp.service.patients.VisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/patients/triage")
public class TriageController {
    @Autowired
    private TriageService triageService;
    @Autowired
    private VisitService visitService;

    @GetMapping
    public Response getTriages(){
        return Response.ok().setPayload(triageService.findAllTriages());
    }

    @GetMapping("/{id}")
    public Response getTriage(@PathVariable("id") final Long id){
        Optional<TriageDto> triageDto = Optional.ofNullable(triageService.findTriageById(id));
        if (triageDto.isPresent())
            return Response.ok().setPayload(triageDto.get());
            else
                return Response.badRequest().setErrors("Triage not found");
    }

    @PostMapping
    public Response addTriage(@RequestBody @Valid AddTriageRequest addTriageRequest){
        TriageDto triageDto = new TriageDto()
                .setStatus(addTriageRequest.getStatus())
                .setVisit(visitService.findVisitById(addTriageRequest.getVisitId()));
        return Response.ok().setPayload(triageService.addTriage(triageDto));
    }

    @PutMapping
    public Response updateTriage(@RequestBody @Valid AddTriageRequest addTriageRequest){
        TriageDto triageDto = new TriageDto()
                .setId(addTriageRequest.getId())
                .setStatus(addTriageRequest.getStatus())
                .setVisit(visitService.findVisitById(addTriageRequest.getVisitId()));
        return Response.ok().setPayload(triageService.updateTriage(triageDto));
    }

    @DeleteMapping("/{id}")
    public void deleteTriage(@PathVariable("id") final Long id){
        triageService.removeTriage(id);
    }
}
