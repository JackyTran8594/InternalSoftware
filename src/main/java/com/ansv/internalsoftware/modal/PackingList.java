package com.ansv.internalsoftware.modal;

import com.ansv.internalsoftware.config.formatdate.LocalDateTimeDeserializer;
import com.ansv.internalsoftware.config.formatdate.LocalDateTimeSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "`packing_list`")
public class PackingList extends Auditable<String> implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "PL_code", length = 100)
    private String plCode;

    @Column(name = "DP_code", length = 50)
    private String dpCode;

    @Column(name = "description", length = 500, columnDefinition = "nvarchar")
    private String description;


    @Column(name = "address", columnDefinition = "nvarchar", length = 500)
    private String address;

    @Column(name = "PL_date")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime pdDate;

    @Column(name = "note", columnDefinition="nvarchar" ,length = 500)
    private String note;

    @ManyToOne
    @JoinColumn(name="id", nullable=true)
    private DeliveryPackage deliveryPackage;

}
