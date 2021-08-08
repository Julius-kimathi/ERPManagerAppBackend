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
@Table(name = "payers")
public class Payer {
    @Id
    @Column(name = "payer_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String payerCode;
    private String name; //eg BRITAM,AAR,CIC,SANLAM,JUBILEE

    @OneToMany(mappedBy = "payer", cascade = CascadeType.ALL)
    private Set<Scheme> schemes;
    @OneToMany(mappedBy = "payer", cascade = CascadeType.ALL)
    private Set<PayerAccount> payerAccounts;


}
