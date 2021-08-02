package com.cleviro.ErpManagerApp.dto.mapper.masters;

import com.cleviro.ErpManagerApp.dto.model.masters.CompanyDto;
import com.cleviro.ErpManagerApp.dto.model.masters.LocationDto;
import com.cleviro.ErpManagerApp.dto.model.masters.ZoneDto;
import com.cleviro.ErpManagerApp.model.masters.Zone;
import org.modelmapper.ModelMapper;

import java.util.HashSet;
import java.util.stream.Collectors;

public class ZoneMapper {
    public static ZoneDto toZoneDto(Zone zone){
        return new ZoneDto()
                .setId(zone.getId())
                .setName(zone.getName())
                .setAbbreviation(zone.getAbbreviation())
                .setCompany(new ModelMapper().map(zone.getCompany(), CompanyDto.class))
                .setLocations(new HashSet<LocationDto>(zone.getLocations().stream().map(location -> new ModelMapper().map(location, LocationDto.class)).collect(Collectors.toSet())));
    }
}
