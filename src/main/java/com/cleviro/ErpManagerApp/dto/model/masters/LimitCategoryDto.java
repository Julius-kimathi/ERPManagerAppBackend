package com.cleviro.ErpManagerApp.dto.model.masters;

import com.cleviro.ErpManagerApp.model.masters.enums.LimitCategories;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@ToString
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class LimitCategoryDto {
    private Short id;
    @Enumerated(EnumType.STRING)
    private LimitCategories name; // Visit,overall,overall-visit,etc

    private Set<PlanDto> plans;
}
