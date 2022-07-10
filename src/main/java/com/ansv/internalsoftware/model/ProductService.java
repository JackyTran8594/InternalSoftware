package com.ansv.internalsoftware.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "`product_service`")
public class ProductService extends Auditable<String> implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "contract_code", length = 100)
    private String contractCode;

    @Column(name = "PO_code", length = 100)
    private String poCode;


    @Column(name = "name", columnDefinition="nvarchar(255)")
    private String name;

    @Column(name = "amount")
    private Long amount;

    @Column(name = "price", precision = 18, scale = 2)
    private BigDecimal price;

    @Column(name = "made_in", columnDefinition="nvarchar(100)")
    private String madeIn;

    @Column(name = "VAT", precision = 18, scale = 2)
    private BigDecimal vat;

    @Column(name = "product_code", length = 50)
    private String productCode;

    @Column(name = "note", columnDefinition="nvarchar(500)")
    private String note;

    @ManyToOne
    @JoinColumn(name="contrac_id", nullable=true, referencedColumnName = "id")
    private Contract contract;

    @ManyToOne
    @JoinColumn(name="PO_id", nullable=true, referencedColumnName = "id")
    private PeriodOrder periodOrder;

    @ManyToOne
    @JoinColumn(name="DP_id", nullable=true, referencedColumnName = "id")
    private DeliveryPackage deliveryPackage;

    @ManyToOne
    @JoinColumn(name="PL_id", nullable=true, referencedColumnName = "id")
    private PackingList packingList;



}
