package com.ansv.internalsoftware.dto.response;


import com.ansv.internalsoftware.dto.BaseDTO;
import com.ansv.internalsoftware.model.*;
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
public class ProductServiceDTO extends BaseDTO<String> implements Serializable  {
    private Long id;

    private String contractCode;

    private String poCode;

    private String name;

    private Long amount;

    private BigDecimal price;

    private String madeIn;

    private BigDecimal vat;

    private String productCode;

    private String note;

}
