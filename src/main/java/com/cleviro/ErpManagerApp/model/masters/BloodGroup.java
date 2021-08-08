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
@Table(name = "blood_groups")
public class BloodGroup {
    @Id
    @Column(name = "blood_group_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Short id;
    private String name; //A,A+,B-,
}
