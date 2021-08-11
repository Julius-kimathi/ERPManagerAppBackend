package com.cleviro.ErpManagerApp.service.masters;

import com.cleviro.ErpManagerApp.dto.mapper.masters.PlanMapper;
import com.cleviro.ErpManagerApp.dto.model.masters.PlanDto;
import com.cleviro.ErpManagerApp.exception.EntityType;
import com.cleviro.ErpManagerApp.exception.ExceptionType;
import com.cleviro.ErpManagerApp.model.masters.*;
import com.cleviro.ErpManagerApp.repository.masters.PlanRepository;
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
public class PlanServiceImpl implements PlanService{
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private PlanRepository planRepository;

    @Override
    public PlanDto findPlanById(int id) {
        Optional<Plan> plan = planRepository.findById(id);
        if (plan.isPresent())
            return PlanMapper.toPlanDto(plan.get());
        else throw ExceptionUtil.exception(EntityType.PLAN, ExceptionType.ENTITY_NOT_FOUND,String.valueOf(id));
    }

    @Override
    public Collection<PlanDto> findAllPlans() {
        return StreamSupport.stream(planRepository.findAll().spliterator(),false)
                .map(PlanMapper::toPlanDto)
                .collect(Collectors.toSet());
    }

    @Override
    public PlanDto addPlan(PlanDto planDto) {
        Optional<Plan> plan = planRepository.findByNameAndPayerAccount(planDto.getName(),planDto.getPayerAccount());
        if (!plan.isPresent()){
            String code = RandomStringUtil.getAlphaNumericString(6, EntityType.PLAN);
            Plan planModel = new Plan()
                    .setPlanCode(code)
                    .setName(planDto.getName())
                    .setHasDepartmentalLimits(planDto.getHasDepartmentalLimits())
                    .setHasDepartmentalCopay(planDto.getHasDepartmentalCopay())
                    .setCopay(planDto.getCopay())
                    .setOverallLimit(planDto.getOverallLimit())
                    .setVisitLimit(planDto.getVisitLimit())
                    .setValidityStartDate(planDto.getValidityStartDate())
                    .setValidityEndDate(planDto.getValidityEndDate())
                    .setStatus(planDto.getStatus())
                    .setHasRegistrationFees(planDto.getHasRegistrationFees())
                    .setSubVisitPeriodInDays(planDto.getSubVisitPeriodInDays())
                    .setSkipCopayForSubVisits(planDto.getSkipCopayForSubVisits())
                    .setPayerAccount(modelMapper.map(planDto.getPayerAccount(), PayerAccount.class))
                    .setPlanCategory(modelMapper.map(planDto.getPlanCategory(), PlanCategory.class))
                    .setLimitCategory(modelMapper.map(planDto.getLimitCategory(), LimitCategory.class))
                    .setCopayCategory(modelMapper.map(planDto.getCopayCategory(), CopayCategory.class));
            return PlanMapper.toPlanDto(planRepository.save(planModel));
        }
        throw ExceptionUtil.exception(EntityType.PLAN, ExceptionType.DUPLICATE_ENTITY,planDto.toString());
    }

    @Override
    public PlanDto updatePlan(PlanDto planDto) {
        Optional<Plan> plan = planRepository.findById(planDto.getId());
        if (plan.isPresent()){
            Plan planModel = plan.get()
                    .setName(planDto.getName())
                    .setHasDepartmentalLimits(planDto.getHasDepartmentalLimits())
                    .setHasDepartmentalCopay(planDto.getHasDepartmentalCopay())
                    .setCopay(planDto.getCopay())
                    .setOverallLimit(planDto.getOverallLimit())
                    .setVisitLimit(planDto.getVisitLimit())
                    .setValidityStartDate(planDto.getValidityStartDate())
                    .setValidityEndDate(planDto.getValidityEndDate())
                    .setStatus(planDto.getStatus())
                    .setHasRegistrationFees(planDto.getHasRegistrationFees())
                    .setSubVisitPeriodInDays(planDto.getSubVisitPeriodInDays())
                    .setSkipCopayForSubVisits(planDto.getSkipCopayForSubVisits())
                    .setPayerAccount(modelMapper.map(planDto.getPayerAccount(), PayerAccount.class))
                    .setPlanCategory(modelMapper.map(planDto.getPlanCategory(), PlanCategory.class))
                    .setLimitCategory(modelMapper.map(planDto.getLimitCategory(), LimitCategory.class))
                    .setCopayCategory(modelMapper.map(planDto.getCopayCategory(), CopayCategory.class));
            return PlanMapper.toPlanDto(planRepository.save(planModel));
        }
        throw ExceptionUtil.exception(EntityType.PLAN, ExceptionType.ENTITY_NOT_FOUND,planDto.toString());
    }

    @Override
    public void removePlan(int id) {
        Optional<Plan> plan = planRepository.findById(id);
        if (plan.isPresent())
            planRepository.deleteById(id);
        else   throw ExceptionUtil.exception(EntityType.PLAN, ExceptionType.ENTITY_NOT_FOUND,String.valueOf(id));

    }
}
