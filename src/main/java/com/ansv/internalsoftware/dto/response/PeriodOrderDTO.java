package com.ansv.internalsoftware.dto.response;

import com.ansv.internalsoftware.config.formatdate.LocalDateTimeDeserializer;
import com.ansv.internalsoftware.config.formatdate.LocalDateTimeSerializer;
import com.ansv.internalsoftware.dto.BaseDTO;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PeriodOrderDTO extends BaseDTO<String> implements Serializable {

    private Long id;

    private String poCode;

    private Integer poNumber;

    private String description;

    private BigDecimal value;

    private String paymentContent;

    private String bankGuarantee;

    private String address;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime poDate;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime timeLine;

    private String note;
}
