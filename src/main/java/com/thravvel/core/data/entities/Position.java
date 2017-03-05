/**
 * 
 */
package com.thravvel.core.data.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

/**
 * @author Philippe SIMO <philippechampion58@gmail.com>
 *
 */
@Entity
@Table(name = "Position")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Position extends BaseClass {

	private double longitutde;
	private double latitude;
	private Date time;

	/**
	 * 
	 */
	public Position() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the longitutde
	 */
	public double getLongitutde() {
		return longitutde;
	}

	/**
	 * @param longitutde
	 *            the longitutde to set
	 */
	public void setLongitutde(double longitutde) {
		this.longitutde = longitutde;
	}

	/**
	 * @return the latitude
	 */
	public double getLatitude() {
		return latitude;
	}

	/**
	 * @param latitude
	 *            the latitude to set
	 */
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	/**
	 * @return the time
	 */
	public Date getTime() {
		return time;
	}

	/**
	 * @param time
	 *            the time to set
	 */
	public void setTime(Date time) {
		this.time = time;
	}

	/**
	 * @param longitutde
	 * @param latitude
	 * @param time
	 */
	public Position(double longitutde, double latitude, Date time) {
		super();
		this.longitutde = longitutde;
		this.latitude = latitude;
		this.time = time;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Position [longitutde=" + longitutde + ", latitude=" + latitude + ", time=" + time + "]";
	}

}
