package com.scm.user.management.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "userrole", schema="autosparescm")
@Data
public class UserRole implements Serializable {
    private static final long serialVersionUID = 343143124L;

    @Id
    @Column(name = "rolecode", insertable = false, nullable = false)
    private String roleCode;

    @Column(name = "roledescription", nullable = false)
    private String roleDescription;

    
}