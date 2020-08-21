package com.scm.user.management.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

@Table(name = "userdetail", schema="autosparescm")
@Entity
@Data
public class UserDetail implements Serializable {
    private static final long serialVersionUID = 35834040L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userid", insertable = false, nullable = false)
    private Integer userId;

    @Column(name = "username", nullable = false)
    private String userName;

    @Column(name = "firstname", nullable = false)
    private String firstName;

    @Column(name = "lastname", nullable = false)
    private String lastName;

    @Column(name = "dob")
    private LocalDate dob;

    @Column(name = "pwd", nullable = false)
    @JsonIgnore
    private String pwd;

    @OneToMany(mappedBy = "userDetail", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<Address> addresses;

    @ManyToOne
    @JoinColumn(name="accounttype", referencedColumnName = "roleCode", nullable = false)
    private UserRole userRole;

}