package com.scm.user.management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@EntityScan(basePackages = "com.scm.user.management.entity")
@SpringBootApplication
public class MsUserManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsUserManagementApplication.class, args);
	}

}
