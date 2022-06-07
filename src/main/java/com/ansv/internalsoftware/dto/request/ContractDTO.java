package com.ansv.internalsoftware.dto.request;


import com.ansv.internalsoftware.config.formatdate.LocalDateTimeDeserializer;
import com.ansv.internalsoftware.config.formatdate.LocalDateTimeSerializer;
import com.ansv.internalsoftware.dto.BaseDTO;
import com.ansv.internalsoftware.model.BankGuarantee;
import com.ansv.internalsoftware.model.Customer;
import com.ansv.internalsoftware.model.PeriodOrder;
import com.ansv.internalsoftware.model.ProductService;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ContractDTO extends BaseDTO {
    private Long id;

    private String contractCode;

    private String name;

    private String description;

    private BigDecimal value;

    private String paymentContent;

    private String bankGuarantee;

    private Long customerId;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime contractDate;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime timeLine;

    private String guarantee;

    private String note;



}
