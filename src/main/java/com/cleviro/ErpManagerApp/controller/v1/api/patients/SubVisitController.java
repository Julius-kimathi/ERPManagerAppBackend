package com.cleviro.ErpManagerApp.controller.v1.api.patients;

import com.cleviro.ErpManagerApp.controller.v1.request.patients.AddSubVisitRequest;
import com.cleviro.ErpManagerApp.dto.model.patients.SubVisitDto;
import com.cleviro.ErpManagerApp.dto.response.Response;
import com.cleviro.ErpManagerApp.service.masters.LocationService;
import com.cleviro.ErpManagerApp.service.patients.SubVisitService;
import com.cleviro.ErpManagerApp.service.patients.VisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/patients/subVisits")
public class SubVisitController {
    @Autowired
    private SubVisitService subVisitService;
    @Autowired
    private LocationService locationService;
    @Autowired
    private VisitService visitService;

    @GetMapping
    public Response getSubVisits(){
        return Response.ok().setPayload(subVisitService.findAllSubVisits());
    }

    @GetMapping("/{id}")
    public Response getSubVisit(@PathVariable("id") final Long id){
        Optional<SubVisitDto> subVisitDto = Optional.ofNullable(subVisitService.findSubVisitById(id));
        if (subVisitDto.isPresent())
            return Response.ok().setPayload(subVisitDto.get());
            else
                return Response.badRequest().setErrors("Follow-up not found");
    }

    @PostMapping
    public Response addSubVisit(@RequestBody @Valid AddSubVisitRequest addSubVisitRequest){
        SubVisitDto subVisitDto = new SubVisitDto()
                .setLocation(locationService.findLocationById(addSubVisitRequest.getLocationId()))
                .setVisit(visitService.findVisitById(addSubVisitRequest.getVisitId()));
        return Response.ok().setPayload(subVisitService.addSubVisit(subVisitDto));
    }

    @PutMapping
    public Response updateSubVisit(@RequestBody @Valid AddSubVisitRequest addSubVisitRequest){
        SubVisitDto subVisitDto = new SubVisitDto()
                .setId(addSubVisitRequest.getId())
                .setLocation(locationService.findLocationById(addSubVisitRequest.getLocationId()))
                .setVisit(visitService.findVisitById(addSubVisitRequest.getVisitId()));
        return Response.ok().setPayload(subVisitService.updateSubVisit(subVisitDto));
    }

    @DeleteMapping("/{id}")
    public void deleteSubVisit(@PathVariable("id") final Long id){
        subVisitService.removeSubVisit(id);
    }
}
