/**
 * 
 */
package com.thravvel.core.data.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Embeddable;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author Philippe SIMO <philippechampion58@gmail.com>
 *
 */
@Embeddable
public class Position implements Serializable{

        /**
	 * 
	 */
	private static final long serialVersionUID = -8029348930130206834L;

		@Column(name = "longitude")
	private double longitutde;
        
        @Column(name = "latitude")
	private double latitude;
        
        @Temporal(TemporalType.DATE)
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
