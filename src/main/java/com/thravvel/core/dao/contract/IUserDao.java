/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thravvel.core.dao.contract;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.thravvel.core.dao.IGenericDao;
import com.thravvel.core.data.entities.User;

/**
 *
 * @author fd
 */
@Repository
public interface IUserDao extends IGenericDao<User> {

	/**
	 * @param phoneNumberAsString
	 */
	@Query("select u from User u where phoneNumber = :phoneNumber ")
	public User getUserByPhoneNumber(@Param("phoneNumber") String phoneNumberAsString);

	/**
	 * @param phoneNumberAsString
	 * @return
	 */
	@Query("select confirmationCode from User where phoneNumber = :phoneNumber ")
	public String getConfirmationCodeByPhoneNumber(@Param("phoneNumber") String phoneNumberAsString);

	/**
	 * @param phoneNumberAsString
	 * @param value
	 */
	@Modifying(clearAutomatically = true)
	@Query("update User u set u.confirmed=:value where u.phoneNumber = :phoneNumber")
	public void setConfirmationCodeByPhoneNumber(@Param("phoneNumber") String phoneNumberAsString,
			@Param("value") Boolean value);

	/**
	 * @param phoneNumberAsString
	 * @return
	 */
	@Query("select confirmed from User where phoneNumber = :phoneNumber ")
	public boolean isConfirmed(@Param("phoneNumber") String phoneNumberAsString);

}
