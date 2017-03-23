/**
 *
 */
package com.thravvel.core.test;

import static org.junit.Assert.assertNotNull;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.AnnotationConfigWebContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;

import com.thravvel.core.SpringGlobalConfig;
import com.thravvel.core.dao.contract.IAgencyDao;
import com.thravvel.core.dao.contract.IStationDao;
import com.thravvel.core.dao.contract.IUserDao;
import com.thravvel.core.data.entities.Agency;
import com.thravvel.core.data.entities.Position;
import com.thravvel.core.data.entities.Station;
import com.thravvel.core.data.entities.User;
import com.thravvel.core.utils.ThravvelCoreConstants;

/**
 * @author Philippe SIMO <philippechampion58@gmail.com>
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {SpringGlobalConfig.class}, loader = AnnotationConfigWebContextLoader.class)

public class DaoTest {

    Logger logger = Logger.getLogger(DaoTest.class);

    @Autowired
    IAgencyDao agencyDao;

    @Autowired
    IUserDao userDao;

    @Autowired
    IStationDao stationDao;

    // @Test
    public void agencyDaoShouldNotBeNull() {

        assertNotNull(agencyDao);
    }

    //@Test
    @Ignore
    public void createAgencyWithoutError() {
        Agency agency = new Agency("Touristique");
        agencyDao.save(agency);
        Agency agency2 = new Agency("Danay");
        agencyDao.save(agency2);
        Agency agency3 = new Agency("Wayla");
        agencyDao.save(agency3);
    }

    @Test
    //@Ignore
    public void createSationsWithoutError() {
        //stationDao.deleteAll();
        //agencyDao.deleteAll();
        Agency agency = new Agency("Touristique");
        agency = agencyDao.save(agency);
        Agency agency2 = new Agency("Danay");
        agency2 = agencyDao.save(agency2);
        Agency agency3 = new Agency("Wayla");
        agency3 = agencyDao.save(agency3);
        Agency agency4 = new Agency("General Express");
        agency4 = agencyDao.save(agency4);
        Agency agency5 = new Agency("Finnest");
        agency5 = agencyDao.save(agency5);

        Position p = new Position(15.2729196548462, -4.31589698791504, new Date());
        Station s = new Station();
        s.setArea("area 1");
        s.setPosition(p);
        s.setAgency(agency);
        stationDao.save(s);
        p = new Position(4.055700, 9.732973, new Date());
        s = new Station();
        s.setArea("area 2");
        s.setPosition(p);
        s.setAgency(agency);
        stationDao.save(s);
        p = new Position(15.274486541748, -4.31505489349365, new Date());
        s = new Station();
        s.setArea("area 3");
        s.setPosition(p);
        s.setAgency(agency);
        stationDao.save(s);

        p = new Position(15.30588722229, -4.31317090988159, new Date());
        s = new Station();
        s.setArea("area 4");
        s.setPosition(p);
        s.setAgency(agency2);
        stationDao.save(s);
        p = new Position(3.850808, 11.499534, new Date());
        s = new Station();
        s.setArea("area 5");
        s.setPosition(p);
        s.setAgency(agency2);
        stationDao.save(s);
        p = new Position(15.2927083969116, -4.31674766540527, new Date());
        s = new Station();
        s.setArea("area 6");
        s.setPosition(p);
        s.setAgency(agency2);
        stationDao.save(s);

        p = new Position(15.2804012298584, -4.31317090988159, new Date());
        s = new Station();
        s.setArea("area 7");
        s.setPosition(p);
        s.setAgency(agency3);
        stationDao.save(s);
        p = new Position(15.3240661621094, -4.30670356750488, new Date());
        s = new Station();
        s.setArea("area 8");
        s.setPosition(p);
        s.setAgency(agency3);
        stationDao.save(s);
        p = new Position(15.3157920837402, -4.30106353759766, new Date());
        s = new Station();
        s.setArea("area 9");
        s.setPosition(p);
        s.setAgency(agency3);
        stationDao.save(s);

        p = new Position(3.844253, 11.494435, new Date());
        s = new Station();
        s.setArea("area 10");
        s.setPosition(p);
        s.setAgency(agency4);
        stationDao.save(s);
        p = new Position(3.857469, 11.503168, new Date());
        s = new Station();
        s.setArea("area 11");
        s.setPosition(p);
        s.setAgency(agency4);
        stationDao.save(s);
        p = new Position(4.064009, 9.760496, new Date());
        s = new Station();
        s.setArea("area 12");
        s.setPosition(p);
        s.setAgency(agency4);
        stationDao.save(s);

        p = new Position(4.060785, 9.739658, new Date());
        s = new Station();
        s.setArea("area 13");
        s.setPosition(p);
        s.setAgency(agency5);
        stationDao.save(s);
        p = new Position(3.850808, 11.499534, new Date());
        s = new Station();
        s.setArea("area 14");
        s.setPosition(p);
        s.setAgency(agency5);
        stationDao.save(s);
        p = new Position(4.055700, 9.732973, new Date());
        s = new Station();
        s.setArea("area 15");
        s.setPosition(p);
        s.setAgency(agency5);
        stationDao.save(s);

    }

    //@Test
    @Ignore
    public void nearestStationsWithoutError() {
        System.out.println("TEST");
        List<Station> stations = stationDao.findNearest(-4.316359, 15.286111, 10, 4);
        System.out.println("RESULT SIZE " + stations.size());
        for (Station station : stations) {
            System.out.println(station);
        }
    }

    //@Test
    @Ignore
    public void createUsersWithoutError() {

        User user;
        for (int j = 693900; j < 694000; j++) {
            user = new User(String.valueOf(j), String.valueOf(j) + "pass",
                    ThravvelCoreConstants.FEMALE);
            userDao.save(user);

        }
    }
}
