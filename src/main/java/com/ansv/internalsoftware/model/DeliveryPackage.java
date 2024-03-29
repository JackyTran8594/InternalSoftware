package com.ansv.internalsoftware.model;

import com.ansv.internalsoftware.config.formatdate.LocalDateTimeDeserializer;
import com.ansv.internalsoftware.config.formatdate.LocalDateTimeSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "`delivery_package`")
public class DeliveryPackage extends Auditable<String> implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "PO_code", length = 100)
    private String poCode;

    @Column(name = "contract_code", length = 100)
    private String contractCode;

    @Column(name = "DP_code", length = 50)
    private String dpCode;

    @Column(name = "description", columnDefinition = "nvarchar(500)")
    private String description;

    @Column(name = "value", precision = 18, scale = 2)
    private BigDecimal value;

    @Column(name = "address", columnDefinition = "nvarchar(500)")
    private String address;

    @Column(name = "province_id")
    private Long provinceId;


    @Column(name = "DP_date")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime dpDate;

    @Column(name = "note", columnDefinition="nvarchar(500)")
    private String note;

    @ManyToOne
    @JoinColumn(name="PO_id", nullable=true, referencedColumnName = "id")
    private PeriodOrder periodOrder;

    @OneToMany(mappedBy="deliveryPackage")
    private Set<PackingList> packingList;


    @OneToMany(mappedBy="deliveryPackage")
    private Set<ProductService> productServices;

}
