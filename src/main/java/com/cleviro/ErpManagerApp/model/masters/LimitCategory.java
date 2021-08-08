package com.cleviro.ErpManagerApp.model.masters;

import com.cleviro.ErpManagerApp.model.masters.enums.LimitCategories;
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
@Table(name = "limit_categories")
public class LimitCategory {
    @Id
    @Column(name = "limit_category_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Short id;
    @Enumerated(EnumType.STRING)
    private LimitCategories name; // Visit,overall,overall-visit,etc

    @OneToMany(mappedBy = "limitCategory", cascade = CascadeType.ALL)
    private Set<Plan> plans;
}
