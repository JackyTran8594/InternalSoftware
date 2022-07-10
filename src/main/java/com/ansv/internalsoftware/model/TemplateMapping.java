package com.ansv.internalsoftware.model;

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
public class TemplateMapping extends Auditable<String> implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "variable", length = 100)
    private String variable;

    @Column(name = "property", length = 100)
    private String property;

    @Column(name = "table", length = 100)
    private String table;

    @Column(name = "description", columnDefinition = "nvarchar(500)")
    private String description;

    @ManyToOne
    @JoinColumn(name = "template_id", nullable = true, referencedColumnName = "id")
    private Template template;
}
