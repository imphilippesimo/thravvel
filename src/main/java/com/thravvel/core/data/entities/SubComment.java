/**
 * 
 */
package com.thravvel.core.data.entities;

import java.util.List;

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
@Table(name = "SubComment")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class SubComment extends GeneralComment {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1496491526807462494L;
	@OneToOne
	@JoinColumn(name = "parentCommentId", unique = true, nullable = false, updatable = false)
	private Comment parent;

	/**
	 * 
	 */
	public SubComment() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param parent
	 * @param reactions
	 * @param commentator
	 * @param commented
	 * @param content
	 * @param abusiveRate
	 */
	public SubComment(Comment parent, List<String> reactions, User commentator, Station commented, String content,
			Integer abusiveRate) {
		super(reactions, commentator, commented, content, abusiveRate);
		this.setParent(parent);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SubComment [parent=" + parent + "]";
	}

	/**
	 * @return the parent
	 */
	public Comment getParent() {
		return parent;
	}

	/**
	 * @param parent
	 *            the parent to set
	 */
	public void setParent(Comment parent) {
		this.parent = parent;
	}

}
