package com.cleviro.ErpManagerApp.model.people;

import com.cleviro.ErpManagerApp.model.masters.Company;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Accessors(chain = true)
@Table(name = "supplier_types")
public class SupplierType {
    @Id
    @Column(name = "supplier_type_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Short id;
    private String name;
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;

    @OneToMany(mappedBy = "supplierType", cascade = CascadeType.ALL)
    private Set<Supplier> suppliers;
}
