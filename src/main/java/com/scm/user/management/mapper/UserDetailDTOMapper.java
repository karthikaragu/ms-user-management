package com.scm.user.management.mapper;

import com.scm.user.management.dto.UserDetailDTO;
import com.scm.user.management.entity.UserDetail;
import org.apache.commons.collections4.CollectionUtils;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserDetailDTOMapper {

    @Mapping(target="userRole.roleCode", source = "roleCode")
    UserDetail convertUserDTOToUserEntity(UserDetailDTO userDTO);

    @AfterMapping
    default void userDetailInAddressMapping(@MappingTarget UserDetail detail){
        if(CollectionUtils.isNotEmpty(detail.getAddresses())){
            detail.getAddresses().forEach(address -> address.setUserDetail(detail));
        }
    }

}
