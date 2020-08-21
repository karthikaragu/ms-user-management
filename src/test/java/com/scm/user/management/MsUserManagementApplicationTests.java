package com.scm.user.management;

import com.scm.user.management.controller.UserDetailController;
import com.scm.user.management.repository.UserDetailRepository;
import com.scm.user.management.service.UserDetailService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MsUserManagementApplicationTests {

    @Autowired
    private UserDetailRepository userDetailRepository;

    @Autowired
    private UserDetailController userDetailController;

    @Autowired
    private UserDetailService userDetailService;

	@Test
	void contextLoads() {
        Assert.assertNotNull(userDetailRepository);
        Assert.assertNotNull(userDetailController);
        Assert.assertNotNull(userDetailService);
	}

}
