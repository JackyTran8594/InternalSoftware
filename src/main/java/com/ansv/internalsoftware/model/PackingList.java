package com.ansv.internalsoftware.model;

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
import java.util.Set;

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

    @Column(name = "description", columnDefinition="nvarchar(255)")
    private String description;


    @Column(name = "address", columnDefinition="nvarchar(255)")
    private String address;

    @Column(name = "PL_date")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime plDate;

    @Column(name = "note", columnDefinition="nvarchar(500)")
    private String note;

    @ManyToOne
    @JoinColumn(name="DP_id", nullable=true, referencedColumnName = "id")
    private DeliveryPackage deliveryPackage;

    @OneToMany(mappedBy = "packingList")
    private Set<ProductService> productServices;

}
