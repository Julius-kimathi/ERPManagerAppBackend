package com.cleviro.ErpManagerApp.repository.masters;

import com.cleviro.ErpManagerApp.dto.model.masters.ConsultationTypeDto;
import com.cleviro.ErpManagerApp.dto.model.masters.DepartmentDto;
import com.cleviro.ErpManagerApp.dto.model.masters.LocationDto;
import com.cleviro.ErpManagerApp.dto.model.masters.SchemeDto;
import com.cleviro.ErpManagerApp.dto.model.people.EmployeeDto;
import com.cleviro.ErpManagerApp.model.masters.ConsultationRate;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ConsultationRateRepository extends CrudRepository<ConsultationRate, Integer> {
    Optional<ConsultationRate> findByConsultationTypeAndDoctorAndLocationAndSchemeAndDepartment(ConsultationTypeDto consultationType, EmployeeDto doctor, LocationDto location, SchemeDto scheme, DepartmentDto departmentDto);
}
