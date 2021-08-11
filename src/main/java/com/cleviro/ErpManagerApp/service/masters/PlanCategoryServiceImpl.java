package com.cleviro.ErpManagerApp.service.masters;

import com.cleviro.ErpManagerApp.dto.mapper.masters.PlanCategoryMapper;
import com.cleviro.ErpManagerApp.dto.model.masters.PlanCategoryDto;
import com.cleviro.ErpManagerApp.exception.EntityType;
import com.cleviro.ErpManagerApp.exception.ExceptionType;
import com.cleviro.ErpManagerApp.model.masters.PlanCategory;
import com.cleviro.ErpManagerApp.repository.masters.PlanCategoryRepository;
import com.cleviro.ErpManagerApp.util.ExceptionUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class PlanCategoryServiceImpl implements PlanCategoryService{
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private PlanCategoryRepository planCategoryRepository;

    @Override
    public PlanCategoryDto findPlanCategoryById(Short id) {
        Optional<PlanCategory> planCategory = planCategoryRepository.findById(id);
        if (planCategory.isPresent())
            return PlanCategoryMapper.toPlanCategoryDto(planCategory.get());
        else
            throw ExceptionUtil.exception(EntityType.PLANCATEGORY, ExceptionType.ENTITY_NOT_FOUND,id.toString());
    }

    @Override
    public Collection<PlanCategoryDto> findAllPlanCategories() {
        return StreamSupport.stream(planCategoryRepository.findAll().spliterator(),false)
                .map(PlanCategoryMapper::toPlanCategoryDto)
                .collect(Collectors.toSet());
    }

    @Override
    public PlanCategoryDto addPlanCategory(PlanCategoryDto planCategoryDto) {
        Optional<PlanCategory> planCategory = planCategoryRepository.findByName(planCategoryDto.getName());
        if (!planCategory.isPresent()){
            PlanCategory planCategoryModel = new PlanCategory().setName(planCategoryDto.getName());
            return PlanCategoryMapper.toPlanCategoryDto(planCategoryRepository.save(planCategoryModel));
        }
        throw ExceptionUtil.exception(EntityType.PLANCATEGORY, ExceptionType.DUPLICATE_ENTITY,planCategoryDto.getName().toString());
    }

    @Override
    public PlanCategoryDto updatePlanCategory(PlanCategoryDto planCategoryDto) {
        Optional<PlanCategory> planCategory = planCategoryRepository.findById(planCategoryDto.getId());
        if (planCategory.isPresent()){
            PlanCategory planCategoryModel = planCategory.get().setName(planCategoryDto.getName());
            return PlanCategoryMapper.toPlanCategoryDto(planCategoryRepository.save(planCategoryModel));
        }
        throw ExceptionUtil.exception(EntityType.PLANCATEGORY, ExceptionType.DUPLICATE_ENTITY,planCategoryDto.getName().toString());
    }

    @Override
    public void removePlanCategory(Short id) {
        Optional<PlanCategory> planCategory = planCategoryRepository.findById(id);
        if (planCategory.isPresent()) planCategoryRepository.deleteById(id);
        else
            throw ExceptionUtil.exception(EntityType.PLANCATEGORY, ExceptionType.ENTITY_NOT_FOUND,id.toString());
    }
}
