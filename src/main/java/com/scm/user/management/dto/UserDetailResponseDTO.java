package com.scm.user.management.dto;

import com.scm.user.management.entity.UserDetail;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
public class UserDetailResponseDTO implements Serializable {

    private static final long serialVersionUID = 46354762L;

    private UserDetail userDetail;
    private List<ErrorDTO> errors;
}
