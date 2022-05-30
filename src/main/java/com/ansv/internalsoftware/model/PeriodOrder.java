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
@Table(name = "`period_order`")
public class PeriodOrder extends Auditable<String> implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "PO_code", length = 100)
    private String poCode;

    @Column(name = "PO_number")
    private Integer poNumber;

    @Column(name = "description", length = 500, columnDefinition = "nvarchar")
    private String description;

    @Column(name = "value", precision = 18, scale = 2)
    private BigDecimal value;

    @Column(name = "payment_content", columnDefinition = "nvarchar", length = 500)
    private String paymentContent;

    @Column(name = "bank_guarantee", length = 50)
    private String bankGuarantee;

    @Column(name = "address", columnDefinition = "nvarchar", length = 500)
    private String address;

    @Column(name = "PO_date")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime poDate;

    @Column(name = "timeline")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime timeLine;

    @Column(name = "note", columnDefinition="nvarchar" ,length = 500)
    private String note;

    @ManyToOne
    @JoinColumn(name="contract_id", nullable=true, referencedColumnName = "id")
    private Contract contract;

    @OneToMany(mappedBy="periodOrder")
    private Set<ProductService> productService;

    @OneToMany(mappedBy="periodOrder")
    private Set<DeliveryPackage> deliveryPackage;
}
