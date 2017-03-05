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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
	public Map<String, Object> saveUser(HttpServletRequest request,
			@RequestParam(defaultValue = "", value = "phoneNumber") String phoneNumberAsString,
			@RequestParam(defaultValue = "", value = "password") String password,
			@RequestParam(defaultValue = "\'\'", value = "gender") char gender) {
		resultMap = new HashMap<String, Object>();
		User payload;

		try {
			HttpSession session = request.getSession();
			payload = userService.saveUser(phoneNumberAsString, password, gender);
			session.setAttribute(ThravvelCoreConstants.SESSION_USER, payload);

			resultMap.put(ThravvelCoreConstants.JSON_SUCCESS_KEY, Boolean.TRUE);
			resultMap.put(ThravvelCoreConstants.JSON_PAYLOAD_KEY, payload);
		} catch (ThravvelCoreException tce) {
			resultMap.put(ThravvelCoreConstants.JSON_SUCCESS_KEY, Boolean.FALSE);
			resultMap.put(ThravvelCoreConstants.JSON_MESSAGE_KEY,
					tce.getMessage().substring(tce.getMessage().lastIndexOf('-') + 1));

			logger.error(tce);
		} catch (Exception e) {
			errorMessage = MessageFormat.format(messageCtx.getProperty("THRAVVELCOREUSERCTRL-001"),
					phoneNumberAsString);
			resultMap.put(ThravvelCoreConstants.JSON_SUCCESS_KEY, Boolean.FALSE);
			resultMap.put(ThravvelCoreConstants.JSON_MESSAGE_KEY,
					errorMessage.substring(errorMessage.lastIndexOf('-') + 1));
			logger.error(errorMessage, e);
		}
		return resultMap;
	}

	@RequestMapping(value = "/users/connect", method = RequestMethod.GET)
	public Map<String, Object> connectUser(
			@RequestParam(defaultValue = "", value = "phoneNumber") String phoneNumberAsString,
			@RequestParam(defaultValue = "", value = "password") String password) {
		resultMap = new HashMap<String, Object>();
		User payload;
		try {
			payload = userService.connect(phoneNumberAsString, password);

			resultMap.put(ThravvelCoreConstants.JSON_SUCCESS_KEY, Boolean.TRUE);
			resultMap.put(ThravvelCoreConstants.JSON_PAYLOAD_KEY, payload);
		} catch (ThravvelCoreException tce) {
			resultMap.put(ThravvelCoreConstants.JSON_SUCCESS_KEY, Boolean.FALSE);
			resultMap.put(ThravvelCoreConstants.JSON_MESSAGE_KEY,
					tce.getMessage().substring(tce.getMessage().lastIndexOf('-') + 1));

			logger.error(tce);
		} catch (Exception e) {
			errorMessage = MessageFormat.format(messageCtx.getProperty("THRAVVELCOREUSERCTRL-002"),
					phoneNumberAsString);
			resultMap.put(ThravvelCoreConstants.JSON_SUCCESS_KEY, Boolean.FALSE);
			resultMap.put(ThravvelCoreConstants.JSON_MESSAGE_KEY,
					errorMessage.substring(errorMessage.lastIndexOf('-') + 1));
			logger.error(errorMessage, e);
		}
		return resultMap;
	}

	@RequestMapping(value = "/users/confirm", method = RequestMethod.GET)
	public Map<String, Object> confirmUser(HttpServletRequest request,
			@RequestParam(defaultValue = "", value = "confirmationCode") String confirmationCode) {
		resultMap = new HashMap<String, Object>();
		User user;

		try {
			HttpSession session = request.getSession();
			user = (User) session.getAttribute(ThravvelCoreConstants.SESSION_USER);
			if (confirmationCode.equals(user.getConfirmationCode())) {
				user.setConfirmed(Boolean.TRUE);
				userService.createOrUpdateEntity(user);
				resultMap.put(ThravvelCoreConstants.JSON_SUCCESS_KEY, Boolean.TRUE);
				resultMap.put(ThravvelCoreConstants.JSON_PAYLOAD_KEY, user);
			} else {
				resultMap.put(ThravvelCoreConstants.JSON_SUCCESS_KEY, Boolean.FALSE);
				resultMap.put(ThravvelCoreConstants.JSON_MESSAGE_KEY, "Wrong Confirmation code !");
			}

		} catch (ThravvelCoreException tce) {
			resultMap.put(ThravvelCoreConstants.JSON_SUCCESS_KEY, Boolean.FALSE);
			resultMap.put(ThravvelCoreConstants.JSON_MESSAGE_KEY,
					tce.getMessage().substring(tce.getMessage().lastIndexOf('-') + 1));
			logger.error(tce);
		} catch (Exception e) {
			errorMessage = MessageFormat.format(messageCtx.getProperty("THRAVVELCOREUSERCTRL-003"), confirmationCode);
			resultMap.put(ThravvelCoreConstants.JSON_SUCCESS_KEY, Boolean.FALSE);
			resultMap.put(ThravvelCoreConstants.JSON_MESSAGE_KEY,
					errorMessage.substring(errorMessage.lastIndexOf('-') + 1));
			logger.error(errorMessage, e);
		}
		return resultMap;
	}

}
