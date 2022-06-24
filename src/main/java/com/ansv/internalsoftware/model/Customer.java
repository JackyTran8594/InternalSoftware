package com.ansv.internalsoftware.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "`customer`")
public class Customer extends Auditable<String> implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "code", length = 50)
    private String code;

    @Column(name = "name", columnDefinition = "nvarchar(500)")
    private String name;

    @Column(name = "address", columnDefinition = "nvarchar(500)")
    private String address;

    @Column(name = "tax_code", length = 50)
    private String taxCode;

    @Column(name = "fax", length = 20)
    private String fax;

    @Column(name = "phone", length = 20)
    private String phone;
    @Column(name = "description", columnDefinition = "nvarchar(500)")
    private String description;

    @OneToOne(mappedBy = "customer")
    private Contract contract;
}
