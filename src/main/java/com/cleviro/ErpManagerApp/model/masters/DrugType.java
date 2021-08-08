package com.cleviro.ErpManagerApp.model.masters;

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
@Table(name = "drug_types")
public class DrugType {
    @Id
    @Column(name = "drug_type_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Short id;
    private String name; // Acute,Chronic,etc
}
