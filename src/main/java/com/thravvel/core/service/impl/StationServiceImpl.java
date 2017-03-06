package com.thravvel.core.service.impl;

import com.thravvel.core.dao.contract.IStationDao;
import com.thravvel.core.data.entities.Station;
import com.thravvel.core.data.entities.projection.AgencyStation;
import com.thravvel.core.data.entities.projection.Coordinates;
import com.thravvel.core.service.CommonService;
import com.thravvel.core.service.contract.IStationService;
import com.thravvel.core.utils.Exceptions.ThravvelCoreException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Kenfack Valmy-Roi <roykenvalmy@gmail.com>
 */
@Service
@Transactional
public class StationServiceImpl extends CommonService implements IStationService {

    private StationServiceImpl() {

    }

    private static final Logger logger = Logger.getLogger(StationServiceImpl.class);

    @Autowired
    IStationDao stationDao;

    /**
     *
     */
    private static final long serialVersionUID = 5312654627637510804L;

    
    public Station createOrUpdateEntity(Station entity) throws ThravvelCoreException {
        try {
            return stationDao.save(entity);
        } catch (Exception e) {
            ThravvelCoreException coreException = new ThravvelCoreException(errorMessagesFilePath,
                    "THRAVVELCORESTATIONSERVICEERROR-002", new Object[]{entity.getArea()});
            logger.error(coreException.getMessage(), e);
            throw coreException;
        }
    }

    public Station getEntityById(Long entityId) throws ThravvelCoreException {
        try {
            return stationDao.findOne(entityId);
        } catch (Exception e) {
            ThravvelCoreException coreException = new ThravvelCoreException(errorMessagesFilePath,
                    "THRAVVELCORESTATIONSERVICEERROR-003");
            logger.error(coreException.getMessage(), e);
            throw coreException;
        }
    }

    public void deleteEntity(Station entity) throws ThravvelCoreException {
        try {
            stationDao.delete(entity);
        } catch (Exception e) {
            ThravvelCoreException coreException = new ThravvelCoreException(errorMessagesFilePath,
                    "THRAVVELCORESTATIONSERVICEERROR-004", new Object[]{entity.getId()});
            logger.error(coreException.getMessage(), e);
            throw coreException;
        }
    }

    public void deleteById(Long entityId) throws ThravvelCoreException {
        try {
            Station station = getEntityById(entityId);
            stationDao.delete(station);
        } catch (ThravvelCoreException tce) {
            throw tce;
        } catch (Exception e) {
            ThravvelCoreException coreException = new ThravvelCoreException(errorMessagesFilePath,
                    "THRAVVELCORESTATIONSERVICEERROR-005");
            logger.error(coreException.getMessage(), e);
            throw coreException;
        }
    }

    public Page<Station> getAllEntities(int page, int size) throws ThravvelCoreException {
        try {
            return stationDao.findAll(new PageRequest(page, size));
        } catch (Exception e) {
            ThravvelCoreException coreException = new ThravvelCoreException(errorMessagesFilePath,
                    "THRAVVELCORESTATIONSERVICEERROR-007");
            logger.error(coreException.getMessage(), e);
            throw coreException;

        }
    }

    public Page<Station> findEntities(String keyWord, int page, int size) throws ThravvelCoreException {
        try {
            return stationDao.findEntity("%" + keyWord + "%", new PageRequest(page, size));
        } catch (Exception e) {
            ThravvelCoreException coreException = new ThravvelCoreException(errorMessagesFilePath,
                    "THRAVVELCORESTATIONSERVICEERROR-001");
            logger.error(coreException.getMessage(), e);
            throw coreException;

        }
    }

    public Page<AgencyStation> findNearest(Coordinates coordinates) throws ThravvelCoreException {
        try {
            List<Station> stations = stationDao.findNearest(coordinates.getLatitude(), coordinates.getLongitude(),coordinates.getDistance(),coordinates.getLimite());
            List<AgencyStation> agencies = new ArrayList<AgencyStation>();
            for (Station station : stations) {
                AgencyStation a = new AgencyStation();
                a.setAgencyId(station.getAgency().getId());
                a.setLatitude(station.getPosition().getLatitude());
                a.setLongitude(station.getPosition().getLongitutde());
                a.setName(station.getAgency().getName());
                a.setStationId(station.getId());
                agencies.add(a);
            }
            return new PageImpl<AgencyStation>(agencies);
        } catch (Exception e) {
            ThravvelCoreException coreException = new ThravvelCoreException(errorMessagesFilePath,
                    "THRAVVELCORESTATIONSERVICEERROR-008");
            logger.error(coreException.getMessage(), e);
            throw coreException;
        }

    }

}
