package com.scm.user.management.enums;

public enum UserRoleEnum {
    ADMIN("ADMIN"),
    CUSTOMER("CUST"),
    DEALER("DLR");

    public final String code;
    private UserRoleEnum(String code){
        this.code = code;
    }
}
