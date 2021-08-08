package com.cleviro.ErpManagerApp.model.masters;

import com.cleviro.ErpManagerApp.model.masters.enums.PlanCategories;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@Entity
@Table(name = "plan_categories")
public class PlanCategory {
    @Id
    @Column(name = "plan_category_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Short id;
    @Enumerated(EnumType.STRING)
    private PlanCategories name; //OP,IP

    @OneToMany(mappedBy = "planCategory", cascade = CascadeType.ALL)
    private Set<Plan> plans;
}
