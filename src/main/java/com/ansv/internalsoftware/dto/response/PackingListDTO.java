package com.ansv.internalsoftware.dto.response;

import com.ansv.internalsoftware.config.formatdate.LocalDateTimeDeserializer;
import com.ansv.internalsoftware.config.formatdate.LocalDateTimeSerializer;
import com.ansv.internalsoftware.model.Auditable;
import com.ansv.internalsoftware.model.DeliveryPackage;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PackingListDTO extends Auditable<String> {
    private Long id;

    private String plCode;

    private String dpCode;

    private String description;

    private String address;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime pdDate;

    private String note;

    private Long dpId;

}
