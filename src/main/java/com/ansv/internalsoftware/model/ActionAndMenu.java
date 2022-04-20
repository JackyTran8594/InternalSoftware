package com.ansv.internalsoftware.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ActionAndMenu extends Auditable<String> implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "menu_name")
    private String menuName;

    @Column(name = "action_code")
    private String actionCode;

    @Column(name = "description")
    private String description;

    @Column(name = "parent_code")
    private String parentCode;

    @OneToMany(cascade = {CascadeType.ALL})
    @Fetch(FetchMode.JOIN)
    @JsonIgnore
    private Collection<User> usersRole = new HashSet<>();
}
