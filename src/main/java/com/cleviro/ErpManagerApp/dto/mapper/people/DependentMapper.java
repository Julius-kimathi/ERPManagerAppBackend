package com.cleviro.ErpManagerApp.dto.mapper.people;

import com.cleviro.ErpManagerApp.dto.model.masters.CompanyDto;
import com.cleviro.ErpManagerApp.dto.model.masters.CountryDto;
import com.cleviro.ErpManagerApp.dto.model.masters.LocationDto;
import com.cleviro.ErpManagerApp.dto.model.patients.VisitDto;
import com.cleviro.ErpManagerApp.dto.model.people.DependentDto;
import com.cleviro.ErpManagerApp.dto.model.people.UserDto;
import com.cleviro.ErpManagerApp.model.people.Dependent;
import org.modelmapper.ModelMapper;

import java.util.HashSet;
import java.util.stream.Collectors;

public class DependentMapper {
    public static DependentDto toDependentDto(Dependent dependent){
        return new DependentDto()
                .setId(dependent.getId())
                .setDependentCode(dependent.getDependentCode())
                .setFirstName(dependent.getFirstName())
                .setMiddleName(dependent.getMiddleName())
                .setLastName(dependent.getLastName())
                .setDob(dependent.getDob())
                .setEmail(dependent.getEmail())
                .setPhone(dependent.getPhone())
                .setIdNo(dependent.getIdNo())
                .setPostalAddress(dependent.getPostalAddress())
                .setCity(dependent.getCity())
                .setState(dependent.getState())
                .setStatus(dependent.getStatus())
                .setRegDate(dependent.getRegDate())
                .setGender(dependent.getGender())
                .setUser(new ModelMapper().map(dependent.getUser(), UserDto.class))
                .setCountry(new ModelMapper().map(dependent.getCountry(), CountryDto.class))
                .setCompany(new ModelMapper().map(dependent.getCompany(), CompanyDto.class))
                .setLocation(new ModelMapper().map(dependent.getLocation(), LocationDto.class))
                .setVisits(new HashSet<>(dependent.getVisits().stream().map(visit -> new ModelMapper().map(visit, VisitDto.class)).collect(Collectors.toSet())));
    }
}
