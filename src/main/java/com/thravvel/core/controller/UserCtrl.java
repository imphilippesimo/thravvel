/**
 * 
 */
package com.thravvel.core.controller;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.thravvel.core.data.entities.User;
import com.thravvel.core.service.contract.IUserService;
import com.thravvel.core.utils.SharedResourcesProvider;
import com.thravvel.core.utils.ThravvelCoreConstants;
import com.thravvel.core.utils.Exceptions.ThravvelCoreException;

/**
 * @author Philippe SIMO <philippechampion58@gmail.com>
 *
 * 
 */
@RestController
public class UserCtrl {

	private static Logger logger = Logger.getLogger(UserCtrl.class);
	private Properties messageCtx = SharedResourcesProvider.getInstance().getFrontMessageCtx();
	private String errorMessage;
	private Map<String, Object> resultMap;

	@Autowired
	IUserService userService;

	@RequestMapping(value = "/users/save", method = RequestMethod.POST)
	public Map<String, Object> saveUser(HttpServletRequest request, @RequestBody User u) {
		resultMap = new HashMap<String, Object>();
		User payload;

		try {
			HttpSession session = request.getSession();
			// Construct a user because the one made by the jsonConverter use a
			// default constructor with plenty of null values
			u = new User("+237" + u.getPhoneNumber(), u.getPassword(), u.getGender());
			payload = userService.createOrUpdateEntity(u);

			session.setAttribute(ThravvelCoreConstants.SESSION_USER, payload);

			resultMap.put(ThravvelCoreConstants.JSON_SUCCESS_KEY, Boolean.TRUE);
			resultMap.put(ThravvelCoreConstants.JSON_PAYLOAD_KEY, payload);
		} catch (ThravvelCoreException tce) {
			resultMap.put(ThravvelCoreConstants.JSON_SUCCESS_KEY, Boolean.FALSE);
			resultMap.put(ThravvelCoreConstants.JSON_MESSAGE_KEY,
					tce.getMessage().substring(tce.getMessage().lastIndexOf('-') + 1));

			logger.error(tce);
		} catch (Exception e) {
			errorMessage = MessageFormat.format(messageCtx.getProperty("THRAVVELCOREUSERCTRL-001"), u.getPhoneNumber());
			resultMap.put(ThravvelCoreConstants.JSON_SUCCESS_KEY, Boolean.FALSE);
			resultMap.put(ThravvelCoreConstants.JSON_MESSAGE_KEY,
					errorMessage.substring(errorMessage.lastIndexOf('-') + 1));
			logger.error(errorMessage, e);
		}
		return resultMap;
	}

	@RequestMapping(value = "/users/connect", method = RequestMethod.POST)
	public Map<String, Object> connectUser(HttpServletRequest request, @RequestBody User u) {
		resultMap = new HashMap<String, Object>();
		User payload;
		logger.debug("Passing by connectUser method");
		try {
			HttpSession session = request.getSession();
			logger.debug("sessionid: " + session.getId());
			payload = userService.connect("+237" + u.getPhoneNumber(), u.getPassword());
			session.setAttribute(ThravvelCoreConstants.SESSION_USER, payload);

			resultMap.put(ThravvelCoreConstants.JSON_SUCCESS_KEY, Boolean.TRUE);
			resultMap.put(ThravvelCoreConstants.JSON_PAYLOAD_KEY, payload);
		} catch (ThravvelCoreException tce) {
			resultMap.put(ThravvelCoreConstants.JSON_SUCCESS_KEY, Boolean.FALSE);
			resultMap.put(ThravvelCoreConstants.JSON_MESSAGE_KEY,
					tce.getMessage().substring(tce.getMessage().lastIndexOf('-') + 1));

			logger.error(tce);
		} catch (Exception e) {
			errorMessage = MessageFormat.format(messageCtx.getProperty("THRAVVELCOREUSERCTRL-002"), u.getPhoneNumber());
			resultMap.put(ThravvelCoreConstants.JSON_SUCCESS_KEY, Boolean.FALSE);
			resultMap.put(ThravvelCoreConstants.JSON_MESSAGE_KEY,
					errorMessage.substring(errorMessage.lastIndexOf('-') + 1));
			logger.error(errorMessage, e);
		}
		return resultMap;
	}

	@RequestMapping(value = "/users/confirm", method = RequestMethod.POST)
	public Map<String, Object> confirmUser(HttpServletRequest request, @RequestBody String confirmationCode) {
		resultMap = new HashMap<String, Object>();
		User payload;
		logger.debug("Passing by confirmUser method");
		try {
			HttpSession session = request.getSession();
			logger.debug("sessionid: " + session.getId());
			logger.debug("user stored in session: " + (User) session.getAttribute(ThravvelCoreConstants.SESSION_USER));
			payload = (User) session.getAttribute(ThravvelCoreConstants.SESSION_USER);
			logger.debug("Entered code: " + confirmationCode);
			String expectedCode = userService.getConfirmationCode(payload.getPhoneNumber());
			logger.debug("Expected code: " + expectedCode);
			if (confirmationCode.equals(expectedCode)) {
				userService.setUserConfirmed(payload.getPhoneNumber(), Boolean.TRUE);
				// payload = userService.createOrUpdateEntity(payload);
				resultMap.put(ThravvelCoreConstants.JSON_SUCCESS_KEY, Boolean.TRUE);
				resultMap.put(ThravvelCoreConstants.JSON_PAYLOAD_KEY, payload);
			} else {
				errorMessage = MessageFormat.format(messageCtx.getProperty("THRAVVELCOREUSERCTRL-004"), "");
				resultMap.put(ThravvelCoreConstants.JSON_SUCCESS_KEY, Boolean.FALSE);
				resultMap.put(ThravvelCoreConstants.JSON_MESSAGE_KEY,
						errorMessage.substring(errorMessage.lastIndexOf('-') + 1));
			}

		} catch (ThravvelCoreException tce) {
			resultMap.put(ThravvelCoreConstants.JSON_SUCCESS_KEY, Boolean.FALSE);
			resultMap.put(ThravvelCoreConstants.JSON_MESSAGE_KEY,
					tce.getMessage().substring(tce.getMessage().lastIndexOf('-') + 1));
			logger.error(tce);
		} catch (Exception e) {
			errorMessage = MessageFormat.format(messageCtx.getProperty("THRAVVELCOREUSERCTRL-003"), "");
			resultMap.put(ThravvelCoreConstants.JSON_SUCCESS_KEY, Boolean.FALSE);
			resultMap.put(ThravvelCoreConstants.JSON_MESSAGE_KEY,
					errorMessage.substring(errorMessage.lastIndexOf('-') + 1));
			logger.error(errorMessage, e);
		}
		return resultMap;
	}

	@RequestMapping(value = "/users/isConfirmed", method = RequestMethod.GET)
	public Map<String, Object> isConfirmed(HttpServletRequest request) {
		resultMap = new HashMap<String, Object>();
		User user;
		logger.debug("Passing by isConfirmed method");
		try {
			HttpSession session = request.getSession();
			logger.debug("sessionid: " + session.getId());
			logger.debug("user stored in session: " + (User) session.getAttribute(ThravvelCoreConstants.SESSION_USER));
			user = (User) session.getAttribute(ThravvelCoreConstants.SESSION_USER);
			Boolean payload;

			if (userService.isConfirmed(user.getPhoneNumber())) {

				payload = Boolean.TRUE;
				resultMap.put(ThravvelCoreConstants.JSON_PAYLOAD_KEY, payload);

			} else {
				payload = Boolean.FALSE;
				resultMap.put(ThravvelCoreConstants.JSON_PAYLOAD_KEY, payload);

			}
			resultMap.put(ThravvelCoreConstants.JSON_SUCCESS_KEY, Boolean.TRUE);

		} catch (ThravvelCoreException tce) {
			resultMap.put(ThravvelCoreConstants.JSON_SUCCESS_KEY, Boolean.FALSE);
			resultMap.put(ThravvelCoreConstants.JSON_MESSAGE_KEY,
					tce.getMessage().substring(tce.getMessage().lastIndexOf('-') + 1));
			logger.error(tce);
		} catch (Exception e) {
			errorMessage = MessageFormat.format(messageCtx.getProperty("THRAVVELCOREUSERCTRL-005"), "");
			resultMap.put(ThravvelCoreConstants.JSON_SUCCESS_KEY, Boolean.FALSE);
			resultMap.put(ThravvelCoreConstants.JSON_MESSAGE_KEY,
					errorMessage.substring(errorMessage.lastIndexOf('-') + 1));
			logger.error(errorMessage, e);
		}
		return resultMap;
	}

}
