package com.ansv.internalsoftware.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "department")
public class Department extends Auditable<String> implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "department_id")
    private Long departmentId;

    @Column(name = "name")
    private String name;

    @Column(name = "code")
    private String code;

    @Column(name = "description")
    private String description;

    @Column(name = "note")
    private String note;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name="department_user", joinColumns = {@JoinColumn(name = "department_id", referencedColumnName = "deparment_id")}, inverseJoinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "user_id")})
    private Set<User> users = new HashSet<>();


}
