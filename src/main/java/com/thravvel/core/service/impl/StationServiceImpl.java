package com.thravvel.core.service.impl;

import com.thravvel.core.dao.contract.IStationDao;
import com.thravvel.core.data.entities.Agency;
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

/**
 *
 * @author Kenfack Valmy-Roi <roykenvalmy@gmail.com>
 */
public class StationServiceImpl extends CommonService implements IStationService {
    
    private StationServiceImpl() {

	}

	private static Logger logger = Logger.getLogger(StationServiceImpl.class);

	@Autowired
	       IStationDao stationDao;

	/**
	 * 
	 */
	private static final long serialVersionUID = 5312654627637510804L;

    public Station createEntity(Station entity) throws ThravvelCoreException {
        return stationDao.save(entity);
    }

    public Station getEntityById(Long entityId) throws ThravvelCoreException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void deleteEntity(Station entity) throws ThravvelCoreException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void deleteById(Long entityId) throws ThravvelCoreException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Station updateEntity(Station entity) throws ThravvelCoreException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Page<Station> getAllEntities(int page, int size) throws ThravvelCoreException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Page<Agency> findEntities(String keyWord, int page, int size) throws ThravvelCoreException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Page<AgencyStation> findNearest(Coordinates coordinates) throws ThravvelCoreException {
        Page<Station> stations = stationDao.findEntity(coordinates.getLatitude(), coordinates.getLongitude(), coordinates.getLimite(), coordinates.getDistance(), null);
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
 
    }
    
}
