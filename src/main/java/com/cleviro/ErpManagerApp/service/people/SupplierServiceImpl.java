package com.cleviro.ErpManagerApp.service.people;

import com.cleviro.ErpManagerApp.dto.mapper.people.SupplierMapper;
import com.cleviro.ErpManagerApp.dto.model.people.SupplierDto;
import com.cleviro.ErpManagerApp.exception.EntityType;
import com.cleviro.ErpManagerApp.exception.ExceptionType;
import com.cleviro.ErpManagerApp.model.masters.Country;
import com.cleviro.ErpManagerApp.model.people.Supplier;
import com.cleviro.ErpManagerApp.model.people.SupplierType;
import com.cleviro.ErpManagerApp.repository.people.SupplierRepository;
import com.cleviro.ErpManagerApp.util.ExceptionUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class SupplierServiceImpl implements SupplierService{

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private SupplierRepository supplierRepository;

    @Override
    public SupplierDto findSupplierById(Long id) {
        Optional<Supplier> supplier = supplierRepository.findById(id);
        if (supplier.isPresent()) return SupplierMapper.toSupplierDto(supplier.get());
        else
            throw ExceptionUtil.exception(EntityType.SUPPLIER, ExceptionType.ENTITY_NOT_FOUND, id.toString());
    }

    @Override
    public SupplierDto findSupplierByEmail(String email) {
        Optional<Supplier> supplier =supplierRepository.findByEmail(email);
        if (supplier.isPresent())
            return SupplierMapper.toSupplierDto(supplier.get());
        else
            throw ExceptionUtil.exception(EntityType.SUPPLIER, ExceptionType.ENTITY_NOT_FOUND,email);
    }

    @Override
    public Collection<SupplierDto> findAllSuppliers() {
        return StreamSupport.stream(supplierRepository.findAll().spliterator(), false)
                .map(SupplierMapper::toSupplierDto)
                .collect(Collectors.toSet());
    }

    @Override
    public SupplierDto addSupplier(SupplierDto supplierDto) {
        Optional<Supplier> supplier = supplierRepository.findByEmail(supplierDto.getEmail());
        if (!supplier.isPresent()){
            Supplier supplierModel = new Supplier()
                    .setName(supplierDto.getName())
                    .setEmail(supplierDto.getEmail())
                    .setPhone(supplierDto.getPhone())
                    .setPhone1(supplierDto.getPhone1())
                    .setKraPin(supplierDto.getKraPin())
                    .setPostalAddress(supplierDto.getPostalAddress())
                    .setState(supplierDto.getState())
                    .setCity(supplierDto.getCity())
                    .setStatus(supplierDto.getStatus())
                    .setRegDate(supplierDto.getRegDate())
                    .setWebsite(supplierDto.getWebsite())
                    .setCountry(modelMapper.map(supplierDto.getCountry(), Country.class))
                    .setSupplierType(modelMapper.map(supplierDto.getSupplierType(), SupplierType.class));
            return SupplierMapper.toSupplierDto(supplierRepository.save(supplierModel));
        }
        throw ExceptionUtil.exception(EntityType.SUPPLIER, ExceptionType.DUPLICATE_ENTITY, supplierDto.getEmail());
    }

    @Override
    public SupplierDto updateSupplier(SupplierDto supplierDto) {
        Optional<Supplier> supplier = supplierRepository.findById(supplierDto.getId());
        if (supplier.isPresent()){
            Supplier supplierModel = supplier.get()
                    .setName(supplierDto.getName())
                    .setEmail(supplierDto.getEmail())
                    .setPhone(supplierDto.getPhone())
                    .setPhone1(supplierDto.getPhone1())
                    .setKraPin(supplierDto.getKraPin())
                    .setPostalAddress(supplierDto.getPostalAddress())
                    .setState(supplierDto.getState())
                    .setCity(supplierDto.getCity())
                    .setStatus(supplierDto.getStatus())
                    .setRegDate(supplierDto.getRegDate())
                    .setWebsite(supplierDto.getWebsite())
                    .setCountry(modelMapper.map(supplierDto.getCountry(), Country.class))
                    .setSupplierType(modelMapper.map(supplierDto.getSupplierType(), SupplierType.class));
            return SupplierMapper.toSupplierDto(supplierRepository.save(supplierModel));
        }
        throw ExceptionUtil.exception(EntityType.SUPPLIER, ExceptionType.ENTITY_NOT_FOUND, supplierDto.getId().toString());
    }

    @Override
    public void removeSupplier(Long id) {
        if (supplierRepository.findById(id).isPresent()) supplierRepository.deleteById(id);
        else
            throw ExceptionUtil.exception(EntityType.SUPPLIER, ExceptionType.ENTITY_NOT_FOUND, id.toString());
    }
}
