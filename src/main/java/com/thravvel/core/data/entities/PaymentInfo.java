/**
 *
 */
package com.thravvel.core.data.entities;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

/**
 * @author Philippe SIMO <philippechampion58@gmail.com>
 *
 */
@Entity
@Table(name = "PaymentInfo")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class PaymentInfo extends BaseClass {

     /**
	 * 
	 */
	private static final long serialVersionUID = 6795215687632130539L;

	/**
     *
     */
    public PaymentInfo() {
        super();
        // TODO Auto-generated constructor stub
    }

}
