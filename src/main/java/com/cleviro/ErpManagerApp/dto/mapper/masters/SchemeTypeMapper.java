package com.cleviro.ErpManagerApp.dto.mapper.masters;

import com.cleviro.ErpManagerApp.dto.model.masters.SchemeTypeDto;
import com.cleviro.ErpManagerApp.model.masters.SchemeType;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class SchemeTypeMapper {
    public static SchemeTypeDto toSchemeTypeDto(SchemeType schemeType){
        return new SchemeTypeDto()
                .setId(schemeType.getId())
                .setName(schemeType.getName())
                .setSchemes(StreamSupport.stream(schemeType.getSchemes().spliterator(),false).map(SchemeMapper::toSchemeDto).collect(Collectors.toSet()));
    }
}
