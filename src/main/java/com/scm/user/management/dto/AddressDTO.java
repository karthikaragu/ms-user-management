package com.scm.user.management.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.*;
import java.io.Serializable;

@Data
@Builder
public class AddressDTO implements Serializable {
    private static final long serialVersionUID = 45618819L;

    @NotEmpty(message = "Enter Address Line")
    private String addressLineOne;
    private String addressLineTwo;

    @NotEmpty(message = "State is Mandatory")
    private String state;

    @NotEmpty(message = "Country is Mandatory")
    private String country;

    @NotNull(message = "Pincode is Required")
    @Digits(integer = 6,fraction = 0,message="Pincode must be 6 digits")
    private Integer pinCode;

    @Email(message = "Invalid Email !!")
    private String email;

    @Pattern(regexp = "^\\d{10}$", message = "Phone must be 10 digits")
    private String phone;
}
