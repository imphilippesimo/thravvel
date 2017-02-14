/**
 * 
 */
package com.thravvel.core.service;

import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;

import com.thravvel.core.data.entities.Agency;
import com.thravvel.core.data.entities.BaseClass;
import com.thravvel.core.utils.Exceptions.ThravvelCoreException;

/**
 * @author Philippe SIMO <philippechampion58@gmail.com>
 *
 */
@Transactional
public interface IGenericService<T extends BaseClass> {

	/**
	 * @param entity
	 * @return
	 * @throws ThravvelCoreException
	 */
	T createEntity(T entity) throws ThravvelCoreException;

	/**
	 * @param id
	 * @return
	 * @throws ThravvelCoreException
	 */
	T getEntityById(Long entityId) throws ThravvelCoreException;

	/**
	 * @param entity
	 * @throws ThravvelCoreException
	 */
	void deleteEntity(T entity) throws ThravvelCoreException;

	/**
	 * @param id
	 * @throws ThravvelCoreException
	 */
	void deleteById(Long entityId) throws ThravvelCoreException;

	/**
	 * @param entity
	 * @param personDbId
	 * @throws ThravvelCoreException
	 */
	T updateEntity(T entity) throws ThravvelCoreException;

	/**
	 * @param page
	 * @param size
	 * @return
	 * @throws ThravvelCoreException
	 */
	Page<T> getAllEntities(int page, int size) throws ThravvelCoreException;

	/**
	 * @param kw
	 * @param page
	 * @param size
	 * @return
	 * @throws ThravvelCoreException
	 */
	Page<Agency> findEntities(String keyWord, int page, int size) throws ThravvelCoreException;

}
