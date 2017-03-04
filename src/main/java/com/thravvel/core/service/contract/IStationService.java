package com.thravvel.core.service.contract;

import com.thravvel.core.data.entities.Station;
import com.thravvel.core.data.entities.projection.AgencyStation;
import com.thravvel.core.data.entities.projection.Coordinates;
import com.thravvel.core.service.IGenericService;
import com.thravvel.core.utils.Exceptions.ThravvelCoreException;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Kenfack Valmy-Roi <roykenvalmy@gmail.com>
 */
@Transactional
public interface IStationService extends IGenericService<Station>{
 
    public Page<AgencyStation> findNearest(Coordinates coordinates) throws ThravvelCoreException;
}
