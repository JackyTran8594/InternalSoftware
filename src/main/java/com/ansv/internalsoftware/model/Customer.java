package com.ansv.internalsoftware.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    @Column(name = "name", length = 500, columnDefinition = "nvarchar")
    private String name;

    @Column(name = "address", columnDefinition = "nvarchar", length = 500)
    private String address;

    @Column(name = "tax_code", length = 50)
    private String taxCode;

    @Column(name = "fax", length = 20)
    private String fax;

    @Column(name = "description", columnDefinition = "nvarchar", length = 500)
    private String description;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id")
    private Contract contract;
}
