package com.cleviro.ErpManagerApp.model.masters;

import com.cleviro.ErpManagerApp.model.masters.enums.CopayCategories;
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
@Table(name = "copay_categories")
public class CopayCategory {
    @Id
    @Column(name = "copay_category_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Short id;
    @Enumerated(EnumType.STRING)
    private CopayCategories name; //fixed,percentage

    @OneToMany(mappedBy = "copayCategory", cascade = CascadeType.ALL)
    private Set<Plan> plans;
}
