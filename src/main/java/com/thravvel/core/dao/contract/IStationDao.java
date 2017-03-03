package com.thravvel.core.dao.contract;

import org.springframework.stereotype.Repository;

import com.thravvel.core.dao.IGenericDao;
import com.thravvel.core.data.entities.Agency;
import com.thravvel.core.data.entities.Station;
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

    @Query("select * from Station s join Position p on s.position_id=p.id\n" +
"join  (SELECT *,\n" +
"    ( 6371 * acos(\n" +
"    cos(radians(:lat)) * cos(radians(latitude)) * cos(radians(latitude) - radians(:long)) +\n" +
"    sin(radians(:lat)) * sin(radians(latitude))\n" +
"    ) ) AS distance\n" +
"FROM `Position`\n" +
"WHERE latitude < degrees( asin( sin(radians(:lat)) * cos(:dist / 6371) +\n" +
"        cos(radians(:lat)) * sin(:dist / 6371) * cos(radians(0)) ))\n" +
"  AND latitude > degrees( asin( sin(radians(:lat)) * cos(:dist / 6371) +\n" +
"        cos(radians(:lat)) * sin(:dist / 6371) * cos(radians(180)) ))\n" +
"  AND longitutde < :long - degrees( atan2(sin(radians(90)) * sin(radians(:dist / 6371)) * cos(radians(:lat)),\n" +
"        cos(radians(:dist / 6371)) - sin(radians(:lat)) * sin(radians(:lat))) )\n" +
"  AND longitutde > :long + degrees( atan2(sin(radians(90)) * sin(radians(:dist / 6371)) * cos(radians(:dist)),\n" +
"        cos(radians(:dist / 6371)) - sin(radians(:lat)) * sin(radians(:lat))) )\n" +
"ORDER BY distance LIMIT :range) as temp on temp.id = p.id;")
	Page<Station> findEntity(@Param("lat") double latitude, @Param("long") double longitude,@Param("dist") int distance, @Param("range") int range , Pageable pageable);
}
