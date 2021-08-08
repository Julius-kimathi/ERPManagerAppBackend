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
@Table(name = "salutations")
public class Salutation {
    @Id
    @Column(name = "salutation_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Short id;
    private String name; //MR,MRS,MISS ETC
}
