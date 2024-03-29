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
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name="`function`")
public class Function extends Auditable<String> implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "menu_name", columnDefinition="nvarchar(500)")
    private String menuName;

    @Column(name = "menu_code")
    private String menuCode;

    @Column(name = "action_code")
    private String actionCode;

    @Column(name = "description", columnDefinition="nvarchar(500)")
    private String description;

    @Column(name = "parent_code")
    private String parentCode;

    @ManyToMany(mappedBy = "functions", fetch = FetchType.LAZY)
    private Set<Role> roles = new HashSet<>();


}
