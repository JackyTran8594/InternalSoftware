package com.ansv.internalsoftware.dto.request;


import com.ansv.internalsoftware.dto.BaseDTO;
import com.ansv.internalsoftware.model.Department;
import com.ansv.internalsoftware.model.Role;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDTO extends BaseDTO<String> {
    private Long id;

    private String username;

    private String code;

    private String fullName;

    private String email;

    private String phone_number;

    private String position;

    private String note;

    private Long roleId;

}
