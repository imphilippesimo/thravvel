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
@Table(name = "TravelType")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class TravelType extends BaseClass {

	private String label;
	private double price;

	/**
	 * @param label
	 * @param price
	 */
	public TravelType(String label, double price) {
		super();
		this.label = label;
		this.price = price;
	}

	/**
	 * 
	 */
	public TravelType() {
		super();
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TravelType [label=" + label + ", price=" + price + "]";
	}

	/**
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * @param label
	 *            the label to set
	 */
	public void setLabel(String label) {
		this.label = label;
	}

	/**
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * @param price
	 *            the price to set
	 */
	public void setPrice(double price) {
		this.price = price;
	}

}
