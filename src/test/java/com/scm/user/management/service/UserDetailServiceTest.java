package com.scm.user.management.service;

import com.scm.user.management.dto.AddressDTO;
import com.scm.user.management.dto.ErrorDTO;
import com.scm.user.management.dto.UserDetailDTO;
import com.scm.user.management.dto.UserDetailResponseDTO;
import com.scm.user.management.exception.UserRegistrationException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.stream.Collectors;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserDetailServiceTest {

    @Autowired
    private UserDetailService userDetailService;

    @Test
    public void registerUser_happyFlow(){
        UserDetailDTO requestDTO = fetchUserDetailDTO();
        UserDetailResponseDTO responseDTO = userDetailService.registerUser(requestDTO);
        Assert.assertNotNull(responseDTO);
        Assert.assertEquals("DLR",responseDTO.getUserDetail().getUserRole().getRoleCode());
        Assert.assertEquals(0,responseDTO.getErrors().size());
    }

    @Test
    public void registerUser_exceptionFlow(){
        UserDetailDTO requestDTO = fetchUserDetailDTO();
        requestDTO.setUserName(null);
        requestDTO.setFirstName(null);
        requestDTO.setLastName(null);
        UserRegistrationException ex = Assert.assertThrows(UserRegistrationException.class,
                () -> userDetailService.registerUser(requestDTO));
        Assert.assertTrue(ex.getMessage().contains("ConstraintViolationException"));
    }

    @Test
    public void registerUser_businessErrorFlow(){
        UserDetailDTO requestDTO = fetchUserDetailDTO();
        requestDTO.setRoleCode("DELER");
        requestDTO.setFirstName("Test");
        requestDTO.setLastName("Test");
        requestDTO.setDob(LocalDate.of(1963,06,03));
        UserDetailResponseDTO responseDTO = userDetailService.registerUser(requestDTO);
        Assert.assertNotNull(responseDTO);
        Assert.assertEquals(2,responseDTO.getErrors().size());
        Assert.assertEquals(Arrays.asList("UM-ER01","UM-ER02"),
                responseDTO.getErrors().stream().map(ErrorDTO::getErrorCode).collect(Collectors.toList()));
    }

    @Test
    public void validateUser_positive(){
        Assert.assertTrue(userDetailService.validateUser(1));
    }

    @Test
    public void validateUser_negative(){
        Assert.assertFalse(userDetailService.validateUser(10));
    }

    private UserDetailDTO fetchUserDetailDTO(){
        AddressDTO address = AddressDTO.builder().addressLineOne("F4-1").state("Johanesburg").country("South Africa")
                .pinCode(3781).email("cn@gmail.com").build();
        return UserDetailDTO.builder()
                .pwd("Aug@20")
                .userName("nortje")
                .firstName("chris")
                .lastName("N")
                .dob(LocalDate.now())
                .roleCode("DEALER")
                .addresses(Arrays.asList(address))
                .build();
    }


}
