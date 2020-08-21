package com.scm.user.management.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class UserDetailDTO implements Serializable {

    private static final long serialVersionUID = 456153265L;

    @NotNull(message = "User Name is Required")
    @Size(min = 5, message = "User Name must have atleast 5 letters" )
    private String userName;

    @NotNull(message = "First Name is Required")
    @Size(min = 1, message = "First Name must have atleast 1 letter" )
    private String firstName;

    @NotNull(message = "Last Name is Required")
    @Size(min = 1, message = "Last Name must have atleast 1 letter" )
    private String lastName;

    @NotNull(message="Date of Birth is Mandatory")
    @Past(message ="Date of Birth must be in the Past")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dob;

    @NotNull(message = "Password is Required")
    @Size(min=5,message = "Password must contain 5 characters minimum")
    private String pwd;

    private String roleCode = "CUSTOMER";

    @NotEmpty(message = "User must have atleast one address")
    private List<@Valid AddressDTO> addresses;
}
