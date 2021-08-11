package com.cleviro.ErpManagerApp.service.masters;

import com.cleviro.ErpManagerApp.dto.model.masters.PlanDto;

import java.util.Collection;

public interface PlanService {
    /**
     * Get a plan by id
     * @param id
     */
    PlanDto findPlanById(int id);

    /**
     * Find all the plans
     * @param
     */
    Collection<PlanDto> findAllPlans();

    /**
     *
     * Create a new plan
     * @param planDto
     */
    PlanDto addPlan(PlanDto planDto);

    /**
     * update plan details
     * @param planDto
     */
    PlanDto updatePlan(PlanDto planDto);

    /**
     * delete a plan from db
     * @param id
     */
    void removePlan(int id);
}
