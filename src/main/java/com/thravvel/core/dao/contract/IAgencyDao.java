/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thravvel.core.dao.contract;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.thravvel.core.dao.IGenericDao;
import com.thravvel.core.data.entities.Agency;

/**
 *
 * @author fd
 */
@Repository
public interface IAgencyDao extends IGenericDao<Agency> {
	@Query("select a from Agency a where name like :kw")
	Page<Agency> findEntity(@Param("kw") String kw, Pageable pageable);

}
