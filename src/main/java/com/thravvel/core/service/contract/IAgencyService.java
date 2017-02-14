/**
 * 
 */
package com.thravvel.core.service.contract;

import org.springframework.transaction.annotation.Transactional;

import com.thravvel.core.data.entities.Agency;
import com.thravvel.core.service.IGenericService;

/**
 * @author Philippe SIMO <philippechampion58@gmail.com>
 *
 */
@Transactional
public interface IAgencyService extends IGenericService<Agency> {

}
