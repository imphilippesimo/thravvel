/**
 * 
 */
package com.thravvel.core.data.entities;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * @author Philippe SIMO <philippechampion58@gmail.com>
 *
 */
@MappedSuperclass
public abstract class BaseClass {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Long id;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

}