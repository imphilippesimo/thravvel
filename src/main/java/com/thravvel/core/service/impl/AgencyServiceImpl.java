/**
 * 
 */
package com.thravvel.core.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thravvel.core.dao.contract.IAgencyDao;
import com.thravvel.core.data.entities.Agency;
import com.thravvel.core.service.CommonService;
import com.thravvel.core.service.contract.IAgencyService;
import com.thravvel.core.utils.Exceptions.ThravvelCoreException;

/**
 * @author Philippe SIMO <philippechampion58@gmail.com>
 *
 */
@Service
@Transactional
public class AgencyServiceImpl extends CommonService implements IAgencyService {

	private AgencyServiceImpl() {

	}

	private static Logger logger = Logger.getLogger(AgencyServiceImpl.class);

	@Autowired
	IAgencyDao agencyDao;

	/**
	 * 
	 */
	private static final long serialVersionUID = 5312654627637510804L;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.thravvel.core.service.IGenericService#createEntity(com.thravvel.core.
	 * entities.BaseClass)
	 */
	@Override
	public Agency createEntity(Agency entity) throws ThravvelCoreException {
		try {
			return agencyDao.save(entity);
		} catch (Exception e) {
			ThravvelCoreException coreException = new ThravvelCoreException(errorMessagesFilePath,
					"THRAVVELCOREAGENCYSERVICEERROR-002", new Object[] { entity.getName() });
			logger.error(coreException.getMessage(), e);
			throw coreException;
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.thravvel.core.service.IGenericService#getEntityById(java.lang.Long)
	 */
	@Override
	public Agency getEntityById(Long entityId) throws ThravvelCoreException {
		try {
			return agencyDao.findOne(entityId);
		} catch (Exception e) {
			ThravvelCoreException coreException = new ThravvelCoreException(errorMessagesFilePath,
					"THRAVVELCOREAGENCYSERVICEERROR-003");
			logger.error(coreException.getMessage(), e);
			throw coreException;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.thravvel.core.service.IGenericService#deleteEntity(com.thravvel.core.
	 * entities.BaseClass)
	 */
	@Override
	public void deleteEntity(Agency entity) throws ThravvelCoreException {
		try {
			agencyDao.delete(entity);

		} catch (Exception e) {
			ThravvelCoreException coreException = new ThravvelCoreException(errorMessagesFilePath,
					"THRAVVELCOREAGENCYSERVICEERROR-004", new Object[] { entity.getName() });
			logger.error(coreException.getMessage(), e);
			throw coreException;
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.thravvel.core.service.IGenericService#deleteById(java.lang.Long)
	 */
	@Override
	public void deleteById(Long entityId) throws ThravvelCoreException {
		try {
			Agency agency = getEntityById(entityId);
			agencyDao.delete(agency);
		} catch (ThravvelCoreException tce) {
			throw tce;
		} catch (Exception e) {
			ThravvelCoreException coreException = new ThravvelCoreException(errorMessagesFilePath,
					"THRAVVELCOREAGENCYSERVICEERROR-005");
			logger.error(coreException.getMessage(), e);
			throw coreException;
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.thravvel.core.service.IGenericService#updateEntity(com.thravvel.core.
	 * entities.BaseClass)
	 */
	@Override
	public Agency updateEntity(Agency entity) throws ThravvelCoreException {
		try {
			return agencyDao.save(entity);

		} catch (Exception e) {
			ThravvelCoreException coreException = new ThravvelCoreException(errorMessagesFilePath,
					"THRAVVELCOREAGENCYSERVICEERROR-006", new Object[] { entity.getName() });
			logger.error(coreException.getMessage(), e);
			throw coreException;
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.thravvel.core.service.IGenericService#getAllEntities(int, int)
	 */
	@Override
	public Page<Agency> getAllEntities(int page, int size) throws ThravvelCoreException {
		try {
			return agencyDao.findAll(new PageRequest(page, size));

		} catch (Exception e) {
			ThravvelCoreException coreException = new ThravvelCoreException(errorMessagesFilePath,
					"THRAVVELCOREAGENCYSERVICEERROR-007");
			logger.error(coreException.getMessage(), e);
			throw coreException;

		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.thravvel.core.service.IGenericService#findEntities(java.lang.String,
	 * int, int)
	 */
	@Override
	public Page<Agency> findEntities(String keyWord, int page, int size) throws ThravvelCoreException {

		try {

			return agencyDao.findEntity("%" + keyWord + "%", new PageRequest(page, size));
		} catch (Exception e) {
			ThravvelCoreException coreException = new ThravvelCoreException(errorMessagesFilePath,
					"THRAVVELCOREAGENCYSERVICEERROR-001");
			logger.error(coreException.getMessage(), e);
			throw coreException;

		}
	}

}
