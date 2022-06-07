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
public class CustomerDTO extends BaseDTO<String> implements Serializable {
    private Long id;

    private String code;

    private String name;

    private String address;

    private String taxCode;

    private String fax;

    private String description;

}
