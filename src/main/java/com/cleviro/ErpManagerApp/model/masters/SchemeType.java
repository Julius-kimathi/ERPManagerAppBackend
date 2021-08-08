package com.cleviro.ErpManagerApp.model.masters;

import com.cleviro.ErpManagerApp.model.masters.enums.SchemeTypes;
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
@Table(name = "scheme_types")
public class SchemeType {
    @Id
    @Column(name = "scheme_type_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Short id;
    @Enumerated(EnumType.STRING)
    private SchemeTypes name; //CASH,FFS,CAPITATION

    @OneToMany(mappedBy = "schemeType", cascade = CascadeType.ALL)
    private Set<Scheme> schemes;
}
