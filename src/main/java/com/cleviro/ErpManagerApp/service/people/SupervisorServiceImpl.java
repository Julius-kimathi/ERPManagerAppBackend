package com.cleviro.ErpManagerApp.service.people;

import com.cleviro.ErpManagerApp.dto.mapper.people.SupervisorMapper;
import com.cleviro.ErpManagerApp.dto.model.people.SupervisorDto;
import com.cleviro.ErpManagerApp.exception.EntityType;
import com.cleviro.ErpManagerApp.exception.ExceptionType;
import com.cleviro.ErpManagerApp.model.people.Supervisor;
import com.cleviro.ErpManagerApp.repository.people.SupervisorRepository;
import com.cleviro.ErpManagerApp.util.ExceptionUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class SupervisorServiceImpl implements SupervisorService{
    @Autowired
    private SupervisorRepository supervisorRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public SupervisorDto findSupervisorById(Long id) {
        Optional<Supervisor> supervisor = supervisorRepository.findById(id);
        if (supervisor.isPresent())
            return modelMapper.map(supervisor.get(), SupervisorDto.class);
        else throw ExceptionUtil.exception(EntityType.SUPERVISOR, ExceptionType.ENTITY_NOT_FOUND,id.toString());
    }

    @Override
    public Collection<SupervisorDto> findAllSupervisors() {
        return StreamSupport.stream(supervisorRepository.findAll().spliterator(),false)
                .map(SupervisorMapper::toSupervisorDto)
                .collect(Collectors.toSet());
    }

    @Override
    public SupervisorDto addSupervisor(SupervisorDto supervisorDto) {
        return null;
    }

    @Override
    public SupervisorDto updateSupervisor(SupervisorDto supervisorDto) {
        return null;
    }

    @Override
    public void removeSupervisor(Long id) {

    }
}
