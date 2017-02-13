/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thravvel.core.dao.contract;

import org.springframework.stereotype.Repository;

import com.thravvel.core.dao.IGenericDao;
import com.thravvel.core.entities.Booking;

/**
 *
 * @author fd
 */
@Repository
public interface IBookingDao extends IGenericDao<Booking> {

}
