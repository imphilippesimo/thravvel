package com.thravvel.core.controller;

import com.thravvel.core.data.entities.projection.AgencyStation;
import com.thravvel.core.data.entities.projection.Coordinates;
import com.thravvel.core.service.contract.IStationService;
import com.thravvel.core.utils.Exceptions.ThravvelCoreException;
import com.thravvel.core.utils.ThravvelCoreConstants;
import java.util.HashMap;
import java.util.Map;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Kenfack Valmy-Roi <roykenvalmy@gmail.com>
 */
@RestController
public class StationCtrl {

    private static Logger logger = Logger.getLogger(AgencyCtrl.class);
    private Map<String, Object> resultMap;

    @Autowired
    IStationService stationService;

    @RequestMapping(value = "/stations/nearest", method = RequestMethod.GET)
    public Map<String, Object> saveAgency(@RequestBody Coordinates coordinates) {
        resultMap = new HashMap<String, Object>();
        try {
            Page<AgencyStation> result = stationService.findNearest(coordinates);
            resultMap.put(ThravvelCoreConstants.JSON_SUCCESS_KEY, Boolean.TRUE);
            resultMap.put(ThravvelCoreConstants.JSON_PAYLOAD_KEY, result);
        } catch (ThravvelCoreException ex) {
            resultMap.put(ThravvelCoreConstants.JSON_SUCCESS_KEY, Boolean.FALSE);
            resultMap.put(ThravvelCoreConstants.JSON_MESSAGE_KEY, ex.getMessage());
            logger.error(ex);
        } catch (Exception e) {
	    resultMap.put(ThravvelCoreConstants.JSON_SUCCESS_KEY, Boolean.FALSE);
            resultMap.put(ThravvelCoreConstants.JSON_MESSAGE_KEY, e.getMessage());
            logger.error("Error occured", e);
        }
        return resultMap;

    }

}
