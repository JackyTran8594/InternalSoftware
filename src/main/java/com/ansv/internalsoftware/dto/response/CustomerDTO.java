package com.ansv.internalsoftware.dto.response;

import com.ansv.internalsoftware.dto.BaseDTO;
import com.ansv.internalsoftware.model.Auditable;
import com.ansv.internalsoftware.model.Contract;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CustomerDTO extends BaseDTO<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "code", length = 50)
    private String code;

    @Column(name = "name", columnDefinition = "nvarchar(500)")
    private String name;

    @Column(name = "address", columnDefinition = "nvarchar(500)", length = 500)
    private String address;

    @Column(name = "tax_code", length = 50)
    private String taxCode;

    @Column(name = "fax", length = 20)
    private String fax;

    @Column(name = "description", columnDefinition = "nvarchar(500)")
    private String description;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id")
    private Contract contract;
}
