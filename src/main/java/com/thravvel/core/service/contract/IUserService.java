/**
 * 
 */
package com.thravvel.core.service.contract;

import org.springframework.transaction.annotation.Transactional;

import com.thravvel.core.data.entities.User;
import com.thravvel.core.service.IGenericService;
import com.thravvel.core.utils.Exceptions.ThravvelCoreException;

/**
 * @author Philippe SIMO <philippechampion58@gmail.com>
 *
 */
@Transactional
public interface IUserService extends IGenericService<User> {

	/**
	 * @param phoneNumberAsString
	 * @param password
	 * @param gender
	 * @return
	 * @throws ThravvelCoreException
	 */
	public User saveUser(String phoneNumberAsString, String password, char gender) throws ThravvelCoreException;

	/**
	 * @param phoneNumberAsString
	 * @param password
	 * @return
	 */
	public User connect(String phoneNumberAsString, String password) throws ThravvelCoreException;

}
