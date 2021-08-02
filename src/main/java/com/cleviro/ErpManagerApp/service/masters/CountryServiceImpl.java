package com.cleviro.ErpManagerApp.service.masters;

import com.cleviro.ErpManagerApp.dto.mapper.masters.CountryMapper;
import com.cleviro.ErpManagerApp.dto.model.masters.CountryDto;
import com.cleviro.ErpManagerApp.exception.EntityType;
import com.cleviro.ErpManagerApp.exception.ExceptionType;
import com.cleviro.ErpManagerApp.model.masters.Country;
import com.cleviro.ErpManagerApp.repository.masters.CountryRepository;
import com.cleviro.ErpManagerApp.util.ExceptionUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class CountryServiceImpl implements CountryService{

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CountryDto findCountryById(int id) {
        Optional<Country> country = countryRepository.findById(id);
        if (country.isPresent())
        {
            return CountryMapper.toCountryDto(country.get());
        }
        throw ExceptionUtil.exception(EntityType.COUNTRY, ExceptionType.ENTITY_NOT_FOUND, String.valueOf(id));
    }

    @Override
    public Collection<CountryDto> findAllCountries() {
        return StreamSupport.stream(countryRepository.findAll().spliterator(), false)
                .map(CountryMapper::toCountryDto)
                .collect(Collectors.toSet());
    }

    @Override
    public CountryDto addCountry(CountryDto countryDto) {
        Optional<Country> country = Optional.ofNullable(countryRepository.findByName(countryDto.getName()));
        if (country.isPresent()){
            Country countryModel = new Country()
                    .setName(countryDto.getName())
                    .setMobileCode(countryDto.getMobileCode())
                    .setAbbreviation(countryDto.getAbbreviation())
                    .setCurrency(countryDto.getCurrency())
                    .setCurrencyCode(countryDto.getCurrencyCode())
                    .setCurrencySymbol(countryDto.getCurrencySymbol());
            return CountryMapper.toCountryDto(countryRepository.save(countryModel));

        }
      throw  ExceptionUtil.exception(EntityType.COUNTRY,ExceptionType.DUPLICATE_ENTITY,countryDto.getName());
    }

    @Override
    public CountryDto updateCountry(CountryDto countryDto) {
        Optional<Country> country = countryRepository.findById(countryDto.getId());
        if (country.isPresent()){
            Optional<Country> country1 = Optional.ofNullable(countryRepository.findByName(countryDto.getName()));
            if (country1.isPresent()){
                Country countryModel = new Country()
                        .setName(countryDto.getName())
                        .setMobileCode(countryDto.getMobileCode())
                        .setAbbreviation(countryDto.getAbbreviation())
                        .setCurrency(countryDto.getCurrency())
                        .setCurrencyCode(countryDto.getCurrencyCode())
                        .setCurrencySymbol(countryDto.getCurrencySymbol());
                return CountryMapper.toCountryDto(countryRepository.save(countryModel));
            }
            throw  ExceptionUtil.exception(EntityType.COUNTRY,ExceptionType.DUPLICATE_ENTITY,countryDto.getName());
        }
        throw  ExceptionUtil.exception(EntityType.COUNTRY,ExceptionType.ENTITY_NOT_FOUND,countryDto.getName());
    }

    @Override
    public void removeCountry(int id) {
        Optional<Country> country = countryRepository.findById(id);
        if (country.isPresent())
            countryRepository.deleteById(id);
        else
            throw  ExceptionUtil.exception(EntityType.COUNTRY,ExceptionType.ENTITY_NOT_FOUND,String.valueOf(id));
    }


}
