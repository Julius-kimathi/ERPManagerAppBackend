package com.cleviro.ErpManagerApp.dto.mapper.masters;

import com.cleviro.ErpManagerApp.dto.model.masters.CompanyDto;
import com.cleviro.ErpManagerApp.dto.model.masters.LocationDto;
import com.cleviro.ErpManagerApp.dto.model.masters.StoreDto;
import com.cleviro.ErpManagerApp.dto.model.masters.ZoneDto;
import com.cleviro.ErpManagerApp.dto.model.people.EmployeeDto;
import com.cleviro.ErpManagerApp.dto.model.people.UserDto;
import com.cleviro.ErpManagerApp.model.masters.Location;
import org.modelmapper.ModelMapper;

import java.util.HashSet;
import java.util.stream.Collectors;

public class LocationMapper {
    public static LocationDto toLocationDto(Location location){
        return new LocationDto()
                .setId(location.getId())
                .setName(location.getName())
                .setAbbreviation(location.getAbbreviation())
                .setPostalAddress(location.getPostalAddress())
                .setState(location.getState())
                .setCity(location.getCity())
                .setPhone(location.getPhone())
                .setPhone1(location.getPhone1())
                .setEmail(location.getEmail())
                .setEmail1(location.getEmail1())
                .setStatus(location.getStatus())
                .setCompany(new ModelMapper().map(location.getCompany(), CompanyDto.class))
                .setZone(new ModelMapper().map(location.getZone(), ZoneDto.class))
                .setStores(new HashSet<StoreDto>(location.getStores().stream().map(store -> new ModelMapper().map(store, StoreDto.class)).collect(Collectors.toSet())))
                .setEmployees(new HashSet<EmployeeDto>(location.getEmployees().stream().map(employee -> new ModelMapper().map(employee, EmployeeDto.class)).collect(Collectors.toSet())))
                .setUsers(new HashSet<UserDto>(location.getUsers().stream().map(user -> new ModelMapper().map(user, UserDto.class)).collect(Collectors.toSet())));

    }
}
