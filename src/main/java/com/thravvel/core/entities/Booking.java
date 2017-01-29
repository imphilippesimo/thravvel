/**
 * 
 */
package com.thravvel.core.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 * @author Philippe SIMO <philippechampion58@gmail.com>
 *
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Booking extends BaseClass {
	@OneToOne
	@JoinColumn(name = "userId", unique = true, nullable = false)
	private User booker;
	@ManyToOne
	private Travel travel;
	private Date date;
	private Boolean confirmed;

	/**
	 * @param booker
	 * @param travel
	 * @param date
	 * @param confirmed
	 */
	public Booking(User booker, Travel travel, Date date, Boolean confirmed) {
		super();
		this.booker = booker;
		this.travel = travel;
		this.date = date;
		this.confirmed = confirmed;
	}

	/**
	 * @return the booker
	 */
	public User getBooker() {
		return booker;
	}

	/**
	 * @param booker
	 *            the booker to set
	 */
	public void setBooker(User booker) {
		this.booker = booker;
	}

	/**
	 * @return the travel
	 */
	public Travel getTravel() {
		return travel;
	}

	/**
	 * @param travel
	 *            the travel to set
	 */
	public void setTravel(Travel travel) {
		this.travel = travel;
	}

	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @param date
	 *            the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * @return the confirmed
	 */
	public Boolean getConfirmed() {
		return confirmed;
	}

	/**
	 * @param confirmed
	 *            the confirmed to set
	 */
	public void setConfirmed(Boolean confirmed) {
		this.confirmed = confirmed;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Booking [booker=" + booker + ", travel=" + travel + ", date=" + date + ", confirmed=" + confirmed + "]";
	}

}
