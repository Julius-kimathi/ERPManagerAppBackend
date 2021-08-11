package com.cleviro.ErpManagerApp.dto.model.masters;

import com.cleviro.ErpManagerApp.model.masters.enums.CopayCategories;
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
public class CopayCategoryDto {
    private Short id;
    @Enumerated(EnumType.STRING)
    private CopayCategories name; //fixed,percentage
    private Set<PlanDto> plans;
}
