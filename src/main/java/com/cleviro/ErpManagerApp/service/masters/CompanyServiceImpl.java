package com.cleviro.ErpManagerApp.service.masters;

import com.cleviro.ErpManagerApp.dto.mapper.masters.CompanyMapper;
import com.cleviro.ErpManagerApp.dto.model.masters.CompanyDto;
import com.cleviro.ErpManagerApp.exception.ERPException;
import com.cleviro.ErpManagerApp.exception.EntityType;
import com.cleviro.ErpManagerApp.exception.ExceptionType;
import com.cleviro.ErpManagerApp.model.masters.Company;
import com.cleviro.ErpManagerApp.model.masters.Country;
import com.cleviro.ErpManagerApp.repository.masters.CompanyRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class CompanyServiceImpl implements CompanyService{
    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CompanyDto findCompanyById(int id)
    {
        Optional<Company> company = companyRepository.findById(id);
        if (company.isPresent())
        {
            return CompanyMapper.toCompanyDto(company.get());
        }
        throw exception(EntityType.COMPANY,ExceptionType.ENTITY_NOT_FOUND, String.valueOf(id));
    }

    @Override
    public Collection<CompanyDto> findAllCompanies()
    {
       return StreamSupport.stream(companyRepository.findAll().spliterator(), false)
               .map(CompanyMapper::toCompanyDto)
               .collect(Collectors.toSet());
    }

    @Override
    public CompanyDto addCompany(CompanyDto companyDto) {
        Optional<Company> company = Optional.ofNullable(companyRepository.findByEmail(companyDto.getEmail()));
        Optional<Company> company1 = Optional.ofNullable((companyRepository.findByName(companyDto.getName())));
        if (!(company.isPresent() || company1.isPresent()))
        {
            Company companyModel = new Company()
                    .setName(companyDto.getName())
                    .setPostalAddress(companyDto.getPostalAddress())
                    .setState(companyDto.getState())
                    .setCity(companyDto.getCity())
                    .setPhone(companyDto.getPhone())
                    .setPhone1(companyDto.getPhone1())
                    .setPhone2(companyDto.getPhone2())
                    .setEmail(companyDto.getEmail())
                    .setEmail1(companyDto.getEmail1())
                    .setEmail2(companyDto.getEmail2())
                    .setAbbreviation(companyDto.getAbbreviation())
                    .setWebsite(companyDto.getWebsite())
                    .setOrderPrefix(companyDto.getOrderPrefix())
                    .setCountry(modelMapper.map(companyDto.getCountry(), Country.class));
            return CompanyMapper.toCompanyDto(companyRepository.save(companyModel));
                  //  .setLocations(companyDto.getLocations().stream().map(locationDto -> modelMapper.map(locationDto, Location.class)).collect(Collectors.toSet()))

        }
            throw exception(EntityType.COMPANY, ExceptionType.DUPLICATE_ENTITY, companyDto.getEmail(), companyDto.getName());

    }

    @Override
    public CompanyDto updateCompany(CompanyDto companyDto) {
        Optional<Company> company = companyRepository.findById(companyDto.getId());
        if (company.isPresent()){
            Company companyModel = company.get()
                    .setName(companyDto.getName())
                    .setPostalAddress(companyDto.getPostalAddress())
                    .setState(companyDto.getState())
                    .setCity(companyDto.getCity())
                    .setPhone(companyDto.getPhone())
                    .setPhone1(companyDto.getPhone1())
                    .setPhone2(companyDto.getPhone2())
                    .setEmail(companyDto.getEmail())
                    .setEmail1(companyDto.getEmail1())
                    .setEmail2(companyDto.getEmail2())
                    .setAbbreviation(companyDto.getAbbreviation())
                    .setWebsite(companyDto.getWebsite())
                    .setOrderPrefix(companyDto.getOrderPrefix())
                    .setCountry(modelMapper.map(companyDto.getCountry(), Country.class));
            return CompanyMapper.toCompanyDto(companyRepository.save(companyModel));
        }
        throw exception(EntityType.COMPANY, ExceptionType.ENTITY_NOT_FOUND,String.valueOf(companyDto.getId()));
    }

    @Override
    public void removeCompany(int id) {
        Optional<Company> company = companyRepository.findById(id);
        if (company.isPresent()){
            companyRepository.deleteById(id);
        }
        throw exception(EntityType.COMPANY, ExceptionType.ENTITY_NOT_FOUND,String.valueOf(id));
    }


    /**
     * Throw new RuntimeException
     * @param entityType,ExceptionType,args
     *
     */
    private RuntimeException exception(EntityType entityType, ExceptionType exceptionType,String... args)
    {
        return ERPException.throwException(entityType, exceptionType,args);
    }

    /**
     * Throw new RuntimeException
     * @param entityType,ExceptionType,id,args
     *
     */
    private RuntimeException exceptionWithId(EntityType entityType, ExceptionType exceptionType, Integer id, String... args)
    {
        return ERPException.throwExceptionWithId(entityType, exceptionType, id,args);
    }

}
