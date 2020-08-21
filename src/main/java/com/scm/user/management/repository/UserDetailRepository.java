package com.scm.user.management.repository;

import com.scm.user.management.entity.UserDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface UserDetailRepository extends JpaRepository<UserDetail, Integer> {

    UserDetail findByFirstNameAndLastNameAndDob(String firstName, String lastName, LocalDate dob);
}