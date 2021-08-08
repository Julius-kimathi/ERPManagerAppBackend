package com.cleviro.ErpManagerApp.model.masters;

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
@Table(name = "consultation_types")
public class ConsultationType {
    @Id
    @Column(name = "consultation_type_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Short id;
    private String name; //REVIEW,CONSULTATION,ETC

    @OneToMany(mappedBy = "consultationType", cascade = CascadeType.ALL)
    private Set<ConsultationRate> consultationRates;
}
