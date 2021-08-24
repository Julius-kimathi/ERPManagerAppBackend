package com.cleviro.ErpManagerApp.service.people;

import com.cleviro.ErpManagerApp.dto.mapper.people.DependentMapper;
import com.cleviro.ErpManagerApp.dto.model.people.DependentDto;
import com.cleviro.ErpManagerApp.exception.EntityType;
import com.cleviro.ErpManagerApp.exception.ExceptionType;
import com.cleviro.ErpManagerApp.model.masters.Company;
import com.cleviro.ErpManagerApp.model.masters.Country;
import com.cleviro.ErpManagerApp.model.masters.Location;
import com.cleviro.ErpManagerApp.model.people.Dependent;
import com.cleviro.ErpManagerApp.model.people.User;
import com.cleviro.ErpManagerApp.repository.people.DependentRepository;
import com.cleviro.ErpManagerApp.util.ExceptionUtil;
import com.cleviro.ErpManagerApp.util.RandomStringUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class DependentServiceImpl implements DependentService{
    @Autowired
    private DependentRepository dependentRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public DependentDto findDependentById(Long id) {
        Optional<Dependent> dependent = dependentRepository.findById(id);
        if (dependent.isPresent())
            return DependentMapper.toDependentDto(dependent.get());
        else throw ExceptionUtil.exception(EntityType.DEPENDENT, ExceptionType.ENTITY_NOT_FOUND,id.toString());
    }

    @Override
    public DependentDto findDependentByIdNo(String idNo) {
        Optional<Dependent> dependent = dependentRepository.findByIdNo(idNo);
        if (dependent.isPresent())
            return DependentMapper.toDependentDto(dependent.get());
        else throw ExceptionUtil.exception(EntityType.DEPENDENT, ExceptionType.ENTITY_NOT_FOUND,idNo);
    }

    @Override
    public DependentDto findDependentByEmail(String email) {
        Optional<Dependent> dependent = dependentRepository.findByEmail(email);
        if (dependent.isPresent())
            return DependentMapper.toDependentDto(dependent.get());
        else throw ExceptionUtil.exception(EntityType.DEPENDENT, ExceptionType.ENTITY_NOT_FOUND,email);
    }

    @Override
    public Collection<DependentDto> findAllDependents() {
        return StreamSupport.stream(dependentRepository.findAll().spliterator(),false)
                .map(DependentMapper::toDependentDto)
                .collect(Collectors.toSet());
    }

    @Override
    public DependentDto addDependent(DependentDto dependentDto) {
       Optional<Dependent> dependent = dependentRepository.findByFirstNameAndMiddleNameAndLastNameAndDob(dependentDto.getFirstName(),dependentDto.getMiddleName(),dependentDto.getLastName(),dependentDto.getDob());
       if (!dependent.isPresent()){
           Optional<Dependent> dependent1 = dependentRepository.findByEmail(dependentDto.getEmail());
           if (!dependent1.isPresent()){
               Optional<Dependent> dependent2 = dependentRepository.findByIdNo(dependentDto.getIdNo());
               if (!dependent2.isPresent()){
                   String code = RandomStringUtil.getAlphaNumericString(6,EntityType.DEPENDENT);
                   Dependent dependentModel = new Dependent()
                           .setDependentCode(code)
                           .setFirstName(dependentDto.getFirstName())
                           .setMiddleName(dependentDto.getMiddleName())
                           .setLastName(dependentDto.getFirstName())
                           .setDob(dependentDto.getDob())
                           .setEmail(dependentDto.getEmail())
                           .setPhone(dependentDto.getPhone())
                           .setIdNo(dependentDto.getIdNo())
                           .setPostalAddress(dependentDto.getPostalAddress())
                           .setCity(dependentDto.getCity())
                           .setState(dependentDto.getState())
                           .setStatus(dependentDto.getStatus())
                           .setRegDate(dependentDto.getRegDate())
                           .setGender(dependentDto.getGender())
                           .setUser(modelMapper.map(dependentDto.getUser(), User.class))
                           .setCountry(modelMapper.map(dependentDto.getCountry(), Country.class))
                           .setCompany(modelMapper.map(dependentDto.getCompany(), Company.class))
                           .setLocation(modelMapper.map(dependentDto.getLocation(), Location.class));
               }
               throw ExceptionUtil.exception(EntityType.DEPENDENT, ExceptionType.DUPLICATE_ENTITY,dependentDto.getIdNo());
           }
           throw ExceptionUtil.exception(EntityType.DEPENDENT, ExceptionType.DUPLICATE_ENTITY,dependentDto.getEmail());
       }
        throw ExceptionUtil.exception(EntityType.DEPENDENT, ExceptionType.DUPLICATE_ENTITY,dependentDto.getFirstName(),dependentDto.getMiddleName(),dependentDto.getLastName(),dependentDto.getDob().toString());
    }

    @Override
    public DependentDto updateDependent(DependentDto dependentDto) {
        Optional<Dependent> dependent = dependentRepository.findById(dependentDto.getId());
        if (dependent.isPresent()){
            Dependent dependentModel = dependent.get()
                    .setFirstName(dependentDto.getFirstName())
                    .setMiddleName(dependentDto.getMiddleName())
                    .setLastName(dependentDto.getFirstName())
                    .setDob(dependentDto.getDob())
                    .setEmail(dependentDto.getEmail())
                    .setPhone(dependentDto.getPhone())
                    .setIdNo(dependentDto.getIdNo())
                    .setPostalAddress(dependentDto.getPostalAddress())
                    .setCity(dependentDto.getCity())
                    .setState(dependentDto.getState())
                    .setStatus(dependentDto.getStatus())
                    .setRegDate(dependentDto.getRegDate())
                    .setGender(dependentDto.getGender())
                    .setUser(modelMapper.map(dependentDto.getUser(), User.class))
                    .setCountry(modelMapper.map(dependentDto.getCountry(), Country.class))
                    .setCompany(modelMapper.map(dependentDto.getCompany(), Company.class))
                    .setLocation(modelMapper.map(dependentDto.getLocation(), Location.class));
        }
        throw ExceptionUtil.exception(EntityType.DEPENDENT, ExceptionType.ENTITY_NOT_FOUND,dependentDto.toString());
    }

    @Override
    public void removeDependent(Long id) {
        Optional<Dependent> dependent = dependentRepository.findById(id);
        if (dependent.isPresent())
           dependentRepository.deleteById(id);
        else throw ExceptionUtil.exception(EntityType.DEPENDENT, ExceptionType.ENTITY_NOT_FOUND,id.toString());
    }
}
