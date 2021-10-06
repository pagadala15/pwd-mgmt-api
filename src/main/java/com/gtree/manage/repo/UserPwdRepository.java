package com.gtree.manage.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gtree.manage.entity.UserPwd;

/**
 * 
 * @author SrinivasuluP
 * 
 *         JPA repository for updating/saving/finding UserPwd object
 */
@Repository
public interface UserPwdRepository extends JpaRepository<UserPwd, Long> {

	UserPwd findByEmail(String email);

}