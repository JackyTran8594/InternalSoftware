package com.ansv.internalsoftware.modal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "`config_value`")
public class ConfigValue extends Auditable<String> implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;


    @Column(name = "code", length = 50)
    private String code;

    @Column(name = "name", columnDefinition="nvarchar", length = 100)
    private String name;

    @Column(name = "note", columnDefinition="nvarchar" ,length = 500)
    private String note;
}
