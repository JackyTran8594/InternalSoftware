package com.ansv.internalsoftware.dto.response;

import com.ansv.internalsoftware.dto.BaseDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerDTO extends BaseDTO<String> implements Serializable {
    private Long id;
    private String code;
    private String name;
    private String address;
    private String taxCode;

    private String phone;
    private String fax;
    private String description;

}
