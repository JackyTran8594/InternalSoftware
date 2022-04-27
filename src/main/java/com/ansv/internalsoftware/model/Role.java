package com.ansv.internalsoftware.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "role")
public class Role extends Auditable<String> implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "code")
    private String code;

    @Column(name = "description")
    private String description;

    @OneToOne
    @JsonIgnore
    private User user;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "role_function", joinColumns = {@JoinColumn(name="role_id", referencedColumnName="id")},inverseJoinColumns ={@JoinColumn(name="function_id", referencedColumnName="id")})
    @JsonIgnore
    private Set<Function> functions;
}
