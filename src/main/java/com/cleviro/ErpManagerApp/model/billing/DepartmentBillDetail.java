package com.cleviro.ErpManagerApp.model.billing;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@Entity
@Table(name = "departmentBillDetail")
public class DepartmentBillDetail {
    @Id
    @Column(name = "department_bill_detail_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer quantity;
    private BigDecimal unitPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_bill_id")
    private DepartmentBill departmentBill;
}
