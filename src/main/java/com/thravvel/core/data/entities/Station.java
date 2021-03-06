/**
 * 
 */
package com.thravvel.core.data.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author Philippe SIMO <philippechampion58@gmail.com>
 *
 */
@Entity
@Table(name = "Station")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Station extends BaseClass {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8466124567502542455L;

	@ManyToOne
	private Agency agency;

	private String area;
        
	@Embedded
	private Position position;

	@OneToMany
	@Column(nullable = true)
	private List<Picture> pictures;

	@OneToMany(mappedBy = "source")
	private List<Travel> departures;

	@OneToMany(mappedBy = "destination")
	private List<Travel> arrivals;

	/**
	 * 
	 */
	public Station() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param agency
	 * @param area
	 * @param position
	 * @param pictures
	 * @param departures
	 * @param arrivals
	 */
	public Station(Agency agency, String area, Position position, List<Picture> pictures, List<Travel> departures,
			List<Travel> arrivals) {
		super();
		this.agency = agency;
		this.area = area;
		this.position = position;
		this.pictures = pictures;
		this.departures = departures;
		this.arrivals = arrivals;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Station [agency=" + agency + ", Area=" + area + ", position=" + position + ", pictures=" + pictures
				+ ", departures=" + departures + ", arrivals=" + arrivals + "]";
	}

	/**
	 * @return the agency
	 */
	public Agency getAgency() {
		return agency;
	}

	/**
	 * @param agency
	 *            the agency to set
	 */
	public void setAgency(Agency agency) {
		this.agency = agency;
	}

	/**
	 * @return the area
	 */
	public String getArea() {
		return area;
	}

	/**
	 * @param area
	 *            the area to set
	 */
	public void setArea(String area) {
		this.area = area;
	}

	/**
	 * @return the position
	 */
	public Position getPosition() {
		return position;
	}

	/**
	 * @param position
	 *            the position to set
	 */
	public void setPosition(Position position) {
		this.position = position;
	}

	/**
	 * @return the pictures
	 */
	public List<Picture> getPictures() {
		return pictures;
	}

	/**
	 * @param pictures
	 *            the pictures to set
	 */
	public void setPictures(List<Picture> pictures) {
		this.pictures = pictures;
	}

	/**
	 * @return the departures
	 */
	public List<Travel> getDepartures() {
		return departures;
	}

	/**
	 * @param departures
	 *            the departures to set
	 */
	public void setDepartures(List<Travel> departures) {
		this.departures = departures;
	}

	/**
	 * @return the arrivals
	 */
	public List<Travel> getArrivals() {
		return arrivals;
	}

	/**
	 * @param arrivals
	 *            the arrivals to set
	 */
	public void setArrivals(List<Travel> arrivals) {
		this.arrivals = arrivals;
	}

}
