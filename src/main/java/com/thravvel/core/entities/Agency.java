/**
 * 
 */
package com.thravvel.core.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;

/**
 * @author Philippe SIMO <philippechampion58@gmail.com>
 *
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Agency extends BaseClass {
	private String name;
	@OneToMany(mappedBy = "agency")
	private List<Station> stations;

	/**
	 * @param name
	 * @param stations
	 */
	public Agency(String name, List<Station> stations) {
		super();
		this.name = name;
		this.stations = stations;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the stations
	 */
	public List<Station> getStations() {
		return stations;
	}

	/**
	 * @param stations
	 *            the stations to set
	 */
	public void setStations(List<Station> stations) {
		this.stations = stations;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Agency [name=" + name + ", stations=" + stations + "]";
	}

}
