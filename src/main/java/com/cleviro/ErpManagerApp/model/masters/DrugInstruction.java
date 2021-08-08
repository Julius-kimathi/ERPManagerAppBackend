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
@Table(name = "drug_instructions")
public class DrugInstruction {
    @Id
    @Column(name = "drug_instruction_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Short id;
    private String name; //Take,Spray,Instill,Insert,Inhale,Gargle,Dissolve,apply,Administer,etc
}
