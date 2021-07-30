package com.cleviro.ErpManagerApp.dto.model.masters;

import com.cleviro.ErpManagerApp.dto.model.people.UserDto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@ToString
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class StoreDto {
    private int id;
    private String name;
    private String status;

    private LocationDto location;
    private Set<UserDto> users;  //All Users who have access to a store
}
