package com.ansv.internalsoftware.modal;

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

    @Column(name = "menu_name")
    private String menuName;

    @Column(name = "menu_code")
    private String menuCode;

    @Column(name = "action_code")
    private String actionCode;

    @Column(name = "description")
    private String description;

    @Column(name = "parent_code")
    private String parentCode;

    @ManyToMany(mappedBy = "functions", fetch = FetchType.LAZY)
    private Set<Role> roles = new HashSet<>();
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "function_id", referencedColumnName="id")
//    private RoleFunction roleFunction;

}
