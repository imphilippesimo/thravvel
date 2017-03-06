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
@Table(name = "Picture")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Picture extends BaseClass {

    private boolean current;
    private String path;

    /**
     *
     */
    public Picture() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @param current
     * @param path
     */
    public Picture(boolean current, String path) {
        super();
        this.current = current;
        this.path = path;
    }

    /**
     * @return the current
     */
    public boolean isCurrent() {
        return current;
    }

    /**
     * @param current the current to set
     */
    public void setCurrent(boolean current) {
        this.current = current;
    }

    /**
     * @return the path
     */
    public String getPath() {
        return path;
    }

    /**
     * @param path the path to set
     */
    public void setPath(String path) {
        this.path = path;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Picture [current=" + current + ", path=" + path + "]";
    }

}
