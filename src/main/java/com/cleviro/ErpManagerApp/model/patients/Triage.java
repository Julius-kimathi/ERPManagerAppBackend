package com.cleviro.ErpManagerApp.model.patients;

import com.cleviro.ErpManagerApp.model.patients.enums.DepartmentStatuses;
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
@Table(name = "triage")
public class Triage {
    @Id
    @Column(name = "visit_id")
    private Long id;
    @Enumerated(EnumType.STRING)
    private DepartmentStatuses status;

    @OneToOne
    @MapsId
    @JoinColumn(name = "visit_id")
    private Visit visit;
}
