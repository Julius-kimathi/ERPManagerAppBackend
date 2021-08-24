package com.cleviro.ErpManagerApp.controller.v1.api.billing;

import com.cleviro.ErpManagerApp.controller.v1.request.billing.AddDepartmentBillDetailRequest;
import com.cleviro.ErpManagerApp.dto.model.billing.DepartmentBillDetailDto;
import com.cleviro.ErpManagerApp.dto.response.Response;
import com.cleviro.ErpManagerApp.service.billing.DepartmentBillDetailService;
import com.cleviro.ErpManagerApp.service.billing.DepartmentBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/billing/departmentBillDetails")
public class DepartmentBillDetailController {
    @Autowired
    private DepartmentBillDetailService departmentBillDetailService;
    @Autowired
    private DepartmentBillService departmentBillService;

    @GetMapping
    public Response getDepartmentBillDetails(){
        return Response.ok().setPayload(departmentBillDetailService.findAllDepartmentBillDetails());
    }

    @GetMapping("/{id}")
    public Response getDepartmentBillDetail(@PathVariable("id") final Long id){
        Optional<DepartmentBillDetailDto> departmentBillDetailDto = Optional.ofNullable(departmentBillDetailService.findDepartmentBillDetailById(id));
        if (departmentBillDetailDto.isPresent())
            return Response.ok().setPayload(departmentBillDetailDto.get());
        else
            return Response.badRequest().setErrors("DepartmentBillDetail not found");
    }

    @PostMapping
    public Response addDepartmentDetail(@RequestBody @Valid AddDepartmentBillDetailRequest addDepartmentBillDetailRequest){
        DepartmentBillDetailDto departmentBillDetailDto = new DepartmentBillDetailDto()
                .setQuantity(addDepartmentBillDetailRequest.getQuantity())
                .setUnitPrice(addDepartmentBillDetailRequest.getUnitPrice());
        return Response.ok().setPayload(departmentBillDetailService.addDepartmentBillDetail(departmentBillDetailDto));
    }

    @PutMapping
    public Response updateDepartmentBillDetail(@RequestBody @Valid AddDepartmentBillDetailRequest addDepartmentBillDetailRequest){
        DepartmentBillDetailDto departmentBillDetailDto = new DepartmentBillDetailDto()
                .setId(addDepartmentBillDetailRequest.getId())
                .setQuantity(addDepartmentBillDetailRequest.getQuantity())
                .setUnitPrice(addDepartmentBillDetailRequest.getUnitPrice());
        return Response.ok().setPayload(departmentBillDetailService.updateDepartmentBillDetail(departmentBillDetailDto));
    }

    @DeleteMapping("/{id}")
    public void deleteDepartmentBillDetail(@PathVariable("id") final Long id){
        departmentBillDetailService.removeDepartmentBillDetail(id);
    }
}
