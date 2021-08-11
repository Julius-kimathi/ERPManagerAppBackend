package com.cleviro.ErpManagerApp.service.masters;

import com.cleviro.ErpManagerApp.dto.mapper.masters.SchemeMapper;
import com.cleviro.ErpManagerApp.dto.model.masters.SchemeDto;
import com.cleviro.ErpManagerApp.exception.EntityType;
import com.cleviro.ErpManagerApp.exception.ExceptionType;
import com.cleviro.ErpManagerApp.model.masters.Payer;
import com.cleviro.ErpManagerApp.model.masters.Scheme;
import com.cleviro.ErpManagerApp.model.masters.SchemeType;
import com.cleviro.ErpManagerApp.repository.masters.SchemeRepository;
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
public class SchemeServiceImpl implements SchemeService{
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private SchemeRepository schemeRepository;

    @Override
    public SchemeDto findCompanyById(int id) {
        Optional<Scheme> scheme = schemeRepository.findById(id);
        if (scheme.isPresent())
            return SchemeMapper.toSchemeDto(scheme.get());
        else
            throw ExceptionUtil.exception(EntityType.SCHEME, ExceptionType.ENTITY_NOT_FOUND,String.valueOf(id));
    }

    @Override
    public Collection<SchemeDto> findAllschemes() {
        return StreamSupport.stream(schemeRepository.findAll().spliterator(),false)
                .map(SchemeMapper::toSchemeDto)
                .collect(Collectors.toSet());
    }

    @Override
    public SchemeDto addScheme(SchemeDto schemeDto) {
        Optional<Scheme> scheme = schemeRepository.findByNameAndPayerAndSchemeType(schemeDto.getName(),schemeDto.getPayer(),schemeDto.getSchemeType());
        if (!scheme.isPresent()){
            String code = RandomStringUtil.getAlphaNumericString(6,EntityType.SCHEME);
           Scheme schemeModel = new Scheme()
           .setSchemeCode(code)
           .setName(schemeDto.getName())
           .setStatus(schemeDto.getStatus())
           .setIsPreauthRequired(schemeDto.getIsPreauthRequired())
           .setSchemeType(modelMapper.map(schemeDto.getSchemeType(), SchemeType.class))
           .setPayer(modelMapper.map(schemeDto.getPayer(), Payer.class));
           return SchemeMapper.toSchemeDto(schemeRepository.save(schemeModel));
        }
        throw ExceptionUtil.exception(EntityType.SCHEME, ExceptionType.DUPLICATE_ENTITY,schemeDto.toString());
    }

    @Override
    public SchemeDto updateScheme(SchemeDto schemeDto) {
        Optional<Scheme> scheme = schemeRepository.findById(schemeDto.getId());
        if (scheme.isPresent()){
            Scheme schemeModel = scheme.get()
                    .setName(schemeDto.getName())
                    .setStatus(schemeDto.getStatus())
                    .setIsPreauthRequired(schemeDto.getIsPreauthRequired())
                    .setSchemeType(modelMapper.map(schemeDto.getSchemeType(), SchemeType.class))
                    .setPayer(modelMapper.map(schemeDto.getPayer(), Payer.class));
            return SchemeMapper.toSchemeDto(schemeRepository.save(schemeModel));
        }
        throw ExceptionUtil.exception(EntityType.SCHEME, ExceptionType.ENTITY_NOT_FOUND,schemeDto.toString());
    }

    @Override
    public void removeScheme(int id) {
        Optional<Scheme> scheme = schemeRepository.findById(id);
        if (scheme.isPresent())
            schemeRepository.deleteById(id);
        else  throw ExceptionUtil.exception(EntityType.SCHEME, ExceptionType.ENTITY_NOT_FOUND,String.valueOf(id));
    }
}
