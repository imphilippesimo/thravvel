/**
 * 
 */
package com.thravvel.core.data.entities;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

/**
 * @author Philippe SIMO <philippechampion58@gmail.com>
 *
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class PaymentInfo extends BaseClass {

	/**
	 * 
	 */
	public PaymentInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

}
