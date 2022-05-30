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
@Table(name = "`bank_guarantee`")
public class BankGuarantee extends Auditable<String> implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "PO_code", length = 100)
    private String poCode;

    @Column(name = "contact_code", length = 100)
    private String contactCode;

    @Column(name = "value", precision = 18, scale = 2)
    private BigDecimal value;


    @Column(name = "note", columnDefinition="nvarchar" ,length = 500)
    private String note;

    @ManyToOne
    @JoinColumn(name="id", nullable=true)
    private Contract contract;

}
