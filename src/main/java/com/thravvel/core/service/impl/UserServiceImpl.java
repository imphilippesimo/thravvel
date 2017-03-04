/**
 * 
 */
package com.thravvel.core.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thravvel.core.dao.contract.IUserDao;
import com.thravvel.core.data.entities.Agency;
import com.thravvel.core.data.entities.User;
import com.thravvel.core.service.CommonService;
import com.thravvel.core.service.contract.IUserService;
import com.thravvel.core.utils.Exceptions.ThravvelCoreException;

/**
 * @author Philippe SIMO <philippechampion58@gmail.com>
 *
 */
@Service
@Transactional
public class UserServiceImpl extends CommonService implements IUserService {

	private Logger logger = Logger.getLogger(UserServiceImpl.class);

	private UserServiceImpl() {

	}

	@Autowired
	IUserDao userDao;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1601718626886539641L;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.thravvel.core.service.contract.IUserService#saveUser(java.lang.
	 * String, java.lang.String)
	 */
	@Override
	public User saveUser(String phoneNumberAsString, String password, char gender) throws ThravvelCoreException {
		try {
			User user = new User(phoneNumberAsString, password, gender);
			userDao.save(user);
			return user;

		} catch (Exception e) {
			ThravvelCoreException coreException = new ThravvelCoreException(errorMessagesFilePath,
					"THRAVVELCOREUSERSERVICEERROR-001", new Object[] { phoneNumberAsString });
			logger.error(coreException.getMessage(), e);
			throw coreException;
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.thravvel.core.service.contract.IUserService#connect(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public User connect(String phoneNumberAsString, String password) throws ThravvelCoreException {
		try {
			User user = userDao.getUserByPhoneNumber(phoneNumberAsString);

			if (user == null) {
				ThravvelCoreException coreException = new ThravvelCoreException(errorMessagesFilePath,
						"THRAVVELCOREUSERSERVICEERROR-002", new Object[] { phoneNumberAsString });
				logger.error(coreException.getMessage());
				throw coreException;
			}

			if (!user.getPassword().equals(password)) {
				String messageParam = user.getUserName() == null ? user.getPhoneNumber() : user.getUserName();
				ThravvelCoreException coreException = new ThravvelCoreException(errorMessagesFilePath,
						"THRAVVELCOREUSERSERVICEERROR-003", new Object[] { messageParam });
				logger.error(coreException.getMessage());
				throw coreException;
			}
			return user;

		} catch (ThravvelCoreException e) {
			throw e;
		} catch (Exception ex) {
			ThravvelCoreException coreException = new ThravvelCoreException(errorMessagesFilePath,
					"THRAVVELCOREUSERSERVICEERROR-004", new Object[] { phoneNumberAsString });
			logger.error(coreException.getMessage());
			throw coreException;

		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.thravvel.core.service.IGenericService#createEntity(com.thravvel.core.
	 * data.entities.BaseClass)
	 */
	@Override
	public User createEntity(User entity) throws ThravvelCoreException {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.thravvel.core.service.IGenericService#getEntityById(java.lang.Long)
	 */
	@Override
	public User getEntityById(Long entityId) throws ThravvelCoreException {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.thravvel.core.service.IGenericService#deleteEntity(com.thravvel.core.
	 * data.entities.BaseClass)
	 */
	@Override
	public void deleteEntity(User entity) throws ThravvelCoreException {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.thravvel.core.service.IGenericService#deleteById(java.lang.Long)
	 */
	@Override
	public void deleteById(Long entityId) throws ThravvelCoreException {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.thravvel.core.service.IGenericService#updateEntity(com.thravvel.core.
	 * data.entities.BaseClass)
	 */
	@Override
	public User updateEntity(User entity) throws ThravvelCoreException {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.thravvel.core.service.IGenericService#getAllEntities(int, int)
	 */
	@Override
	public Page<User> getAllEntities(int page, int size) throws ThravvelCoreException {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.thravvel.core.service.IGenericService#findEntities(java.lang.String,
	 * int, int)
	 */
	@Override
	public Page<Agency> findEntities(String keyWord, int page, int size) throws ThravvelCoreException {
		// TODO Auto-generated method stub
		return null;
	}

}
