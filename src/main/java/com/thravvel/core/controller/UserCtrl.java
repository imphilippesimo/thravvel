/**
 * 
 */
package com.thravvel.core.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.thravvel.core.data.entities.User;
import com.thravvel.core.service.contract.IUserService;
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
	private Map<String, Object> resultMap;

	@Autowired
	IUserService userService;

	@RequestMapping(value = "/users/save", method = RequestMethod.POST)
	public Map<String, Object> saveUser(
			@RequestParam(defaultValue = "", value = "phoneNumber") String phoneNumberAsString,
			@RequestParam(defaultValue = "", value = "password") String password,
			@RequestParam(defaultValue = "\'\'", value = "gender") char gender) {
		resultMap = new HashMap<String, Object>();
		User payload;
		try {
			payload = userService.saveUser(phoneNumberAsString, password, gender);

			resultMap.put(ThravvelCoreConstants.JSON_SUCCESS_KEY, Boolean.TRUE);
			resultMap.put(ThravvelCoreConstants.JSON_PAYLOAD_KEY, payload);
		} catch (ThravvelCoreException tce) {
			resultMap.put(ThravvelCoreConstants.JSON_SUCCESS_KEY, Boolean.FALSE);
			resultMap.put(ThravvelCoreConstants.JSON_MESSAGE_KEY,
					tce.getMessage().substring(tce.getMessage().lastIndexOf('-') + 1));

			logger.error(tce);
		} catch (Exception e) {
			// TODO:get the personalized message by code from error messages
			// properties file, instead of e.getMessage()
			resultMap.put(ThravvelCoreConstants.JSON_SUCCESS_KEY, Boolean.FALSE);
			resultMap.put(ThravvelCoreConstants.JSON_MESSAGE_KEY, e.getMessage());
			logger.error("Error occured", e);
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
			// TODO:get the personalized message by code from error messages
			// properties file, instead of e.getMessage()
			resultMap.put(ThravvelCoreConstants.JSON_SUCCESS_KEY, Boolean.FALSE);
			resultMap.put(ThravvelCoreConstants.JSON_MESSAGE_KEY, e.getMessage());
			logger.error("Error occured", e);
		}
		return resultMap;
	}

}
