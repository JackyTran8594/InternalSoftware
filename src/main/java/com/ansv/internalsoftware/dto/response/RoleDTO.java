package com.ansv.internalsoftware.dto.response;


import com.ansv.internalsoftware.dto.BaseDTO;
import com.ansv.internalsoftware.model.Department;
import com.ansv.internalsoftware.model.Function;
import com.ansv.internalsoftware.model.Role;
import com.ansv.internalsoftware.model.UserEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RoleDTO extends BaseDTO<String> implements Serializable {

    private Long id;

    private String name;

    private String code;

    private String description;

}
