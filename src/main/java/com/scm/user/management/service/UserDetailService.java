package com.scm.user.management.service;

import com.scm.user.management.dto.ErrorDTO;
import com.scm.user.management.dto.UserDetailDTO;
import com.scm.user.management.dto.UserDetailResponseDTO;
import com.scm.user.management.entity.UserDetail;
import com.scm.user.management.enums.ErrorType;
import com.scm.user.management.enums.UserRoleEnum;
import com.scm.user.management.exception.UserRegistrationException;
import com.scm.user.management.mapper.UserDetailDTOMapper;
import com.scm.user.management.repository.UserDetailRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.EnumUtils;
import org.apache.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@AllArgsConstructor
public class UserDetailService {

    private UserDetailRepository userDetailRepository;
    private UserDetailDTOMapper mapper;
    private PasswordEncoder passwordEncoder;

    public UserDetailResponseDTO registerUser(UserDetailDTO userDTO){
        UserDetailResponseDTO dto = null;
        List<ErrorDTO> errorList = new ArrayList<>();
        try{
            UserDetail userDetail = null;
            validateUserDetails(userDTO, errorList);
            if(CollectionUtils.isEmpty(errorList)){
                userDTO.setRoleCode(UserRoleEnum.valueOf(userDTO.getRoleCode()).code);
                userDTO.setPwd(passwordEncoder.encode(userDTO.getPwd()));
                userDetail = mapper.convertUserDTOToUserEntity(userDTO);
                userDetailRepository.save(userDetail);
            }
            dto = UserDetailResponseDTO.builder().errors(errorList).userDetail(userDetail).build();
        }catch(Exception e){
            log.error("Error occured while Registering User",e);
            throw new UserRegistrationException(HttpStatus.SC_INTERNAL_SERVER_ERROR, e.getMessage());
        }
        return dto;
    }

    public boolean validateUser(Integer userId){
        return userDetailRepository.findById(userId).isPresent();
    }

    private void validateUserDetails(UserDetailDTO userDTO, List<ErrorDTO> errorList){
        if(isDuplicateUserDetail(userDTO)){
            errorList.add(retrieveErrorDTO("User Already Exists !!","UM-ER01"));
        }
        if(!EnumUtils.isValidEnumIgnoreCase(UserRoleEnum.class,userDTO.getRoleCode())){
            errorList.add(retrieveErrorDTO("Invalid User Role","UM-ER02"));
        }
    }

    private boolean isDuplicateUserDetail(UserDetailDTO userDTO){
        return Objects.nonNull(userDetailRepository.findByFirstNameAndLastNameAndDob(userDTO.getFirstName(),
                userDTO.getLastName(),userDTO.getDob()));
    }

    private ErrorDTO retrieveErrorDTO(String message, String errorCode){
        return ErrorDTO.builder()
                .errorSeverity(ErrorType.ERROR)
                .errorMessage(message)
                .errorCode(errorCode).build();
    }
}
