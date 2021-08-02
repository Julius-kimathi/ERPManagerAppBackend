package com.cleviro.ErpManagerApp.dto.mapper.masters;

import com.cleviro.ErpManagerApp.dto.model.masters.LocationDto;
import com.cleviro.ErpManagerApp.dto.model.masters.StoreDto;
import com.cleviro.ErpManagerApp.dto.model.people.UserDto;
import com.cleviro.ErpManagerApp.model.masters.Store;
import org.modelmapper.ModelMapper;

import java.util.HashSet;
import java.util.stream.Collectors;

public class StoreMapper {
    public static StoreDto toStoreDto(Store store){
        return new StoreDto()
                .setId(store.getId())
                .setName(store.getName())
                .setStatus(store.getStatus())
                .setLocation(new ModelMapper().map(store.getLocation(), LocationDto.class))
                .setUsers(new HashSet<UserDto>(store.getUsers().stream().map(user -> new ModelMapper().map(user, UserDto.class)).collect(Collectors.toSet())));
    }
}
