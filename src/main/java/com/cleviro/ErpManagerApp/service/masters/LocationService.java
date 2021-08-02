package com.cleviro.ErpManagerApp.service.masters;

import com.cleviro.ErpManagerApp.dto.model.masters.LocationDto;

import java.util.Collection;

public interface LocationService {

    /**
     * Get a department by id
     * @param id
     */
    LocationDto findLocationById(int id);

    /**
     * Find all the locations
     * @param
     */
    Collection<LocationDto> findAllLocations();

    /**
     *
     * Create a new location
     * @param locationDto
     */
    LocationDto addLocation(LocationDto locationDto);

    /**
     * update location details
     * @param locationDto
     */
    LocationDto updateLocation(LocationDto locationDto);

    /**
     * delete a location from db
     * @param id
     */
    void removeLocation(int id);
}
