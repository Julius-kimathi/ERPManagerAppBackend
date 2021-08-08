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
@Table(name = "schemes")
public class Scheme {
    @Id
    @Column(name = "scheme_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String schemeCode;
    private String name;
    private Boolean status;
    private Boolean isPreauthRequired;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "scheme_type_id")
    private SchemeType schemeType;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "payer_id")
    private Payer payer;
    @OneToMany(mappedBy = "scheme", cascade = CascadeType.ALL)
    private Set<PayerAccount> payerAccounts;

    @OneToMany(mappedBy = "scheme", cascade = CascadeType.ALL)
    private Set<ConsultationRate> consultationRates;

}
