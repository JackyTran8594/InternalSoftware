package com.ansv.internalsoftware.dto.response;

import com.ansv.internalsoftware.model.Auditable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class FunctionDTO extends Auditable<String> {
    private Long id;

    private String menuName;

    private String menuCode;

    private String actionCode;

    private String description;

    private String parentCode;

}
