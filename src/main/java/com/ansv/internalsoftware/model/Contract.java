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
@Table(name="`contract`")
public class Contract extends Auditable<String> implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "contract_code", length = 100)
    private String contractCode;

    @Column(name = "name", length = 500, columnDefinition = "nvarchar")
    private String name;

    @Column(name = "description", length = 500, columnDefinition = "nvarchar")
    private String description;

    @Column(name = "value", precision = 18, scale = 2)
    private BigDecimal value;

    @Column(name = "payment_content", columnDefinition = "nvarchar", length = 500)
    private String paymentContent;

    @Column(name = "bank_guarantee", length = 50)
    private String bankGuarantee;

    @Column(name = "customer_id")
    private Long customerId;

    @Column(name = "contract_date")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime contractDate;

    @Column(name = "timeline")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime timeLine;

    @Column(name = "guarantee", columnDefinition="nvarchar" ,length = 500)
    private String guarantee;

    @Column(name = "note", columnDefinition="nvarchar" ,length = 500)
    private String note;

    @OneToMany(mappedBy="contract")
    private Set<PeriodOrder> periodOrders;

    // relationshipe with bank_guarantee
    @OneToMany(mappedBy="contract")
    private Set<BankGuarantee> bankGuarantees;

    @OneToMany(mappedBy="contract")
    private Set<ProductService> productService;

    @OneToOne(mappedBy="contract")
    private Customer customer;


}
