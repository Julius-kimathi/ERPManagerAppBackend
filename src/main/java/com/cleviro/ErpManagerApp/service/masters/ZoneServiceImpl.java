package com.cleviro.ErpManagerApp.service.masters;

import com.cleviro.ErpManagerApp.dto.mapper.masters.ZoneMapper;
import com.cleviro.ErpManagerApp.dto.model.masters.ZoneDto;
import com.cleviro.ErpManagerApp.exception.EntityType;
import com.cleviro.ErpManagerApp.exception.ExceptionType;
import com.cleviro.ErpManagerApp.model.masters.Company;
import com.cleviro.ErpManagerApp.model.masters.Zone;
import com.cleviro.ErpManagerApp.repository.masters.ZoneRepository;
import com.cleviro.ErpManagerApp.util.ExceptionUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class ZoneServiceImpl implements ZoneService{

    @Autowired
    private ZoneRepository zoneRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ZoneDto findZoneById(int id) {
        Optional<Zone> zone = zoneRepository.findById(id);
        if (zone.isPresent())
            return ZoneMapper.toZoneDto(zone.get());
        else throw ExceptionUtil.exception(EntityType.ZONE, ExceptionType.ENTITY_NOT_FOUND,String.valueOf(id));
    }

    @Override
    public Collection<ZoneDto> findAllZones() {
        return StreamSupport.stream(zoneRepository.findAll().spliterator(), false)
                .map(ZoneMapper::toZoneDto)
                .collect(Collectors.toSet());
    }

    @Override
    public ZoneDto addZone(ZoneDto zoneDto) {
        Optional<Zone> zone = Optional.ofNullable(zoneRepository.findByName(zoneDto.getName()));
        if (!zone.isPresent()){
            Zone zoneModel = new Zone()
                    .setName(zoneDto.getName())
                    .setAbbreviation(zoneDto.getAbbreviation())
                    .setCompany(modelMapper.map(zoneDto.getCompany(), Company.class));
            return ZoneMapper.toZoneDto(zoneRepository.save(zoneModel));
        }
        throw ExceptionUtil.exception(EntityType.ZONE, ExceptionType.DUPLICATE_ENTITY,zoneDto.getName());
    }

    @Override
    public ZoneDto updateZone(ZoneDto zoneDto) {
        Optional<Zone> zone = zoneRepository.findById(zoneDto.getId());
        if (zone.isPresent()){
            List<Zone> zones = zoneRepository.findAllByName(zoneDto.getName());
            if (zones.size() > 1)
            {
                Zone zoneModel = zone.get()
                        .setName(zoneDto.getName())
                        .setAbbreviation(zoneDto.getAbbreviation())
                        .setCompany(modelMapper.map(zoneDto.getCompany(), Company.class));
                return ZoneMapper.toZoneDto(zoneRepository.save(zoneModel));
            }

            else  throw ExceptionUtil.exception(EntityType.ZONE, ExceptionType.DUPLICATE_ENTITY,zoneDto.getName());
        }
        throw ExceptionUtil.exception(EntityType.ZONE, ExceptionType.ENTITY_NOT_FOUND,zoneDto.getName());
    }

    @Override
    public void removeZone(int id) {
        Optional<Zone> zone = zoneRepository.findById(id);
        if (zone.isPresent())
            zoneRepository.deleteById(id);
        else throw ExceptionUtil.exception(EntityType.ZONE,ExceptionType.ENTITY_NOT_FOUND,String.valueOf(id));
    }
}
