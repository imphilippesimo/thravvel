/**
 *
 */
package com.thravvel.core.data.entities;

import java.io.Serializable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * @author Philippe SIMO <philippechampion58@gmail.com>
 *
 */
@MappedSuperclass
public abstract class BaseClass implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

}
