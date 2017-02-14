/**
 * 
 */
package com.thravvel.core.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import com.thravvel.core.data.entities.BaseClass;

/**
 * @author Philippe SIMO <philippechampion58@gmail.com>
 *
 */
@NoRepositoryBean
public interface IGenericDao<T extends BaseClass> extends JpaRepository<T, Long> {

}
