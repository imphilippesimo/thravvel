/**
 * 
 */
package com.thravvel.core.data.entities;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author Philippe SIMO <philippechampion58@gmail.com>
 *
 */
@Entity
@Table(name = "Rating")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Rating extends BaseClass {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6201478210335070641L;
	@OneToOne
	@JoinColumn(name = "raterUserId", unique = true, nullable = false, updatable = false)
	private User rater;
	@OneToOne
	@JoinColumn(name = "ratedStationId", unique = true, nullable = false, updatable = false)
	private Station rated;
	private int rate;

	/**
	 * 
	 */
	public Rating() {
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
		return "Rating [rater=" + rater + ", rated=" + rated + ", rate=" + rate + "]";
	}

	/**
	 * @return the rater
	 */
	public User getRater() {
		return rater;
	}

	/**
	 * @param rater
	 *            the rater to set
	 */
	public void setRater(User rater) {
		this.rater = rater;
	}

	/**
	 * @return the rated
	 */
	public Station getRated() {
		return rated;
	}

	/**
	 * @param rated
	 *            the rated to set
	 */
	public void setRated(Station rated) {
		this.rated = rated;
	}

	/**
	 * @return the rate
	 */
	public int getRate() {
		return rate;
	}

	/**
	 * @param rate
	 *            the rate to set
	 */
	public void setRate(int rate) {
		this.rate = rate;
	}

	/**
	 * @param rater
	 * @param rated
	 * @param rate
	 */
	public Rating(User rater, Station rated, int rate) {
		super();
		this.rater = rater;
		this.rated = rated;
		this.rate = rate;
	}

}
