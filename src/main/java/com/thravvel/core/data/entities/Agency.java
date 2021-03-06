/**
 *
 */
package com.thravvel.core.data.entities;

import java.util.List;
import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlTransient;

/**
 * @author Philippe SIMO <philippechampion58@gmail.com>
 *
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "Agency")
public class Agency extends BaseClass {

    /**
	 * 
	 */
	private static final long serialVersionUID = 4832640222935879427L;

	@Column(name = "name")
    private String name;
    
    @XmlTransient
    @Transient
    @OneToMany(mappedBy = "agency")
    private List<Station> stations;

    /**
     *
     */
    public Agency() {
        super();
        // TODO Auto-generated constructor stub
    }

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
     * @param name
     */
    public Agency(String name) {
        
        this.name = name;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
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
     * @param stations the stations to set
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
        return "Agency [name=" + name +  "]";
    }

}
