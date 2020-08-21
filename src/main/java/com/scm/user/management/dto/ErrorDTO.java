package com.scm.user.management.dto;

import com.scm.user.management.enums.ErrorType;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public final class ErrorDTO implements Serializable {

    private static final long serialVersionUID = 29893478L;

    private String errorCode;
    private ErrorType errorSeverity;
    private String errorMessage;
}

