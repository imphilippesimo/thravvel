/**
 * 
 */
package com.thravvel.core.data.entities;

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
public class Travel extends BaseClass {

	@ManyToOne
	private Station source;
	@ManyToOne
	private Station destination;
	@OneToOne
	@JoinColumn(name = "TypeId", unique = true, nullable = false, updatable = false)
	private TravelType type;
	private Date departureTime;
	private Date arrivalTime;
	private int nbChairs;
	private int remainingChairs;

	/**
	 * 
	 */
	public Travel() {
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
		return "Travel [source=" + source + ", destination=" + destination + ", type=" + type + ", departureTime="
				+ departureTime + ", arrivalTime=" + arrivalTime + ", nbChairs=" + nbChairs + ", remainingChairs="
				+ remainingChairs + "]";
	}

	/**
	 * @return the nbChairs
	 */
	public int getNbChairs() {
		return nbChairs;
	}

	/**
	 * @param nbChairs
	 *            the nbChairs to set
	 */
	public void setNbChairs(int nbChairs) {
		this.nbChairs = nbChairs;
	}

	/**
	 * @return the remainingChairs
	 */
	public int getRemainingChairs() {
		return remainingChairs;
	}

	/**
	 * @param remainingChairs
	 *            the remainingChairs to set
	 */
	public void setRemainingChairs(int remainingChairs) {
		this.remainingChairs = remainingChairs;
	}

	/**
	 * @param source
	 * @param destination
	 * @param type
	 * @param departureTime
	 * @param arrivalTime
	 * @param nbPlaces
	 */
	public Travel(Station source, Station destination, TravelType type, Date departureTime, Date arrivalTime,
			int nbChairs, int remainingChairs) {
		super();
		this.source = source;
		this.destination = destination;
		this.type = type;
		this.departureTime = departureTime;
		this.arrivalTime = arrivalTime;
		this.nbChairs = nbChairs;
		this.remainingChairs = remainingChairs;
	}

	/**
	 * @return the source
	 */
	public Station getSource() {
		return source;
	}

	/**
	 * @param source
	 *            the source to set
	 */
	public void setSource(Station source) {
		this.source = source;
	}

	/**
	 * @return the destination
	 */
	public Station getDestination() {
		return destination;
	}

	/**
	 * @param destination
	 *            the destination to set
	 */
	public void setDestination(Station destination) {
		this.destination = destination;
	}

	/**
	 * @return the type
	 */
	public TravelType getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(TravelType type) {
		this.type = type;
	}

	/**
	 * @return the departureTime
	 */
	public Date getDepartureTime() {
		return departureTime;
	}

	/**
	 * @param departureTime
	 *            the departureTime to set
	 */
	public void setDepartureTime(Date departureTime) {
		this.departureTime = departureTime;
	}

	/**
	 * @return the arrivalTime
	 */
	public Date getArrivalTime() {
		return arrivalTime;
	}

	/**
	 * @param arrivalTime
	 *            the arrivalTime to set
	 */
	public void setArrivalTime(Date arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

}
