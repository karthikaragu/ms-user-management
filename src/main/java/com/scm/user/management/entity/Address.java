package com.scm.user.management.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.StringJoiner;

@Data
@Entity
@Table(name = "address",schema="autosparescm")
public class Address implements Serializable {
    private static final long serialVersionUID = 367648439L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "addressid", insertable = false, nullable = false)
    private Integer addressId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="userid",nullable = false)
    @NotNull(message = "Mandatory Field - User")
    @JsonIgnore
    private UserDetail userDetail;

    @Column(name = "addresslineone")
    private String addressLineOne;

    @Column(name = "addresslinetwo")
    private String addressLineTwo;

    @Column(name = "state", nullable = false)
    private String state;

    @Column(name = "country", nullable = false)
    private String country;

    @Column(name = "pincode", nullable = false)
    private Integer pinCode;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @Override
    public String toString() {
        return new StringJoiner(", ", Address.class.getSimpleName() + "[", "]")
                .add("addressLineOne='" + addressLineOne + "'")
                .add("addressLineTwo='" + addressLineTwo + "'")
                .add("state='" + state + "'")
                .add("country='" + country + "'")
                .add("pinCode=" + pinCode)
                .add("email='" + email + "'")
                .add("phone='" + phone + "'")
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof Address)) return false;

        Address address = (Address) o;

        return new EqualsBuilder()
                .append(getAddressId(), address.getAddressId())
                .append(getAddressLineOne(), address.getAddressLineOne())
                .append(getAddressLineTwo(), address.getAddressLineTwo())
                .append(getState(), address.getState())
                .append(getCountry(), address.getCountry())
                .append(getPinCode(), address.getPinCode())
                .append(getEmail(), address.getEmail())
                .append(getPhone(), address.getPhone())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(getAddressId())
                .append(getAddressLineOne())
                .append(getAddressLineTwo())
                .append(getState())
                .append(getCountry())
                .append(getPinCode())
                .append(getEmail())
                .append(getPhone())
                .toHashCode();
    }
}