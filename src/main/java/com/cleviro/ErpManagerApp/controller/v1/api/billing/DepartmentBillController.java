package com.cleviro.ErpManagerApp.controller.v1.api.billing;

import com.cleviro.ErpManagerApp.controller.v1.request.billing.AddDepartmentBillRequest;
import com.cleviro.ErpManagerApp.dto.model.billing.DepartmentBillDetailDto;
import com.cleviro.ErpManagerApp.dto.model.billing.DepartmentBillDto;
import com.cleviro.ErpManagerApp.dto.response.Response;
import com.cleviro.ErpManagerApp.service.billing.DepartmentBillService;
import com.cleviro.ErpManagerApp.service.masters.DepartmentService;
import com.cleviro.ErpManagerApp.service.patients.SubVisitService;
import com.cleviro.ErpManagerApp.service.patients.VisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/billing/departmentBills")
public class DepartmentBillController {
    @Autowired
    private DepartmentBillService departmentBillService;
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private VisitService visitService;
    @Autowired
    private SubVisitService subVisitService;

    @GetMapping
    public Response getDepartmentBills(){
        return Response.ok().setPayload(departmentBillService.findAllDepartmentBills());
    }

    @GetMapping("/{id}")
    public Response getDepartmentBill(@PathVariable("id") final Long id){
        Optional<DepartmentBillDto> departmentBillDto = Optional.ofNullable(departmentBillService.findDepartmentBillById(id));
        if (departmentBillDto.isPresent())
            return Response.ok().setPayload(departmentBillDto.get());
        else
            return Response.badRequest().setErrors("DepartmentBill not found");
    }

    @PostMapping
    public Response addDepartmentBill(@RequestBody @Valid AddDepartmentBillRequest addDepartmentBillRequest){
        DepartmentBillDto departmentBillDto = new DepartmentBillDto()
                .setPaymentStatus(addDepartmentBillRequest.getPaymentStatus())
                .setDepartment(departmentService.findDepartmentById(addDepartmentBillRequest.getDepartmentId()))
                .setVisit(visitService.findVisitById(addDepartmentBillRequest.getVisitId()))
                .setSubVisit(subVisitService.findSubVisitById(addDepartmentBillRequest.getSubVisitId()))
                .setDepartmentBillDetails(addDepartmentBillRequest.getDepartmentBillDetails()
                        .stream()
                        .map(addDepartmentBillDetailRequest ->
                        new DepartmentBillDetailDto().setUnitPrice(addDepartmentBillDetailRequest.getUnitPrice()).setQuantity(addDepartmentBillDetailRequest.getQuantity())
                        ).collect(Collectors.toSet()));
        return Response.ok().setPayload(departmentBillService.addDepartmentBill(departmentBillDto));
    }

    @PutMapping
    public Response updateDepartmentBill(@RequestBody @Valid AddDepartmentBillRequest addDepartmentBillRequest){
        DepartmentBillDto departmentBillDto = new DepartmentBillDto()
                .setId(addDepartmentBillRequest.getId())
                .setPaymentStatus(addDepartmentBillRequest.getPaymentStatus())
                .setDepartment(departmentService.findDepartmentById(addDepartmentBillRequest.getDepartmentId()))
                .setVisit(visitService.findVisitById(addDepartmentBillRequest.getVisitId()))
                .setSubVisit(subVisitService.findSubVisitById(addDepartmentBillRequest.getSubVisitId()))
                .setDepartmentBillDetails(addDepartmentBillRequest.getDepartmentBillDetails()
                        .stream()
                        .map(addDepartmentBillDetailRequest ->
                                new DepartmentBillDetailDto()
                                        .setQuantity(addDepartmentBillDetailRequest.getQuantity())
                                        .setUnitPrice(addDepartmentBillDetailRequest.getUnitPrice())
                                        .setId(addDepartmentBillDetailRequest.getId())
                        ).collect(Collectors.toSet()));
        return Response.ok().setPayload(departmentBillService.updateDepartmentBill(departmentBillDto));
    }

    @DeleteMapping("/{id}")
    public void deleteDepartmentDetail(@PathVariable("id") final Long id){
        departmentBillService.removeDepartmentBill(id);
    }
}
