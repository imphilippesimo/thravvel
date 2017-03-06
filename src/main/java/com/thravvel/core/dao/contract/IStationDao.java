package com.thravvel.core.dao.contract;

import org.springframework.stereotype.Repository;

import com.thravvel.core.dao.IGenericDao;
import com.thravvel.core.data.entities.Station;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author fd
 */
@Repository
public interface IStationDao extends IGenericDao<Station> {

    /**
     *
     * @param latitude
     * @param longitude
     * @param distance in km
     * @param range
     * @return nearest stations
     */
    @Query(value = "SELECT *, ( 6371 * acos( cos( radians(:lat) ) * cos( radians( latitude ) ) * cos( radians( longitude ) - radians(:long) ) + sin( radians(:lat) ) * sin( radians( latitude ) ) ) ) AS distance FROM Station HAVING distance < :dist ORDER BY distance LIMIT 0 , :range ;", nativeQuery = true)
    List<Station> findNearest(@Param("lat") double latitude, @Param("long") double longitude, @Param("dist") double distance, @Param("range") int range);

    @Query("select s from Station s where area like :kw")
    Page<Station> findEntity(@Param("kw") String kw, Pageable pageable);
}
