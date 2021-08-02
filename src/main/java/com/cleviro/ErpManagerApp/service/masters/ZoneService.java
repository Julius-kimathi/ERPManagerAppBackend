package com.cleviro.ErpManagerApp.service.masters;

import com.cleviro.ErpManagerApp.dto.model.masters.ZoneDto;

import java.util.Collection;

public interface ZoneService {
    /**
     * Get a zone by id
     * @param id
     */
    ZoneDto findZoneById(int id);

    /**
     * Find all the zones
     * @param
     */
    Collection<ZoneDto> findAllZones();

    /**
     *
     * Create a new zone
     * @param zoneDto
     */
    ZoneDto addZone(ZoneDto zoneDto);

    /**
     * update zone details
     * @param zoneDto
     */
    ZoneDto updateZone(ZoneDto zoneDto);

    /**
     * delete a zone from db
     * @param id
     */
    void removeZone(int id);
}
