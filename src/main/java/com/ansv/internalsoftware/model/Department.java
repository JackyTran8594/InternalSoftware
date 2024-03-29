package com.ansv.internalsoftware.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "`department`")
public class Department extends Auditable<String> implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", columnDefinition="nvarchar(255)")
    private String name;

    @Column(name = "code")
    private String code;

    @Column(name = "description", columnDefinition="nvarchar(500)")
    private String description;

    @Column(name = "note", columnDefinition="nvarchar(500)")
    private String note;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name="department_user", joinColumns = {@JoinColumn(name = "deparment_id", referencedColumnName = "id")}, inverseJoinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")})
    private Set<UserEntity> users = new HashSet<>();




}
