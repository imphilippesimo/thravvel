/**
 * 
 */
package com.thravvel.core.service.impl;

import java.io.DataOutputStream;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thravvel.core.dao.contract.IUserDao;
import com.thravvel.core.data.entities.User;
import com.thravvel.core.service.CommonService;
import com.thravvel.core.service.contract.IUserService;
import com.thravvel.core.service.specific.chat.ChatLauncher;
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

		// initialize the chat server
		ChatLauncher chatLauncher = new ChatLauncher();
		try {
			chatLauncher.launch();
		} catch (ThravvelCoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

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
	 * @see com.thravvel.core.service.IGenericService#createOrUpdateEntity(com.
	 * thravvel.core.data.entities.BaseClass)
	 */
	@Override
	public User createOrUpdateEntity(User u) throws ThravvelCoreException {
		try {
			String phoneNumberAsString = u.getPhoneNumber();
			User existingUser = userDao.getUserByPhoneNumber(phoneNumberAsString);
			if (existingUser != null) {

				if (existingUser.getPassword().equals(u.getPassword())) {
					// same password: this is a request for update
					userDao.save(u);
					return u;
				} else {
					// password provided not the one retrieved: this is a
					// hijacking attempt, throw exception
					ThravvelCoreException coreException = new ThravvelCoreException(errorMessagesFilePath,
							"THRAVVELCOREUSERSERVICEERROR-005", new Object[] { phoneNumberAsString });
					logger.error(coreException.getMessage());
					throw coreException;
				}
			} else {
				// new user: register him
				userDao.save(u);
				// send confirmation code
				sendConfirmationCode(getConfirmationCode(u.getPhoneNumber()), u.getPhoneNumber());
				return u;
			}
		} catch (ThravvelCoreException tce) {
			throw tce;

		} catch (Exception e) {
			ThravvelCoreException coreException = new ThravvelCoreException(errorMessagesFilePath,
					"THRAVVELCOREUSERSERVICEERROR-001", new Object[] { u.getPhoneNumber() });
			logger.error(coreException.getMessage(), e);
			throw coreException;
		}

	}

	/**
	 * @param confirmationCode
	 */
	@SuppressWarnings("unchecked")
	private void sendConfirmationCode(String confirmationCode, String phoneNumberAsString)
			throws ThravvelCoreException {

		String urlAsString = "https://api.orange.com/smsmessaging/v1/outbound/tel%3A%2B22500000000/requests";
		URL url;
		try {
			url = new URL(urlAsString);
			HttpsURLConnection connexion = (HttpsURLConnection) url.openConnection();
			connexion.setRequestMethod("POST");
			connexion.setRequestProperty("Authorization", "Bearer 8BZ7PQ7zLbtAVTxafgePXDxlmW19");
			connexion.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
			JSONObject data = new JSONObject();
			JSONObject outboundSMSMessageRequest = new JSONObject();
			JSONObject outboundSMSTextMessage = new JSONObject();
			outboundSMSMessageRequest.put("address", "tel:" + phoneNumberAsString);
			outboundSMSTextMessage.put("message",
					"Pour confirmer votre compte, veuillez entrer le code: " + confirmationCode);
			outboundSMSMessageRequest.put("outboundSMSTextMessage", outboundSMSTextMessage);
			outboundSMSMessageRequest.put("senderAddress", "tel:+22500000000");
			data.put("outboundSMSMessageRequest", outboundSMSMessageRequest);
			String dataAsString = data.toString();
			connexion.setDoOutput(Boolean.TRUE);
			DataOutputStream wr = new DataOutputStream(connexion.getOutputStream());
			wr.writeBytes(dataAsString);
			wr.flush();
			wr.close();
			logger.info("Sms Sending request response = " + connexion.getResponseCode() + " - "
					+ connexion.getResponseMessage());

		} catch (Exception e) {
			ThravvelCoreException coreException = new ThravvelCoreException(errorMessagesFilePath,
					"THRAVVELCOREUSERSERVICEERROR-006", new Object[] { phoneNumberAsString });
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
			logger.error(coreException.getMessage(), ex);
			throw coreException;

		}

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
	public Page<User> findEntities(String keyWord, int page, int size) throws ThravvelCoreException {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.thravvel.core.service.contract.IUserService#getConfirmationCode(java.
	 * lang.String)
	 */
	@Override
	public String getConfirmationCode(String phoneNumberAsString) throws ThravvelCoreException {
		try {
			String confirmationCode = userDao.getConfirmationCodeByPhoneNumber(phoneNumberAsString);
			return confirmationCode;
		} catch (Exception e) {
			ThravvelCoreException coreException = new ThravvelCoreException(errorMessagesFilePath,
					"THRAVVELCOREUSERSERVICEERROR-007", new Object[] { phoneNumberAsString });
			logger.error(coreException.getMessage(), e);
			throw coreException;
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.thravvel.core.service.contract.IUserService#setUserConfirmed(java.
	 * lang.String, java.lang.Boolean)
	 */
	@Override
	public void setUserConfirmed(String phoneNumberAsString, Boolean value) throws ThravvelCoreException {
		try {
			userDao.setConfirmationCodeByPhoneNumber(phoneNumberAsString, value);
		} catch (Exception e) {
			ThravvelCoreException coreException = new ThravvelCoreException(errorMessagesFilePath,
					"THRAVVELCOREUSERSERVICEERROR-008", new Object[] { phoneNumberAsString });
			logger.error(coreException.getMessage(), e);
			throw coreException;
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.thravvel.core.service.contract.IUserService#isConfirmed(java.lang.
	 * String)
	 */
	@Override
	public boolean isConfirmed(String phoneNumberAsString) throws ThravvelCoreException {
		try {
			return userDao.isConfirmed(phoneNumberAsString);

		} catch (Exception e) {
			ThravvelCoreException coreException = new ThravvelCoreException(errorMessagesFilePath,
					"THRAVVELCOREUSERSERVICEERROR-009", new Object[] { phoneNumberAsString });
			logger.error(coreException.getMessage(), e);
			throw coreException;
		}

	}

}
