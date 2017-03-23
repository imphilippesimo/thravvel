/**
 *
 */
package com.thravvel.core.data.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author Philippe SIMO <philippechampion58@gmail.com>
 *
 */
@Entity
@Table(name = "Comment")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Comment extends GeneralComment {

    /**
	 * 
	 */
	private static final long serialVersionUID = -1415463627341217700L;
	@OneToMany(mappedBy = "parent")
    private List<SubComment> subComments;

    /**
     */
    public Comment() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @param subComments
     * @param reactions
     * @param commentator
     * @param commented
     * @param content
     * @param abusiveRate
     */
    public Comment(List<SubComment> subComments, List<String> reactions, User commentator, Station commented,
            String content, Integer abusiveRate) {
        super(reactions, commentator, commented, content, abusiveRate);
        this.subComments = subComments;
    }

    /**
     * @return the subComments
     */
    public List<SubComment> getSubComments() {
        return subComments;
    }

    /**
     * @param subComments the subComments to set
     */
    public void setSubComments(List<SubComment> subComments) {
        this.subComments = subComments;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Comment [subComments=" + subComments + "]";
    }

}
