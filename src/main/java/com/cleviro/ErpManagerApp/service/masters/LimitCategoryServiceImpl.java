package com.cleviro.ErpManagerApp.service.masters;

import com.cleviro.ErpManagerApp.dto.mapper.masters.LimitCategoryMapper;
import com.cleviro.ErpManagerApp.dto.model.masters.LimitCategoryDto;
import com.cleviro.ErpManagerApp.exception.EntityType;
import com.cleviro.ErpManagerApp.exception.ExceptionType;
import com.cleviro.ErpManagerApp.model.masters.LimitCategory;
import com.cleviro.ErpManagerApp.repository.masters.LimitCategoryRepository;
import com.cleviro.ErpManagerApp.util.ExceptionUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class LimitCategoryServiceImpl implements LimitCategoryService{
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private LimitCategoryRepository limitCategoryRepository;

    @Override
    public LimitCategoryDto findLimitCategoryById(Short id) {
        Optional<LimitCategory> limitCategory = limitCategoryRepository.findById(id);
        if (limitCategory.isPresent())
            return LimitCategoryMapper.toLimitCategoryDto(limitCategory.get());
        else
            throw ExceptionUtil.exception(EntityType.LIMITCATEGORY, ExceptionType.ENTITY_NOT_FOUND,id.toString());

    }

    @Override
    public Collection<LimitCategoryDto> findAllLimitCategories() {
        return StreamSupport.stream(limitCategoryRepository.findAll().spliterator(),false)
                .map(LimitCategoryMapper::toLimitCategoryDto)
                .collect(Collectors.toSet());
    }

    @Override
    public LimitCategoryDto addLimitCategory(LimitCategoryDto limitCategoryDto) {
        Optional<LimitCategory> limitCategory = limitCategoryRepository.findByName(limitCategoryDto.getName());
        if (!limitCategory.isPresent()){
            LimitCategory limitCategoryModel = new LimitCategory().setName(limitCategoryDto.getName());
            return LimitCategoryMapper.toLimitCategoryDto(limitCategoryRepository.save(limitCategoryModel));
        }
        throw ExceptionUtil.exception(EntityType.LIMITCATEGORY, ExceptionType.DUPLICATE_ENTITY,limitCategoryDto.getName().toString());
    }

    @Override
    public LimitCategoryDto updateLimitCategory(LimitCategoryDto limitCategoryDto) {
        Optional<LimitCategory> limitCategory = limitCategoryRepository.findById(limitCategoryDto.getId());
        if (limitCategory.isPresent()){
            LimitCategory limitCategoryModel = limitCategory.get().setName(limitCategoryDto.getName());
            return LimitCategoryMapper.toLimitCategoryDto(limitCategoryRepository.save(limitCategoryModel));
        }
        throw ExceptionUtil.exception(EntityType.LIMITCATEGORY, ExceptionType.ENTITY_NOT_FOUND,limitCategoryDto.getName().toString());
    }

    @Override
    public void removeLimitCategory(Short id) {
        Optional<LimitCategory> limitCategory = limitCategoryRepository.findById(id);
        if (limitCategory.isPresent())
            limitCategoryRepository.deleteById(id);
        else
            throw ExceptionUtil.exception(EntityType.LIMITCATEGORY, ExceptionType.ENTITY_NOT_FOUND,id.toString());
    }
}
