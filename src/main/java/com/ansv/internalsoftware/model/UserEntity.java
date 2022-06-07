package com.ansv.internalsoftware.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "`user_entity`")
public class UserEntity extends Auditable<String> implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "username", length = 100)
    private String username;

    @Column(name = "code", length = 50)
    private String code;

    @Column(name = "full_name", columnDefinition="nvarchar(255)")
    private String fullName;

    @Column(name = "email", length = 500)
    private String email;

    @Column(name = "phone_number", length = 20)
    private String phone_number;

    @Column(name = "password", length = 1000)
    private String password;

    @Column(name = "position", columnDefinition="nvarchar(100)")
    private String position;

    @Column(name = "note", columnDefinition="nvarchar(500)")
    private String note;

    @ManyToMany(mappedBy = "users", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Department> departments = new HashSet<>();


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    private Role role;
}
