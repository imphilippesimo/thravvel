/**
 * 
 */
package com.thravvel.core.entities;

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
	private long id;

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

}
