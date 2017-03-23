package com.thravvel.core.controller;

import com.thravvel.core.data.entities.Station;
import com.thravvel.core.data.entities.projection.AgencyStation;
import com.thravvel.core.data.entities.projection.Coordinates;
import com.thravvel.core.service.contract.IStationService;
import com.thravvel.core.utils.Exceptions.ThravvelCoreException;
import com.thravvel.core.utils.SharedResourcesProvider;
import com.thravvel.core.utils.ThravvelCoreConstants;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Kenfack Valmy-Roi <roykenvalmy@gmail.com>
 */
@RestController
public class StationCtrl {

    private static final Logger logger = Logger.getLogger(StationCtrl.class);
    private Map<String, Object> resultMap;
    private final Properties messageCtx = SharedResourcesProvider.getInstance().getFrontMessageCtx();
    private String errorMessage;

    @Autowired
    IStationService stationService;

    @RequestMapping(value = "/stations/nearest", method = RequestMethod.POST)
    public Map<String, Object> findNearest(@RequestBody Coordinates coordinates) {
        resultMap = new HashMap<String, Object>();
        try {
            Page<AgencyStation> result = stationService.findNearest(coordinates);
            resultMap.put(ThravvelCoreConstants.JSON_SUCCESS_KEY, Boolean.TRUE);
            resultMap.put(ThravvelCoreConstants.JSON_PAYLOAD_KEY, result);
        } catch (ThravvelCoreException ex) {
            resultMap.put(ThravvelCoreConstants.JSON_SUCCESS_KEY, Boolean.FALSE);
            resultMap.put(ThravvelCoreConstants.JSON_MESSAGE_KEY, ex.getMessage().substring(ex.getMessage().lastIndexOf('-') + 1));
            logger.error(ex);
        } catch (Exception e) {
            errorMessage = MessageFormat.format(messageCtx.getProperty("THRAVVELCORESTATIONCTRL-001"), (Object) null);
            resultMap.put(ThravvelCoreConstants.JSON_SUCCESS_KEY, Boolean.FALSE);
            resultMap.put(ThravvelCoreConstants.JSON_MESSAGE_KEY, errorMessage.substring(errorMessage.lastIndexOf('-') + 1));
            logger.error(errorMessage, e);
        }
        return resultMap;

    }

    @RequestMapping(value = "/stations", method = RequestMethod.GET)
    public Map<String, Object> listStations(@RequestParam(defaultValue = "0", value = "page") int page,
            @RequestParam(defaultValue = "5", value = "size") int size) {
        resultMap = new HashMap<String, Object>();
        try {
            Page<Station> payload = stationService.getAllEntities(page, size);
            resultMap.put(ThravvelCoreConstants.JSON_SUCCESS_KEY, Boolean.TRUE);
            resultMap.put(ThravvelCoreConstants.JSON_PAYLOAD_KEY, payload);

        } catch (ThravvelCoreException tce) {
            resultMap.put(ThravvelCoreConstants.JSON_SUCCESS_KEY, Boolean.FALSE);
            resultMap.put(ThravvelCoreConstants.JSON_MESSAGE_KEY, tce.getMessage().substring(tce.getMessage().lastIndexOf('-') + 1));
            logger.error(tce);

        } catch (Exception e) {
            errorMessage = MessageFormat.format(messageCtx.getProperty("THRAVVELCORESTATIONCTRL-002"), (Object) null);
            resultMap.put(ThravvelCoreConstants.JSON_SUCCESS_KEY, Boolean.FALSE);
            resultMap.put(ThravvelCoreConstants.JSON_MESSAGE_KEY, errorMessage.substring(errorMessage.lastIndexOf('-') + 1));
            logger.error(errorMessage, e);

        }
        return resultMap;
    }

    @RequestMapping(value = "/stations/save", method = RequestMethod.POST)
    public Map<String, Object> saveStation(@RequestBody Station station) {
        resultMap = new HashMap<String, Object>();
        try {
            Station payload = stationService.createOrUpdateEntity(station);
            resultMap.put(ThravvelCoreConstants.JSON_SUCCESS_KEY, Boolean.TRUE);
            resultMap.put(ThravvelCoreConstants.JSON_PAYLOAD_KEY, payload);
        } catch (ThravvelCoreException tce) {
            resultMap.put(ThravvelCoreConstants.JSON_SUCCESS_KEY, Boolean.FALSE);
            resultMap.put(ThravvelCoreConstants.JSON_MESSAGE_KEY, tce.getMessage().substring(tce.getMessage().lastIndexOf('-') + 1));
            logger.error(tce);
        } catch (Exception e) {
            errorMessage = MessageFormat.format(messageCtx.getProperty("THRAVVELCORESTATIONCTRL-003"), station.getArea());
            resultMap.put(ThravvelCoreConstants.JSON_SUCCESS_KEY, Boolean.FALSE);
            resultMap.put(ThravvelCoreConstants.JSON_MESSAGE_KEY, errorMessage.substring(errorMessage.lastIndexOf('-') + 1));
            logger.error(errorMessage, e);
        }
        return resultMap;
    }

}
