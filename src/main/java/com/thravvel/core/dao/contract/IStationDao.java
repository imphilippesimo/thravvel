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

    @Query(value = "select s from Station s join Position p on s.position_id=p.id join  (SELECT *,( 6371 * acos( cos(radians(:lat)) * cos(radians(latitude)) * cos(radians(latitude) - radians(:long)) + sin(radians(:lat)) * sin(radians(latitude)) ) ) AS distance FROM Position WHERE latitude < degrees( asin( sin(radians(:lat)) * cos(:dist / 6371) cos(radians(:lat)) * sin(:dist / 6371) * cos(radians(0)) ))AND latitude > degrees( asin( sin(radians(:lat)) * cos(:dist / 6371) +"
            + "        cos(radians(:lat)) * sin(:dist / 6371) * cos(radians(180)) ))"
            + "  AND longitutde < :long - degrees( atan2(sin(radians(90)) * sin(radians(:dist / 6371)) * cos(radians(:lat)),"
            + "        cos(radians(:dist / 6371)) - sin(radians(:lat)) * sin(radians(:lat))) )"
            + "  AND longitutde > :long + degrees( atan2(sin(radians(90)) * sin(radians(:dist / 6371)) * cos(radians(:dist)),"
            + "        cos(radians(:dist / 6371)) - sin(radians(:lat)) * sin(radians(:lat))) )"
            + " ORDER BY distance LIMIT :range) as temp on temp.id = p.id", nativeQuery = true)
    List<Station> findNearest(@Param("lat") double latitude, @Param("long") double longitude, @Param("dist") int distance, @Param("range") int range);

    @Query("select s from Station s where area like :kw")
    Page<Station> findEntity(@Param("kw") String kw, Pageable pageable);
}
