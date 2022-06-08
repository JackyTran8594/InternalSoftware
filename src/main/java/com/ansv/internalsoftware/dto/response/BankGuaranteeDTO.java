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
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BankGuaranteeDTO extends BaseDTO<String> {

    private Long id;

    private String poCode;

    private String contactCode;

    private BigDecimal value;

    private String note;

    private Long contract_id;

}
