package com.thravvel.core.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.thravvel.core.data.entities.Agency;
import com.thravvel.core.service.contract.IAgencyService;
import com.thravvel.core.utils.ThravvelCoreConstants;
import com.thravvel.core.utils.Exceptions.ThravvelCoreException;

@RestController
public class AgencyCtrl {

	private static Logger logger = Logger.getLogger(AgencyCtrl.class);
	private Map<String, Object> resultMap;

	@Autowired
	IAgencyService agencyService;

	@RequestMapping(value = "/agencies", method = RequestMethod.GET)
	public Map<String, Object> listAgencies(@RequestParam(defaultValue = "0", value = "page") int page,
			@RequestParam(defaultValue = "5", value = "size") int size) {
		resultMap = new HashMap<String, Object>();
		try {
			Page<Agency> payload = agencyService.getAllEntities(page, size);
			resultMap.put(ThravvelCoreConstants.JSON_SUCCESS_KEY, Boolean.TRUE);
			resultMap.put(ThravvelCoreConstants.JSON_PAYLOAD_KEY, payload);

		} catch (ThravvelCoreException tce) {
			resultMap.put(ThravvelCoreConstants.JSON_SUCCESS_KEY, Boolean.FALSE);
			resultMap.put(ThravvelCoreConstants.JSON_MESSAGE_KEY, tce.getMessage());

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

	@RequestMapping(value = "/agencies/find", method = RequestMethod.GET)
	public Map<String, Object> findAgencies(@RequestParam(defaultValue = "", value = "keyWord") String kw,
			@RequestParam(defaultValue = "0", value = "page") int page,
			@RequestParam(defaultValue = "5", value = "size") int size) {
		resultMap = new HashMap<String, Object>();
		try {
			Page<Agency> payload = agencyService.findEntities("%" + kw + "%", page, size);
			resultMap.put(ThravvelCoreConstants.JSON_SUCCESS_KEY, Boolean.TRUE);
			resultMap.put(ThravvelCoreConstants.JSON_PAYLOAD_KEY, payload);
		} catch (ThravvelCoreException tce) {
			resultMap.put(ThravvelCoreConstants.JSON_SUCCESS_KEY, Boolean.FALSE);
			resultMap.put(ThravvelCoreConstants.JSON_MESSAGE_KEY, tce.getMessage());

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

	@RequestMapping(value = "/agencies/{id}", method = RequestMethod.GET)
	public Map<String, Object> getAgency(@PathVariable("id") Long id) {
		resultMap = new HashMap<String, Object>();
		try {
			Agency payload = agencyService.getEntityById(id);
			resultMap.put(ThravvelCoreConstants.JSON_SUCCESS_KEY, Boolean.TRUE);
			resultMap.put(ThravvelCoreConstants.JSON_PAYLOAD_KEY, payload);
		} catch (ThravvelCoreException tce) {
			resultMap.put(ThravvelCoreConstants.JSON_SUCCESS_KEY, Boolean.FALSE);
			resultMap.put(ThravvelCoreConstants.JSON_MESSAGE_KEY, tce.getMessage());

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

	@RequestMapping(value = "/agencies/save", method = RequestMethod.POST)
	public Map<String, Object> saveAgency(@RequestBody Agency agency) {
		resultMap = new HashMap<String, Object>();
		try {
			Agency payload = agencyService.createEntity(agency);
			resultMap.put(ThravvelCoreConstants.JSON_SUCCESS_KEY, Boolean.TRUE);
			resultMap.put(ThravvelCoreConstants.JSON_PAYLOAD_KEY, payload);
		} catch (ThravvelCoreException tce) {
			resultMap.put(ThravvelCoreConstants.JSON_SUCCESS_KEY, Boolean.FALSE);
			resultMap.put(ThravvelCoreConstants.JSON_MESSAGE_KEY, tce.getMessage());

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

	@RequestMapping(value = "/agencies/update", method = RequestMethod.GET)
	public Map<String, Object> updateAgency(@RequestBody Agency agency) {
		resultMap = new HashMap<String, Object>();
		try {
			Agency payload = agencyService.updateEntity(agency);
			resultMap.put(ThravvelCoreConstants.JSON_SUCCESS_KEY, Boolean.TRUE);
			resultMap.put(ThravvelCoreConstants.JSON_PAYLOAD_KEY, payload);
		} catch (ThravvelCoreException tce) {
			resultMap.put(ThravvelCoreConstants.JSON_SUCCESS_KEY, Boolean.FALSE);
			resultMap.put(ThravvelCoreConstants.JSON_MESSAGE_KEY, tce.getMessage());

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

	@RequestMapping(value = "/agencies/delete/{id}", method = RequestMethod.GET)
	public Map<String, Object> deleteAgency(@PathVariable("id") Long id) {
		resultMap = new HashMap<String, Object>();
		try {
			agencyService.deleteById(id);
			resultMap.put("succes", Boolean.TRUE);
			// TODO:get the personalized message by code from messages
			// properties file
			resultMap.put(ThravvelCoreConstants.JSON_MESSAGE_KEY,
					"Agency with id: " + id + " has been deleted successfully");
		} catch (ThravvelCoreException tce) {
			resultMap.put(ThravvelCoreConstants.JSON_SUCCESS_KEY, Boolean.FALSE);
			resultMap.put(ThravvelCoreConstants.JSON_MESSAGE_KEY, tce.getMessage());

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
