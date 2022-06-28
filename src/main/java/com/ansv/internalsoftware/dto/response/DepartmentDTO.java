package com.ansv.internalsoftware.dto.response;


import com.ansv.internalsoftware.dto.BaseDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DepartmentDTO extends BaseDTO<String> implements Serializable {
    private Long id;

    private String name;

    private String code;

    private String description;

    private String note;

}
