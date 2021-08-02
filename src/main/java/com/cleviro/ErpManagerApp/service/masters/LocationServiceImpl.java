package com.cleviro.ErpManagerApp.service.masters;

import com.cleviro.ErpManagerApp.dto.mapper.masters.LocationMapper;
import com.cleviro.ErpManagerApp.dto.model.masters.LocationDto;
import com.cleviro.ErpManagerApp.exception.EntityType;
import com.cleviro.ErpManagerApp.exception.ExceptionType;
import com.cleviro.ErpManagerApp.model.masters.Company;
import com.cleviro.ErpManagerApp.model.masters.Location;
import com.cleviro.ErpManagerApp.model.masters.Zone;
import com.cleviro.ErpManagerApp.repository.masters.LocationRepository;
import com.cleviro.ErpManagerApp.util.ExceptionUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class LocationServiceImpl implements LocationService{
    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public LocationDto findLocationById(int id) {
        Optional<Location> location = locationRepository.findById(id);
        if (location.isPresent())
            return LocationMapper.toLocationDto(location.get());
        else throw ExceptionUtil.exception(EntityType.LOCATION, ExceptionType.ENTITY_NOT_FOUND,String.valueOf(id));
    }

    @Override
    public Collection<LocationDto> findAllLocations() {
        return StreamSupport.stream(locationRepository.findAll().spliterator(), false)
                .map(LocationMapper::toLocationDto)
                .collect(Collectors.toSet());
    }

    @Override
    public LocationDto addLocation(LocationDto locationDto) {
        Optional<Location> location = Optional.ofNullable(locationRepository.findByName(locationDto.getName()));
        if (!location.isPresent()){
            Location locationModel = new Location()
                    .setName(locationDto.getName())
                    .setAbbreviation(locationDto.getAbbreviation())
                    .setPostalAddress(locationDto.getPostalAddress())
                    .setState(locationDto.getState())
                    .setCity(locationDto.getCity())
                    .setPhone(locationDto.getPhone())
                    .setPhone1(locationDto.getPhone1())
                    .setEmail(locationDto.getEmail())
                    .setEmail1(locationDto.getEmail1())
                    .setStatus(locationDto.getStatus())
                    .setCompany(modelMapper.map(locationDto.getCompany(), Company.class))
                    .setZone(modelMapper.map(locationDto.getZone(), Zone.class));
            return LocationMapper.toLocationDto(locationRepository.save(locationModel));

        }
        throw ExceptionUtil.exception(EntityType.LOCATION, ExceptionType.DUPLICATE_ENTITY,locationDto.getName());
    }

    @Override
    public LocationDto updateLocation(LocationDto locationDto) {
        Optional<Location> location = locationRepository.findById(locationDto.getId());
        if (location.isPresent()){
            Optional<Location> location1 = Optional.ofNullable(locationRepository.findByName(locationDto.getName()));
            if (!location1.isPresent()){
                Location locationModel = new Location()
                        .setName(locationDto.getName())
                        .setAbbreviation(locationDto.getAbbreviation())
                        .setPostalAddress(locationDto.getPostalAddress())
                        .setState(locationDto.getState())
                        .setCity(locationDto.getCity())
                        .setPhone(locationDto.getPhone())
                        .setPhone1(locationDto.getPhone1())
                        .setEmail(locationDto.getEmail())
                        .setEmail1(locationDto.getEmail1())
                        .setStatus(locationDto.getStatus())
                        .setCompany(modelMapper.map(locationDto.getCompany(), Company.class))
                        .setZone(modelMapper.map(locationDto.getZone(), Zone.class));
                return LocationMapper.toLocationDto(locationRepository.save(locationModel));
            }
            throw ExceptionUtil.exception(EntityType.LOCATION, ExceptionType.DUPLICATE_ENTITY,locationDto.getName());
        }
        throw ExceptionUtil.exception(EntityType.LOCATION, ExceptionType.ENTITY_NOT_FOUND,String.valueOf(locationDto.getId()));
    }

    @Override
    public void removeLocation(int id) {
        Optional<Location> location = locationRepository.findById(id);
        if (location.isPresent())
            locationRepository.deleteById(id);
        else throw ExceptionUtil.exception(EntityType.LOCATION, ExceptionType.ENTITY_NOT_FOUND,String.valueOf(id));

    }
}
