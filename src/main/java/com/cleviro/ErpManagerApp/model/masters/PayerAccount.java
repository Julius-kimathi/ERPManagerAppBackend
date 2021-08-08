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
@Table(name = "payer_accounts")
public class PayerAccount {
    @Id
    @Column(name = "payer_account_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String payerAccountCode;
    private String name;
    private String email;
    private String phone;
    private String address;
    private Boolean status;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "scheme_id")
    private Scheme scheme; //eg AAR FFS,AAR CAPITATION,BRITAM FFS,BRITAM CAPITATION ETC
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "payer_id")
    private Payer payer;
    @OneToMany(mappedBy = "payerAccount", cascade = CascadeType.ALL)
    private Set<Plan> plans;
}
