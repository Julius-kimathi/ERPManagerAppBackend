package com.cleviro.ErpManagerApp.service.masters;

import com.cleviro.ErpManagerApp.dto.mapper.masters.CopayCategoryMapper;
import com.cleviro.ErpManagerApp.dto.model.masters.CopayCategoryDto;
import com.cleviro.ErpManagerApp.exception.EntityType;
import com.cleviro.ErpManagerApp.exception.ExceptionType;
import com.cleviro.ErpManagerApp.model.masters.CopayCategory;
import com.cleviro.ErpManagerApp.repository.masters.CopayCategoryRepository;
import com.cleviro.ErpManagerApp.util.ExceptionUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class CopayCategoryServiceImpl implements CopayCategoryService{
    @Autowired
    private CopayCategoryRepository copayCategoryRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CopayCategoryDto findCopayCategoryById(Short id) {
        Optional<CopayCategory> consultationType = copayCategoryRepository.findById(id);
        if (consultationType.isPresent())
            return CopayCategoryMapper.toCopayCategoryDto(consultationType.get());
        else
            throw ExceptionUtil.exception(EntityType.COPAYCATEGORY, ExceptionType.ENTITY_NOT_FOUND,id.toString());
    }

    @Override
    public Collection<CopayCategoryDto> findAllCopayCategories() {
        return StreamSupport.stream(copayCategoryRepository.findAll().spliterator(),false)
                .map(CopayCategoryMapper::toCopayCategoryDto)
                .collect(Collectors.toSet());
    }

    @Override
    public CopayCategoryDto addCopayCategory(CopayCategoryDto copayCategoryDto) {
        Optional<CopayCategory> copayCategory = copayCategoryRepository.findByName(copayCategoryDto.getName());
        if (!copayCategory.isPresent()){
            CopayCategory copayCategoryModel = new CopayCategory()
                    .setName(copayCategoryDto.getName());
            return CopayCategoryMapper.toCopayCategoryDto(copayCategoryRepository.save(copayCategoryModel));

        }
        throw ExceptionUtil.exception(EntityType.COPAYCATEGORY, ExceptionType.DUPLICATE_ENTITY,copayCategoryDto.getName().toString());
    }

    @Override
    public CopayCategoryDto updateCopayCategory(CopayCategoryDto copayCategoryDto) {
        Optional<CopayCategory> copayCategory = copayCategoryRepository.findById(copayCategoryDto.getId());
        if (copayCategory.isPresent()){
            CopayCategory copayCategoryModel = copayCategory.get()
                    .setName(copayCategoryDto.getName());
            return CopayCategoryMapper.toCopayCategoryDto(copayCategoryRepository.save(copayCategoryModel));

        }
        throw ExceptionUtil.exception(EntityType.COPAYCATEGORY, ExceptionType.ENTITY_NOT_FOUND,copayCategoryDto.getName().toString());
    }

    @Override
    public void removeCopayCategory(Short id) {
        Optional<CopayCategory> copayCategory = copayCategoryRepository.findById(id);
        if (copayCategory.isPresent())
            copayCategoryRepository.deleteById(id);
        else
            throw ExceptionUtil.exception(EntityType.COPAYCATEGORY, ExceptionType.ENTITY_NOT_FOUND,id.toString());
    }
}
