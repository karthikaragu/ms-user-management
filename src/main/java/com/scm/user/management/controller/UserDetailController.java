package com.scm.user.management.controller;

import com.scm.user.management.dto.ErrorDTO;
import com.scm.user.management.dto.UserDetailDTO;
import com.scm.user.management.dto.UserDetailResponseDTO;
import com.scm.user.management.enums.ErrorType;
import com.scm.user.management.exception.UserRegistrationException;
import com.scm.user.management.service.UserDetailService;
import lombok.AllArgsConstructor;
import org.apache.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
public class UserDetailController {

    private UserDetailService userDetailService;

    @PostMapping("/registeruser")
    public ResponseEntity<UserDetailResponseDTO> registerNewUser(@RequestBody @Valid UserDetailDTO userDTO,
                                                                 BindingResult result){
        if(result.hasFieldErrors()){
            return ResponseEntity.badRequest().body(fetchUserDetailResponseDTO(result));
        }
        return Optional.ofNullable(userDetailService.registerUser(userDTO))
                .map(user -> ResponseEntity.ok().body(user))
                .orElseThrow(() -> new UserRegistrationException(HttpStatus.SC_INTERNAL_SERVER_ERROR,"User Registration Failed"));

    }

    @GetMapping("/checkuserexists/{userid}")
    public boolean checkUserExists(@PathVariable("userid") Integer userId){
        return userDetailService.validateUser(userId);
    }

    private UserDetailResponseDTO fetchUserDetailResponseDTO(BindingResult result){
        List<ErrorDTO> errorList = new ArrayList<>();
        result.getFieldErrors().forEach(error ->
                errorList.add(ErrorDTO.builder()
                        .errorSeverity(ErrorType.FIELD_VALIDATION)
                        .errorMessage(error.getDefaultMessage())
                        .errorCode("UM-FV01").build()));
        return UserDetailResponseDTO.builder()
                .errors(errorList)
                .build();
    }
}
