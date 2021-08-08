package com.cleviro.ErpManagerApp.model.masters;

import com.cleviro.ErpManagerApp.model.masters.enums.PayerCategories;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@Entity
@Table(name = "payer_categories")
public class PayerCategory {
    @Id
    @Column(name = "payer_category_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Short id;
    @Enumerated(EnumType.STRING)
    private PayerCategories name; //eg CASH,DIRECT COMPANIES,INSURANCE COMPANIES
}
