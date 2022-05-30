package com.ansv.internalsoftware.modal;


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


    @Column(name = "name", length = 500, columnDefinition = "nvarchar")
    private String name;

    @Column(name = "amount")
    private Long amount;

    @Column(name = "price", precision = 18, scale = 2)
    private BigDecimal price;

    @Column(name = "made_in", columnDefinition = "nvarchar", length = 50)
    private String madeIn;

    @Column(name = "VAT", precision = 18, scale = 2)
    private BigDecimal vat;

    @Column(name = "product_code", length = 50)
    private String productCode;

    @Column(name = "note", columnDefinition="nvarchar" ,length = 500)
    private String note;

    @ManyToOne
    @JoinColumn(name="id", nullable=true)
    private Contract contract;

    @ManyToOne
    @JoinColumn(name="id", nullable=true)
    private PeriodOrder periodOrder;

    @ManyToOne
    @JoinColumn(name="id", nullable=true)
    private DeliveryPackage deliveryPackage;

    @ManyToOne
    @JoinColumn(name="id", nullable=true)
    private PackingList packingList;

}
